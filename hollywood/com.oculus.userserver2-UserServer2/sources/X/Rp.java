package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract class Rp implements Closeable, AbstractC00138d, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
    }
}
