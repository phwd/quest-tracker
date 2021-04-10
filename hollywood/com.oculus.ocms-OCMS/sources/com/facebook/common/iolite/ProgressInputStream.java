package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProgressInputStream extends FilterInputStream {
    private final Listener mListener;
    private long mOffset = 0;

    public interface Listener {
        void onClose(long j);

        void onProgress(long j, long j2, boolean z);
    }

    public boolean markSupported() {
        return false;
    }

    public ProgressInputStream(InputStream inputStream, Listener listener) {
        super((InputStream) checkNotNull(inputStream));
        this.mListener = (Listener) checkNotNull(listener);
    }

    public long getOffset() {
        return this.mOffset;
    }

    public synchronized void mark(int i) {
        throw new UnsupportedOperationException("Mark not supported");
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
        int read = super.read(bArr, i, i2);
        if (read > 0) {
            long j = (long) read;
            this.mOffset += j;
            this.mListener.onProgress(j, this.mOffset, false);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException("Mark not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long skip = super.skip(j);
        if (skip > 0) {
            this.mOffset += skip;
            this.mListener.onProgress(skip, this.mOffset, true);
        }
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        super.close();
        this.mListener.onClose(this.mOffset);
    }

    private static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
