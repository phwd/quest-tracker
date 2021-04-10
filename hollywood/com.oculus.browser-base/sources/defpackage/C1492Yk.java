package defpackage;

/* renamed from: Yk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1492Yk extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public byte d;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public byte[] h;

    static {
        CC[] ccArr = {new CC(48, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1492Yk(int i) {
        super(48, i);
    }

    public static C1492Yk d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1492Yk yk = new C1492Yk(sDVar.c(b).b);
            sDVar.x(8, 1);
            yk.d = sDVar.f11257a.f10015a.get(sDVar.b + 8);
            yk.e = sDVar.e(16, 1, 16);
            yk.f = sDVar.e(24, 1, 16);
            yk.g = sDVar.e(32, 1, 32);
            yk.h = sDVar.e(40, 1, -1);
            return yk;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.b.b.put(x.f9425a + 8, this.d);
        x.n(this.e, 16, 1, 16);
        x.n(this.f, 24, 1, 16);
        x.n(this.g, 32, 1, 32);
        x.n(this.h, 40, 1, -1);
    }
}
