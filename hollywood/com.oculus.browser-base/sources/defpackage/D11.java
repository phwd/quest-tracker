package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: D11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D11 extends AbstractC2300e70 {
    public boolean V;
    public final Context W;
    public final AbstractC4692s70 X;
    public final C0236Dw Y;
    public final UH0 Z;
    public C0297Ew a0;
    public J11 b0;
    public final C11 c0 = new C11(this, null);
    public final Handler d0 = new Handler();
    public boolean e0 = false;
    public AbstractC0124Ca1 f0;
    public AbstractC0855Oa1 g0;
    public AbstractC1099Sa1 h0;
    public AbstractC2400ek i0;
    public AbstractC2230dk j0;
    public TabContentManager k0;
    public final C4316pw l0;
    public final Q31 m0;
    public boolean n0;
    public boolean o0;
    public float p0;

    public D11(Context context, K70 k70, F70 f70, AbstractC4692s70 s70, C0236Dw dw, AbstractC0124Ca1 ca1, TabContentManager tabContentManager, AbstractC0956Pq0 pq0, Q31 q31) {
        super(context, k70, f70);
        this.W = context;
        this.X = s70;
        this.Y = dw;
        this.k0 = tabContentManager;
        this.f0 = ca1;
        this.g0 = new C5865z11(this, ca1);
        this.h0 = new A11(this, ca1);
        ((C1078Rq0) pq0).l(new C5695y11(this, pq0));
        Map c = UH0.c(J70.Y);
        SH0 sh0 = J70.e;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = -1;
        HashMap hashMap = (HashMap) c;
        hashMap.put(sh0, jh0);
        RH0 rh0 = J70.g;
        IH0 ih0 = new IH0(null);
        ih0.f8214a = 1.0f;
        hashMap.put(rh0, ih0);
        RH0 rh02 = J70.l;
        IH0 ih02 = new IH0(null);
        ih02.f8214a = 0.0f;
        hashMap.put(rh02, ih02);
        RH0 rh03 = J70.m;
        IH0 ih03 = new IH0(null);
        ih03.f8214a = 0.0f;
        hashMap.put(rh03, ih03);
        RH0 rh04 = J70.n;
        IH0 ih04 = new IH0(null);
        ih04.f8214a = 0.0f;
        hashMap.put(rh04, ih04);
        RH0 rh05 = J70.o;
        IH0 ih05 = new IH0(null);
        ih05.f8214a = 0.0f;
        hashMap.put(rh05, ih05);
        RH0 rh06 = J70.u;
        IH0 ih06 = new IH0(null);
        ih06.f8214a = 1.0f;
        hashMap.put(rh06, ih06);
        RH0 rh07 = J70.C;
        IH0 ih07 = new IH0(null);
        ih07.f8214a = 0.0f;
        hashMap.put(rh07, ih07);
        RH0 rh08 = J70.D;
        IH0 ih08 = new IH0(null);
        ih08.f8214a = 1.0f;
        hashMap.put(rh08, ih08);
        this.Z = new UH0(c, null);
        this.l0 = ((D70) k70).h0;
        this.m0 = q31;
        this.p0 = 1.0f / context.getResources().getDisplayMetrics().density;
    }

    @Override // defpackage.AbstractC2300e70
    public void A() {
        this.o0 = true;
        if (this.b0 == null) {
            J11 j11 = new J11();
            this.b0 = j11;
            N.M9WjiX4q(j11.G, j11, this.k0);
        }
        this.a0 = new C0297Ew(this.Z, this.b0, new C5525x11(), this.Y, true);
    }

    @Override // defpackage.AbstractC2300e70
    public void E(long j, int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
    }

    @Override // defpackage.AbstractC2300e70
    public void G(boolean z) {
    }

    @Override // defpackage.AbstractC2300e70
    public void I(long j, int i, int i2, boolean z) {
    }

    @Override // defpackage.AbstractC2300e70
    public void J(long j, int i) {
    }

    @Override // defpackage.AbstractC2300e70
    public void O(AbstractC0124Ca1 ca1, TabContentManager tabContentManager) {
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        this.Q = false;
        this.R = true;
        this.S = -1;
        this.n0 = true;
        Tab j2 = ((AbstractC0246Ea1) this.f0).j();
        if (j2 != null) {
            Y(j2);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void R() {
        if (this.Z.h(J70.F) && this.e0) {
            this.d0.removeCallbacks(this.c0);
            this.c0.run();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void T(long j, long j2) {
        W(j2, this.Z);
    }

    @Override // defpackage.AbstractC2300e70
    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
        boolean z;
        if (tabContentManager != null) {
            int f = this.Z.f(J70.e);
            long j = tabContentManager.g;
            if (j == 0) {
                z = false;
            } else {
                z = N.M6RdXfrR(j, tabContentManager, f);
            }
            if (z) {
                AbstractC5109uc1.b();
            }
        }
    }

    public final void X() {
        this.d0.removeCallbacks(this.c0);
        this.Z.k(J70.C, 0.0f);
        this.Z.k(J70.u, 1.0f);
        this.e0 = false;
    }

    public final void Y(Tab tab) {
        UH0 uh0 = this.Z;
        SH0 sh0 = J70.e;
        if (uh0.f(sh0) != tab.getId() || this.Z.h(J70.F)) {
            TabContentManager tabContentManager = this.k0;
            if (tabContentManager != null) {
                tabContentManager.k(new LinkedList(Arrays.asList(Integer.valueOf(tab.getId()))), tab.getId());
            }
            this.Z.l(sh0, tab.getId());
            this.Z.j(J70.f, tab.a());
            this.Z.k(J70.y, ((float) this.X.getWidth()) * this.p0);
            this.Z.k(J70.z, ((float) this.X.getHeight()) * this.p0);
            this.Z.k(J70.A, ((float) this.X.getWidth()) * this.p0);
            this.Z.k(J70.B, ((float) this.X.getHeight()) * this.p0);
            Z(tab);
            if (this.Z.h(J70.F)) {
                this.d0.removeCallbacks(this.c0);
                this.Z.k(J70.C, 1.0f);
                this.Z.k(J70.u, 0.0f);
                this.e0 = true;
                this.d0.postDelayed(this.c0, 2000);
                return;
            }
            X();
            return;
        }
        X();
    }

    public final void Z(Tab tab) {
        if (this.n0 && this.Z.f(J70.e) == tab.getId()) {
            C2921hm1 hm1 = (C2921hm1) this.m0.get();
            UH0 uh0 = this.Z;
            SH0 sh0 = J70.T;
            Objects.requireNonNull(hm1);
            uh0.l(sh0, AbstractC1300Vg1.a(tab));
            this.Z.l(J70.U, hm1.f(tab));
            this.Z.k(J70.W, ((C2921hm1) this.m0.get()).g(tab));
            boolean z = true;
            this.Z.j(J70.F, (tab.G() || tab.f()) && !AbstractC5818ym0.a(tab.s(), tab.a()));
            this.Z.l(J70.V, Pj1.b(this.W.getResources(), tab, ((C2921hm1) this.m0.get()).e(tab, tab.m())));
            String s = tab.s();
            boolean z2 = tab.isNativePage() || (s != null && s.startsWith("chrome-native://"));
            if (tab.l() == null || C3372kO0.W(tab) || z2) {
                z = false;
            }
            this.Z.j(J70.G, z);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void f() {
        J11 j11 = this.b0;
        if (j11 != null) {
            N.MPFnESYL(j11.F, j11);
            j11.G = 0;
            this.b0 = null;
        }
        C0297Ew ew = this.a0;
        if (ew != null) {
            UH0 uh0 = ew.b;
            uh0.f9530a.c(ew.e);
            C0236Dw dw = ew.d;
            dw.I.c(ew.f);
            this.a0 = null;
        }
        if (this.f0 != null) {
            this.g0.destroy();
            this.h0.destroy();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void h() {
        super.h();
        this.n0 = false;
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return null;
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 0;
    }

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.b0;
    }

    @Override // defpackage.AbstractC2300e70
    public int r() {
        return 2;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean s() {
        return this.V;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean t() {
        return this.V;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean u() {
        J70[] j70Arr = this.P;
        if (j70Arr != null) {
            int length = j70Arr.length;
        }
        if (this.V) {
            return true;
        }
        return false;
    }
}
