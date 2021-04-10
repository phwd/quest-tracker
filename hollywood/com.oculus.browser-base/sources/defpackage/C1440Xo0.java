package defpackage;

/* renamed from: Xo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1440Xo0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C3264jn0 d;
    public C3777mn0 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1440Xo0() {
        super(24, 0);
    }

    /* JADX INFO: finally extract failed */
    public static C1440Xo0 d(C2740gj0 gj0) {
        C3777mn0 mn0;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1440Xo0 xo0 = new C1440Xo0(sDVar.c(b).b);
            xo0.d = C3264jn0.d(sDVar.s(8, false));
            C4709sD s = sDVar.s(16, true);
            CC[] ccArr = C3777mn0.b;
            if (s == null) {
                mn0 = null;
            } else {
                s.b();
                try {
                    C3777mn0 mn02 = new C3777mn0(s.c(C3777mn0.b).b);
                    mn02.d = s.d(8, 0);
                    s.a();
                    mn0 = mn02;
                } catch (Throwable th) {
                    s.a();
                    throw th;
                }
            }
            xo0.e = mn0;
            return xo0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.i(this.e, 16, true);
    }

    public C1440Xo0(int i) {
        super(24, i);
    }
}
