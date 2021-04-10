package sun.net.www;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.net.ProgressSource;

public class MeteredStream extends FilterInputStream {
    protected boolean closed = false;
    protected long count = 0;
    protected long expected;
    protected int markLimit = -1;
    protected long markedCount = 0;
    protected ProgressSource pi;

    public MeteredStream(InputStream is, ProgressSource pi2, long expected2) {
        super(is);
        this.pi = pi2;
        this.expected = expected2;
        if (pi2 != null) {
            pi2.updateProgress(0, expected2);
        }
    }

    private final void justRead(long n) throws IOException {
        if (n != -1) {
            this.count += n;
            if (this.count - this.markedCount > ((long) this.markLimit)) {
                this.markLimit = -1;
            }
            ProgressSource progressSource = this.pi;
            if (progressSource != null) {
                progressSource.updateProgress(this.count, this.expected);
            }
            if (!isMarked()) {
                long j = this.expected;
                if (j > 0 && this.count >= j) {
                    close();
                }
            }
        } else if (!isMarked()) {
            close();
        }
    }

    private boolean isMarked() {
        int i = this.markLimit;
        if (i >= 0 && this.count - this.markedCount <= ((long) i)) {
            return true;
        }
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        int c = this.in.read();
        if (c != -1) {
            justRead(1);
        } else {
            justRead((long) c);
        }
        return c;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            return -1;
        }
        int n = this.in.read(b, off, len);
        justRead((long) n);
        return n;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long n) throws IOException {
        if (this.closed) {
            return 0;
        }
        long n2 = this.in.skip(n > this.expected - this.count ? this.expected - this.count : n);
        justRead(n2);
        return n2;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public synchronized void close() throws IOException {
        if (!this.closed) {
            if (this.pi != null) {
                this.pi.finishTracking();
            }
            this.closed = true;
            this.in.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        return this.closed ? 0 : this.in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readLimit) {
        if (!this.closed) {
            super.mark(readLimit);
            this.markedCount = this.count;
            this.markLimit = readLimit;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (!this.closed) {
            if (isMarked()) {
                this.count = this.markedCount;
                super.reset();
                return;
            }
            throw new IOException("Resetting to an invalid mark");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        if (this.closed) {
            return false;
        }
        return super.markSupported();
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            close();
            if (this.pi != null) {
                this.pi.close();
            }
        } finally {
            super.finalize();
        }
    }
}
