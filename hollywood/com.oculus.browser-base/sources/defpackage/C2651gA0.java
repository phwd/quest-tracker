package defpackage;

/* renamed from: gA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2651gA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public String e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2651gA0() {
        super(24, 0);
    }

    public static C2651gA0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C2651gA0 ga0 = new C2651gA0(sDVar.c(b).b);
            int n = sDVar.n(8);
            ga0.d = n;
            if (n >= 0 && n <= 5) {
                ga0.e = sDVar.v(16, false);
                return ga0;
            }
            throw new C4200pE("Invalid enum value.");
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.c(this.d, 8);
        x.k(this.e, 16, false);
    }

    public C2651gA0(int i) {
        super(24, i);
    }
}
