package com.facebook.mobileconfig.metadata;

import com.oculus.common.build.BuildConfig;
import java.util.HashMap;
import java.util.Map;

public final class ParamsMapEntry {
    public final int configIndex;
    public final int configKey;
    public final String configName;
    public final boolean isSessionless;
    public final int key;
    public final int paramIndex;
    public final String paramName;
    public final int paramType;
    public final boolean requireCallsiteDefault;
    public final int slotId;
    public final boolean stdDefaultValue;

    private ParamsMapEntry(String str, String str2, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6) {
        this.configName = str;
        this.paramName = str2;
        this.key = i;
        this.configIndex = i2;
        this.paramIndex = i3;
        this.slotId = i4;
        this.paramType = i5;
        this.isSessionless = z;
        this.requireCallsiteDefault = z2;
        this.stdDefaultValue = z3;
        this.configKey = i6;
    }

    public static Map<String, ParamsMapEntry> parseParamsMapToMap(String str) {
        HashMap hashMap = new HashMap();
        for (ParamsMapEntry paramsMapEntry : parseAllParamsMapResource(str, BuildConfig.PROVIDER_SUFFIX, true)) {
            hashMap.put(paramsMapEntry.configName + ":" + paramsMapEntry.paramName, paramsMapEntry);
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0136, code lost:
        throw new java.lang.RuntimeException("could not find key in configs " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0168, code lost:
        throw new java.lang.RuntimeException("could not find configName in configs " + r14);
     */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02b9  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02d0  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02e2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.facebook.mobileconfig.metadata.ParamsMapEntry> parseAllParamsMapResource(java.lang.String r45, java.lang.String r46, boolean r47) {
        /*
        // Method dump skipped, instructions count: 971
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.metadata.ParamsMapEntry.parseAllParamsMapResource(java.lang.String, java.lang.String, boolean):java.util.List");
    }
}
