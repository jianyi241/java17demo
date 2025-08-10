package com.example.java17demo.pojo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    // 用户名
    private String userName;

    // 密码
    private String password;

    // 验证码
    private String verifyCode;
}
