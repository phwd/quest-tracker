package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* renamed from: qV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4411qV extends VL {
    public final int e;
    public final GestureDetector f;
    public final AbstractC4581rV g;
    public final boolean h;
    public final int i;
    public boolean j = true;
    public boolean k;
    public boolean l;
    public int m;
    public RunnableC4240pV n = new RunnableC4240pV(this, null);
    public Handler o = new Handler();

    public C4411qV(Context context, AbstractC4581rV rVVar, boolean z, boolean z2) {
        super(context, z);
        this.i = ViewConfiguration.get(context).getScaledTouchSlop();
        this.e = ViewConfiguration.getLongPressTimeout();
        this.h = z2;
        this.g = rVVar;
        context.getResources();
        GestureDetector gestureDetector = new GestureDetector(context, new C4069oV(this));
        this.f = gestureDetector;
        gestureDetector.setIsLongpressEnabled(z2);
    }

    public static void d(C4411qV qVVar, MotionEvent motionEvent) {
        if (qVVar.j) {
            qVVar.k = true;
            qVVar.g.p(motionEvent.getX() * qVVar.f9077a, motionEvent.getY() * qVVar.f9077a);
        }
    }

    @Override // defpackage.VL
    public boolean b(MotionEvent motionEvent, boolean z) {
        return true;
    }

    @Override // defpackage.VL
    public boolean c(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!this.h) {
            if (motionEvent.getPointerCount() > 1) {
                if (this.n.G) {
                    e();
                }
            } else if (actionMasked == 0) {
                if (this.n.G) {
                    e();
                }
                RunnableC4240pV pVVar = this.n;
                MotionEvent motionEvent2 = pVVar.F;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                pVVar.F = MotionEvent.obtain(motionEvent);
                pVVar.G = true;
                this.o.postDelayed(this.n, (long) this.e);
            } else if (actionMasked == 1 || actionMasked == 3) {
                e();
            } else {
                RunnableC4240pV pVVar2 = this.n;
                if (pVVar2.G) {
                    MotionEvent motionEvent3 = pVVar2.F;
                    float x = motionEvent3.getX() - motionEvent.getX();
                    float y = motionEvent3.getY() - motionEvent.getY();
                    float f2 = (y * y) + (x * x);
                    int i2 = this.i;
                    if (f2 > ((float) (i2 * i2))) {
                        e();
                    }
                }
            }
        }
        if (motionEvent.getPointerCount() > 1) {
            this.g.g(motionEvent.getX(0) * this.f9077a, motionEvent.getY(0) * this.f9077a, motionEvent.getX(1) * this.f9077a, motionEvent.getY(1) * this.f9077a, actionMasked == 5);
            this.f.setIsLongpressEnabled(false);
            this.j = false;
        } else {
            this.f.setIsLongpressEnabled(this.h);
            this.j = true;
        }
        this.f.onTouchEvent(motionEvent);
        if (actionMasked == 1 || actionMasked == 3) {
            this.g.l();
        }
        return true;
    }

    public final void e() {
        this.o.removeCallbacks(this.n);
        this.n.G = false;
    }
}
