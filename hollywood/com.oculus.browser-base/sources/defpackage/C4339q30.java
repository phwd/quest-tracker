package defpackage;

/* renamed from: q30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4339q30 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public AbstractC1552Zj0 e = H30.F;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4339q30() {
        super(24, 0);
    }

    public static C4339q30 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C4339q30 q30 = new C4339q30(sDVar.c(b).b);
            q30.d = sDVar.v(8, false);
            q30.e = sDVar.r(16, false);
            return q30;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.f(this.e, 16, false);
    }

    public C4339q30(int i) {
        super(24, i);
    }
}
