package defpackage;

import java.util.Objects;

/* renamed from: fr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2594fr1 extends AbstractC3142j30 {
    public C2594fr1(SA sa, AbstractC2082cr1 cr1) {
        super(sa, cr1);
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            C0942Pj0 pj0 = a2.d;
            if (!pj0.c(pj0.a(4) ? 4 : 0)) {
                return false;
            }
            int i = pj0.d;
            if (i == -2) {
                return AbstractC3484l30.b(AbstractC4474qr1.f11168a, a2);
            }
            if (i == 0) {
                C4984tr1 tr1 = C3277jr1.d(a2.b()).d;
                C2423er1 er1 = (C2423er1) ((AbstractC2082cr1) this.G);
                Objects.requireNonNull(er1);
                C3277jr1 jr1 = new C3277jr1();
                jr1.d = tr1;
                C2288e30 e30 = er1.F;
                e30.G.b(jr1.c(e30.F, new C0942Pj0(0)));
                return true;
            } else if (i == 1) {
                C3106ir1 d = C3106ir1.d(a2.b());
                C4814sr1 sr1 = d.d;
                C4984tr1 tr12 = d.e;
                C2423er1 er12 = (C2423er1) ((AbstractC2082cr1) this.G);
                Objects.requireNonNull(er12);
                C3106ir1 ir1 = new C3106ir1();
                ir1.d = sr1;
                ir1.e = tr12;
                C2288e30 e302 = er12.F;
                e302.G.b(ir1.c(e302.F, new C0942Pj0(1)));
                return true;
            } else if (i == 3) {
                C2740gj0 b = a2.b();
                CC[] ccArr = C2936hr1.b;
                C4709sD sDVar = new C4709sD(b);
                sDVar.b();
                try {
                    C2936hr1 hr1 = new C2936hr1(sDVar.c(C2936hr1.b).b);
                    hr1.d = C4442qh.b(sDVar, 8);
                    sDVar.a();
                    C4442qh qhVar = hr1.d;
                    C2423er1 er13 = (C2423er1) ((AbstractC2082cr1) this.G);
                    Objects.requireNonNull(er13);
                    C2936hr1 hr12 = new C2936hr1();
                    hr12.d = qhVar;
                    C2288e30 e303 = er13.F;
                    e303.G.b(hr12.c(e303.F, new C0942Pj0(3)));
                    return true;
                } catch (Throwable th) {
                    sDVar.a();
                    throw th;
                }
            } else if (i == 4) {
                C2740gj0 b2 = a2.b();
                CC[] ccArr2 = C3619lr1.b;
                C4709sD sDVar2 = new C4709sD(b2);
                sDVar2.b();
                try {
                    C3619lr1 lr1 = new C3619lr1(sDVar2.c(C3619lr1.b).b);
                    lr1.d = sDVar2.n(8);
                    sDVar2.a();
                    int i2 = lr1.d;
                    C2423er1 er14 = (C2423er1) ((AbstractC2082cr1) this.G);
                    Objects.requireNonNull(er14);
                    C3619lr1 lr12 = new C3619lr1();
                    lr12.d = i2;
                    C2288e30 e304 = er14.F;
                    e304.G.b(lr12.c(e304.F, new C0942Pj0(4)));
                    return true;
                } catch (Throwable th2) {
                    sDVar2.a();
                    throw th2;
                }
            } else if (i == 5) {
                DC dc = C3448kr1.d(a2.b()).d;
                C2423er1 er15 = (C2423er1) ((AbstractC2082cr1) this.G);
                Objects.requireNonNull(er15);
                C3448kr1 kr1 = new C3448kr1();
                kr1.d = dc;
                C2288e30 e305 = er15.F;
                e305.G.b(kr1.c(e305.F, new C0942Pj0(5)));
                return true;
            } else if (i != 6) {
                return false;
            } else {
                C4644rr1 rr1 = C2765gr1.d(a2.b()).d;
                C2423er1 er16 = (C2423er1) ((AbstractC2082cr1) this.G);
                Objects.requireNonNull(er16);
                C2765gr1 gr1 = new C2765gr1();
                gr1.d = rr1;
                C2288e30 e306 = er16.F;
                e306.G.b(gr1.c(e306.F, new C0942Pj0(6)));
                return true;
            }
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override // defpackage.AbstractC3426kk0
    public boolean g(C2740gj0 gj0, AbstractC3255jk0 jk0) {
        try {
            IS0 a2 = gj0.a();
            C0942Pj0 pj0 = a2.d;
            if (!pj0.c(pj0.a(4) ? 5 : 1)) {
                return false;
            }
            int i = pj0.d;
            if (i == -1) {
                return AbstractC3484l30.a(this.F, AbstractC4474qr1.f11168a, a2, jk0);
            }
            if (i != 2) {
                return false;
            }
            C3790mr1 d = C3790mr1.d(a2.b());
            long j = d.d;
            long j2 = d.e;
            C4303pr1 pr1 = new C4303pr1(this.F, jk0, pj0.f);
            C2423er1 er1 = (C2423er1) ((AbstractC2082cr1) this.G);
            Objects.requireNonNull(er1);
            C3790mr1 mr1 = new C3790mr1();
            mr1.d = j;
            mr1.e = j2;
            C2288e30 e30 = er1.F;
            e30.G.g(mr1.c(e30.F, new C0942Pj0(2, 1, 0)), new C4132or1(pr1));
            return true;
        } catch (C4200pE e) {
            System.err.println(e.toString());
            return false;
        }
    }
}
