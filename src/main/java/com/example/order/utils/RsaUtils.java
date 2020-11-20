package com.example.order.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RsaUtils
 */
public class RsaUtils {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsD1gI70BxYujhNw8NpaVKRXkcRofoeUbN9Dj5m3i3h9XAIS6LkjI01L4ieRpTHnMEzoXUY8a2/svDf//xuHuDJlZBNtCXK4DPx5x4zHdUWDjFGpWlMQzhsqQlfs0tkN5gP095g27L0ki/NrRuBpgxP1q2dHKpL37sBF8XNRpedwIDAQAB";

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwPWAjvQHFi6OE3Dw2lpUpFeRxGh+h5Rs30OPmbeLeH1cAhLouSMjTUviJ5GlMecwTOhdRjxrb+y8N///G4e4MmVkE20JcrgM/HnHjMd1RYOMUalaUxDOGypCV+zS2Q3mA/T3mDbsvSSL82tG4GmDE/WrZ0cqkvfuwEXxc1Gl53AgMBAAECgYEAogyhgWi0bRYW92Z/yv6julvMQRE8l3sBcJ//uTbwbwqECrw1tkYu+wsTOCyO2pHnCjPoX6zJTziSeMJpMCPsToCm3+EO38Ki/12RSJ51DgSl7C4R8leoFMZAmExQO4L1rVae2dJS80dvfpLeNiquJw4y7xjXIBUYbQfu+mNKHoECQQDic2F3UMm05M1d/POQHqsuMohoDhwVM0N8/SdbA4fpYFFOahMWdmuFclYbkgTwfYBdGMH/UHLsCGWVv8z48jKHAkEAwoMID93DzTsZWqxAoyGDg7GGdNsMPvYErRxwcOVNpdQXv8F7IAXOslLTvJF542dplCCNzLgyupwxMPlMLuZAkQJANpcoHPJt3dz2oTzUnp62F6n49lTIclfsYhpJPYipYBpnH2c0+MpNe1sn5Peblzo6ErdgNSN4wOv5SVN2n2ELywJAGjyiccFwD9bQ7LIfZeG3Y6QmhsylMjjtGIylfhTwDFY3fd4TRZaC8vrJJL5aupnQW/KoLd0KurEm0XxPEmRsgQJARKNghvpoce39bD/BbC2AojY5Ea6afLzW+GllxHGNGkgGu+x3c4PIUDAt8f60021WoENtonEQpEjzbIU5XpefJA==";

    protected static final Logger logger = LoggerFactory.getLogger(RsaUtils.class);


    /**
     * 用于封装随机产生的公钥与私钥
     *
     * @author yihur
     * @date 2019/4/4
     * @param
     * @return
     */
    private static Map<Integer, String> keyMap = new HashMap<>();


    /**
     * 测试方法
     *
     * @param args
     * @return void
     * <p>
     * <p>
     * 前端用crypto-js进行加密，
     * npm i jsencrypt，
     * 然后页面头引入import JSEncrypt from 'jsencrypt';
     * const encrypt = new JSEncrypt();
     * encrypt.setPublicKey('你的公钥');
     * password = encrypt.encrypt(‘你的密码’);// 加密后的字符串
     * @author yihur
     * @date 2019/4/4
     */
    public static void main(String[] args) {
        //生成公钥和私钥
        genKeyPair();
        //加密字符串
        String message = "df723820";
        System.out.println("随机生成的公钥为:" + keyMap.get(0));
        System.out.println("随机生成的私钥为:" + keyMap.get(1));
        String messageEn = encrypt(message, keyMap.get(0));
        System.out.println("加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn, keyMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);
    }

    /**
     * 随机生成密钥对
     *
     * @param
     * @return void
     * @author yihur
     * @date 2019/4/4
     */
    public static void genKeyPair() {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        assert keyPairGen != null;
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
     */
    public static String encrypt(String str, String publicKey) {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = null;
        String outStr = null;
        try {
            pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        //RSA加密
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     */
    public static String decrypt(String str, String privateKey) {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = null;
        //RSA解密
        Cipher cipher = null;
        String outStr = null;
        try {
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return outStr;
    }


}
