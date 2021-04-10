package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
public enum TriState {
    YES,
    NO,
    UNSET;

    public static TriState valueOf(Boolean bool) {
        if (bool == null) {
            return UNSET;
        }
        if (bool.booleanValue()) {
            return YES;
        }
        return NO;
    }
}
