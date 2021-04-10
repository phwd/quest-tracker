package X;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: X.1rc  reason: invalid class name and case insensitive filesystem */
public class C11281rc extends C05430vh {
    public final /* synthetic */ AnonymousClass1rU A00;

    public C11281rc(AnonymousClass1rU r1) {
        this.A00 = r1;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6i(View view) {
        AnonymousClass1rJ r2 = this.A00.A01;
        r2.A0J.setVisibility(8);
        PopupWindow popupWindow = r2.A09;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (r2.A0J.getParent() instanceof View) {
            ((View) r2.A0J.getParent()).requestApplyInsets();
        }
        r2.A0J.removeAllViews();
        r2.A0L.A04(null);
        r2.A0L = null;
        r2.A07.requestApplyInsets();
    }
}
