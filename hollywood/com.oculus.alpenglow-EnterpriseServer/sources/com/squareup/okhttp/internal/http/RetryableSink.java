package com.squareup.okhttp.internal.http;

import X.AnonymousClass006;
import X.AnonymousClass0HR;
import X.AnonymousClass0h1;
import X.C04540gz;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;

public final class RetryableSink implements AnonymousClass0h1 {
    public boolean closed;
    public final AnonymousClass0HR content;
    public final int limit;

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            long j = this.content.A00;
            int i = this.limit;
            if (j < ((long) i)) {
                throw new ProtocolException("content-length promised " + i + " bytes, but received " + j);
            }
        }
    }

    public long contentLength() throws IOException {
        return this.content.A00;
    }

    @Override // X.AnonymousClass0h1
    public C04540gz timeout() {
        return C04540gz.NONE;
    }

    @Override // X.AnonymousClass0h1
    public void write(AnonymousClass0HR r9, long j) throws IOException {
        if (!this.closed) {
            Util.checkOffsetAndCount(r9.A00, 0, j);
            int i = this.limit;
            if (i == -1 || this.content.A00 <= ((long) i) - j) {
                this.content.write(r9, j);
                return;
            }
            throw new ProtocolException(AnonymousClass006.A02("exceeded content-length limit of ", i, " bytes"));
        }
        throw new IllegalStateException("closed");
    }

    public void writeToSocket(AnonymousClass0h1 r8) throws IOException {
        AnonymousClass0HR r2 = new AnonymousClass0HR();
        AnonymousClass0HR r1 = this.content;
        r1.A0H(r2, 0, r1.A00);
        r8.write(r2, r2.A00);
    }

    public RetryableSink() {
        this(-1);
    }

    public RetryableSink(int i) {
        this.content = new AnonymousClass0HR();
        this.limit = i;
    }
}
