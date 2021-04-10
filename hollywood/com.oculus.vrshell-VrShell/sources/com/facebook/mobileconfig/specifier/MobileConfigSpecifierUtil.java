package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigSpecifierUtil {
    private static final int CONFIG_INDEX_OFFSET = 32;
    private static final int NUM_USED_BITS = 62;
    private static final int PARAM_INDEX_OFFSET = 16;
    private static final int REQUIRE_CALLSITE_DEFAULT_OFFSET = 60;
    private static final int SIX_BIT_MASK = 63;
    private static final int STD_DEFAULT_OFFSET = 61;
    private static final int TWO_BYTE_MASK = 65535;
    private static final int TYPE_OFFSET = 48;
    private static final int UNIT_TYPE_OFFSET = 54;

    public static int getConfigIndex(long j) {
        return (int) ((j >>> 32) & 65535);
    }

    public static int getConfigKey(long j) {
        return (int) ((j >>> 32) & 65535);
    }

    @Deprecated
    public static int getParamIndex(long j) {
        return (int) ((j >>> 16) & 65535);
    }

    public static int getParamKey(long j) {
        return (int) ((j >>> 16) & 65535);
    }

    public static boolean getRequireCallsiteDefault(long j) {
        return ((j >>> 60) & 1) == 1;
    }

    public static int getSlotId(long j) {
        return (int) (j & 65535);
    }

    public static boolean getStdDefault(long j) {
        return ((j >> 61) & 1) == 1;
    }

    public static long makeSpecifier(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        long j = ((long) i5) << 54;
        long j2 = 0;
        long j3 = z ? 1152921504606846976L : 0;
        long j4 = ((long) i) << 48;
        long j5 = ((long) i2) << 32;
        long j6 = ((long) i3) << 16;
        if (z2) {
            j2 = 1;
        }
        return j5 | j3 | j | j4 | j6 | ((long) i4) | (j2 << 61);
    }

    public static int getParamType(long j) {
        return MobileConfigParamTypeUtil.valueOf((int) ((j >>> 48) & 63));
    }

    public static int getUnitType(long j) {
        return MobileConfigUnitTypeUtil.valueOf((int) ((j >>> 54) & 63));
    }

    public static boolean getIsSessionless(long j) {
        return getUnitType(j) == 1;
    }

    @Deprecated
    public static long makeSpecifier(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return makeSpecifier(i, i2, i3, i4, z ? 1 : 0, z2, false);
    }

    @Deprecated
    public static long makeSpecifier(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3) {
        return makeSpecifier(i, i2, i3, i4, z ? 1 : 0, z2, z3);
    }

    public static long makeSpecifier(int i, int i2, int i3, int i4, int i5, boolean z) {
        return makeSpecifier(i, i2, i3, i4, i5, z, false);
    }

    public static long maskSpecifier(long j, byte b) {
        if (b < 8) {
            return j | (((long) b) << 62);
        }
        throw new AssertionError("Additional info cannot exceed 7");
    }
}
