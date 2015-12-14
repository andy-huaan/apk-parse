package com.blueshit.apk.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类封装了一个Apk的信息。包括版本号，支持平台，图标，名称，权限，所需设备特性等。
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 17:02
 * description
 */
public class ApkInfo {

    /**
     * apk的包名
     */
    private String packageName;

    /**
     * apk内部版本号
     */
    private String versionCode;

    /**
     * apk外部版本号
     */
    private String versionName;

    /**
     * 启动界面
     */
    private String launchableActivity;

    /**
     * 支持的SDK版本。
     */
    private String sdkVersion;

    /**
     * 建议的SDK版本
     */
    private String targetSdkVersion;

    /**
     * apk所需要的权限
     */
    private List<String> usesPermissions;

    /**
     * 应用程序名
     */
    private String applicationLable;

    /**
     * 各个分辨率下的图标的路径。
     */
    private Map<String, String> applicationIcons;

    /**
     * 程序的图标。
     */
    private String applicationIcon;

    /**
     * 所需设备特性(通知其他外部实体，该应用程序所依赖的硬件和软件功能)
     */
    private List<String> features;

    /**
     * 暗指的特性。
     */
    private List<ImpliedFeature> impliedFeatures;

    public ApkInfo() {
        this.usesPermissions = new ArrayList<String>();
        this.applicationIcons = new HashMap<String, String>();
        this.impliedFeatures = new ArrayList<ImpliedFeature>();
        this.features = new ArrayList<String>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public List<String> getUsesPermissions() {
        return usesPermissions;
    }

    public void setUsesPermissions(List<String> usesPermissions) {
        this.usesPermissions = usesPermissions;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(String targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getApplicationLable() {
        return applicationLable;
    }

    public void setApplicationLable(String applicationLable) {
        this.applicationLable = applicationLable;
    }

    public Map<String, String> getApplicationIcons() {
        return applicationIcons;
    }

    public void setApplicationIcons(Map<String, String> applicationIcons) {
        this.applicationIcons = applicationIcons;
    }

    public String getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(String applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public List<ImpliedFeature> getImpliedFeatures() {
        return impliedFeatures;
    }

    public void setImpliedFeatures(List<ImpliedFeature> impliedFeatures) {
        this.impliedFeatures = impliedFeatures;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getLaunchableActivity() {
        return launchableActivity;
    }

    public void setLaunchableActivity(String launchableActivity) {
        this.launchableActivity = launchableActivity;
    }

    public void addToApplicationIcons(String key, String value) {
		this.applicationIcons.put(key, value);
	}

	public void addToImpliedFeatures(ImpliedFeature impliedFeature) {
		this.impliedFeatures.add(impliedFeature);
	}

	public void addToFeatures(String feature) {
		this.features.add(feature);
	}

    public void addToUsesPermissions(String usesPermission) {
        this.usesPermissions.add(usesPermission);
    }

	@Override
	public String toString() {
		return "ApkInfo [versionCode=" + versionCode + ",\n versionName="
				+ versionName + ",\n packageName=" + packageName
				+ ",\n usesPermissions="
				+ usesPermissions + ",\n sdkVersion=" + sdkVersion
				+ ",\n targetSdkVersion=" + targetSdkVersion
				+ ",\n applicationLable=" + applicationLable
				+ ",\n applicationIcons=" + applicationIcons
				+ ",\n applicationIcon=" + applicationIcon
				+ ",\n impliedFeatures=" + impliedFeatures + ",\n features="
				+ features + ",\n launchableActivity=" + launchableActivity + "\n]";
	}
}
