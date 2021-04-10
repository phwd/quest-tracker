package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECLookupTable;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;

public class SecP384R1Curve extends ECCurve.AbstractFp {
    private static final int SecP384R1_DEFAULT_COORDS = 2;
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFF"));
    protected SecP384R1Point infinity = new SecP384R1Point(this, null, null);

    public SecP384R1Curve() {
        super(q);
        this.a = fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decode("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF")));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP384R1Curve();
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
        return new SecP384R1FieldElement(x);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecP384R1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecP384R1Point(this, x, y, zs, withCompression);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
        final int[] table = new int[(len * 12 * 2)];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            ECPoint p = points[off + i];
            Nat.copy(12, ((SecP384R1FieldElement) p.getRawXCoord()).x, 0, table, pos);
            int pos2 = pos + 12;
            Nat.copy(12, ((SecP384R1FieldElement) p.getRawYCoord()).x, 0, table, pos2);
            pos = pos2 + 12;
        }
        return new ECLookupTable() {
            /* class com.android.org.bouncycastle.math.ec.custom.sec.SecP384R1Curve.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return len;
            }

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int index) {
                int[] x = Nat.create(12);
                int[] y = Nat.create(12);
                int pos = 0;
                for (int i = 0; i < len; i++) {
                    int MASK = ((i ^ index) - 1) >> 31;
                    for (int j = 0; j < 12; j++) {
                        int i2 = x[j];
                        int[] iArr = table;
                        x[j] = i2 ^ (iArr[pos + j] & MASK);
                        y[j] = y[j] ^ (iArr[(pos + 12) + j] & MASK);
                    }
                    pos += 24;
                }
                return SecP384R1Curve.this.createRawPoint(new SecP384R1FieldElement(x), new SecP384R1FieldElement(y), false);
            }
        };
    }
}
