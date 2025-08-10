package com.example.java17demo.service;



import com.example.java17demo.pojo.vo.FriendsVo;

import java.util.List;

public interface ChatService {
    List<FriendsVo> getFriendsList(Integer userId);
}
