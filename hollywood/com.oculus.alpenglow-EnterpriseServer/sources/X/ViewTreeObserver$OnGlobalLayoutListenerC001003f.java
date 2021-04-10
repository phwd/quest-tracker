package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.03f  reason: invalid class name and case insensitive filesystem */
public class ViewTreeObserver$OnGlobalLayoutListenerC001003f implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ AnonymousClass0Mv A00;

    public ViewTreeObserver$OnGlobalLayoutListenerC001003f(AnonymousClass0Mv r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        AnonymousClass0Mv r2 = this.A00;
        if (r2.A5a()) {
            C01840Md r1 = r2.A0G;
            if (!r1.A0E) {
                View view = r2.A03;
                if (view == null || !view.isShown()) {
                    r2.dismiss();
                } else {
                    r1.A8P();
                }
            }
        }
    }
}
