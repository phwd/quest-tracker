package X;

import java.io.IOException;

/* renamed from: X.0BB  reason: invalid class name */
public class AnonymousClass0BB extends AnonymousClass0Lt {
    public final /* synthetic */ AnonymousClass0MT A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0BB(AnonymousClass0MT r1, AbstractC07630v6 r2) {
        super(r2);
        this.A00 = r1;
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0Lt
    public final void close() throws IOException {
        AnonymousClass0MT r2 = this.A00;
        r2.A01.A05(false, r2);
        super.close();
    }
}
