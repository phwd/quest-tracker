package java.io;

public class PushbackReader extends FilterReader {
    private char[] buf;
    private int pos;

    public PushbackReader(Reader in, int size) {
        super(in);
        if (size > 0) {
            this.buf = new char[size];
            this.pos = size;
            return;
        }
        throw new IllegalArgumentException("size <= 0");
    }

    public PushbackReader(Reader in) {
        this(in, 1);
    }

    private void ensureOpen() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.pos < this.buf.length) {
                char[] cArr = this.buf;
                int i = this.pos;
                this.pos = i + 1;
                return cArr[i];
            }
            return super.read();
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (len > 0) {
                int avail = this.buf.length - this.pos;
                if (avail > 0) {
                    if (len < avail) {
                        avail = len;
                    }
                    System.arraycopy((Object) this.buf, this.pos, (Object) cbuf, off, avail);
                    this.pos += avail;
                    off += avail;
                    len -= avail;
                }
                if (len <= 0) {
                    return avail;
                }
                int len2 = super.read(cbuf, off, len);
                int i = -1;
                if (len2 == -1) {
                    if (avail != 0) {
                        i = avail;
                    }
                    return i;
                }
                return avail + len2;
            } else if (len >= 0) {
                if (off >= 0) {
                    try {
                        if (off <= cbuf.length) {
                            return 0;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new IndexOutOfBoundsException();
                    }
                }
                throw new IndexOutOfBoundsException();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void unread(int c) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.pos != 0) {
                char[] cArr = this.buf;
                int i = this.pos - 1;
                this.pos = i;
                cArr[i] = (char) c;
            } else {
                throw new IOException("Pushback buffer overflow");
            }
        }
    }

    public void unread(char[] cbuf, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (len <= this.pos) {
                this.pos -= len;
                System.arraycopy((Object) cbuf, off, (Object) this.buf, this.pos, len);
            } else {
                throw new IOException("Pushback buffer overflow");
            }
        }
    }

    public void unread(char[] cbuf) throws IOException {
        unread(cbuf, 0, cbuf.length);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (this.lock) {
            ensureOpen();
            if (this.pos >= this.buf.length) {
                if (!super.ready()) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public void mark(int readAheadLimit) throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterReader, java.io.Reader
    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.Closeable, java.io.FilterReader, java.io.Reader, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.buf = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public long skip(long n) throws IOException {
        if (n >= 0) {
            synchronized (this.lock) {
                ensureOpen();
                int avail = this.buf.length - this.pos;
                if (avail > 0) {
                    if (n <= ((long) avail)) {
                        this.pos = (int) (((long) this.pos) + n);
                        return n;
                    }
                    this.pos = this.buf.length;
                    n -= (long) avail;
                }
                return ((long) avail) + super.skip(n);
            }
        }
        throw new IllegalArgumentException("skip value is negative");
    }
}
