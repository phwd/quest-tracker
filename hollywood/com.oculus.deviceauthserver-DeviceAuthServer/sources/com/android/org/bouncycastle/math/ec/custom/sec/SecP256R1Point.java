package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.math.raw.Nat256;

public class SecP256R1Point extends ECPoint.AbstractFp {
    public SecP256R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y) {
        this(curve, x, y, false);
    }

    public SecP256R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
        super(curve, x, y);
        if ((x == null) == (y != null ? false : true)) {
            this.withCompression = withCompression;
            return;
        }
        throw new IllegalArgumentException("Exactly one of the field elements is null");
    }

    SecP256R1Point(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        super(curve, x, y, zs);
        this.withCompression = withCompression;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint detach() {
        return new SecP256R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint add(ECPoint b) {
        int[] U2;
        int[] S2;
        int[] U1;
        int[] U12;
        SecP256R1FieldElement Y3;
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
        SecP256R1FieldElement X1 = (SecP256R1FieldElement) this.x;
        SecP256R1FieldElement Y1 = (SecP256R1FieldElement) this.y;
        SecP256R1FieldElement X2 = (SecP256R1FieldElement) b.getXCoord();
        SecP256R1FieldElement Y2 = (SecP256R1FieldElement) b.getYCoord();
        SecP256R1FieldElement Z1 = (SecP256R1FieldElement) this.zs[0];
        SecP256R1FieldElement Z2 = (SecP256R1FieldElement) b.getZCoord(0);
        int[] tt1 = Nat256.createExt();
        int[] t2 = Nat256.create();
        int[] t3 = Nat256.create();
        int[] t4 = Nat256.create();
        boolean Z1IsOne = Z1.isOne();
        if (Z1IsOne) {
            int[] U22 = X2.x;
            S2 = Y2.x;
            U2 = U22;
        } else {
            S2 = t3;
            SecP256R1Field.square(Z1.x, S2);
            SecP256R1Field.multiply(S2, X2.x, t2);
            SecP256R1Field.multiply(S2, Z1.x, S2);
            SecP256R1Field.multiply(S2, Y2.x, S2);
            U2 = t2;
        }
        boolean Z2IsOne = Z2.isOne();
        if (Z2IsOne) {
            U1 = X1.x;
            U12 = Y1.x;
        } else {
            SecP256R1Field.square(Z2.x, t4);
            SecP256R1Field.multiply(t4, X1.x, tt1);
            SecP256R1Field.multiply(t4, Z2.x, t4);
            SecP256R1Field.multiply(t4, Y1.x, t4);
            U1 = tt1;
            U12 = t4;
        }
        int[] H = Nat256.create();
        SecP256R1Field.subtract(U1, U2, H);
        SecP256R1Field.subtract(U12, S2, t2);
        if (!Nat256.isZero(H)) {
            SecP256R1Field.square(H, t3);
            int[] G = Nat256.create();
            SecP256R1Field.multiply(t3, H, G);
            SecP256R1Field.multiply(t3, U1, t3);
            SecP256R1Field.negate(G, G);
            Nat256.mul(U12, G, tt1);
            SecP256R1Field.reduce32(Nat256.addBothTo(t3, t3, G), G);
            SecP256R1FieldElement X3 = new SecP256R1FieldElement(t4);
            SecP256R1Field.square(t2, X3.x);
            SecP256R1Field.subtract(X3.x, G, X3.x);
            SecP256R1FieldElement Y32 = new SecP256R1FieldElement(G);
            SecP256R1Field.subtract(t3, X3.x, Y32.x);
            SecP256R1Field.multiplyAddToExt(Y32.x, t2, tt1);
            SecP256R1Field.reduce(tt1, Y32.x);
            SecP256R1FieldElement Z3 = new SecP256R1FieldElement(H);
            if (!Z1IsOne) {
                Y3 = Y32;
                SecP256R1Field.multiply(Z3.x, Z1.x, Z3.x);
            } else {
                Y3 = Y32;
            }
            if (!Z2IsOne) {
                SecP256R1Field.multiply(Z3.x, Z2.x, Z3.x);
            }
            return new SecP256R1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
        } else if (Nat256.isZero(t2)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    @Override // com.android.org.bouncycastle.math.ec.ECPoint
    public ECPoint twice() {
        int[] Z1Squared;
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP256R1FieldElement Y1 = (SecP256R1FieldElement) this.y;
        if (Y1.isZero()) {
            return curve.getInfinity();
        }
        SecP256R1FieldElement X1 = (SecP256R1FieldElement) this.x;
        SecP256R1FieldElement Z1 = (SecP256R1FieldElement) this.zs[0];
        int[] t1 = Nat256.create();
        int[] t2 = Nat256.create();
        int[] Y1Squared = Nat256.create();
        SecP256R1Field.square(Y1.x, Y1Squared);
        int[] T = Nat256.create();
        SecP256R1Field.square(Y1Squared, T);
        boolean Z1IsOne = Z1.isOne();
        int[] Z1Squared2 = Z1.x;
        if (!Z1IsOne) {
            SecP256R1Field.square(Z1.x, t2);
            Z1Squared = t2;
        } else {
            Z1Squared = Z1Squared2;
        }
        SecP256R1Field.subtract(X1.x, Z1Squared, t1);
        SecP256R1Field.add(X1.x, Z1Squared, t2);
        SecP256R1Field.multiply(t2, t1, t2);
        SecP256R1Field.reduce32(Nat256.addBothTo(t2, t2, t2), t2);
        SecP256R1Field.multiply(Y1Squared, X1.x, Y1Squared);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, Y1Squared, 2, 0), Y1Squared);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, T, 3, 0, t1), t1);
        SecP256R1FieldElement X3 = new SecP256R1FieldElement(T);
        SecP256R1Field.square(t2, X3.x);
        SecP256R1Field.subtract(X3.x, Y1Squared, X3.x);
        SecP256R1Field.subtract(X3.x, Y1Squared, X3.x);
        SecP256R1FieldElement Y3 = new SecP256R1FieldElement(Y1Squared);
        SecP256R1Field.subtract(Y1Squared, X3.x, Y3.x);
        SecP256R1Field.multiply(Y3.x, t2, Y3.x);
        SecP256R1Field.subtract(Y3.x, t1, Y3.x);
        SecP256R1FieldElement Z3 = new SecP256R1FieldElement(t2);
        SecP256R1Field.twice(Y1.x, Z3.x);
        if (!Z1IsOne) {
            SecP256R1Field.multiply(Z3.x, Z1.x, Z3.x);
        }
        return new SecP256R1Point(curve, X3, Y3, new ECFieldElement[]{Z3}, this.withCompression);
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
        return new SecP256R1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}
