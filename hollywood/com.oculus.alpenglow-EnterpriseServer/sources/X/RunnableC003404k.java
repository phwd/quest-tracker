package X;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: X.04k  reason: invalid class name and case insensitive filesystem */
public class RunnableC003404k implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ForwardingListener$TriggerLongPress";
    public final /* synthetic */ AbstractView$OnAttachStateChangeListenerC003504l A00;

    public RunnableC003404k(AbstractView$OnAttachStateChangeListenerC003504l r1) {
        this.A00 = r1;
    }

    public final void run() {
        AbstractView$OnAttachStateChangeListenerC003504l r3 = this.A00;
        AbstractView$OnAttachStateChangeListenerC003504l.A00(r3);
        View view = r3.A07;
        if (view.isEnabled() && !view.isLongClickable() && r3.A02()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            r3.A03 = true;
        }
    }
}
