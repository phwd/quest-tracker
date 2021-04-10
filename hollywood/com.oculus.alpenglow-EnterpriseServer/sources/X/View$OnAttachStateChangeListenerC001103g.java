package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.03g  reason: invalid class name and case insensitive filesystem */
public class View$OnAttachStateChangeListenerC001103g implements View.OnAttachStateChangeListener {
    public final /* synthetic */ AnonymousClass0Mv A00;

    public final void onViewAttachedToWindow(View view) {
    }

    public View$OnAttachStateChangeListenerC001103g(AnonymousClass0Mv r1) {
        this.A00 = r1;
    }

    public final void onViewDetachedFromWindow(View view) {
        AnonymousClass0Mv r2 = this.A00;
        ViewTreeObserver viewTreeObserver = r2.A04;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                r2.A04 = view.getViewTreeObserver();
            }
            r2.A04.removeGlobalOnLayoutListener(r2.A0D);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
