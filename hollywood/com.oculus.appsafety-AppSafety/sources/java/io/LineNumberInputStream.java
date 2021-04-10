package java.io;

@Deprecated
public class LineNumberInputStream extends FilterInputStream {
    int lineNumber;
    int markLineNumber;
    int markPushBack = -1;
    int pushBack = -1;

    public LineNumberInputStream(InputStream in) {
        super(in);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int c = this.pushBack;
        if (c != -1) {
            this.pushBack = -1;
        } else {
            c = this.in.read();
        }
        if (c != 10) {
            if (c != 13) {
                return c;
            }
            this.pushBack = this.in.read();
            if (this.pushBack == 10) {
                this.pushBack = -1;
            }
        }
        this.lineNumber++;
        return 10;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            int c = read();
            if (c == -1) {
                return -1;
            }
            b[off] = (byte) c;
            int i = 1;
            while (true) {
                if (i >= len) {
                    break;
                }
                try {
                    int c2 = read();
                    if (c2 == -1) {
                        break;
                    }
                    b[off + i] = (byte) c2;
                    i++;
                } catch (IOException e) {
                }
            }
            return i;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        int nr;
        long remaining = n;
        if (n <= 0) {
            return 0;
        }
        byte[] data = new byte[2048];
        while (remaining > 0 && (nr = read(data, 0, (int) Math.min((long) 2048, remaining))) >= 0) {
            remaining -= (long) nr;
        }
        return n - remaining;
    }

    public void setLineNumber(int lineNumber2) {
        this.lineNumber = lineNumber2;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.pushBack == -1 ? super.available() / 2 : (super.available() / 2) + 1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int readlimit) {
        this.markLineNumber = this.lineNumber;
        this.markPushBack = this.pushBack;
        this.in.mark(readlimit);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        this.lineNumber = this.markLineNumber;
        this.pushBack = this.markPushBack;
        this.in.reset();
    }
}
