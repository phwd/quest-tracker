package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.1tE  reason: invalid class name and case insensitive filesystem */
public class ViewTreeObserver$OnGlobalLayoutListenerC11881tE implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ View$OnKeyListenerC11681se A00;

    public ViewTreeObserver$OnGlobalLayoutListenerC11881tE(View$OnKeyListenerC11681se r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        View$OnKeyListenerC11681se r2 = this.A00;
        if (r2.A6B()) {
            C11651sb r1 = r2.A0G;
            if (!r1.A0E) {
                View view = r2.A03;
                if (view == null || !view.isShown()) {
                    r2.dismiss();
                } else {
                    r1.AAO();
                }
            }
        }
    }
}
