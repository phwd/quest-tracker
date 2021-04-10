package java.io;

public class StringWriter extends Writer {
    private StringBuffer buf;

    public StringWriter() {
        this.buf = new StringBuffer();
        this.lock = this.buf;
    }

    public StringWriter(int initialSize) {
        if (initialSize >= 0) {
            this.buf = new StringBuffer(initialSize);
            this.lock = this.buf;
            return;
        }
        throw new IllegalArgumentException("Negative buffer size");
    }

    @Override // java.io.Writer
    public void write(int c) {
        this.buf.append((char) c);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) {
        if (off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            this.buf.append(cbuf, off, len);
        }
    }

    @Override // java.io.Writer
    public void write(String str) {
        this.buf.append(str);
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) {
        this.buf.append(str.substring(off, off + len));
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public StringWriter append(CharSequence csq) {
        if (csq == null) {
            write("null");
        } else {
            write(csq.toString());
        }
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public StringWriter append(CharSequence csq, int start, int end) {
        write((csq == null ? "null" : csq).subSequence(start, end).toString());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public StringWriter append(char c) {
        write(c);
        return this;
    }

    public String toString() {
        return this.buf.toString();
    }

    public StringBuffer getBuffer() {
        return this.buf;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
    }
}
