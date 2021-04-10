package java.io;

public abstract class FilterWriter extends Writer {
    protected Writer out;

    protected FilterWriter(Writer out2) {
        super(out2);
        this.out = out2;
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        this.out.write(c);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.out.write(cbuf, off, len);
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) throws IOException {
        this.out.write(str, off, len);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
