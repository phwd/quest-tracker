package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: u40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5023u40 extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11384a = true;
    public final /* synthetic */ C5533x40 b;

    public C5023u40(C5533x40 x40) {
        this.b = x40;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        View o;
        XK0 L;
        int i;
        if (this.f11384a && (o = this.b.o(motionEvent)) != null && (L = this.b.r.L(o)) != null) {
            C5533x40 x40 = this.b;
            if (((x40.m.e(x40.r, L) & 16711680) != 0) && motionEvent.getPointerId(0) == (i = this.b.l)) {
                int findPointerIndex = motionEvent.findPointerIndex(i);
                float x = motionEvent.getX(findPointerIndex);
                float y = motionEvent.getY(findPointerIndex);
                C5533x40 x402 = this.b;
                x402.d = x;
                x402.e = y;
                x402.i = 0.0f;
                x402.h = 0.0f;
                if (x402.m.i()) {
                    this.b.t(L, 2);
                }
            }
        }
    }
}
