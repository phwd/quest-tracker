package defpackage;

/* renamed from: Bb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0064Bb extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public int d;
    public int e;
    public int f;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0064Bb(int i) {
        super(24, i);
    }

    public static C0064Bb d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0064Bb bb = new C0064Bb(sDVar.c(b).b);
            int n = sDVar.n(8);
            bb.d = n;
            boolean z = true;
            if (n >= 0 && n <= 2) {
                int n2 = sDVar.n(12);
                bb.e = n2;
                if (n2 < 0 || n2 > 2) {
                    z = false;
                }
                if (z) {
                    int n3 = sDVar.n(16);
                    bb.f = n3;
                    AbstractC2085cs1.a(n3);
                    return bb;
                }
                throw new C4200pE("Invalid enum value.");
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
        x.c(this.e, 12);
        x.c(this.f, 16);
    }
}
