package java.io;

public abstract class OutputStream implements Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public void flush() {
    }

    public abstract void write(int i);

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                write(bArr[i + i4]);
            }
        }
    }
}
