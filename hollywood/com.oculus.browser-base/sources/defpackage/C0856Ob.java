package defpackage;

/* renamed from: Ob  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0856Ob extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4554rI0 d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0856Ob() {
        super(16, 0);
    }

    public static C0856Ob d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0856Ob ob = new C0856Ob(sDVar.c(b).b);
            ob.d = C4554rI0.d(sDVar.s(8, false));
            return ob;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        aLVar.x(c).i(this.d, 8, false);
    }

    public C0856Ob(int i) {
        super(16, i);
    }
}
