package defpackage;

import java.util.Iterator;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: hj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2910hj extends I70 implements AbstractC2230dk, AbstractC3322k60 {
    public final UH0 F;
    public final UT G;
    public final AbstractC1888bk H;
    public final C1128Sl I;

    /* renamed from: J  reason: collision with root package name */
    public int f10095J;
    public final WindowAndroid K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public AbstractC2642g70 P;

    public C2910hj(WindowAndroid windowAndroid, UH0 uh0, AbstractC1888bk bkVar, UT ut, int i, AbstractC0956Pq0 pq0) {
        this.F = uh0;
        this.G = ut;
        this.H = bkVar;
        ((C1551Zj) bkVar).Y.b(this);
        this.f10095J = i;
        C1128Sl sl = new C1128Sl();
        this.I = sl;
        ((C1078Rq0) pq0).l(sl.b(new C2739gj(this)));
        this.K = windowAndroid;
        windowAndroid.u0().a(this);
    }

    @Override // defpackage.AbstractC3322k60
    public void b(boolean z) {
        this.O = z;
        i();
        g();
    }

    @Override // defpackage.I70
    public void e(int i, boolean z) {
        this.N = i == 2;
        g();
    }

    public boolean f() {
        if (this.L && !this.O) {
            UT ut = this.G;
            if (!(ut != null && ut.a())) {
                return true;
            }
        }
        return false;
    }

    public final void g() {
        this.F.j(AbstractC3080ij.c, f() && !this.M && !this.N && ((C1551Zj) this.H).b() == 0);
    }

    @Override // defpackage.AbstractC2230dk
    public void h(int i, int i2) {
    }

    public final void i() {
        boolean f = f();
        this.F.j(AbstractC3080ij.d, f);
        AbstractC1888bk bkVar = this.H;
        int i = f ? this.f10095J : 0;
        int i2 = ((C1551Zj) bkVar).P;
        C1551Zj zj = (C1551Zj) bkVar;
        if (zj.O != i || zj.P != i2) {
            zj.O = i;
            zj.P = i2;
            Iterator it = zj.Y.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC2230dk) uq0.next()).h(zj.O, zj.P);
                } else {
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        this.F.l(AbstractC3080ij.b, i3);
        g();
    }

    @Override // defpackage.AbstractC2230dk
    public void k(int i, int i2) {
    }
}
