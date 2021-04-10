package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class FacebookLoggingRequestInfo {
    private String mAnalyticsTag;
    private String mCallerClass;
    private String mLogName;

    @DoNotStrip
    public FacebookLoggingRequestInfo(String str, String str2, String str3) {
        this.mLogName = str;
        this.mAnalyticsTag = str2;
        this.mCallerClass = str3;
    }
}
