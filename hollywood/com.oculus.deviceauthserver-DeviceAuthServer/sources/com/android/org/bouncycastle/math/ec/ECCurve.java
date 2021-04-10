package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.ec.endo.ECEndomorphism;
import com.android.org.bouncycastle.math.ec.endo.GLVEndomorphism;
import com.android.org.bouncycastle.math.field.FiniteField;
import com.android.org.bouncycastle.math.field.FiniteFields;
import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.util.BigIntegers;
import com.android.org.bouncycastle.util.Integers;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Random;

public abstract class ECCurve {
    public static final int COORD_AFFINE = 0;
    public static final int COORD_HOMOGENEOUS = 1;
    public static final int COORD_JACOBIAN = 2;
    public static final int COORD_JACOBIAN_CHUDNOVSKY = 3;
    public static final int COORD_JACOBIAN_MODIFIED = 4;
    public static final int COORD_LAMBDA_AFFINE = 5;
    public static final int COORD_LAMBDA_PROJECTIVE = 6;
    public static final int COORD_SKEWED = 7;
    protected ECFieldElement a;
    protected ECFieldElement b;
    protected BigInteger cofactor;
    protected int coord = 0;
    protected ECEndomorphism endomorphism = null;
    protected FiniteField field;
    protected ECMultiplier multiplier = null;
    protected BigInteger order;

    /* access modifiers changed from: protected */
    public abstract ECCurve cloneCurve();

    /* access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z);

    /* access modifiers changed from: protected */
    public abstract ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z);

    /* access modifiers changed from: protected */
    public abstract ECPoint decompressPoint(int i, BigInteger bigInteger);

    public abstract ECFieldElement fromBigInteger(BigInteger bigInteger);

    public abstract int getFieldSize();

    public abstract ECPoint getInfinity();

    public abstract boolean isValidFieldElement(BigInteger bigInteger);

    public static int[] getAllCoordinateSystems() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    public class Config {
        protected int coord;
        protected ECEndomorphism endomorphism;
        protected ECMultiplier multiplier;

        Config(int coord2, ECEndomorphism endomorphism2, ECMultiplier multiplier2) {
            this.coord = coord2;
            this.endomorphism = endomorphism2;
            this.multiplier = multiplier2;
        }

        public Config setCoordinateSystem(int coord2) {
            this.coord = coord2;
            return this;
        }

        public Config setEndomorphism(ECEndomorphism endomorphism2) {
            this.endomorphism = endomorphism2;
            return this;
        }

        public Config setMultiplier(ECMultiplier multiplier2) {
            this.multiplier = multiplier2;
            return this;
        }

        public ECCurve create() {
            if (ECCurve.this.supportsCoordinateSystem(this.coord)) {
                ECCurve c = ECCurve.this.cloneCurve();
                if (c != ECCurve.this) {
                    synchronized (c) {
                        c.coord = this.coord;
                        c.endomorphism = this.endomorphism;
                        c.multiplier = this.multiplier;
                    }
                    return c;
                }
                throw new IllegalStateException("implementation returned current curve");
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    protected ECCurve(FiniteField field2) {
        this.field = field2;
    }

    public synchronized Config configure() {
        return new Config(this.coord, this.endomorphism, this.multiplier);
    }

    public ECPoint validatePoint(BigInteger x, BigInteger y) {
        ECPoint p = createPoint(x, y);
        if (p.isValid()) {
            return p;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public ECPoint validatePoint(BigInteger x, BigInteger y, boolean withCompression) {
        ECPoint p = createPoint(x, y, withCompression);
        if (p.isValid()) {
            return p;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public ECPoint createPoint(BigInteger x, BigInteger y) {
        return createPoint(x, y, false);
    }

    public ECPoint createPoint(BigInteger x, BigInteger y, boolean withCompression) {
        return createRawPoint(fromBigInteger(x), fromBigInteger(y), withCompression);
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createDefaultMultiplier() {
        ECEndomorphism eCEndomorphism = this.endomorphism;
        if (eCEndomorphism instanceof GLVEndomorphism) {
            return new GLVMultiplier(this, (GLVEndomorphism) eCEndomorphism);
        }
        return new WNafL2RMultiplier();
    }

    public boolean supportsCoordinateSystem(int coord2) {
        return coord2 == 0;
    }

    public PreCompInfo getPreCompInfo(ECPoint point, String name) {
        Hashtable table;
        PreCompInfo preCompInfo;
        checkPoint(point);
        synchronized (point) {
            table = point.preCompTable;
        }
        if (table == null) {
            return null;
        }
        synchronized (table) {
            preCompInfo = (PreCompInfo) table.get(name);
        }
        return preCompInfo;
    }

    public PreCompInfo precompute(ECPoint point, String name, PreCompCallback callback) {
        Hashtable table;
        PreCompInfo result;
        checkPoint(point);
        synchronized (point) {
            table = point.preCompTable;
            if (table == null) {
                Hashtable hashtable = new Hashtable(4);
                table = hashtable;
                point.preCompTable = hashtable;
            }
        }
        synchronized (table) {
            PreCompInfo existing = (PreCompInfo) table.get(name);
            result = callback.precompute(existing);
            if (result != existing) {
                table.put(name, result);
            }
        }
        return result;
    }

    public ECPoint importPoint(ECPoint p) {
        if (this == p.getCurve()) {
            return p;
        }
        if (p.isInfinity()) {
            return getInfinity();
        }
        ECPoint p2 = p.normalize();
        return createPoint(p2.getXCoord().toBigInteger(), p2.getYCoord().toBigInteger(), p2.withCompression);
    }

    public void normalizeAll(ECPoint[] points) {
        normalizeAll(points, 0, points.length, null);
    }

    public void normalizeAll(ECPoint[] points, int off, int len, ECFieldElement iso) {
        checkPoints(points, off, len);
        int coordinateSystem = getCoordinateSystem();
        if (coordinateSystem != 0 && coordinateSystem != 5) {
            ECFieldElement[] zs = new ECFieldElement[len];
            int[] indices = new int[len];
            int count = 0;
            for (int i = 0; i < len; i++) {
                ECPoint p = points[off + i];
                if (p != null && (iso != null || !p.isNormalized())) {
                    zs[count] = p.getZCoord(0);
                    indices[count] = off + i;
                    count++;
                }
            }
            if (count != 0) {
                ECAlgorithms.montgomeryTrick(zs, 0, count, iso);
                for (int j = 0; j < count; j++) {
                    int index = indices[j];
                    points[index] = points[index].normalize(zs[j]);
                }
            }
        } else if (iso != null) {
            throw new IllegalArgumentException("'iso' not valid for affine coordinates");
        }
    }

    public FiniteField getField() {
        return this.field;
    }

    public ECFieldElement getA() {
        return this.a;
    }

    public ECFieldElement getB() {
        return this.b;
    }

    public BigInteger getOrder() {
        return this.order;
    }

    public BigInteger getCofactor() {
        return this.cofactor;
    }

    public int getCoordinateSystem() {
        return this.coord;
    }

    public ECEndomorphism getEndomorphism() {
        return this.endomorphism;
    }

    public synchronized ECMultiplier getMultiplier() {
        if (this.multiplier == null) {
            this.multiplier = createDefaultMultiplier();
        }
        return this.multiplier;
    }

    public ECPoint decodePoint(byte[] encoded) {
        ECPoint p;
        int expectedLength = (getFieldSize() + 7) / 8;
        boolean z = false;
        byte type = encoded[0];
        if (type != 0) {
            if (type == 2 || type == 3) {
                if (encoded.length == expectedLength + 1) {
                    p = decompressPoint(type & 1, BigIntegers.fromUnsignedByteArray(encoded, 1, expectedLength));
                    if (!p.implIsValid(true, true)) {
                        throw new IllegalArgumentException("Invalid point");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
            } else if (type != 4) {
                if (type != 6 && type != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(type, 16));
                } else if (encoded.length == (expectedLength * 2) + 1) {
                    BigInteger X = BigIntegers.fromUnsignedByteArray(encoded, 1, expectedLength);
                    BigInteger Y = BigIntegers.fromUnsignedByteArray(encoded, expectedLength + 1, expectedLength);
                    boolean testBit = Y.testBit(0);
                    if (type == 7) {
                        z = true;
                    }
                    if (testBit == z) {
                        p = validatePoint(X, Y);
                    } else {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
            } else if (encoded.length == (expectedLength * 2) + 1) {
                p = validatePoint(BigIntegers.fromUnsignedByteArray(encoded, 1, expectedLength), BigIntegers.fromUnsignedByteArray(encoded, expectedLength + 1, expectedLength));
            } else {
                throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
            }
        } else if (encoded.length == 1) {
            p = getInfinity();
        } else {
            throw new IllegalArgumentException("Incorrect length for infinity encoding");
        }
        if (type == 0 || !p.isInfinity()) {
            return p;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
        final int FE_BYTES = (getFieldSize() + 7) >>> 3;
        final byte[] table = new byte[(len * FE_BYTES * 2)];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            ECPoint p = points[off + i];
            byte[] px = p.getRawXCoord().toBigInteger().toByteArray();
            byte[] py = p.getRawYCoord().toBigInteger().toByteArray();
            int pyStart = 0;
            int pxStart = px.length > FE_BYTES ? 1 : 0;
            int pxLen = px.length - pxStart;
            if (py.length > FE_BYTES) {
                pyStart = 1;
            }
            int pyLen = py.length - pyStart;
            System.arraycopy(px, pxStart, table, (pos + FE_BYTES) - pxLen, pxLen);
            int pos2 = pos + FE_BYTES;
            System.arraycopy(py, pyStart, table, (pos2 + FE_BYTES) - pyLen, pyLen);
            pos = pos2 + FE_BYTES;
        }
        return new ECLookupTable() {
            /* class com.android.org.bouncycastle.math.ec.ECCurve.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public int getSize() {
                return len;
            }

            @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
            public ECPoint lookup(int index) {
                int i;
                int i2 = FE_BYTES;
                byte[] x = new byte[i2];
                byte[] y = new byte[i2];
                int pos = 0;
                for (int i3 = 0; i3 < len; i3++) {
                    int MASK = ((i3 ^ index) - 1) >> 31;
                    int j = 0;
                    while (true) {
                        i = FE_BYTES;
                        if (j >= i) {
                            break;
                        }
                        byte b = x[j];
                        byte[] bArr = table;
                        x[j] = (byte) (b ^ (bArr[pos + j] & MASK));
                        y[j] = (byte) ((bArr[(i + pos) + j] & MASK) ^ y[j]);
                        j++;
                    }
                    pos += i * 2;
                }
                ECCurve eCCurve = ECCurve.this;
                return eCCurve.createRawPoint(eCCurve.fromBigInteger(new BigInteger(1, x)), ECCurve.this.fromBigInteger(new BigInteger(1, y)), false);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void checkPoint(ECPoint point) {
        if (point == null || this != point.getCurve()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    /* access modifiers changed from: protected */
    public void checkPoints(ECPoint[] points) {
        checkPoints(points, 0, points.length);
    }

    /* access modifiers changed from: protected */
    public void checkPoints(ECPoint[] points, int off, int len) {
        if (points == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        } else if (off < 0 || len < 0 || off > points.length - len) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        } else {
            for (int i = 0; i < len; i++) {
                ECPoint point = points[off + i];
                if (point != null && this != point.getCurve()) {
                    throw new IllegalArgumentException("'points' entries must be null or on this curve");
                }
            }
        }
    }

    public boolean equals(ECCurve other) {
        return this == other || (other != null && getField().equals(other.getField()) && getA().toBigInteger().equals(other.getA().toBigInteger()) && getB().toBigInteger().equals(other.getB().toBigInteger()));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ECCurve) && equals((ECCurve) obj));
    }

    public int hashCode() {
        return (getField().hashCode() ^ Integers.rotateLeft(getA().toBigInteger().hashCode(), 8)) ^ Integers.rotateLeft(getB().toBigInteger().hashCode(), 16);
    }

    public static abstract class AbstractFp extends ECCurve {
        protected AbstractFp(BigInteger q) {
            super(FiniteFields.getPrimeField(q));
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger x) {
            return x != null && x.signum() >= 0 && x.compareTo(getField().getCharacteristic()) < 0;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint decompressPoint(int yTilde, BigInteger X1) {
            ECFieldElement x = fromBigInteger(X1);
            ECFieldElement y = x.square().add(this.a).multiply(x).add(this.b).sqrt();
            if (y != null) {
                if (y.testBitZero() != (yTilde == 1)) {
                    y = y.negate();
                }
                return createRawPoint(x, y, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }
    }

    public static class Fp extends AbstractFp {
        private static final int FP_DEFAULT_COORDS = 4;
        ECPoint.Fp infinity;
        BigInteger q;
        BigInteger r;

        public Fp(BigInteger q2, BigInteger a, BigInteger b) {
            this(q2, a, b, null, null);
        }

        public Fp(BigInteger q2, BigInteger a, BigInteger b, BigInteger order, BigInteger cofactor) {
            super(q2);
            this.q = q2;
            this.r = ECFieldElement.Fp.calculateResidue(q2);
            this.infinity = new ECPoint.Fp(this, null, null, false);
            this.a = fromBigInteger(a);
            this.b = fromBigInteger(b);
            this.order = order;
            this.cofactor = cofactor;
            this.coord = 4;
        }

        protected Fp(BigInteger q2, BigInteger r2, ECFieldElement a, ECFieldElement b) {
            this(q2, r2, a, b, null, null);
        }

        protected Fp(BigInteger q2, BigInteger r2, ECFieldElement a, ECFieldElement b, BigInteger order, BigInteger cofactor) {
            super(q2);
            this.q = q2;
            this.r = r2;
            this.infinity = new ECPoint.Fp(this, null, null, false);
            this.a = a;
            this.b = b;
            this.order = order;
            this.cofactor = cofactor;
            this.coord = 4;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECCurve cloneCurve() {
            return new Fp(this.q, this.r, this.a, this.b, this.order, this.cofactor);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int coord) {
            if (coord == 0 || coord == 1 || coord == 2 || coord == 4) {
                return true;
            }
            return false;
        }

        public BigInteger getQ() {
            return this.q;
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.q.bitLength();
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger x) {
            return new ECFieldElement.Fp(this.q, this.r, x);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
            return new ECPoint.Fp(this, x, y, withCompression);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            return new ECPoint.Fp(this, x, y, zs, withCompression);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint importPoint(ECPoint p) {
            int coordinateSystem;
            if (this == p.getCurve() || getCoordinateSystem() != 2 || p.isInfinity() || ((coordinateSystem = p.getCurve().getCoordinateSystem()) != 2 && coordinateSystem != 3 && coordinateSystem != 4)) {
                return super.importPoint(p);
            }
            return new ECPoint.Fp(this, fromBigInteger(p.x.toBigInteger()), fromBigInteger(p.y.toBigInteger()), new ECFieldElement[]{fromBigInteger(p.zs[0].toBigInteger())}, p.withCompression);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }
    }

    public static abstract class AbstractF2m extends ECCurve {
        private BigInteger[] si = null;

        public static BigInteger inverse(int m, int[] ks, BigInteger x) {
            return new LongArray(x).modInverse(m, ks).toBigInteger();
        }

        private static FiniteField buildField(int m, int k1, int k2, int k3) {
            if (k1 == 0) {
                throw new IllegalArgumentException("k1 must be > 0");
            } else if (k2 == 0) {
                if (k3 == 0) {
                    return FiniteFields.getBinaryExtensionField(new int[]{0, k1, m});
                }
                throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
            } else if (k2 <= k1) {
                throw new IllegalArgumentException("k2 must be > k1");
            } else if (k3 > k2) {
                return FiniteFields.getBinaryExtensionField(new int[]{0, k1, k2, k3, m});
            } else {
                throw new IllegalArgumentException("k3 must be > k2");
            }
        }

        protected AbstractF2m(int m, int k1, int k2, int k3) {
            super(buildField(m, k1, k2, k3));
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public boolean isValidFieldElement(BigInteger x) {
            return x != null && x.signum() >= 0 && x.bitLength() <= getFieldSize();
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint createPoint(BigInteger x, BigInteger y, boolean withCompression) {
            ECFieldElement X = fromBigInteger(x);
            ECFieldElement Y = fromBigInteger(y);
            int coord = getCoordinateSystem();
            if (coord == 5 || coord == 6) {
                if (!X.isZero()) {
                    Y = Y.divide(X).add(X);
                } else if (!Y.square().equals(getB())) {
                    throw new IllegalArgumentException();
                }
            }
            return createRawPoint(X, Y, withCompression);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint decompressPoint(int yTilde, BigInteger X1) {
            ECFieldElement x = fromBigInteger(X1);
            ECFieldElement y = null;
            if (x.isZero()) {
                y = getB().sqrt();
            } else {
                ECFieldElement z = solveQuadraticEquation(x.square().invert().multiply(getB()).add(getA()).add(x));
                if (z != null) {
                    if (z.testBitZero() != (yTilde == 1)) {
                        z = z.addOne();
                    }
                    int coordinateSystem = getCoordinateSystem();
                    if (coordinateSystem == 5 || coordinateSystem == 6) {
                        y = z.add(x);
                    } else {
                        y = z.multiply(x);
                    }
                }
            }
            if (y != null) {
                return createRawPoint(x, y, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        /* JADX INFO: Multiple debug info for r3v2 com.android.org.bouncycastle.math.ec.ECFieldElement: [D('t' com.android.org.bouncycastle.math.ec.ECFieldElement), D('gamma' com.android.org.bouncycastle.math.ec.ECFieldElement)] */
        /* access modifiers changed from: protected */
        public ECFieldElement solveQuadraticEquation(ECFieldElement beta) {
            ECFieldElement z;
            if (beta.isZero()) {
                return beta;
            }
            ECFieldElement zeroElement = fromBigInteger(ECConstants.ZERO);
            int m = getFieldSize();
            Random rand = new Random();
            do {
                ECFieldElement t = fromBigInteger(new BigInteger(m, rand));
                z = zeroElement;
                ECFieldElement w = beta;
                for (int i = 1; i < m; i++) {
                    ECFieldElement w2 = w.square();
                    z = z.square().add(w2.multiply(t));
                    w = w2.add(beta);
                }
                if (!w.isZero()) {
                    return null;
                }
            } while (z.square().add(z).isZero());
            return z;
        }

        /* access modifiers changed from: package-private */
        public synchronized BigInteger[] getSi() {
            if (this.si == null) {
                this.si = Tnaf.getSi(this);
            }
            return this.si;
        }

        public boolean isKoblitz() {
            return this.order != null && this.cofactor != null && this.b.isOne() && (this.a.isZero() || this.a.isOne());
        }
    }

    public static class F2m extends AbstractF2m {
        private static final int F2M_DEFAULT_COORDS = 6;
        private ECPoint.F2m infinity;
        private int k1;
        private int k2;
        private int k3;
        private int m;

        public F2m(int m2, int k, BigInteger a, BigInteger b) {
            this(m2, k, 0, 0, a, b, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int m2, int k, BigInteger a, BigInteger b, BigInteger order, BigInteger cofactor) {
            this(m2, k, 0, 0, a, b, order, cofactor);
        }

        public F2m(int m2, int k12, int k22, int k32, BigInteger a, BigInteger b) {
            this(m2, k12, k22, k32, a, b, (BigInteger) null, (BigInteger) null);
        }

        public F2m(int m2, int k12, int k22, int k32, BigInteger a, BigInteger b, BigInteger order, BigInteger cofactor) {
            super(m2, k12, k22, k32);
            this.m = m2;
            this.k1 = k12;
            this.k2 = k22;
            this.k3 = k32;
            this.order = order;
            this.cofactor = cofactor;
            this.infinity = new ECPoint.F2m(this, null, null, false);
            this.a = fromBigInteger(a);
            this.b = fromBigInteger(b);
            this.coord = 6;
        }

        protected F2m(int m2, int k12, int k22, int k32, ECFieldElement a, ECFieldElement b, BigInteger order, BigInteger cofactor) {
            super(m2, k12, k22, k32);
            this.m = m2;
            this.k1 = k12;
            this.k2 = k22;
            this.k3 = k32;
            this.order = order;
            this.cofactor = cofactor;
            this.infinity = new ECPoint.F2m(this, null, null, false);
            this.a = a;
            this.b = b;
            this.coord = 6;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECCurve cloneCurve() {
            return new F2m(this.m, this.k1, this.k2, this.k3, this.a, this.b, this.order, this.cofactor);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public boolean supportsCoordinateSystem(int coord) {
            if (coord == 0 || coord == 1 || coord == 6) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECMultiplier createDefaultMultiplier() {
            if (isKoblitz()) {
                return new WTauNafMultiplier();
            }
            return super.createDefaultMultiplier();
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public int getFieldSize() {
            return this.m;
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECFieldElement fromBigInteger(BigInteger x) {
            return new ECFieldElement.F2m(this.m, this.k1, this.k2, this.k3, x);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, boolean withCompression) {
            return new ECPoint.F2m(this, x, y, withCompression);
        }

        /* access modifiers changed from: protected */
        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint createRawPoint(ECFieldElement x, ECFieldElement y, ECFieldElement[] zs, boolean withCompression) {
            return new ECPoint.F2m(this, x, y, zs, withCompression);
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECPoint getInfinity() {
            return this.infinity;
        }

        public int getM() {
            return this.m;
        }

        public boolean isTrinomial() {
            return this.k2 == 0 && this.k3 == 0;
        }

        public int getK1() {
            return this.k1;
        }

        public int getK2() {
            return this.k2;
        }

        public int getK3() {
            return this.k3;
        }

        @Override // com.android.org.bouncycastle.math.ec.ECCurve
        public ECLookupTable createCacheSafeLookupTable(ECPoint[] points, int off, final int len) {
            final int FE_LONGS = (this.m + 63) >>> 6;
            final int[] ks = isTrinomial() ? new int[]{this.k1} : new int[]{this.k1, this.k2, this.k3};
            final long[] table = new long[(len * FE_LONGS * 2)];
            int pos = 0;
            for (int i = 0; i < len; i++) {
                ECPoint p = points[off + i];
                ((ECFieldElement.F2m) p.getRawXCoord()).x.copyTo(table, pos);
                int pos2 = pos + FE_LONGS;
                ((ECFieldElement.F2m) p.getRawYCoord()).x.copyTo(table, pos2);
                pos = pos2 + FE_LONGS;
            }
            return new ECLookupTable() {
                /* class com.android.org.bouncycastle.math.ec.ECCurve.F2m.AnonymousClass1 */

                @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
                public int getSize() {
                    return len;
                }

                @Override // com.android.org.bouncycastle.math.ec.ECLookupTable
                public ECPoint lookup(int index) {
                    int i;
                    long[] x = Nat.create64(FE_LONGS);
                    long[] y = Nat.create64(FE_LONGS);
                    int pos = 0;
                    for (int i2 = 0; i2 < len; i2++) {
                        long MASK = (long) (((i2 ^ index) - 1) >> 31);
                        int j = 0;
                        while (true) {
                            i = FE_LONGS;
                            if (j >= i) {
                                break;
                            }
                            long j2 = x[j];
                            long[] jArr = table;
                            x[j] = j2 ^ (jArr[pos + j] & MASK);
                            y[j] = y[j] ^ (jArr[(i + pos) + j] & MASK);
                            j++;
                        }
                        pos += i * 2;
                    }
                    F2m f2m = F2m.this;
                    return f2m.createRawPoint(new ECFieldElement.F2m(f2m.m, ks, new LongArray(x)), new ECFieldElement.F2m(F2m.this.m, ks, new LongArray(y)), false);
                }
            };
        }
    }
}
