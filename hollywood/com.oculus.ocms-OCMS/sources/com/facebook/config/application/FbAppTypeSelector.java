package com.facebook.config.application;

import com.facebook.common.build.config.BuildConfig;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FbAppTypeSelector {
    public static FbAppType selectAppTypeForSample(FbAppType fbAppType, FbAppType fbAppType2, String str) {
        String str2 = BuildConfig.KEYSTORE_TYPE;
        if ("inhouse".equals(str2)) {
            return fbAppType2;
        }
        if ("debug".equals(str2)) {
            return fbAppType;
        }
        throw new RuntimeException(str);
    }

    public static FbAppType selectAppType(FbAppType fbAppType, FbAppType fbAppType2, FbAppType fbAppType3) {
        String str = BuildConfig.KEYSTORE_TYPE;
        if ("inhouse".equals(str)) {
            return fbAppType2;
        }
        return "debug".equals(str) ? fbAppType : fbAppType3;
    }
}
