package com.example.java17demo.pojo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {
    private Integer pageNum;
    private Integer pageSize;
    private String userName;
    private String nickName;
    private Integer roleId;
    private Integer nationId;
}
