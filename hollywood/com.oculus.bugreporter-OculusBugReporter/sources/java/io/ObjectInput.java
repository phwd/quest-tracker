package java.io;

public interface ObjectInput extends DataInput, AutoCloseable {
    int available() throws IOException;

    @Override // java.lang.AutoCloseable
    void close() throws IOException;

    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    Object readObject() throws ClassNotFoundException, IOException;

    long skip(long j) throws IOException;
}
