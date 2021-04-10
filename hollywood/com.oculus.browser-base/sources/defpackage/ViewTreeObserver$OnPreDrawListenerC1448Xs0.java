package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.Objects;

/* renamed from: Xs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ViewTreeObserver$OnPreDrawListenerC1448Xs0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View F;
    public ViewTreeObserver G;
    public final Runnable H;

    public ViewTreeObserver$OnPreDrawListenerC1448Xs0(View view, Runnable runnable) {
        this.F = view;
        this.G = view.getViewTreeObserver();
        this.H = runnable;
    }

    public static ViewTreeObserver$OnPreDrawListenerC1448Xs0 a(View view, Runnable runnable) {
        Objects.requireNonNull(view, "view == null");
        ViewTreeObserver$OnPreDrawListenerC1448Xs0 xs0 = new ViewTreeObserver$OnPreDrawListenerC1448Xs0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(xs0);
        view.addOnAttachStateChangeListener(xs0);
        return xs0;
    }

    public void b() {
        if (this.G.isAlive()) {
            this.G.removeOnPreDrawListener(this);
        } else {
            this.F.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.F.removeOnAttachStateChangeListener(this);
    }

    public boolean onPreDraw() {
        b();
        this.H.run();
        return true;
    }

    public void onViewAttachedToWindow(View view) {
        this.G = view.getViewTreeObserver();
    }

    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
