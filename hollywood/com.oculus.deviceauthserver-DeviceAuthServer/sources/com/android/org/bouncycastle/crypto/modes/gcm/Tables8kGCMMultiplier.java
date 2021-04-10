package com.android.org.bouncycastle.crypto.modes.gcm;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.base.Ascii;
import java.lang.reflect.Array;

public class Tables8kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][][] T;

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] H2) {
        if (this.T == null) {
            this.T = (long[][][]) Array.newInstance(long.class, 32, 16, 2);
        } else if (Arrays.areEqual(this.H, H2)) {
            return;
        }
        this.H = Arrays.clone(H2);
        for (int i = 0; i < 32; i++) {
            long[][][] jArr = this.T;
            long[][] t = jArr[i];
            if (i == 0) {
                GCMUtil.asLongs(this.H, t[1]);
                GCMUtil.multiplyP3(t[1], t[1]);
            } else {
                GCMUtil.multiplyP4(jArr[i - 1][1], t[1]);
            }
            for (int n = 2; n < 16; n += 2) {
                GCMUtil.divideP(t[n >> 1], t[n]);
                GCMUtil.xor(t[n], t[1], t[n + 1]);
            }
        }
    }

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] x) {
        long z0 = 0;
        long z1 = 0;
        for (int i = 15; i >= 0; i--) {
            long[][][] jArr = this.T;
            long[] u = jArr[i + i + 1][x[i] & Ascii.SI];
            long[] v = jArr[i + i][(x[i] & 240) >>> 4];
            z0 ^= u[0] ^ v[0];
            z1 ^= u[1] ^ v[1];
        }
        Pack.longToBigEndian(z0, x, 0);
        Pack.longToBigEndian(z1, x, 8);
    }
}
