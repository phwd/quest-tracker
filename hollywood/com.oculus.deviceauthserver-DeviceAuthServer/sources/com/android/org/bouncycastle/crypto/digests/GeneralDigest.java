package com.android.org.bouncycastle.crypto.digests;

import com.android.org.bouncycastle.crypto.ExtendedDigest;
import com.android.org.bouncycastle.util.Memoable;
import com.android.org.bouncycastle.util.Pack;
import com.google.common.primitives.UnsignedBytes;

public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;

    /* access modifiers changed from: protected */
    public abstract void processBlock();

    /* access modifiers changed from: protected */
    public abstract void processLength(long j);

    /* access modifiers changed from: protected */
    public abstract void processWord(byte[] bArr, int i);

    protected GeneralDigest() {
        this.xBuf = new byte[4];
        this.xBufOff = 0;
    }

    protected GeneralDigest(GeneralDigest t) {
        this.xBuf = new byte[4];
        copyIn(t);
    }

    protected GeneralDigest(byte[] encodedState) {
        this.xBuf = new byte[4];
        byte[] bArr = this.xBuf;
        System.arraycopy(encodedState, 0, bArr, 0, bArr.length);
        this.xBufOff = Pack.bigEndianToInt(encodedState, 4);
        this.byteCount = Pack.bigEndianToLong(encodedState, 8);
    }

    /* access modifiers changed from: protected */
    public void copyIn(GeneralDigest t) {
        byte[] bArr = t.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = t.xBufOff;
        this.byteCount = t.byteCount;
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte in) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = in;
        if (this.xBufOff == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte[] in, int inOff, int len) {
        int len2 = Math.max(0, len);
        int i = 0;
        if (this.xBufOff != 0) {
            while (true) {
                if (i >= len2) {
                    break;
                }
                byte[] bArr = this.xBuf;
                int i2 = this.xBufOff;
                this.xBufOff = i2 + 1;
                int i3 = i + 1;
                bArr[i2] = in[i + inOff];
                if (this.xBufOff == 4) {
                    processWord(bArr, 0);
                    this.xBufOff = 0;
                    i = i3;
                    break;
                }
                i = i3;
            }
        }
        int limit = ((len2 - i) & -4) + i;
        while (i < limit) {
            processWord(in, inOff + i);
            i += 4;
        }
        while (i < len2) {
            byte[] bArr2 = this.xBuf;
            int i4 = this.xBufOff;
            this.xBufOff = i4 + 1;
            bArr2[i4] = in[i + inOff];
            i++;
        }
        this.byteCount += (long) len2;
    }

    public void finish() {
        long bitLength = this.byteCount << 3;
        update(UnsignedBytes.MAX_POWER_OF_TWO);
        while (this.xBufOff != 0) {
            update((byte) 0);
        }
        processLength(bitLength);
        processBlock();
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0;
        this.xBufOff = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void populateState(byte[] state) {
        System.arraycopy(this.xBuf, 0, state, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, state, 4);
        Pack.longToBigEndian(this.byteCount, state, 8);
    }

    @Override // com.android.org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }
}
