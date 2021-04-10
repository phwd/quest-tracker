package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BoundedInputStream extends FilterInputStream {
    private boolean mCloseDelegate;
    private long mLeft;
    private long mMarkedLeft = -1;

    public BoundedInputStream(InputStream inputStream, long j, boolean z) {
        super(inputStream);
        this.mLeft = j;
        this.mCloseDelegate = z;
    }

    public synchronized void mark(int i) {
        super.mark(i);
        this.mMarkedLeft = this.mLeft;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.mLeft = this.mMarkedLeft;
        this.mMarkedLeft = -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == 1) {
            return bArr[0];
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.mLeft;
        if (j <= 0) {
            return i2 == 0 ? 0 : -1;
        }
        if (j < ((long) i2)) {
            i2 = (int) j;
        }
        int read = super.read(bArr, i, i2);
        if (read >= 0) {
            this.mLeft -= (long) read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = this.mLeft;
        if (j2 <= 0) {
            return j == 0 ? 0 : -1;
        }
        if (j2 < j) {
            j = j2;
        }
        long skip = super.skip(j);
        if (skip >= 0) {
            this.mLeft -= skip;
        }
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (this.mCloseDelegate) {
            super.close();
        }
    }
}
