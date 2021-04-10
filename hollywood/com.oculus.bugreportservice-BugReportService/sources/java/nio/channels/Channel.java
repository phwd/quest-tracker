package java.nio.channels;

import java.io.Closeable;

public interface Channel extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    boolean isOpen();
}
