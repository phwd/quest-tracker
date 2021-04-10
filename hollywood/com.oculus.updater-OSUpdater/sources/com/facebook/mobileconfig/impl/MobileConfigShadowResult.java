package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigShadowResult {
    public final String cachedResult;
    public final String cachedResultFromFlatbuffer;
    public final String configParameter;
    public final String debugString;
    public final String liveResult;
    public final String liveResultFromFlatbuffer;

    public MobileConfigShadowResult(String str, String str2, String str3, String str4, String str5, String str6) {
        this.configParameter = str;
        this.cachedResult = str2;
        this.cachedResultFromFlatbuffer = str3;
        this.liveResult = str4;
        this.liveResultFromFlatbuffer = str5;
        this.debugString = str6;
    }
}
