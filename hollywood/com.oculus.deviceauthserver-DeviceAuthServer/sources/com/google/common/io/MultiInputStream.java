package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.Nullable;

final class MultiInputStream extends InputStream {
    private InputStream in;
    private Iterator<? extends ByteSource> it;

    public MultiInputStream(Iterator<? extends ByteSource> it2) throws IOException {
        this.it = (Iterator) Preconditions.checkNotNull(it2);
        advance();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.it.hasNext()) {
            this.in = ((ByteSource) this.it.next()).openStream();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return -1;
        }
        int result = inputStream.read();
        if (result != -1) {
            return result;
        }
        advance();
        return read();
    }

    @Override // java.io.InputStream
    public int read(@Nullable byte[] b, int off, int len) throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return -1;
        }
        int result = inputStream.read(b, off, len);
        if (result != -1) {
            return result;
        }
        advance();
        return read(b, off, len);
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null || n <= 0) {
            return 0;
        }
        long result = inputStream.skip(n);
        if (result != 0) {
            return result;
        }
        if (read() == -1) {
            return 0;
        }
        return this.in.skip(n - 1) + 1;
    }
}
