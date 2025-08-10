package com.example.java17demo.controller;

import com.example.java17demo.annotation.Auth;
import com.example.java17demo.pojo.dto.UserListDTO;
import com.example.java17demo.pojo.dto.UserLoginDTO;
import com.example.java17demo.pojo.vo.UserVO;
import com.example.java17demo.service.UserService;
import com.example.java17demo.util.PageUtil;
import com.example.java17demo.util.RSAEncryption;
import com.example.java17demo.util.ResultMap;
import com.example.java17demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {


    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Value("${image-url}")
    private String imagePort;

    @PostMapping(value = "/login")
    public ResultMap login(HttpServletResponse response, UserLoginDTO userLoginDTO) {
        log.info("/user/login params ===> {}", userLoginDTO.toString());
        if (userLoginDTO.getUserName() == null || userLoginDTO.getUserName().isEmpty()) {
            return new ResultMap(500, "用户名不能为空", "");
        }
        if (userLoginDTO.getPassword() == null || userLoginDTO.getPassword().isEmpty()) {
            return new ResultMap(500, "密码不能为空", "");
        }
        try {
            String decryptPwd = RSAEncryption.decrypt(userLoginDTO.getPassword());
            userLoginDTO.setPassword(decryptPwd);
            log.info("decryptPwd ===> {}", decryptPwd);
            UserVO userVO = userService.login(userLoginDTO.getUserName(), userLoginDTO.getPassword());
            if (userVO != null) {
                if (userVO.getAvatar() != null && !userVO.getAvatar().isEmpty()) {
                    String avatarUrl = imagePort + userVO.getAvatar();
                    userVO.setAvatar(avatarUrl);
                }
                String token = TokenUtil.sign(userVO.getUserName(), userVO.getId().toString(),userVO.getRoleId());
                Map<String, Object> data = new HashMap<>();
//                data.put("userInfo", userVO);
                data.put("token", token);
                Cookie cookie = new Cookie("auth", token);
                cookie.setHttpOnly(true); // 设置 HttpOnly
                cookie.setSecure(true); // 如果使用 HTTPS，设置为 true
                cookie.setPath("/"); // 可访问的路径
                cookie.setMaxAge(3600); // 设置过期时间（单位：秒）

                response.addCookie(cookie);
                response.setStatus(HttpServletResponse.SC_OK);
                return new ResultMap(200, "登录成功", data);
            } else {
                return new ResultMap(500, "用户名或密码错误", "");
            }
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResultMap(500, "failed", msg);
        }
    }

    @Auth
    @GetMapping(value = "/getUserList")
    public ResultMap getUserList(UserListDTO userListDTO) {
        log.info("userListDTO {}", userListDTO);
        PageUtil<UserVO> pageUtil = userService.getUserList(userListDTO.getUserName(), userListDTO.getNickName(), userListDTO.getRoleId(), userListDTO.getNationId(), userListDTO.getPageNum(), userListDTO.getPageSize());
        ResultMap map;
        try {
            map = new ResultMap(200, "success", pageUtil);
        } catch (Exception e) {
            map = new ResultMap(500, "failed", e.toString());
        }
        return map;
    }

    @Auth
    @GetMapping(value = "/getUserInfo")
    public ResultMap getUserInfo(HttpServletRequest request) {
        Map<String, Object> authInfo = TokenUtil.verify(TokenUtil.getToken(request));
        Integer userId = (Integer) authInfo.get("uId");
        UserVO user = userService.getUserInfo(userId);
        ResultMap map;
        try {
            map = new ResultMap(200, "success", user);
        } catch (Exception e) {
            map = new ResultMap(500, "failed", e.toString());
        }
        return map;
    }
}
