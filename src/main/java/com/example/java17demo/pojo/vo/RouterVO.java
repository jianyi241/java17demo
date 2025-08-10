package com.example.java17demo.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
 public class RouterVO {

    private Integer key;

    private Integer id;

    private String name;

    private String path;

    private String component;

    private List<RouterVO> children;

    private Meta meta;

    private Integer navMenuId;

    private Integer sort;

    private  Integer parentId;
    @Data
    public static class Meta {
        private String title;

        private String icon;

        private Integer cache;

        private Integer menu;

        private Integer hidden;
    }
}
