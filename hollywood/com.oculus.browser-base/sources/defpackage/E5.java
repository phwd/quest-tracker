package defpackage;

/* renamed from: E5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class E5 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public B30 d;
    public AbstractC3831n5 e;
    public C5875z5 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public E5() {
        super(32, 0);
    }

    public static E5 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            E5 e5 = new E5(sDVar.c(b).b);
            e5.d = sDVar.o(8, false);
            int i = AbstractC3831n5.c;
            e5.e = (AbstractC3831n5) sDVar.t(12, false, AbstractC5705y5.f11661a);
            e5.f = C5875z5.d(sDVar.s(24, false));
            return e5;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.h(this.d, 8, false);
        AbstractC3831n5 n5Var = this.e;
        int i = AbstractC3831n5.c;
        x.g(n5Var, 12, false, AbstractC5705y5.f11661a);
        x.i(this.f, 24, false);
    }

    public E5(int i) {
        super(32, i);
    }
}
