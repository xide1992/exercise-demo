package com.exercise.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/7 22:58
 */
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    //默认的加密算法
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    //默认的加密密码
    private static final String PASSWORD = "xidexide";


    /**
     * 直接使用这个方法的话,secretKey生成长度要是8的倍数
     * @param enc
     * @return
     * @throws Exception
     */
    public static String encode(String enc) throws Exception {

        Key secretKey = new SecretKeySpec(Base64.decodeBase64(PASSWORD), KEY_ALGORITHM);

        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 执行操作
        String abc = Base64.encodeBase64String(cipher.doFinal(enc.getBytes()));
        return abc;
    }

    public static String decode(String enc) throws Exception {

        Key secretKey = new SecretKeySpec(Base64.decodeBase64(PASSWORD), KEY_ALGORITHM);

        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

        // 初始化，设置为加密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // 执行操作
        byte[] result = cipher.doFinal(Base64.decodeBase64(enc));
        return new String(result, "utf-8");
    }


    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");

            if (StringUtils.isEmpty(password)) {
                password = PASSWORD;
            }
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            //TODO log
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            if (StringUtils.isEmpty(password)) {
                password = PASSWORD;
            }
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            //TODO log
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            kg.init(128, secureRandom);

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            //TODO log
        }
        return null;
    }
}
