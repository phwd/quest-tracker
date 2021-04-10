package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigParamMeta {
    public final int configIndex;
    public final String configName;
    public final boolean isSessionless;
    public final int key;
    public final int paramIndex;
    public final String paramName;
    public final int paramType;

    public MobileConfigParamMeta(String configName2, String paramName2, int key2, int configIndex2, int paramIndex2, int paramType2, boolean isSessionless2) {
        this.configName = configName2;
        this.paramName = paramName2;
        this.key = key2;
        this.configIndex = configIndex2;
        this.paramIndex = paramIndex2;
        this.paramType = paramType2;
        this.isSessionless = isSessionless2;
    }
}
