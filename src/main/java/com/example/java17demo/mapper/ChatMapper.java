package com.example.java17demo.mapper;

import com.example.java17demo.pojo.User;
import com.example.java17demo.pojo.vo.FriendsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    List<FriendsVo> getFriendsList(@Param("userId") Integer userId);

    User getFriendInfo(@Param("friendId") Integer friendId);

}
