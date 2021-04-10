package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: Km  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnAttachStateChangeListenerC0643Km implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View$OnKeyListenerC0886Om F;

    public View$OnAttachStateChangeListenerC0643Km(View$OnKeyListenerC0886Om om) {
        this.F = om;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.F.d0;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.F.d0 = view.getViewTreeObserver();
            }
            View$OnKeyListenerC0886Om om = this.F;
            om.d0.removeGlobalOnLayoutListener(om.O);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
