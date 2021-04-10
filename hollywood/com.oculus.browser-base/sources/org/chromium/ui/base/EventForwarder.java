package org.chromium.ui.base;

import J.N;
import android.view.MotionEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EventForwarder {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11016a;
    public long b;
    public float c;
    public float d;
    public int e;

    public EventForwarder(long j, boolean z) {
        this.b = j;
        this.f11016a = z;
    }

    public static EventForwarder create(long j, boolean z) {
        return new EventForwarder(j, z);
    }

    public MotionEvent a(MotionEvent motionEvent) {
        if (!c()) {
            return motionEvent;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(this.c, this.d);
        return obtain;
    }

    public final float b() {
        return ((WindowAndroid) N.M4_mlka_(this.b, this)).I.a();
    }

    public final boolean c() {
        return (this.c == 0.0f && this.d == 0.0f) ? false : true;
    }

    public boolean d(MotionEvent motionEvent) {
        int actionMasked;
        if (this.b == 0) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            this.e = motionEvent.getButtonState();
        }
        return N.MvdB06Zi(this.b, this, motionEvent, motionEvent.getEventTime());
    }

    public final void destroy() {
        this.b = 0;
    }

    public boolean e(int i, long j, float f) {
        long j2 = this.b;
        if (j2 == 0) {
            return false;
        }
        return N.MtyC4Bd2(j2, this, i, j, f);
    }

    public void f(float f, float f2) {
        long j = this.b;
        if (j != 0) {
            N.M6lTZ5w8(j, this, f, f2);
        }
    }

    public final boolean g(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9 || actionMasked == 10) {
            return false;
        }
        if (actionMasked == 0 || actionMasked == 1) {
            return true;
        }
        float b2 = b();
        N.M$2oj6EQ(this.b, this, motionEvent.getEventTime(), actionMasked, motionEvent.getX() / b2, motionEvent.getY() / b2, motionEvent.getPointerId(0), motionEvent.getPressure(0), motionEvent.getOrientation(0), motionEvent.getAxisValue(25, 0), motionEvent.getActionButton(), motionEvent.getButtonState(), motionEvent.getMetaState(), motionEvent.getToolType(0));
        return true;
    }
}
