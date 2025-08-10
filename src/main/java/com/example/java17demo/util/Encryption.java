package com.example.java17demo.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    /**
     * MD5加密
     */
    public String encoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        Base64 base64 = new Base64();
        String newStr = Base64.encodeBase64String(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
//        Base64.Encoder base64en = Base64.getEncoder();
        //加密后的字符串
//        String newStr = base64en.encodeToString(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    /**
     * SHA加密
     */
    public String encoderBySHA(String str) {
        String encoderStr = "";
        try {
            //创建SHA算法对象
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(str.getBytes());
            encoderStr = new BigInteger(md.digest()).toString(32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encoderStr;
    }

    /**
     * 双层加密密码
     */
    public String encodePwd(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return encoderBySHA(encoderByMd5(pwd));
    }
}

