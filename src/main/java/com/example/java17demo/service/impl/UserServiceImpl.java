package com.example.java17demo.service.impl;

import com.example.java17demo.mapper.UserMapper;
import com.example.java17demo.pojo.vo.UserVO;
import com.example.java17demo.service.UserService;
import com.example.java17demo.util.Encryption;
import com.example.java17demo.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {this.userMapper = userMapper;}

    @Value("${image-url}")
    private String imagePort;

    @Override
    public PageUtil<UserVO> getUserList(String userName, String nickName, Integer roleId, Integer nationId, Integer pageNum, Integer pageSize) {
        Integer _pageNum = (pageNum - 1) * pageSize;
        List<UserVO> userVOList = userMapper.getUserList(userName,nickName,roleId,nationId, _pageNum, pageSize);
        Integer totalRecord = userMapper.getUserTotalRecord(userName,nickName,roleId,nationId);
        PageUtil pageUtil = new PageUtil(pageNum, pageSize, totalRecord, userVOList);
        return pageUtil;
    }

    @Override
    public UserVO login(String userName, String password) {
        log.info("login info " + userName + " ------ " + password);
        try {
            password = new Encryption().encodePwd(password);
            log.info("encryption login info " + userName + " ------ " + password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userMapper.login(userName, password);
    }

    @Override
    public UserVO getUserInfo(Integer userId) {
         UserVO user = userMapper.getUserInfo(userId);
         user.setAvatar(imagePort+user.getAvatar());
        return user;
    }
}
