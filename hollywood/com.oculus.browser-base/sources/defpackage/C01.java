package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.RectF;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: C01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C01 extends AbstractC2300e70 {
    public AnimatorSet V;
    public boolean W;
    public Q91 X;
    public final AbstractC2451f01 Y;
    public final AbstractC1939c01 Z;
    public final C5862z01 a0;
    public final AbstractC5789yc1 b0;
    public final J70 c0;
    public boolean d0;
    public float e0;
    public int f0;
    public long g0;
    public long h0;
    public long i0;
    public int j0;
    public float k0;
    public boolean l0;

    public C01(Context context, K70 k70, F70 f70, AbstractC2451f01 f01) {
        super(context, k70, f70);
        J70 d = d(-1, false, false, false);
        this.c0 = d;
        d.j(J70.H, true);
        this.Y = f01;
        ((C3818n01) f01).e(new C3989o01(this));
        M01 m01 = ((C3818n01) f01).c;
        this.Z = m01;
        C5862z01 z01 = new C5862z01(this);
        this.a0 = z01;
        m01.F.b(z01);
        this.b0 = ((C3818n01) f01).a();
        if (AbstractC4772sd1.i()) {
            float c = (float) AbstractC4772sd1.c.c();
            this.k0 = c;
            this.k0 = AbstractC4089od0.b(c, 0.5f, 2.0f);
        }
    }

    public static void X(C01 c01, boolean z) {
        int i = c01.f0 - c01.j0;
        long elapsedRealtime = SystemClock.elapsedRealtime() - c01.g0;
        int f = (int) (c01.b0.f() - c01.g0);
        float f2 = (((float) i) * 1000.0f) / ((float) elapsedRealtime);
        String.format(Locale.US, "fps = %.2f (%d / %dms), maxFrameInterval = %d, dirtySpan = %d", Float.valueOf(f2), Integer.valueOf(i), Long.valueOf(elapsedRealtime), Long.valueOf(c01.i0), Integer.valueOf(f));
        String str = z ? ".Shrink" : ".Expand";
        AbstractC3364kK0.c(AbstractC2531fV.f("GridTabSwitcher.FramePerSecond", str), (int) f2);
        AbstractC3364kK0.k("GridTabSwitcher.MaxFrameInterval" + str, c01.i0);
        AbstractC3364kK0.k("GridTabSwitcher.DirtySpan" + str, (long) f);
    }

    @Override // defpackage.AbstractC2300e70
    public void A() {
        if (!this.d0) {
            this.d0 = true;
            ((C3818n01) this.Y).b();
            Y();
            this.X.H = this.L;
        }
    }

    @Override // defpackage.AbstractC2300e70
    public boolean L(long j, boolean z) {
        return this.V == null && !this.W;
    }

    @Override // defpackage.AbstractC2300e70
    public void O(AbstractC0124Ca1 ca1, TabContentManager tabContentManager) {
        this.L = ca1;
        N(tabContentManager);
        Q91 q91 = this.X;
        if (q91 != null) {
            q91.H = ca1;
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        float f;
        this.Q = false;
        this.R = true;
        this.S = -1;
        ((C3818n01) this.Y).c();
        boolean z2 = z && AbstractC4772sd1.j() && !(((AbstractC0246Ea1) this.L).i().getCount() == 0);
        boolean l = this.b0.l();
        if (AbstractC4772sd1.f11289a.c()) {
            z2 &= l;
        }
        if (AbstractC4772sd1.d()) {
            z2 &= !C0283Ep.h().d();
        }
        J70 d = d(((AbstractC0246Ea1) this.L).k(), ((AbstractC0246Ea1) this.L).r(), false, false);
        d.k(J70.Q, 0.0f);
        this.P = new J70[]{d};
        if (!z2) {
            ((M01) this.Z).q(z);
            return;
        }
        C4160p01 p01 = new C4160p01(this);
        if (p01.get() == null) {
            ((M01) this.Z).q(z);
            return;
        }
        j();
        J70 j70 = this.P[0];
        C4316pw m = m();
        ArrayList arrayList = new ArrayList(5);
        C4331q01 q01 = new C4331q01();
        C4501r01 r01 = new C4501r01(this, p01);
        C4672s01 s01 = new C4672s01();
        C4842t01 t01 = new C4842t01(this, p01);
        C5012u01 u01 = new C5012u01();
        C5182v01 v01 = new C5182v01(this, p01);
        RH0 rh0 = J70.g;
        animation.InterpolatorC2176dO dOVar = G30.c;
        arrayList.add(C5677xw.h(m, j70, rh0, q01, r01, 300, dOVar));
        arrayList.add(C5677xw.h(m, j70, J70.l, s01, t01, 300, dOVar));
        arrayList.add(C5677xw.h(m, j70, J70.m, u01, v01, 300, dOVar));
        RH0 rh02 = J70.B;
        float A = j70.A();
        if (AbstractC4772sd1.i()) {
            f = Math.min(this.F / this.k0, j70.A());
        } else {
            f = this.F;
        }
        arrayList.add(C5677xw.g(m, j70, rh02, A, f, 300, dOVar));
        C5677xw c = C5677xw.c(m, 0.0f, 1.0f, 150, new C5352w01(this));
        c.K = G30.b;
        arrayList.add(c);
        AnimatorSet animatorSet = new AnimatorSet();
        this.V = animatorSet;
        animatorSet.playTogether(arrayList);
        this.V.addListener(new A01(this));
        this.j0 = this.f0;
        this.g0 = SystemClock.elapsedRealtime();
        this.h0 = SystemClock.elapsedRealtime();
        this.i0 = 0;
        this.V.start();
    }

    @Override // defpackage.AbstractC2300e70
    public void Q(int i, boolean z) {
        this.N.e(i, z);
        this.Q = true;
        this.S = i;
        if (i == -1) {
            i = ((AbstractC0246Ea1) this.L).k();
        }
        J70 d = d(i, ((AbstractC0246Ea1) this.L).r(), false, false);
        RH0 rh0 = J70.Q;
        d.k(rh0, 0.0f);
        ArrayList arrayList = new ArrayList();
        arrayList.add(d);
        if (i != ((AbstractC0246Ea1) this.L).k()) {
            J70 d2 = d(((AbstractC0246Ea1) this.L).k(), ((AbstractC0246Ea1) this.L).r(), false, false);
            d2.k(J70.g, 0.0f);
            d2.k(rh0, 0.0f);
            arrayList.add(d2);
        }
        this.P = (J70[]) arrayList.toArray(new J70[0]);
        LinkedList linkedList = new LinkedList(Arrays.asList(Integer.valueOf(i)));
        TabContentManager tabContentManager = this.M;
        if (tabContentManager != null) {
            tabContentManager.k(linkedList, -1);
        }
        this.W = true;
        ((M01) this.Z).G.c(true ^ AbstractC4772sd1.j());
    }

    @Override // defpackage.AbstractC2300e70
    public void T(long j, long j2) {
        Y();
        J70[] j70Arr = this.P;
        if (j70Arr != null && W(j2, j70Arr[0])) {
            M();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
        Y();
        this.X.d(this.f9833J, rectF2, this, layerTitleCache, tabContentManager, resourceManager, ekVar, AbstractC4772sd1.j() ? this.b0.g() : 0, this.e0, ((C3818n01) this.Y).a().k());
        this.f0++;
        if (this.h0 != 0) {
            this.i0 = Math.max(this.i0, SystemClock.elapsedRealtime() - this.h0);
        }
        this.h0 = SystemClock.elapsedRealtime();
    }

    public final void Y() {
        if (this.X == null) {
            this.X = new Q91();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public boolean b() {
        return !AbstractC4772sd1.d() || !C0283Ep.h().d();
    }

    @Override // defpackage.AbstractC2300e70
    public void f() {
        AbstractC1939c01 c01 = this.Z;
        if (c01 != null) {
            ((M01) c01).F.c(this.a0);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void h() {
        super.h();
        AbstractC3535lK0.a("MobileExitStackView");
    }

    @Override // defpackage.AbstractC2300e70
    public void i() {
        if (this.l0) {
            super.i();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void j() {
        AnimatorSet animatorSet = this.V;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.V.end();
        }
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return null;
    }

    @Override // defpackage.AbstractC2300e70
    public J70 o(int i) {
        return this.c0;
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 1;
    }

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.X;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean s() {
        return false;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean t() {
        return true;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean u() {
        return true;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean z() {
        if (((AbstractC0246Ea1) this.L).i().getCount() == 0) {
            return false;
        }
        return ((M01) this.Z).h();
    }
}
