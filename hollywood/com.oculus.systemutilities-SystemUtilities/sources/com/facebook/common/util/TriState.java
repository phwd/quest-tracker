package com.facebook.common.util;

public enum TriState {
    YES,
    NO,
    UNSET;

    public static TriState valueOf(boolean bool) {
        return bool ? YES : NO;
    }

    public static TriState valueOf(Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }
}
