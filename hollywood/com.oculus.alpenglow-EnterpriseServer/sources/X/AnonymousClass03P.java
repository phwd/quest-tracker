package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.03P  reason: invalid class name */
public class AnonymousClass03P implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View$OnKeyListenerC01900Mx A00;

    public final void onViewAttachedToWindow(View view) {
    }

    public AnonymousClass03P(View$OnKeyListenerC01900Mx r1) {
        this.A00 = r1;
    }

    public final void onViewDetachedFromWindow(View view) {
        View$OnKeyListenerC01900Mx r2 = this.A00;
        ViewTreeObserver viewTreeObserver = r2.A00;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                r2.A00 = view.getViewTreeObserver();
            }
            r2.A00.removeGlobalOnLayoutListener(r2.A0G);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
