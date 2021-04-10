package defpackage;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: nd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3919nd1 implements AbstractC5279vc1, P91, AbstractC5209v91, JF0 {
    public boolean A;
    public int B;

    /* renamed from: a  reason: collision with root package name */
    public final Handler f10505a;
    public final Runnable b;
    public final Runnable c;
    public final AbstractC3748md1 d;
    public final UH0 e;
    public final AbstractC0124Ca1 f;
    public final AbstractC5783ya1 g;
    public final AbstractC0612Ka1 h;
    public final C1322Vq0 i = new C1322Vq0();
    public final AbstractC2400ek j;
    public final AbstractC2230dk k;
    public final ViewGroup l;
    public final TabContentManager m;
    public final C3261jm0 n;
    public final AbstractC3090im0 o;
    public I61 p;
    public AbstractC0249Eb1 q;
    public AbstractC5449wc1 r;
    public KF0 s;
    public boolean t;
    public int u;
    public int v;
    public int w;
    public boolean x;
    public boolean y;
    public C3235jd1 z;

    public C3919nd1(Context context, AbstractC3748md1 md1, UH0 uh0, AbstractC0124Ca1 ca1, AbstractC2400ek ekVar, ViewGroup viewGroup, TabContentManager tabContentManager, AbstractC3406kd1 kd1, AbstractC3577ld1 ld1, C3261jm0 jm0, int i2) {
        boolean z2;
        this.d = md1;
        this.e = uh0;
        this.f = ca1;
        this.j = ekVar;
        this.n = jm0;
        this.B = i2;
        C2381ed1 ed1 = new C2381ed1(this);
        this.h = ed1;
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        ea1.c(ed1);
        C2552fd1 fd1 = new C2552fd1(this, kd1, ld1);
        this.g = fd1;
        C2723gd1 gd1 = new C2723gd1(this);
        this.k = gd1;
        C1551Zj zj = (C1551Zj) ekVar;
        zj.Y.b(gd1);
        if (ea1.f7969a.isEmpty()) {
            ea1.c(new C2894hd1(this));
        } else {
            ea1.c.a(fd1);
        }
        uh0.m(O81.c, this);
        AbstractC3568la1 d2 = ea1.c.d();
        QH0 qh0 = O81.b;
        if (d2 == null) {
            z2 = false;
        } else {
            z2 = d2.a();
        }
        uh0.j(qh0, z2);
        uh0.j(O81.e, true);
        if (this.B != 2) {
            q();
            uh0.l(O81.g, zj.O);
        }
        if (this.B == 0) {
            uh0.l(O81.i, (int) context.getResources().getDimension(R.dimen.f25370_resource_name_obfuscated_RES_2131166156));
        }
        this.l = viewGroup;
        this.b = new RunnableC1471Yc1(md1);
        this.c = new RunnableC1532Zc1(this);
        this.f10505a = new Handler();
        this.m = tabContentManager;
        this.y = k();
        C1689ad1 ad1 = new C1689ad1(kd1);
        this.o = ad1;
        jm0.b.b(ad1);
    }

    public static boolean k() {
        if (CachedFeatureFlags.isEnabled("InstantStart")) {
            return false;
        }
        return TextUtils.equals(AbstractC2793h01.f10042a.c(), "twopanes");
    }

    @Override // defpackage.AbstractC5279vc1
    public void a(boolean z2) {
        this.f10505a.removeCallbacks(this.b);
        this.f10505a.removeCallbacks(this.c);
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) this.f;
        if (ea1.h) {
            ((C1349Wc1) this.d).t(ea1.c.d(), AbstractC4772sd1.j(), this.y);
            o();
        } else if (CachedFeatureFlags.isEnabled("InstantStart")) {
            P21 f0 = P21.f0();
            try {
                C4384qI0.n();
                List list = C4384qI0.d;
                f0.close();
                ((C1349Wc1) this.d).u(list, AbstractC4772sd1.j(), this.y);
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (!z2) {
            this.e.j(O81.e, false);
        }
        AbstractC3535lK0.a("MobileToolbarShowStackView");
        this.e.j(O81.f8603a, true);
        AbstractC0246Ea1 ea12 = (AbstractC0246Ea1) this.f;
        this.u = ea12.e;
        this.v = ea12.k();
        this.e.j(O81.e, true);
        return;
        throw th;
    }

    @Override // defpackage.AbstractC5279vc1
    public void b(long j2) {
    }

    @Override // defpackage.AbstractC5279vc1
    public void c(boolean z2) {
        if (!z2) {
            this.e.j(O81.e, false);
        }
        this.e.j(O81.f8603a, false);
        this.e.j(O81.e, true);
        I61 i61 = this.p;
        if (i61 != null) {
            i61.c.h(false);
        }
    }

    @Override // defpackage.AbstractC5279vc1
    public void d(AbstractC5619xc1 xc1) {
        this.i.b(xc1);
    }

    @Override // defpackage.AbstractC5279vc1
    public boolean e(boolean z2) {
        boolean z3;
        if (!this.e.h(O81.f8603a)) {
            return false;
        }
        if (!z2 || this.B != 2) {
            AbstractC0249Eb1 eb1 = this.q;
            if (eb1 != null) {
                C0676Lb1 lb1 = (C0676Lb1) eb1;
                if (!lb1.d.h(AbstractC0736Mb1.f8486a)) {
                    z3 = false;
                } else {
                    lb1.i.a();
                    z3 = true;
                }
                if (z3) {
                    return true;
                }
            }
            I61 i61 = this.p;
            if (i61 != null && i61.b()) {
                return true;
            }
            if (((AbstractC0246Ea1) this.f).j() == null) {
                return false;
            }
            m(((AbstractC0246Ea1) this.f).k(), false);
            return true;
        }
        I61 i612 = this.p;
        return i612 != null && i612.b();
    }

    @Override // defpackage.AbstractC5279vc1
    public boolean f() {
        return this.e.h(O81.f8603a);
    }

    @Override // defpackage.AbstractC5279vc1
    public void g(long j2) {
        this.z = new C3235jd1(j2, null);
    }

    public final int h() {
        try {
            return Integer.valueOf(N.MMltG$kc("TabGridLayoutAndroid", "cleanup-delay")).intValue();
        } catch (NumberFormatException unused) {
            return 30000;
        }
    }

    public final List i(int i2) {
        return ((AbstractC0246Ea1) this.f).c.d().N(i2);
    }

    public final int j() {
        try {
            return Integer.valueOf(N.MMltG$kc("TabGridLayoutAndroid", "soft-cleanup-delay")).intValue();
        } catch (NumberFormatException unused) {
            return 3000;
        }
    }

    public final void l(int i2) {
        Object obj = ThreadUtils.f10596a;
        C3235jd1 jd1 = this.z;
        Objects.requireNonNull(jd1);
        PostTask.b(Zo1.b, new RunnableC3065id1(SystemClock.elapsedRealtime() - jd1.f10221a, i2), 0);
        this.z = null;
    }

    public void m(int i2, boolean z2) {
        int i3;
        if (z2 && ((i3 = this.B) == 2 || i3 == 0)) {
            Tab d2 = AbstractC1160Ta1.d(((AbstractC0246Ea1) this.f).i(), i2);
            C1771b11 b11 = (C1771b11) d2.M().c(C1771b11.class);
            if (b11 == null) {
                b11 = new C1771b11();
            }
            b11.F = true;
            d2.M().e(C1771b11.class, b11);
        }
        this.x = true;
        AbstractC5449wc1 wc1 = this.r;
        if (wc1 != null) {
            wc1.a(SystemClock.uptimeMillis(), i2);
        }
    }

    public D91 n(Tab tab) {
        boolean z2 = true;
        if (!AbstractC4772sd1.g() || ((AbstractC0246Ea1) this.f).r() != tab.a() || i(tab.getId()).size() == 1) {
            z2 = false;
        }
        if (!z2) {
            return null;
        }
        return new C2211dd1(this);
    }

    public final void o() {
        TabModel i2 = ((AbstractC0246Ea1) this.f).i();
        if (i2 != null) {
            AbstractC3364kK0.d("Tabs.TabCountInSwitcher", i2.getCount());
            AbstractC3568la1 d2 = ((AbstractC0246Ea1) this.f).c.d();
            if (d2 != null) {
                AbstractC3364kK0.d("Tabs.IndependentTabCountInSwitcher", d2.getCount());
            }
        }
    }

    public final void p() {
        int i2 = 0;
        int max = Math.max(((AbstractC0246Ea1) this.f).c.d().index() - 2, 0);
        if (!this.y) {
            i2 = max;
        }
        this.e.m(O81.d, Integer.valueOf(i2));
    }

    public final void q() {
        if (AbstractC2793h01.b()) {
            this.e.l(O81.f, 0);
            this.e.l(O81.h, 0);
            return;
        }
        int i2 = ((C1551Zj) this.j).T;
        this.e.l(O81.f, i2);
        this.e.l(O81.h, i2);
    }
}
