package com.example.java17demo.util;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.resource.ResourceStore;
import cloud.tianai.captcha.resource.common.model.dto.Resource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class CaptchaResourceConfiguration {

    private final ResourceStore resourceStore;

    public CaptchaResourceConfiguration(ResourceStore resourceStore) {
        this.resourceStore = resourceStore;
    }

    @PostConstruct
    public void init() throws Exception {
        // 背景图片存放在 resource/background 目录下
        String backgroundDirectory = "background";
        List<String> backgroundList = new ArrayList<>();

        String pattern = "classpath*:" + backgroundDirectory + "/**";
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources(pattern);
        for (org.springframework.core.io.Resource resource : resources) {
            String path = resource.getURL().getPath();
            if (!path.endsWith("/")) {
                String background = path.substring(path.lastIndexOf(backgroundDirectory));
                log.info("Found background image: {}", background);
                backgroundList.add(background);
            }
        }

        backgroundList.forEach((String background) -> {
            // 添加自定义背景图片, Resource 接收三个参数
            // 第一个参数为资源类型, 支持 classpath file url 三种类型
            // 第二个参数为资源路径
            // 第三个参数为资源的标签, 默认为 default
            Resource resource = new Resource("classpath", background);
            resourceStore.addResource(CaptchaTypeConstant.CONCAT, resource);
            resourceStore.addResource(CaptchaTypeConstant.SLIDER, resource);
            resourceStore.addResource(CaptchaTypeConstant.ROTATE, resource);
            resourceStore.addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, resource);

            // 使用日志记录每个添加的资源
            log.info("Added background resource: {}", background);
        });

        // 打印最终的背景列表
        backgroundList.forEach(background -> log.info("Background image: {}", background));
    }

}
