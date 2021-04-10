package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0Lo  reason: invalid class name */
public class AnonymousClass0Lo implements AbstractC07630v6 {
    public final /* synthetic */ InputStream A00;
    public final /* synthetic */ C07620v5 A01;

    public AnonymousClass0Lo(C07620v5 r1, InputStream inputStream) {
        this.A01 = r1;
        this.A00 = inputStream;
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AbstractC07630v6
    public final long read(AnonymousClass0B3 r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.A01.throwIfReached();
                C07660v9 A07 = r6.A07(1);
                int i = A07.A01;
                int read = this.A00.read(A07.A06, i, (int) Math.min(j, (long) (8192 - i)));
                if (read == -1) {
                    return -1;
                }
                A07.A01 += read;
                long j2 = (long) read;
                r6.A00 += j2;
                return j2;
            } catch (AssertionError e) {
                if (e.getCause() == null || e.getMessage() == null || !e.getMessage().contains("getsockname failed")) {
                    throw e;
                }
                throw new IOException(e);
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A01;
    }
}
