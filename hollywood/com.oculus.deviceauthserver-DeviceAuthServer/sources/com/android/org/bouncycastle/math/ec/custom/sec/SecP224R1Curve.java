package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECLookupTable;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat224;
import com.android.org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;

public class SecP224R1Curve extends ECCurve.AbstractFp {
    private static final int SecP224R1_DEFAULT_COORDS = 2;
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"));
    protected SecP224R1Point infinity = new SecP224R1Point(this, null, null);

    public SecP224R1Curve() {
        super(q);
        this.a = fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decode("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4")));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP224R1Curve();
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int coord) {
        if (coord != 2) {
            return false;
        }
        return true;
    }

    public BigInteger getQ() {
        return q;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return q.bitLength();
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger x) {
        return new SecP224R1FieldElement(x);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecP224R1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecP224R1Point(this, x, y, zs, withCompression);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
        final int[] table = new int[(len * 7 * 2)];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            ECPoint p = points[off + i];
            Nat224.copy(((SecP224R1FieldElement) p.getRawXCoord()).x, 0, table, pos);
            int pos2 = pos + 7;
            Nat224.copy(((SecP224R1FieldElement) p.getRawYCoord()).x, 0, table, pos2);
            pos = pos2 + 7;
        }
        return new ECLookupTable() {
            /* class com.android.org.bouncycastle.math.ec.custom.sec.SecP224R1Curve.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return len;
            }

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int index) {
                int[] x = Nat224.create();
                int[] y = Nat224.create();
                int pos = 0;
                for (int i = 0; i < len; i++) {
                    int MASK = ((i ^ index) - 1) >> 31;
                    for (int j = 0; j < 7; j++) {
                        int i2 = x[j];
                        int[] iArr = table;
                        x[j] = i2 ^ (iArr[pos + j] & MASK);
                        y[j] = y[j] ^ (iArr[(pos + 7) + j] & MASK);
                    }
                    pos += 14;
                }
                return SecP224R1Curve.this.createRawPoint(new SecP224R1FieldElement(x), new SecP224R1FieldElement(y), false);
            }
        };
    }
}
