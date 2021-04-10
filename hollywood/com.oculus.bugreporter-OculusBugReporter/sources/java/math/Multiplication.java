package java.math;

import dalvik.system.VMRuntime;

/* access modifiers changed from: package-private */
public class Multiplication {
    static final BigInteger[] bigFivePows = new BigInteger[32];
    static final BigInteger[] bigTenPows = new BigInteger[32];
    static final int[] fivePows = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    static final int[] tenPows = {1, 10, 100, 1000, VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, 100000, 1000000, 10000000, 100000000, 1000000000};

    private Multiplication() {
    }

    static {
        long fivePow = 1;
        int i = 0;
        while (i <= 18) {
            bigFivePows[i] = BigInteger.valueOf(fivePow);
            bigTenPows[i] = BigInteger.valueOf(fivePow << i);
            fivePow *= 5;
            i++;
        }
        while (i < bigTenPows.length) {
            BigInteger[] bigIntegerArr = bigFivePows;
            bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(bigIntegerArr[1]);
            BigInteger[] bigIntegerArr2 = bigTenPows;
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(BigInteger.TEN);
            i++;
        }
    }

    static BigInteger multiplyByPositiveInt(BigInteger val, int factor) {
        BigInt bi = val.getBigInt().copy();
        bi.multiplyByPositiveInt(factor);
        return new BigInteger(bi);
    }

    static BigInteger multiplyByTenPow(BigInteger val, long exp) {
        int[] iArr = tenPows;
        if (exp < ((long) iArr.length)) {
            return multiplyByPositiveInt(val, iArr[(int) exp]);
        }
        return val.multiply(powerOf10(exp));
    }

    static BigInteger powerOf10(long exp) {
        int intExp = (int) exp;
        BigInteger[] bigIntegerArr = bigTenPows;
        if (exp < ((long) bigIntegerArr.length)) {
            return bigIntegerArr[intExp];
        }
        if (exp <= 50) {
            return BigInteger.TEN.pow(intExp);
        }
        if (exp <= 2147483647L) {
            try {
                return bigFivePows[1].pow(intExp).shiftLeft(intExp);
            } catch (OutOfMemoryError error) {
                throw new ArithmeticException(error.getMessage());
            }
        } else {
            BigInteger powerOfFive = bigFivePows[1].pow(Integer.MAX_VALUE);
            BigInteger res = powerOfFive;
            int intExp2 = (int) (exp % 2147483647L);
            for (long longExp = exp - 2147483647L; longExp > 2147483647L; longExp -= 2147483647L) {
                res = res.multiply(powerOfFive);
            }
            BigInteger res2 = res.multiply(bigFivePows[1].pow(intExp2)).shiftLeft(Integer.MAX_VALUE);
            for (long longExp2 = exp - 2147483647L; longExp2 > 2147483647L; longExp2 -= 2147483647L) {
                res2 = res2.shiftLeft(Integer.MAX_VALUE);
            }
            return res2.shiftLeft(intExp2);
        }
    }

    static BigInteger multiplyByFivePow(BigInteger val, int exp) {
        int[] iArr = fivePows;
        if (exp < iArr.length) {
            return multiplyByPositiveInt(val, iArr[exp]);
        }
        BigInteger[] bigIntegerArr = bigFivePows;
        if (exp < bigIntegerArr.length) {
            return val.multiply(bigIntegerArr[exp]);
        }
        return val.multiply(bigIntegerArr[1].pow(exp));
    }
}
