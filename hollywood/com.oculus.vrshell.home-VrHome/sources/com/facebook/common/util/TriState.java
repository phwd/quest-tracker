package com.facebook.common.util;

import javax.annotation.Nullable;

public enum TriState {
    YES,
    NO,
    UNSET;

    public static TriState valueOf(boolean bool) {
        return bool ? YES : NO;
    }

    public static TriState valueOf(@Nullable Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }
}
