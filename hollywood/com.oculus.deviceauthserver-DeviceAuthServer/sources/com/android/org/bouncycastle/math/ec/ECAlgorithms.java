package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.endo.ECEndomorphism;
import com.android.org.bouncycastle.math.ec.endo.GLVEndomorphism;
import com.android.org.bouncycastle.math.field.FiniteField;
import com.android.org.bouncycastle.math.field.PolynomialExtensionField;
import com.google.common.base.Ascii;
import java.math.BigInteger;

public class ECAlgorithms {
    public static boolean isF2mCurve(ECCurve c) {
        return isF2mField(c.getField());
    }

    public static boolean isF2mField(FiniteField field) {
        return field.getDimension() > 1 && field.getCharacteristic().equals(ECConstants.TWO) && (field instanceof PolynomialExtensionField);
    }

    public static boolean isFpCurve(ECCurve c) {
        return isFpField(c.getField());
    }

    public static boolean isFpField(FiniteField field) {
        return field.getDimension() == 1;
    }

    public static ECPoint sumOfMultiplies(ECPoint[] ps, BigInteger[] ks) {
        if (ps == null || ks == null || ps.length != ks.length || ps.length < 1) {
            throw new IllegalArgumentException("point and scalar arrays should be non-null, and of equal, non-zero, length");
        }
        int count = ps.length;
        if (count == 1) {
            return ps[0].multiply(ks[0]);
        }
        if (count == 2) {
            return sumOfTwoMultiplies(ps[0], ks[0], ps[1], ks[1]);
        }
        ECPoint p = ps[0];
        ECCurve c = p.getCurve();
        ECPoint[] imported = new ECPoint[count];
        imported[0] = p;
        for (int i = 1; i < count; i++) {
            imported[i] = importPoint(c, ps[i]);
        }
        ECEndomorphism endomorphism = c.getEndomorphism();
        if (endomorphism instanceof GLVEndomorphism) {
            return implCheckResult(implSumOfMultipliesGLV(imported, ks, (GLVEndomorphism) endomorphism));
        }
        return implCheckResult(implSumOfMultiplies(imported, ks));
    }

    public static ECPoint sumOfTwoMultiplies(ECPoint P, BigInteger a, ECPoint Q, BigInteger b) {
        ECCurve cp = P.getCurve();
        ECPoint Q2 = importPoint(cp, Q);
        if ((cp instanceof ECCurve.AbstractF2m) && ((ECCurve.AbstractF2m) cp).isKoblitz()) {
            return implCheckResult(P.multiply(a).add(Q2.multiply(b)));
        }
        ECEndomorphism endomorphism = cp.getEndomorphism();
        if (!(endomorphism instanceof GLVEndomorphism)) {
            return implCheckResult(implShamirsTrickWNaf(P, a, Q2, b));
        }
        return implCheckResult(implSumOfMultipliesGLV(new ECPoint[]{P, Q2}, new BigInteger[]{a, b}, (GLVEndomorphism) endomorphism));
    }

    public static ECPoint shamirsTrick(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        return implCheckResult(implShamirsTrickJsf(P, k, importPoint(P.getCurve(), Q), l));
    }

    public static ECPoint importPoint(ECCurve c, ECPoint p) {
        if (c.equals(p.getCurve())) {
            return c.importPoint(p);
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    public static void montgomeryTrick(ECFieldElement[] zs, int off, int len) {
        montgomeryTrick(zs, off, len, null);
    }

    /* JADX INFO: Multiple debug info for r1v6 int: [D('i' int), D('j' int)] */
    public static void montgomeryTrick(ECFieldElement[] zs, int off, int len, ECFieldElement scale) {
        ECFieldElement[] c = new ECFieldElement[len];
        c[0] = zs[off];
        int i = 0;
        while (true) {
            i++;
            if (i >= len) {
                break;
            }
            c[i] = c[i - 1].multiply(zs[off + i]);
        }
        int j = i - 1;
        if (scale != null) {
            c[j] = c[j].multiply(scale);
        }
        ECFieldElement u = c[j].invert();
        while (j > 0) {
            int i2 = j - 1;
            int j2 = j + off;
            ECFieldElement tmp = zs[j2];
            zs[j2] = c[i2].multiply(u);
            u = u.multiply(tmp);
            j = i2;
        }
        zs[off] = u;
    }

    public static ECPoint referenceMultiply(ECPoint p, BigInteger k) {
        BigInteger x = k.abs();
        ECPoint q = p.getCurve().getInfinity();
        int t = x.bitLength();
        if (t > 0) {
            if (x.testBit(0)) {
                q = p;
            }
            for (int i = 1; i < t; i++) {
                p = p.twice();
                if (x.testBit(i)) {
                    q = q.add(p);
                }
            }
        }
        return k.signum() < 0 ? q.negate() : q;
    }

    public static ECPoint validatePoint(ECPoint p) {
        if (p.isValid()) {
            return p;
        }
        throw new IllegalStateException("Invalid point");
    }

    public static ECPoint cleanPoint(ECCurve c, ECPoint p) {
        if (c.equals(p.getCurve())) {
            return c.decodePoint(p.getEncoded(false));
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    static ECPoint implCheckResult(ECPoint p) {
        if (p.isValidPartial()) {
            return p;
        }
        throw new IllegalStateException("Invalid result");
    }

    static ECPoint implShamirsTrickJsf(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        ECCurve curve = P.getCurve();
        ECPoint infinity = curve.getInfinity();
        ECPoint[] points = {Q, P.subtract(Q), P, P.add(Q)};
        curve.normalizeAll(points);
        ECPoint[] table = {points[3].negate(), points[2].negate(), points[1].negate(), points[0].negate(), infinity, points[0], points[1], points[2], points[3]};
        byte[] jsf = WNafUtil.generateJSF(k, l);
        ECPoint R = infinity;
        int i = jsf.length;
        while (true) {
            i--;
            if (i < 0) {
                return R;
            }
            byte b = jsf[i];
            R = R.twicePlus(table[(((b << Ascii.CAN) >> 28) * 3) + 4 + ((b << Ascii.FS) >> 28)]);
        }
    }

    static ECPoint implShamirsTrickWNaf(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        boolean negL = false;
        boolean negK = k.signum() < 0;
        if (l.signum() < 0) {
            negL = true;
        }
        BigInteger k2 = k.abs();
        BigInteger l2 = l.abs();
        int widthP = Math.max(2, Math.min(16, WNafUtil.getWindowSize(k2.bitLength())));
        int widthQ = Math.max(2, Math.min(16, WNafUtil.getWindowSize(l2.bitLength())));
        WNafPreCompInfo infoP = WNafUtil.precompute(P, widthP, true);
        WNafPreCompInfo infoQ = WNafUtil.precompute(Q, widthQ, true);
        return implShamirsTrickWNaf(negK ? infoP.getPreCompNeg() : infoP.getPreComp(), negK ? infoP.getPreComp() : infoP.getPreCompNeg(), WNafUtil.generateWindowNaf(widthP, k2), negL ? infoQ.getPreCompNeg() : infoQ.getPreComp(), negL ? infoQ.getPreComp() : infoQ.getPreCompNeg(), WNafUtil.generateWindowNaf(widthQ, l2));
    }

    static ECPoint implShamirsTrickWNaf(ECPoint P, BigInteger k, ECPointMap pointMapQ, BigInteger l) {
        boolean negL = false;
        boolean negK = k.signum() < 0;
        if (l.signum() < 0) {
            negL = true;
        }
        BigInteger k2 = k.abs();
        BigInteger l2 = l.abs();
        int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(k2.bitLength(), l2.bitLength()))));
        ECPoint Q = WNafUtil.mapPointWithPrecomp(P, width, true, pointMapQ);
        WNafPreCompInfo infoP = WNafUtil.getWNafPreCompInfo(P);
        WNafPreCompInfo infoQ = WNafUtil.getWNafPreCompInfo(Q);
        return implShamirsTrickWNaf(negK ? infoP.getPreCompNeg() : infoP.getPreComp(), negK ? infoP.getPreComp() : infoP.getPreCompNeg(), WNafUtil.generateWindowNaf(width, k2), negL ? infoQ.getPreCompNeg() : infoQ.getPreComp(), negL ? infoQ.getPreComp() : infoQ.getPreCompNeg(), WNafUtil.generateWindowNaf(width, l2));
    }

    private static ECPoint implShamirsTrickWNaf(ECPoint[] preCompP, ECPoint[] preCompNegP, byte[] wnafP, ECPoint[] preCompQ, ECPoint[] preCompNegQ, byte[] wnafQ) {
        int len = Math.max(wnafP.length, wnafQ.length);
        ECPoint infinity = preCompP[0].getCurve().getInfinity();
        ECPoint R = infinity;
        int zeroes = 0;
        int i = len - 1;
        while (i >= 0) {
            byte b = i < wnafP.length ? wnafP[i] : 0;
            byte b2 = i < wnafQ.length ? wnafQ[i] : 0;
            if ((b | b2) == 0) {
                zeroes++;
            } else {
                ECPoint r = infinity;
                if (b != 0) {
                    r = r.add((b < 0 ? preCompNegP : preCompP)[Math.abs((int) b) >>> 1]);
                }
                if (b2 != 0) {
                    r = r.add((b2 < 0 ? preCompNegQ : preCompQ)[Math.abs((int) b2) >>> 1]);
                }
                if (zeroes > 0) {
                    R = R.timesPow2(zeroes);
                    zeroes = 0;
                }
                R = R.twicePlus(r);
            }
            i--;
        }
        if (zeroes > 0) {
            return R.timesPow2(zeroes);
        }
        return R;
    }

    static ECPoint implSumOfMultiplies(ECPoint[] ps, BigInteger[] ks) {
        int count = ps.length;
        boolean[] negs = new boolean[count];
        WNafPreCompInfo[] infos = new WNafPreCompInfo[count];
        byte[][] wnafs = new byte[count][];
        for (int i = 0; i < count; i++) {
            BigInteger ki = ks[i];
            negs[i] = ki.signum() < 0;
            BigInteger ki2 = ki.abs();
            int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(ki2.bitLength())));
            infos[i] = WNafUtil.precompute(ps[i], width, true);
            wnafs[i] = WNafUtil.generateWindowNaf(width, ki2);
        }
        return implSumOfMultiplies(negs, infos, wnafs);
    }

    static ECPoint implSumOfMultipliesGLV(ECPoint[] ps, BigInteger[] ks, GLVEndomorphism glvEndomorphism) {
        BigInteger n = ps[0].getCurve().getOrder();
        int len = ps.length;
        BigInteger[] abs = new BigInteger[(len << 1)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            BigInteger[] ab = glvEndomorphism.decomposeScalar(ks[i].mod(n));
            int j2 = j + 1;
            abs[j] = ab[0];
            j = j2 + 1;
            abs[j2] = ab[1];
        }
        ECPointMap pointMap = glvEndomorphism.getPointMap();
        if (glvEndomorphism.hasEfficientPointMap()) {
            return implSumOfMultiplies(ps, pointMap, abs);
        }
        ECPoint[] pqs = new ECPoint[(len << 1)];
        int j3 = 0;
        for (ECPoint p : ps) {
            ECPoint q = pointMap.map(p);
            int j4 = j3 + 1;
            pqs[j3] = p;
            j3 = j4 + 1;
            pqs[j4] = q;
        }
        return implSumOfMultiplies(pqs, abs);
    }

    static ECPoint implSumOfMultiplies(ECPoint[] ps, ECPointMap pointMap, BigInteger[] ks) {
        int halfCount = ps.length;
        int fullCount = halfCount << 1;
        boolean[] negs = new boolean[fullCount];
        WNafPreCompInfo[] infos = new WNafPreCompInfo[fullCount];
        byte[][] wnafs = new byte[fullCount][];
        for (int i = 0; i < halfCount; i++) {
            int j0 = i << 1;
            int j1 = j0 + 1;
            BigInteger kj0 = ks[j0];
            boolean z = false;
            negs[j0] = kj0.signum() < 0;
            BigInteger kj02 = kj0.abs();
            BigInteger kj1 = ks[j1];
            if (kj1.signum() < 0) {
                z = true;
            }
            negs[j1] = z;
            BigInteger kj12 = kj1.abs();
            int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(kj02.bitLength(), kj12.bitLength()))));
            ECPoint P = ps[i];
            ECPoint Q = WNafUtil.mapPointWithPrecomp(P, width, true, pointMap);
            infos[j0] = WNafUtil.getWNafPreCompInfo(P);
            infos[j1] = WNafUtil.getWNafPreCompInfo(Q);
            wnafs[j0] = WNafUtil.generateWindowNaf(width, kj02);
            wnafs[j1] = WNafUtil.generateWindowNaf(width, kj12);
        }
        return implSumOfMultiplies(negs, infos, wnafs);
    }

    private static ECPoint implSumOfMultiplies(boolean[] negs, WNafPreCompInfo[] infos, byte[][] wnafs) {
        int len = 0;
        int count = wnafs.length;
        for (byte[] bArr : wnafs) {
            len = Math.max(len, bArr.length);
        }
        byte b = 0;
        ECPoint infinity = infos[0].getPreComp()[0].getCurve().getInfinity();
        ECPoint R = infinity;
        int zeroes = 0;
        int i = len - 1;
        while (i >= 0) {
            ECPoint r = infinity;
            int j = 0;
            while (j < count) {
                byte[] wnaf = wnafs[j];
                byte b2 = i < wnaf.length ? wnaf[i] : b;
                if (b2 != 0) {
                    int n = Math.abs((int) b2);
                    WNafPreCompInfo info = infos[j];
                    r = r.add(((b2 < 0 ? 1 : b) == negs[j] ? info.getPreComp() : info.getPreCompNeg())[n >>> 1]);
                }
                j++;
                b = 0;
            }
            if (r == infinity) {
                zeroes++;
            } else {
                if (zeroes > 0) {
                    R = R.timesPow2(zeroes);
                    zeroes = 0;
                }
                R = R.twicePlus(r);
            }
            i--;
            b = 0;
        }
        if (zeroes > 0) {
            return R.timesPow2(zeroes);
        }
        return R;
    }
}
