package defpackage;

/* renamed from: Ih  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0509Ih extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0570Jh d;
    public C4442qh e;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0509Ih(int i) {
        super(32, i);
    }

    public static C0509Ih d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0509Ih ih = new C0509Ih(sDVar.c(b).b);
            ih.d = C0570Jh.d(sDVar.s(8, false));
            ih.e = C4442qh.b(sDVar, 16);
            return ih;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.j(this.e, 16, false);
    }
}
