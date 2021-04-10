package X;

import android.view.View;

/* renamed from: X.1s8  reason: invalid class name and case insensitive filesystem */
public class C11531s8 extends C05430vh {
    public boolean A00 = false;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass1sI A02;

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6h(View view) {
        this.A00 = true;
    }

    public C11531s8(AnonymousClass1sI r2, int i) {
        this.A02 = r2;
        this.A01 = i;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6i(View view) {
        if (!this.A00) {
            this.A02.A08.setVisibility(this.A01);
        }
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6k(View view) {
        this.A02.A08.setVisibility(0);
    }
}
