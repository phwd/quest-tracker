package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigTableUtil {
    public static final byte INVALID_BOOL_META = createBoolMeta(true, true, 0, false);
    public static final int INVALID_META = createMeta(true, true, false, 0, -1);

    public static byte createBoolMeta(boolean z, boolean z2, int i, boolean z3) {
        byte b = (byte) ((i << 1) & 6);
        int i2 = 0;
        int i3 = (i & 128) != 0 ? 8 : 0;
        int i4 = z ? 16 : 0;
        if (z3) {
            i2 = -128;
        }
        return (byte) (i4 | (z2 ? 1 : 0) | b | i3 | i2);
    }

    public static int createMeta(boolean z, boolean z2, boolean z3, int i, int i2) {
        int i3 = (i << 1) & 6;
        int i4 = 0;
        int i5 = (i & 128) != 0 ? 8 : 0;
        int i6 = z ? 16 : 0;
        if (z3) {
            i4 = 32;
        }
        return i6 | (z2 ? 1 : 0) | i3 | i5 | i4 | (i2 << 8);
    }

    public static boolean getBooleanValue(byte b) {
        return (b >>> 7) != 0;
    }

    public static int getLoggingModeWithoutCaptureStackFromMeta(int i) {
        return (i & 6) >>> 1;
    }

    public static int getLoggingSlotIdFromMeta(int i) {
        return (i >>> 8) & 16777215;
    }

    public static boolean hasLoggingIdFromMeta(byte b) {
        return (b & 6) != 0;
    }

    public static boolean hasLoggingIdFromMeta(int i) {
        return (i & 6) != 0;
    }

    public static boolean isCaptureStackFromMeta(int i) {
        return (i & 8) != 0;
    }

    public static boolean isMissingFromMeta(int i) {
        return (i & 16) != 0;
    }

    public static boolean isNullFromMeta(byte b) {
        return (b & 1) != 0;
    }

    public static boolean isNullFromMeta(int i) {
        return (i & 1) != 0;
    }
}
