package java.nio.channels;

import java.io.Closeable;
import java.io.IOException;

public interface Channel extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    boolean isOpen();
}
