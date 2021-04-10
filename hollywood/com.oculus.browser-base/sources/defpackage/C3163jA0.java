package defpackage;

/* renamed from: jA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3163jA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3163jA0() {
        super(24, 0);
    }

    public static C3163jA0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3163jA0 ja0 = new C3163jA0(sDVar.c(b).b);
            ja0.d = sDVar.v(8, false);
            ja0.e = sDVar.v(16, false);
            return ja0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
    }

    public C3163jA0(int i) {
        super(24, i);
    }
}
