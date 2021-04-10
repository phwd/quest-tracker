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
    public static TriState fromDbValue(int i) {
        if (i == 1) {
            return YES;
        }
        if (i != 2) {
            return UNSET;
        }
        return NO;
    }

    @Functional
    public boolean isSet() {
        if (this != UNSET) {
            return true;
        }
        return false;
    }

    @Nullable
    @Functional
    public Boolean asBooleanObject() {
        switch (ordinal()) {
            case 0:
                return Boolean.TRUE;
            case 1:
                return Boolean.FALSE;
            case 2:
                return null;
            default:
                StringBuilder sb = new StringBuilder("Unrecognized TriState value: ");
                sb.append(this);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Functional
    public int getDbValue() {
        switch (ordinal()) {
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return 3;
        }
    }

    @Functional
    public static TriState valueOf(@Nullable Boolean bool) {
        return bool != null ? valueOf(bool.booleanValue()) : UNSET;
    }

    @Functional
    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    @Functional
    public boolean asBoolean() {
        switch (ordinal()) {
            case 0:
                return true;
            case 1:
                return false;
            case 2:
                throw new IllegalStateException("No boolean equivalent for UNSET");
            default:
                StringBuilder sb = new StringBuilder("Unrecognized TriState value: ");
                sb.append(this);
                throw new IllegalStateException(sb.toString());
        }
    }

    @Functional
    public boolean asBoolean(boolean z) {
        switch (ordinal()) {
            case 0:
                return true;
            case 1:
                return false;
            case 2:
                return z;
            default:
                StringBuilder sb = new StringBuilder("Unrecognized TriState value: ");
                sb.append(this);
                throw new IllegalStateException(sb.toString());
        }
    }
}
