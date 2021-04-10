package X;

import android.view.View;

/* renamed from: X.0N3  reason: invalid class name */
public class AnonymousClass0N3 extends C03750dB {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public AnonymousClass0N3(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5q(View view) {
        LayoutInflater$Factory2C04430et r2 = this.A00;
        r2.A0K.setAlpha(1.0f);
        r2.A0M.A04(null);
        r2.A0M = null;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5r(View view) {
        LayoutInflater$Factory2C04430et r2 = this.A00;
        r2.A0K.setVisibility(0);
        r2.A0K.sendAccessibilityEvent(32);
        if (r2.A0K.getParent() instanceof View) {
            ((View) r2.A0K.getParent()).requestApplyInsets();
        }
    }
}
