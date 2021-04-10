package okhttp3;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.InternalCache;

public final class Cache implements Closeable, Flushable {
    final DiskLruCache cache;
    final InternalCache internalCache;

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.cache.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cache.close();
    }
}
