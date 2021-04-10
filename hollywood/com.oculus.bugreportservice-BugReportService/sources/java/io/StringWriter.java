package java.io;

public class StringWriter extends Writer {
    private StringBuffer buf = new StringBuffer();

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer
    public void flush() {
    }

    public StringWriter() {
        this.lock = this.buf;
    }

    @Override // java.io.Writer
    public void write(int i) {
        this.buf.append((char) i);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            this.buf.append(cArr, i, i2);
        }
    }

    @Override // java.io.Writer
    public void write(String str) {
        this.buf.append(str);
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        this.buf.append(str.substring(i, i2 + i));
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public StringWriter append(CharSequence charSequence) {
        if (charSequence == null) {
            write("null");
        } else {
            write(charSequence.toString());
        }
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public StringWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence.subSequence(i, i2).toString());
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
}
