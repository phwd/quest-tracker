package com.facebook.common.internal;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
public class CountingOutputStream extends FilterOutputStream {
    private long mCount = 0;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    public long getCount() {
        return this.mCount;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.mCount += (long) len;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
        this.mCount++;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
