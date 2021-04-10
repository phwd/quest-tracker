package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: E90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E90 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f7939a;
    public final AB b;
    public final H90 c;
    public boolean d;

    public E90(AbstractC0956Pq0 pq0, UH0 uh0) {
        this.f7939a = uh0;
        this.c = new H90(uh0);
        this.b = new AB(pq0, new D90(this));
        C1078Rq0 rq0 = (C1078Rq0) pq0;
        rq0.l(new C90(this));
        b((Tab) rq0.H);
    }

    public final void a(boolean z) {
        H90 h90 = this.c;
        UH0 uh0 = h90.f8140a;
        SH0 sh0 = F90.f7995a;
        int i = 2;
        uh0.l(sh0, 2);
        h90.b.removeMessages(1);
        if (z) {
            i = 1;
        }
        this.f7939a.l(sh0, i);
    }

    public final void b(Tab tab) {
        if (tab == null) {
            if (AbstractC2793h01.b()) {
                a(false);
            }
        } else if (!tab.d()) {
            a(false);
        } else if (AbstractC5818ym0.a(tab.s(), tab.a())) {
            a(false);
        } else {
            if (!this.d) {
                this.f7939a.l(F90.f7995a, 0);
            }
            c(tab.H());
        }
    }

    public final void c(float f) {
        if (!this.d) {
            float max = Math.max(f, 0.05f);
            this.f7939a.k(F90.b, max);
            if (AbstractC4089od0.a(max, 1.0f)) {
                a(true);
            }
        }
    }
}
