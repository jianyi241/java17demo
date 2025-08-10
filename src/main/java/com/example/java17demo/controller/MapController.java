package com.example.java17demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.java17demo.util.HttpURLConnectionUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
@CrossOrigin(origins = "*", maxAge = 36000)
public class MapController {

    @GetMapping(value = "/getGeoJsonByAdCode")
    public String getGeoJSONByAdCode(Integer adCode) {
        try {
            String result = HttpURLConnectionUtil.doGet("https://geo.datav.aliyun.com/areas_v3/bound/" + adCode + "_full.json");
            if (result.isEmpty()) {
                result = HttpURLConnectionUtil.doGet("https://geo.datav.aliyun.com/areas_v3/bound/" + adCode + ".json");
            }
            return JSON.toJSONString(result);
        } catch (Exception e) {
            return "";
        }
    }
}
