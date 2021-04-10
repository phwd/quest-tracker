package defpackage;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: n40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3829n40 implements LK0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5533x40 f10471a;

    public C3829n40(C5533x40 x40) {
        this.f10471a = x40;
    }

    @Override // defpackage.LK0
    public boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        int findPointerIndex;
        ((C3727mV) this.f10471a.y.f10494a).f10425a.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        C4000o40 o40 = null;
        if (actionMasked == 0) {
            this.f10471a.l = motionEvent.getPointerId(0);
            this.f10471a.d = motionEvent.getX();
            this.f10471a.e = motionEvent.getY();
            C5533x40 x40 = this.f10471a;
            VelocityTracker velocityTracker = x40.t;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            x40.t = VelocityTracker.obtain();
            C5533x40 x402 = this.f10471a;
            if (x402.c == null) {
                if (!x402.p.isEmpty()) {
                    View o = x402.o(motionEvent);
                    int size = x402.p.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        C4000o40 o402 = (C4000o40) x402.p.get(size);
                        if (o402.f10530J.G == o) {
                            o40 = o402;
                            break;
                        }
                        size--;
                    }
                }
                if (o40 != null) {
                    C5533x40 x403 = this.f10471a;
                    x403.d -= o40.N;
                    x403.e -= o40.O;
                    x403.n(o40.f10530J, true);
                    if (this.f10471a.f11584a.remove(o40.f10530J.G)) {
                        C5533x40 x404 = this.f10471a;
                        x404.m.b(x404.r, o40.f10530J);
                    }
                    this.f10471a.t(o40.f10530J, o40.K);
                    C5533x40 x405 = this.f10471a;
                    x405.u(motionEvent, x405.o, 0);
                }
            }
        } else if (actionMasked == 3 || actionMasked == 1) {
            C5533x40 x406 = this.f10471a;
            x406.l = -1;
            x406.t(null, 0);
        } else {
            int i = this.f10471a.l;
            if (i != -1 && (findPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                this.f10471a.l(actionMasked, motionEvent, findPointerIndex);
            }
        }
        VelocityTracker velocityTracker2 = this.f10471a.t;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
        }
        return this.f10471a.c != null;
    }

    @Override // defpackage.LK0
    public void c(RecyclerView recyclerView, MotionEvent motionEvent) {
        ((C3727mV) this.f10471a.y.f10494a).f10425a.onTouchEvent(motionEvent);
        VelocityTracker velocityTracker = this.f10471a.t;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        if (this.f10471a.l != -1) {
            int actionMasked = motionEvent.getActionMasked();
            int findPointerIndex = motionEvent.findPointerIndex(this.f10471a.l);
            if (findPointerIndex >= 0) {
                this.f10471a.l(actionMasked, motionEvent, findPointerIndex);
            }
            C5533x40 x40 = this.f10471a;
            XK0 xk0 = x40.c;
            if (xk0 != null) {
                int i = 0;
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked == 3) {
                            VelocityTracker velocityTracker2 = x40.t;
                            if (velocityTracker2 != null) {
                                velocityTracker2.clear();
                            }
                        } else if (actionMasked == 6) {
                            int actionIndex = motionEvent.getActionIndex();
                            int pointerId = motionEvent.getPointerId(actionIndex);
                            C5533x40 x402 = this.f10471a;
                            if (pointerId == x402.l) {
                                if (actionIndex == 0) {
                                    i = 1;
                                }
                                x402.l = motionEvent.getPointerId(i);
                                C5533x40 x403 = this.f10471a;
                                x403.u(motionEvent, x403.o, actionIndex);
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    } else if (findPointerIndex >= 0) {
                        x40.u(motionEvent, x40.o, findPointerIndex);
                        this.f10471a.r(xk0);
                        C5533x40 x404 = this.f10471a;
                        x404.r.removeCallbacks(x404.s);
                        this.f10471a.s.run();
                        this.f10471a.r.invalidate();
                        return;
                    } else {
                        return;
                    }
                }
                this.f10471a.t(null, 0);
                this.f10471a.l = -1;
            }
        }
    }

    @Override // defpackage.LK0
    public void e(boolean z) {
        if (z) {
            this.f10471a.t(null, 0);
        }
    }
}
