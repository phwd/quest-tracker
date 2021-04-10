package java.util.zip;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InflaterInputStream extends FilterInputStream {
    private byte[] b = new byte[512];
    protected byte[] buf;
    protected boolean closed = false;
    protected Inflater inf;
    protected int len;
    private boolean reachEOF = false;
    private byte[] singleByteBuf = new byte[1];

    /* access modifiers changed from: protected */
    public void fill() {
        throw null;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    private void ensureOpen() {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    public InflaterInputStream(InputStream inputStream, Inflater inflater, int i) {
        super(inputStream);
        if (inputStream == null || inflater == null) {
            throw new NullPointerException();
        } else if (i > 0) {
            this.inf = inflater;
            this.buf = new byte[i];
        } else {
            throw new IllegalArgumentException("buffer size <= 0");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        ensureOpen();
        if (read(this.singleByteBuf, 0, 1) == -1) {
            return -1;
        }
        return Byte.toUnsignedInt(this.singleByteBuf[0]);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        ensureOpen();
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            while (true) {
                try {
                    int inflate = this.inf.inflate(bArr, i, i2);
                    if (inflate != 0) {
                        return inflate;
                    }
                    if (this.inf.finished()) {
                        break;
                    } else if (this.inf.needsDictionary()) {
                        break;
                    } else if (this.inf.needsInput()) {
                        fill();
                    }
                } catch (DataFormatException e) {
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Invalid ZLIB data format";
                    }
                    throw new ZipException(message);
                }
            }
            this.reachEOF = true;
            return -1;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        if (j >= 0) {
            ensureOpen();
            int min = (int) Math.min(j, 2147483647L);
            int i = 0;
            while (true) {
                if (i >= min) {
                    break;
                }
                int i2 = min - i;
                byte[] bArr = this.b;
                if (i2 > bArr.length) {
                    i2 = bArr.length;
                }
                int read = read(this.b, 0, i2);
                if (read == -1) {
                    this.reachEOF = true;
                    break;
                }
                i += read;
            }
            return (long) i;
        }
        throw new IllegalArgumentException("negative skip length");
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        if (!this.closed) {
            this.inf.end();
            this.in.close();
            this.closed = true;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        throw new IOException("mark/reset not supported");
    }
}
