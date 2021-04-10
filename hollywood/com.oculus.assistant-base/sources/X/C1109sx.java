package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.sx  reason: case insensitive filesystem */
public final class C1109sx implements AbstractC0609cs {
    public final /* synthetic */ InputStream A00;
    public final /* synthetic */ C0610ct A01;

    public C1109sx(C0610ct ctVar, InputStream inputStream) {
        this.A01 = ctVar;
        this.A00 = inputStream;
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r6, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.A01.A05();
                C0606cp A06 = r6.A06(1);
                int i = A06.A00;
                int read = this.A00.read(A06.A06, i, (int) Math.min(j, (long) (8192 - i)));
                if (read == -1) {
                    return -1;
                }
                A06.A00 += read;
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

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        this.A00.close();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source(");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A01;
    }
}
