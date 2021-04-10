package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.vr.VrModuleProvider;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Ij  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0515Ij extends OK implements AbstractC4371qE {
    public final AbstractC0956Pq0 F;
    public final Callback G;
    public final AbstractC3976nw1 H;
    public final TT I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC2230dk f8245J;
    public final W2 K;
    public final AbstractC1404Xa1 L;
    public final C1128Sl M;
    public final AbstractC1509Ys0 N;
    public AbstractC2280e01 O;
    public AbstractC1888bk P;
    public UT Q;
    public int R = -1;
    public int S = -1;
    public C5638xj T;
    public C1595a3 U;
    public Q31 V;
    public Q31 W;
    public C1343Wa1 X;
    public int Y;
    public Q31 Z;
    public Tab a0;
    public boolean b0;
    public int c0;
    public int d0;

    public C0515Ij(C5638xj xjVar, C1595a3 a3Var, AbstractC1888bk bkVar, UT ut, Q31 q31, Q31 q312, C1343Wa1 wa1, AbstractC0956Pq0 pq0, Q31 q313, AbstractC1509Ys0 ys0) {
        this.T = xjVar;
        this.U = a3Var;
        this.P = bkVar;
        this.Q = ut;
        this.V = q31;
        this.W = q312;
        this.X = wa1;
        this.Y = -1;
        this.F = pq0;
        this.Z = q313;
        this.N = ys0;
        C1128Sl sl = new C1128Sl();
        this.M = sl;
        ys0.g(sl.b(new C0027Aj(this)));
        this.T.j(this);
        this.T.O = C0283Ep.h();
        this.L = new C0088Bj(this, xjVar);
        C0149Cj cj = new C0149Cj(this, xjVar);
        this.K = cj;
        C1595a3 a3Var2 = this.U;
        a3Var2.F.b(cj);
        cj.a(a3Var2.H, false);
        C0210Dj dj = new C0210Dj(this, xjVar);
        this.H = dj;
        VrModuleProvider.b.add(dj);
        C0271Ej ej = new C0271Ej(this, xjVar);
        this.f8245J = ej;
        ((C1551Zj) this.P).Y.b(ej);
        C0332Fj fj = new C0332Fj(this, xjVar);
        this.I = fj;
        this.Q.b(fj);
        C0393Gj gj = new C0393Gj(this, xjVar);
        this.G = gj;
        ((C1078Rq0) pq0).l(gj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r6.intValue() != 7) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r5 == null) goto L_0x0032;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void l(defpackage.C0515Ij r4, org.chromium.chrome.browser.tab.Tab r5, java.lang.Integer r6) {
        /*
            Ys0 r0 = r4.N
            java.lang.Object r0 = r0.get()
            f01 r0 = (defpackage.AbstractC2451f01) r0
            r1 = 1
            r2 = 0
            if (r5 != 0) goto L_0x000f
            if (r0 != 0) goto L_0x000f
            goto L_0x0032
        L_0x000f:
            if (r6 == 0) goto L_0x002e
            int r0 = r6.intValue()
            r3 = 10
            if (r0 == r3) goto L_0x0031
            int r0 = r6.intValue()
            if (r0 != r1) goto L_0x0020
            goto L_0x0031
        L_0x0020:
            int r0 = r6.intValue()
            if (r0 == 0) goto L_0x002e
            int r6 = r6.intValue()
            r0 = 7
            if (r6 == r0) goto L_0x002e
            goto L_0x0032
        L_0x002e:
            if (r5 != 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r1 = r2
        L_0x0032:
            if (r1 == 0) goto L_0x0042
            int r5 = r4.d0
            if (r5 != 0) goto L_0x004d
            xj r5 = r4.T
            r6 = 5
            int r5 = r5.z(r6)
            r4.d0 = r5
            goto L_0x004d
        L_0x0042:
            xj r5 = r4.T
            int r6 = r4.d0
            ej1 r5 = r5.N
            r5.c(r6)
            r4.d0 = r2
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0515Ij.l(Ij, org.chromium.chrome.browser.tab.Tab, java.lang.Integer):void");
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.M.a();
        Tab tab = this.a0;
        if (tab != null) {
            tab.I(this.L);
        }
        C1595a3 a3Var = this.U;
        a3Var.F.c(this.K);
        this.T.r(this);
        this.Q.f(this.I);
        AbstractC1888bk bkVar = this.P;
        ((C1551Zj) bkVar).Y.c(this.f8245J);
        AbstractC0956Pq0 pq0 = this.F;
        ((C1078Rq0) pq0).I.c(this.G);
        VrModuleProvider.b.remove(this.H);
        if (this.N.get() != null) {
            ((C3818n01) ((AbstractC2451f01) this.N.get())).c.L.c(this.O);
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void f(float f, float f2) {
        if (this.W.get() != null) {
            View$OnClickListenerC5098uY0 uy0 = (View$OnClickListenerC5098uY0) this.W.get();
            if (!uy0.I.b()) {
                C4418qY0 qy0 = uy0.I;
                while (!qy0.b()) {
                    qy0.c(false);
                }
                uy0.m();
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void g(int i) {
        WebContents l;
        AbstractC1888bk bkVar = this.P;
        if (((C1551Zj) bkVar).G != null) {
            this.c0 = ((C1551Zj) bkVar).G.q();
        }
        Tab tab = this.U.H;
        if (!(tab == null || (l = tab.l()) == null)) {
            SelectionPopupControllerImpl.r(l).m();
        }
        AbstractC4277pj n = this.T.n();
        if (n == null || !n.s()) {
            m(true);
            if (this.V.get() != null) {
                this.R = ((C2746gl0) this.V.get()).l(0);
                this.S = ((C2746gl0) this.V.get()).l(1);
                return;
            }
            return;
        }
        this.b0 = true;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void k(int i) {
        AbstractC1888bk bkVar = this.P;
        if (((C1551Zj) bkVar).G != null) {
            ((C1551Zj) bkVar).G.p(this.c0);
        }
        this.T.n();
        if (this.b0) {
            this.b0 = false;
            return;
        }
        m(false);
        if (!(this.V.get() == null || (this.R == -1 && this.S == -1))) {
            ((C2746gl0) this.V.get()).g(0, this.R);
            ((C2746gl0) this.V.get()).g(1, this.S);
        }
        this.R = -1;
        this.S = -1;
    }

    public final void m(boolean z) {
        if (z) {
            this.Y = this.X.a();
            return;
        }
        C1343Wa1 wa1 = this.X;
        wa1.f9156a.c(this.Y);
        this.Y = -1;
    }
}
