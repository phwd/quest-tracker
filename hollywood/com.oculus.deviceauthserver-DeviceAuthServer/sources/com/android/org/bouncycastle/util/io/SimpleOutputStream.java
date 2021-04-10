package com.android.org.bouncycastle.util.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class SimpleOutputStream extends OutputStream {
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }
}
