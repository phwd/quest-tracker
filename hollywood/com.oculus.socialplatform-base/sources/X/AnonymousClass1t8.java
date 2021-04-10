package X;

import android.view.ViewTreeObserver;

/* renamed from: X.1t8  reason: invalid class name */
public class AnonymousClass1t8 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ C11701sg A00;

    public AnonymousClass1t8(C11701sg r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        C11701sg r2 = this.A00;
        AnonymousClass1sR r1 = r2.A04;
        if (!r1.isAttachedToWindow() || !r1.getGlobalVisibleRect(r2.A03)) {
            r2.dismiss();
            return;
        }
        r2.A01();
        r2.AAO();
    }
}
