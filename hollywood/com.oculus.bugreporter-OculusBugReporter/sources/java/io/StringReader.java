package java.io;

public class StringReader extends Reader {
    private int length;
    private int mark = 0;
    private int next = 0;
    private String str;

    public StringReader(String s) {
        this.str = s;
        this.length = s.length();
    }

    private void ensureOpen() throws IOException {
        if (this.str == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.next >= this.length) {
                return -1;
            }
            String str2 = this.str;
            int i = this.next;
            this.next = i + 1;
            return str2.charAt(i);
        }
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            } else {
                if (this.next >= this.length) {
                    return -1;
                }
                int n = Math.min(this.length - this.next, len);
                this.str.getChars(this.next, this.next + n, cbuf, off);
                this.next += n;
                return n;
            }
        }
    }

    @Override // java.io.Reader
    public long skip(long ns) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (this.next >= this.length) {
                return 0;
            }
            long n = Math.max((long) (-this.next), Math.min((long) (this.length - this.next), ns));
            this.next = (int) (((long) this.next) + n);
            return n;
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
        }
        return true;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public void mark(int readAheadLimit) throws IOException {
        if (readAheadLimit >= 0) {
            synchronized (this.lock) {
                ensureOpen();
                this.mark = this.next;
            }
            return;
        }
        throw new IllegalArgumentException("Read-ahead limit < 0");
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            this.next = this.mark;
        }
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() {
        this.str = null;
    }
}
