package java.nio.channels;

import java.io.IOException;

public interface InterruptibleChannel extends Channel {
    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    void close() throws IOException;
}
