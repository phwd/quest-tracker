package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* renamed from: Rw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC1089Rw implements View.OnLayoutChangeListener {
    public final /* synthetic */ C1150Sw F;

    public View$OnLayoutChangeListenerC1089Rw(C1150Sw sw) {
        this.F = sw;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        view.removeOnLayoutChangeListener(this);
        MotionEvent motionEvent = this.F.f8926a.x0;
        if (motionEvent != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(0);
            this.F.f8926a.dispatchTouchEvent(obtain);
            for (int i9 = 1; i9 < this.F.f8926a.x0.getPointerCount(); i9++) {
                MotionEvent obtain2 = MotionEvent.obtain(this.F.f8926a.x0);
                obtain2.setAction((i9 << 8) | 5);
                this.F.f8926a.dispatchTouchEvent(obtain2);
            }
        }
    }
}
