package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.math.raw.Nat192;

public class SecP192K1Point extends ECPoint.AbstractFp {
    public SecP192K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP192K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
        super(curve, x, y);
        if ((x == null) == (y != null ? false : true)) {
            this.withCompression = withCompression;
            return;
        }
        throw new IllegalArgumentException("Exactly one of the field elements is null");
    }

    SecP192K1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecP192K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint add(ECPoint b) {
        int[] U2;
        int[] S2;
        int[] U1;
        int[] U12;
        SecP192K1FieldElement Y3;
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return this;
        }
        if (this == b) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement X1 = (SecP192K1FieldElement) this.x;
        SecP192K1FieldElement Y1 = (SecP192K1FieldElement) this.y;
        SecP192K1FieldElement X2 = (SecP192K1FieldElement) b.getXCoord();
        SecP192K1FieldElement Y2 = (SecP192K1FieldElement) b.getYCoord();
        SecP192K1FieldElement Z1 = (SecP192K1FieldElement) this.zs[0];
        SecP192K1FieldElement Z2 = (SecP192K1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat192.createExt();
        int[] t2 = Nat192.create();
        int[] t3 = Nat192.create();
        int[] t4 = Nat192.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            int[] U22 = X2.x;
            S2 = Y2.x;
            U2 = U22;
        } else {
            S2 = t3;
            SecP192K1Field.square(Z1.x, S2);
            SecP192K1Field.multiply(S2, X2.x, t2);
            SecP192K1Field.multiply(S2, Z1.x, S2);
            SecP192K1Field.multiply(S2, Y2.x, S2);
            U2 = t2;
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.x;
            U12 = Y1.x;
        } else {
            SecP192K1Field.square(Z2.x, t4);
            SecP192K1Field.multiply(t4, X1.x, tt1);
            SecP192K1Field.multiply(t4, Z2.x, t4);
            SecP192K1Field.multiply(t4, Y1.x, t4);
            U1 = tt1;
            U12 = t4;
        }
        int[] H = Nat192.create();
        SecP192K1Field.subtract(U1, U2, H);
        SecP192K1Field.subtract(U12, S2, t2);
        if (!Nat192.isZero(H)) {
            SecP192K1Field.square(H, t3);
            int[] G = Nat192.create();
            SecP192K1Field.multiply(t3, H, G);
            SecP192K1Field.multiply(t3, U1, t3);
            SecP192K1Field.negate(G, G);
            Nat192.mul(U12, G, tt1);
            SecP192K1Field.reduce32(Nat192.addBothTo(t3, t3, G), G);
            SecP192K1FieldElement X3 = new SecP192K1FieldElement(t4);
            SecP192K1Field.square(t2, X3.x);
            SecP192K1Field.subtract(X3.x, G, X3.x);
            SecP192K1FieldElement Y32 = new SecP192K1FieldElement(G);
            SecP192K1Field.subtract(t3, X3.x, Y32.x);
            SecP192K1Field.multiplyAddToExt(Y32.x, t2, tt1);
            SecP192K1Field.reduce(tt1, Y32.x);
            SecP192K1FieldElement Z3 = new SecP192K1FieldElement(H);
            if (!Z1IsOne) {
                Y3 = Y32;
                SecP192K1Field.multiply(Z3.x, Z1.x, Z3.x);
            } else {
                Y3 = Y32;
            }
            if (!Z2IsOne) {
                SecP192K1Field.multiply(Z3.x, Z2.x, Z3.x);
            }
            return new SecP192K1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
        } else if (Nat192.isZero(t2)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint twice() {
        SecP192K1FieldElement Y3;
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement Y1 = (SecP192K1FieldElement) this.y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP192K1FieldElement X1 = (SecP192K1FieldElement) this.x;
        SecP192K1FieldElement Z1 = (SecP192K1FieldElement) this.zs[0];
        int[] Y1Squared = Nat192.create();
        SecP192K1Field.square(Y1.x, Y1Squared);
        int[] T = Nat192.create();
        SecP192K1Field.square(Y1Squared, T);
        int[] M = Nat192.create();
        SecP192K1Field.square(X1.x, M);
        SecP192K1Field.reduce32(Nat192.addBothTo(M, M, M), M);
        SecP192K1Field.multiply(Y1Squared, X1.x, Y1Squared);
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, Y1Squared, 2, 0), Y1Squared);
        int[] t1 = Nat192.create();
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, T, 3, 0, t1), t1);
        SecP192K1FieldElement X3 = new SecP192K1FieldElement(T);
        SecP192K1Field.square(M, X3.x);
        SecP192K1Field.subtract(X3.x, Y1Squared, X3.x);
        SecP192K1Field.subtract(X3.x, Y1Squared, X3.x);
        SecP192K1FieldElement Y32 = new SecP192K1FieldElement(Y1Squared);
        SecP192K1Field.subtract(Y1Squared, X3.x, Y32.x);
        SecP192K1Field.multiply(Y32.x, M, Y32.x);
        SecP192K1Field.subtract(Y32.x, t1, Y32.x);
        SecP192K1FieldElement Z3 = new SecP192K1FieldElement(M);
        SecP192K1Field.twice(Y1.x, Z3.x);
        if (!Z1.isOne()) {
            Y3 = Y32;
            SecP192K1Field.multiply(Z3.x, Z1.x, Z3.x);
        } else {
            Y3 = Y32;
        }
        return new SecP192K1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint b) {
        if (this == b) {
            return threeTimes();
        }
        if (isInfinity()) {
            return b;
        }
        if (b.isInfinity()) {
            return twice();
        }
        if (this.y.isZero()) {
            return b;
        }
        return twice().add(b);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        if (isInfinity() || this.y.isZero()) {
            return this;
        }
        return twice().add(this);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        return new SecP192K1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}
