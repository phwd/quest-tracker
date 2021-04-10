package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: M01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M01 implements AbstractC1939c01, AbstractC5619xc1, View.OnClickListener {
    public final C1322Vq0 F = new C1322Vq0();
    public final AbstractC5279vc1 G;
    public final AbstractC0124Ca1 H;
    public final UH0 I;

    /* renamed from: J  reason: collision with root package name */
    public final C2964i01 f8454J;
    public final int K;
    public final C1322Vq0 L = new C1322Vq0();
    public EM M;
    public AbstractC5279vc1 N;
    public UH0 O;
    public boolean P;
    public AbstractC1834bO Q;
    public Context R;
    public AbstractC1740ar1 S;
    public int T;
    public int U;
    public TabModel V;
    public AbstractC5783ya1 W;
    public AbstractC0612Ka1 X;
    public AbstractC2400ek Y;
    public AbstractC2230dk Z;
    public C3134j01 a0;
    public boolean b0;
    public boolean c0;
    public AbstractC1509Ys0 d0;
    public boolean e0;
    public Boolean f0;
    public Boolean g0;
    public boolean h0;

    public M01(AbstractC5279vc1 vc1, AbstractC0124Ca1 ca1, UH0 uh0, C2964i01 i01, int i, Context context, AbstractC2400ek ekVar, C3134j01 j01, boolean z, boolean z2, AbstractC1509Ys0 ys0, boolean z3) {
        this.G = vc1;
        this.H = ca1;
        this.I = uh0;
        this.f8454J = i01;
        this.K = i;
        this.R = context;
        this.Y = ekVar;
        this.a0 = j01;
        this.b0 = z;
        this.c0 = z2;
        this.d0 = ys0;
        this.h0 = z3;
        if (uh0 != null) {
            AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
            boolean r = ea1.r();
            this.P = r;
            this.X = new G01(this);
            uh0.j(AbstractC5798yf1.b, r);
            if (i == 2) {
                uh0.m(N01.f8519a, new H01(this));
            }
            if (i == 3) {
                uh0.m(AbstractC5798yf1.q, this);
                this.W = new I01(this);
                if (ea1.f7969a.isEmpty()) {
                    ea1.c(new J01(this));
                } else {
                    this.V = ea1.l(false);
                }
            }
            this.Z = new K01(this);
            this.S = new L01(this);
            if (!(i == 4 || i == 5)) {
                Resources resources = this.R.getResources();
                uh0.l(AbstractC5798yf1.s, resources.getDimensionPixelSize(R.dimen.f25850_resource_name_obfuscated_RES_2131166204));
                uh0.l(AbstractC5798yf1.t, resources.getDimensionPixelSize(R.dimen.f22720_resource_name_obfuscated_RES_2131165891));
                uh0.l(AbstractC5798yf1.u, resources.getDimensionPixelSize(R.dimen.f25770_resource_name_obfuscated_RES_2131166196));
            }
        }
        vc1.d(this);
        this.U = 0;
        this.T = 0;
    }

    @Override // defpackage.AbstractC5619xc1
    public void a() {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                C5862z01 z01 = (C5862z01) uq0.next();
                C01 c01 = z01.f11716a;
                c01.l0 = true;
                c01.i();
                if (AbstractC4772sd1.j()) {
                    new Handler().postDelayed(new RunnableC5692y01(z01), 300);
                }
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5619xc1
    public void b() {
        if (this.I != null) {
            AbstractC1834bO bOVar = this.Q;
            if (bOVar != null) {
                ((View$OnKeyListenerC0001Aa0) bOVar).F.M.c(this.S);
            }
            this.I.j(N01.g, false);
            if (((AbstractC0246Ea1) this.H).j() == null || ((AbstractC0246Ea1) this.H).j().F() != 12) {
                UH0 uh0 = this.I;
                TH0 th0 = N01.i;
                XO xo = (XO) uh0.g(th0);
                if (xo != null) {
                    C2861hP hPVar = xo.j;
                    hPVar.V();
                    PrefChangeRegistrar prefChangeRegistrar = hPVar.G;
                    long j = prefChangeRegistrar.b;
                    if (j != 0) {
                        N.Mn0XciAY(j, prefChangeRegistrar);
                    }
                    prefChangeRegistrar.b = 0;
                    AbstractC0444Hf1.a().b.c(hPVar);
                    HM hm = xo.u;
                    if (hm != null) {
                        hm.c();
                    }
                    xo.u = null;
                    Objects.requireNonNull(xo.f9205a);
                    C3536lL lLVar = xo.s;
                    if (lLVar != null) {
                        lLVar.a();
                    }
                }
                this.I.m(th0, null);
            }
            AbstractC5783ya1 ya1 = this.W;
            if (ya1 != null) {
                TabModel tabModel = this.V;
                if (tabModel != null) {
                    tabModel.w(ya1);
                } else if (this.e0) {
                    this.e0 = false;
                }
            }
            AbstractC0612Ka1 ka1 = this.X;
            if (ka1 != null) {
                ((AbstractC0246Ea1) this.H).f.c(ka1);
            }
            AbstractC2230dk dkVar = this.Z;
            if (dkVar != null) {
                ((C1551Zj) this.Y).Y.c(dkVar);
            }
            l(0);
            AbstractC3535lK0.a("StartSurface.Hidden");
        }
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                Objects.requireNonNull((C5862z01) uq0.next());
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5619xc1
    public void c() {
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C5862z01) uq0.next()).f11716a.l0 = false;
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5619xc1
    public void d() {
        float f;
        Iterator it = this.F.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                C5862z01 z01 = (C5862z01) uq0.next();
                Objects.requireNonNull(z01);
                if (!AbstractC4772sd1.j()) {
                    C01 c01 = z01.f11716a;
                    c01.b0.j();
                    c01.W = false;
                    c01.h();
                } else {
                    C01 c012 = z01.f11716a;
                    Rect m = c012.b0.m(true);
                    J70 j70 = c012.P[0];
                    c012.j();
                    C4316pw m2 = c012.m();
                    ArrayList arrayList = new ArrayList(5);
                    animation.InterpolatorC2176dO dOVar = G30.c;
                    arrayList.add(C5677xw.g(m2, j70, J70.g, ((float) m.width()) / (c012.F * c012.T), 1.0f, 300, dOVar));
                    arrayList.add(C5677xw.g(m2, j70, J70.l, ((float) m.left) / c012.T, 0.0f, 300, dOVar));
                    arrayList.add(C5677xw.g(m2, j70, J70.m, ((float) m.top) / c012.T, 0.0f, 300, dOVar));
                    RH0 rh0 = J70.B;
                    if (AbstractC4772sd1.i()) {
                        f = Math.min(c012.F / c012.k0, j70.A());
                    } else {
                        f = c012.F;
                    }
                    arrayList.add(C5677xw.g(m2, j70, rh0, f, j70.A(), 300, dOVar));
                    C5677xw c = C5677xw.c(m2, 1.0f, 0.0f, 150, new C5522x01(c012));
                    c.K = G30.b;
                    arrayList.add(c);
                    AnimatorSet animatorSet = new AnimatorSet();
                    c012.V = animatorSet;
                    animatorSet.playTogether(arrayList);
                    c012.V.addListener(new B01(c012));
                    c012.j0 = c012.f0;
                    c012.g0 = SystemClock.elapsedRealtime();
                    c012.h0 = SystemClock.elapsedRealtime();
                    c012.i0 = 0;
                    c012.V.start();
                }
            } else {
                return;
            }
        }
    }

    public final int e() {
        int i = this.K;
        if (i == 3) {
            int i2 = this.T;
            if (i2 == 11) {
                int i3 = this.U;
                if (i3 == 0) {
                    return 1;
                }
                return i3;
            } else if (i2 == 9) {
                return (!((AbstractC0246Ea1) this.H).r() || this.c0) ? 1 : 2;
            } else {
                if (i2 == 8) {
                    return 2;
                }
                if (i2 == 10) {
                    return 1;
                }
                return i2;
            }
        } else if (i == 2) {
            return 3;
        } else {
            if (i == 1) {
                return 4;
            }
            if (i == 4) {
                return 5;
            }
            return i == 5 ? 6 : 7;
        }
    }

    public final boolean f(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6;
    }

    public final void g() {
        this.d0.g(new E01(this));
    }

    public boolean h() {
        boolean z = true;
        if (this.T == 2 && !this.P && this.U == 1) {
            l(1);
            return true;
        }
        UH0 uh0 = this.I;
        if (uh0 == null || !uh0.h(N01.e) || this.T != 3) {
            AbstractC5279vc1 vc1 = this.G;
            if (this.T != 1) {
                z = false;
            }
            return vc1.e(z);
        }
        i(false);
        g();
        return true;
    }

    public final void i(boolean z) {
        UH0 uh0 = this.I;
        QH0 qh0 = N01.e;
        if (z != uh0.h(qh0)) {
            if (z && this.I.h(N01.g)) {
                UH0 uh02 = this.I;
                TH0 th0 = N01.i;
                if (uh02.g(th0) == null && !this.a0.a()) {
                    this.I.m(th0, this.M.a(AbstractC1270Uv.e(this.R), p()));
                }
            }
            this.I.j(qh0, z);
            if (this.T == 3) {
                this.I.l(N01.c, z ? 1 : 0);
                FM0.a().edit().putBoolean("explore_surface_visible_last", z).apply();
            }
        }
    }

    public final void j(boolean z) {
        UH0 uh0 = this.I;
        if (uh0 != null) {
            uh0.j(AbstractC5798yf1.f11692a, z);
            ThreadUtils.d(new F01(this));
        }
    }

    public final void k(boolean z) {
        if (!this.b0) {
            UH0 uh0 = this.I;
            QH0 qh0 = AbstractC5798yf1.f11692a;
            QH0 qh02 = AbstractC0703Ll0.f8436a;
            if (z != uh0.h(qh02)) {
                this.I.j(qh02, z);
            }
        }
    }

    public void l(int i) {
        int i2;
        UH0 uh0 = this.I;
        if (uh0 != null && i != this.T) {
            if (i == 10) {
                TH0 th0 = AbstractC5798yf1.w;
                Boolean bool = Boolean.TRUE;
                uh0.m(th0, bool);
                this.I.m(N01.k, bool);
            }
            int i3 = this.T;
            if (i3 != 0) {
                this.U = i3;
            }
            this.T = i;
            m();
            if (this.I.h(N01.g) && (i2 = this.T) != 0 && !f(i2)) {
                int e = e();
                this.U = this.T;
                this.T = e;
                m();
            }
            g();
            int i4 = this.T;
            if (i4 == 1) {
                AbstractC3535lK0.a("StartSurface.SinglePane.Home");
            } else if (i4 == 2) {
                AbstractC3535lK0.a("StartSurface.SinglePane.Tabswitcher");
            } else if (i4 == 3) {
                AbstractC3535lK0.a("StartSurface.TwoPanes");
                String str = this.I.h(N01.e) ? "ExploreSurface" : "HomeSurface";
                AbstractC3535lK0.a("StartSurface.TwoPanes.DefaultOn" + str);
            } else if (i4 == 4) {
                AbstractC3535lK0.a("StartSurface.TasksOnly");
            } else if (i4 == 5) {
                AbstractC3535lK0.a("StartSurface.OmniboxOnly");
            } else if (i4 == 6) {
                AbstractC3535lK0.a("StartSurface.TrendyTerms");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r3.isEmpty() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        if (((defpackage.AbstractC0246Ea1) r7.H).l(false).getCount() > 0) goto L_0x003f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m() {
        /*
        // Method dump skipped, instructions count: 412
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.M01.m():void");
    }

    public final void n(boolean z) {
        if (z) {
            if (this.O == null) {
                this.N = this.f8454J.a();
            }
            UH0 uh0 = this.O;
            if (uh0 != null) {
                QH0 qh0 = AbstractC5798yf1.f11692a;
                boolean z2 = true;
                if (!this.P || this.T != 1) {
                    z2 = false;
                }
                uh0.j(qh0, z2);
                this.O.j(AbstractC5798yf1.b, this.P);
            }
            AbstractC5279vc1 vc1 = this.N;
            if (vc1 != null) {
                vc1.a(false);
            }
        } else {
            AbstractC5279vc1 vc12 = this.N;
            if (vc12 != null) {
                vc12.c(false);
            }
        }
        this.I.j(N01.f, z);
    }

    public final void o(boolean z) {
        UH0 uh0 = this.I;
        QH0 qh0 = AbstractC5798yf1.f;
        if (z != uh0.h(qh0)) {
            this.I.j(qh0, z);
        }
    }

    public void onClick(View view) {
        if (this.O == null && !this.c0) {
            this.N = this.f8454J.a();
        }
        AbstractC3535lK0.a("StartSurface.SinglePane.MoreTabs");
        l(2);
    }

    public boolean p() {
        if (this.g0 == null) {
            this.g0 = Boolean.valueOf(NU0.f8549a.d("Chrome.Feed.ArticlesListVisible", true));
        }
        if (this.K != 3 || !CachedFeatureFlags.isEnabled("InstantStart") || !NU0.f8549a.d("Chrome.Feed.ArticlesListVisible", true) || this.h0) {
            return false;
        }
        return true;
    }

    public void q(boolean z) {
        EM em;
        if (this.I != null) {
            AbstractC3535lK0.a("StartSurface.Shown");
            boolean r = ((AbstractC0246Ea1) this.H).r();
            this.P = r;
            this.I.j(AbstractC5798yf1.b, r);
            if (this.T == 0) {
                this.T = 8;
            }
            l(e());
            if (this.I.h(N01.e)) {
                UH0 uh0 = this.I;
                TH0 th0 = N01.i;
                if (uh0.g(th0) == null && !this.a0.a() && (em = this.M) != null) {
                    this.I.m(th0, em.a(AbstractC1270Uv.e(this.R), p()));
                }
            }
            ((AbstractC0246Ea1) this.H).c(this.X);
            AbstractC2230dk dkVar = this.Z;
            if (dkVar != null) {
                ((C1551Zj) this.Y).Y.b(dkVar);
            }
            this.I.l(N01.j, ((C1551Zj) this.Y).M);
            this.I.j(N01.g, true);
            AbstractC1834bO bOVar = this.Q;
            if (bOVar != null) {
                ((View$OnKeyListenerC0001Aa0) bOVar).p(this.S);
            }
        }
        this.G.a(z);
    }
}
