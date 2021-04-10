package X;

import com.facebook.acra.util.HttpRequestMultipart;

/* renamed from: X.ta  reason: case insensitive filesystem */
public final class C1134ta implements AbstractC0608cr {
    public boolean A00;
    public final t3 A01;
    public final /* synthetic */ tY A02;

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.A00) {
            this.A00 = true;
            tY tYVar = this.A02;
            tYVar.A01.A5z("0\r\n\r\n");
            t3 t3Var = this.A01;
            C0610ct ctVar = t3Var.A00;
            t3Var.A00 = C0610ct.A03;
            ctVar.A01();
            ctVar.A02();
            tYVar.A00 = 3;
        }
    }

    @Override // X.AbstractC0608cr, java.io.Flushable
    public final synchronized void flush() {
        if (!this.A00) {
            this.A02.A01.flush();
        }
    }

    public C1134ta(tY tYVar) {
        this.A02 = tYVar;
        this.A01 = new t3(tYVar.A01.A5G());
    }

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r4, long j) {
        if (this.A00) {
            throw new IllegalStateException("closed");
        } else if (j != 0) {
            t6 t6Var = this.A02.A01;
            t6Var.A5p(j);
            t6Var.A5z(HttpRequestMultipart.LINE_FEED);
            t6Var.A5k(r4, j);
            t6Var.A5z(HttpRequestMultipart.LINE_FEED);
        }
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A01;
    }
}
