package X;

import java.io.IOException;

/* renamed from: X.0Wg  reason: invalid class name */
public final class AnonymousClass0Wg<T> extends AnonymousClass0Bd<T> {
    public AnonymousClass0Bd<T> A00;
    public final AnonymousClass08D A01;
    public final AnonymousClass0C3 A02;
    public final AnonymousClass0Fe<T> A03;
    public final AbstractC008109t<T> A04;
    public final AnonymousClass0BN<T> A05;
    public final AnonymousClass0Wg<T>.GsonContextImpl A06 = new C02090Wi(this);

    @Override // X.AnonymousClass0Bd
    public final T A02(AnonymousClass0Fo r5) throws IOException {
        AbstractC008109t<T> r3 = this.A04;
        if (r3 == null) {
            AnonymousClass0Bd<T> r0 = this.A00;
            if (r0 == null) {
                r0 = this.A01.A06(this.A02, this.A03);
                this.A00 = r0;
            }
            return r0.A02(r5);
        }
        AnonymousClass0AU A002 = AnonymousClass0ED.A00(r5);
        if (A002 instanceof AnonymousClass0XT) {
            return null;
        }
        return r3.A27(A002, this.A03.A02, this.A06);
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, T t) throws IOException {
        AnonymousClass0Bd<T> r0;
        AnonymousClass0BN<T> r2 = this.A05;
        if (r2 == null) {
            r0 = this.A00;
            if (r0 == null) {
                r0 = this.A01.A06(this.A02, this.A03);
                this.A00 = r0;
            }
        } else if (t == null) {
            r4.A0A();
            return;
        } else {
            t = (T) r2.A7g(t, this.A03.A02, this.A06);
            r0 = (AnonymousClass0Bd<T>) C01220Fb.A0H;
        }
        r0.A03(r4, t);
    }

    public AnonymousClass0Wg(AnonymousClass0BN<T> r2, AbstractC008109t<T> r3, AnonymousClass08D r4, AnonymousClass0Fe<T> r5, AnonymousClass0C3 r6) {
        this.A05 = r2;
        this.A04 = r3;
        this.A01 = r4;
        this.A03 = r5;
        this.A02 = r6;
    }
}
