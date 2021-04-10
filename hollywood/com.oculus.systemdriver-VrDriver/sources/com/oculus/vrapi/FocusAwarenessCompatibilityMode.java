package com.oculus.vrapi;

public enum FocusAwarenessCompatibilityMode {
    UNSET("UNSET"),
    ENABLED("ENABLED"),
    DISABLED("DISABLED"),
    FORCE_ENABLED("FORCE_ENABLED"),
    FORCE_DISABLED("FORCE_DISABLED");
    
    private final String mText;

    private FocusAwarenessCompatibilityMode(String text) {
        this.mText = text;
    }

    public String toString() {
        return this.mText;
    }

    public static FocusAwarenessCompatibilityMode fromString(String text, FocusAwarenessCompatibilityMode defaultValue) {
        if (text != null) {
            FocusAwarenessCompatibilityMode[] values = values();
            for (FocusAwarenessCompatibilityMode entry : values) {
                if (entry.mText.equalsIgnoreCase(text)) {
                    return entry;
                }
            }
        }
        return defaultValue;
    }
}
