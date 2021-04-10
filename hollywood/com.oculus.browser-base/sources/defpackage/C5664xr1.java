package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: xr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5664xr1 implements AbstractC3087il0 {
    public final /* synthetic */ C5834yr1 F;

    public C5664xr1(C5834yr1 yr1) {
        this.F = yr1;
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        Jr1 a2 = Jr1.a();
        boolean z = i == 0;
        if (z) {
            boolean z2 = !this.F.d;
            Objects.requireNonNull(a2);
            Object obj = ThreadUtils.f10596a;
            N.Mf2ABpoH(Wr1.a(a2.b).f10883a, "usage_stats_reporting.enabled", z2);
            if (a2.j != z2) {
                a2.j = z2;
                Objects.requireNonNull(a2.i);
                C5232vH0.c(null);
                if (!z2) {
                    a2.e.c.f(new C3488l41()).g(new Br1(a2));
                    a2.f.f10443a.f(new C3424kj1()).g(new Cr1(a2));
                }
                AbstractC6004zr1.a(!z2);
            }
        }
        this.F.e.onResult(Boolean.valueOf(z));
        this.F.b.a();
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        this.F.e.onResult(Boolean.FALSE);
        this.F.b.a();
    }
}
