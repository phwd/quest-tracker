package java.io;

public interface ObjectOutput extends DataOutput, AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close() throws IOException;

    void flush() throws IOException;

    @Override // java.io.DataOutput
    void write(int i) throws IOException;

    @Override // java.io.DataOutput
    void write(byte[] bArr) throws IOException;

    @Override // java.io.DataOutput
    void write(byte[] bArr, int i, int i2) throws IOException;

    void writeObject(Object obj) throws IOException;
}
