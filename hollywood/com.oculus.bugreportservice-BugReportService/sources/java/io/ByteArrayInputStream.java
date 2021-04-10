package java.io;

public class ByteArrayInputStream extends InputStream {
    protected byte[] buf;
    protected int count;
    protected int mark = 0;
    protected int pos;

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public ByteArrayInputStream(byte[] bArr) {
        this.buf = bArr;
        this.pos = 0;
        this.count = bArr.length;
    }

    public ByteArrayInputStream(byte[] bArr, int i, int i2) {
        this.buf = bArr;
        this.pos = i;
        this.count = Math.min(i2 + i, bArr.length);
        this.mark = i;
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        int i;
        if (this.pos < this.count) {
            byte[] bArr = this.buf;
            int i2 = this.pos;
            this.pos = i2 + 1;
            i = bArr[i2] & 255;
        } else {
            i = -1;
        }
        return i;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i >= 0 && i2 >= 0) {
                if (i2 <= bArr.length - i) {
                    if (this.pos >= this.count) {
                        return -1;
                    }
                    int i3 = this.count - this.pos;
                    if (i2 > i3) {
                        i2 = i3;
                    }
                    if (i2 <= 0) {
                        return 0;
                    }
                    System.arraycopy(this.buf, this.pos, bArr, i, i2);
                    this.pos += i2;
                    return i2;
                }
            }
            throw new IndexOutOfBoundsException();
        }
        throw new NullPointerException();
    }

    @Override // java.io.InputStream
    public synchronized long skip(long j) {
        long j2;
        j2 = (long) (this.count - this.pos);
        if (j < j2) {
            j2 = 0;
            if (j >= 0) {
                j2 = j;
            }
        }
        this.pos = (int) (((long) this.pos) + j2);
        return j2;
    }

    @Override // java.io.InputStream
    public synchronized int available() {
        return this.count - this.pos;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.mark = this.pos;
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.pos = this.mark;
    }
}
