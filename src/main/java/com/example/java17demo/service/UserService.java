package com.example.java17demo.service;


import com.example.java17demo.pojo.vo.UserVO;
import com.example.java17demo.util.PageUtil;

public interface UserService {
   PageUtil<UserVO> getUserList(String userName, String nickName, Integer roleId, Integer nationId, Integer pageNum, Integer pageSize);

   UserVO login(String userName, String password);

   UserVO getUserInfo(Integer userId);
}
