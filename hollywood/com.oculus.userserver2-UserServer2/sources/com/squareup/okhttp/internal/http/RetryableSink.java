package com.squareup.okhttp.internal.http;

import X.AnonymousClass8k;
import X.WE;
import X.WG;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.ProtocolException;

public final class RetryableSink implements WG {
    public boolean closed;
    public final AnonymousClass8k content;
    public final int limit;

    @Override // X.WG, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
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

    @Override // X.WG
    public void write(AnonymousClass8k r9, long j) throws IOException {
        if (!this.closed) {
            Util.checkOffsetAndCount(r9.A00, 0, j);
            int i = this.limit;
            if (i == -1 || this.content.A00 <= ((long) i) - j) {
                this.content.write(r9, j);
                return;
            }
            StringBuilder sb = new StringBuilder("exceeded content-length limit of ");
            sb.append(i);
            sb.append(" bytes");
            throw new ProtocolException(sb.toString());
        }
        throw new IllegalStateException("closed");
    }

    public void writeToSocket(WG wg) throws IOException {
        AnonymousClass8k r2 = new AnonymousClass8k();
        AnonymousClass8k r1 = this.content;
        r1.A0H(r2, 0, r1.A00);
        wg.write(r2, r2.A00);
    }

    @Override // X.WG
    public WE timeout() {
        return WE.NONE;
    }

    public RetryableSink() {
        this(-1);
    }

    public RetryableSink(int i) {
        this.content = new AnonymousClass8k();
        this.limit = i;
    }
}
