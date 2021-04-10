package java.io;

import java.util.Arrays;

public class ByteArrayOutputStream extends OutputStream {
    protected byte[] buf;
    protected int count;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public ByteArrayOutputStream() {
        this(32);
    }

    public ByteArrayOutputStream(int i) {
        if (i >= 0) {
            this.buf = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    private void ensureCapacity(int i) {
        if (i - this.buf.length > 0) {
            grow(i);
        }
    }

    private void grow(int i) {
        int length = this.buf.length << 1;
        if (length - i < 0) {
            length = i;
        }
        if (length - 2147483639 > 0) {
            length = hugeCapacity(i);
        }
        this.buf = Arrays.copyOf(this.buf, length);
    }

    private static int hugeCapacity(int i) {
        if (i >= 0) {
            return i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        throw new OutOfMemoryError();
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
        ensureCapacity(this.count + 1);
        this.buf[this.count] = (byte) i;
        this.count++;
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        if (i >= 0) {
            if (i <= bArr.length && i2 >= 0 && (i + i2) - bArr.length <= 0) {
                ensureCapacity(this.count + i2);
                System.arraycopy(bArr, i, this.buf, this.count, i2);
                this.count += i2;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public synchronized void reset() {
        this.count = 0;
    }

    public synchronized byte[] toByteArray() {
        return Arrays.copyOf(this.buf, this.count);
    }

    public synchronized String toString() {
        new String(this.buf, 0, this.count);
        throw null;
    }

    public synchronized String toString(String str) {
        new String(this.buf, 0, this.count, str);
        throw null;
    }
}
