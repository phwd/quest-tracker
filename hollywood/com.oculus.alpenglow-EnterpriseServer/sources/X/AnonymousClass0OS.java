package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0OS  reason: invalid class name */
public class AnonymousClass0OS implements AbstractC04550h0 {
    public final /* synthetic */ InputStream A00;
    public final /* synthetic */ C04540gz A01;

    public AnonymousClass0OS(C04540gz r1, InputStream inputStream) {
        this.A01 = r1;
        this.A00 = inputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.A01.throwIfReached();
                C04570h3 A07 = r6.A07(1);
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

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A01;
    }

    public final String toString() {
        return "source(" + this.A00 + ")";
    }
}
