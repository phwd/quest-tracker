package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* renamed from: X.cc  reason: case insensitive filesystem */
public interface AbstractC0313cc extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.io.Flushable
    void flush() throws IOException;

    ca timeout();

    void write(AnonymousClass98 v, long j) throws IOException;
}
