package defpackage;

/* renamed from: aw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1753aw extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public byte[] e;
    public byte[] f;
    public byte[] g;

    static {
        CC[] ccArr = {new CC(40, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1753aw() {
        super(40, 0);
    }

    public static C1753aw d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1753aw awVar = new C1753aw(sDVar.c(b).b);
            awVar.d = sDVar.v(8, false);
            awVar.e = sDVar.e(16, 0, -1);
            awVar.f = sDVar.e(24, 0, -1);
            awVar.g = sDVar.e(32, 0, -1);
            return awVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.n(this.e, 16, 0, -1);
        x.n(this.f, 24, 0, -1);
        x.n(this.g, 32, 0, -1);
    }

    public C1753aw(int i) {
        super(40, i);
    }
}
