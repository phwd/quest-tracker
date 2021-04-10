package com.oculus.library.model;

public enum AppMediaCapability {
    UNKNOWN,
    LIVESTREAMING,
    VRCASTING,
    SCREENRECORDING;

    public static AppMediaCapability fromString(String cap) {
        AppMediaCapability[] values = values();
        for (AppMediaCapability capability : values) {
            if (capability.name().equals(cap)) {
                return capability;
            }
        }
        return UNKNOWN;
    }
}
