package X;

import java.io.IOException;
import java.io.InputStream;

public class Dh implements WF {
    public final /* synthetic */ InputStream A00;
    public final /* synthetic */ WE A01;

    public Dh(WE we, InputStream inputStream) {
        this.A01 = we;
        this.A00 = inputStream;
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.WF
    public final long read(AnonymousClass8k r6, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A02("byteCount < 0: ", j));
        } else if (j == 0) {
            return 0;
        } else {
            try {
                this.A01.throwIfReached();
                WI A07 = r6.A07(1);
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

    @Override // X.WF
    public final WE timeout() {
        return this.A01;
    }
}
