package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @VisibleForTesting
    @GwtIncompatible("TODO")
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, 16, 16, 16, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    @VisibleForTesting
    @GwtIncompatible("TODO")
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    public static boolean isPowerOfTwo(long x) {
        boolean z = true;
        boolean z2 = x > 0;
        if (((x - 1) & x) != 0) {
            z = false;
        }
        return z2 & z;
    }

    @VisibleForTesting
    static int lessThanBranchFree(long x, long y) {
        return (int) ((~(~(x - y))) >>> 63);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.LongMath$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        static {
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(long x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(x));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(x - 1);
            case 6:
            case 7:
            case 8:
                int leadingZeros = Long.numberOfLeadingZeros(x);
                return lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> leadingZeros, x) + (63 - leadingZeros);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(x);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @GwtIncompatible("TODO")
    public static int log10(long x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        int logFloor = log10Floor(x);
        long floorPow = powersOf10[logFloor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(x == floorPow);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return lessThanBranchFree(floorPow, x) + logFloor;
            case 6:
            case 7:
            case 8:
                return lessThanBranchFree(halfPowersOf10[logFloor], x) + logFloor;
            default:
                throw new AssertionError();
        }
        return logFloor;
    }

    @GwtIncompatible("TODO")
    static int log10Floor(long x) {
        byte b = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(x)];
        return b - lessThanBranchFree(x, powersOf10[b]);
    }

    @GwtIncompatible("TODO")
    public static long pow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (-2 > b || b > 2) {
            long accum = 1;
            while (k != 0) {
                if (k == 1) {
                    return accum * b;
                }
                accum *= (k & 1) == 0 ? 1 : b;
                b *= b;
                k >>= 1;
            }
            return accum;
        }
        int i = (int) b;
        if (i != -2) {
            if (i != -1) {
                if (i != 0) {
                    if (i == 1) {
                        return 1;
                    }
                    if (i != 2) {
                        throw new AssertionError();
                    } else if (k < 64) {
                        return 1 << k;
                    } else {
                        return 0;
                    }
                } else if (k == 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else if ((k & 1) == 0) {
                return 1;
            } else {
                return -1;
            }
        } else if (k < 64) {
            return (k & 1) == 0 ? 1 << k : -(1 << k);
        } else {
            return 0;
        }
    }

    @GwtIncompatible("TODO")
    public static long sqrt(long x, RoundingMode mode) {
        MathPreconditions.checkNonNegative("x", x);
        if (fitsInInt(x)) {
            return (long) IntMath.sqrt((int) x, mode);
        }
        long guess = (long) Math.sqrt((double) x);
        long guessSquared = guess * guess;
        boolean z = true;
        int i = 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                if (guessSquared != x) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return guess;
            case 2:
            case 3:
                if (x < guessSquared) {
                    return guess - 1;
                }
                return guess;
            case 4:
            case 5:
                if (x > guessSquared) {
                    return 1 + guess;
                }
                return guess;
            case 6:
            case 7:
            case 8:
                if (x >= guessSquared) {
                    i = 0;
                }
                long sqrtFloor = guess - ((long) i);
                return ((long) lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, x)) + sqrtFloor;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible("TODO")
    public static long divide(long p, long q, RoundingMode mode) {
        boolean increment;
        Preconditions.checkNotNull(mode);
        long div = p / q;
        long rem = p - (q * div);
        if (rem == 0) {
            return div;
        }
        boolean z = true;
        int signum = ((int) ((p ^ q) >> 63)) | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                if (rem != 0) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
            case 2:
                increment = false;
                break;
            case 3:
                if (signum >= 0) {
                    z = false;
                }
                increment = z;
                break;
            case 4:
                increment = true;
                break;
            case 5:
                if (signum <= 0) {
                    z = false;
                }
                increment = z;
                break;
            case 6:
            case 7:
            case 8:
                long absRem = Math.abs(rem);
                long cmpRemToHalfDivisor = absRem - (Math.abs(q) - absRem);
                if (cmpRemToHalfDivisor != 0) {
                    if (cmpRemToHalfDivisor <= 0) {
                        z = false;
                    }
                    increment = z;
                    break;
                } else {
                    boolean z2 = mode == RoundingMode.HALF_UP;
                    boolean z3 = mode == RoundingMode.HALF_EVEN;
                    if ((div & 1) == 0) {
                        z = false;
                    }
                    increment = (z3 & z) | z2;
                    break;
                }
            default:
                throw new AssertionError();
        }
        return increment ? ((long) signum) + div : div;
    }

    @GwtIncompatible("TODO")
    public static int mod(long x, int m) {
        return (int) mod(x, (long) m);
    }

    @GwtIncompatible("TODO")
    public static long mod(long x, long m) {
        if (m > 0) {
            long result = x % m;
            return result >= 0 ? result : result + m;
        }
        throw new ArithmeticException("Modulus must be positive");
    }

    public static long gcd(long a, long b) {
        MathPreconditions.checkNonNegative("a", a);
        MathPreconditions.checkNonNegative("b", b);
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int aTwos = Long.numberOfTrailingZeros(a);
        long a2 = a >> aTwos;
        int bTwos = Long.numberOfTrailingZeros(b);
        long b2 = b >> bTwos;
        while (a2 != b2) {
            long delta = a2 - b2;
            long minDeltaOrZero = (delta >> 63) & delta;
            long a3 = (delta - minDeltaOrZero) - minDeltaOrZero;
            b2 += minDeltaOrZero;
            a2 = a3 >> Long.numberOfTrailingZeros(a3);
        }
        return a2 << Math.min(aTwos, bTwos);
    }

    @GwtIncompatible("TODO")
    public static long checkedAdd(long a, long b) {
        long result = a + b;
        boolean z = true;
        boolean z2 = (a ^ b) < 0;
        if ((a ^ result) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z);
        return result;
    }

    @GwtIncompatible("TODO")
    public static long checkedSubtract(long a, long b) {
        long result = a - b;
        boolean z = true;
        boolean z2 = (a ^ b) >= 0;
        if ((a ^ result) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z);
        return result;
    }

    @GwtIncompatible("TODO")
    public static long checkedMultiply(long a, long b) {
        int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(~a) + Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(~b);
        if (leadingZeros > 65) {
            return a * b;
        }
        boolean z = true;
        MathPreconditions.checkNoOverflow(leadingZeros >= 64);
        MathPreconditions.checkNoOverflow((a >= 0) | (b != Long.MIN_VALUE));
        long result = a * b;
        if (!(a == 0 || result / a == b)) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z);
        return result;
    }

    @GwtIncompatible("TODO")
    public static long checkedPow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        boolean z = false;
        if ((b >= -2) && (b <= 2)) {
            int i = (int) b;
            if (i == -2) {
                if (k < 64) {
                    z = true;
                }
                MathPreconditions.checkNoOverflow(z);
                return (k & 1) == 0 ? 1 << k : -1 << k;
            } else if (i == -1) {
                return (k & 1) == 0 ? 1 : -1;
            } else {
                if (i != 0) {
                    if (i == 1) {
                        return 1;
                    }
                    if (i == 2) {
                        if (k < 63) {
                            z = true;
                        }
                        MathPreconditions.checkNoOverflow(z);
                        return 1 << k;
                    }
                    throw new AssertionError();
                } else if (k == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            long accum = 1;
            while (k != 0) {
                if (k == 1) {
                    return checkedMultiply(accum, b);
                }
                if ((k & 1) != 0) {
                    accum = checkedMultiply(accum, b);
                }
                k >>= 1;
                if (k > 0) {
                    MathPreconditions.checkNoOverflow(b <= FLOOR_SQRT_MAX_LONG);
                    b *= b;
                }
            }
            return accum;
        }
    }

    @GwtIncompatible("TODO")
    public static long factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        long[] jArr = factorials;
        if (n < jArr.length) {
            return jArr[n];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int n, int k) {
        int k2 = k;
        MathPreconditions.checkNonNegative("n", n);
        MathPreconditions.checkNonNegative("k", k2);
        Preconditions.checkArgument(k2 <= n, "k (%s) > n (%s)", Integer.valueOf(k), Integer.valueOf(n));
        if (k2 > (n >> 1)) {
            k2 = n - k2;
        }
        if (k2 == 0) {
            return 1;
        }
        if (k2 == 1) {
            return (long) n;
        }
        long[] jArr = factorials;
        if (n < jArr.length) {
            return jArr[n] / (jArr[k2] * jArr[n - k2]);
        }
        int[] iArr = biggestBinomials;
        if (k2 >= iArr.length || n > iArr[k2]) {
            return Long.MAX_VALUE;
        }
        int[] iArr2 = biggestSimpleBinomials;
        if (k2 >= iArr2.length || n > iArr2[k2]) {
            int nBits = log2((long) n, RoundingMode.CEILING);
            int i = 2;
            long result = 1;
            long numerator = (long) n;
            long denominator = 1;
            int numeratorBits = nBits;
            int n2 = n - 1;
            while (i <= k2) {
                if (numeratorBits + nBits < 63) {
                    numerator *= (long) n2;
                    denominator *= (long) i;
                    numeratorBits += nBits;
                } else {
                    numeratorBits = nBits;
                    result = multiplyFraction(result, numerator, denominator);
                    numerator = (long) n2;
                    denominator = (long) i;
                }
                i++;
                n2--;
            }
            return multiplyFraction(result, numerator, denominator);
        }
        int n3 = n - 1;
        long result2 = (long) n;
        for (int i2 = 2; i2 <= k2; i2++) {
            result2 = (result2 * ((long) n3)) / ((long) i2);
            n3--;
        }
        return result2;
    }

    static long multiplyFraction(long x, long numerator, long denominator) {
        if (x == 1) {
            return numerator / denominator;
        }
        long commonDivisor = gcd(x, denominator);
        return (numerator / (denominator / commonDivisor)) * (x / commonDivisor);
    }

    static boolean fitsInInt(long x) {
        return ((long) ((int) x)) == x;
    }

    public static long mean(long x, long y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    private LongMath() {
    }
}
