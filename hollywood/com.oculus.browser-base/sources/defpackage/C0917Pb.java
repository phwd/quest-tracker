package defpackage;

/* renamed from: Pb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0917Pb extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public C0431Hb0 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0917Pb() {
        super(24, 0);
    }

    public static C0917Pb d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0917Pb pb = new C0917Pb(sDVar.c(b).b);
            int n = sDVar.n(8);
            pb.d = n;
            AbstractC0125Cb.a(n);
            pb.e = C0431Hb0.d(sDVar.s(16, true));
            return pb;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.i(this.e, 16, true);
    }

    public C0917Pb(int i) {
        super(24, i);
    }
}
