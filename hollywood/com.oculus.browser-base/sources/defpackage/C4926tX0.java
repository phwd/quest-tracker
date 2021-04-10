package defpackage;

import android.os.SystemClock;
import java.util.Iterator;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: tX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4926tX0 implements AbstractC5279vc1 {

    /* renamed from: a  reason: collision with root package name */
    public final C1322Vq0 f11347a = new C1322Vq0();
    public final AbstractC0124Ca1 b;
    public final UH0 c;
    public final C2307e91 d;
    public final AbstractC5783ya1 e;
    public final AbstractC0612Ka1 f;
    public AbstractC5449wc1 g;
    public boolean h;
    public boolean i;
    public boolean j;
    public Long k;
    public boolean l;

    public C4926tX0(UH0 uh0, AbstractC0124Ca1 ca1, C2307e91 e91) {
        this.b = ca1;
        this.c = uh0;
        this.d = e91;
        uh0.m(AbstractC5096uX0.b, e91.a(false));
        uh0.m(AbstractC5096uX0.f11416a, new View$OnClickListenerC4074oX0(this));
        this.e = new C4586rX0(this);
        this.f = new C4756sX0(this);
    }

    @Override // defpackage.AbstractC5279vc1
    public void a(boolean z) {
        this.i = true;
        ((AbstractC0246Ea1) this.b).c(this.f);
        if (!CachedFeatureFlags.isEnabled("InstantStart") || ((AbstractC0246Ea1) this.b).h) {
            TabModel l2 = ((AbstractC0246Ea1) this.b).l(false);
            l2.n(this.e);
            int index = l2.index();
            if (index != -1) {
                i(l2.getTabAt(index));
                if (this.k == null) {
                    this.k = Long.valueOf(SystemClock.elapsedRealtime());
                }
            }
        } else {
            this.j = true;
            P21 f0 = P21.f0();
            try {
                C4384qI0.n();
                C4384qI0 qi0 = C4384qI0.e;
                f0.close();
                if (qi0 != null) {
                    this.c.m(AbstractC5096uX0.d, qi0.i());
                    if (this.k == null) {
                        this.k = Long.valueOf(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        this.c.j(AbstractC5096uX0.c, true);
        Iterator it = this.f11347a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC5619xc1) uq0.next()).c();
        }
        Iterator it2 = this.f11347a.iterator();
        while (true) {
            C1261Uq0 uq02 = (C1261Uq0) it2;
            if (uq02.hasNext()) {
                ((AbstractC5619xc1) uq02.next()).a();
            } else {
                return;
            }
        }
        throw th;
    }

    @Override // defpackage.AbstractC5279vc1
    public void b(long j2) {
        Long l2 = this.k;
        if (l2 != null) {
            AbstractC2793h01.e("SingleTabTitleAvailableTime", l2.longValue() - j2, AbstractC4772sd1.k(false));
        }
    }

    @Override // defpackage.AbstractC5279vc1
    public void c(boolean z) {
        this.h = false;
        ((AbstractC0246Ea1) this.b).l(false).w(this.e);
        AbstractC0124Ca1 ca1 = this.b;
        ((AbstractC0246Ea1) ca1).f.c(this.f);
        this.c.j(AbstractC5096uX0.c, false);
        this.c.m(AbstractC5096uX0.b, this.d.a(false));
        this.c.m(AbstractC5096uX0.d, "");
        Iterator it = this.f11347a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                break;
            }
            ((AbstractC5619xc1) uq0.next()).b();
        }
        Iterator it2 = this.f11347a.iterator();
        while (true) {
            C1261Uq0 uq02 = (C1261Uq0) it2;
            if (uq02.hasNext()) {
                ((AbstractC5619xc1) uq02.next()).d();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC5279vc1
    public void d(AbstractC5619xc1 xc1) {
        this.f11347a.b(xc1);
    }

    @Override // defpackage.AbstractC5279vc1
    public boolean e(boolean z) {
        if (!f() || ((AbstractC0246Ea1) this.b).r() || ((AbstractC0246Ea1) this.b).k() == -1) {
            return false;
        }
        h();
        return true;
    }

    @Override // defpackage.AbstractC5279vc1
    public boolean f() {
        return this.c.h(AbstractC5096uX0.c);
    }

    @Override // defpackage.AbstractC5279vc1
    public void g(long j2) {
    }

    public final void h() {
        if (this.i) {
            AbstractC3535lK0.a("MobileTabReturnedToCurrentTab.SingleTabCard");
        }
        this.g.a(SystemClock.uptimeMillis(), ((AbstractC0246Ea1) this.b).k());
    }

    public final void i(Tab tab) {
        this.c.m(AbstractC5096uX0.d, tab.getTitle());
        this.d.b(tab.s(), false, new C4416qX0(this));
    }
}
