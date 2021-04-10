package com.facebook.mobileconfig.specifier;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigSpecifierUtil {
    public static int getConfigIndex(long j) {
        return (int) ((j >>> 32) & 65535);
    }

    public static int getConfigKey(long j) {
        return (int) ((j >>> 32) & 65535);
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

    public static int getParamType(long j) {
        return MobileConfigParamTypeUtil.valueOf((int) ((j >>> 48) & 63));
    }

    public static int getUnitType(long j) {
        return MobileConfigUnitTypeUtil.valueOf((int) ((j >>> 54) & 63));
    }

    public static boolean getIsSessionless(long j) {
        return getUnitType(j) == 1;
    }
}
