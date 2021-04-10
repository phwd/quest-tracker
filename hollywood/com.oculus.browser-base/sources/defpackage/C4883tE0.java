package defpackage;

/* renamed from: tE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4883tE0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public float d;
    public float e;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4883tE0() {
        super(16, 0);
    }

    public static C4883tE0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4883tE0 te0 = new C4883tE0(sDVar.c(b).b);
            te0.d = sDVar.l(8);
            te0.e = sDVar.l(12);
            return te0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.b(this.d, 8);
        x.b(this.e, 12);
    }

    public C4883tE0(int i) {
        super(16, i);
    }
}
