package defpackage;

/* renamed from: ls1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3622ls1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public short e;
    public short f;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3622ls1() {
        super(16, 0);
    }

    public static C3622ls1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C3622ls1 ls1 = new C3622ls1(sDVar.c(b).b);
            ls1.d = sDVar.n(8);
            ls1.e = sDVar.u(12);
            ls1.f = sDVar.u(14);
            return ls1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.l(this.e, 12);
        x.l(this.f, 14);
    }

    public C3622ls1(int i) {
        super(16, i);
    }
}
