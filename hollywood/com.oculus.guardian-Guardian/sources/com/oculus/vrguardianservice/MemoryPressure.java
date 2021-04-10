package com.oculus.vrguardianservice;

import androidx.core.os.EnvironmentCompat;

public enum MemoryPressure {
    Unknown(-1, EnvironmentCompat.MEDIA_UNKNOWN),
    None(0, "none"),
    Some(1, "some"),
    Critical(3, "critical");
    
    private final int mEnumVal;
    private final String mStringVal;

    private MemoryPressure(int enumVal, String stringVal) {
        this.mEnumVal = enumVal;
        this.mStringVal = stringVal;
    }

    public int enumValue() {
        return this.mEnumVal;
    }

    public String stringVal() {
        return this.mStringVal;
    }

    public static MemoryPressure fromMemoryPressureValue(float memoryPressure) {
        if (memoryPressure == 0.0f) {
            return None;
        }
        if (memoryPressure >= 1.0f) {
            return Critical;
        }
        if (memoryPressure < 0.0f) {
            return Unknown;
        }
        return Some;
    }
}
