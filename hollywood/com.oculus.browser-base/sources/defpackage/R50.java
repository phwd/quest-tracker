package defpackage;

import java.util.HashSet;
import java.util.Set;

/* renamed from: R50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R50 implements AbstractC5716y81, AbstractC1641aI0, AbstractC1953c50 {
    public final UH0 F;
    public M50 G;
    public Set H = new HashSet();

    public R50(UH0 uh0) {
        this.F = uh0;
        uh0.f9530a.b(this);
        uh0.m(S50.c, this);
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
        UH0 uh0 = this.F;
        TH0 th0 = S50.b;
        if (uh0.g(th0) == null) {
            this.F.m(th0, h(d81.d));
            return;
        }
        M50 m50 = this.G;
        if (m50 != null) {
            ((C4686s50) m50).c();
        }
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        KH0 kh0 = (KH0) obj;
        TH0 th0 = S50.b;
        if (kh0 == th0) {
            M50 m50 = this.G;
            if (m50 != null) {
                Integer num = (Integer) this.F.g(th0);
                C4686s50 s50 = (C4686s50) m50;
                s50.F.j(I50.f, num != null);
                if (num == null) {
                    ((View$OnLayoutChangeListenerC4598rc0) s50.G).e0();
                    return;
                }
                AbstractC2124d50 d50 = s50.G;
                int intValue = num.intValue();
                View$OnLayoutChangeListenerC4598rc0 rc0 = (View$OnLayoutChangeListenerC4598rc0) d50;
                if (rc0.c0()) {
                    rc0.M.f10320a.F.l(AbstractC4158p0.b, intValue);
                    BJ bj = rc0.P;
                    if (bj != null && ((EJ) bj.F).L.c()) {
                        rc0.P.a();
                    }
                    if (rc0.b0(5)) {
                        rc0.F.l(AbstractC4938tc0.c, 3);
                    } else if (rc0.b0(13)) {
                        rc0.F.l(AbstractC4938tc0.c, 11);
                    }
                }
            }
        } else if (kh0 == S50.f8876a) {
            d();
        }
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    public void d() {
        this.F.m(S50.b, null);
    }

    public C3148j50 e() {
        UH0 uh0 = this.F;
        TH0 th0 = S50.b;
        if (uh0.g(th0) == null) {
            return null;
        }
        return (C3148j50) ((C1794b90) this.F.g(S50.f8876a)).get(((Integer) this.F.g(th0)).intValue());
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        this.F.m(S50.b, h(d81.d));
    }

    public boolean g() {
        return ((C1794b90) this.F.g(S50.f8876a)).size() > 0;
    }

    public Integer h(int i) {
        if (i != -1 && i < ((C1794b90) this.F.g(S50.f8876a)).size()) {
            return Integer.valueOf(i);
        }
        return null;
    }
}
