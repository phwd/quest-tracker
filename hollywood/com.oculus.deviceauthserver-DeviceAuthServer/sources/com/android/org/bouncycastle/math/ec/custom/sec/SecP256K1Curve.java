package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.ec.ECConstants;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECLookupTable;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.raw.Nat256;
import com.android.org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;

public class SecP256K1Curve extends ECCurve.AbstractFp {
    private static final int SECP256K1_DEFAULT_COORDS = 2;
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F"));
    protected SecP256K1Point infinity = new SecP256K1Point(this, null, null);

    public SecP256K1Curve() {
        super(q);
        this.a = fromBigInteger(ECConstants.ZERO);
        this.b = fromBigInteger(BigInteger.valueOf(7));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP256K1Curve();
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
        return new SecP256K1FieldElement(x);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
        return new SecP256K1Point(this, x, y, withCompression);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
        return new SecP256K1Point(this, x, y, zs, withCompression);
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    @Override // com.android.org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
        final int[] table = new int[(len * 8 * 2)];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            ECPoint p = points[off + i];
            Nat256.copy(((SecP256K1FieldElement) p.getRawXCoord()).x, 0, table, pos);
            int pos2 = pos + 8;
            Nat256.copy(((SecP256K1FieldElement) p.getRawYCoord()).x, 0, table, pos2);
            pos = pos2 + 8;
        }
        return new ECLookupTable() {
            /* class com.android.org.bouncycastle.math.ec.custom.sec.SecP256K1Curve.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return len;
            }

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int index) {
                int[] x = Nat256.create();
                int[] y = Nat256.create();
                int pos = 0;
                for (int i = 0; i < len; i++) {
                    int MASK = ((i ^ index) - 1) >> 31;
                    for (int j = 0; j < 8; j++) {
                        int i2 = x[j];
                        int[] iArr = table;
                        x[j] = i2 ^ (iArr[pos + j] & MASK);
                        y[j] = y[j] ^ (iArr[(pos + 8) + j] & MASK);
                    }
                    pos += 16;
                }
                return SecP256K1Curve.this.createRawPoint(new SecP256K1FieldElement(x), new SecP256K1FieldElement(y), false);
            }
        };
    }
}
