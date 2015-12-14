package com.blueshit.apk.utils;

import com.blueshit.apk.bean.ApkInfo;
import com.blueshit.apk.bean.ImpliedFeature;

import java.io.*;

/**
 * apk工具类。封装了获取Apk信息的方法。
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 17:02
 * description
 */
public class ApkParseUtils {

    //aapt工具全路径
    private String aaptPath;

    //此类用于创建操作系统进程
    private ProcessBuilder mBuilder;

    public ApkParseUtils(String aaptPath) {
        this.aaptPath = aaptPath;
        this.mBuilder = new ProcessBuilder();
        this.mBuilder.redirectErrorStream(false);
    }

    /**
     * 解析APK包并返回apk程序的信息。
     *
     * @param apkPath apk文件全路径。
     * @return apk信息对象
     * @throws Exception
     */
    public ApkInfo getApkInfo(String apkPath) throws Exception {
        if(!FileUtils.fileExists(aaptPath)){
            throw new Exception("aapt工具路径错误");
        }
        if(!FileUtils.fileExists(apkPath)){
            throw new Exception("代解析apk文件路径错误");
        }
        return startParse(apkPath);
    }

    /**
     * 启动apk解析
     * @param apkPath apk文件全路径。
     * @return apk信息对象
     * @throws Exception
     */
    private ApkInfo startParse(String apkPath) throws Exception{
        //设置此进程生成器的操作系统程序和参数 并 创建一个本机进程
        Process process = mBuilder.command(aaptPath, "d", "badging", apkPath).start();

        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, "utf8"));
        String tmp = br.readLine();
        try {
            if (null == tmp || !tmp.startsWith("package")) {
                throw new Exception("参数不正确，无法正常解析APK包。输出结果为:\n" + tmp + "...");
            }
            ApkInfo apkInfo = new ApkInfo();
            do {
                setApkInfoProperty(apkInfo, tmp);
            } while ((tmp = br.readLine()) != null);
            return apkInfo;
        } catch (Exception e) {
            throw e;
        } finally {
            process.destroy();
            closeIO(is);
            closeIO(br);
        }
    }

    /**
     * 设置APK的属性信息。
     *
     * @param apkInfo
     * @param source
     */
    private void setApkInfoProperty(ApkInfo apkInfo, String source) {

        if (source.startsWith(Constants.PACKAGE)) {
            // package: name='com.example.addemo' versionCode='1' versionName='1.0'
            splitPackageInfo(apkInfo, source);
        } else if(source.startsWith(Constants.LAUNCHABLE_ACTIVITY)){
            // launchable-activity: name='com.example.addemo.MainActivity'  label='AdDemo' icon=''
            apkInfo.setLaunchableActivity(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.SDK_VERSION)) {
            // sdkVersion:'8'
            apkInfo.setSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.TARGET_SDK_VERSION)) {
            // targetSdkVersion:'18'
            apkInfo.setTargetSdkVersion(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.USES_PERMISSION)) {
            //uses-permission:'android.permission.INTERNET'
            apkInfo.addToUsesPermissions(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.APPLICATION_LABEL)) {
            //application-label:'AdDemo'
            apkInfo.setApplicationLable(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.APPLICATION_ICON)) {
            //application-icon-160:'res/drawable-mdpi/ic_launcher.png'
            //application-icon-240:'res/drawable-hdpi/ic_launcher.png'
            //application-icon-320:'res/drawable-xhdpi/ic_launcher.png'
            //application-icon-480:'res/drawable-xxhdpi/ic_launcher.png'
            apkInfo.addToApplicationIcons(getKeyBeforeColon(source),
                                          getPropertyInQuote(source));
        } else if (source.startsWith(Constants.APPLICATION)) {
            //application: label='AdDemo' icon='res/drawable-mdpi/ic_launcher.png'
            String[] rs = source.split("( icon=')|'");
            apkInfo.setApplicationIcon(rs[rs.length - 1]);
        } else if (source.startsWith(Constants.USES_FEATURE)) {
            //uses-feature:'android.hardware.screen.portrait'
            apkInfo.addToFeatures(getPropertyInQuote(source));
        } else if (source.startsWith(Constants.USES_IMPLIED_FEATURE)) {
            //uses-implied-feature:'android.hardware.location','requested a location access permission'
            apkInfo.addToImpliedFeatures(getFeature(source));
        } else {
            //还有以下几个信息未解析
            //supports-screens: 'small' 'normal' 'large' 'xlarge'
            //supports-any-density: 'true'
            //densities: '160' '240' '320' '480'
			System.out.println(source);
        }
    }

    /**
     * 分离出包名、版本等信息。
     *
     * @param apkInfo apk对象
     * @param packageSource apk解析出的部分信息
     */
    private void splitPackageInfo(ApkInfo apkInfo, String packageSource) {
        String[] packageInfo = packageSource.split(Constants.SPLIT_REGEX);
        apkInfo.setPackageName(packageInfo[2]);
        apkInfo.setVersionCode(packageInfo[4]);
        apkInfo.setVersionName(packageInfo[6]);
    }

    /**
     * 分离出格式为name: 'value'中的value内容
     *
     * @param source apk解析出的部分信息
     * @return 返回分离出的value内容
     */
    private String getPropertyInQuote(String source) {
        int index = source.indexOf("'") + 1;
        return source.substring(index, source.indexOf('\'', index));
    }

    /**
     * 返回冒号前的属性名称
     *
     * @param source
     * @return
     */
    private String getKeyBeforeColon(String source) {
        return source.substring(0, source.indexOf(':'));
    }

    private ImpliedFeature getFeature(String source) {
        String[] result = source.split(Constants.FEATURE_SPLIT_REGEX);
        ImpliedFeature impliedFeature = new ImpliedFeature(result[1], result[2]);
        return impliedFeature;
    }

    /**
     * 释放资源。
     *
     * @param c
     *            将关闭的资源
     */
    private final void closeIO(Closeable c) {
        if (null != c) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
