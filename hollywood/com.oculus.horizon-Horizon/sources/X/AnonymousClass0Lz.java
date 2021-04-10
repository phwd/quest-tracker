package X;

import java.io.IOException;

/* renamed from: X.0Lz  reason: invalid class name */
public class AnonymousClass0Lz implements AbstractC07630v6 {
    public final /* synthetic */ AnonymousClass0Ly A00;
    public final /* synthetic */ AbstractC07630v6 A01;

    public AnonymousClass0Lz(AnonymousClass0Ly r1, AbstractC07630v6 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            this.A01.close();
            this.A00.exit(true);
        } catch (IOException e) {
            throw this.A00.exit(e);
        } catch (Throwable th) {
            this.A00.exit(false);
            throw th;
        }
    }

    @Override // X.AbstractC07630v6
    public final long read(AnonymousClass0B3 r5, long j) throws IOException {
        AnonymousClass0Ly r3 = this.A00;
        r3.enter();
        try {
            long read = this.A01.read(r5, j);
            r3.exit(true);
            return read;
        } catch (IOException e) {
            throw r3.exit(e);
        } catch (Throwable th) {
            r3.exit(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.source(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A00;
    }
}
