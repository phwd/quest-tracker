package com.oculus.base.app;

public class AppInfo {
    public final String appId;
    public final String appName;
    public final String appSecret;
    public final String flytrapCategoryId;
    public final String oculusAppId;
    public final String oculusLoginToken;

    public AppInfo(String appId2, String appName2, String appSecret2, String flytrapCategoryId2, String oculusAppId2, String oculusLoginToken2) {
        this.appId = appId2;
        this.appName = appName2;
        this.appSecret = appSecret2;
        this.flytrapCategoryId = flytrapCategoryId2;
        this.oculusAppId = oculusAppId2;
        this.oculusLoginToken = oculusLoginToken2;
    }
}
