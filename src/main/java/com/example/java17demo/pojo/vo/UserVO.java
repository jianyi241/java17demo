package com.example.java17demo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    // 用户ID
    private Integer id;

    // 用户名称
    private String userName;

    // 昵称
    private String nickName;

    // 电话号码
    private String phoneNum;

    // 邮箱
    private String mail;

    // 头像
    private String avatar;

    // 年龄
    private Integer age;

    // 性别
    private Integer gender;

    // 家庭地址
    private String address;

    // 角色ID
    private Integer roleId;

    // 角色名称
    private String roleName;

    // 国家ID
    private Integer nationId;

    // 国家名称
    private String nationName;

    // 创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date creationTime;

    // 更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
}
