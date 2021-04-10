package defpackage;

/* renamed from: qt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4479qt0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public short f;
    public C5318vp1 g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4479qt0(int i) {
        super(40, i);
    }

    public static C4479qt0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4479qt0 qt0 = new C4479qt0(sDVar.c(b).b);
            qt0.d = sDVar.v(8, false);
            qt0.e = sDVar.v(16, false);
            qt0.f = sDVar.u(24);
            qt0.g = C5318vp1.d(sDVar.s(32, true));
            return qt0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.l(this.f, 24);
        x.i(this.g, 32, true);
    }
}
