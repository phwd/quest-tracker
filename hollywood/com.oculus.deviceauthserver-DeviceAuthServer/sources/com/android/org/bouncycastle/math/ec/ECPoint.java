package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import java.math.BigInteger;
import java.util.Hashtable;

public abstract class ECPoint {
    protected static final ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;
    protected ECFieldElement x;
    protected ECFieldElement y;
    protected ECFieldElement[] zs;

    public abstract ECPoint add(ECPoint eCPoint);

    /* access modifiers changed from: protected */
    public abstract ECPoint detach();

    /* access modifiers changed from: protected */
    public abstract boolean getCompressionYTilde();

    public abstract ECPoint negate();

    /* access modifiers changed from: protected */
    public abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve curve2) {
        int coord = curve2 == null ? 0 : curve2.getCoordinateSystem();
        if (coord == 0 || coord == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement one = curve2.fromBigInteger(ECConstants.ONE);
        if (!(coord == 1 || coord == 2)) {
            if (coord == 3) {
                return new ECFieldElement[]{one, one, one};
            } else if (coord == 4) {
                return new ECFieldElement[]{one, curve2.getA()};
            } else if (coord != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{one};
    }

    protected ECPoint(ECCurve curve2, ECFieldElement x2, ECFieldElement y2) {
        this(curve2, x2, y2, getInitialZCoords(curve2));
    }

    protected ECPoint(ECCurve curve2, ECFieldElement x2, ECFieldElement y2, ECFieldElement[] zs2) {
        this.preCompTable = null;
        this.curve = curve2;
        this.x = x2;
        this.y = y2;
        this.zs = zs2;
    }

    /* access modifiers changed from: protected */
    public boolean satisfiesOrder() {
        BigInteger n;
        if (!ECConstants.ONE.equals(this.curve.getCofactor()) && (n = this.curve.getOrder()) != null && !ECAlgorithms.referenceMultiply(this, n).isInfinity()) {
            return false;
        }
        return true;
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    /* access modifiers changed from: protected */
    public int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.x;
    }

    public ECFieldElement getYCoord() {
        return this.y;
    }

    public ECFieldElement getZCoord(int index) {
        if (index >= 0) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (index < eCFieldElementArr.length) {
                return eCFieldElementArr[index];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.zs;
        int zsLen = eCFieldElementArr.length;
        if (zsLen == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] copy = new ECFieldElement[zsLen];
        System.arraycopy(eCFieldElementArr, 0, copy, 0, zsLen);
        return copy;
    }

    public final ECFieldElement getRawXCoord() {
        return this.x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.y;
    }

    /* access modifiers changed from: protected */
    public final ECFieldElement[] getRawZCoords() {
        return this.zs;
    }

    /* access modifiers changed from: protected */
    public void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int coord = getCurveCoordinateSystem();
        if (coord == 0 || coord == 5 || isInfinity() || this.zs[0].isOne()) {
            return true;
        }
        return false;
    }

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement Z1 = getZCoord(0);
        if (Z1.isOne()) {
            return this;
        }
        return normalize(Z1.invert());
    }

    /* access modifiers changed from: package-private */
    public ECPoint normalize(ECFieldElement zInv) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement zInv2 = zInv.square();
                return createScaledPoint(zInv2, zInv2.multiply(zInv));
            } else if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(zInv, zInv);
    }

    /* access modifiers changed from: protected */
    public ECPoint createScaledPoint(ECFieldElement sx, ECFieldElement sy) {
        return getCurve().createRawPoint(getRawXCoord().multiply(sx), getRawYCoord().multiply(sy), this.withCompression);
    }

    public boolean isInfinity() {
        if (!(this.x == null || this.y == null)) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        return implIsValid(false, true);
    }

    /* access modifiers changed from: package-private */
    public boolean isValidPartial() {
        return implIsValid(false, false);
    }

    /* access modifiers changed from: package-private */
    public boolean implIsValid(final boolean decompressed, final boolean checkOrder) {
        if (isInfinity()) {
            return true;
        }
        return true ^ ((ValidityPrecompInfo) getCurve().precompute(this, "bc_validity", new PreCompCallback() {
            /* class com.android.org.bouncycastle.math.ec.ECPoint.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo existing) {
                ValidityPrecompInfo info = existing instanceof ValidityPrecompInfo ? (ValidityPrecompInfo) existing : null;
                if (info == null) {
                    info = new ValidityPrecompInfo();
                }
                if (info.hasFailed()) {
                    return info;
                }
                if (!info.hasCurveEquationPassed()) {
                    if (decompressed || ECPoint.this.satisfiesCurveEquation()) {
                        info.reportCurveEquationPassed();
                    } else {
                        info.reportFailed();
                        return info;
                    }
                }
                if (checkOrder && !info.hasOrderPassed()) {
                    if (!ECPoint.this.satisfiesOrder()) {
                        info.reportFailed();
                        return info;
                    }
                    info.reportOrderPassed();
                }
                return info;
            }
        })).hasFailed();
    }

    public ECPoint scaleX(ECFieldElement scale) {
        if (isInfinity()) {
            return this;
        }
        return getCurve().createRawPoint(getRawXCoord().multiply(scale), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement scale) {
        if (isInfinity()) {
            return this;
        }
        return getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(scale), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint other) {
        if (other == null) {
            return false;
        }
        ECCurve c1 = getCurve();
        ECCurve c2 = other.getCurve();
        boolean n1 = c1 == null;
        boolean n2 = c2 == null;
        boolean i1 = isInfinity();
        boolean i2 = other.isInfinity();
        if (!i1 && !i2) {
            ECPoint p1 = this;
            ECPoint p2 = other;
            if (!n1 || !n2) {
                if (n1) {
                    p2 = p2.normalize();
                } else if (n2) {
                    p1 = p1.normalize();
                } else if (!c1.equals(c2)) {
                    return false;
                } else {
                    ECPoint[] points = {this, c1.importPoint(p2)};
                    c1.normalizeAll(points);
                    p1 = points[0];
                    p2 = points[1];
                }
            }
            if (!p1.getXCoord().equals(p2.getXCoord()) || !p1.getYCoord().equals(p2.getYCoord())) {
                return false;
            }
            return true;
        } else if (!i1 || !i2) {
            return false;
        } else {
            if (n1 || n2 || c1.equals(c2)) {
                return true;
            }
            return false;
        }
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ECPoint)) {
            return false;
        }
        return equals((ECPoint) other);
    }

    public int hashCode() {
        ECCurve c = getCurve();
        int hc = c == null ? 0 : ~c.hashCode();
        if (isInfinity()) {
            return hc;
        }
        ECPoint p = normalize();
        return (hc ^ (p.getXCoord().hashCode() * 17)) ^ (p.getYCoord().hashCode() * 257);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer sb = new StringBuffer();
        sb.append('(');
        sb.append(getRawXCoord());
        sb.append(',');
        sb.append(getRawYCoord());
        for (int i = 0; i < this.zs.length; i++) {
            sb.append(',');
            sb.append(this.zs[i]);
        }
        sb.append(')');
        return sb.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean compressed) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normed = normalize();
        byte[] X = normed.getXCoord().getEncoded();
        if (compressed) {
            byte[] PO = new byte[(X.length + 1)];
            PO[0] = (byte) (normed.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(X, 0, PO, 1, X.length);
            return PO;
        }
        byte[] Y = normed.getYCoord().getEncoded();
        byte[] PO2 = new byte[(X.length + Y.length + 1)];
        PO2[0] = 4;
        System.arraycopy(X, 0, PO2, 1, X.length);
        System.arraycopy(Y, 0, PO2, X.length + 1, Y.length);
        return PO2;
    }

    public ECPoint timesPow2(int e) {
        if (e >= 0) {
            ECPoint p = this;
            while (true) {
                e--;
                if (e < 0) {
                    return p;
                }
                p = p.twice();
            }
        } else {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
    }

    public ECPoint twicePlus(ECPoint b) {
        return twice().add(b);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger k) {
        return getCurve().getMultiplier().multiply(this, k);
    }

    public static abstract class AbstractFp extends ECPoint {
        protected AbstractFp(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            super(curve, x, y);
        }

        protected AbstractFp(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs) {
            super(curve, x, y, zs);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement X = this.x;
            ECFieldElement Y = this.y;
            ECFieldElement A = this.curve.getA();
            ECFieldElement B = this.curve.getB();
            ECFieldElement lhs = Y.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement Z = this.zs[0];
                    if (!Z.isOne()) {
                        ECFieldElement Z2 = Z.square();
                        ECFieldElement Z3 = Z.multiply(Z2);
                        lhs = lhs.multiply(Z);
                        A = A.multiply(Z2);
                        B = B.multiply(Z3);
                    }
                } else if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                    ECFieldElement Z4 = this.zs[0];
                    if (!Z4.isOne()) {
                        ECFieldElement Z22 = Z4.square();
                        ECFieldElement Z42 = Z22.square();
                        ECFieldElement Z6 = Z22.multiply(Z42);
                        A = A.multiply(Z42);
                        B = B.multiply(Z6);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return lhs.equals(X.square().add(A).multiply(X).add(B));
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint b) {
            if (b.isInfinity()) {
                return this;
            }
            return add(b.negate());
        }
    }

    public static class Fp extends AbstractFp {
        public Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
            super(curve, x, y);
            if ((x == null) == (y != null ? false : true)) {
                this.withCompression = withCompression;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        Fp(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            super(curve, x, y, zs);
            this.withCompression = withCompression;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new Fp(null, getAffineXCoord(), getAffineYCoord(), false);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int index) {
            if (index == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(index);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint add(ECPoint b) {
            ECFieldElement Y1;
            ECFieldElement u2;
            ECFieldElement X2;
            ECFieldElement v1;
            ECFieldElement C;
            ECFieldElement X22;
            ECFieldElement Y12;
            ECFieldElement X1;
            ECFieldElement Z1Cubed;
            ECFieldElement U2;
            ECFieldElement Z1Squared;
            ECFieldElement U1;
            ECFieldElement Z2Squared;
            ECFieldElement Z1Squared2;
            ECFieldElement Z3;
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
            int coord = curve.getCoordinateSystem();
            ECFieldElement X12 = this.x;
            ECFieldElement Y13 = this.y;
            ECFieldElement X23 = b.x;
            ECFieldElement Y2 = b.y;
            if (coord == 0) {
                ECFieldElement dx = X23.subtract(X12);
                ECFieldElement dy = Y2.subtract(Y13);
                if (!dx.isZero()) {
                    ECFieldElement gamma = dy.divide(dx);
                    ECFieldElement X3 = gamma.square().subtract(X12).subtract(X23);
                    return new Fp(curve, X3, gamma.multiply(X12.subtract(X3)).subtract(Y13), this.withCompression);
                } else if (dy.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coord == 1) {
                ECFieldElement Z1 = this.zs[0];
                ECFieldElement Z2 = b.zs[0];
                boolean Z1IsOne = Z1.isOne();
                boolean Z2IsOne = Z2.isOne();
                ECFieldElement u1 = Z1IsOne ? Y2 : Y2.multiply(Z1);
                if (Z2IsOne) {
                    u2 = Y13;
                    Y1 = u2;
                } else {
                    Y1 = Y13;
                    u2 = Y1.multiply(Z2);
                }
                ECFieldElement u = u1.subtract(u2);
                if (Z1IsOne) {
                    v1 = X23;
                    X2 = v1;
                } else {
                    X2 = X23;
                    v1 = X2.multiply(Z1);
                }
                ECFieldElement v2 = Z2IsOne ? X12 : X12.multiply(Z2);
                ECFieldElement v = v1.subtract(v2);
                if (!v.isZero()) {
                    ECFieldElement w = Z1IsOne ? Z2 : Z2IsOne ? Z1 : Z1.multiply(Z2);
                    ECFieldElement vSquared = v.square();
                    ECFieldElement vCubed = vSquared.multiply(v);
                    ECFieldElement vSquaredV2 = vSquared.multiply(v2);
                    ECFieldElement A = u.square().multiply(w).subtract(vCubed).subtract(two(vSquaredV2));
                    return new Fp(curve, v.multiply(A), vSquaredV2.subtract(A).multiplyMinusProduct(u, u2, vCubed), new ECFieldElement[]{vCubed.multiply(w)}, this.withCompression);
                } else if (u.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coord == 2 || coord == 4) {
                ECFieldElement Z12 = this.zs[0];
                ECFieldElement Z22 = b.zs[0];
                boolean Z1IsOne2 = Z12.isOne();
                if (Z1IsOne2 || !Z12.equals(Z22)) {
                    if (Z1IsOne2) {
                        Z1Squared = Z12;
                        U2 = X23;
                        Z1Cubed = Y2;
                    } else {
                        Z1Squared = Z12.square();
                        U2 = Z1Squared.multiply(X23);
                        Z1Cubed = Z1Squared.multiply(Z12).multiply(Y2);
                    }
                    boolean Z2IsOne2 = Z22.isOne();
                    if (Z2IsOne2) {
                        Z2Squared = Z22;
                        U1 = X12;
                        Z1Squared2 = Y13;
                    } else {
                        Z2Squared = Z22.square();
                        U1 = Z2Squared.multiply(X12);
                        Z1Squared2 = Z2Squared.multiply(Z22).multiply(Y13);
                    }
                    ECFieldElement H = U1.subtract(U2);
                    ECFieldElement R = Z1Squared2.subtract(Z1Cubed);
                    if (!H.isZero()) {
                        ECFieldElement HSquared = H.square();
                        ECFieldElement G = HSquared.multiply(H);
                        ECFieldElement V = HSquared.multiply(U1);
                        ECFieldElement X32 = R.square().add(G).subtract(two(V));
                        ECFieldElement Y3 = V.subtract(X32).multiplyMinusProduct(R, G, Z1Squared2);
                        if (!Z1IsOne2) {
                            Z3 = H.multiply(Z12);
                        } else {
                            Z3 = H;
                        }
                        if (!Z2IsOne2) {
                            Z3 = Z3.multiply(Z22);
                        }
                        if (Z3 == H) {
                            X1 = Z3;
                            X22 = X32;
                            C = Y3;
                            Y12 = HSquared;
                        } else {
                            X1 = Z3;
                            X22 = X32;
                            C = Y3;
                            Y12 = null;
                        }
                    } else if (R.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                } else {
                    ECFieldElement dx2 = X12.subtract(X23);
                    ECFieldElement dy2 = Y13.subtract(Y2);
                    if (!dx2.isZero()) {
                        ECFieldElement C2 = dx2.square();
                        ECFieldElement W1 = X12.multiply(C2);
                        ECFieldElement W2 = X23.multiply(C2);
                        ECFieldElement A1 = W1.subtract(W2).multiply(Y13);
                        ECFieldElement X33 = dy2.square().subtract(W1).subtract(W2);
                        C = W1.subtract(X33).multiply(dy2).subtract(A1);
                        Y12 = null;
                        X1 = dx2.multiply(Z12);
                        X22 = X33;
                    } else if (dy2.isZero()) {
                        return twice();
                    } else {
                        return curve.getInfinity();
                    }
                }
                return new Fp(curve, X22, C, coord == 4 ? new ECFieldElement[]{X1, calculateJacobianModifiedW(X1, Y12)} : new ECFieldElement[]{X1}, this.withCompression);
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        /* JADX INFO: Multiple debug info for r4v16 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('Z1Squared' com.android.org.bouncycastle.math.ec.ECFieldElement), D('S' com.android.org.bouncycastle.math.ec.ECFieldElement)] */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement M;
            ECFieldElement M2;
            ECFieldElement Z3;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement Y1 = this.y;
            if (Y1.isZero()) {
                return curve.getInfinity();
            }
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.x;
            if (coord == 0) {
                ECFieldElement gamma = three(X1.square()).add(getCurve().getA()).divide(two(Y1));
                ECFieldElement X3 = gamma.square().subtract(two(X1));
                return new Fp(curve, X3, gamma.multiply(X1.subtract(X3)).subtract(Y1), this.withCompression);
            } else if (coord == 1) {
                ECFieldElement Z1 = this.zs[0];
                boolean Z1IsOne = Z1.isOne();
                ECFieldElement w = curve.getA();
                if (!w.isZero() && !Z1IsOne) {
                    w = w.multiply(Z1.square());
                }
                ECFieldElement w2 = w.add(three(X1.square()));
                ECFieldElement s = Z1IsOne ? Y1 : Y1.multiply(Z1);
                ECFieldElement t = Z1IsOne ? Y1.square() : s.multiply(Y1);
                ECFieldElement _4B = four(X1.multiply(t));
                ECFieldElement h = w2.square().subtract(two(_4B));
                ECFieldElement _2s = two(s);
                ECFieldElement X32 = h.multiply(_2s);
                ECFieldElement _2t = two(t);
                return new Fp(curve, X32, _4B.subtract(h).multiply(w2).subtract(two(_2t.square())), new ECFieldElement[]{two(Z1IsOne ? two(_2t) : _2s.square()).multiply(s)}, this.withCompression);
            } else if (coord == 2) {
                ECFieldElement Z12 = this.zs[0];
                boolean Z1IsOne2 = Z12.isOne();
                ECFieldElement Y1Squared = Y1.square();
                ECFieldElement T = Y1Squared.square();
                ECFieldElement a4 = curve.getA();
                ECFieldElement a4Neg = a4.negate();
                if (a4Neg.toBigInteger().equals(BigInteger.valueOf(3))) {
                    ECFieldElement Z1Squared = Z1IsOne2 ? Z12 : Z12.square();
                    M = three(X1.add(Z1Squared).multiply(X1.subtract(Z1Squared)));
                    M2 = four(Y1Squared.multiply(X1));
                } else {
                    ECFieldElement M3 = three(X1.square());
                    if (Z1IsOne2) {
                        M3 = M3.add(a4);
                    } else if (!a4.isZero()) {
                        ECFieldElement Z1Pow4 = Z12.square().square();
                        if (a4Neg.bitLength() < a4.bitLength()) {
                            M3 = M3.subtract(Z1Pow4.multiply(a4Neg));
                        } else {
                            M3 = M3.add(Z1Pow4.multiply(a4));
                        }
                    }
                    M = M3;
                    M2 = four(X1.multiply(Y1Squared));
                }
                ECFieldElement X33 = M.square().subtract(two(M2));
                ECFieldElement Y3 = M2.subtract(X33).multiply(M).subtract(eight(T));
                ECFieldElement Z32 = two(Y1);
                if (!Z1IsOne2) {
                    Z3 = Z32.multiply(Z12);
                } else {
                    Z3 = Z32;
                }
                return new Fp(curve, X33, Y3, new ECFieldElement[]{Z3}, this.withCompression);
            } else if (coord == 4) {
                return twiceJacobianModified(true);
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
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
            ECFieldElement Y1 = this.y;
            if (Y1.isZero()) {
                return b;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            if (coord == 0) {
                ECFieldElement X1 = this.x;
                ECFieldElement X2 = b.x;
                ECFieldElement Y2 = b.y;
                ECFieldElement dx = X2.subtract(X1);
                ECFieldElement dy = Y2.subtract(Y1);
                if (!dx.isZero()) {
                    ECFieldElement X = dx.square();
                    ECFieldElement d = X.multiply(two(X1).add(X2)).subtract(dy.square());
                    if (d.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement I = d.multiply(dx).invert();
                    ECFieldElement L1 = d.multiply(I).multiply(dy);
                    ECFieldElement L2 = two(Y1).multiply(X).multiply(dx).multiply(I).subtract(L1);
                    ECFieldElement X4 = L2.subtract(L1).multiply(L1.add(L2)).add(X2);
                    return new Fp(curve, X4, X1.subtract(X4).multiply(L2).subtract(Y1), this.withCompression);
                } else if (dy.isZero()) {
                    return threeTimes();
                } else {
                    return this;
                }
            } else if (coord != 4) {
                return twice().add(b);
            } else {
                return twiceJacobianModified(false).add(b);
            }
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement Y1 = this.y;
            if (Y1.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            if (coord == 0) {
                ECFieldElement X1 = this.x;
                ECFieldElement _2Y1 = two(Y1);
                ECFieldElement X = _2Y1.square();
                ECFieldElement Z = three(X1.square()).add(getCurve().getA());
                ECFieldElement d = three(X1).multiply(X).subtract(Z.square());
                if (d.isZero()) {
                    return getCurve().getInfinity();
                }
                ECFieldElement I = d.multiply(_2Y1).invert();
                ECFieldElement L1 = d.multiply(I).multiply(Z);
                ECFieldElement L2 = X.square().multiply(I).subtract(L1);
                ECFieldElement X4 = L2.subtract(L1).multiply(L1.add(L2)).add(X1);
                return new Fp(curve, X4, X1.subtract(X4).multiply(L2).subtract(Y1), this.withCompression);
            } else if (coord != 4) {
                return twice().add(this);
            } else {
                return twiceJacobianModified(false).add(this);
            }
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint timesPow2(int e) {
            int i = e;
            if (i < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            } else if (i == 0 || isInfinity()) {
                return this;
            } else {
                if (i == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement Y1 = this.y;
                if (Y1.isZero()) {
                    return curve.getInfinity();
                }
                int coord = curve.getCoordinateSystem();
                ECFieldElement W1 = curve.getA();
                ECFieldElement X1 = this.x;
                ECFieldElement Z1 = this.zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.zs[0];
                if (!Z1.isOne() && coord != 0) {
                    if (coord == 1) {
                        ECFieldElement Z1Sq = Z1.square();
                        X1 = X1.multiply(Z1);
                        Y1 = Y1.multiply(Z1Sq);
                        W1 = calculateJacobianModifiedW(Z1, Z1Sq);
                    } else if (coord == 2) {
                        W1 = calculateJacobianModifiedW(Z1, null);
                    } else if (coord == 4) {
                        W1 = getJacobianModifiedW();
                    } else {
                        throw new IllegalStateException("unsupported coordinate system");
                    }
                }
                int i2 = 0;
                ECFieldElement Y12 = Y1;
                ECFieldElement W12 = W1;
                ECFieldElement X12 = X1;
                ECFieldElement Z12 = Z1;
                while (i2 < i) {
                    if (Y12.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement M = three(X12.square());
                    ECFieldElement _2Y1 = two(Y12);
                    ECFieldElement _2Y1Squared = _2Y1.multiply(Y12);
                    ECFieldElement S = two(X12.multiply(_2Y1Squared));
                    ECFieldElement _8T = two(_2Y1Squared.square());
                    if (!W12.isZero()) {
                        M = M.add(W12);
                        W12 = two(_8T.multiply(W12));
                    }
                    X12 = M.square().subtract(two(S));
                    Y12 = M.multiply(S.subtract(X12)).subtract(_8T);
                    Z12 = Z12.isOne() ? _2Y1 : _2Y1.multiply(Z12);
                    i2++;
                    i = e;
                }
                if (coord == 0) {
                    ECFieldElement zInv = Z12.invert();
                    ECFieldElement zInv2 = zInv.square();
                    return new Fp(curve, X12.multiply(zInv2), Y12.multiply(zInv2.multiply(zInv)), this.withCompression);
                } else if (coord == 1) {
                    return new Fp(curve, X12.multiply(Z12), Y12, new ECFieldElement[]{Z12.multiply(Z12.square())}, this.withCompression);
                } else if (coord == 2) {
                    return new Fp(curve, X12, Y12, new ECFieldElement[]{Z12}, this.withCompression);
                } else if (coord == 4) {
                    return new Fp(curve, X12, Y12, new ECFieldElement[]{Z12, W12}, this.withCompression);
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
        }

        /* access modifiers changed from: protected */
        public ECFieldElement two(ECFieldElement x) {
            return x.add(x);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement three(ECFieldElement x) {
            return two(x).add(x);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement four(ECFieldElement x) {
            return two(two(x));
        }

        /* access modifiers changed from: protected */
        public ECFieldElement eight(ECFieldElement x) {
            return four(two(x));
        }

        /* access modifiers changed from: protected */
        public ECFieldElement doubleProductFromSquares(ECFieldElement a, ECFieldElement b, ECFieldElement aSquared, ECFieldElement bSquared) {
            return a.add(b).square().subtract(aSquared).subtract(bSquared);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() != 0) {
                return new Fp(curve, this.x, this.y.negate(), this.zs, this.withCompression);
            }
            return new Fp(curve, this.x, this.y.negate(), this.withCompression);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement calculateJacobianModifiedW(ECFieldElement Z, ECFieldElement ZSquared) {
            ECFieldElement a4 = getCurve().getA();
            if (a4.isZero() || Z.isOne()) {
                return a4;
            }
            if (ZSquared == null) {
                ZSquared = Z.square();
            }
            ECFieldElement W = ZSquared.square();
            ECFieldElement a4Neg = a4.negate();
            if (a4Neg.bitLength() < a4.bitLength()) {
                return W.multiply(a4Neg).negate();
            }
            return W.multiply(a4);
        }

        /* access modifiers changed from: protected */
        public ECFieldElement getJacobianModifiedW() {
            ECFieldElement W = this.zs[1];
            if (W != null) {
                return W;
            }
            ECFieldElement[] eCFieldElementArr = this.zs;
            ECFieldElement W2 = calculateJacobianModifiedW(this.zs[0], null);
            eCFieldElementArr[1] = W2;
            return W2;
        }

        /* access modifiers changed from: protected */
        public Fp twiceJacobianModified(boolean calculateW) {
            ECFieldElement X1 = this.x;
            ECFieldElement Y1 = this.y;
            ECFieldElement Z1 = this.zs[0];
            ECFieldElement W1 = getJacobianModifiedW();
            ECFieldElement M = three(X1.square()).add(W1);
            ECFieldElement _2Y1 = two(Y1);
            ECFieldElement _2Y1Squared = _2Y1.multiply(Y1);
            ECFieldElement S = two(X1.multiply(_2Y1Squared));
            ECFieldElement X3 = M.square().subtract(two(S));
            ECFieldElement _8T = two(_2Y1Squared.square());
            return new Fp(getCurve(), X3, M.multiply(S.subtract(X3)).subtract(_8T), new ECFieldElement[]{Z1.isOne() ? _2Y1 : _2Y1.multiply(Z1), calculateW ? two(_8T.multiply(W1)) : null}, this.withCompression);
        }
    }

    public static abstract class AbstractF2m extends ECPoint {
        protected AbstractF2m(ECCurve curve, ECFieldElement x, ECFieldElement y) {
            super(curve, x, y);
        }

        protected AbstractF2m(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs) {
            super(curve, x, y, zs);
        }

        /* JADX INFO: Multiple debug info for r6v1 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('ZIsOne' boolean), D('Y' com.android.org.bouncycastle.math.ec.ECFieldElement)] */
        /* JADX INFO: Multiple debug info for r7v5 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('L' com.android.org.bouncycastle.math.ec.ECFieldElement), D('Y' com.android.org.bouncycastle.math.ec.ECFieldElement)] */
        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public boolean satisfiesCurveEquation() {
            ECFieldElement Z4;
            ECFieldElement Z2;
            ECCurve curve = getCurve();
            ECFieldElement X = this.x;
            ECFieldElement A = curve.getA();
            ECFieldElement B = curve.getB();
            int coord = curve.getCoordinateSystem();
            if (coord == 6) {
                ECFieldElement Z = this.zs[0];
                boolean ZIsOne = Z.isOne();
                if (X.isZero()) {
                    ECFieldElement lhs = this.y.square();
                    ECFieldElement rhs = B;
                    if (!ZIsOne) {
                        rhs = rhs.multiply(Z.square());
                    }
                    return lhs.equals(rhs);
                }
                ECFieldElement Y = this.y;
                ECFieldElement X2 = X.square();
                if (ZIsOne) {
                    Z2 = Y.square().add(Y).add(A);
                    Z4 = X2.square().add(B);
                } else {
                    ECFieldElement Z22 = Z.square();
                    ECFieldElement Z42 = Z22.square();
                    ECFieldElement lhs2 = Y.add(Z).multiplyPlusProduct(Y, A, Z22);
                    Z4 = X2.squarePlusProduct(B, Z42);
                    Z2 = lhs2;
                }
                return Z2.multiply(X2).equals(Z4);
            }
            ECFieldElement Y2 = this.y;
            ECFieldElement lhs3 = Y2.add(X).multiply(Y2);
            if (coord != 0) {
                if (coord == 1) {
                    ECFieldElement Z3 = this.zs[0];
                    if (!Z3.isOne()) {
                        ECFieldElement Z32 = Z3.multiply(Z3.square());
                        lhs3 = lhs3.multiply(Z3);
                        A = A.multiply(Z3);
                        B = B.multiply(Z32);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return lhs3.equals(X.add(A).multiply(X.square()).add(B));
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public boolean satisfiesOrder() {
            BigInteger cofactor = this.curve.getCofactor();
            if (ECConstants.TWO.equals(cofactor)) {
                return ((ECFieldElement.AbstractF2m) normalize().getAffineXCoord().add(this.curve.getA())).trace() == 0;
            }
            if (!ECConstants.FOUR.equals(cofactor)) {
                return ECPoint.super.satisfiesOrder();
            }
            ECPoint N = normalize();
            ECFieldElement X = N.getAffineXCoord();
            ECFieldElement lambda = ((ECCurve.AbstractF2m) this.curve).solveQuadraticEquation(X.add(this.curve.getA()));
            if (lambda == null) {
                return false;
            }
            ECFieldElement t = X.multiply(lambda).add(N.getAffineYCoord()).add(this.curve.getA());
            return ((ECFieldElement.AbstractF2m) t).trace() == 0 || ((ECFieldElement.AbstractF2m) t.add(X)).trace() == 0;
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement scale) {
            if (isInfinity()) {
                return this;
            }
            int coord = getCurveCoordinateSystem();
            if (coord == 5) {
                ECFieldElement X = getRawXCoord();
                ECFieldElement L = getRawYCoord();
                return getCurve().createRawPoint(X, L.add(X).divide(scale).add(X.multiply(scale)), getRawZCoords(), this.withCompression);
            } else if (coord != 6) {
                return ECPoint.super.scaleX(scale);
            } else {
                ECFieldElement X2 = getRawXCoord();
                ECFieldElement L2 = getRawYCoord();
                ECFieldElement Z = getRawZCoords()[0];
                ECFieldElement X22 = X2.multiply(scale.square());
                ECFieldElement L22 = L2.add(X2).add(X22);
                ECFieldElement Z2 = Z.multiply(scale);
                return getCurve().createRawPoint(X22, L22, new ECFieldElement[]{Z2}, this.withCompression);
            }
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement scale) {
            if (isInfinity()) {
                return this;
            }
            int coord = getCurveCoordinateSystem();
            if (coord != 5 && coord != 6) {
                return ECPoint.super.scaleY(scale);
            }
            ECFieldElement X = getRawXCoord();
            return getCurve().createRawPoint(X, getRawYCoord().add(X).multiply(scale).add(X), getRawZCoords(), this.withCompression);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint b) {
            if (b.isInfinity()) {
                return this;
            }
            return add(b.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.x;
            if (coord != 0) {
                if (coord != 1) {
                    if (coord != 5) {
                        if (coord != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                ECFieldElement Y1 = this.y;
                ECFieldElement Z1 = this.zs[0];
                return (AbstractF2m) curve.createRawPoint(X1.square(), Y1.square(), new ECFieldElement[]{Z1.square()}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(X1.square(), this.y.square(), this.withCompression);
        }

        public AbstractF2m tauPow(int pow) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.x;
            if (coord != 0) {
                if (coord != 1) {
                    if (coord != 5) {
                        if (coord != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                ECFieldElement Y1 = this.y;
                ECFieldElement Z1 = this.zs[0];
                return (AbstractF2m) curve.createRawPoint(X1.squarePow(pow), Y1.squarePow(pow), new ECFieldElement[]{Z1.squarePow(pow)}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(X1.squarePow(pow), this.y.squarePow(pow), this.withCompression);
        }
    }

    public static class F2m extends AbstractF2m {
        public F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, boolean withCompression) {
            super(curve, x, y);
            if ((x == null) == (y != null ? false : true)) {
                if (x != null) {
                    ECFieldElement.F2m.checkFieldElements(this.x, this.y);
                    if (curve != null) {
                        ECFieldElement.F2m.checkFieldElements(this.x, this.curve.getA());
                    }
                }
                this.withCompression = withCompression;
                return;
            }
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }

        F2m(ECCurve curve, ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            super(curve, x, y, zs);
            this.withCompression = withCompression;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord(), false);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int coord = getCurveCoordinateSystem();
            if (coord != 5 && coord != 6) {
                return this.y;
            }
            ECFieldElement X = this.x;
            ECFieldElement L = this.y;
            if (isInfinity() || X.isZero()) {
                return L;
            }
            ECFieldElement Y = L.add(X).multiply(X);
            if (6 != coord) {
                return Y;
            }
            ECFieldElement Z = this.zs[0];
            if (!Z.isOne()) {
                return Y.divide(Z);
            }
            return Y;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public boolean getCompressionYTilde() {
            ECFieldElement X = getRawXCoord();
            if (X.isZero()) {
                return false;
            }
            ECFieldElement Y = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 5 && curveCoordinateSystem != 6) {
                return Y.divide(X).testBitZero();
            }
            if (Y.testBitZero() != X.testBitZero()) {
                return true;
            }
            return false;
        }

        /* JADX INFO: Multiple debug info for r9v2 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('Y1' com.android.org.bouncycastle.math.ec.ECFieldElement), D('coord' int)] */
        /* JADX INFO: Multiple debug info for r3v18 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('Y1' com.android.org.bouncycastle.math.ec.ECFieldElement), D('Z3' com.android.org.bouncycastle.math.ec.ECFieldElement)] */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint add(ECPoint b) {
            ECFieldElement U2;
            ECFieldElement S2;
            ECFieldElement S1;
            ECFieldElement Z3;
            ECFieldElement L3;
            ECFieldElement X3;
            if (isInfinity()) {
                return b;
            }
            if (b.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coord = curve.getCoordinateSystem();
            ECFieldElement X1 = this.x;
            ECFieldElement X2 = b.x;
            if (coord == 0) {
                ECFieldElement Y1 = this.y;
                ECFieldElement Y2 = b.y;
                ECFieldElement dx = X1.add(X2);
                ECFieldElement dy = Y1.add(Y2);
                if (!dx.isZero()) {
                    ECFieldElement L = dy.divide(dx);
                    ECFieldElement X32 = L.square().add(L).add(dx).add(curve.getA());
                    return new F2m(curve, X32, L.multiply(X1.add(X32)).add(X32).add(Y1), this.withCompression);
                } else if (dy.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coord == 1) {
                ECFieldElement Y12 = this.y;
                ECFieldElement Z1 = this.zs[0];
                ECFieldElement Y22 = b.y;
                ECFieldElement Z2 = b.zs[0];
                boolean Z2IsOne = Z2.isOne();
                ECFieldElement U = Z1.multiply(Y22).add(Z2IsOne ? Y12 : Y12.multiply(Z2));
                ECFieldElement V = Z1.multiply(X2).add(Z2IsOne ? X1 : X1.multiply(Z2));
                if (!V.isZero()) {
                    ECFieldElement VSq = V.square();
                    ECFieldElement VCu = VSq.multiply(V);
                    ECFieldElement W = Z2IsOne ? Z1 : Z1.multiply(Z2);
                    ECFieldElement uv = U.add(V);
                    ECFieldElement A = uv.multiplyPlusProduct(U, VSq, curve.getA()).multiply(W).add(VCu);
                    return new F2m(curve, V.multiply(A), U.multiplyPlusProduct(X1, V, Y12).multiplyPlusProduct(Z2IsOne ? VSq : VSq.multiply(Z2), uv, A), new ECFieldElement[]{VCu.multiply(W)}, this.withCompression);
                } else if (U.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (coord != 6) {
                throw new IllegalStateException("unsupported coordinate system");
            } else if (!X1.isZero()) {
                ECFieldElement L1 = this.y;
                ECFieldElement Z12 = this.zs[0];
                ECFieldElement L2 = b.y;
                ECFieldElement Z22 = b.zs[0];
                boolean Z1IsOne = Z12.isOne();
                if (!Z1IsOne) {
                    U2 = X2.multiply(Z12);
                    S2 = L2.multiply(Z12);
                } else {
                    U2 = X2;
                    S2 = L2;
                }
                boolean Z2IsOne2 = Z22.isOne();
                ECFieldElement U1 = X1;
                ECFieldElement S12 = L1;
                if (!Z2IsOne2) {
                    U1 = U1.multiply(Z22);
                    S12 = S12.multiply(Z22);
                }
                ECFieldElement A2 = S12.add(S2);
                ECFieldElement B = U1.add(U2);
                if (!B.isZero()) {
                    if (X2.isZero()) {
                        ECPoint p = normalize();
                        ECFieldElement X12 = p.getXCoord();
                        ECFieldElement Y13 = p.getYCoord();
                        S1 = S12;
                        ECFieldElement L4 = Y13.add(L2).divide(X12);
                        ECFieldElement X33 = L4.square().add(L4).add(X12).add(curve.getA());
                        if (X33.isZero()) {
                            return new F2m(curve, X33, curve.getB().sqrt(), this.withCompression);
                        }
                        L3 = L4.multiply(X12.add(X33)).add(X33).add(Y13).divide(X33).add(X33);
                        Z3 = curve.fromBigInteger(ECConstants.ONE);
                        X3 = X33;
                    } else {
                        S1 = S12;
                        ECFieldElement B2 = B.square();
                        ECFieldElement AU1 = A2.multiply(U1);
                        ECFieldElement AU2 = A2.multiply(U2);
                        X3 = AU1.multiply(AU2);
                        if (X3.isZero()) {
                            return new F2m(curve, X3, curve.getB().sqrt(), this.withCompression);
                        }
                        ECFieldElement ABZ2 = A2.multiply(B2);
                        if (!Z2IsOne2) {
                            ABZ2 = ABZ2.multiply(Z22);
                        }
                        L3 = AU2.add(B2).squarePlusProduct(ABZ2, L1.add(Z12));
                        Z3 = ABZ2;
                        if (!Z1IsOne) {
                            Z3 = Z3.multiply(Z12);
                        }
                    }
                    return new F2m(curve, X3, L3, new ECFieldElement[]{Z3}, this.withCompression);
                } else if (A2.isZero()) {
                    return twice();
                } else {
                    return curve.getInfinity();
                }
            } else if (X2.isZero()) {
                return curve.getInfinity();
            } else {
                return b.add(this);
            }
        }

        /* JADX INFO: Multiple debug info for r1v2 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('Y1' com.android.org.bouncycastle.math.ec.ECFieldElement), D('curve' com.android.org.bouncycastle.math.ec.ECCurve)] */
        /* JADX INFO: Multiple debug info for r1v3 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('Y1' com.android.org.bouncycastle.math.ec.ECFieldElement), D('curve' com.android.org.bouncycastle.math.ec.ECCurve)] */
        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement t1;
            ECFieldElement t2;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement X1 = this.x;
            if (X1.isZero()) {
                return curve.getInfinity();
            }
            int coord = curve.getCoordinateSystem();
            if (coord == 0) {
                ECFieldElement L1 = this.y.divide(X1).add(X1);
                ECFieldElement X3 = L1.square().add(L1).add(curve.getA());
                return new F2m(curve, X3, X1.squarePlusProduct(X3, L1.addOne()), this.withCompression);
            } else if (coord == 1) {
                ECFieldElement Y1 = this.y;
                ECFieldElement Z1 = this.zs[0];
                boolean Z1IsOne = Z1.isOne();
                ECFieldElement X1Z1 = Z1IsOne ? X1 : X1.multiply(Z1);
                ECFieldElement Y1Z1 = Z1IsOne ? Y1 : Y1.multiply(Z1);
                ECFieldElement X1Sq = X1.square();
                ECFieldElement S = X1Sq.add(Y1Z1);
                ECFieldElement vSquared = X1Z1.square();
                ECFieldElement sv = S.add(X1Z1);
                ECFieldElement h = sv.multiplyPlusProduct(S, vSquared, curve.getA());
                return new F2m(curve, X1Z1.multiply(h), X1Sq.square().multiplyPlusProduct(X1Z1, h, sv), new ECFieldElement[]{X1Z1.multiply(vSquared)}, this.withCompression);
            } else if (coord == 6) {
                ECFieldElement L12 = this.y;
                ECFieldElement Z12 = this.zs[0];
                boolean Z1IsOne2 = Z12.isOne();
                ECFieldElement L1Z1 = Z1IsOne2 ? L12 : L12.multiply(Z12);
                ECFieldElement Z1Sq = Z1IsOne2 ? Z12 : Z12.square();
                ECFieldElement a = curve.getA();
                ECFieldElement aZ1Sq = Z1IsOne2 ? a : a.multiply(Z1Sq);
                ECFieldElement T = L12.square().add(L1Z1).add(aZ1Sq);
                if (T.isZero()) {
                    return new F2m(curve, T, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement X32 = T.square();
                ECFieldElement Z3 = Z1IsOne2 ? T : T.multiply(Z1Sq);
                ECFieldElement b = curve.getB();
                if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement t12 = L12.add(X1).square();
                    if (b.isOne()) {
                        t2 = aZ1Sq.add(Z1Sq).square();
                    } else {
                        t2 = aZ1Sq.squarePlusProduct(b, Z1Sq.square());
                    }
                    ECFieldElement L3 = t12.add(T).add(Z1Sq).multiply(t12).add(t2).add(X32);
                    if (a.isZero()) {
                        L3 = L3.add(Z3);
                    } else if (!a.isOne()) {
                        L3 = L3.add(a.addOne().multiply(Z3));
                    }
                    t1 = L3;
                } else {
                    t1 = (Z1IsOne2 ? X1 : X1.multiply(Z12)).squarePlusProduct(T, L1Z1).add(X32).add(Z3);
                }
                return new F2m(curve, X32, t1, new ECFieldElement[]{Z3}, this.withCompression);
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint b) {
            if (isInfinity()) {
                return b;
            }
            if (b.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement X1 = this.x;
            if (X1.isZero()) {
                return b;
            }
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(b);
            }
            ECFieldElement X2 = b.x;
            ECFieldElement Z2 = b.zs[0];
            if (!X2.isZero()) {
                if (Z2.isOne()) {
                    ECFieldElement L1 = this.y;
                    ECFieldElement Z1 = this.zs[0];
                    ECFieldElement L2 = b.y;
                    ECFieldElement X1Sq = X1.square();
                    ECFieldElement L1Sq = L1.square();
                    ECFieldElement Z1Sq = Z1.square();
                    ECFieldElement T = curve.getA().multiply(Z1Sq).add(L1Sq).add(L1.multiply(Z1));
                    ECFieldElement L2plus1 = L2.addOne();
                    ECFieldElement A = curve.getA().add(L2plus1).multiply(Z1Sq).add(L1Sq).multiplyPlusProduct(T, X1Sq, Z1Sq);
                    ECFieldElement X2Z1Sq = X2.multiply(Z1Sq);
                    ECFieldElement B = X2Z1Sq.add(T).square();
                    if (B.isZero()) {
                        if (A.isZero()) {
                            return b.twice();
                        }
                        return curve.getInfinity();
                    } else if (A.isZero()) {
                        return new F2m(curve, A, curve.getB().sqrt(), this.withCompression);
                    } else {
                        ECFieldElement X3 = A.square().multiply(X2Z1Sq);
                        ECFieldElement Z3 = A.multiply(B).multiply(Z1Sq);
                        return new F2m(curve, X3, A.add(B).square().multiplyPlusProduct(T, L2plus1, Z3), new ECFieldElement[]{Z3}, this.withCompression);
                    }
                }
            }
            return twice().add(b);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement X = this.x;
            if (X.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, X, this.y.add(X), this.withCompression);
            } else if (curveCoordinateSystem == 1) {
                ECFieldElement Y = this.y;
                ECFieldElement Z = this.zs[0];
                return new F2m(this.curve, X, Y.add(X), new ECFieldElement[]{Z}, this.withCompression);
            } else if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, X, this.y.addOne(), this.withCompression);
            } else if (curveCoordinateSystem == 6) {
                ECFieldElement L = this.y;
                ECFieldElement Z2 = this.zs[0];
                return new F2m(this.curve, X, L.add(Z2), new ECFieldElement[]{Z2}, this.withCompression);
            } else {
                throw new IllegalStateException("unsupported coordinate system");
            }
        }
    }
}
