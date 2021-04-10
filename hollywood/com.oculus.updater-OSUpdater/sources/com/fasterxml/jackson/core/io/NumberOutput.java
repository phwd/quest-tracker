package com.fasterxml.jackson.core.io;

import com.facebook.acra.constants.ErrorReportingConstants;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    static final char[] FULL_TRIPLETS = new char[4000];
    static final byte[] FULL_TRIPLETS_B = new byte[4000];
    static final char[] LEADING_TRIPLETS = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    static final String[] sSmallIntStrs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] sSmallIntStrs2 = {ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL, "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int i = 0;
        int i2 = 0;
        while (i < 10) {
            char c = (char) (i + 48);
            char c2 = i == 0 ? 0 : c;
            int i3 = i2;
            int i4 = 0;
            while (i4 < 10) {
                char c3 = (char) (i4 + 48);
                char c4 = (i == 0 && i4 == 0) ? 0 : c3;
                int i5 = i3;
                for (int i6 = 0; i6 < 10; i6++) {
                    char c5 = (char) (i6 + 48);
                    char[] cArr = LEADING_TRIPLETS;
                    cArr[i5] = c2;
                    int i7 = i5 + 1;
                    cArr[i7] = c4;
                    int i8 = i5 + 2;
                    cArr[i8] = c5;
                    char[] cArr2 = FULL_TRIPLETS;
                    cArr2[i5] = c;
                    cArr2[i7] = c3;
                    cArr2[i8] = c5;
                    i5 += 4;
                }
                i4++;
                i3 = i5;
            }
            i++;
            i2 = i3;
        }
        for (int i9 = 0; i9 < 4000; i9++) {
            FULL_TRIPLETS_B[i9] = (byte) FULL_TRIPLETS[i9];
        }
    }

    public static String toString(int i) {
        String[] strArr = sSmallIntStrs;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            return Long.toString(j);
        }
        return toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }
}
