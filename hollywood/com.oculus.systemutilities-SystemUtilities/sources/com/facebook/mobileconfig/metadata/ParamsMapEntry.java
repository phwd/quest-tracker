package com.facebook.mobileconfig.metadata;

import java.util.HashMap;
import java.util.Map;

public class ParamsMapEntry {
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

    public ParamsMapEntry(String configName2, String paramName2, int key2, int configIndex2, int paramIndex2, int slotId2, int paramType2, boolean isSessionless2, boolean requireCallsiteDefault2, boolean stdDefaultValue2, int configKey2) {
        this.configName = configName2;
        this.paramName = paramName2;
        this.key = key2;
        this.configIndex = configIndex2;
        this.paramIndex = paramIndex2;
        this.slotId = slotId2;
        this.paramType = paramType2;
        this.isSessionless = isSessionless2;
        this.requireCallsiteDefault = requireCallsiteDefault2;
        this.stdDefaultValue = stdDefaultValue2;
        this.configKey = configKey2;
    }

    public static String parseSchemaHash(String paramsMapContent) {
        if (!paramsMapContent.startsWith("v2,")) {
            return "";
        }
        String[] lines = paramsMapContent.split("\\r?\\n");
        if (lines.length == 0) {
            return "";
        }
        String[] parts = lines[0].split(",");
        if (parts.length < 2) {
            return "";
        }
        return parts[1];
    }

    public static Map<String, ParamsMapEntry> parseParamsMapToMap(String paramsMapContent) {
        Map<String, ParamsMapEntry> result = new HashMap<>();
        for (ParamsMapEntry param : parseAllParamsMapResource(paramsMapContent, "", true)) {
            result.put(param.configName + ":" + param.paramName, param);
        }
        return result;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03c7, code lost:
        r4 = r4;
        r7 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b3, code lost:
        if (r22 != false) goto L_0x03c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bc, code lost:
        throw new java.lang.RuntimeException("Invalid paramsMapContent: no END marker found");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01d8, code lost:
        throw new java.lang.RuntimeException("could not find key in configs " + r31);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.facebook.mobileconfig.metadata.ParamsMapEntry> parseAllParamsMapResource(java.lang.String r48, java.lang.String r49, boolean r50) {
        /*
        // Method dump skipped, instructions count: 1018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.metadata.ParamsMapEntry.parseAllParamsMapResource(java.lang.String, java.lang.String, boolean):java.util.List");
    }
}
