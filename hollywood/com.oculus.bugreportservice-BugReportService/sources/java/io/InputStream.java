package java.io;

public abstract class InputStream implements Closeable {
    public int available() {
        return 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public boolean markSupported() {
        return false;
    }

    public abstract int read();

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            int read = read();
            if (read == -1) {
                return -1;
            }
            bArr[i] = (byte) read;
            int i3 = 1;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                try {
                    int read2 = read();
                    if (read2 == -1) {
                        break;
                    }
                    bArr[i + i3] = (byte) read2;
                    i3++;
                } catch (IOException unused) {
                }
            }
            return i3;
        }
    }

    public long skip(long j) {
        int read;
        if (j <= 0) {
            return 0;
        }
        int min = (int) Math.min(2048L, j);
        byte[] bArr = new byte[min];
        long j2 = j;
        while (j2 > 0 && (read = read(bArr, 0, (int) Math.min((long) min, j2))) >= 0) {
            j2 -= (long) read;
        }
        return j - j2;
    }

    public synchronized void mark(int i) {
    }

    public synchronized void reset() {
        throw new IOException("mark/reset not supported");
    }
}
