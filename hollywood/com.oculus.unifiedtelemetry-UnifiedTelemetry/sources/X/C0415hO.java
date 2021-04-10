package X;

import java.io.IOException;
import java.io.Writer;

/* renamed from: X.hO  reason: case insensitive filesystem */
public class C0415hO extends Writer {
    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() throws IOException {
        throw new AssertionError();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        throw new AssertionError();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        throw new AssertionError();
    }
}
