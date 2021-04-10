package X;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: X.cb  reason: case insensitive filesystem */
public interface AbstractC0312cb extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(AnonymousClass98 v, long j) throws IOException;

    ca timeout();
}
