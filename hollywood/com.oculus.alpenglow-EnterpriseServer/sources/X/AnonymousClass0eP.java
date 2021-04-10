package X;

import android.view.View;

/* renamed from: X.0eP  reason: invalid class name */
public class AnonymousClass0eP implements AnonymousClass0B1 {
    public int A00;
    public boolean A01 = false;
    public final /* synthetic */ AbstractC001203i A02;

    @Override // X.AnonymousClass0B1
    public final void A5p(View view) {
        this.A01 = true;
    }

    public AnonymousClass0eP(AbstractC001203i r2) {
        this.A02 = r2;
    }

    @Override // X.AnonymousClass0B1
    public final void A5q(View view) {
        if (!this.A01) {
            AbstractC001203i r1 = this.A02;
            r1.A02 = null;
            AnonymousClass0eP.super.setVisibility(this.A00);
        }
    }

    @Override // X.AnonymousClass0B1
    public final void A5r(View view) {
        AnonymousClass0eP.super.setVisibility(0);
        this.A01 = false;
    }
}
