package com.oculus.library.model;

public enum AppMediaCapability {
    UNKNOWN,
    LIVESTREAMING,
    VRCASTING,
    SCREENRECORDING;

    public static AppMediaCapability fromString(String str) {
        AppMediaCapability[] values = values();
        for (AppMediaCapability appMediaCapability : values) {
            if (appMediaCapability.name().equals(str)) {
                return appMediaCapability;
            }
        }
        return UNKNOWN;
    }
}
