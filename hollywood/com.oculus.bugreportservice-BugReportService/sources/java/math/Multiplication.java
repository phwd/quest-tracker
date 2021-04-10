package java.math;

/* access modifiers changed from: package-private */
public class Multiplication {
    static final BigInteger[] bigFivePows = new BigInteger[32];
    static final BigInteger[] bigTenPows = new BigInteger[32];
    static final int[] fivePows = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    static final int[] tenPows = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    static {
        long j = 1;
        int i = 0;
        while (i <= 18) {
            bigFivePows[i] = BigInteger.valueOf(j);
            bigTenPows[i] = BigInteger.valueOf(j << i);
            j *= 5;
            i++;
        }
        while (i < bigTenPows.length) {
            BigInteger[] bigIntegerArr = bigFivePows;
            int i2 = i - 1;
            bigIntegerArr[i] = bigIntegerArr[i2].multiply(bigIntegerArr[1]);
            BigInteger[] bigIntegerArr2 = bigTenPows;
            bigIntegerArr2[i] = bigIntegerArr2[i2].multiply(BigInteger.TEN);
            i++;
        }
    }

    static BigInteger multiplyByPositiveInt(BigInteger bigInteger, int i) {
        BigInt copy = bigInteger.getBigInt().copy();
        copy.multiplyByPositiveInt(i);
        return new BigInteger(copy);
    }

    static BigInteger multiplyByTenPow(BigInteger bigInteger, long j) {
        int[] iArr = tenPows;
        if (j < ((long) iArr.length)) {
            return multiplyByPositiveInt(bigInteger, iArr[(int) j]);
        }
        return bigInteger.multiply(powerOf10(j));
    }

    static BigInteger powerOf10(long j) {
        int i = (int) j;
        BigInteger[] bigIntegerArr = bigTenPows;
        if (j < ((long) bigIntegerArr.length)) {
            return bigIntegerArr[i];
        }
        if (j <= 50) {
            return BigInteger.TEN.pow(i);
        }
        if (j <= 2147483647L) {
            try {
                return bigFivePows[1].pow(i).shiftLeft(i);
            } catch (OutOfMemoryError e) {
                throw new ArithmeticException(e.getMessage());
            }
        } else {
            BigInteger pow = bigFivePows[1].pow(Integer.MAX_VALUE);
            long j2 = j - 2147483647L;
            int i2 = (int) (j % 2147483647L);
            BigInteger bigInteger = pow;
            for (long j3 = j2; j3 > 2147483647L; j3 -= 2147483647L) {
                bigInteger = bigInteger.multiply(pow);
            }
            BigInteger shiftLeft = bigInteger.multiply(bigFivePows[1].pow(i2)).shiftLeft(Integer.MAX_VALUE);
            while (j2 > 2147483647L) {
                shiftLeft = shiftLeft.shiftLeft(Integer.MAX_VALUE);
                j2 -= 2147483647L;
            }
            return shiftLeft.shiftLeft(i2);
        }
    }

    static BigInteger multiplyByFivePow(BigInteger bigInteger, int i) {
        int[] iArr = fivePows;
        if (i < iArr.length) {
            return multiplyByPositiveInt(bigInteger, iArr[i]);
        }
        BigInteger[] bigIntegerArr = bigFivePows;
        if (i < bigIntegerArr.length) {
            return bigInteger.multiply(bigIntegerArr[i]);
        }
        return bigInteger.multiply(bigIntegerArr[1].pow(i));
    }
}
