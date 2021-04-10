package com.facebook.common.util;

import com.facebook.infer.annotation.Functional;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
public enum TriState {
    YES,
    NO,
    UNSET;

    @Functional
    public boolean isSet() {
        return this != UNSET;
    }

    @Functional
    public static TriState valueOf(boolean bool) {
        return bool ? YES : NO;
    }

    @Functional
    public static TriState valueOf(@Nullable Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    @Functional
    public boolean asBoolean() {
        switch (this) {
            case YES:
                return true;
            case NO:
                return false;
            case UNSET:
                throw new IllegalStateException("No boolean equivalent for UNSET");
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Functional
    public boolean asBoolean(boolean defaultValue) {
        switch (this) {
            case YES:
                return true;
            case NO:
                return false;
            case UNSET:
                return defaultValue;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Nullable
    @Functional
    public Boolean asBooleanObject() {
        switch (this) {
            case YES:
                return Boolean.TRUE;
            case NO:
                return Boolean.FALSE;
            case UNSET:
                return null;
            default:
                throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
    }

    @Functional
    public int getDbValue() {
        switch (this) {
            case YES:
                return 1;
            case NO:
                return 2;
            default:
                return 3;
        }
    }

    @Functional
    public static TriState fromDbValue(int value) {
        switch (value) {
            case 1:
                return YES;
            case 2:
                return NO;
            default:
                return UNSET;
        }
    }
}
