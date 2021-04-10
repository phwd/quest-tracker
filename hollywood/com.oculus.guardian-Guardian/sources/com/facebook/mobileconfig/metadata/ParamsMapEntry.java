package com.facebook.mobileconfig.metadata;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamsMapEntry {
    public static final String END_MARKER = "END";
    public static final String PARAMS_MAP_V2_PREFIX = "v2,";
    public static final String VIRTUAL_GK_PREFIX = "gk_";
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

    public long getSpecifier() {
        return MobileConfigSpecifierUtil.makeSpecifier(this.paramType, this.configIndex, this.paramIndex, this.slotId, this.isSessionless, this.requireCallsiteDefault, this.stdDefaultValue);
    }

    public static String parseSchemaHash(String paramsMapContent) {
        if (!paramsMapContent.startsWith(PARAMS_MAP_V2_PREFIX)) {
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

    public static List<ParamsMapEntry> parseParamsMapResource(String paramsMapContent) {
        return parseAllParamsMapResource(paramsMapContent, "", false);
    }

    public static Map<String, ParamsMapEntry> parseParamsMapToMap(String paramsMapContent) {
        Map<String, ParamsMapEntry> result = new HashMap<>();
        for (ParamsMapEntry param : parseAllParamsMapResource(paramsMapContent, "", true)) {
            result.put(param.configName + ":" + param.paramName, param);
        }
        return result;
    }

    public static String parseMergedSchemaHash(String nativeParamsMapContent, String addedParamsMapContent) {
        String nativeSchemaHash = parseSchemaHash(nativeParamsMapContent);
        if (addedParamsMapContent == null || addedParamsMapContent.isEmpty()) {
            return "";
        }
        String addedParamsMapHash = parseSchemaHash(addedParamsMapContent);
        StringBuffer sb = new StringBuffer();
        int partLength = addedParamsMapHash.length() / 8;
        for (int i = 0; i < 8; i++) {
            sb.append(String.format("%04x", Integer.valueOf(Integer.parseInt(nativeSchemaHash.substring(partLength * i, (i + 1) * partLength), 16) ^ Integer.parseInt(addedParamsMapHash.substring(partLength * i, (i + 1) * partLength), 16))));
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0168, code lost:
        throw new java.lang.RuntimeException("could not find key in configs " + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01a4, code lost:
        throw new java.lang.RuntimeException("could not find configName in configs " + r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.facebook.mobileconfig.metadata.ParamsMapEntry> parseAllParamsMapResource(java.lang.String r53, java.lang.String r54, boolean r55) {
        /*
        // Method dump skipped, instructions count: 1103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.metadata.ParamsMapEntry.parseAllParamsMapResource(java.lang.String, java.lang.String, boolean):java.util.List");
    }
}
