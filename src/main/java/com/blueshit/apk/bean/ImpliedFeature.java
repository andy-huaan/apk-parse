package com.blueshit.apk.bean;

/**
 * 暗指特性系你想
 * author  9527
 * version 1.0
 * email   zhaohuaan0925@163.com
 * created  2015/12/14 17:02
 * description
 */
public class ImpliedFeature {

    /**
     * 要的设备特性名称。
     */
    private String feature;

    /**
     * 表明所需特性的内容。
     */
    private String implied;

    public ImpliedFeature(String feature, String implied) {
        super();
        this.feature = feature;
        this.implied = implied;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getImplied() {
        return implied;
    }

    public void setImplied(String implied) {
        this.implied = implied;
    }

    @Override
    public String toString() {
        return "Feature [feature=" + feature + ", implied=" + implied + "]";
    }
}
