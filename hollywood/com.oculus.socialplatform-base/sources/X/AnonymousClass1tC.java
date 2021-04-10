package X;

import android.view.ViewTreeObserver;

/* renamed from: X.1tC  reason: invalid class name */
public class AnonymousClass1tC implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ AnonymousClass1sR A00;

    public AnonymousClass1tC(AnonymousClass1sR r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        AnonymousClass1sR r3 = this.A00;
        if (!r3.A02.A6B()) {
            r3.A02.AAP(r3.getTextDirection(), r3.getTextAlignment());
        }
        ViewTreeObserver viewTreeObserver = r3.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }
}
