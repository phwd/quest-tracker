package X;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: X.0v6  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07630v6 extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(AnonymousClass0B3 v, long j) throws IOException;

    C07620v5 timeout();
}
