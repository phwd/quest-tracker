package com.oculus.library.model;

public enum SupportedPlatform {
    UNKNOWN,
    ANDROID_6DOF,
    ANDROID;

    public static SupportedPlatform fromString(String type) {
        SupportedPlatform[] values = values();
        for (SupportedPlatform platform : values) {
            if (platform.name().equals(type)) {
                return platform;
            }
        }
        return UNKNOWN;
    }
}
