package X;

import java.io.IOException;

public class KL implements AbstractC0312cb {
    public final /* synthetic */ KK A00;
    public final /* synthetic */ AbstractC0312cb A01;

    public KL(KK kk, AbstractC0312cb cbVar) {
        this.A00 = kk;
        this.A01 = cbVar;
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
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

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r5, long j) throws IOException {
        KK kk = this.A00;
        kk.enter();
        try {
            long read = this.A01.read(r5, j);
            kk.exit(true);
            return read;
        } catch (IOException e) {
            throw kk.exit(e);
        } catch (Throwable th) {
            kk.exit(false);
            throw th;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AsyncTimeout.source(");
        sb.append(this.A01);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A00;
    }
}
