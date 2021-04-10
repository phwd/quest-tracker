package java.io;

public abstract class Writer implements Appendable, Closeable, Flushable {
    protected Object lock;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract void flush();

    public abstract void write(int i);

    public abstract void write(String str, int i, int i2);

    public abstract void write(char[] cArr, int i, int i2);

    protected Writer() {
        this.lock = this;
    }

    protected Writer(Object obj) {
        if (obj != null) {
            this.lock = obj;
            return;
        }
        throw new NullPointerException();
    }

    public void write(String str) {
        write(str, 0, str.length());
    }

    @Override // java.lang.Appendable
    public Writer append(CharSequence charSequence) {
        if (charSequence == null) {
            write("null");
        } else {
            write(charSequence.toString());
        }
        return this;
    }

    @Override // java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence.subSequence(i, i2).toString());
        return this;
    }

    @Override // java.lang.Appendable
    public Writer append(char c) {
        write(c);
        return this;
    }
}
