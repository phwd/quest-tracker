package X;

import java.io.IOException;

/* renamed from: X.0eO  reason: invalid class name */
public class AnonymousClass0eO extends AnonymousClass13Y<T> {
    public final /* synthetic */ AnonymousClass13Y A00;

    public AnonymousClass0eO(AnonymousClass13Y r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, T t) throws IOException {
        if (t == null) {
            r2.A09();
        } else {
            this.A00.A03(r2, t);
        }
    }

    @Override // X.AnonymousClass13Y
    public final T A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return (T) this.A00.A02(r3);
        }
        r3.A0P();
        return null;
    }
}
