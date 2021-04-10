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

    public MeteredStream(InputStream inputStream, ProgressSource progressSource, long j) {
        super(inputStream);
        this.pi = progressSource;
        this.expected = j;
        if (progressSource != null) {
            progressSource.updateProgress(0, j);
        }
    }

    private final void justRead(long j) {
        if (j != -1) {
            this.count += j;
            if (this.count - this.markedCount > ((long) this.markLimit)) {
                this.markLimit = -1;
            }
            ProgressSource progressSource = this.pi;
            if (progressSource != null) {
                progressSource.updateProgress(this.count, this.expected);
            }
            if (!isMarked()) {
                long j2 = this.expected;
                if (j2 > 0 && this.count >= j2) {
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
    public synchronized int read() {
        if (this.closed) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            justRead(1);
        } else {
            justRead((long) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) {
        if (this.closed) {
            return -1;
        }
        int read = this.in.read(bArr, i, i2);
        justRead((long) read);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) {
        if (this.closed) {
            return 0;
        }
        if (j > this.expected - this.count) {
            j = this.expected - this.count;
        }
        long skip = this.in.skip(j);
        justRead(skip);
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public synchronized void close() {
        if (!this.closed) {
            if (this.pi != null) {
                this.pi.finishTracking();
            }
            this.closed = true;
            this.in.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return this.closed ? 0 : this.in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        if (!this.closed) {
            super.mark(i);
            this.markedCount = this.count;
            this.markLimit = i;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
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
}
