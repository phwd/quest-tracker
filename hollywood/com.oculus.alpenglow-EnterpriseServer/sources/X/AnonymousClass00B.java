package X;

import android.content.Context;

/* renamed from: X.00B  reason: invalid class name */
public final class AnonymousClass00B extends AbstractC006205y implements AnonymousClass0Lf, AnonymousClass0R6 {
    public final C03060bR A00;
    public final AnonymousClass0Lh A01;

    @Override // X.AbstractC03040bP
    public final Object A2P() {
        return this.A00.A01();
    }

    @Override // X.AbstractC03040bP
    public final void A2U(Object obj) {
        C03060bR.A00((AnonymousClass0RA) obj);
    }

    @Override // X.AnonymousClass0Lf
    public final Context A3m() {
        return this.A00.A00;
    }

    public AnonymousClass00B(AnonymousClass0Lh r1, C03060bR r2) {
        super(r1);
        this.A01 = r1;
        this.A00 = r2;
    }
}
