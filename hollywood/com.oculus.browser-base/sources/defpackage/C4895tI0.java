package defpackage;

/* renamed from: tI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4895tI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public int e;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4895tI0(int i) {
        super(16, i);
    }

    public static C4895tI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4895tI0 ti0 = new C4895tI0(sDVar.c(b).b);
            int n = sDVar.n(8);
            ti0.d = n;
            AbstractC5575xI0.a(n);
            ti0.e = sDVar.n(12);
            return ti0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.c(this.e, 12);
    }
}
