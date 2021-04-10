package com.squareup.okhttp.internal.http;

import X.AbstractC07640v7;
import X.AnonymousClass006;
import X.AnonymousClass0B3;
import X.C07620v5;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;

public final class RetryableSink implements AbstractC07640v7 {
    public boolean closed;
    public final AnonymousClass0B3 content;
    public final int limit;

    @Override // X.AbstractC07640v7, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            long j = this.content.A00;
            int i = this.limit;
            if (j < ((long) i)) {
                StringBuilder sb = new StringBuilder("content-length promised ");
                sb.append(i);
                sb.append(" bytes, but received ");
                sb.append(j);
                throw new ProtocolException(sb.toString());
            }
        }
    }

    public long contentLength() throws IOException {
        return this.content.A00;
    }

    @Override // X.AbstractC07640v7
    public void write(AnonymousClass0B3 r9, long j) throws IOException {
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

    public void writeToSocket(AbstractC07640v7 r8) throws IOException {
        AnonymousClass0B3 r2 = new AnonymousClass0B3();
        AnonymousClass0B3 r1 = this.content;
        r1.A0H(r2, 0, r1.A00);
        r8.write(r2, r2.A00);
    }

    @Override // X.AbstractC07640v7
    public C07620v5 timeout() {
        return C07620v5.NONE;
    }

    public RetryableSink() {
        this(-1);
    }

    public RetryableSink(int i) {
        this.content = new AnonymousClass0B3();
        this.limit = i;
    }
}
