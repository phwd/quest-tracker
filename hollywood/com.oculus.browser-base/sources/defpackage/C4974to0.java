package defpackage;

/* renamed from: to0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4974to0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int[] d;
    public String e;
    public C3264jn0 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4974to0() {
        super(32, 0);
    }

    public static C4974to0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C4974to0 to0 = new C4974to0(sDVar.c(b).b);
            to0.d = sDVar.p(8, 0, -1);
            to0.e = sDVar.v(16, true);
            to0.f = C3264jn0.d(sDVar.s(24, false));
            return to0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.p(this.d, 8, 0, -1);
        x.k(this.e, 16, true);
        x.i(this.f, 24, false);
    }

    public C4974to0(int i) {
        super(32, i);
    }
}
