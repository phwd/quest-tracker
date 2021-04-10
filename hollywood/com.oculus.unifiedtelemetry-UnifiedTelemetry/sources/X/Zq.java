package X;

import java.io.IOException;
import java.io.Reader;

public class Zq extends Reader {
    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public final void close() throws IOException {
        throw new AssertionError();
    }

    @Override // java.io.Reader
    public final int read(char[] cArr, int i, int i2) throws IOException {
        throw new AssertionError();
    }
}
