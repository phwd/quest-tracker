package com.google.common.math;

import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.common.time.Clock;
import com.facebook.common.time.TimeConstants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    @VisibleForTesting
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @VisibleForTesting
    @GwtIncompatible
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Clock.MAX_TIME, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};
    @VisibleForTesting
    @GwtIncompatible
    static final long[] powersOf10 = {1, 10, 100, 1000, StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS, 100000, 1000000, 10000000, 100000000, TimeConstants.NS_PER_SECOND, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    @Beta
    public static long ceilingPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        if (x <= 4611686018427387904L) {
            return 1 << (-Long.numberOfLeadingZeros(x - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + x + ") is not representable as a long");
    }

    @Beta
    public static long floorPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        return 1 << (63 - Long.numberOfLeadingZeros(x));
    }

    public static boolean isPowerOfTwo(long x) {
        boolean z = true;
        boolean z2 = x > 0;
        if (((x - 1) & x) != 0) {
            z = false;
        }
        return z & z2;
    }

    @VisibleForTesting
    static int lessThanBranchFree(long x, long y) {
        return (int) ((((x - y) ^ -1) ^ -1) >>> 63);
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

    @GwtIncompatible
    public static int log10(long x, RoundingMode mode) {
        boolean z;
        MathPreconditions.checkPositive("x", x);
        int logFloor = log10Floor(x);
        long floorPow = powersOf10[logFloor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                if (x == floorPow) {
                    z = true;
                } else {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return logFloor;
            case 2:
            case 3:
                return logFloor;
            case 4:
            case 5:
                return logFloor + lessThanBranchFree(floorPow, x);
            case 6:
            case 7:
            case 8:
                return logFloor + lessThanBranchFree(halfPowersOf10[logFloor], x);
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    static int log10Floor(long x) {
        byte b = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(x)];
        return b - lessThanBranchFree(x, powersOf10[b]);
    }

    @GwtIncompatible
    public static long pow(long b, int k) {
        long j;
        long j2 = 0;
        MathPreconditions.checkNonNegative("exponent", k);
        if (-2 > b || b > 2) {
            long accum = 1;
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return accum * b;
                    default:
                        if ((k & 1) == 0) {
                            j = 1;
                        } else {
                            j = b;
                        }
                        accum *= j;
                        b *= b;
                        k >>= 1;
                }
            }
        } else {
            switch ((int) b) {
                case -2:
                    if (k < 64) {
                        return (k & 1) == 0 ? 1 << k : -(1 << k);
                    }
                    return 0;
                case -1:
                    if ((k & 1) != 0) {
                        return -1;
                    }
                    return 1;
                case 0:
                    return k == 0 ? 1 : 0;
                case 1:
                    return 1;
                case 2:
                    if (k < 64) {
                        j2 = 1 << k;
                    }
                    return j2;
                default:
                    throw new AssertionError();
            }
        }
    }

    @GwtIncompatible
    public static long sqrt(long x, RoundingMode mode) {
        MathPreconditions.checkNonNegative("x", x);
        if (fitsInInt(x)) {
            return (long) IntMath.sqrt((int) x, mode);
        }
        long guess = (long) Math.sqrt((double) x);
        long guessSquared = guess * guess;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(guessSquared == x);
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
                    return guess + 1;
                }
                return guess;
            case 6:
            case 7:
            case 8:
                long sqrtFloor = guess - ((long) (x < guessSquared ? 1 : 0));
                return sqrtFloor + ((long) lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, x));
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static long divide(long p, long q, RoundingMode mode) {
        boolean increment;
        Preconditions.checkNotNull(mode);
        long div = p / q;
        long rem = p - (q * div);
        if (rem == 0) {
            return div;
        }
        int signum = ((int) ((p ^ q) >> 63)) | 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(rem == 0);
            case 2:
                increment = false;
                break;
            case 3:
                if (signum >= 0) {
                    increment = false;
                    break;
                } else {
                    increment = true;
                    break;
                }
            case 4:
                increment = true;
                break;
            case 5:
                if (signum <= 0) {
                    increment = false;
                    break;
                } else {
                    increment = true;
                    break;
                }
            case 6:
            case 7:
            case 8:
                long absRem = Math.abs(rem);
                long cmpRemToHalfDivisor = absRem - (Math.abs(q) - absRem);
                if (cmpRemToHalfDivisor != 0) {
                    if (cmpRemToHalfDivisor <= 0) {
                        increment = false;
                        break;
                    } else {
                        increment = true;
                        break;
                    }
                } else {
                    increment = (mode == RoundingMode.HALF_UP) | (((1 & div) != 0) & (mode == RoundingMode.HALF_EVEN));
                    break;
                }
            default:
                throw new AssertionError();
        }
        if (increment) {
            return div + ((long) signum);
        }
        return div;
    }

    @GwtIncompatible
    public static int mod(long x, int m) {
        return (int) mod(x, (long) m);
    }

    @GwtIncompatible
    public static long mod(long x, long m) {
        if (m <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        long result = x % m;
        return result >= 0 ? result : result + m;
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
            long minDeltaOrZero = delta & (delta >> 63);
            long a3 = (delta - minDeltaOrZero) - minDeltaOrZero;
            b2 += minDeltaOrZero;
            a2 = a3 >> Long.numberOfTrailingZeros(a3);
        }
        return a2 << Math.min(aTwos, bTwos);
    }

    @GwtIncompatible
    public static long checkedAdd(long a, long b) {
        boolean z;
        boolean z2 = true;
        long result = a + b;
        if ((a ^ b) < 0) {
            z = true;
        } else {
            z = false;
        }
        if ((a ^ result) < 0) {
            z2 = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedAdd", a, b);
        return result;
    }

    @GwtIncompatible
    public static long checkedSubtract(long a, long b) {
        boolean z;
        boolean z2 = true;
        long result = a - b;
        if ((a ^ b) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if ((a ^ result) < 0) {
            z2 = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedSubtract", a, b);
        return result;
    }

    public static long checkedMultiply(long a, long b) {
        int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(-1 ^ a) + Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(-1 ^ b);
        if (leadingZeros > 65) {
            return a * b;
        }
        MathPreconditions.checkNoOverflow(leadingZeros >= 64, "checkedMultiply", a, b);
        MathPreconditions.checkNoOverflow((b != Long.MIN_VALUE) | (a >= 0), "checkedMultiply", a, b);
        long result = a * b;
        MathPreconditions.checkNoOverflow(a == 0 || result / a == b, "checkedMultiply", a, b);
        return result;
    }

    @GwtIncompatible
    public static long checkedPow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (!(b <= 2) || !(b >= -2)) {
            long accum = 1;
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return checkedMultiply(accum, b);
                    default:
                        if ((k & 1) != 0) {
                            accum = checkedMultiply(accum, b);
                        }
                        k >>= 1;
                        if (k > 0) {
                            MathPreconditions.checkNoOverflow(-3037000499L <= b && b <= FLOOR_SQRT_MAX_LONG, "checkedPow", b, (long) k);
                            b *= b;
                        }
                        break;
                }
            }
        } else {
            switch ((int) b) {
                case -2:
                    MathPreconditions.checkNoOverflow(k < 64, "checkedPow", b, (long) k);
                    return (k & 1) == 0 ? 1 << k : -1 << k;
                case -1:
                    return (k & 1) == 0 ? 1 : -1;
                case 0:
                    if (k == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    MathPreconditions.checkNoOverflow(k < 63, "checkedPow", b, (long) k);
                    return 1 << k;
                default:
                    throw new AssertionError();
            }
        }
    }

    @Beta
    public static long saturatedAdd(long a, long b) {
        boolean z;
        boolean z2 = true;
        long naiveSum = a + b;
        if ((a ^ b) < 0) {
            z = true;
        } else {
            z = false;
        }
        if ((a ^ naiveSum) < 0) {
            z2 = false;
        }
        return z2 | z ? naiveSum : Clock.MAX_TIME + ((naiveSum >>> 63) ^ 1);
    }

    @Beta
    public static long saturatedSubtract(long a, long b) {
        boolean z;
        boolean z2 = true;
        long naiveDifference = a - b;
        if ((a ^ b) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if ((a ^ naiveDifference) < 0) {
            z2 = false;
        }
        return z2 | z ? naiveDifference : Clock.MAX_TIME + ((naiveDifference >>> 63) ^ 1);
    }

    @Beta
    public static long saturatedMultiply(long a, long b) {
        int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(-1 ^ a) + Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(-1 ^ b);
        if (leadingZeros > 65) {
            return a * b;
        }
        long j = Clock.MAX_TIME + ((a ^ b) >>> 63);
        if (((b == Long.MIN_VALUE) & (a < 0)) || (leadingZeros < 64)) {
            return j;
        }
        long result = a * b;
        return (a == 0 || result / a == b) ? result : j;
    }

    @Beta
    public static long saturatedPow(long b, int k) {
        boolean z;
        MathPreconditions.checkNonNegative("exponent", k);
        if ((b <= 2) && (b >= -2)) {
            switch ((int) b) {
                case -2:
                    if (k >= 64) {
                        return ((long) (k & 1)) + Clock.MAX_TIME;
                    }
                    return (k & 1) == 0 ? 1 << k : -1 << k;
                case -1:
                    if ((k & 1) != 0) {
                        return -1;
                    }
                    return 1;
                case 0:
                    if (k == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    if (k >= 63) {
                        return Clock.MAX_TIME;
                    }
                    return 1 << k;
                default:
                    throw new AssertionError();
            }
        } else {
            long accum = 1;
            long limit = Clock.MAX_TIME + ((b >>> 63) & ((long) (k & 1)));
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return saturatedMultiply(accum, b);
                    default:
                        if ((k & 1) != 0) {
                            accum = saturatedMultiply(accum, b);
                        }
                        k >>= 1;
                        if (k > 0) {
                            if (-3037000499L > b) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if ((b > FLOOR_SQRT_MAX_LONG) || z) {
                                return limit;
                            }
                            b *= b;
                        }
                }
            }
        }
    }

    @GwtIncompatible
    public static long factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        return n < factorials.length ? factorials[n] : Clock.MAX_TIME;
    }

    public static long binomial(int n, int k) {
        MathPreconditions.checkNonNegative("n", n);
        MathPreconditions.checkNonNegative("k", k);
        Preconditions.checkArgument(k <= n, "k (%s) > n (%s)", k, n);
        if (k > (n >> 1)) {
            k = n - k;
        }
        switch (k) {
            case 0:
                return 1;
            case 1:
                return (long) n;
            default:
                if (n < factorials.length) {
                    return factorials[n] / (factorials[k] * factorials[n - k]);
                }
                if (k >= biggestBinomials.length || n > biggestBinomials[k]) {
                    return Clock.MAX_TIME;
                }
                if (k >= biggestSimpleBinomials.length || n > biggestSimpleBinomials[k]) {
                    int nBits = log2((long) n, RoundingMode.CEILING);
                    long result = 1;
                    long numerator = (long) n;
                    long denominator = 1;
                    int numeratorBits = nBits;
                    int i = 2;
                    int n2 = n - 1;
                    while (i <= k) {
                        if (numeratorBits + nBits < 63) {
                            numerator *= (long) n2;
                            denominator *= (long) i;
                            numeratorBits += nBits;
                        } else {
                            result = multiplyFraction(result, numerator, denominator);
                            numerator = (long) n2;
                            denominator = (long) i;
                            numeratorBits = nBits;
                        }
                        i++;
                        n2--;
                    }
                    return multiplyFraction(result, numerator, denominator);
                }
                long result2 = (long) n;
                int n3 = n - 1;
                for (int i2 = 2; i2 <= k; i2++) {
                    result2 = (result2 * ((long) n3)) / ((long) i2);
                    n3--;
                }
                return result2;
        }
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

    @Beta
    @GwtIncompatible
    public static boolean isPrime(long n) {
        if (n < 2) {
            MathPreconditions.checkNonNegative("n", n);
            return false;
        } else if (n == 2 || n == 3 || n == 5 || n == 7 || n == 11 || n == 13) {
            return true;
        } else {
            if ((SIEVE_30 & (1 << ((int) (n % 30)))) != 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0) {
                return false;
            }
            if (n < 289) {
                return true;
            }
            long[][] jArr = millerRabinBaseSets;
            for (long[] baseSet : jArr) {
                if (n <= baseSet[0]) {
                    for (int i = 1; i < baseSet.length; i++) {
                        if (!MillerRabinTester.test(baseSet[i], n)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: private */
    public enum MillerRabinTester {
        SMALL {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long mulMod(long a, long b, long m) {
                return (a * b) % m;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long squareMod(long a, long m) {
                return (a * a) % m;
            }
        },
        LARGE {
            private long plusMod(long a, long b, long m) {
                return a >= m - b ? (a + b) - m : a + b;
            }

            private long times2ToThe32Mod(long a, long m) {
                int remainingPowersOf2 = 32;
                do {
                    int shift = Math.min(remainingPowersOf2, Long.numberOfLeadingZeros(a));
                    a = UnsignedLongs.remainder(a << shift, m);
                    remainingPowersOf2 -= shift;
                } while (remainingPowersOf2 > 0);
                return a;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long mulMod(long a, long b, long m) {
                long aHi = a >>> 32;
                long bHi = b >>> 32;
                long aLo = a & 4294967295L;
                long bLo = b & 4294967295L;
                long result = times2ToThe32Mod(aHi * bHi, m) + (aHi * bLo);
                if (result < 0) {
                    result = UnsignedLongs.remainder(result, m);
                }
                return plusMod(times2ToThe32Mod(result + (aLo * bHi), m), UnsignedLongs.remainder(aLo * bLo, m), m);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.math.LongMath.MillerRabinTester
            public long squareMod(long a, long m) {
                long aHi = a >>> 32;
                long aLo = a & 4294967295L;
                long result = times2ToThe32Mod(aHi * aHi, m);
                long hiLo = aHi * aLo * 2;
                if (hiLo < 0) {
                    hiLo = UnsignedLongs.remainder(hiLo, m);
                }
                return plusMod(times2ToThe32Mod(result + hiLo, m), UnsignedLongs.remainder(aLo * aLo, m), m);
            }
        };

        /* access modifiers changed from: package-private */
        public abstract long mulMod(long j, long j2, long j3);

        /* access modifiers changed from: package-private */
        public abstract long squareMod(long j, long j2);

        /* synthetic */ MillerRabinTester(AnonymousClass1 x2) {
            this();
        }

        static boolean test(long base, long n) {
            return (n <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(base, n);
        }

        private long powMod(long a, long p, long m) {
            long res = 1;
            while (p != 0) {
                if ((1 & p) != 0) {
                    res = mulMod(res, a, m);
                }
                a = squareMod(a, m);
                p >>= 1;
            }
            return res;
        }

        private boolean testWitness(long base, long n) {
            int r = Long.numberOfTrailingZeros(n - 1);
            long d = (n - 1) >> r;
            long base2 = base % n;
            if (base2 == 0) {
                return true;
            }
            long a = powMod(base2, d, n);
            if (a == 1) {
                return true;
            }
            int j = 0;
            while (a != n - 1) {
                j++;
                if (j == r) {
                    return false;
                }
                a = squareMod(a, n);
            }
            return true;
        }
    }

    private LongMath() {
    }
}
