package com.android.org.bouncycastle.crypto.modes.gcm;

import com.android.org.bouncycastle.util.Arrays;

public class BasicGCMExponentiator implements GCMExponentiator {
    private long[] x;

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] x2) {
        this.x = GCMUtil.asLongs(x2);
    }

    @Override // com.android.org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long pow, byte[] output) {
        long[] y = GCMUtil.oneAsLongs();
        if (pow > 0) {
            long[] powX = Arrays.clone(this.x);
            do {
                if ((1 & pow) != 0) {
                    GCMUtil.multiply(y, powX);
                }
                GCMUtil.square(powX, powX);
                pow >>>= 1;
            } while (pow > 0);
        }
        GCMUtil.asBytes(y, output);
    }
}
