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

    public ParamsMapEntry(String str, String str2, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6) {
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

    public long getSpecifier() {
        return MobileConfigSpecifierUtil.makeSpecifier(this.paramType, this.configIndex, this.paramIndex, this.slotId, this.isSessionless, this.requireCallsiteDefault, this.stdDefaultValue);
    }

    public static String parseSchemaHash(String str) {
        if (!str.startsWith(PARAMS_MAP_V2_PREFIX)) {
            return "";
        }
        String[] split = str.split("\\r?\\n");
        if (split.length == 0) {
            return "";
        }
        String[] split2 = split[0].split(",");
        if (split2.length < 2) {
            return "";
        }
        return split2[1];
    }

    public static List<ParamsMapEntry> parseParamsMapResource(String str) {
        return parseAllParamsMapResource(str, "", false);
    }

    public static Map<String, ParamsMapEntry> parseParamsMapToMap(String str) {
        HashMap hashMap = new HashMap();
        for (ParamsMapEntry paramsMapEntry : parseAllParamsMapResource(str, "", true)) {
            hashMap.put(paramsMapEntry.configName + ":" + paramsMapEntry.paramName, paramsMapEntry);
        }
        return hashMap;
    }

    public static String parseMergedSchemaHash(String str, String str2) {
        String parseSchemaHash = parseSchemaHash(str);
        if (str2 == null || str2.isEmpty()) {
            return "";
        }
        String parseSchemaHash2 = parseSchemaHash(str2);
        StringBuffer stringBuffer = new StringBuffer();
        int length = parseSchemaHash2.length() / 8;
        int i = 0;
        while (i < 8) {
            int i2 = length * i;
            i++;
            int i3 = length * i;
            stringBuffer.append(String.format("%04x", Integer.valueOf(Integer.parseInt(parseSchemaHash2.substring(i2, i3), 16) ^ Integer.parseInt(parseSchemaHash.substring(i2, i3), 16))));
        }
        return stringBuffer.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r45v0, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r45v1, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r45v2, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0148, code lost:
        throw new java.lang.RuntimeException("could not find key in configs " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x017d, code lost:
        throw new java.lang.RuntimeException("could not find configName in configs " + r6);
     */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x027b  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x027e  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.facebook.mobileconfig.metadata.ParamsMapEntry> parseAllParamsMapResource(java.lang.String r50, java.lang.String r51, boolean r52) {
        /*
        // Method dump skipped, instructions count: 1008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.metadata.ParamsMapEntry.parseAllParamsMapResource(java.lang.String, java.lang.String, boolean):java.util.List");
    }
}
