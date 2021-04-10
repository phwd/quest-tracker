package com.facebook.tigon.iface;

public class FacebookLoggingRequestInfo {
    public String mAnalyticsTag;
    public String mCallerClass;
    public String mLogName;

    public FacebookLoggingRequestInfo(String str, String str2, String str3) {
        this.mLogName = str;
        this.mAnalyticsTag = str2;
        this.mCallerClass = str3;
    }
}
