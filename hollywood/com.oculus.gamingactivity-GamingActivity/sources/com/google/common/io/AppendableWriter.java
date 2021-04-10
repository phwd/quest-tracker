package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
class AppendableWriter extends Writer {
    private boolean closed;
    private final Appendable target;

    AppendableWriter(Appendable target2) {
        this.target = (Appendable) Preconditions.checkNotNull(target2);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        checkNotClosed();
        this.target.append(new String(cbuf, off, len));
    }

    @Override // java.io.Writer
    public void write(int c) throws IOException {
        checkNotClosed();
        this.target.append((char) c);
    }

    @Override // java.io.Writer
    public void write(@NullableDecl String str) throws IOException {
        checkNotClosed();
        this.target.append(str);
    }

    @Override // java.io.Writer
    public void write(@NullableDecl String str, int off, int len) throws IOException {
        checkNotClosed();
        this.target.append(str, off, off + len);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        checkNotClosed();
        if (this.target instanceof Flushable) {
            ((Flushable) this.target).flush();
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        if (this.target instanceof Closeable) {
            ((Closeable) this.target).close();
        }
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(char c) throws IOException {
        checkNotClosed();
        this.target.append(c);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(@NullableDecl CharSequence charSeq) throws IOException {
        checkNotClosed();
        this.target.append(charSeq);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public Writer append(@NullableDecl CharSequence charSeq, int start, int end) throws IOException {
        checkNotClosed();
        this.target.append(charSeq, start, end);
        return this;
    }

    private void checkNotClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write to a closed writer.");
        }
    }
}
