package com.oculus.vrshell.memorypressure;

import androidx.core.os.EnvironmentCompat;

public enum MemoryPressure {
    Unknown(-1, EnvironmentCompat.MEDIA_UNKNOWN),
    None(0, "none"),
    Some(1, "some"),
    Critical(3, "critical");
    
    private final int mEnumVal;
    private final String mStringVal;

    private MemoryPressure(int i, String str) {
        this.mEnumVal = i;
        this.mStringVal = str;
    }

    public int enumValue() {
        return this.mEnumVal;
    }

    public String stringVal() {
        return this.mStringVal;
    }

    public static MemoryPressure fromMemoryPressureValue(float f) {
        if (f == 0.0f) {
            return None;
        }
        if (f >= 1.0f) {
            return Critical;
        }
        if (f < 0.0f) {
            return Unknown;
        }
        return Some;
    }
}
