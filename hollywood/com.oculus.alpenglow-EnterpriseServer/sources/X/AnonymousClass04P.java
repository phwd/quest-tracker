package X;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: X.04P  reason: invalid class name */
public class AnonymousClass04P implements PopupWindow.OnDismissListener {
    public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener A00;
    public final /* synthetic */ AnonymousClass0Ml A01;

    public AnonymousClass04P(AnonymousClass0Ml r1, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.A01 = r1;
        this.A00 = onGlobalLayoutListener;
    }

    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.A01.A04.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.A00);
        }
    }
}
