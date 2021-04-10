package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* renamed from: X.0gr  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04120gr implements Closeable, AbstractC04870jo, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;
}
