package X;

import java.io.IOException;
import java.io.InputStream;

public class Jy implements AbstractC0312cb {
    public final /* synthetic */ InputStream A00;
    public final /* synthetic */ ca A01;

    public Jy(ca caVar, InputStream inputStream) {
        this.A01 = caVar;
        this.A00 = inputStream;
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A03("byteCount < 0: ", j));
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.A01.throwIfReached();
                C0315ce A07 = r6.A07(1);
                int i = A07.A00;
                int read = this.A00.read(A07.A06, i, (int) Math.min(j, (long) (8192 - i)));
                if (read == -1) {
                    return -1;
                }
                A07.A00 += read;
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

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A01;
    }
}
