package X;

import java.io.IOException;

/* renamed from: X.0XW  reason: invalid class name */
public class AnonymousClass0XW<T> extends AnonymousClass0Bd<T> {
    public AnonymousClass0Bd<T> A00;

    @Override // X.AnonymousClass0Bd
    public final T A02(AnonymousClass0Fo r2) throws IOException {
        AnonymousClass0Bd<T> r0 = this.A00;
        if (r0 != null) {
            return r0.A02(r2);
        }
        throw new IllegalStateException();
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, T t) throws IOException {
        AnonymousClass0Bd<T> r0 = this.A00;
        if (r0 != null) {
            r0.A03(r2, t);
            return;
        }
        throw new IllegalStateException();
    }
}
