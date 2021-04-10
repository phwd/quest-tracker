package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
final class MultiInputStream extends InputStream {
    @NullableDecl
    private InputStream in;
    private Iterator<? extends ByteSource> it;

    public MultiInputStream(Iterator<? extends ByteSource> it2) throws IOException {
        this.it = (Iterator) Preconditions.checkNotNull(it2);
        advance();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (this.in != null) {
            try {
                this.in.close();
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
        if (this.in == null) {
            return 0;
        }
        return this.in.available();
    }

    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (this.in != null) {
            int result = this.in.read();
            if (result != -1) {
                return result;
            }
            advance();
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(@NullableDecl byte[] b, int off, int len) throws IOException {
        while (this.in != null) {
            int result = this.in.read(b, off, len);
            if (result != -1) {
                return result;
            }
            advance();
        }
        return -1;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        if (this.in == null || n <= 0) {
            return 0;
        }
        long result = this.in.skip(n);
        if (result != 0) {
            return result;
        }
        if (read() == -1) {
            return 0;
        }
        return 1 + this.in.skip(n - 1);
    }
}
