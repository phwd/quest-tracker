package defpackage;

/* renamed from: yG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5738yG extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public Ex1 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5738yG() {
        super(16, 0);
    }

    public static C5738yG d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C5738yG yGVar = new C5738yG(sDVar.c(b).b);
            yGVar.d = Ex1.d(sDVar.s(8, true));
            return yGVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, true);
    }

    public C5738yG(int i) {
        super(16, i);
    }
}
