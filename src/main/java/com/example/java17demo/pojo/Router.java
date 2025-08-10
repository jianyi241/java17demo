package com.example.java17demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Router {

    private Integer id;

    private String title;

    private String name;

    private String path;

    private String component;

    private String icon;

    private Integer cache;

    private Integer menu;

    private Integer hidden;

    private Integer navMenuId;

    private  Integer parentId;

    private Integer sort;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date creationTime;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
}
