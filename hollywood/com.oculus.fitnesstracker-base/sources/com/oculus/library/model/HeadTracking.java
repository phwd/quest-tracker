package com.oculus.library.model;

public enum HeadTracking {
    UNKNOWN,
    REQUIRE_3DOF,
    ALLOW_6DOF,
    REQUIRE_6DOF;

    public static HeadTracking fromString(String str) {
        HeadTracking[] values = values();
        for (HeadTracking headTracking : values) {
            if (headTracking.name().equals(str)) {
                return headTracking;
            }
        }
        return UNKNOWN;
    }
}
