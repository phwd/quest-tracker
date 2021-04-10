package defpackage;

/* renamed from: Hb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0430Hb extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public C5941zV e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0430Hb() {
        super(24, 0);
    }

    public static C0430Hb d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0430Hb hb = new C0430Hb(sDVar.c(b).b);
            int n = sDVar.n(8);
            hb.d = n;
            AbstractC0125Cb.a(n);
            hb.e = C5941zV.d(sDVar.s(16, true));
            return hb;
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

    public C0430Hb(int i) {
        super(24, i);
    }
}
