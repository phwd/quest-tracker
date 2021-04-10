package X;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: X.0N2  reason: invalid class name */
public class AnonymousClass0N2 extends C03750dB {
    public final /* synthetic */ C04490ez A00;

    public AnonymousClass0N2(C04490ez r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5q(View view) {
        LayoutInflater$Factory2C04430et r2 = this.A00.A01;
        r2.A0K.setVisibility(8);
        PopupWindow popupWindow = r2.A0C;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (r2.A0K.getParent() instanceof View) {
            ((View) r2.A0K.getParent()).requestApplyInsets();
        }
        r2.A0K.removeAllViews();
        r2.A0M.A04(null);
        r2.A0M = null;
        r2.A0A.requestApplyInsets();
    }
}
