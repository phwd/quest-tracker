package X;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: X.0h0  reason: invalid class name and case insensitive filesystem */
public interface AbstractC04550h0 extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(AnonymousClass0HR v, long j) throws IOException;

    C04540gz timeout();
}
