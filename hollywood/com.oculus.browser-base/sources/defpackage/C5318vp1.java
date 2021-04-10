package defpackage;

/* renamed from: vp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5318vp1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public long d;
    public long e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5318vp1(int i) {
        super(24, i);
    }

    public static C5318vp1 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5318vp1 vp1 = new C5318vp1(sDVar.c(b).b);
            vp1.d = sDVar.q(8);
            vp1.e = sDVar.q(16);
            return vp1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.d(this.d, 8);
        x.d(this.e, 16);
    }
}
