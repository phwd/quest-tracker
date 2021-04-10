package java.util.zip;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DeflaterInputStream extends FilterInputStream {
    protected final byte[] buf;
    protected final Deflater def;
    private byte[] rbuf;
    private boolean reachEOF;
    private boolean usesDefaultDeflater;

    private void ensureOpen() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
    }

    public DeflaterInputStream(InputStream in) {
        this(in, new Deflater());
        this.usesDefaultDeflater = true;
    }

    public DeflaterInputStream(InputStream in, Deflater defl) {
        this(in, defl, 512);
    }

    public DeflaterInputStream(InputStream in, Deflater defl, int bufLen) {
        super(in);
        this.rbuf = new byte[1];
        this.usesDefaultDeflater = false;
        this.reachEOF = false;
        if (in == null) {
            throw new NullPointerException("Null input");
        } else if (defl == null) {
            throw new NullPointerException("Null deflater");
        } else if (bufLen >= 1) {
            this.def = defl;
            this.buf = new byte[bufLen];
        } else {
            throw new IllegalArgumentException("Buffer size < 1");
        }
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (this.in != null) {
            try {
                if (this.usesDefaultDeflater) {
                    this.def.end();
                }
                this.in.close();
            } finally {
                this.in = null;
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (read(this.rbuf, 0, 1) <= 0) {
            return -1;
        }
        return this.rbuf[0] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        ensureOpen();
        if (b == null) {
            throw new NullPointerException("Null buffer for read");
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            int cnt = 0;
            while (len > 0 && !this.def.finished()) {
                if (this.def.needsInput()) {
                    InputStream inputStream = this.in;
                    byte[] bArr = this.buf;
                    int n = inputStream.read(bArr, 0, bArr.length);
                    if (n < 0) {
                        this.def.finish();
                    } else if (n > 0) {
                        this.def.setInput(this.buf, 0, n);
                    }
                }
                int n2 = this.def.deflate(b, off, len);
                cnt += n2;
                off += n2;
                len -= n2;
            }
            if (!this.def.finished()) {
                return cnt;
            }
            this.reachEOF = true;
            if (cnt == 0) {
                return -1;
            }
            return cnt;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        if (n >= 0) {
            ensureOpen();
            if (this.rbuf.length < 512) {
                this.rbuf = new byte[512];
            }
            int total = (int) Math.min(n, 2147483647L);
            long cnt = 0;
            while (total > 0) {
                byte[] bArr = this.rbuf;
                int len = read(bArr, 0, total <= bArr.length ? total : bArr.length);
                if (len < 0) {
                    break;
                }
                cnt += (long) len;
                total -= len;
            }
            return cnt;
        }
        throw new IllegalArgumentException("negative skip length");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        ensureOpen();
        if (this.reachEOF) {
            return 0;
        }
        return 1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int limit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }
}
