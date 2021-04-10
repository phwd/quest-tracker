package java.io;

import java.util.Arrays;

public class CharArrayWriter extends Writer {
    protected char[] buf;
    protected int count;

    public CharArrayWriter() {
        this(32);
    }

    public CharArrayWriter(int initialSize) {
        if (initialSize >= 0) {
            this.buf = new char[initialSize];
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + initialSize);
    }

    @Override // java.io.Writer
    public void write(int c) {
        synchronized (this.lock) {
            int newcount = this.count + 1;
            if (newcount > this.buf.length) {
                this.buf = Arrays.copyOf(this.buf, Math.max(this.buf.length << 1, newcount));
            }
            this.buf[this.count] = (char) c;
            this.count = newcount;
        }
    }

    @Override // java.io.Writer
    public void write(char[] c, int off, int len) {
        if (off < 0 || off > c.length || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            synchronized (this.lock) {
                int newcount = this.count + len;
                if (newcount > this.buf.length) {
                    this.buf = Arrays.copyOf(this.buf, Math.max(this.buf.length << 1, newcount));
                }
                System.arraycopy((Object) c, off, (Object) this.buf, this.count, len);
                this.count = newcount;
            }
        }
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) {
        synchronized (this.lock) {
            int newcount = this.count + len;
            if (newcount > this.buf.length) {
                this.buf = Arrays.copyOf(this.buf, Math.max(this.buf.length << 1, newcount));
            }
            str.getChars(off, off + len, this.buf, this.count);
            this.count = newcount;
        }
    }

    public void writeTo(Writer out) throws IOException {
        synchronized (this.lock) {
            out.write(this.buf, 0, this.count);
        }
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public CharArrayWriter append(CharSequence csq) {
        String s = csq == null ? "null" : csq.toString();
        write(s, 0, s.length());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public CharArrayWriter append(CharSequence csq, int start, int end) {
        String s = (csq == null ? "null" : csq).subSequence(start, end).toString();
        write(s, 0, s.length());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public CharArrayWriter append(char c) {
        write(c);
        return this;
    }

    public void reset() {
        this.count = 0;
    }

    public char[] toCharArray() {
        char[] copyOf;
        synchronized (this.lock) {
            copyOf = Arrays.copyOf(this.buf, this.count);
        }
        return copyOf;
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        String str;
        synchronized (this.lock) {
            str = new String(this.buf, 0, this.count);
        }
        return str;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
    }
}
