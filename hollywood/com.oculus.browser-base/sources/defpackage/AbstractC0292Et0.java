package defpackage;

import J.N;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.bottombar.OverlayPanelContent;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Et0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0292Et0 extends AbstractC0536It0 implements Z9, AbstractC5194v41, AbstractC4581rV, AbstractC0840Nt0, LO0 {
    public boolean A0;
    public boolean B0;
    public boolean C0;
    public float D0;
    public float E0;
    public final D70 q0;
    public final KO0 r0;
    public ChromeActivity s0;
    public float t0;
    public float u0;
    public boolean v0;
    public VL w0;
    public AbstractC0840Nt0 x0 = this;
    public OverlayPanelContent y0;
    public C1328Vt0 z0;

    public AbstractC0292Et0(Context context, D70 d70, C1328Vt0 vt0) {
        super(context, d70);
        this.q0 = d70;
        this.z0 = vt0;
        IJ ij = vt0.e;
        if (ij != null) {
            this.l0 = ij;
        }
        ViewGroup viewGroup = vt0.f;
        if (viewGroup != null) {
            this.k0 = viewGroup;
        }
        vt0.f9113a.add(this);
        this.w0 = new C1084Rt0(context, this);
        C0109Bt0 bt0 = new C0109Bt0(this);
        this.r0 = bt0;
        if (d70 != null) {
            d70.R.b(bt0);
        }
    }

    public void W() {
        OverlayPanelContent overlayPanelContent = this.y0;
        if (overlayPanelContent != null) {
            if (overlayPanelContent.f10635a != null) {
                overlayPanelContent.b();
            }
            long j = overlayPanelContent.c;
            if (j != 0) {
                N.MUq5ITc4(j, overlayPanelContent);
            }
            this.y0 = null;
        }
    }

    public abstract float X();

    public OverlayPanelContent Y() {
        if (this.y0 == null) {
            C1796bA bAVar = (C1796bA) this.x0;
            ContextualSearchManager contextualSearchManager = (ContextualSearchManager) bAVar.I0;
            Objects.requireNonNull(contextualSearchManager);
            OverlayPanelContent overlayPanelContent = new OverlayPanelContent(new C1461Xz(contextualSearchManager), new C0231Dt0(bAVar), bAVar.s0, false, bAVar.Y);
            int H = H();
            int round = Math.round(this.R / this.F);
            boolean M = M();
            overlayPanelContent.q = H;
            overlayPanelContent.r = round;
            overlayPanelContent.s = M;
            this.y0 = overlayPanelContent;
        }
        return this.y0;
    }

    public WebContents Z() {
        OverlayPanelContent overlayPanelContent = this.y0;
        if (overlayPanelContent != null) {
            return overlayPanelContent.f10635a;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d9, code lost:
        if (r5 <= ((r6.I() + r6.q0()) + ((float) r6.M))) goto L_0x00dd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a0(float r5, float r6) {
        /*
        // Method dump skipped, instructions count: 235
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0292Et0.a0(float, float):void");
    }

    @Override // defpackage.AbstractC4581rV
    public void b(float f, float f2, float f3, float f4) {
        b0(f4);
    }

    public void b0(float f) {
        this.v0 = true;
        C1796bA bAVar = (C1796bA) this;
        int C = bAVar.C(bAVar.U - ((218.0f * f) / 2000.0f), f);
        int i = 2;
        if (bAVar.r0().U && C == 4 && bAVar.L == 2) {
            C = 3;
        }
        if (!(bAVar.L == 4 && C == 3)) {
            i = C;
        }
        w(Integer.valueOf(i), 14, x(f, J(Integer.valueOf(i)) - this.U));
    }

    @Override // defpackage.LO0
    public VL c() {
        return this.w0;
    }

    public void c0() {
        if (!this.v0) {
            this.v0 = true;
            int C = C(this.U, 0.0f);
            w(Integer.valueOf(C), 13, x(1750.0f, J(Integer.valueOf(C)) - this.U));
        }
    }

    @Override // defpackage.AbstractC5194v41
    public void d(int i, MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        if (!this.B0) {
            b0(f4 * this.F);
        }
    }

    public void d0(float f) {
        WebContents webContents;
        OverlayPanelContent overlayPanelContent = this.y0;
        if (overlayPanelContent != null && f > 0.0f && ((C1796bA) this).L == 4 && (webContents = overlayPanelContent.f10635a) != null) {
            webContents.n0().f(0.0f, 0.0f);
        }
        U(AbstractC4089od0.b(this.t0 - f, J(Integer.valueOf(G())), J(2)));
        R();
    }

    @Override // defpackage.LO0
    public boolean e(long j, long j2) {
        if (!N()) {
            return true;
        }
        k0(false);
        return true;
    }

    public boolean e0() {
        OverlayPanelContent overlayPanelContent = this.y0;
        return overlayPanelContent != null && overlayPanelContent.k;
    }

    @Override // defpackage.AbstractC5194v41
    public void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        if (!this.B0) {
            d0(f2 * this.F);
        }
    }

    public boolean f0(float f, float f2) {
        if (h0(f, f2)) {
            float f3 = this.T;
            return f2 >= f3 && f2 <= E() + f3;
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void g(float f, float f2, float f3, float f4, boolean z) {
    }

    public boolean g0(float f, float f2) {
        return h0(f, f2) && f2 > X();
    }

    @Override // defpackage.AbstractC5194v41
    public void h() {
        if (this.B0) {
            this.B0 = false;
        } else {
            c0();
        }
    }

    public boolean h0(float f, float f2) {
        float f3 = this.T;
        if (f2 >= f3 && f2 <= f3 + this.U) {
            float f4 = this.S;
            return f >= f4 && f <= f4 + this.Q;
        }
    }

    @Override // defpackage.AbstractC5194v41
    public boolean i(int i) {
        return i == 3 && O();
    }

    public boolean i0() {
        return A(2);
    }

    @Override // defpackage.AbstractC5194v41
    public void j(int i, MotionEvent motionEvent) {
        if (((C1796bA) this).t0()) {
            this.B0 = true;
            return;
        }
        C5677xw xwVar = this.o0;
        if (xwVar != null) {
            xwVar.cancel();
        }
        this.v0 = false;
        this.t0 = this.U;
    }

    public abstract void j0(int i);

    @Override // defpackage.LO0
    public boolean k() {
        return N();
    }

    public void k0(boolean z) {
        WebContents l;
        ChromeActivity chromeActivity = this.s0;
        if (chromeActivity != null && chromeActivity.K0() != null && (l = this.s0.K0().l()) != null) {
            if (N() && this.A0 && !z) {
                return;
            }
            if (N() || this.A0 || !z) {
                this.A0 = !z;
                SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(l);
                if (!z) {
                    r.j0 = true;
                }
                View containerView = l.F() != null ? l.F().getContainerView() : null;
                if (containerView != null) {
                    if (z) {
                        containerView.requestFocus();
                    } else {
                        containerView.clearFocus();
                    }
                }
                r.I(z);
            }
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void l() {
        c0();
    }

    @Override // defpackage.LO0
    public void m(float f, float f2, float f3, int i) {
        C1175Tf1 tf1;
        if (f2 != this.E0 || f != this.D0) {
            this.D0 = f;
            this.E0 = f2;
            float f4 = this.N;
            int i2 = (f > f4 ? 1 : (f == f4 ? 0 : -1));
            if (!(i2 == 0 && f2 == this.O && f3 == this.P)) {
                this.N = f;
                this.O = f2;
                this.P = f3;
                this.Q = M() ? this.N : 600.0f;
                this.R = J(4);
                if (O()) {
                    boolean z = f4 <= 680.0f;
                    boolean M = M();
                    if ((M && z) || (!M && !z && i2 == 0)) {
                        Integer num = this.m0;
                        if (num == null || num.intValue() != 0) {
                            w(this.m0, this.n0, 218);
                        } else {
                            C1796bA bAVar = (C1796bA) this;
                            if (bAVar.r0().U) {
                                bAVar.r0().f(true);
                            }
                            Objects.requireNonNull(bAVar.o0());
                            bAVar.N0 = 0.0f;
                            bAVar.f8254J = bAVar.y();
                            bAVar.S(bAVar.L, 0);
                            C0059Az az = ((ContextualSearchManager) bAVar.I0).N;
                            if (az.f && (tf1 = az.c) != null && tf1.I.c()) {
                                C4390qK0 qk0 = az.d;
                                qk0.F.set(az.a());
                                AbstractC4219pK0 pk0 = qk0.G;
                                if (pk0 != null) {
                                    pk0.a();
                                }
                            }
                        }
                    } else {
                        this.k0.getHandler().post(new RunnableC0414Gt0(this));
                    }
                }
            }
            if (O()) {
                OverlayPanelContent Y = Y();
                int H = H();
                int round = Math.round(this.R / this.F);
                boolean M2 = M();
                Y.q = H;
                Y.r = round;
                Y.s = M2;
                Y.d();
            }
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void n(float f, float f2, boolean z, int i) {
        this.u0 = f2;
        C5677xw xwVar = this.o0;
        if (xwVar != null) {
            xwVar.cancel();
        }
        this.v0 = false;
        this.t0 = this.U;
    }

    @Override // defpackage.AbstractC4581rV
    public void o(float f, float f2, boolean z, int i) {
        a0(f, f2);
    }

    @Override // defpackage.AbstractC4581rV
    public void p(float f, float f2) {
    }

    @Override // defpackage.AbstractC4581rV
    public void r(float f, float f2, float f3, float f4, float f5, float f6) {
        d0(f2 - this.u0);
    }

    @Override // defpackage.LO0
    public void s(List list) {
    }

    @Override // defpackage.LO0
    public boolean u() {
        return O();
    }

    @Override // defpackage.LO0
    public boolean v() {
        if (!N()) {
            return false;
        }
        C1786b61.j(this.s0.K0(), 3, false);
        return true;
    }

    @Override // defpackage.AbstractC0536It0
    public abstract void z(int i, boolean z);
}
