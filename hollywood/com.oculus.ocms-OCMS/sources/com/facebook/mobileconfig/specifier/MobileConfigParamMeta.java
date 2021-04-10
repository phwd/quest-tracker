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

    public MobileConfigParamMeta(String str, String str2, int i, int i2, int i3, int i4, boolean z) {
        this.configName = str;
        this.paramName = str2;
        this.key = i;
        this.configIndex = i2;
        this.paramIndex = i3;
        this.paramType = i4;
        this.isSessionless = z;
    }
}
