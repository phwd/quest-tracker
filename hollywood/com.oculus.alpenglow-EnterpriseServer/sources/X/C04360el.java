package X;

import android.view.View;

/* renamed from: X.0el  reason: invalid class name and case insensitive filesystem */
public class C04360el implements AnonymousClass0B2 {
    public final /* synthetic */ C04340ej A00;

    public C04360el(C04340ej r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0B2
    public final void A5s(View view) {
        ((View) this.A00.A09.getParent()).invalidate();
    }
}
