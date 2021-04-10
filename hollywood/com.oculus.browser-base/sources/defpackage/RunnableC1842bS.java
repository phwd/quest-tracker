package defpackage;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: bS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1842bS implements Runnable {
    public final /* synthetic */ AbstractView$OnTouchListenerC2013cS F;

    public RunnableC1842bS(AbstractView$OnTouchListenerC2013cS cSVar) {
        this.F = cSVar;
    }

    public void run() {
        AbstractView$OnTouchListenerC2013cS cSVar = this.F;
        cSVar.a();
        View view = cSVar.I;
        if (view.isEnabled() && !view.isLongClickable() && cSVar.c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            cSVar.L = true;
        }
    }
}
