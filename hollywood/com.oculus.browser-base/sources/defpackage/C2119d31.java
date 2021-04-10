package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: d31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2119d31 extends AbstractC0855Oa1 {
    public final /* synthetic */ C2631g31 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2119d31(C2631g31 g31, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = g31;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        C2631g31 g31 = this.c;
        X21 x21 = z ? g31.L : g31.K;
        SystemClock.uptimeMillis();
        x21.r(i);
        this.c.h();
    }

    @Override // defpackage.AbstractC5783ya1
    public void B(Tab tab, int i, int i2) {
        X21 f = this.c.f(tab.a());
        SystemClock.uptimeMillis();
        f.k(tab.getId(), i2, i, false);
        f.z();
        ((D70) f.b).s(null);
    }

    @Override // defpackage.AbstractC5783ya1
    public void C(Tab tab, int i, int i2) {
        if (tab.getId() != i2) {
            this.c.f(tab.a()).u(SystemClock.uptimeMillis(), tab.getId(), i2);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        int i;
        X21 f = this.c.f(tab.a());
        long uptimeMillis = SystemClock.uptimeMillis();
        int id = tab.getId();
        Tab c2 = AbstractC1160Ta1.c(f.d);
        if (c2 == null) {
            i = -1;
        } else {
            i = c2.getId();
        }
        f.s(uptimeMillis, id, -1, i == id);
        this.c.h();
    }

    @Override // defpackage.AbstractC5783ya1
    public void H(Tab tab) {
        X21 f = this.c.f(tab.a());
        SystemClock.uptimeMillis();
        f.r(tab.getId());
        this.c.h();
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        X21 f = this.c.f(tab.a());
        SystemClock.uptimeMillis();
        f.r(tab.getId());
        this.c.h();
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        this.c.f(tab.a()).s(SystemClock.uptimeMillis(), tab.getId(), ((AbstractC0246Ea1) this.c.G).k(), i != 5 || (((AbstractC0246Ea1) this.c.G).r() && tab.a()));
    }
}
