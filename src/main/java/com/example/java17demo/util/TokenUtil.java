package com.example.java17demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil {

    //过期时间
    private static final long EXPIRE_TIME = 3600000;

    //token私钥
    private static final String TOKEN_SECRET = "love2apple3orange4dog9cat8girl0boy0banana58Tree90flowers8khk7hello65word78simple9cch8979990wyl2Dkp92020from2048GoToPlayGameOver";

    /**
     * 生成token
     *
     * @param userName
     * @param uId
     * @return
     */
    public static String sign(String userName, String uId, Integer roleId) {
        //过期时间点
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥加密算法
        Algorithm algorithm = null;
        algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");
        //附带userName,uId生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("userName", userName)
                .withClaim("uId", uId)
                .withClaim("roleId", roleId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static Map<String, Object> verify(String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            result.put("uId", Integer.valueOf(decodedJWT.getClaim("uId").asString()));
            result.put("userName", decodedJWT.getClaim("userName").asString());
            result.put("roleId", decodedJWT.getClaim("roleId").asInt());
            result.put("requireAuth", true);
        } catch (Exception e) {
            System.out.println(e);
            result.put("uId", "");
            result.put("userName", "");
            result.put("roleId", null);
            result.put("requireAuth", false);
        }
        return result;
    }

    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 检查 Cookie 名称是否为 "auth"
                if ("auth".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break; // 找到后可以退出循环
                }
            }
        }
        return token;
    }
}