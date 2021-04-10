package X;

import java.io.IOException;

/* renamed from: X.0XN  reason: invalid class name */
public class AnonymousClass0XN extends AnonymousClass0Bd<T> {
    public final /* synthetic */ AnonymousClass0Bd A00;

    public AnonymousClass0XN(AnonymousClass0Bd r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, T t) throws IOException {
        if (t == null) {
            r2.A0A();
        } else {
            this.A00.A03(r2, t);
        }
    }

    @Override // X.AnonymousClass0Bd
    public final T A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return (T) this.A00.A02(r3);
        }
        r3.A0L();
        return null;
    }
}
