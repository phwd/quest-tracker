package defpackage;

/* renamed from: e5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2293e5 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1827bK0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2293e5() {
        super(16, 0);
    }

    /* JADX INFO: finally extract failed */
    public static C2293e5 d(C2740gj0 gj0) {
        C1827bK0 bk0;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2293e5 e5Var = new C2293e5(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, true);
            CC[] ccArr = C1827bK0.b;
            if (s == null) {
                bk0 = null;
            } else {
                s.b();
                try {
                    C1827bK0 bk02 = new C1827bK0(s.c(C1827bK0.b).b);
                    bk02.d = s.w(8, false);
                    bk02.e = s.d(12, 0);
                    s.a();
                    bk0 = bk02;
                } catch (Throwable th) {
                    s.a();
                    throw th;
                }
            }
            e5Var.d = bk0;
            return e5Var;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, true);
    }

    public C2293e5(int i) {
        super(16, i);
    }
}
