package defpackage;

/* renamed from: Py0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0972Py0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public String f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0972Py0(int i) {
        super(32, i);
    }

    public static C0972Py0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0972Py0 py0 = new C0972Py0(sDVar.c(b).b);
            py0.d = sDVar.v(8, false);
            py0.e = sDVar.v(16, false);
            py0.f = sDVar.v(24, false);
            return py0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.k(this.f, 24, false);
    }
}
