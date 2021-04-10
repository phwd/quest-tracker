package com.android.org.bouncycastle.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class BigIntegers {
    private static final int MAX_ITERATIONS = 1000;
    public static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger ZERO = BigInteger.valueOf(0);

    public static byte[] asUnsignedByteArray(BigInteger value) {
        byte[] bytes = value.toByteArray();
        if (bytes[0] != 0) {
            return bytes;
        }
        byte[] tmp = new byte[(bytes.length - 1)];
        System.arraycopy(bytes, 1, tmp, 0, tmp.length);
        return tmp;
    }

    public static byte[] asUnsignedByteArray(int length, BigInteger value) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == length) {
            return bytes;
        }
        int start = 0;
        if (bytes[0] == 0) {
            start = 1;
        }
        int count = bytes.length - start;
        if (count <= length) {
            byte[] tmp = new byte[length];
            System.arraycopy(bytes, start, tmp, tmp.length - count, count);
            return tmp;
        }
        throw new IllegalArgumentException("standard length exceeded for value");
    }

    public static BigInteger createRandomInRange(BigInteger min, BigInteger max, SecureRandom random) {
        int cmp = min.compareTo(max);
        if (cmp >= 0) {
            if (cmp <= 0) {
                return min;
            }
            throw new IllegalArgumentException("'min' may not be greater than 'max'");
        } else if (min.bitLength() > max.bitLength() / 2) {
            return createRandomInRange(ZERO, max.subtract(min), random).add(min);
        } else {
            for (int i = 0; i < 1000; i++) {
                BigInteger x = createRandomBigInteger(max.bitLength(), random);
                if (x.compareTo(min) >= 0 && x.compareTo(max) <= 0) {
                    return x;
                }
            }
            return createRandomBigInteger(max.subtract(min).bitLength() - 1, random).add(min);
        }
    }

    public static BigInteger fromUnsignedByteArray(byte[] buf) {
        return new BigInteger(1, buf);
    }

    public static BigInteger fromUnsignedByteArray(byte[] buf, int off, int length) {
        byte[] mag = buf;
        if (!(off == 0 && length == buf.length)) {
            mag = new byte[length];
            System.arraycopy(buf, off, mag, 0, length);
        }
        return new BigInteger(1, mag);
    }

    public static int getUnsignedByteLength(BigInteger n) {
        return (n.bitLength() + 7) / 8;
    }

    public static BigInteger createRandomBigInteger(int bitLength, SecureRandom random) {
        return new BigInteger(1, createRandom(bitLength, random));
    }

    public static BigInteger createRandomPrime(int bitLength, int certainty, SecureRandom random) {
        BigInteger rv;
        if (bitLength < 2) {
            throw new IllegalArgumentException("bitLength < 2");
        } else if (bitLength == 2) {
            return random.nextInt() < 0 ? TWO : THREE;
        } else {
            do {
                byte[] base = createRandom(bitLength, random);
                base[0] = (byte) (base[0] | ((byte) (1 << (7 - ((base.length * 8) - bitLength)))));
                int length = base.length - 1;
                base[length] = (byte) (base[length] | 1);
                rv = new BigInteger(1, base);
            } while (!rv.isProbablePrime(certainty));
            return rv;
        }
    }

    private static byte[] createRandom(int bitLength, SecureRandom random) throws IllegalArgumentException {
        if (bitLength >= 1) {
            int nBytes = (bitLength + 7) / 8;
            byte[] rv = new byte[nBytes];
            random.nextBytes(rv);
            rv[0] = (byte) (rv[0] & ((byte) (255 >>> ((nBytes * 8) - bitLength))));
            return rv;
        }
        throw new IllegalArgumentException("bitLength must be at least 1");
    }
}
