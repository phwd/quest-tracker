package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: Nm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0826Nm0 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0887Om0 f8574a;

    public C0826Nm0(C0887Om0 om0, View$OnAttachStateChangeListenerC0765Mm0 mm0) {
        this.f8574a = om0;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.f8574a.i = 1;
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        C0887Om0 om0 = this.f8574a;
        if (om0.i == 0) {
            return true;
        }
        float x = motionEvent.getX();
        float x2 = motionEvent2.getX();
        float y = motionEvent2.getY();
        int i = om0.i;
        if (!(i == 0 || om0.d == null)) {
            if (i == 1) {
                if (Math.abs(f) > Math.abs(f2) * 1.73f && (x < om0.f8647a || ((float) om0.b.getWidth()) - om0.f8647a < x)) {
                    om0.d(f > 0.0f, x2, y);
                }
                int i2 = om0.i;
                if (!(i2 == 2 || i2 == 3)) {
                    om0.i = 0;
                }
            }
            om0.a(-f);
        }
        return true;
    }
}
