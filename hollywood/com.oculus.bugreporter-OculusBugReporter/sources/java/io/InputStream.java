package java.io;

import android.support.v4.media.session.PlaybackStateCompat;

public abstract class InputStream implements Closeable {
    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    public abstract int read() throws IOException;

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            int c = read();
            if (c == -1) {
                return -1;
            }
            b[off] = (byte) c;
            int i = 1;
            while (true) {
                if (i >= len) {
                    break;
                }
                try {
                    int c2 = read();
                    if (c2 == -1) {
                        break;
                    }
                    b[off + i] = (byte) c2;
                    i++;
                } catch (IOException e) {
                }
            }
            return i;
        }
    }

    public long skip(long n) throws IOException {
        int nr;
        long remaining = n;
        if (n <= 0) {
            return 0;
        }
        int size = (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, remaining);
        byte[] skipBuffer = new byte[size];
        while (remaining > 0 && (nr = read(skipBuffer, 0, (int) Math.min((long) size, remaining))) >= 0) {
            remaining -= (long) nr;
        }
        return n - remaining;
    }

    public int available() throws IOException {
        return 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public synchronized void mark(int readlimit) {
    }

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}
