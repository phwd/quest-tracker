package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

@GwtIncompatible
final class CharSequenceReader extends Reader {
    private int mark;
    private int pos;
    private CharSequence seq;

    public CharSequenceReader(CharSequence seq2) {
        this.seq = (CharSequence) Preconditions.checkNotNull(seq2);
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        return this.seq.length() - this.pos;
    }

    @Override // java.lang.Readable, java.io.Reader
    public synchronized int read(CharBuffer target) throws IOException {
        int charsToRead;
        Preconditions.checkNotNull(target);
        checkOpen();
        if (!hasRemaining()) {
            charsToRead = -1;
        } else {
            charsToRead = Math.min(target.remaining(), remaining());
            for (int i = 0; i < charsToRead; i++) {
                CharSequence charSequence = this.seq;
                int i2 = this.pos;
                this.pos = i2 + 1;
                target.put(charSequence.charAt(i2));
            }
        }
        return charsToRead;
    }

    @Override // java.io.Reader
    public synchronized int read() throws IOException {
        char c;
        checkOpen();
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i = this.pos;
            this.pos = i + 1;
            c = charSequence.charAt(i);
        } else {
            c = 65535;
        }
        return c;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cbuf, int off, int len) throws IOException {
        int charsToRead;
        Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        checkOpen();
        if (!hasRemaining()) {
            charsToRead = -1;
        } else {
            charsToRead = Math.min(len, remaining());
            for (int i = 0; i < charsToRead; i++) {
                CharSequence charSequence = this.seq;
                int i2 = this.pos;
                this.pos = i2 + 1;
                cbuf[off + i] = charSequence.charAt(i2);
            }
        }
        return charsToRead;
    }

    @Override // java.io.Reader
    public synchronized long skip(long n) throws IOException {
        int charsToSkip;
        Preconditions.checkArgument(n >= 0, "n (%s) may not be negative", n);
        checkOpen();
        charsToSkip = (int) Math.min((long) remaining(), n);
        this.pos += charsToSkip;
        return (long) charsToSkip;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() throws IOException {
        checkOpen();
        return true;
    }

    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public synchronized void mark(int readAheadLimit) throws IOException {
        Preconditions.checkArgument(readAheadLimit >= 0, "readAheadLimit (%s) may not be negative", readAheadLimit);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.seq = null;
    }
}
