package X;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: X.1tG  reason: invalid class name */
public class AnonymousClass1tG implements PopupWindow.OnDismissListener {
    public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener A00;
    public final /* synthetic */ C11701sg A01;

    public AnonymousClass1tG(C11701sg r1, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
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
