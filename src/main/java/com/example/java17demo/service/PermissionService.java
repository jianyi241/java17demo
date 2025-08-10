package com.example.java17demo.service;


import com.example.java17demo.pojo.Router;
import com.example.java17demo.pojo.vo.PermissionVO;

import java.util.List;

public interface PermissionService {

    List<PermissionVO> getPermissionRouterList(Integer roleId);

    Integer insertRouter(Router router);

    Integer updateRouter(Router router);
}
