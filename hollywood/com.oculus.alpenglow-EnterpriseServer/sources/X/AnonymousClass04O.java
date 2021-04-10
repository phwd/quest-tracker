package X;

import android.view.ViewTreeObserver;

/* renamed from: X.04O  reason: invalid class name */
public class AnonymousClass04O implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ AnonymousClass0Ml A00;

    public AnonymousClass04O(AnonymousClass0Ml r1) {
        this.A00 = r1;
    }

    public final void onGlobalLayout() {
        AnonymousClass0Ml r2 = this.A00;
        C04110e4 r1 = r2.A04;
        if (!r1.isAttachedToWindow() || !r1.getGlobalVisibleRect(r2.A03)) {
            r2.dismiss();
            return;
        }
        r2.A02();
        r2.A8P();
    }
}
