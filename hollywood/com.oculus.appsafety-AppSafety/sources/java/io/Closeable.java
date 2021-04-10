package java.io;

public interface Closeable extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close() throws IOException;
}
