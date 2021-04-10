package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.raw.Mod;
import com.android.org.bouncycastle.math.raw.Nat192;
import com.android.org.bouncycastle.util.Arrays;
import java.math.BigInteger;

public class SecP192K1FieldElement extends ECFieldElement.AbstractFp {
    public static final BigInteger Q = SecP192K1Curve.q;
    protected int[] x;

    public SecP192K1FieldElement(BigInteger x2) {
        if (x2 == null || x2.signum() < 0 || x2.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.x = SecP192K1Field.fromBigInteger(x2);
    }

    public SecP192K1FieldElement() {
        this.x = Nat192.create();
    }

    protected SecP192K1FieldElement(int[] x2) {
        this.x = x2;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat192.isZero(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat192.isOne(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat192.getBit(this.x, 0) == 1;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP192K1Field";
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return Q.bitLength();
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.add(this.x, ((SecP192K1FieldElement) b).x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] z = Nat192.create();
        SecP192K1Field.addOne(this.x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.subtract(this.x, ((SecP192K1FieldElement) b).x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat192.create();
        SecP192K1Field.multiply(this.x, ((SecP192K1FieldElement) b).x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat192.create();
        Mod.invert(SecP192K1Field.P, ((SecP192K1FieldElement) b).x, z);
        SecP192K1Field.multiply(z, this.x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] z = Nat192.create();
        SecP192K1Field.negate(this.x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] z = Nat192.create();
        SecP192K1Field.square(this.x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] z = Nat192.create();
        Mod.invert(SecP192K1Field.P, this.x, z);
        return new SecP192K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] x1 = this.x;
        if (Nat192.isZero(x1) || Nat192.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat192.create();
        SecP192K1Field.square(x1, x2);
        SecP192K1Field.multiply(x2, x1, x2);
        int[] x3 = Nat192.create();
        SecP192K1Field.square(x2, x3);
        SecP192K1Field.multiply(x3, x1, x3);
        int[] x6 = Nat192.create();
        SecP192K1Field.squareN(x3, 3, x6);
        SecP192K1Field.multiply(x6, x3, x6);
        SecP192K1Field.squareN(x6, 2, x6);
        SecP192K1Field.multiply(x6, x2, x6);
        SecP192K1Field.squareN(x6, 8, x2);
        SecP192K1Field.multiply(x2, x6, x2);
        SecP192K1Field.squareN(x2, 3, x6);
        SecP192K1Field.multiply(x6, x3, x6);
        int[] x35 = Nat192.create();
        SecP192K1Field.squareN(x6, 16, x35);
        SecP192K1Field.multiply(x35, x2, x35);
        SecP192K1Field.squareN(x35, 35, x2);
        SecP192K1Field.multiply(x2, x35, x2);
        SecP192K1Field.squareN(x2, 70, x35);
        SecP192K1Field.multiply(x35, x2, x35);
        SecP192K1Field.squareN(x35, 19, x2);
        SecP192K1Field.multiply(x2, x6, x2);
        SecP192K1Field.squareN(x2, 20, x2);
        SecP192K1Field.multiply(x2, x6, x2);
        SecP192K1Field.squareN(x2, 4, x2);
        SecP192K1Field.multiply(x2, x3, x2);
        SecP192K1Field.squareN(x2, 6, x2);
        SecP192K1Field.multiply(x2, x3, x2);
        SecP192K1Field.square(x2, x2);
        SecP192K1Field.square(x2, x3);
        if (Nat192.eq(x1, x3)) {
            return new SecP192K1FieldElement(x2);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP192K1FieldElement)) {
            return false;
        }
        return Nat192.eq(this.x, ((SecP192K1FieldElement) other).x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 6);
    }
}
