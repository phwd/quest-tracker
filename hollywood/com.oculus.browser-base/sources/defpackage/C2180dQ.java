package defpackage;

/* renamed from: dQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2180dQ extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5318vp1 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2180dQ() {
        super(16, 0);
    }

    public static C2180dQ d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2180dQ dQVar = new C2180dQ(sDVar.c(b).b);
            dQVar.d = C5318vp1.d(sDVar.s(8, false));
            return dQVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C2180dQ(int i) {
        super(16, i);
    }
}
