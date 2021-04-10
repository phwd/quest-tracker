package X;

import android.content.Context;

/* renamed from: X.00B  reason: invalid class name */
public final class AnonymousClass00B extends AbstractC000300x implements AnonymousClass0VD, AnonymousClass0RD {
    public final AnonymousClass0mK A00;
    public final AnonymousClass0VF A01;

    @Override // X.AbstractC03270lw
    public final Object A2s() {
        return this.A00.A01();
    }

    @Override // X.AbstractC03270lw
    public final void A2u(Object obj) {
        AnonymousClass0mK.A00((AnonymousClass0RH) obj);
    }

    @Override // X.AnonymousClass0VD
    public final Context A4B() {
        return this.A00.A00;
    }

    public AnonymousClass00B(AnonymousClass0VF r1, AnonymousClass0mK r2) {
        super(r1);
        this.A01 = r1;
        this.A00 = r2;
    }
}
