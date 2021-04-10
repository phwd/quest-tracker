package com.google.common.math;

import com.google.common.base.Preconditions;
import java.math.BigInteger;

/* access modifiers changed from: package-private */
public final class DoubleUtils {
    static final int EXPONENT_BIAS = 1023;
    static final long EXPONENT_MASK = 9218868437227405312L;
    static final long IMPLICIT_BIT = 4503599627370496L;
    private static final long ONE_BITS = Double.doubleToRawLongBits(1.0d);
    static final int SIGNIFICAND_BITS = 52;
    static final long SIGNIFICAND_MASK = 4503599627370495L;
    static final long SIGN_MASK = Long.MIN_VALUE;

    private DoubleUtils() {
    }

    static double nextDown(double d) {
        return -Math.nextUp(-d);
    }

    static long getSignificand(double d) {
        Preconditions.checkArgument(isFinite(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long bits = Double.doubleToRawLongBits(d) & SIGNIFICAND_MASK;
        if (exponent == -1023) {
            return bits << 1;
        }
        return IMPLICIT_BIT | bits;
    }

    static boolean isFinite(double d) {
        return Math.getExponent(d) <= EXPONENT_BIAS;
    }

    static boolean isNormal(double d) {
        return Math.getExponent(d) >= -1022;
    }

    static double scaleNormalize(double x) {
        return Double.longBitsToDouble(ONE_BITS | (Double.doubleToRawLongBits(x) & SIGNIFICAND_MASK));
    }

    static double bigToDouble(BigInteger x) {
        BigInteger absX = x.abs();
        boolean increment = true;
        int exponent = absX.bitLength() - 1;
        if (exponent < 63) {
            return (double) x.longValue();
        }
        if (exponent > EXPONENT_BIAS) {
            return ((double) x.signum()) * Double.POSITIVE_INFINITY;
        }
        int shift = (exponent - 52) - 1;
        long twiceSignifFloor = absX.shiftRight(shift).longValue();
        long signifFloor = (twiceSignifFloor >> 1) & SIGNIFICAND_MASK;
        if ((twiceSignifFloor & 1) == ONE_BITS || ((signifFloor & 1) == ONE_BITS && absX.getLowestSetBit() >= shift)) {
            increment = false;
        }
        return Double.longBitsToDouble(((((long) (exponent + EXPONENT_BIAS)) << 52) + (increment ? 1 + signifFloor : signifFloor)) | (((long) x.signum()) & SIGN_MASK));
    }

    static double ensureNonNegative(double value) {
        Preconditions.checkArgument(!Double.isNaN(value));
        if (value > 0.0d) {
            return value;
        }
        return 0.0d;
    }
}
