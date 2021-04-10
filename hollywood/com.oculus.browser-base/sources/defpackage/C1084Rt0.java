package defpackage;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;

/* renamed from: Rt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1084Rt0 extends C4411qV {
    public int A;
    public boolean B;
    public boolean C;
    public float D;
    public float E;
    public final ArrayList F = new ArrayList();
    public float G;
    public boolean H;
    public final AbstractC0292Et0 p;
    public final GestureDetector q;
    public final AbstractC5364w41 r;
    public final float s;
    public int t;
    public boolean u;
    public boolean v;
    public int w;
    public boolean x;
    public boolean y;
    public boolean z;

    public C1084Rt0(Context context, AbstractC0292Et0 et0) {
        super(context, et0, false, false);
        this.q = new GestureDetector(context, new C0962Pt0(this, null));
        this.p = et0;
        this.r = new C1023Qt0(this, context);
        float scaledTouchSlop = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.s = scaledTouchSlop * scaledTouchSlop;
        g();
    }

    @Override // defpackage.C4411qV, defpackage.VL
    public boolean b(MotionEvent motionEvent, boolean z2) {
        if (this.p.O() && (this.p.h0(motionEvent.getX() * this.f9077a, motionEvent.getY() * this.f9077a) || this.p.N())) {
            return true;
        }
        this.F.clear();
        g();
        return false;
    }

    @Override // defpackage.C4411qV, defpackage.VL
    public boolean c(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        AbstractC0292Et0 et0 = this.p;
        if (((C1796bA) et0).L == 2) {
            if (actionMasked == 0) {
                motionEvent.getX();
                C1796bA bAVar = (C1796bA) et0;
                if (bAVar.l0()) {
                    bAVar.Y().e(true);
                }
            }
            this.r.a(motionEvent);
            this.q.onTouchEvent(motionEvent);
            return true;
        }
        if (!this.u && actionMasked == 0) {
            this.G = motionEvent.getY();
            AbstractC0292Et0 et02 = this.p;
            float x2 = motionEvent.getX();
            float f = this.f9077a;
            if (et02.g0(x2 * f, this.G * f)) {
                this.u = true;
                this.y = true;
            } else {
                h(1);
                this.y = false;
            }
        }
        this.q.onTouchEvent(motionEvent);
        if (this.v) {
            if (this.B) {
                int size = this.F.size();
                for (int i = 0; i < size; i++) {
                    MotionEvent motionEvent2 = (MotionEvent) this.F.get(i);
                    f(motionEvent2, this.t);
                    motionEvent2.recycle();
                }
                this.F.clear();
                this.B = false;
            }
            if (this.x) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(3);
                f(obtain, this.w);
                obtain.recycle();
                MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                obtain2.setAction(0);
                this.C = true;
                this.D = obtain2.getX();
                this.E = obtain2.getY() - (this.p.X() / this.f9077a);
                f(obtain2, this.t);
                obtain2.recycle();
                this.x = false;
            }
            f(motionEvent, this.t);
        } else {
            this.F.add(MotionEvent.obtain(motionEvent));
            this.B = true;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            g();
        }
        return true;
    }

    public final void f(MotionEvent motionEvent, int i) {
        boolean z2;
        boolean z3 = false;
        if (i == 1) {
            if (motionEvent.getActionMasked() == 0) {
                this.H = true;
            }
            if (!this.H) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction(0);
                super.c(obtain);
                this.H = true;
            }
            super.c(motionEvent);
        } else if (i == 2) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.A != 1 || this.p.V) {
                z2 = false;
            } else if (actionMasked != 6 && actionMasked != 5) {
                motionEvent = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getActionMasked(), motionEvent.getX(), this.G, motionEvent.getMetaState());
                z2 = true;
            } else {
                return;
            }
            AbstractC0292Et0 et0 = this.p;
            motionEvent.offsetLocation(-(et0.S / this.f9077a), -(et0.X() / this.f9077a));
            this.p.Z();
            AbstractC0292Et0 et02 = this.p;
            OverlayPanelContent overlayPanelContent = et02.y0;
            ViewGroup viewGroup = overlayPanelContent != null ? overlayPanelContent.b : null;
            if (this.C && actionMasked == 1) {
                float x2 = motionEvent.getX() - this.D;
                float y2 = motionEvent.getY() - this.E;
                if (!((y2 * y2) + (x2 * x2) > this.s)) {
                    motionEvent.setAction(3);
                    if (viewGroup != null) {
                        viewGroup.dispatchTouchEvent(motionEvent);
                    }
                    z3 = true;
                }
            } else if (actionMasked == 0) {
                ((C1796bA) et02).J0 = true;
            }
            if (!z3 && viewGroup != null) {
                viewGroup.dispatchTouchEvent(motionEvent);
            }
            if (z2) {
                motionEvent.recycle();
            }
        }
    }

    public final void g() {
        this.t = 0;
        this.u = false;
        this.v = false;
        this.w = 0;
        this.x = false;
        this.y = false;
        this.C = false;
        this.A = 0;
        this.z = false;
    }

    public final void h(int i) {
        this.t = i;
        this.u = false;
        this.v = true;
    }
}
