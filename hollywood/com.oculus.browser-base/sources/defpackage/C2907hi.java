package defpackage;

/* renamed from: hi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2907hi extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public B30 d;
    public String e;
    public C2537fY f;
    public AbstractC2082cr1 g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2907hi() {
        super(40, 0);
    }

    public static C2907hi d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2907hi hiVar = new C2907hi(sDVar.c(b).b);
            hiVar.d = sDVar.o(8, false);
            hiVar.e = sDVar.v(16, false);
            hiVar.f = C2537fY.d(sDVar.s(24, false));
            int i = AbstractC2082cr1.D;
            hiVar.g = (AbstractC2082cr1) sDVar.t(32, false, AbstractC4474qr1.f11168a);
            return hiVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.h(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, false);
        AbstractC2082cr1 cr1 = this.g;
        int i = AbstractC2082cr1.D;
        x.g(cr1, 32, false, AbstractC4474qr1.f11168a);
    }

    public C2907hi(int i) {
        super(40, i);
    }
}
