package com.example.java17demo.util;

import com.example.java17demo.pojo.vo.RouterVO;

import java.util.List;
import java.util.stream.Collectors;

public class CascadePermission {

    public static List<RouterVO> listWithTree(List<RouterVO> entitiesList) {
        // 1、查询数据库钟，所有的级联信息（ 我这里是使用上面的默认数据在测试）
        // 2、先找到一级父类，再通过级联去查询子类菜单
        List<RouterVO> topLevel = entitiesList.stream().filter(linkageEntities ->
                linkageEntities.getParentId() == 0     //查询父类
        ).map((menu) -> {
            List<RouterVO> routerVOs = getChildren(menu, entitiesList);
            if (routerVOs.size() > 0) {
                menu.setChildren(routerVOs); //查询子类菜单
            } else {
                menu.setChildren(null);
            }
            return menu;
        }).sorted((menu1, menu2) -> {
            //排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return topLevel;
    }

    //递归查找所有菜单的子类菜单
    private static List<RouterVO> getChildren(RouterVO root, List<RouterVO> all) {

        List<RouterVO> children = all.stream().filter(linkageEntities -> {
            return linkageEntities.getParentId() == root.getId();
        }).map(linkageEntities -> {
            // 1、递归找到子菜单
            List<RouterVO> routerVOs = getChildren(linkageEntities, all);
            if (routerVOs.size() > 0) {
                linkageEntities.setChildren(routerVOs);
            } else {
                linkageEntities.setChildren(null);
            }
            return linkageEntities;
        }).sorted((menu1, menu2) -> {
            // 2、菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return children;
    }
}

