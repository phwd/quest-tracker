package X;

import java.io.IOException;

/* renamed from: X.0c2  reason: invalid class name */
public class AnonymousClass0c2 extends AnonymousClass0yl<T> {
    public final /* synthetic */ AnonymousClass0yl A00;

    public AnonymousClass0c2(AnonymousClass0yl r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, T t) throws IOException {
        if (t == null) {
            r2.A09();
        } else {
            this.A00.A03(r2, t);
        }
    }

    @Override // X.AnonymousClass0yl
    public final T A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return (T) this.A00.A02(r3);
        }
        r3.A0P();
        return null;
    }
}
