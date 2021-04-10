package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface WG extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    @Override // java.io.Flushable
    void flush() throws IOException;

    WE timeout();

    void write(AnonymousClass8k v, long j) throws IOException;
}
