package java.io;

public abstract class FilterReader extends Reader {
    protected Reader in;

    protected FilterReader(Reader in2) {
        super(in2);
        this.in = in2;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return this.in.read();
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        return this.in.read(cbuf, off, len);
    }

    @Override // java.io.Reader
    public long skip(long n) throws IOException {
        return this.in.skip(n);
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return this.in.ready();
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.in.markSupported();
    }

    @Override // java.io.Reader
    public void mark(int readAheadLimit) throws IOException {
        this.in.mark(readAheadLimit);
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.in.reset();
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }
}
