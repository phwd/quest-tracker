package defpackage;

/* renamed from: bk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1889bk0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public AbstractC1552Zj0 d = H30.F;
    public C5318vp1 e;
    public long f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1889bk0(int i) {
        super(32, i);
    }

    public static C1889bk0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1889bk0 bk0 = new C1889bk0(sDVar.c(b).b);
            bk0.d = sDVar.r(8, false);
            bk0.e = C5318vp1.d(sDVar.s(16, false));
            bk0.f = sDVar.q(24);
            return bk0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.f(this.d, 8, false);
        x.i(this.e, 16, false);
        x.d(this.f, 24);
    }
}
