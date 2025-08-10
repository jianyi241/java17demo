package com.example.java17demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionVO {

    // 唯一key
    private Integer key;

    // 菜单ID
    private Integer menuId;

    // 菜单名称
    private String menuName;

    // 排序字段
    private Integer sort;

    // 路由列表
    private List<RouterVO> list;

}
