package com.example.java17demo.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    // 角色ID
    private Integer id;

    // 角色名称
    private String roleName;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date creationTime;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
}
