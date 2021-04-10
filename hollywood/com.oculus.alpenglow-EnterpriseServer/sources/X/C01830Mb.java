package X;

import android.view.View;

/* renamed from: X.0Mb  reason: invalid class name and case insensitive filesystem */
public class C01830Mb extends C03750dB {
    public boolean A00 = false;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C04030dq A02;

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5p(View view) {
        this.A00 = true;
    }

    public C01830Mb(C04030dq r2, int i) {
        this.A02 = r2;
        this.A01 = i;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5q(View view) {
        if (!this.A00) {
            this.A02.A08.setVisibility(this.A01);
        }
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5r(View view) {
        this.A02.A08.setVisibility(0);
    }
}
