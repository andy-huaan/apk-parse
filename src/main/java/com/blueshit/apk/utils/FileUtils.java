package com.blueshit.apk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 文件操作工具类
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 17:29
 * description
 */
public class FileUtils {

    /**
     * 判断文件是否存在
     * @param filePath 文件全路径
     * @return
     */
    public static boolean fileExists(String filePath){
        return new File(filePath).exists();
    }

    /**
     * 创建目录
     * @param dir 待创建目录
     */
    public static void mkdir(String dir) throws Exception{
        File dirPath = new File(dir);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
    }

    /**
     * 复制单个文件
     *
     * @param srcFile
     *            包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest
     *            目标文件目录；若文件目录不存在则自动创建 如：E:/phsftp/dest
     * @throws Exception
     */
    public static void copyFile(String srcFile, String dirDest) throws Exception{
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            mkdir(dirDest);
            out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
            int len;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

        } catch (Exception e) {
            throw e;
        }
        finally {
            if(null != in){
                in.close();
            }
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path 文件夹路径
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] childFiles = file.list();
        File temp;
        for (int i = 0; i < childFiles.length; i++) {
            // File.separator与系统有关的默认名称分隔符
            // 在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'。
            if (path.endsWith(File.separator)) {
                temp = new File(path + childFiles[i]);
            } else {
                temp = new File(path + File.separator + childFiles[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + childFiles[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + childFiles[i]);// 再删除空文件夹
            }
        }
        file.delete();
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹路径
     */
    public static void delFolder(String folderPath) {
        // 删除文件夹里面所有内容
        delAllFile(folderPath);
        String filePath = folderPath;
        File myFilePath = new File(filePath);
        // 删除空文件夹
        myFilePath.delete();
    }

}
