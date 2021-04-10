package com.android.org.bouncycastle.math.ec;

import java.math.BigInteger;

/* access modifiers changed from: package-private */
public class SimpleBigDecimal {
    private static final long serialVersionUID = 1;
    private final BigInteger bigInt;
    private final int scale;

    public static SimpleBigDecimal getInstance(BigInteger value, int scale2) {
        return new SimpleBigDecimal(value.shiftLeft(scale2), scale2);
    }

    public SimpleBigDecimal(BigInteger bigInt2, int scale2) {
        if (scale2 >= 0) {
            this.bigInt = bigInt2;
            this.scale = scale2;
            return;
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    private void checkScale(SimpleBigDecimal b) {
        if (this.scale != b.scale) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    public SimpleBigDecimal adjustScale(int newScale) {
        if (newScale >= 0) {
            int i = this.scale;
            if (newScale == i) {
                return this;
            }
            return new SimpleBigDecimal(this.bigInt.shiftLeft(newScale - i), newScale);
        }
        throw new IllegalArgumentException("scale may not be negative");
    }

    public SimpleBigDecimal add(SimpleBigDecimal b) {
        checkScale(b);
        return new SimpleBigDecimal(this.bigInt.add(b.bigInt), this.scale);
    }

    public SimpleBigDecimal add(BigInteger b) {
        return new SimpleBigDecimal(this.bigInt.add(b.shiftLeft(this.scale)), this.scale);
    }

    public SimpleBigDecimal negate() {
        return new SimpleBigDecimal(this.bigInt.negate(), this.scale);
    }

    public SimpleBigDecimal subtract(SimpleBigDecimal b) {
        return add(b.negate());
    }

    public SimpleBigDecimal subtract(BigInteger b) {
        return new SimpleBigDecimal(this.bigInt.subtract(b.shiftLeft(this.scale)), this.scale);
    }

    public SimpleBigDecimal multiply(SimpleBigDecimal b) {
        checkScale(b);
        BigInteger multiply = this.bigInt.multiply(b.bigInt);
        int i = this.scale;
        return new SimpleBigDecimal(multiply, i + i);
    }

    public SimpleBigDecimal multiply(BigInteger b) {
        return new SimpleBigDecimal(this.bigInt.multiply(b), this.scale);
    }

    public SimpleBigDecimal divide(SimpleBigDecimal b) {
        checkScale(b);
        return new SimpleBigDecimal(this.bigInt.shiftLeft(this.scale).divide(b.bigInt), this.scale);
    }

    public SimpleBigDecimal divide(BigInteger b) {
        return new SimpleBigDecimal(this.bigInt.divide(b), this.scale);
    }

    public SimpleBigDecimal shiftLeft(int n) {
        return new SimpleBigDecimal(this.bigInt.shiftLeft(n), this.scale);
    }

    public int compareTo(SimpleBigDecimal val) {
        checkScale(val);
        return this.bigInt.compareTo(val.bigInt);
    }

    public int compareTo(BigInteger val) {
        return this.bigInt.compareTo(val.shiftLeft(this.scale));
    }

    public BigInteger floor() {
        return this.bigInt.shiftRight(this.scale);
    }

    public BigInteger round() {
        return add(new SimpleBigDecimal(ECConstants.ONE, 1).adjustScale(this.scale)).floor();
    }

    public int intValue() {
        return floor().intValue();
    }

    public long longValue() {
        return floor().longValue();
    }

    public int getScale() {
        return this.scale;
    }

    public String toString() {
        if (this.scale == 0) {
            return this.bigInt.toString();
        }
        BigInteger floorBigInt = floor();
        BigInteger fract = this.bigInt.subtract(floorBigInt.shiftLeft(this.scale));
        if (this.bigInt.signum() == -1) {
            fract = ECConstants.ONE.shiftLeft(this.scale).subtract(fract);
        }
        if (floorBigInt.signum() == -1 && !fract.equals(ECConstants.ZERO)) {
            floorBigInt = floorBigInt.add(ECConstants.ONE);
        }
        String leftOfPoint = floorBigInt.toString();
        char[] fractCharArr = new char[this.scale];
        String fractStr = fract.toString(2);
        int fractLen = fractStr.length();
        int zeroes = this.scale - fractLen;
        for (int i = 0; i < zeroes; i++) {
            fractCharArr[i] = '0';
        }
        for (int j = 0; j < fractLen; j++) {
            fractCharArr[zeroes + j] = fractStr.charAt(j);
        }
        String rightOfPoint = new String(fractCharArr);
        StringBuffer sb = new StringBuffer(leftOfPoint);
        sb.append(".");
        sb.append(rightOfPoint);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleBigDecimal)) {
            return false;
        }
        SimpleBigDecimal other = (SimpleBigDecimal) o;
        if (!this.bigInt.equals(other.bigInt) || this.scale != other.scale) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.bigInt.hashCode() ^ this.scale;
    }
}
