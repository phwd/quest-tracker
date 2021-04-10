package com.android.org.bouncycastle.math.ec;

import java.math.BigInteger;

public abstract class WNafUtil {
    private static final int[] DEFAULT_WINDOW_SIZE_CUTOFFS = {13, 41, 121, 337, 897, 2305};
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final int[] EMPTY_INTS = new int[0];
    private static final ECPoint[] EMPTY_POINTS = new ECPoint[0];
    public static final String PRECOMP_NAME = "bc_wnaf";

    public static int[] generateCompactNaf(BigInteger k) {
        if ((k.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        } else if (k.signum() == 0) {
            return EMPTY_INTS;
        } else {
            BigInteger _3k = k.shiftLeft(1).add(k);
            int bits = _3k.bitLength();
            int[] naf = new int[(bits >> 1)];
            BigInteger diff = _3k.xor(k);
            int highBit = bits - 1;
            int zeroes = 0;
            int zeroes2 = 0;
            int i = 1;
            while (i < highBit) {
                if (!diff.testBit(i)) {
                    zeroes2++;
                } else {
                    naf[zeroes] = ((k.testBit(i) ? -1 : 1) << 16) | zeroes2;
                    i++;
                    zeroes2 = 1;
                    zeroes++;
                }
                i++;
            }
            int length = zeroes + 1;
            naf[zeroes] = 65536 | zeroes2;
            if (naf.length > length) {
                return trim(naf, length);
            }
            return naf;
        }
    }

    public static int[] generateCompactWindowNaf(int width, BigInteger k) {
        if (width == 2) {
            return generateCompactNaf(k);
        }
        if (width < 2 || width > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        } else if ((k.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        } else if (k.signum() == 0) {
            return EMPTY_INTS;
        } else {
            int[] wnaf = new int[((k.bitLength() / width) + 1)];
            int pow2 = 1 << width;
            int mask = pow2 - 1;
            int sign = pow2 >>> 1;
            boolean carry = false;
            int length = 0;
            int pos = 0;
            while (pos <= k.bitLength()) {
                if (k.testBit(pos) == carry) {
                    pos++;
                } else {
                    k = k.shiftRight(pos);
                    int digit = k.intValue() & mask;
                    if (carry) {
                        digit++;
                    }
                    carry = (digit & sign) != 0;
                    if (carry) {
                        digit -= pow2;
                    }
                    wnaf[length] = (digit << 16) | (length > 0 ? pos - 1 : pos);
                    pos = width;
                    length++;
                }
            }
            if (wnaf.length > length) {
                return trim(wnaf, length);
            }
            return wnaf;
        }
    }

    public static byte[] generateJSF(BigInteger g, BigInteger h) {
        byte[] jsf = new byte[(Math.max(g.bitLength(), h.bitLength()) + 1)];
        BigInteger k0 = g;
        BigInteger k1 = h;
        int j = 0;
        int d0 = 0;
        int d1 = 0;
        int offset = 0;
        while (true) {
            if ((d0 | d1) == 0 && k0.bitLength() <= offset && k1.bitLength() <= offset) {
                break;
            }
            int n0 = ((k0.intValue() >>> offset) + d0) & 7;
            int n1 = ((k1.intValue() >>> offset) + d1) & 7;
            int u0 = n0 & 1;
            if (u0 != 0) {
                u0 -= n0 & 2;
                if (n0 + u0 == 4 && (n1 & 3) == 2) {
                    u0 = -u0;
                }
            }
            int u1 = n1 & 1;
            if (u1 != 0) {
                u1 -= n1 & 2;
                if (n1 + u1 == 4 && (n0 & 3) == 2) {
                    u1 = -u1;
                }
            }
            if ((d0 << 1) == u0 + 1) {
                d0 ^= 1;
            }
            if ((d1 << 1) == u1 + 1) {
                d1 ^= 1;
            }
            offset++;
            if (offset == 30) {
                offset = 0;
                k0 = k0.shiftRight(30);
                k1 = k1.shiftRight(30);
            }
            jsf[j] = (byte) ((u0 << 4) | (u1 & 15));
            j++;
        }
        if (jsf.length > j) {
            return trim(jsf, j);
        }
        return jsf;
    }

    public static byte[] generateNaf(BigInteger k) {
        if (k.signum() == 0) {
            return EMPTY_BYTES;
        }
        BigInteger _3k = k.shiftLeft(1).add(k);
        int digits = _3k.bitLength() - 1;
        byte[] naf = new byte[digits];
        BigInteger diff = _3k.xor(k);
        int i = 1;
        while (i < digits) {
            if (diff.testBit(i)) {
                naf[i - 1] = (byte) (k.testBit(i) ? -1 : 1);
                i++;
            }
            i++;
        }
        naf[digits - 1] = 1;
        return naf;
    }

    public static byte[] generateWindowNaf(int width, BigInteger k) {
        if (width == 2) {
            return generateNaf(k);
        }
        if (width < 2 || width > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        } else if (k.signum() == 0) {
            return EMPTY_BYTES;
        } else {
            byte[] wnaf = new byte[(k.bitLength() + 1)];
            int pow2 = 1 << width;
            int mask = pow2 - 1;
            int sign = pow2 >>> 1;
            boolean carry = false;
            int length = 0;
            int pos = 0;
            while (pos <= k.bitLength()) {
                if (k.testBit(pos) == carry) {
                    pos++;
                } else {
                    k = k.shiftRight(pos);
                    int digit = k.intValue() & mask;
                    if (carry) {
                        digit++;
                    }
                    carry = (digit & sign) != 0;
                    if (carry) {
                        digit -= pow2;
                    }
                    int length2 = length + (length > 0 ? pos - 1 : pos);
                    wnaf[length2] = (byte) digit;
                    pos = width;
                    length = length2 + 1;
                }
            }
            if (wnaf.length > length) {
                return trim(wnaf, length);
            }
            return wnaf;
        }
    }

    public static int getNafWeight(BigInteger k) {
        if (k.signum() == 0) {
            return 0;
        }
        return k.shiftLeft(1).add(k).xor(k).bitCount();
    }

    public static WNafPreCompInfo getWNafPreCompInfo(ECPoint p) {
        return getWNafPreCompInfo(p.getCurve().getPreCompInfo(p, PRECOMP_NAME));
    }

    public static WNafPreCompInfo getWNafPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo instanceof WNafPreCompInfo) {
            return (WNafPreCompInfo) preCompInfo;
        }
        return null;
    }

    public static int getWindowSize(int bits) {
        return getWindowSize(bits, DEFAULT_WINDOW_SIZE_CUTOFFS);
    }

    public static int getWindowSize(int bits, int[] windowSizeCutoffs) {
        int w = 0;
        while (w < windowSizeCutoffs.length && bits >= windowSizeCutoffs[w]) {
            w++;
        }
        return w + 2;
    }

    public static ECPoint mapPointWithPrecomp(ECPoint p, int width, final boolean includeNegated, final ECPointMap pointMap) {
        ECCurve c = p.getCurve();
        final WNafPreCompInfo wnafPreCompP = precompute(p, width, includeNegated);
        ECPoint q = pointMap.map(p);
        c.precompute(q, PRECOMP_NAME, new PreCompCallback() {
            /* class com.android.org.bouncycastle.math.ec.WNafUtil.AnonymousClass1 */

            @Override // com.android.org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo existing) {
                WNafPreCompInfo result = new WNafPreCompInfo();
                ECPoint twiceP = WNafPreCompInfo.this.getTwice();
                if (twiceP != null) {
                    result.setTwice(pointMap.map(twiceP));
                }
                ECPoint[] preCompP = WNafPreCompInfo.this.getPreComp();
                ECPoint[] preCompQ = new ECPoint[preCompP.length];
                for (int i = 0; i < preCompP.length; i++) {
                    preCompQ[i] = pointMap.map(preCompP[i]);
                }
                result.setPreComp(preCompQ);
                if (includeNegated) {
                    ECPoint[] preCompNegQ = new ECPoint[preCompQ.length];
                    for (int i2 = 0; i2 < preCompNegQ.length; i2++) {
                        preCompNegQ[i2] = preCompQ[i2].negate();
                    }
                    result.setPreCompNeg(preCompNegQ);
                }
                return result;
            }
        });
        return q;
    }

    public static WNafPreCompInfo precompute(final ECPoint p, final int width, final boolean includeNegated) {
        final ECCurve c = p.getCurve();
        return (WNafPreCompInfo) c.precompute(p, PRECOMP_NAME, new PreCompCallback() {
            /* class com.android.org.bouncycastle.math.ec.WNafUtil.AnonymousClass2 */

            @Override // com.android.org.bouncycastle.math.ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo existing) {
                int pos;
                int coordinateSystem;
                WNafPreCompInfo existingWNaf = existing instanceof WNafPreCompInfo ? (WNafPreCompInfo) existing : null;
                int reqPreCompLen = 1 << Math.max(0, width - 2);
                if (checkExisting(existingWNaf, reqPreCompLen, includeNegated)) {
                    return existingWNaf;
                }
                ECPoint[] preComp = null;
                ECPoint[] preCompNeg = null;
                ECPoint twiceP = null;
                if (existingWNaf != null) {
                    preComp = existingWNaf.getPreComp();
                    preCompNeg = existingWNaf.getPreCompNeg();
                    twiceP = existingWNaf.getTwice();
                }
                int iniPreCompLen = 0;
                if (preComp == null) {
                    preComp = WNafUtil.EMPTY_POINTS;
                } else {
                    iniPreCompLen = preComp.length;
                }
                if (iniPreCompLen < reqPreCompLen) {
                    preComp = WNafUtil.resizeTable(preComp, reqPreCompLen);
                    if (reqPreCompLen == 1) {
                        preComp[0] = p.normalize();
                    } else {
                        int curPreCompLen = iniPreCompLen;
                        if (curPreCompLen == 0) {
                            preComp[0] = p;
                            curPreCompLen = 1;
                        }
                        ECFieldElement iso2 = null;
                        if (reqPreCompLen == 2) {
                            preComp[1] = p.threeTimes();
                        } else {
                            ECPoint isoTwiceP = twiceP;
                            ECPoint last = preComp[curPreCompLen - 1];
                            if (isoTwiceP == null) {
                                isoTwiceP = preComp[0].twice();
                                twiceP = isoTwiceP;
                                if (!twiceP.isInfinity() && ECAlgorithms.isFpCurve(c) && c.getFieldSize() >= 64 && ((coordinateSystem = c.getCoordinateSystem()) == 2 || coordinateSystem == 3 || coordinateSystem == 4)) {
                                    ECFieldElement iso = twiceP.getZCoord(0);
                                    isoTwiceP = c.createPoint(twiceP.getXCoord().toBigInteger(), twiceP.getYCoord().toBigInteger());
                                    ECFieldElement iso22 = iso.square();
                                    last = last.scaleX(iso22).scaleY(iso22.multiply(iso));
                                    if (iniPreCompLen == 0) {
                                        preComp[0] = last;
                                    }
                                    iso2 = iso;
                                }
                            }
                            while (curPreCompLen < reqPreCompLen) {
                                ECPoint add = last.add(isoTwiceP);
                                last = add;
                                preComp[curPreCompLen] = add;
                                curPreCompLen++;
                            }
                        }
                        c.normalizeAll(preComp, iniPreCompLen, reqPreCompLen - iniPreCompLen, iso2);
                    }
                }
                if (includeNegated) {
                    if (preCompNeg == null) {
                        pos = 0;
                        preCompNeg = new ECPoint[reqPreCompLen];
                    } else {
                        pos = preCompNeg.length;
                        if (pos < reqPreCompLen) {
                            preCompNeg = WNafUtil.resizeTable(preCompNeg, reqPreCompLen);
                        }
                    }
                    while (pos < reqPreCompLen) {
                        preCompNeg[pos] = preComp[pos].negate();
                        pos++;
                    }
                }
                WNafPreCompInfo result = new WNafPreCompInfo();
                result.setPreComp(preComp);
                result.setPreCompNeg(preCompNeg);
                result.setTwice(twiceP);
                return result;
            }

            private boolean checkExisting(WNafPreCompInfo existingWNaf, int reqPreCompLen, boolean includeNegated) {
                return existingWNaf != null && checkTable(existingWNaf.getPreComp(), reqPreCompLen) && (!includeNegated || checkTable(existingWNaf.getPreCompNeg(), reqPreCompLen));
            }

            private boolean checkTable(ECPoint[] table, int reqLen) {
                return table != null && table.length >= reqLen;
            }
        });
    }

    private static byte[] trim(byte[] a, int length) {
        byte[] result = new byte[length];
        System.arraycopy(a, 0, result, 0, result.length);
        return result;
    }

    private static int[] trim(int[] a, int length) {
        int[] result = new int[length];
        System.arraycopy(a, 0, result, 0, result.length);
        return result;
    }

    /* access modifiers changed from: private */
    public static ECPoint[] resizeTable(ECPoint[] a, int length) {
        ECPoint[] result = new ECPoint[length];
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }
}
