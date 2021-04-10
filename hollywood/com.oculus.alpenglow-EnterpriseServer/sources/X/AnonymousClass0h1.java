package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* renamed from: X.0h1  reason: invalid class name */
public interface AnonymousClass0h1 extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.io.Flushable
    void flush() throws IOException;

    C04540gz timeout();

    void write(AnonymousClass0HR v, long j) throws IOException;
}
