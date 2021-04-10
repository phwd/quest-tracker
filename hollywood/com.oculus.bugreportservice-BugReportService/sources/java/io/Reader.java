package java.io;

public abstract class Reader implements Readable, Closeable {
    protected Object lock;
    private char[] skipBuffer = null;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract int read(char[] cArr, int i, int i2);

    public abstract boolean ready();

    protected Reader(Object obj) {
        if (obj != null) {
            this.lock = obj;
            return;
        }
        throw new NullPointerException();
    }

    public int read(char[] cArr) {
        return read(cArr, 0, cArr.length);
    }
}
