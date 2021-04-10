package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* renamed from: X.0v7  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07640v7 extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.io.Flushable
    void flush() throws IOException;

    C07620v5 timeout();

    void write(AnonymousClass0B3 v, long j) throws IOException;
}
