package com.facebook.zstd;

import X.C05400jG;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.FilterInputStream;
import java.io.IOException;

public class ZstdInputStream extends FilterInputStream {
    @DoNotStrip
    public final HybridData mHybridData;
    public boolean sourceEOF;

    private native boolean canWrite();

    public static native HybridData initHybrid(int i);

    private native int nativeRead(byte[] bArr, int i);

    private native void nativeWrite(byte[] bArr, int i);

    static {
        C05400jG.A00("zstddecoder");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) == 1) {
            return bArr[0];
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0 || (i3 = i + i2) < 0 || bArr.length - i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            int i4 = 0;
            while (true) {
                if (canWrite()) {
                    int read = this.in.read(null);
                    if (read == -1) {
                        this.sourceEOF = true;
                    } else {
                        nativeWrite(null, read);
                    }
                }
                int nativeRead = nativeRead(null, Math.min(0, i2 - i4));
                if (this.sourceEOF) {
                    if (nativeRead == 0) {
                        throw new IOException("Unexpected end of ZSTD stream");
                    } else if (nativeRead == -1) {
                        if (i4 == 0) {
                            return -1;
                        }
                    }
                }
                System.arraycopy(null, 0, bArr, i + i4, nativeRead);
                i4 += nativeRead;
                if (i4 >= i2) {
                    break;
                }
            }
            return i4;
        }
    }
}
