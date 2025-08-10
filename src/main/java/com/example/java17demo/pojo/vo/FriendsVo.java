package com.example.java17demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsVo {
    private Integer id;
    private String userName;
    private String nickName;
    private String avatar;
}
