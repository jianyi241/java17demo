package com.example.java17demo.controller;

import com.example.java17demo.annotation.Auth;
import com.example.java17demo.pojo.Router;
import com.example.java17demo.pojo.vo.PermissionVO;
import com.example.java17demo.service.PermissionService;
import com.example.java17demo.util.ResultMap;
import com.example.java17demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/permission")
@RestController
public class PermissionController {

    private PermissionService permissionService;

    @Autowired
    private void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Auth
    @GetMapping(value = "/getPermissions")
    public ResultMap getPermissionRouterList(HttpServletRequest request) {
        Map<String, Object> authInfo = TokenUtil.verify(TokenUtil.getToken(request));
        Integer roleId = (Integer) authInfo.get("roleId");
        List<PermissionVO> list = permissionService.getPermissionRouterList(roleId);
        ResultMap map;
        try {
            map = new ResultMap(200, "success", list);
        } catch (Exception e) {
            map = new ResultMap(500, "failed", e.toString());
        }
        return map;
    }

    @Auth
    @GetMapping(value = "/getAllRouters")
    public ResultMap getPermissionRouterAllList() {
        List<PermissionVO> list = permissionService.getPermissionRouterList(null);
        ResultMap map;
        try {
            map = new ResultMap(200, "success", list);
        } catch (Exception e) {
            map = new ResultMap(500, "failed", e.toString());
        }
        return map;
    }

    @Auth
    @PostMapping(value = "/addRouter")
    public ResultMap addRouter(Router router) {
        try {
            log.info("addRouter params ===> " + router.toString());
            Integer count = permissionService.insertRouter(router);
            return new ResultMap(200, "success", count);
        } catch (Exception e) {
            return new ResultMap(500, "failed", e.toString());
        }
    }

    @Auth
    @PostMapping(value = "/updateRouter")
    public ResultMap updateRouter(Router router) {
        try {
            log.info("updateRouter params ===> " + router.toString());
            Integer count = permissionService.updateRouter(router);
            return new ResultMap(200, "success", count);
        } catch (Exception e) {
            return new ResultMap(500, "failed", e.toString());
        }
    }
}
