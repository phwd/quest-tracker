package com.oculus.library.model;

public enum SupportedPlatform {
    UNKNOWN,
    ANDROID_6DOF,
    ANDROID;

    public static SupportedPlatform fromString(String str) {
        SupportedPlatform[] values = values();
        for (SupportedPlatform supportedPlatform : values) {
            if (supportedPlatform.name().equals(str)) {
                return supportedPlatform;
            }
        }
        return UNKNOWN;
    }
}
