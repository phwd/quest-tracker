package defpackage;

/* renamed from: Qz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1035Qz0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public C2617fz0 e;
    public boolean f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1035Qz0(int i) {
        super(32, i);
    }

    public static C1035Qz0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1035Qz0 qz0 = new C1035Qz0(sDVar.c(b).b);
            qz0.d = sDVar.v(8, false);
            qz0.e = C2617fz0.d(sDVar.s(16, false));
            qz0.f = sDVar.d(24, 0);
            return qz0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.i(this.e, 16, false);
        x.m(this.f, 24, 0);
    }
}
