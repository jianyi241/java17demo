package com.example.java17demo.util;

import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Mr Pan
 * on 2019/5/20.
 */
public class Base64ToImage {
    public static String GenerateImage(String imgStr, String imgFilePath,String rootPath) throws Exception {
        if (imgStr == null) // 图像数据为空
            return "";
//        BASE64Decoder decoder = new BASE64Decoder();

        // Base64解码,对字节数组字符串进行Base64解码并生成图片
        imgStr = imgStr.replaceAll(" ", "+");
        System.out.println(imgStr);
        byte[] b = Base64.decodeBase64(imgStr.replace("data:image/jpeg;base64,", ""));
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
                b[i] += 256;
            }
        }
        String imgName = getRandomFileName()+".jpg";
        String dbUrl = "";
        // 生成jpeg图片D:\test\attendance\src\main\webapp\assets\images\leave
        imgFilePath = imgFilePath+imgName;//新生成的图片
//        String imgFilePath2 = "D:\\test\\attendance\\target\\assets\\images\\leave\\"+imgName;
        OutputStream out = new FileOutputStream(imgFilePath);
        out.write(b);
        out.flush();
        out.close();
//        OutputStream out1 = new FileOutputStream(imgFilePath2);
//        out1.write(b);
//        out1.flush();
//        out1.close();
        dbUrl = rootPath+""+imgName;
        return dbUrl;
    }
    public static String getRandomFileName() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        Integer ranNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return ranNum + str;// 当前时间
    }

}

