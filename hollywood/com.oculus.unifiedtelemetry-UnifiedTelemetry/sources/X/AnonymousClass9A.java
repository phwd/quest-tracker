package X;

import java.io.IOException;

/* renamed from: X.9A  reason: invalid class name */
public class AnonymousClass9A extends K5 {
    public final /* synthetic */ C0126Kw A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass9A(C0126Kw kw, AbstractC0312cb cbVar) {
        super(cbVar);
        this.A00 = kw;
    }

    @Override // java.io.Closeable, X.K5, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
        C0126Kw kw = this.A00;
        kw.A01.A05(false, kw);
        super.close();
    }
}
