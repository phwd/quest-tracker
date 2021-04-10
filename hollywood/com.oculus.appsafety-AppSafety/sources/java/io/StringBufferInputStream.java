package java.io;

@Deprecated
public class StringBufferInputStream extends InputStream {
    protected String buffer;
    protected int count;
    protected int pos;

    public StringBufferInputStream(String s) {
        this.buffer = s;
        this.count = s.length();
    }

    @Override // java.io.InputStream
    public synchronized int read() {
        int i;
        if (this.pos < this.count) {
            String str = this.buffer;
            int i2 = this.pos;
            this.pos = i2 + 1;
            i = str.charAt(i2) & 255;
        } else {
            i = -1;
        }
        return i;
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] b, int off, int len) {
        if (b != null) {
            if (off >= 0) {
                if (off <= b.length && len >= 0 && off + len <= b.length && off + len >= 0) {
                    if (this.pos >= this.count) {
                        return -1;
                    }
                    int avail = this.count - this.pos;
                    if (len > avail) {
                        len = avail;
                    }
                    if (len <= 0) {
                        return 0;
                    }
                    String s = this.buffer;
                    int cnt = len;
                    while (true) {
                        cnt--;
                        if (cnt < 0) {
                            return len;
                        }
                        int i = this.pos;
                        this.pos = i + 1;
                        b[off] = (byte) s.charAt(i);
                        off++;
                    }
                }
            }
            throw new IndexOutOfBoundsException();
        }
        throw new NullPointerException();
    }

    @Override // java.io.InputStream
    public synchronized long skip(long n) {
        if (n < 0) {
            return 0;
        }
        if (n > ((long) (this.count - this.pos))) {
            n = (long) (this.count - this.pos);
        }
        this.pos = (int) (((long) this.pos) + n);
        return n;
    }

    @Override // java.io.InputStream
    public synchronized int available() {
        return this.count - this.pos;
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.pos = 0;
    }
}
