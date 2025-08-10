package com.example.java17demo.service.impl;

import com.example.java17demo.mapper.ChatMapper;
import com.example.java17demo.pojo.vo.FriendsVo;
import com.example.java17demo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("ChatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Value("${image-url}")
    private String imagePort;

    @Override
    public List<FriendsVo> getFriendsList(Integer userId) {
        List<FriendsVo> list = chatMapper.getFriendsList(userId);
        List<FriendsVo> friendsList = new ArrayList<>();
        for (FriendsVo friendsVo : list) {
            friendsVo.setAvatar(imagePort + friendsVo.getAvatar());
            friendsList.add(friendsVo);
        }
        return friendsList;
    }
}
