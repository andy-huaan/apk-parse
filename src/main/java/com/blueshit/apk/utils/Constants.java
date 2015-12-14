package com.blueshit.apk.utils;

/**
 * 常量工具类
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 17:41
 * description
 */
public class Constants {
    //字符串截取正则表达式
    public static final String SPLIT_REGEX = "(: )|(=')|(' )|'";

    //字符串截取正则表达式
    public static final String FEATURE_SPLIT_REGEX = "(:')|(',')|'";

    //apk包名
    public static final String PACKAGE = "package";

    //app启动Activity
    public static final String LAUNCHABLE_ACTIVITY = "launchable-activity";

    //支持的SDK版本号
    public static final String SDK_VERSION = "sdkVersion";

    //建议的SDK版本
    public static final String TARGET_SDK_VERSION = "targetSdkVersion";

    //apk所需要的权限
    public static final String USES_PERMISSION = "uses-permission";

    //应用程序名
    public static final String APPLICATION_LABEL = "application-label";

    //各个分辨率下的图标的路径。
    public static final String APPLICATION_ICON = "application-icon";

    //应用程序ICON
    public static final String APPLICATION = "application:";

    //作用:通知其他外部实体，该应用程序所依赖的硬件和软件功能
    public static final String USES_FEATURE = "uses-feature";

    //暗指的特性
    public static final String USES_IMPLIED_FEATURE = "uses-implied-feature";

}
