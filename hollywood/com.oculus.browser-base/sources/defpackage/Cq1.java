package defpackage;

/* renamed from: Cq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Cq1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public Cq1(int i) {
        super(16, i);
    }

    public static Cq1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            Cq1 cq1 = new Cq1(sDVar.c(b).b);
            cq1.d = sDVar.v(8, false);
            return cq1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).k(this.d, 8, false);
    }
}
