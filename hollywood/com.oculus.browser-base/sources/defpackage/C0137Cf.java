package defpackage;

/* renamed from: Cf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0137Cf extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public B30 d;
    public C1234Uf e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0137Cf() {
        super(24, 0);
    }

    public static C0137Cf d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0137Cf cf = new C0137Cf(sDVar.c(b).b);
            cf.d = sDVar.o(8, false);
            cf.e = C1234Uf.d(sDVar.s(16, false));
            return cf;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.h(this.d, 8, false);
        x.i(this.e, 16, false);
    }

    public C0137Cf(int i) {
        super(24, i);
    }
}
