package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;

/* renamed from: X.0MW  reason: invalid class name */
public final class AnonymousClass0MW implements AbstractC07640v7 {
    public boolean A00;
    public final AnonymousClass0Ls A01;
    public final /* synthetic */ AnonymousClass0MU A02;

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (!this.A00) {
            this.A00 = true;
            AnonymousClass0MU r2 = this.A02;
            r2.A01.AAI("0\r\n\r\n");
            AnonymousClass0MU.A00(this.A01);
            r2.A00 = 3;
        }
    }

    @Override // X.AbstractC07640v7, java.io.Flushable
    public final synchronized void flush() throws IOException {
        if (!this.A00) {
            this.A02.A01.flush();
        }
    }

    public AnonymousClass0MW(AnonymousClass0MU r3) {
        this.A02 = r3;
        this.A01 = new AnonymousClass0Ls(r3.A01.timeout());
    }

    @Override // X.AbstractC07640v7
    public final void write(AnonymousClass0B3 r4, long j) throws IOException {
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            AnonymousClass0Lx r1 = this.A02.A01;
            r1.AAE(j);
            r1.AAI(HttpRequestMultipart.LINE_FEED);
            r1.write(r4, j);
            r1.AAI(HttpRequestMultipart.LINE_FEED);
        }
    }

    @Override // X.AbstractC07640v7
    public final C07620v5 timeout() {
        return this.A01;
    }
}
