package java.lang;

public final class Math {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static long negativeZeroDoubleBits = Double.doubleToRawLongBits(-0.0d);
    private static long negativeZeroFloatBits = ((long) Float.floatToRawIntBits(-0.0f));
    static double twoToTheDoubleScaleDown = powerOfTwoD(-512);
    static double twoToTheDoubleScaleUp = powerOfTwoD(512);

    public static int abs(int i) {
        return i < 0 ? -i : i;
    }

    public static long abs(long j) {
        return j < 0 ? -j : j;
    }

    public static native double ceil(double d);

    public static native double floor(double d);

    public static native double log(double d);

    public static native double log10(double d);

    public static int max(int i, int i2) {
        return i >= i2 ? i : i2;
    }

    public static long max(long j, long j2) {
        return j >= j2 ? j : j2;
    }

    public static int min(int i, int i2) {
        return i <= i2 ? i : i2;
    }

    public static long min(long j, long j2) {
        return j <= j2 ? j : j2;
    }

    public static native double pow(double d, double d2);

    public static native double rint(double d);

    public static long round(double d) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = 1074 - ((9218868437227405312L & doubleToRawLongBits) >> 52);
        if ((-64 & j) != 0) {
            return (long) d;
        }
        long j2 = (4503599627370495L & doubleToRawLongBits) | 4503599627370496L;
        if (doubleToRawLongBits < 0) {
            j2 = -j2;
        }
        return ((j2 >> ((int) j)) + 1) >> 1;
    }

    public static long addExact(long j, long j2) {
        long j3 = j + j2;
        if (((j ^ j3) & (j2 ^ j3)) >= 0) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static long subtractExact(long j, long j2) {
        long j3 = j - j2;
        if (((j ^ j3) & (j2 ^ j)) >= 0) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static long multiplyExact(long j, long j2) {
        long j3 = j * j2;
        if (((abs(j) | abs(j2)) >>> 31) == 0 || ((j2 == 0 || j3 / j2 == j) && (j != Long.MIN_VALUE || j2 != -1))) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int toIntExact(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            return i;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long floorDiv(long j, long j2) {
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    public static long floorMod(long j, long j2) {
        return j - (floorDiv(j, j2) * j2);
    }

    public static double abs(double d) {
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d) & Long.MAX_VALUE);
    }

    public static double max(double d, double d2) {
        if (d != d) {
            return d;
        }
        if (d == 0.0d && d2 == 0.0d && Double.doubleToRawLongBits(d) == negativeZeroDoubleBits) {
            return d2;
        }
        return d >= d2 ? d : d2;
    }

    public static float min(float f, float f2) {
        if (f != f) {
            return f;
        }
        if (f == 0.0f && f2 == 0.0f && ((long) Float.floatToRawIntBits(f2)) == negativeZeroFloatBits) {
            return f2;
        }
        return f <= f2 ? f : f2;
    }

    public static double min(double d, double d2) {
        if (d != d) {
            return d;
        }
        if (d == 0.0d && d2 == 0.0d && Double.doubleToRawLongBits(d2) == negativeZeroDoubleBits) {
            return d2;
        }
        return d <= d2 ? d : d2;
    }

    public static float signum(float f) {
        return (f == 0.0f || Float.isNaN(f)) ? f : copySign(1.0f, f);
    }

    public static double copySign(double d, double d2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d) & Long.MAX_VALUE) | (Double.doubleToRawLongBits(d2) & Long.MIN_VALUE));
    }

    public static float copySign(float f, float f2) {
        return Float.intBitsToFloat((Float.floatToRawIntBits(f) & Integer.MAX_VALUE) | (Float.floatToRawIntBits(f2) & Integer.MIN_VALUE));
    }

    public static int getExponent(double d) {
        return (int) (((Double.doubleToRawLongBits(d) & 9218868437227405312L) >> 52) - 1023);
    }

    public static double scalb(double d, int i) {
        int i2;
        double d2;
        int i3;
        if (i < 0) {
            i2 = max(i, -2099);
            i3 = -512;
            d2 = twoToTheDoubleScaleDown;
        } else {
            i2 = min(i, 2099);
            i3 = 512;
            d2 = twoToTheDoubleScaleUp;
        }
        int i4 = (i2 >> 8) >>> 23;
        int i5 = ((i2 + i4) & 511) - i4;
        double powerOfTwoD = d * powerOfTwoD(i5);
        for (int i6 = i2 - i5; i6 != 0; i6 -= i3) {
            powerOfTwoD *= d2;
        }
        return powerOfTwoD;
    }

    static double powerOfTwoD(int i) {
        if ($assertionsDisabled || (i >= -1022 && i <= 1023)) {
            return Double.longBitsToDouble(((((long) i) + 1023) << 52) & 9218868437227405312L);
        }
        throw new AssertionError();
    }
}
