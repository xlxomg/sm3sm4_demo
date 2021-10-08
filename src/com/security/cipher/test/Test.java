package com.security.cipher.test;

import com.security.cipher.sm.SM3Digest;
import com.security.cipher.sm.SM4Utils;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * @Author xieluxin
 * @Date 2021/10/8 17:45
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        sm3Test("asdfasdfsdf");
        sm4Test("abcd000000","a1097fadcabe4c40a1cbdc9106dccb24");
    }

    private static void sm3Test(String plainText) {
        System.out.println("==========SM3 TEST===========");
        long startTime = System.currentTimeMillis();

        byte[] md = new byte[32];
        byte[] msg1 = plainText.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        System.out.println(s);


        SM3Digest sm33 = new SM3Digest();
        sm33.update(msg1, 0, msg1.length);
        sm33.doFinal(md, 0);
        String s2 = new String(Hex.encode(md));
        System.out.println(s2);
        System.out.println("耗时: " + (System.currentTimeMillis()-startTime) +"毫秒");
    }

    private static void sm4Test(String plainText,String appSecret) {
        System.out.println("==========SM4 TEST===========");
        long startTime = System.currentTimeMillis();

        SM4Utils sm4 = new SM4Utils();
        //这里将appSecret md5后取其中的16位作为key
        sm4.setSecretKey(MD5_cut16(appSecret));
        //sm4.setSecretKey("JeF8U9wHFOMfs2Y8");
        sm4.setHexString(false);

        System.out.println("ECB模式");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);

        plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);

        //System.out.println("CBC模式");
        //sm4.iv = "UISwD9fW6cFh9SNS";
        //cipherText = sm4.encryptData_CBC(plainText);
        //System.out.println("密文: " + cipherText);
        //
        //plainText = sm4.decryptData_CBC(cipherText);
        //System.out.println("明文: " + plainText);
        System.out.println("耗时: " + (System.currentTimeMillis()-startTime) +"毫秒");
    }

    /**
     * 生成 MD5, 并截取其中8到24位(即16位长度)
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5_cut16(String data) {
        StringBuilder sb = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }
        } catch (Exception e) {
            System.out.println("生成md5失败");
        }
        return sb.substring(8, 24).toUpperCase();
    }
}