package defpackage;

/* renamed from: nF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3861nF extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public boolean e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3861nF() {
        super(24, 0);
    }

    public static C3861nF d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3861nF nFVar = new C3861nF(sDVar.c(b).b);
            nFVar.d = sDVar.v(8, false);
            nFVar.e = sDVar.d(16, 0);
            return nFVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.m(this.e, 16, 0);
    }

    public C3861nF(int i) {
        super(24, i);
    }
}
