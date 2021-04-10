package com.android.org.bouncycastle.math.ec.custom.sec;

import com.android.org.bouncycastle.math.raw.Nat;
import com.android.org.bouncycastle.math.raw.Nat256;
import java.math.BigInteger;

public class SecP256R1Field {
    private static final long M = 4294967295L;
    static final int[] P = {-1, -1, -1, 0, 0, 0, 1, -1};
    private static final int P7 = -1;
    static final int[] PExt = {1, 0, 0, -2, -1, -1, -2, 1, -2, 1, -2, 1, 1, -2, 2, -2};
    private static final int PExt15s1 = Integer.MAX_VALUE;

    public static void add(int[] x, int[] y, int[] z) {
        if (Nat256.add(x, y, z) != 0 || (z[7] == -1 && Nat256.gte(z, P))) {
            addPInvTo(z);
        }
    }

    public static void addExt(int[] xx, int[] yy, int[] zz) {
        if (Nat.add(16, xx, yy, zz) != 0 || ((zz[15] >>> 1) >= PExt15s1 && Nat.gte(16, zz, PExt))) {
            Nat.subFrom(16, PExt, zz);
        }
    }

    public static void addOne(int[] x, int[] z) {
        if (Nat.inc(8, x, z) != 0 || (z[7] == -1 && Nat256.gte(z, P))) {
            addPInvTo(z);
        }
    }

    public static int[] fromBigInteger(BigInteger x) {
        int[] z = Nat256.fromBigInteger(x);
        if (z[7] == -1 && Nat256.gte(z, P)) {
            Nat256.subFrom(P, z);
        }
        return z;
    }

    public static void half(int[] x, int[] z) {
        if ((x[0] & 1) == 0) {
            Nat.shiftDownBit(8, x, 0, z);
        } else {
            Nat.shiftDownBit(8, z, Nat256.add(x, P, z));
        }
    }

    public static void multiply(int[] x, int[] y, int[] z) {
        int[] tt = Nat256.createExt();
        Nat256.mul(x, y, tt);
        reduce(tt, z);
    }

    public static void multiplyAddToExt(int[] x, int[] y, int[] zz) {
        if (Nat256.mulAddTo(x, y, zz) != 0 || ((zz[15] >>> 1) >= PExt15s1 && Nat.gte(16, zz, PExt))) {
            Nat.subFrom(16, PExt, zz);
        }
    }

    public static void negate(int[] x, int[] z) {
        if (Nat256.isZero(x)) {
            Nat256.zero(z);
        } else {
            Nat256.sub(P, x, z);
        }
    }

    public static void reduce(int[] xx, int[] z) {
        long xx08 = ((long) xx[8]) & M;
        long xx09 = ((long) xx[9]) & M;
        long xx10 = ((long) xx[10]) & M;
        long xx11 = ((long) xx[11]) & M;
        long xx12 = ((long) xx[12]) & M;
        long xx13 = ((long) xx[13]) & M;
        long xx14 = ((long) xx[14]) & M;
        long xx15 = ((long) xx[15]) & M;
        long xx082 = xx08 - 6;
        long t1 = xx09 + xx10;
        long t2 = (xx10 + xx11) - xx15;
        long t3 = xx11 + xx12;
        long t4 = xx12 + xx13;
        long t5 = xx13 + xx14;
        long t6 = xx14 + xx15;
        long t7 = t5 - (xx082 + xx09);
        long cc = 0 + (((((long) xx[0]) & M) - t3) - t7);
        z[0] = (int) cc;
        long cc2 = (cc >> 32) + ((((((long) xx[1]) & M) + t1) - t4) - t6);
        z[1] = (int) cc2;
        long cc3 = (cc2 >> 32) + (((((long) xx[2]) & M) + t2) - t5);
        z[2] = (int) cc3;
        long cc4 = (cc3 >> 32) + ((((((long) xx[3]) & M) + (t3 << 1)) + t7) - t6);
        z[3] = (int) cc4;
        long cc5 = (cc4 >> 32) + ((((((long) xx[4]) & M) + (t4 << 1)) + xx14) - t1);
        z[4] = (int) cc5;
        long cc6 = (cc5 >> 32) + (((((long) xx[5]) & M) + (t5 << 1)) - t2);
        z[5] = (int) cc6;
        long cc7 = (cc6 >> 32) + (((long) xx[6]) & M) + (t6 << 1) + t7;
        z[6] = (int) cc7;
        long cc8 = (cc7 >> 32) + (((((((long) xx[7]) & M) + (xx15 << 1)) + xx082) - t2) - t4);
        z[7] = (int) cc8;
        reduce32((int) ((cc8 >> 32) + 6), z);
    }

    public static void reduce32(int x, int[] z) {
        long cc = 0;
        if (x != 0) {
            long xx08 = ((long) x) & M;
            long cc2 = 0 + (((long) z[0]) & M) + xx08;
            z[0] = (int) cc2;
            long cc3 = cc2 >> 32;
            if (cc3 != 0) {
                long cc4 = cc3 + (((long) z[1]) & M);
                z[1] = (int) cc4;
                long cc5 = (cc4 >> 32) + (((long) z[2]) & M);
                z[2] = (int) cc5;
                cc3 = cc5 >> 32;
            }
            long cc6 = cc3 + ((((long) z[3]) & M) - xx08);
            z[3] = (int) cc6;
            long cc7 = cc6 >> 32;
            if (cc7 != 0) {
                long cc8 = cc7 + (((long) z[4]) & M);
                z[4] = (int) cc8;
                long cc9 = (cc8 >> 32) + (((long) z[5]) & M);
                z[5] = (int) cc9;
                cc7 = cc9 >> 32;
            }
            long cc10 = cc7 + ((((long) z[6]) & M) - xx08);
            z[6] = (int) cc10;
            long cc11 = (cc10 >> 32) + (M & ((long) z[7])) + xx08;
            z[7] = (int) cc11;
            cc = cc11 >> 32;
        }
        if (cc != 0 || (z[7] == -1 && Nat256.gte(z, P))) {
            addPInvTo(z);
        }
    }

    public static void square(int[] x, int[] z) {
        int[] tt = Nat256.createExt();
        Nat256.square(x, tt);
        reduce(tt, z);
    }

    public static void squareN(int[] x, int n, int[] z) {
        int[] tt = Nat256.createExt();
        Nat256.square(x, tt);
        reduce(tt, z);
        while (true) {
            n--;
            if (n > 0) {
                Nat256.square(z, tt);
                reduce(tt, z);
            } else {
                return;
            }
        }
    }

    public static void subtract(int[] x, int[] y, int[] z) {
        if (Nat256.sub(x, y, z) != 0) {
            subPInvFrom(z);
        }
    }

    public static void subtractExt(int[] xx, int[] yy, int[] zz) {
        if (Nat.sub(16, xx, yy, zz) != 0) {
            Nat.addTo(16, PExt, zz);
        }
    }

    public static void twice(int[] x, int[] z) {
        if (Nat.shiftUpBit(8, x, 0, z) != 0 || (z[7] == -1 && Nat256.gte(z, P))) {
            addPInvTo(z);
        }
    }

    private static void addPInvTo(int[] z) {
        long c = (((long) z[0]) & M) + 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & M);
            z[1] = (int) c3;
            long c4 = (c3 >> 32) + (((long) z[2]) & M);
            z[2] = (int) c4;
            c2 = c4 >> 32;
        }
        long c5 = c2 + ((((long) z[3]) & M) - 1);
        z[3] = (int) c5;
        long c6 = c5 >> 32;
        if (c6 != 0) {
            long c7 = c6 + (((long) z[4]) & M);
            z[4] = (int) c7;
            long c8 = (c7 >> 32) + (((long) z[5]) & M);
            z[5] = (int) c8;
            c6 = c8 >> 32;
        }
        long c9 = c6 + ((((long) z[6]) & M) - 1);
        z[6] = (int) c9;
        z[7] = (int) ((c9 >> 32) + (M & ((long) z[7])) + 1);
    }

    private static void subPInvFrom(int[] z) {
        long c = (((long) z[0]) & M) - 1;
        z[0] = (int) c;
        long c2 = c >> 32;
        if (c2 != 0) {
            long c3 = c2 + (((long) z[1]) & M);
            z[1] = (int) c3;
            long c4 = (c3 >> 32) + (((long) z[2]) & M);
            z[2] = (int) c4;
            c2 = c4 >> 32;
        }
        long c5 = c2 + (((long) z[3]) & M) + 1;
        z[3] = (int) c5;
        long c6 = c5 >> 32;
        if (c6 != 0) {
            long c7 = c6 + (((long) z[4]) & M);
            z[4] = (int) c7;
            long c8 = (c7 >> 32) + (((long) z[5]) & M);
            z[5] = (int) c8;
            c6 = c8 >> 32;
        }
        long c9 = c6 + (((long) z[6]) & M) + 1;
        z[6] = (int) c9;
        z[7] = (int) ((c9 >> 32) + ((M & ((long) z[7])) - 1));
    }
}
