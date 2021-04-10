package defpackage;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: Zj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1551Zj implements Z9, AbstractC3976nw1, AbstractC1888bk {
    public final Activity F;
    public final C0090Bk G;
    public final int H;
    public final C2399ej1 I = new C2399ej1(new RunnableC0941Pj(this));

    /* renamed from: J  reason: collision with root package name */
    public final C1078Rq0 f9362J;
    public AbstractC1099Sa1 K;
    public DA L;
    public int M;
    public int N;
    public int O;
    public int P;
    public boolean Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public float W;
    public Y2 X;
    public final C1322Vq0 Y;
    public ST Z;
    public Tab a0;
    public ValueAnimator b0;
    public boolean c0;
    public final Runnable d0;

    public C1551Zj(Activity activity, int i) {
        C1078Rq0 rq0 = new C1078Rq0();
        this.f9362J = rq0;
        this.Y = new C1322Vq0();
        this.d0 = new RunnableC1246Uj(this);
        this.F = activity;
        this.H = i;
        rq0.m(Boolean.FALSE);
        ST st = new ST(activity, rq0, true);
        this.Z = st;
        C0090Bk bk = new C0090Bk(st.H);
        this.G = bk;
        bk.l(new C1002Qj(this));
        VrModuleProvider.b.add(this);
        Objects.requireNonNull(VrModuleProvider.b());
    }

    public static void a(C1551Zj zj, int i, int i2, int i3, int i4, int i5) {
        if (zj.c0) {
            ValueAnimator valueAnimator = zj.b0;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            zj.c0 = false;
        }
        Tab tab = zj.a0;
        if (C3372kO0.W(tab) || tab.isNativePage()) {
            zj.j(false);
        } else {
            zj.k(false, i, i2, i3, i4, i5);
        }
        if (AbstractC5109uc1.f11423a > 0) {
            AbstractC5109uc1.c = true;
        }
    }

    public int b() {
        return Math.min(this.S, this.O);
    }

    public float c() {
        return (float) (this.M + this.R);
    }

    public void d(DA da, C1595a3 a3Var, AbstractC0124Ca1 ca1, int i) {
        ST st = this.Z;
        ApplicationStatus.g(st, st.F);
        ApplicationStatus.i.b(st);
        st.S = new MT(st, a3Var);
        st.T = new NT(st, ca1, ca1);
        ApplicationStatus.g(this, this.F);
        this.X = new C1307Vj(this, a3Var);
        this.K = new C1368Wj(this, ca1);
        this.L = da;
        int i2 = this.H;
        if (i2 == 0) {
            this.M = this.F.getResources().getDimensionPixelSize(i);
        } else if (i2 == 1) {
            this.W = 1.0f;
        }
        this.T = this.M;
        l();
        f();
    }

    public final void e() {
        if (this.c0) {
            ValueAnimator valueAnimator = this.b0;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.c0 = false;
        }
        Tab tab = this.a0;
        C1957c61 c61 = null;
        if (tab != null) {
            c61 = C1957c61.V(tab);
        }
        if (c61 == null || !c61.L || tab == null || tab.isNativePage()) {
            j(false);
        } else {
            k(false, c61.G, c61.H, c61.I, c61.f9583J, c61.K);
        }
        C1786b61.l(tab);
    }

    public final void f() {
        if (this.L != null) {
            int i = i() ? 0 : 4;
            ToolbarControlContainer toolbarControlContainer = (ToolbarControlContainer) this.L;
            Objects.requireNonNull(toolbarControlContainer);
            if (toolbarControlContainer.getVisibility() != i) {
                ToolbarControlContainer toolbarControlContainer2 = (ToolbarControlContainer) this.L;
                Objects.requireNonNull(toolbarControlContainer2);
                toolbarControlContainer2.removeCallbacks(this.d0);
                ToolbarControlContainer toolbarControlContainer3 = (ToolbarControlContainer) this.L;
                Objects.requireNonNull(toolbarControlContainer3);
                toolbarControlContainer3.postOnAnimation(this.d0);
            }
        }
    }

    public final void g(int i, int i2, int i3, int i4, int i5) {
        int max = Math.max(i, -this.M);
        int min = Math.min(i2, this.O);
        int min2 = Math.min(i3, this.M + max);
        if (max != this.R || min != this.S || min2 != this.T || i4 != this.U || i5 != this.V) {
            this.R = max;
            this.S = min;
            this.U = i4;
            this.V = i5;
            this.T = min2;
            this.f9362J.m(Boolean.valueOf(min2 == this.N && AbstractC2571fk.b(this) == this.P));
            l();
            f();
            if (i()) {
                ToolbarControlContainer toolbarControlContainer = (ToolbarControlContainer) this.L;
                Objects.requireNonNull(toolbarControlContainer);
                toolbarControlContainer.setTranslationY((float) this.R);
            }
            boolean i6 = i();
            Iterator it = this.Y.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC2230dk) uq0.next()).j(this.R, this.U, b(), this.V, i6);
                } else {
                    return;
                }
            }
        }
    }

    public final void h() {
        Tab tab = this.a0;
        if (tab == null || !tab.isInitialized() || C1786b61.e(tab) != 2) {
            g(0, 0, this.M, this.N, this.P);
            return;
        }
        int i = this.N;
        int i2 = i - this.M;
        int i3 = this.O;
        int i4 = this.P;
        g(i2, i3 - i4, i, i, i4);
    }

    public final boolean i() {
        if (this.L == null || this.I.b()) {
            return false;
        }
        if (this.c0) {
            return true;
        }
        boolean z = !(this.W > 0.0f);
        Tab tab = this.a0;
        AbstractViewGroup$OnHierarchyChangeListenerC1520Yy u = tab != null ? tab.u() : null;
        if (u == null) {
            return z;
        }
        for (int i = 0; i < u.getChildCount(); i++) {
            View childAt = u.getChildAt(i);
            if ((childAt.getLayoutParams() instanceof FrameLayout.LayoutParams) && 48 == (((FrameLayout.LayoutParams) childAt.getLayoutParams()).gravity & 112)) {
                return true;
            }
        }
        return z;
    }

    public void j(boolean z) {
        if (!z) {
            k(true, 0, 0, this.M, this.N, this.P);
        } else if (this.b0 == null) {
            this.c0 = true;
            float f = this.W;
            int i = this.M;
            ValueAnimator ofInt = ValueAnimator.ofInt(this.R, 0);
            this.b0 = ofInt;
            ofInt.setDuration((long) Math.abs(f * 200.0f));
            this.b0.addListener(new C1429Xj(this, i));
            this.b0.addUpdateListener(new C1124Sj(this, i));
            this.b0.start();
        }
    }

    public final void k(boolean z, int i, int i2, int i3, int i4, int i5) {
        Objects.requireNonNull(VrModuleProvider.b());
        if (z) {
            h();
        } else {
            g(i, i2, i3, i4, i5);
        }
    }

    public final void l() {
        if (this.H != 1) {
            int i = this.M;
            if (i == 0) {
                this.W = 1.0f;
            } else {
                this.W = Math.abs(((float) this.R) / ((float) i));
            }
        }
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 2) {
            C3070if1 if1 = Zo1.f9374a;
            C0090Bk bk = this.G;
            bk.getClass();
            PostTask.b(if1, new RunnableC1063Rj(bk), 100);
        } else if (i == 6) {
            ApplicationStatus.h(this);
        }
    }
}
