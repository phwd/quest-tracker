package defpackage;

/* renamed from: BB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BB0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public C0972Py0 e;
    public T3 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public BB0(int i) {
        super(32, i);
    }

    public static BB0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            BB0 bb0 = new BB0(sDVar.c(b).b);
            bb0.d = sDVar.v(8, false);
            bb0.e = C0972Py0.d(sDVar.s(16, false));
            bb0.f = T3.d(sDVar.s(24, false));
            return bb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.i(this.e, 16, false);
        x.i(this.f, 24, false);
    }
}
