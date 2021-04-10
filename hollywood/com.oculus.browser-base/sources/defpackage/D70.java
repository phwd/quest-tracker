package defpackage;

import J.N;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorView;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: D70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class D70 extends K70 implements AbstractC2642g70, E70, AbstractC0063Ba1 {
    public final float F;
    public final AbstractC4692s70 G;
    public final C1078Rq0 H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f7867J;
    public D11 K;
    public final ViewGroup L;
    public AbstractC0124Ca1 M;
    public AbstractC0612Ka1 N;
    public AbstractC1099Sa1 O;
    public AbstractC5783ya1 P;
    public final C1322Vq0 Q = new C1322Vq0();
    public final C1322Vq0 R = new C1322Vq0();
    public AbstractC2300e70 S;
    public AbstractC2300e70 T;
    public VL U;
    public final SparseArray V = new SparseArray();
    public int W = -1;
    public int X = -1;
    public boolean Y;
    public final C1328Vt0 Z;
    public final Context a0;
    public boolean b0;
    public final RectF c0 = new RectF();
    public final RectF d0 = new RectF();
    public final RectF e0 = new RectF();
    public final PointF f0 = new PointF();
    public boolean g0;
    public final C4316pw h0;
    public final C1078Rq0 i0 = new C1078Rq0();
    public final AbstractC0956Pq0 j0;
    public final C1078Rq0 k0 = new C1078Rq0();
    public final C0236Dw l0;
    public final List m0 = new ArrayList();
    public Map n0 = new HashMap();
    public final C1570Zs0 o0;
    public final Q31 p0;
    public Q31 q0;

    public D70(AbstractC4692s70 s70, ViewGroup viewGroup, AbstractC0956Pq0 pq0, Q31 q31, C1570Zs0 zs0, Q31 q312) {
        this.G = s70;
        this.F = 1.0f / s70.getContext().getResources().getDisplayMetrics().density;
        C1078Rq0 rq0 = new C1078Rq0();
        this.H = rq0;
        rq0.m(Boolean.TRUE);
        this.j0 = pq0;
        this.o0 = zs0;
        this.q0 = q31;
        this.p0 = q312;
        this.a0 = s70.getContext();
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) s70;
        Class[] clsArr = {C1572Zt0.class, Xl1.class, VP0.class, C2631g31.class, C4678s21.class, C1796bA.class};
        for (int i = 0; i < 6; i++) {
            this.n0.put(clsArr[i], Integer.valueOf(i));
        }
        this.L = viewGroup;
        this.h0 = new C4316pw(new RunnableC4862t70(this));
        this.Z = new C1328Vt0();
        this.l0 = new C0236Dw(new RunnableC5032u70(this));
        C1570Zs0 zs02 = this.o0;
        Objects.requireNonNull(zs02.G);
        zs02.F.b(this);
    }

    public static void f(D70 d70, int i, boolean z, boolean z2) {
        AbstractC0124Ca1 ca1 = d70.M;
        Tab j = ca1 != null ? ((AbstractC0246Ea1) ca1).j() : null;
        d70.u(i, j != null ? j.getId() : -1, z, z2);
    }

    @Override // defpackage.K70
    public abstract void c(int i);

    @Override // defpackage.K70
    public void d(int i) {
        this.V.remove(i);
    }

    public void g(LO0 lo0) {
        if (this.m0.contains(lo0)) {
            throw new RuntimeException("Overlay already added!");
        } else if (this.n0.containsKey(lo0.getClass())) {
            int intValue = ((Integer) this.n0.get(lo0.getClass())).intValue();
            int i = 0;
            while (i < this.m0.size() && intValue >= ((Integer) this.n0.get(((LO0) this.m0.get(i)).getClass())).intValue()) {
                i++;
            }
            this.m0.add(i, lo0);
        } else {
            throw new RuntimeException("Please add overlay to order list in constructor.");
        }
    }

    public C70 h() {
        return new C70(this);
    }

    public abstract void i();

    public void j(int i) {
        J70 j70 = (J70) this.V.get(i);
        this.V.clear();
        if (j70 != null) {
            this.V.put(i, j70);
        }
    }

    public C1551Zj k() {
        AbstractC4692s70 s70 = this.G;
        if (s70 != null) {
            return ((CompositorViewHolder) s70).U;
        }
        return null;
    }

    public final PointF m(MotionEvent motionEvent) {
        int a2 = ZN0.a(motionEvent.getActionMasked());
        if (a2 == 0 || a2 == 9) {
            o(this.e0);
            PointF pointF = this.f0;
            RectF rectF = this.e0;
            pointF.set(-rectF.left, -rectF.top);
            return this.f0;
        } else if (a2 != 1 && a2 != 3 && a2 != 10) {
            return null;
        } else {
            this.f0.set(0.0f, 0.0f);
            return this.f0;
        }
    }

    public final int n() {
        return this.G.getWidth() > this.G.getHeight() ? 2 : 1;
    }

    public void o(RectF rectF) {
        AbstractC2300e70 e70 = this.S;
        if (e70 == null) {
            ((CompositorViewHolder) this.G).r(rectF);
            return;
        }
        int r = e70.r();
        if (r == 0) {
            ((CompositorViewHolder) this.G).r(rectF);
        } else if (r == 1) {
            ((CompositorViewHolder) this.G).n(rectF);
        } else if (r != 3) {
            ((CompositorViewHolder) this.G).p(rectF);
        } else if (this.b0) {
            ((CompositorViewHolder) this.G).n(rectF);
        } else {
            ((CompositorViewHolder) this.G).r(rectF);
        }
    }

    public abstract void p(AbstractC0124Ca1 ca1, AbstractC3226ja1 ja1, DA da, IJ ij);

    public final boolean q(MotionEvent motionEvent) {
        VL vl = this.U;
        if (vl.b) {
            motionEvent.offsetLocation(vl.c, vl.d);
        }
        boolean c = vl.c(motionEvent);
        PointF m = m(motionEvent);
        if (m != null) {
            VL vl2 = this.U;
            float f = m.x;
            float f2 = m.y;
            vl2.c = f;
            vl2.d = f2;
        }
        return c;
    }

    public void r() {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            float f = e70.F;
            float f2 = e70.G;
            RectF rectF = this.d0;
            float f3 = rectF.top;
            ((CompositorViewHolder) this.G).r(rectF);
            ((CompositorViewHolder) this.G).p(this.c0);
            AbstractC2300e70 e702 = this.S;
            RectF rectF2 = this.d0;
            int m = ((CompositorViewHolder) this.G).m();
            int c = ((CompositorViewHolder) this.G).c();
            int n = n();
            Objects.requireNonNull(e702);
            float width = rectF2.width() / e702.T;
            float height = rectF2.height();
            float f4 = e702.T;
            float f5 = height / f4;
            float f6 = ((float) m) / f4;
            float f7 = ((float) c) / f4;
            boolean z = (Float.compare(e702.F, width) == 0 && Float.compare(e702.G, f5) == 0 && Float.compare(e702.H, f6) == 0 && Float.compare(e702.I, f7) == 0 && e702.K == n) ? false : true;
            e702.F = width;
            e702.G = f5;
            e702.H = f6;
            e702.I = f7;
            e702.K = n;
            if (z) {
                e702.y(width, f5, n);
            }
            float width2 = this.d0.width() * this.F;
            float height2 = this.d0.height() * this.F;
            if (!(width2 == f && height2 == f2 && f3 == this.c0.top)) {
                for (int i = 0; i < this.m0.size(); i++) {
                    ((LO0) this.m0.get(i)).m(width2, height2, this.c0.top, n());
                }
            }
        }
        for (int i2 = 0; i2 < this.V.size(); i2++) {
            J70 j70 = (J70) this.V.valueAt(i2);
            int width3 = this.G.getWidth();
            int height3 = this.G.getHeight();
            j70.k(J70.y, ((float) width3) * J70.d);
            j70.k(J70.z, ((float) height3) * J70.d);
        }
    }

    public void s(Runnable runnable) {
        if (!this.Y || runnable != null) {
            CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.G;
            if (runnable != null) {
                compositorViewHolder.u0.add(runnable);
            }
            CompositorView compositorView = compositorViewHolder.M;
            long j = compositorView.K;
            if (j != 0) {
                N.M_Nkznfe(j, compositorView);
            }
            this.Y = true;
        }
    }

    public void t(AbstractC2300e70 e70) {
        if (e70 == null) {
            e70 = this.K;
        }
        this.T = e70;
    }

    public void u(int i, int i2, boolean z, boolean z2) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.B(SystemClock.uptimeMillis(), i, i2, z);
        }
    }

    public void v(int i, boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.D(SystemClock.uptimeMillis(), i, z);
        }
    }

    public abstract void w(int i, int i2, int i3, boolean z, boolean z2, float f, float f2);

    public void x(int i, String str, boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.F(i);
        }
    }

    public void y(boolean z) {
        AbstractC2300e70 e70 = this.S;
        if (e70 != null) {
            e70.G(z);
        }
    }
}
