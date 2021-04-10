package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.1tH  reason: invalid class name */
public class AnonymousClass1tH implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View$OnKeyListenerC11671sd A00;

    public final void onViewAttachedToWindow(View view) {
    }

    public AnonymousClass1tH(View$OnKeyListenerC11671sd r1) {
        this.A00 = r1;
    }

    public final void onViewDetachedFromWindow(View view) {
        View$OnKeyListenerC11671sd r2 = this.A00;
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
