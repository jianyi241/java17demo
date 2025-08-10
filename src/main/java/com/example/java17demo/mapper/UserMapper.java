package com.example.java17demo.mapper;

import com.example.java17demo.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserVO login(@Param("userName")String userName, @Param("password")String password);

    UserVO getUserInfo(@Param("userId")Integer userId);

    List<UserVO> getUserList(@Param("userName") String userName, @Param("nickName") String nickName, @Param("roleId") Integer roleId, @Param("nationId") Integer nationId, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

    Integer getUserTotalRecord(@Param("userName") String userName, @Param("nickName") String nickName, @Param("roleId") Integer roleId, @Param("nationId") Integer nationId);

}
