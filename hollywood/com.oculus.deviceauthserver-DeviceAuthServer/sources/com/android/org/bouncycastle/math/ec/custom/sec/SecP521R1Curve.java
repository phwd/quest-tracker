package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECLookupTable;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;

public class SecP521R1Curve extends ECCurve.AbstractFp {
    private static final int SecP521R1_DEFAULT_COORDS = 2;
    public static final BigInteger q = new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));
    protected SecP521R1Point infinity = new SecP521R1Point(this, null, null);

    public SecP521R1Curve() {
        super(q);
        this.a = fromBigInteger(new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decode("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00")));
        this.order = new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP521R1Curve();
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
        return new SecP521R1FieldElement(x);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecP521R1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecP521R1Point(this, x, y, zs, withCompression);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
        final int[] table = new int[(len * 17 * 2)];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            ECPoint p = points[off + i];
            Nat.copy(17, ((SecP521R1FieldElement) p.getRawXCoord()).x, 0, table, pos);
            int pos2 = pos + 17;
            Nat.copy(17, ((SecP521R1FieldElement) p.getRawYCoord()).x, 0, table, pos2);
            pos = pos2 + 17;
        }
        return new ECLookupTable() {
            /* class com.android.org.bouncycastle.math.ec.custom.sec.SecP521R1Curve.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return len;
            }

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int index) {
                int[] x = Nat.create(17);
                int[] y = Nat.create(17);
                int pos = 0;
                for (int i = 0; i < len; i++) {
                    int MASK = ((i ^ index) - 1) >> 31;
                    for (int j = 0; j < 17; j++) {
                        int i2 = x[j];
                        int[] iArr = table;
                        x[j] = i2 ^ (iArr[pos + j] & MASK);
                        y[j] = y[j] ^ (iArr[(pos + 17) + j] & MASK);
                    }
                    pos += 34;
                }
                return SecP521R1Curve.this.createRawPoint(new SecP521R1FieldElement(x), new SecP521R1FieldElement(y), false);
            }
        };
    }
}
