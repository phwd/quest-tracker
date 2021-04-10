package defpackage;

import android.content.Context;
import android.view.MotionEvent;

/* renamed from: VL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VL {

    /* renamed from: a  reason: collision with root package name */
    public final float f9077a;
    public boolean b;
    public float c;
    public float d;

    public VL(Context context, boolean z) {
        this.f9077a = 1.0f / context.getResources().getDisplayMetrics().density;
        this.b = z;
    }

    public final boolean a(MotionEvent motionEvent, boolean z) {
        MotionEvent motionEvent2;
        if (!this.b || (this.c == 0.0f && this.d == 0.0f)) {
            motionEvent2 = motionEvent;
        } else {
            motionEvent2 = MotionEvent.obtain(motionEvent);
            motionEvent2.offsetLocation(this.c, this.d);
        }
        boolean b2 = b(motionEvent2, z);
        if (motionEvent2 != motionEvent) {
            motionEvent2.recycle();
        }
        return b2;
    }

    public abstract boolean b(MotionEvent motionEvent, boolean z);

    public abstract boolean c(MotionEvent motionEvent);
}
