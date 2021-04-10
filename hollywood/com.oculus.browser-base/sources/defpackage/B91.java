package defpackage;

import java.util.Objects;

/* renamed from: B91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class B91 implements Runnable {
    public final C91 F;
    public final C2361eV0 G;

    public B91(C91 c91, C2361eV0 ev0) {
        this.F = c91;
        this.G = ev0;
    }

    public void run() {
        C91 c91 = this.F;
        C2361eV0 ev0 = this.G;
        Objects.requireNonNull(c91);
        if (!CF0.d() && c91.b != null && c91.c != null && ev0 != null && ev0.s() != null && c91.b.A(3) == -1) {
            AbstractC3577ld1 ld1 = c91.c;
            HF0 hf0 = new HF0(c91.f7791a.getId(), ev0.s());
            C1349Wc1 wc1 = (C1349Wc1) ld1;
            if (wc1.Z != null && !CF0.d()) {
                KF0 kf0 = wc1.Z;
                Objects.requireNonNull(kf0);
                PU0 pu0 = CF0.f7797a;
                pu0.n("Chrome.PriceTracking.PriceWelcomeShowCount", pu0.f("Chrome.PriceTracking.PriceWelcomeShowCount", 0) + 1);
                if (pu0.f("Chrome.PriceTracking.PriceWelcomeShowCount", 0) > KF0.c * 10) {
                    kf0.e();
                    CF0.a();
                } else if (!hf0.equals(kf0.f)) {
                    kf0.f = hf0;
                    kf0.c();
                    kf0.b(new IF0(kf0, kf0.f.b, new FF0(kf0), new GF0(kf0)));
                }
                wc1.q(3);
                wc1.f9158J.e.m(O81.d, Integer.valueOf(((AbstractC0246Ea1) wc1.M).c.d().index()));
            }
        }
    }
}
