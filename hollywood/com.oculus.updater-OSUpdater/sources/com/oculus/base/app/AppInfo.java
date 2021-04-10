package com.oculus.base.app;

import com.facebook.inject.RequiresBinding;

@RequiresBinding
public class AppInfo {
    public final String appId;
    public final String appName;
    public final String appSecret;
    public final String flytrapCategoryId;
    public final String oculusAppId;
    public final String oculusLoginToken;

    public AppInfo(String str, String str2, String str3, String str4, String str5, String str6) {
        this.appId = str;
        this.appName = str2;
        this.appSecret = str3;
        this.flytrapCategoryId = str4;
        this.oculusAppId = str5;
        this.oculusLoginToken = str6;
    }
}
