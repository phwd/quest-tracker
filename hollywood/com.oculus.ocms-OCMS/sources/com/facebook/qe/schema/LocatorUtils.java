package com.facebook.qe.schema;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LocatorUtils {
    private static final int MAX_SHORT_AS_INT = 32767;
    private static final int MIN_SHORT_AS_INT = -32768;
    private static final int SESSIONLESS_LOCATOR_MASK = 1;

    public static boolean isTypeSafeLocatorSessionless(char c) {
        return (c & 1) != 0;
    }

    public static boolean isTypeSafeLocatorSessionless(double d) {
        return (((int) d) & 1) != 0;
    }

    public static boolean isTypeSafeLocatorSessionless(float f) {
        return (((int) f) & 1) != 0;
    }

    public static boolean isTypeSafeLocatorSessionless(int i) {
        return (i & 1) != 0;
    }

    public static boolean isTypeSafeLocatorSessionless(long j) {
        return (j & 1) != 0;
    }

    public static boolean isTypeSafeLocatorSessionless(short s) {
        return (s & 1) != 0;
    }

    public static short makeTypeSafeBooleanLocator(int i, boolean z) {
        return (short) (((i << 1) + MIN_SHORT_AS_INT) | (z ? 1 : 0));
    }

    public static double makeTypeSafeEnumLocator(int i, boolean z) {
        return (double) ((i << 1) | (z ? 1 : 0));
    }

    public static float makeTypeSafeFloatLocator(int i, boolean z) {
        return (float) ((i << 1) | (z ? 1 : 0));
    }

    public static int makeTypeSafeIntLocator(int i, boolean z) {
        return (i << 1) | (z ? 1 : 0);
    }

    public static long makeTypeSafeLongLocator(int i, boolean z) {
        return (long) ((i << 1) | (z ? 1 : 0));
    }

    public static char makeTypeSafeStringLocator(int i, boolean z) {
        return (char) ((i << 1) | (z ? 1 : 0));
    }

    public static int typeSafeLocatorToSlot(char c) {
        return c >> 1;
    }

    public static int typeSafeLocatorToSlot(double d) {
        return ((int) d) >> 1;
    }

    public static int typeSafeLocatorToSlot(float f) {
        return ((int) f) >> 1;
    }

    public static int typeSafeLocatorToSlot(int i) {
        return i >> 1;
    }

    public static int typeSafeLocatorToSlot(long j) {
        return ((int) j) >> 1;
    }

    public static int typeSafeLocatorToSlot(short s) {
        return (s <= MAX_SHORT_AS_INT ? s + 32768 : s - 32768) >> 1;
    }
}
