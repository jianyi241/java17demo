package com.example.java17demo.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncryption {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChEL++GBMv5/0JBrGW8U9FVmNVMQhxLDoCPntiVcCLi8u0Ut4+mg31cyMtrmWVzQBuuTDFMA+WMw/0H5CZp5cbEUseE5gKvO8lCHxbWwuouRcBrhN/asKPIHLZWP0lW0EMrcMkSTK64QCtV0SrL0v3U9MtUd04D5euw6aoLKS2awIDAQAB";
    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKEQv74YEy/n/QkGsZbxT0VWY1UxCHEsOgI+e2JVwIuLy7RS3j6aDfVzIy2uZZXNAG65MMUwD5YzD/QfkJmnlxsRSx4TmAq87yUIfFtbC6i5FwGuE39qwo8gctlY/SVbQQytwyRJMrrhAK1XRKsvS/dT0y1R3TgPl67DpqgspLZrAgMBAAECgYA7lw3Jz/BxXfxhNYfI17jDZ4XSxBRigsFniSRvKljViEAylCJDuQ2HDTUrtoVBGhqZ3IxtuC1cHcI+SNtf7gBaXfSe0PLEgboDXxh9cXQKl5AMZUVDGB2iMv+Vh8VHC+v7rkt9xBEZo31N3ejyzjCOVLZfjZfY3asadZJJuNSDyQJBANPcyGGIbUimV9sAIAP+HTHc+pqdDvgN3pa5vo57wl6thJ+vDsr4Yh8aFq0t+Xq1XdtAYJQLghHO9A83hPf8WeUCQQDCntHMhhTm/dwVnXZ0dmAAcmscGkuxEHTbgfcUgi4xydB64HyAbObMcuPfc2V/C3iclFE7+PtaHTv4Tb1MbooPAkAOCVSlUZqb85ligxGb8hwRD6jy0XEKcjDLH6dGXWC1UR22TqzWObqWgO37r04WGSoMuqkrZc/KSkHVOEJxA6EZAkAdbpU9v90zXZrF7g026Zv1FVibZGKkAWAavhnWkSVZRDLyEjbh2RZCPvG1HidwQhTYYRmz3yBr7VXfZCpHf8QDAkEAsXJp206uGGdN+Z3MLTpNEkI0AHGZcPW/YPRRj1K79uMSzyxgdVq2EU9K4LhWdx7TIC5O29Fmq41knCD+q84cBQ==";

    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        //genKeyPair();
        //加密字符串
        String message = "df723820";
        System.out.println("随机生成的公钥为:" + PUBLIC_KEY);
        System.out.println("随机生成的私钥为:" + PRIVATE_KEY);
        String messageEn = encrypt(message, PUBLIC_KEY);
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn);
        System.out.println("还原后的字符串为:" + messageDe);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0, publicKeyString);  //0表示公钥
        keyMap.put(1, privateKeyString);  //1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

}

