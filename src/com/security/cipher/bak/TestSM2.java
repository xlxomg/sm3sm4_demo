//package com.security.cipher.sm;
//
//import com.security.cipher.util.EncryptUtil;
//
//import java.io.*;
//
///**
// * @Description:
// * @Author: 陈欢
// * @Date: 2018/10/23 14:18
// */
//public class TestSM2 {
//    public static void main(String[] args) throws Exception {
//
//        String filePath = "F:" + File.separator + "0123.zip";
//        File file = new File(filePath);
//
//        // 读取文件, 转换为字节流
//        byte[] datas = FileToByteArray.file2buf(file);
//
//        String plainText = "1122334455667788";
//        byte[] sourceData = EncryptUtil.hexStringToBytes(plainText);
//
//        // 国密规范测试私钥
//        String prik = "969FC0F73FA117A040B37D5B5018382A74D40590EAA02809B87FA09196F8276D";
//
//        // 国密规范测试公钥
//        String pubk = "04ABC2230A05A72CEB667B20019C4F2A580E4D0A3BE9D20BF914565AB3B82631E1C0E15803FA3ADE3E6D9EEF293CBD8BAECC51D82B61404A39584198B6985686FB";
//        System.out.println("加密: ");
//        byte[] cipherText = SM2Utils.encrypt(EncryptUtil.hexStringToBytes(pubk), datas);
//        System.out.println(EncryptUtil.encodeHexString(cipherText));
//        System.out.println("");
//        FileToByteArray.BetyToFile("F:" + File.separator + "0123加密.zip",cipherText);
//
//        FileToByteArray.fileToBetyArray("F:" + File.separator + "0123解密.zip");
//        System.out.println("解密: ");
//        String data = EncryptUtil.encodeHexString(cipherText);
//        byte[] decrypt = SM2Utils.decrypt(EncryptUtil.hexStringToBytes(prik), EncryptUtil.hexStringToBytes(data));
//        FileToByteArray.BetyToFile("F:" + File.separator + "0123解密.zip",decrypt);
//
//
//    }
//}
