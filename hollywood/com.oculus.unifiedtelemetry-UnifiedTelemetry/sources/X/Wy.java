package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public abstract class Wy implements Closeable, q8, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;
}
