package X;

import android.view.ViewParent;

/* renamed from: X.04j  reason: invalid class name and case insensitive filesystem */
public class RunnableC003304j implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ForwardingListener$DisallowIntercept";
    public final /* synthetic */ AbstractView$OnAttachStateChangeListenerC003504l A00;

    public RunnableC003304j(AbstractView$OnAttachStateChangeListenerC003504l r1) {
        this.A00 = r1;
    }

    public final void run() {
        ViewParent parent = this.A00.A07.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }
}
