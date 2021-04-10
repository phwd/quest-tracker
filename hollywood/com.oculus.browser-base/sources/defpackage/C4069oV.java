package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: oV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4069oV extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a  reason: collision with root package name */
    public float f10553a;
    public float b;
    public final /* synthetic */ C4411qV c;

    public C4069oV(C4411qV qVVar) {
        this.c = qVVar;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.c.m = motionEvent.getButtonState();
        C4411qV qVVar = this.c;
        boolean z = false;
        qVVar.k = false;
        qVVar.l = false;
        if (qVVar.j) {
            AbstractC4581rV rVVar = qVVar.g;
            float x = motionEvent.getX() * this.c.f9077a;
            float y = motionEvent.getY() * this.c.f9077a;
            if (motionEvent.getToolType(0) == 3) {
                z = true;
            }
            rVVar.n(x, y, z, this.c.m);
        }
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        C4411qV qVVar = this.c;
        if (!qVVar.j) {
            return true;
        }
        AbstractC4581rV rVVar = qVVar.g;
        float x = motionEvent.getX() * this.c.f9077a;
        float y = motionEvent.getY();
        float f3 = this.c.f9077a;
        rVVar.b(x, y * f3, f * f3, f2 * f3);
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        C4411qV.d(this.c, motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        C4411qV qVVar = this.c;
        if (!qVVar.l) {
            qVVar.l = true;
            float sqrt = (float) Math.sqrt((double) ((f2 * f2) + (f * f)));
            if (sqrt > 0.0f) {
                float max = Math.max(0.0f, sqrt - ((float) this.c.i)) / sqrt;
                float f3 = 1.0f - max;
                this.f10553a = (f * f3) + motionEvent.getX();
                this.b = (f3 * f2) + motionEvent.getY();
                f *= max;
                f2 *= max;
            }
        }
        if (this.c.j) {
            float x = motionEvent2.getX() - this.f10553a;
            float y = motionEvent2.getY() - this.b;
            AbstractC4581rV rVVar = this.c.g;
            float x2 = this.c.f9077a * motionEvent2.getX();
            float y2 = motionEvent2.getY();
            float f4 = this.c.f9077a;
            rVVar.r(x2, y2 * f4, (-f) * f4, (-f2) * f4, x * f4, y * f4);
        }
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        C4411qV qVVar = this.c;
        if (qVVar.j && !qVVar.k) {
            AbstractC4581rV rVVar = qVVar.g;
            float x = motionEvent.getX() * this.c.f9077a;
            float y = motionEvent.getY() * this.c.f9077a;
            boolean z = false;
            if (motionEvent.getToolType(0) == 3) {
                z = true;
            }
            rVVar.o(x, y, z, this.c.m);
        }
        return true;
    }
}
