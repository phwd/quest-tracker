package X;

import android.view.View;

/* renamed from: X.1ub  reason: invalid class name */
public class AnonymousClass1ub implements Runnable {
    public static final String __redex_internal_original_name = "androidx.databinding.ViewDataBinding$7";
    public final /* synthetic */ AnonymousClass1uW A00;

    public final void run() {
        AnonymousClass1uW r2;
        synchronized (this) {
            r2 = this.A00;
            r2.mPendingRebind = false;
        }
        AnonymousClass1uW.processReferenceQueue();
        if (!r2.mRoot.isAttachedToWindow()) {
            View view = r2.mRoot;
            View.OnAttachStateChangeListener onAttachStateChangeListener = AnonymousClass1uW.ROOT_REATTACHED_LISTENER;
            view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
            r2.mRoot.addOnAttachStateChangeListener(onAttachStateChangeListener);
            return;
        }
        r2.executePendingBindings();
    }

    public AnonymousClass1ub(AnonymousClass1uW r1) {
        this.A00 = r1;
    }
}
