package java.nio.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public interface WatchService extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    WatchKey poll();

    WatchKey poll(long j, TimeUnit timeUnit) throws InterruptedException;

    WatchKey take() throws InterruptedException;
}
