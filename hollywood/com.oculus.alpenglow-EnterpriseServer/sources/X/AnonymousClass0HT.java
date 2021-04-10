package X;

import java.io.IOException;

/* renamed from: X.0HT  reason: invalid class name */
public class AnonymousClass0HT extends AnonymousClass0OY {
    public final /* synthetic */ AnonymousClass0PH A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0HT(AnonymousClass0PH r1, AbstractC04550h0 r2) {
        super(r2);
        this.A00 = r1;
    }

    @Override // java.io.Closeable, X.AnonymousClass0OY, java.lang.AutoCloseable, X.AbstractC04550h0
    public final void close() throws IOException {
        AnonymousClass0PH r2 = this.A00;
        r2.A01.A05(false, r2);
        super.close();
    }
}
