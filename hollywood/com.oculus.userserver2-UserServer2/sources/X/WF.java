package X;

import java.io.Closeable;
import java.io.IOException;

public interface WF extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(AnonymousClass8k v, long j) throws IOException;

    WE timeout();
}
