package X;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1F9  reason: invalid class name */
public class AnonymousClass1F9 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ForwardingListener$TriggerLongPress";
    public final /* synthetic */ AnonymousClass1F8 A00;

    public AnonymousClass1F9(AnonymousClass1F8 r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1F8 r3 = this.A00;
        AnonymousClass1F8.A00(r3);
        View view = r3.A07;
        if (view.isEnabled() && !view.isLongClickable() && r3.A02()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            r3.A03 = true;
        }
    }
}
