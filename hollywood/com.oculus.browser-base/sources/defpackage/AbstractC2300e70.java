package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import android.view.ViewGroup;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: e70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2300e70 {
    public float F;
    public float G;
    public float H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public Context f9833J;
    public int K;
    public AbstractC0124Ca1 L;
    public TabContentManager M;
    public final K70 N;
    public final F70 O;
    public J70[] P;
    public boolean Q;
    public boolean R;
    public int S = -1;
    public final float T;
    public final float U;

    public AbstractC2300e70(Context context, K70 k70, F70 f70) {
        this.f9833J = context;
        this.N = k70;
        this.O = f70;
        this.F = -1.0f;
        this.G = -1.0f;
        this.H = -1.0f;
        this.I = -1.0f;
        this.K = 0;
        float f = context.getResources().getDisplayMetrics().density;
        this.T = f;
        this.U = 1.0f / f;
    }

    public void A() {
    }

    public void B(long j, int i, int i2, boolean z) {
    }

    public void C(long j, int i) {
    }

    public void D(long j, int i, boolean z) {
    }

    public void E(long j, int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
    }

    public void F(int i) {
    }

    public void G(boolean z) {
    }

    public void H(long j, int i) {
    }

    public void I(long j, int i, int i2, boolean z) {
    }

    public void J(long j, int i) {
        Q(i, true);
    }

    public void K(long j, boolean z) {
    }

    public boolean L(long j, boolean z) {
        return true;
    }

    public void M() {
        ((D70) this.N).s(null);
    }

    public void N(TabContentManager tabContentManager) {
        if (tabContentManager != null) {
            TabContentManager tabContentManager2 = this.M;
            if (tabContentManager2 != null) {
                tabContentManager2.h.remove(this);
            }
            this.M = tabContentManager;
            if (!tabContentManager.h.contains(this)) {
                tabContentManager.h.add(this);
            }
        }
    }

    public void O(AbstractC0124Ca1 ca1, TabContentManager tabContentManager) {
        this.L = ca1;
        N(tabContentManager);
    }

    public abstract void P(long j, boolean z);

    public void Q(int i, boolean z) {
        this.N.e(i, z);
        this.Q = true;
        this.S = i;
    }

    public void R() {
    }

    public void S(List list) {
        TabContentManager tabContentManager = this.M;
        if (tabContentManager != null) {
            tabContentManager.k(list, -1);
        }
    }

    public void T(long j, long j2) {
    }

    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
    }

    public final float V(float f, float f2, float f3) {
        if (Math.abs(f2 - f3) > this.U) {
            return f3;
        }
        float round = ((float) Math.round(this.T * f3)) * this.U;
        if (round < f3) {
            return Math.max(round, f2 - f);
        }
        return Math.min(round, f2 + f);
    }

    public boolean W(long j, UH0 uh0) {
        float f = (((float) j) * 1.0f) / 1000.0f;
        RH0 rh0 = J70.n;
        float e = uh0.e(rh0);
        RH0 rh02 = J70.o;
        float e2 = uh0.e(rh02);
        float V = V(f, e, uh0.e(J70.l));
        float V2 = V(f, e2, uh0.e(J70.m));
        boolean z = (V == e && V2 == e2) ? false : true;
        uh0.k(rh0, V);
        uh0.k(rh02, V2);
        return z;
    }

    public void a(ViewGroup viewGroup) {
    }

    public boolean b() {
        return true;
    }

    public void c(Context context) {
        this.f9833J = context;
        float f = context.getResources().getDisplayMetrics().density;
        J70.c = f;
        J70.d = 1.0f / f;
    }

    public J70 d(int i, boolean z, boolean z2, boolean z3) {
        return e(i, z, z2, z3, -1.0f, -1.0f);
    }

    public J70 e(int i, boolean z, boolean z2, boolean z3, float f, float f2) {
        D70 d70 = (D70) this.N;
        J70 j70 = (J70) d70.V.get(i);
        if (j70 == null) {
            j70 = new J70(i, z, d70.G.getWidth(), d70.G.getHeight(), z2, z3);
            d70.V.put(i, j70);
        } else {
            j70.D(d70.G.getWidth(), d70.G.getHeight(), z2, z3);
        }
        if (f > 0.0f) {
            j70.k(J70.A, f);
        }
        if (f2 > 0.0f) {
            j70.k(J70.B, f2);
        }
        v(j70);
        return j70;
    }

    public void f() {
    }

    public void g() {
    }

    public void h() {
        if (this.Q) {
            this.Q = false;
            int i = this.S;
            if (i != -1) {
                TabModel m = ((AbstractC0246Ea1) this.L).m(i);
                if (m != null) {
                    m.x(AbstractC1160Ta1.e(m, this.S), 3);
                }
                this.S = -1;
            }
            this.N.b();
            F70 f70 = this.O;
            if (!(f70 == null || ((CompositorViewHolder) f70).M.N == null)) {
                ResourceManager resourceManager = ((CompositorViewHolder) f70).M.N;
                long j = resourceManager.d;
                if (j != 0) {
                    N.MnAVuP1v(j, resourceManager);
                }
            }
            if (q() != null) {
                SceneLayer q = q();
                long j2 = q.F;
                if (j2 != 0) {
                    N.MJ5Z0mi3(j2, q);
                }
            }
        }
    }

    public void i() {
        if (this.R) {
            this.R = false;
            AbstractC3838n70 n70 = (AbstractC3838n70) this.N;
            int p = n70.S.p();
            C1570Zs0 zs0 = n70.o0;
            C5372w70 w70 = new C5372w70(n70, p);
            Objects.requireNonNull(zs0.G);
            zs0.F.g(w70);
            zs0.get();
            if (n70.B(n70.S)) {
                C1570Zs0 zs02 = n70.B0;
                C2984i70 i70 = new C2984i70(n70);
                Objects.requireNonNull(zs02.G);
                zs02.F.g(i70);
                zs02.get();
            }
        }
    }

    public void j() {
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public C4316pw m() {
        return ((D70) this.N).h0;
    }

    public abstract VL n();

    public J70 o(int i) {
        if (this.P == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            J70[] j70Arr = this.P;
            if (i2 >= j70Arr.length) {
                return null;
            }
            if (j70Arr[i2].q() == i) {
                return this.P[i2];
            }
            i2++;
        }
    }

    public abstract int p();

    public abstract SceneLayer q();

    public int r() {
        return 1;
    }

    public boolean s() {
        return this instanceof C2090cu0;
    }

    public boolean t() {
        return this instanceof C2090cu0;
    }

    public boolean u() {
        J70[] j70Arr = this.P;
        if (j70Arr == null || j70Arr.length != 1) {
        }
        return false;
    }

    public boolean v(J70 j70) {
        if (!(!j70.h(J70.S))) {
            return false;
        }
        this.N.c(j70.q());
        return true;
    }

    public boolean x() {
        return this == ((D70) this.N).S;
    }

    public void y(float f, float f2, int i) {
    }

    public boolean z() {
        return false;
    }
}
