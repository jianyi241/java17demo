package com.example.java17demo.service.impl;

import com.example.java17demo.mapper.PermissionMapper;
import com.example.java17demo.pojo.Router;
import com.example.java17demo.pojo.vo.PermissionVO;
import com.example.java17demo.pojo.vo.RouterVO;
import com.example.java17demo.service.PermissionService;
import com.example.java17demo.util.CascadePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

    private PermissionMapper permissionMapper;

    @Autowired
    private void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<PermissionVO> getPermissionRouterList(Integer roleId) {
        List<RouterVO> routerVOList = null;
        if (roleId != null) {
            routerVOList = permissionMapper.getPermissionRouterList(roleId);
        } else {
            routerVOList = permissionMapper.getPermissionRouterAllList();
        }
        List<PermissionVO> navigationMenuList = permissionMapper.getNavigationMenus();
        routerVOList = CascadePermission.listWithTree(routerVOList);
        for (int i = 0; i < navigationMenuList.size(); i++) {
            PermissionVO nav = navigationMenuList.get(i);
            List<RouterVO> filterRouterVOList = routerVOList.stream().filter(r -> nav.getMenuId() == r.getNavMenuId()).collect(Collectors.toList());
            nav.setList(filterRouterVOList);
            navigationMenuList.set(i, nav);
        }
//        navigationMenuList = navigationMenuList.stream().filter(r -> r.getList().size() > 0).collect(Collectors.toList());
        return navigationMenuList;

    }


    @Override
    public Integer insertRouter(Router router) {
        return permissionMapper.insertRouter(router);
    }

    @Override
    public Integer updateRouter(Router router) {
        return permissionMapper.updateRouter(router);
    }
}
