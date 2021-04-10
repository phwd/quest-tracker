package com.android.org.bouncycastle.crypto.digests;

import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.util.Arrays;
import java.io.ByteArrayOutputStream;

public class NullDigest implements Digest {
    private OpenByteArrayOutputStream bOut = new OpenByteArrayOutputStream();

    @Override // com.android.org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "NULL";
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.bOut.size();
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte in) {
        this.bOut.write(in);
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void update(byte[] in, int inOff, int len) {
        this.bOut.write(in, inOff, len);
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public int doFinal(byte[] out, int outOff) {
        int size = this.bOut.size();
        this.bOut.copy(out, outOff);
        reset();
        return size;
    }

    @Override // com.android.org.bouncycastle.crypto.Digest
    public void reset() {
        this.bOut.reset();
    }

    /* access modifiers changed from: private */
    public static class OpenByteArrayOutputStream extends ByteArrayOutputStream {
        private OpenByteArrayOutputStream() {
        }

        public void reset() {
            super.reset();
            Arrays.clear(this.buf);
        }

        /* access modifiers changed from: package-private */
        public void copy(byte[] out, int outOff) {
            System.arraycopy(this.buf, 0, out, outOff, size());
        }
    }
}
