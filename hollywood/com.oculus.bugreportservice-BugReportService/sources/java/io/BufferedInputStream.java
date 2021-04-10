package java.io;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class BufferedInputStream extends FilterInputStream {
    private static final AtomicReferenceFieldUpdater bufUpdater = AtomicReferenceFieldUpdater.newUpdater(BufferedInputStream.class, byte[].class, "buf");
    protected volatile byte[] buf;
    protected int count;
    protected int marklimit;
    protected int markpos;
    protected int pos;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    private InputStream getInIfOpen() {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            return inputStream;
        }
        throw new IOException("Stream closed");
    }

    private byte[] getBufIfOpen() {
        byte[] bArr = this.buf;
        if (bArr != null) {
            return bArr;
        }
        throw new IOException("Stream closed");
    }

    public BufferedInputStream(InputStream inputStream) {
        this(inputStream, 8192);
    }

    public BufferedInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.markpos = -1;
        if (i > 0) {
            this.buf = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    private void fill() {
        byte[] bufIfOpen = getBufIfOpen();
        int i = this.markpos;
        if (i < 0) {
            this.pos = 0;
        } else {
            int i2 = this.pos;
            if (i2 >= bufIfOpen.length) {
                if (i > 0) {
                    int i3 = i2 - i;
                    System.arraycopy(bufIfOpen, i, bufIfOpen, 0, i3);
                    this.pos = i3;
                    this.markpos = 0;
                } else if (bufIfOpen.length >= this.marklimit) {
                    this.markpos = -1;
                    this.pos = 0;
                } else {
                    int i4 = 2147483639;
                    if (bufIfOpen.length < 2147483639) {
                        if (i2 <= 2147483639 - i2) {
                            i4 = i2 * 2;
                        }
                        int i5 = this.marklimit;
                        if (i4 <= i5) {
                            i5 = i4;
                        }
                        byte[] bArr = new byte[i5];
                        System.arraycopy(bufIfOpen, 0, bArr, 0, this.pos);
                        if (bufUpdater.compareAndSet(this, bufIfOpen, bArr)) {
                            bufIfOpen = bArr;
                        } else {
                            throw new IOException("Stream closed");
                        }
                    } else {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                }
            }
        }
        this.count = this.pos;
        InputStream inIfOpen = getInIfOpen();
        int i6 = this.pos;
        int read = inIfOpen.read(bufIfOpen, i6, bufIfOpen.length - i6);
        if (read > 0) {
            this.count = read + this.pos;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        if (this.pos >= this.count) {
            fill();
            if (this.pos >= this.count) {
                return -1;
            }
        }
        byte[] bufIfOpen = getBufIfOpen();
        int i = this.pos;
        this.pos = i + 1;
        return bufIfOpen[i] & 255;
    }

    private int read1(byte[] bArr, int i, int i2) {
        int i3 = this.count - this.pos;
        if (i3 <= 0) {
            if (i2 >= getBufIfOpen().length && this.markpos < 0) {
                return getInIfOpen().read(bArr, i, i2);
            }
            fill();
            i3 = this.count - this.pos;
            if (i3 <= 0) {
                return -1;
            }
        }
        if (i3 < i2) {
            i2 = i3;
        }
        System.arraycopy(getBufIfOpen(), this.pos, bArr, i, i2);
        this.pos += i2;
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        getBufIfOpen();
        int i3 = i + i2;
        if ((i | i2 | i3 | (bArr.length - i3)) >= 0) {
            int i4 = 0;
            if (i2 == 0) {
                return 0;
            }
            while (true) {
                int read1 = read1(bArr, i + i4, i2 - i4);
                if (read1 <= 0) {
                    if (i4 == 0) {
                        i4 = read1;
                    }
                    return i4;
                }
                i4 += read1;
                if (i4 >= i2) {
                    return i4;
                }
                InputStream inputStream = this.in;
                if (inputStream != null && inputStream.available() <= 0) {
                    return i4;
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) {
        getBufIfOpen();
        if (j <= 0) {
            return 0;
        }
        long j2 = (long) (this.count - this.pos);
        if (j2 <= 0) {
            if (this.markpos < 0) {
                return getInIfOpen().skip(j);
            }
            fill();
            j2 = (long) (this.count - this.pos);
            if (j2 <= 0) {
                return 0;
            }
        }
        if (j2 < j) {
            j = j2;
        }
        this.pos = (int) (((long) this.pos) + j);
        return j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        int i;
        int i2 = this.count - this.pos;
        int available = getInIfOpen().available();
        i = Integer.MAX_VALUE;
        if (i2 <= Integer.MAX_VALUE - available) {
            i = i2 + available;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        this.marklimit = i;
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        getBufIfOpen();
        if (this.markpos >= 0) {
            this.pos = this.markpos;
        } else {
            throw new IOException("Resetting to invalid mark");
        }
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        byte[] bArr;
        do {
            bArr = this.buf;
            if (bArr == null) {
                return;
            }
        } while (!bufUpdater.compareAndSet(this, bArr, null));
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
