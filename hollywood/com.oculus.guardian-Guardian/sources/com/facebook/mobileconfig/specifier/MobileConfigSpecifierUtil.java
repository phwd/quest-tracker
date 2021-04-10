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

    public static int getConfigIndex(long paramSpecifier) {
        return (int) ((paramSpecifier >>> 32) & 65535);
    }

    public static int getConfigKey(long paramSpecifier) {
        return (int) ((paramSpecifier >>> 32) & 65535);
    }

    @Deprecated
    public static int getParamIndex(long paramSpecifier) {
        return (int) ((paramSpecifier >>> 16) & 65535);
    }

    public static int getParamKey(long paramSpecifier) {
        return (int) ((paramSpecifier >>> 16) & 65535);
    }

    public static int getSlotId(long paramSpecifier) {
        return (int) (65535 & paramSpecifier);
    }

    public static boolean getStdDefault(long paramSpecifier) {
        return ((paramSpecifier >> 61) & 1) == 1;
    }

    public static int getParamType(long paramSpecifier) {
        return MobileConfigParamTypeUtil.valueOf((int) ((paramSpecifier >>> 48) & 63));
    }

    public static int getUnitType(long paramSpecifier) {
        return MobileConfigUnitTypeUtil.valueOf((int) ((paramSpecifier >>> 54) & 63));
    }

    public static boolean getIsSessionless(long paramSpecifier) {
        return getUnitType(paramSpecifier) == 1;
    }

    public static boolean getRequireCallsiteDefault(long paramSpecifier) {
        return ((paramSpecifier >>> 60) & 1) == 1;
    }

    @Deprecated
    public static long makeSpecifier(int paramType, int configIndex, int paramIndex, int slotId, boolean isSessionless, boolean requireCallsiteDefault) {
        return makeSpecifier(paramType, configIndex, paramIndex, slotId, isSessionless ? 1 : 0, requireCallsiteDefault, false);
    }

    @Deprecated
    public static long makeSpecifier(int paramType, int configIndex, int paramIndex, int slotId, boolean isSessionless, boolean requireCallsiteDefault, boolean stdDefaultValue) {
        return makeSpecifier(paramType, configIndex, paramIndex, slotId, isSessionless ? 1 : 0, requireCallsiteDefault, stdDefaultValue);
    }

    public static long makeSpecifier(int paramType, int configIndex, int paramIndex, int slotId, int unitType, boolean requireCallsiteDefault) {
        return makeSpecifier(paramType, configIndex, paramIndex, slotId, unitType, requireCallsiteDefault, false);
    }

    public static long makeSpecifier(int paramType, int configIndex, int paramIndex, int slotId, int unitType, boolean requireCallsiteDefault, boolean stdDefaultValue) {
        long shiftedUnitType = ((long) unitType) << 54;
        long shiftedRequireCallsiteDefaultBit = 0;
        if (requireCallsiteDefault) {
            shiftedRequireCallsiteDefaultBit = 1152921504606846976L;
        }
        return shiftedUnitType | shiftedRequireCallsiteDefaultBit | (((long) paramType) << 48) | (((long) configIndex) << 32) | (((long) paramIndex) << 16) | ((long) slotId) | ((stdDefaultValue ? 1 : 0) << 61);
    }

    public static long maskSpecifier(long paramSpecifier, byte value) {
        if (value < 8) {
            return (((long) value) << 62) | paramSpecifier;
        }
        throw new AssertionError("Additional info cannot exceed 7");
    }
}
