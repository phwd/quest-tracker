package com.android.org.bouncycastle.crypto.modes.gcm;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.Array;

public class Tables4kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][] T;

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] H2) {
        if (this.T == null) {
            this.T = (long[][]) Array.newInstance(long.class, 256, 2);
        } else if (Arrays.areEqual(this.H, H2)) {
            return;
        }
        this.H = Arrays.clone(H2);
        GCMUtil.asLongs(this.H, this.T[1]);
        long[][] jArr = this.T;
        GCMUtil.multiplyP7(jArr[1], jArr[1]);
        for (int n = 2; n < 256; n += 2) {
            long[][] jArr2 = this.T;
            GCMUtil.divideP(jArr2[n >> 1], jArr2[n]);
            long[][] jArr3 = this.T;
            GCMUtil.xor(jArr3[n], jArr3[1], jArr3[n + 1]);
        }
    }

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] x) {
        long[] t = this.T[x[15] & UnsignedBytes.MAX_VALUE];
        long z0 = t[0];
        long z1 = t[1];
        for (int i = 14; i >= 0; i--) {
            long[] t2 = this.T[x[i] & UnsignedBytes.MAX_VALUE];
            long c = z1 << 56;
            z1 = t2[1] ^ ((z1 >>> 8) | (z0 << 56));
            z0 = (((((z0 >>> 8) ^ t2[0]) ^ c) ^ (c >>> 1)) ^ (c >>> 2)) ^ (c >>> 7);
        }
        Pack.longToBigEndian(z0, x, 0);
        Pack.longToBigEndian(z1, x, 8);
    }
}
