package com.oculus.video.drm;

public enum DrmProxyResponseFormat {
    BYTECODE("bytecode", "BYTECODE"),
    JSON("json", "JSON"),
    STRING("string", "STRING");
    
    public final String key;
    public final String value;

    private DrmProxyResponseFormat(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public static DrmProxyResponseFormat getDrmProxyResponseFormatFromString(String str) {
        if (str == null) {
            return BYTECODE;
        }
        DrmProxyResponseFormat[] values = values();
        for (DrmProxyResponseFormat drmProxyResponseFormat : values) {
            if (drmProxyResponseFormat.key.equalsIgnoreCase(str)) {
                return drmProxyResponseFormat;
            }
        }
        return BYTECODE;
    }

    public String toString() {
        return this.key;
    }
}
