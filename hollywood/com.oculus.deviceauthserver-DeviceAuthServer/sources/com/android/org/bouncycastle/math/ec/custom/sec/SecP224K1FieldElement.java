package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.raw.Mod;
import com.android.org.bouncycastle.math.raw.Nat224;
import com.android.org.bouncycastle.util.Arrays;
import java.math.BigInteger;

public class SecP224K1FieldElement extends ECFieldElement.AbstractFp {
    private static final int[] PRECOMP_POW2 = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};
    public static final BigInteger Q = SecP224K1Curve.q;
    protected int[] x;

    public SecP224K1FieldElement(BigInteger x2) {
        if (x2 == null || x2.signum() < 0 || x2.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.x = SecP224K1Field.fromBigInteger(x2);
    }

    public SecP224K1FieldElement() {
        this.x = Nat224.create();
    }

    protected SecP224K1FieldElement(int[] x2) {
        this.x = x2;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat224.isZero(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat224.isOne(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat224.getBit(this.x, 0) == 1;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.x);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP224K1Field";
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return Q.bitLength();
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.add(this.x, ((SecP224K1FieldElement) b).x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] z = Nat224.create();
        SecP224K1Field.addOne(this.x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.subtract(this.x, ((SecP224K1FieldElement) b).x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement b) {
        int[] z = Nat224.create();
        SecP224K1Field.multiply(this.x, ((SecP224K1FieldElement) b).x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement b) {
        int[] z = Nat224.create();
        Mod.invert(SecP224K1Field.P, ((SecP224K1FieldElement) b).x, z);
        SecP224K1Field.multiply(z, this.x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] z = Nat224.create();
        SecP224K1Field.negate(this.x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] z = Nat224.create();
        SecP224K1Field.square(this.x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] z = Nat224.create();
        Mod.invert(SecP224K1Field.P, this.x, z);
        return new SecP224K1FieldElement(z);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] x1 = this.x;
        if (Nat224.isZero(x1) || Nat224.isOne(x1)) {
            return this;
        }
        int[] x2 = Nat224.create();
        SecP224K1Field.square(x1, x2);
        SecP224K1Field.multiply(x2, x1, x2);
        SecP224K1Field.square(x2, x2);
        SecP224K1Field.multiply(x2, x1, x2);
        int[] x4 = Nat224.create();
        SecP224K1Field.square(x2, x4);
        SecP224K1Field.multiply(x4, x1, x4);
        int[] x8 = Nat224.create();
        SecP224K1Field.squareN(x4, 4, x8);
        SecP224K1Field.multiply(x8, x4, x8);
        int[] x11 = Nat224.create();
        SecP224K1Field.squareN(x8, 3, x11);
        SecP224K1Field.multiply(x11, x2, x11);
        SecP224K1Field.squareN(x11, 8, x11);
        SecP224K1Field.multiply(x11, x8, x11);
        SecP224K1Field.squareN(x11, 4, x8);
        SecP224K1Field.multiply(x8, x4, x8);
        SecP224K1Field.squareN(x8, 19, x4);
        SecP224K1Field.multiply(x4, x11, x4);
        int[] x84 = Nat224.create();
        SecP224K1Field.squareN(x4, 42, x84);
        SecP224K1Field.multiply(x84, x4, x84);
        SecP224K1Field.squareN(x84, 23, x4);
        SecP224K1Field.multiply(x4, x8, x4);
        SecP224K1Field.squareN(x4, 84, x8);
        SecP224K1Field.multiply(x8, x84, x8);
        SecP224K1Field.squareN(x8, 20, x8);
        SecP224K1Field.multiply(x8, x11, x8);
        SecP224K1Field.squareN(x8, 3, x8);
        SecP224K1Field.multiply(x8, x1, x8);
        SecP224K1Field.squareN(x8, 2, x8);
        SecP224K1Field.multiply(x8, x1, x8);
        SecP224K1Field.squareN(x8, 4, x8);
        SecP224K1Field.multiply(x8, x2, x8);
        SecP224K1Field.square(x8, x8);
        SecP224K1Field.square(x8, x84);
        if (Nat224.eq(x1, x84)) {
            return new SecP224K1FieldElement(x8);
        }
        SecP224K1Field.multiply(x8, PRECOMP_POW2, x8);
        SecP224K1Field.square(x8, x84);
        if (Nat224.eq(x1, x84)) {
            return new SecP224K1FieldElement(x8);
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SecP224K1FieldElement)) {
            return false;
        }
        return Nat224.eq(this.x, ((SecP224K1FieldElement) other).x);
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 7);
    }
}
