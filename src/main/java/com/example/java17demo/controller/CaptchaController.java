package com.example.java17demo.controller;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.common.response.ApiResponse;
import com.example.java17demo.util.CaptchaTrackDto;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    
    @GetMapping("/test")
    public String test() {
        return "Hello from Java17Demo!";
    }

    private final ImageCaptchaApplication imageCaptchaApplication;
    public CaptchaController(ImageCaptchaApplication imageCaptchaApplication) {
        this.imageCaptchaApplication = imageCaptchaApplication;
    }

    @PostMapping("/gen")
    public CaptchaResponse<?> genCaptcha() {
        // 1.生成验证码(该数据返回给前端用于展示验证码数据)
        // 参数1为具体的验证码类型， 默认支持 SLIDER、ROTATE、WORD_IMAGE_CLICK、CONCAT 等验证码类型，详见： `CaptchaTypeConstant`类
        return  imageCaptchaApplication.generateCaptcha(CaptchaTypeConstant.SLIDER);
    }

    @PostMapping("/check")
    public ApiResponse<?> checkCaptcha(@RequestBody CaptchaTrackDto data) {
        ApiResponse<?> response = imageCaptchaApplication.matching(data.getId(), data.getData());
        if (response.isSuccess()) {
            // 验证码验证成功，此处应该进行自定义业务处理， 或者返回验证token进行二次验证等。
            return ApiResponse.ofSuccess(Collections.singletonMap("validToken", data.getId()));
        }
        return response;
    }

}
