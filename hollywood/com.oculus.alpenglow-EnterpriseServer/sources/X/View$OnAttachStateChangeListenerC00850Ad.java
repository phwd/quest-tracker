package X;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;

/* renamed from: X.0Ad  reason: invalid class name and case insensitive filesystem */
public final class View$OnAttachStateChangeListenerC00850Ad implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    public ViewTreeObserver A00;
    public final View A01;
    public final Runnable A02;

    @NonNull
    public static void A00(@NonNull View view, @NonNull Runnable runnable) {
        if (view != null) {
            View$OnAttachStateChangeListenerC00850Ad r1 = new View$OnAttachStateChangeListenerC00850Ad(view, runnable);
            view.getViewTreeObserver().addOnPreDrawListener(r1);
            view.addOnAttachStateChangeListener(r1);
            return;
        }
        throw new NullPointerException("view == null");
    }

    public final boolean onPreDraw() {
        ViewTreeObserver viewTreeObserver;
        if (this.A00.isAlive()) {
            viewTreeObserver = this.A00;
        } else {
            viewTreeObserver = this.A01.getViewTreeObserver();
        }
        viewTreeObserver.removeOnPreDrawListener(this);
        this.A01.removeOnAttachStateChangeListener(this);
        this.A02.run();
        return true;
    }

    public final void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver;
        if (this.A00.isAlive()) {
            viewTreeObserver = this.A00;
        } else {
            viewTreeObserver = this.A01.getViewTreeObserver();
        }
        viewTreeObserver.removeOnPreDrawListener(this);
        this.A01.removeOnAttachStateChangeListener(this);
    }

    public View$OnAttachStateChangeListenerC00850Ad(View view, Runnable runnable) {
        this.A01 = view;
        this.A00 = view.getViewTreeObserver();
        this.A02 = runnable;
    }

    public final void onViewAttachedToWindow(View view) {
        this.A00 = view.getViewTreeObserver();
    }
}
