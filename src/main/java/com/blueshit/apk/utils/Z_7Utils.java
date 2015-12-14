package com.blueshit.apk.utils;

import java.io.*;

/**
 * 7Z解压工具类
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 18:36
 * description
 */
public class Z_7Utils {

    /**
     * 7z 解压缩文件
     * @param z7zPath  7z.exe路径 如 "d:/zip/7z.exe"
     * @param filePath 待解压文件路径     *.zip  apk 等
     * @param target   解压到文件夹路径
     */
    public static void tar7Z(String z7zPath, String filePath, String target) throws Exception{
        Process proc;
        String log7z;
        String lastlog7z;

        String cmd = z7zPath + " x \""+ filePath +"\" -y -o\""+target+"\"";

        System.out.print("开始解压缩......\n" + cmd + "\n");
        InputStream is = null;
        BufferedReader br = null;
        try {
            proc = Runtime.getRuntime().exec(cmd);
            is = proc.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            while((log7z = br.readLine()) != null) {
                lastlog7z = log7z;
                System.out.print(lastlog7z);
            }
        } catch (IOException ex) {
            throw ex;
        } finally{
            if (null != br){
                br.close();
            }
            if (null != is){
                is.close();
            }
        }
    }
}
