package com.blueshit.apk.utils;

import java.io.File;

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
}
