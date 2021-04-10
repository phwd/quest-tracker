package defpackage;

/* renamed from: vI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5235vI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public Cq1 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5235vI0(int i) {
        super(32, i);
    }

    public static C5235vI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5235vI0 vi0 = new C5235vI0(sDVar.c(b).b);
            vi0.d = sDVar.v(8, false);
            vi0.e = sDVar.v(16, false);
            vi0.f = Cq1.d(sDVar.s(24, true));
            return vi0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, true);
    }
}
