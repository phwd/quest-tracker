package X;

import java.io.IOException;

/* renamed from: X.0eX  reason: invalid class name */
public class AnonymousClass0eX<T> extends AnonymousClass13Y<T> {
    public AnonymousClass13Y<T> A00;

    @Override // X.AnonymousClass13Y
    public final T A02(AnonymousClass14I r2) throws IOException {
        AnonymousClass13Y<T> r0 = this.A00;
        if (r0 != null) {
            return r0.A02(r2);
        }
        throw new IllegalStateException();
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, T t) throws IOException {
        AnonymousClass13Y<T> r0 = this.A00;
        if (r0 != null) {
            r0.A03(r2, t);
            return;
        }
        throw new IllegalStateException();
    }
}
