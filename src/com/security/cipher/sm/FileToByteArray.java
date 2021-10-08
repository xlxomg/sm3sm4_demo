//package com.security.cipher.sm;
//
//import java.io.*;
//
///**
// * @Description:
// * @Author: 陈欢
// * @Date: 2018/10/24 10:56
// */
//public class FileToByteArray {
//
//
//    public static byte[] file2ByteArray(String filePath) {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            return null;
//        }
//        FileInputStream stream = null;
//        ByteArrayOutputStream out = null;
//        try {
//            stream = new FileInputStream(file);
//            out = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = stream.read(b)) != -1) {
//                out.write(b, 0, n);
//            }
//            return out.toByteArray();       // 此方法大文件OutOfMemory
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        } finally {
//            try {
//                stream.close();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }
//
//    public static byte[] fileToBetyArray(String filePath) {
//        FileInputStream fileInputStream = null;
//        File file = new File(filePath);
//        byte[] bFile = null;
//        try {
//            bFile = new byte[(int) file.length()];
//            fileInputStream = new FileInputStream(file);
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//            for (int i = 0; i < bFile.length; i++) {
//                System.out.print((char) bFile[i]);
//            }
//            System.out.println("Done");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fileInputStream.close();
//                bFile.clone();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return bFile;
//    }
//
//    public static File BetyToFile(String filePath, byte[] data) {
//        File file = new File(filePath);
//        BufferedOutputStream stream = null;
//        FileOutputStream fstream = null;
//        //byte[] data = new byte[(int) file.length()];
//        try {
//            fstream = new FileOutputStream(file);
//            stream = new BufferedOutputStream(fstream);
//            stream.write(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stream != null) {
//                    stream.close();
//                }
//                if (null != fstream) {
//                    fstream.close();
//                }
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return file;
//    }
//
//    public static byte[] file2buf(File fobj) {
//        byte[] buffer = null;
//        try {
//            if (!fobj.exists()) {
//                return null;
//            }
//
//            FileInputStream fis = new FileInputStream(fobj);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int len = -1;
//            while ((len = fis.read(b)) != -1) {
//                bos.write(b, 0, len);
//            }
//            fis.close();
//            bos.close();
//            buffer = bos.toByteArray();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return buffer;
//    }
//
//}
