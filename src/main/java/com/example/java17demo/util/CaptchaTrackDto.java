package com.example.java17demo.util;

import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import lombok.Data;

@Data
public class CaptchaTrackDto {

    private String id;

    private ImageCaptchaTrack data;

}
