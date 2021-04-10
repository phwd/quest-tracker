package defpackage;

/* renamed from: Gb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0369Gb extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5065uI0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0369Gb() {
        super(16, 0);
    }

    public static C0369Gb d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0369Gb gb = new C0369Gb(sDVar.c(b).b);
            gb.d = C5065uI0.d(sDVar.s(8, false));
            return gb;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C0369Gb(int i) {
        super(16, i);
    }
}
