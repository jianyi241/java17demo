package com.example.java17demo.mapper;

import com.example.java17demo.pojo.Router;
import com.example.java17demo.pojo.vo.PermissionVO;
import com.example.java17demo.pojo.vo.RouterVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper {

    // 根据角色ID获取动态路由
    List<RouterVO> getPermissionRouterList(@Param("roleId") Integer roleId);

    // 获取全部路由
    List<RouterVO> getPermissionRouterAllList();

    @Results({
            @Result(property = "key", column = "id"),
            @Result(property = "menuId", column = "id"),
            @Result(property = "menuName", column = "name")
    })
    @Select("SELECT * FROM navigation_menu")
    List<PermissionVO> getNavigationMenus();

    Integer insertRouter(Router router);

    Integer updateRouter(Router router);

}
