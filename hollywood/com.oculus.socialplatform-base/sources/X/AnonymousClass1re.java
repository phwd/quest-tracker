package X;

import android.view.View;

/* renamed from: X.1re  reason: invalid class name */
public class AnonymousClass1re extends C05430vh {
    public final /* synthetic */ AnonymousClass1rJ A00;

    public AnonymousClass1re(AnonymousClass1rJ r1) {
        this.A00 = r1;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6i(View view) {
        AnonymousClass1rJ r2 = this.A00;
        r2.A0J.setAlpha(1.0f);
        r2.A0L.A04(null);
        r2.A0L = null;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6k(View view) {
        AnonymousClass1rJ r2 = this.A00;
        r2.A0J.setVisibility(0);
        r2.A0J.sendAccessibilityEvent(32);
        if (r2.A0J.getParent() instanceof View) {
            ((View) r2.A0J.getParent()).requestApplyInsets();
        }
    }
}
