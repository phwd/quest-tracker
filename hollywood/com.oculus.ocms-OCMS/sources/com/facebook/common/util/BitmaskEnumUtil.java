package com.facebook.common.util;

import com.facebook.debug.log.BLog;
import com.google.common.base.Preconditions;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;

public class BitmaskEnumUtil {
    private static final Class<?> TAG = BitmaskEnumUtil.class;

    private BitmaskEnumUtil() {
    }

    @Nullable
    public static <T extends Enum<T>> T fromFlagValue(Class<T> cls, long j) {
        Preconditions.checkArgument(Long.bitCount(j) == 1, "Invalid flag value: %d", j);
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        T[] enumConstants = cls.getEnumConstants();
        if (numberOfTrailingZeros < enumConstants.length) {
            return enumConstants[numberOfTrailingZeros];
        }
        BLog.w(TAG, "Unrecognized flag value for enum %s: %d", cls.getSimpleName(), Long.valueOf(j));
        return null;
    }

    public static long toFlagValue(Enum r2) {
        return 1 << r2.ordinal();
    }

    public static <T extends Enum<T>> EnumSet<T> fromBitmaskValue(Class<T> cls, long j) {
        EnumSet<T> noneOf = EnumSet.noneOf(cls);
        if (j == 0) {
            return noneOf;
        }
        T[] enumConstants = cls.getEnumConstants();
        for (T t : enumConstants) {
            if (hasFlagSet(j, toFlagValue(t))) {
                noneOf.add(t);
            }
        }
        return noneOf;
    }

    public static <T extends Enum<T>> long toBitmaskValue(Set<T> set) {
        long j = 0;
        for (T t : set) {
            j |= toFlagValue(t);
        }
        return j;
    }

    private static boolean hasFlagSet(long j, long j2) {
        Preconditions.checkArgument(Long.bitCount(j2) == 1);
        return (j & j2) != 0;
    }
}
