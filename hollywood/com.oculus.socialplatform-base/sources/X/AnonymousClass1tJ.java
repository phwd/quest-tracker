package X;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: X.1tJ  reason: invalid class name */
public class AnonymousClass1tJ implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View$OnKeyListenerC11681se A00;

    public final void onViewAttachedToWindow(View view) {
    }

    public AnonymousClass1tJ(View$OnKeyListenerC11681se r1) {
        this.A00 = r1;
    }

    public final void onViewDetachedFromWindow(View view) {
        View$OnKeyListenerC11681se r2 = this.A00;
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
