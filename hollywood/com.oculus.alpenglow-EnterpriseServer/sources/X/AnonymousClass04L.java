package X;

import android.view.ViewTreeObserver;

/* renamed from: X.04L  reason: invalid class name */
public class AnonymousClass04L implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ C04110e4 A00;

    public AnonymousClass04L(C04110e4 r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        C04110e4 r3 = this.A00;
        if (!r3.A01.A5a()) {
            r3.A01.A8Q(r3.getTextDirection(), r3.getTextAlignment());
        }
        ViewTreeObserver viewTreeObserver = r3.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }
}
