package X;

import android.view.View;

/* renamed from: X.1ry  reason: invalid class name and case insensitive filesystem */
public class C11431ry implements AbstractC003107k {
    public int A00;
    public boolean A01 = false;
    public final /* synthetic */ AnonymousClass1rP A02;

    @Override // X.AbstractC003107k
    public final void A6h(View view) {
        this.A01 = true;
    }

    public C11431ry(AnonymousClass1rP r2) {
        this.A02 = r2;
    }

    @Override // X.AbstractC003107k
    public final void A6i(View view) {
        if (!this.A01) {
            AnonymousClass1rP r1 = this.A02;
            r1.A02 = null;
            C11431ry.super.setVisibility(this.A00);
        }
    }

    @Override // X.AbstractC003107k
    public final void A6k(View view) {
        C11431ry.super.setVisibility(0);
        this.A01 = false;
    }
}
