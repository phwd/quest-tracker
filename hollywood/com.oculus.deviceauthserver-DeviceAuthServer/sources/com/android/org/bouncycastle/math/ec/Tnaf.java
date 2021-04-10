package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;

/* access modifiers changed from: package-private */
public class Tnaf {
    private static final BigInteger MINUS_ONE = ECConstants.ONE.negate();
    private static final BigInteger MINUS_THREE = ECConstants.THREE.negate();
    private static final BigInteger MINUS_TWO = ECConstants.TWO.negate();
    public static final byte POW_2_WIDTH = 16;
    public static final byte WIDTH = 4;
    public static final ZTauElement[] alpha0;
    public static final byte[][] alpha0Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
    public static final ZTauElement[] alpha1 = {null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, ECConstants.ONE), null, new ZTauElement(MINUS_ONE, ECConstants.ONE), null, new ZTauElement(ECConstants.ONE, ECConstants.ONE), null};
    public static final byte[][] alpha1Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};

    Tnaf() {
    }

    static {
        BigInteger bigInteger = MINUS_ONE;
        alpha0 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, MINUS_ONE), null, new ZTauElement(bigInteger, bigInteger), null, new ZTauElement(ECConstants.ONE, MINUS_ONE), null};
    }

    public static BigInteger norm(byte mu, ZTauElement lambda) {
        BigInteger s1 = lambda.u.multiply(lambda.u);
        BigInteger s2 = lambda.u.multiply(lambda.v);
        BigInteger s3 = lambda.v.multiply(lambda.v).shiftLeft(1);
        if (mu == 1) {
            return s1.add(s2).add(s3);
        }
        if (mu == -1) {
            return s1.subtract(s2).add(s3);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static SimpleBigDecimal norm(byte mu, SimpleBigDecimal u, SimpleBigDecimal v) {
        SimpleBigDecimal s1 = u.multiply(u);
        SimpleBigDecimal s2 = u.multiply(v);
        SimpleBigDecimal s3 = v.multiply(v).shiftLeft(1);
        if (mu == 1) {
            return s1.add(s2).add(s3);
        }
        if (mu == -1) {
            return s1.subtract(s2).add(s3);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static ZTauElement round(SimpleBigDecimal lambda0, SimpleBigDecimal lambda1, byte mu) {
        SimpleBigDecimal eta;
        SimpleBigDecimal check2;
        SimpleBigDecimal check1;
        if (lambda1.getScale() != lambda0.getScale()) {
            throw new IllegalArgumentException("lambda0 and lambda1 do not have same scale");
        } else if (mu == 1 || mu == -1) {
            BigInteger f0 = lambda0.round();
            BigInteger f1 = lambda1.round();
            SimpleBigDecimal eta0 = lambda0.subtract(f0);
            SimpleBigDecimal eta1 = lambda1.subtract(f1);
            SimpleBigDecimal eta2 = eta0.add(eta0);
            if (mu == 1) {
                eta = eta2.add(eta1);
            } else {
                eta = eta2.subtract(eta1);
            }
            SimpleBigDecimal threeEta1 = eta1.add(eta1).add(eta1);
            SimpleBigDecimal fourEta1 = threeEta1.add(eta1);
            if (mu == 1) {
                check1 = eta0.subtract(threeEta1);
                check2 = eta0.add(fourEta1);
            } else {
                check1 = eta0.add(threeEta1);
                check2 = eta0.subtract(fourEta1);
            }
            byte h0 = 0;
            byte h1 = 0;
            if (eta.compareTo(ECConstants.ONE) >= 0) {
                if (check1.compareTo(MINUS_ONE) < 0) {
                    h1 = mu;
                } else {
                    h0 = 1;
                }
            } else if (check2.compareTo(ECConstants.TWO) >= 0) {
                h1 = mu;
            }
            if (eta.compareTo(MINUS_ONE) < 0) {
                if (check1.compareTo(ECConstants.ONE) >= 0) {
                    h1 = (byte) (-mu);
                } else {
                    h0 = -1;
                }
            } else if (check2.compareTo(MINUS_TWO) < 0) {
                h1 = (byte) (-mu);
            }
            return new ZTauElement(f0.add(BigInteger.valueOf((long) h0)), f1.add(BigInteger.valueOf((long) h1)));
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger k, BigInteger s, BigInteger vm, byte a, int m, int c) {
        int _k = ((m + 5) / 2) + c;
        BigInteger gs = s.multiply(k.shiftRight(((m - _k) - 2) + a));
        BigInteger gsPlusJs = gs.add(vm.multiply(gs.shiftRight(m)));
        BigInteger ls = gsPlusJs.shiftRight(_k - c);
        if (gsPlusJs.testBit((_k - c) - 1)) {
            ls = ls.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(ls, c);
    }

    public static byte[] tauAdicNaf(byte mu, ZTauElement lambda) {
        if (mu == 1 || mu == -1) {
            int log2Norm = norm(mu, lambda).bitLength();
            byte[] u = new byte[(log2Norm > 30 ? log2Norm + 4 : 34)];
            int i = 0;
            int length = 0;
            BigInteger r0 = lambda.u;
            BigInteger r1 = lambda.v;
            while (true) {
                if (!r0.equals(ECConstants.ZERO) || !r1.equals(ECConstants.ZERO)) {
                    if (r0.testBit(0)) {
                        u[i] = (byte) ECConstants.TWO.subtract(r0.subtract(r1.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                        if (u[i] == 1) {
                            r0 = r0.clearBit(0);
                        } else {
                            r0 = r0.add(ECConstants.ONE);
                        }
                        length = i;
                    } else {
                        u[i] = 0;
                    }
                    BigInteger s = r0.shiftRight(1);
                    if (mu == 1) {
                        r0 = r1.add(s);
                    } else {
                        r0 = r1.subtract(s);
                    }
                    r1 = r0.shiftRight(1).negate();
                    i++;
                } else {
                    int length2 = length + 1;
                    byte[] tnaf = new byte[length2];
                    System.arraycopy(u, 0, tnaf, 0, length2);
                    return tnaf;
                }
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static ECPoint.AbstractF2m tau(ECPoint.AbstractF2m p) {
        return p.tau();
    }

    public static byte getMu(ECCurve.AbstractF2m curve) {
        if (!curve.isKoblitz()) {
            throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
        } else if (curve.getA().isZero()) {
            return -1;
        } else {
            return 1;
        }
    }

    public static byte getMu(ECFieldElement curveA) {
        return (byte) (curveA.isZero() ? -1 : 1);
    }

    public static byte getMu(int curveA) {
        return (byte) (curveA == 0 ? -1 : 1);
    }

    public static BigInteger[] getLucas(byte mu, int k, boolean doV) {
        BigInteger u1;
        BigInteger u0;
        BigInteger s;
        if (mu == 1 || mu == -1) {
            if (doV) {
                u0 = ECConstants.TWO;
                u1 = BigInteger.valueOf((long) mu);
            } else {
                u0 = ECConstants.ZERO;
                u1 = ECConstants.ONE;
            }
            for (int i = 1; i < k; i++) {
                if (mu == 1) {
                    s = u1;
                } else {
                    s = u1.negate();
                }
                u0 = u1;
                u1 = s.subtract(u0.shiftLeft(1));
            }
            return new BigInteger[]{u0, u1};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static BigInteger getTw(byte mu, int w) {
        if (w != 4) {
            BigInteger[] us = getLucas(mu, w, false);
            BigInteger twoToW = ECConstants.ZERO.setBit(w);
            return ECConstants.TWO.multiply(us[0]).multiply(us[1].modInverse(twoToW)).mod(twoToW);
        } else if (mu == 1) {
            return BigInteger.valueOf(6);
        } else {
            return BigInteger.valueOf(10);
        }
    }

    public static BigInteger[] getSi(ECCurve.AbstractF2m curve) {
        if (curve.isKoblitz()) {
            int m = curve.getFieldSize();
            int a = curve.getA().toBigInteger().intValue();
            byte mu = getMu(a);
            int shifts = getShiftsForCofactor(curve.getCofactor());
            BigInteger[] ui = getLucas(mu, (m + 3) - a, false);
            if (mu == 1) {
                ui[0] = ui[0].negate();
                ui[1] = ui[1].negate();
            }
            return new BigInteger[]{ECConstants.ONE.add(ui[1]).shiftRight(shifts), ECConstants.ONE.add(ui[0]).shiftRight(shifts).negate()};
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    public static BigInteger[] getSi(int fieldSize, int curveA, BigInteger cofactor) {
        byte mu = getMu(curveA);
        int shifts = getShiftsForCofactor(cofactor);
        BigInteger[] ui = getLucas(mu, (fieldSize + 3) - curveA, false);
        if (mu == 1) {
            ui[0] = ui[0].negate();
            ui[1] = ui[1].negate();
        }
        return new BigInteger[]{ECConstants.ONE.add(ui[1]).shiftRight(shifts), ECConstants.ONE.add(ui[0]).shiftRight(shifts).negate()};
    }

    protected static int getShiftsForCofactor(BigInteger h) {
        if (h != null) {
            if (h.equals(ECConstants.TWO)) {
                return 1;
            }
            if (h.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static ZTauElement partModReduction(BigInteger k, int m, byte a, BigInteger[] s, byte mu, byte c) {
        BigInteger d0;
        if (mu == 1) {
            d0 = s[0].add(s[1]);
        } else {
            d0 = s[0].subtract(s[1]);
        }
        BigInteger vm = getLucas(mu, m, true)[1];
        ZTauElement q = round(approximateDivisionByN(k, s[0], vm, a, m, c), approximateDivisionByN(k, s[1], vm, a, m, c), mu);
        return new ZTauElement(k.subtract(d0.multiply(q.u)).subtract(BigInteger.valueOf(2).multiply(s[1]).multiply(q.v)), s[1].multiply(q.u).subtract(s[0].multiply(q.v)));
    }

    public static ECPoint.AbstractF2m multiplyRTnaf(ECPoint.AbstractF2m p, BigInteger k) {
        ECCurve.AbstractF2m curve = (ECCurve.AbstractF2m) p.getCurve();
        int m = curve.getFieldSize();
        int a = curve.getA().toBigInteger().intValue();
        return multiplyTnaf(p, partModReduction(k, m, (byte) a, curve.getSi(), getMu(a), (byte) 10));
    }

    public static ECPoint.AbstractF2m multiplyTnaf(ECPoint.AbstractF2m p, ZTauElement lambda) {
        return multiplyFromTnaf(p, tauAdicNaf(getMu(((ECCurve.AbstractF2m) p.getCurve()).getA()), lambda));
    }

    public static ECPoint.AbstractF2m multiplyFromTnaf(ECPoint.AbstractF2m p, byte[] u) {
        ECPoint.AbstractF2m q = (ECPoint.AbstractF2m) p.getCurve().getInfinity();
        ECPoint pNeg = (ECPoint.AbstractF2m) p.negate();
        int tauCount = 0;
        for (int i = u.length - 1; i >= 0; i--) {
            tauCount++;
            byte ui = u[i];
            if (ui != 0) {
                ECPoint.AbstractF2m q2 = q.tauPow(tauCount);
                tauCount = 0;
                q = (ECPoint.AbstractF2m) q2.add(ui > 0 ? p : pNeg);
            }
        }
        if (tauCount > 0) {
            return q.tauPow(tauCount);
        }
        return q;
    }

    public static byte[] tauAdicWNaf(byte mu, ZTauElement lambda, byte width, BigInteger pow2w, BigInteger tw, ZTauElement[] alpha) {
        byte uLocal;
        if (mu == 1 || mu == -1) {
            int log2Norm = norm(mu, lambda).bitLength();
            byte[] u = new byte[(log2Norm > 30 ? log2Norm + 4 + width : width + 34)];
            BigInteger pow2wMin1 = pow2w.shiftRight(1);
            BigInteger r0 = lambda.u;
            BigInteger r1 = lambda.v;
            int i = 0;
            while (true) {
                if (r0.equals(ECConstants.ZERO) && r1.equals(ECConstants.ZERO)) {
                    return u;
                }
                if (r0.testBit(0)) {
                    BigInteger uUnMod = r0.add(r1.multiply(tw)).mod(pow2w);
                    if (uUnMod.compareTo(pow2wMin1) >= 0) {
                        uLocal = (byte) uUnMod.subtract(pow2w).intValue();
                    } else {
                        uLocal = (byte) uUnMod.intValue();
                    }
                    u[i] = uLocal;
                    boolean s = true;
                    if (uLocal < 0) {
                        s = false;
                        uLocal = (byte) (-uLocal);
                    }
                    if (s) {
                        BigInteger r02 = r0.subtract(alpha[uLocal].u);
                        r1 = r1.subtract(alpha[uLocal].v);
                        r0 = r02;
                    } else {
                        BigInteger r03 = r0.add(alpha[uLocal].u);
                        r1 = r1.add(alpha[uLocal].v);
                        r0 = r03;
                    }
                } else {
                    u[i] = 0;
                }
                if (mu == 1) {
                    r0 = r1.add(r0.shiftRight(1));
                } else {
                    r0 = r1.subtract(r0.shiftRight(1));
                }
                r1 = r0.shiftRight(1).negate();
                i++;
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static ECPoint.AbstractF2m[] getPreComp(ECPoint.AbstractF2m p, byte a) {
        byte[][] alphaTnaf = a == 0 ? alpha0Tnaf : alpha1Tnaf;
        ECPoint.AbstractF2m[] pu = new ECPoint.AbstractF2m[((alphaTnaf.length + 1) >>> 1)];
        pu[0] = p;
        int precompLen = alphaTnaf.length;
        for (int i = 3; i < precompLen; i += 2) {
            pu[i >>> 1] = multiplyFromTnaf(p, alphaTnaf[i]);
        }
        p.getCurve().normalizeAll(pu);
        return pu;
    }
}
