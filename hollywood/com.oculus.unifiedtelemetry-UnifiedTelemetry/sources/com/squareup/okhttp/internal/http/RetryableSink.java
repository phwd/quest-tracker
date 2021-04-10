package com.squareup.okhttp.internal.http;

import X.AbstractC0313cc;
import X.AnonymousClass06;
import X.AnonymousClass98;
import X.ca;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;

public final class RetryableSink implements AbstractC0313cc {
    public boolean closed;
    public final AnonymousClass98 content;
    public final int limit;

    @Override // X.AbstractC0313cc, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
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

    @Override // X.AbstractC0313cc
    public void write(AnonymousClass98 r9, long j) throws IOException {
        if (!this.closed) {
            Util.checkOffsetAndCount(r9.A00, 0, j);
            int i = this.limit;
            if (i == -1 || this.content.A00 <= ((long) i) - j) {
                this.content.write(r9, j);
                return;
            }
            throw new ProtocolException(AnonymousClass06.A02("exceeded content-length limit of ", i, " bytes"));
        }
        throw new IllegalStateException("closed");
    }

    public void writeToSocket(AbstractC0313cc ccVar) throws IOException {
        AnonymousClass98 r2 = new AnonymousClass98();
        AnonymousClass98 r1 = this.content;
        r1.A0I(r2, 0, r1.A00);
        ccVar.write(r2, r2.A00);
    }

    @Override // X.AbstractC0313cc
    public ca timeout() {
        return ca.NONE;
    }

    public RetryableSink() {
        this(-1);
    }

    public RetryableSink(int i) {
        this.content = new AnonymousClass98();
        this.limit = i;
    }
}
