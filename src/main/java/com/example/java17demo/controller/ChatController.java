package com.example.java17demo.controller;

import com.example.java17demo.annotation.Auth;
import com.example.java17demo.pojo.vo.FriendsVo;
import com.example.java17demo.service.ChatService;
import com.example.java17demo.util.ResultMap;
import com.example.java17demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private ChatService chatService;

    @Autowired
    private void setChatMapper(ChatService chatService) {
        this.chatService = chatService;
    }

    @Auth
    @GetMapping(value = "/getFriendsList")
    public ResultMap getUserList(HttpServletRequest request) {
        Map<String, Object> authInfo = TokenUtil.verify(TokenUtil.getToken(request));
        Integer userId = (Integer) authInfo.get("uId");
        log.info("authInfo:{}", authInfo);
        List<FriendsVo> list = chatService.getFriendsList(userId);
        ResultMap map = null;
        try {
            map = new ResultMap(200, "success", list);
        } catch (Exception e) {
            map = new ResultMap(500, "failed", e.toString());
        }
        return map;
    }
}
