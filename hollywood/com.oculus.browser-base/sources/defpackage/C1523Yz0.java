package defpackage;

/* renamed from: Yz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1523Yz0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int h;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1523Yz0() {
        super(16, 0);
    }

    public static C1523Yz0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1523Yz0 yz0 = new C1523Yz0(sDVar.c(b).b);
            boolean z = false;
            yz0.d = sDVar.d(8, 0);
            yz0.e = sDVar.d(8, 1);
            yz0.f = sDVar.d(8, 2);
            yz0.g = sDVar.d(8, 3);
            int n = sDVar.n(12);
            yz0.h = n;
            if (n >= 0 && n <= 2) {
                z = true;
            }
            if (z) {
                return yz0;
            }
            throw new C4200pE("Invalid enum value.");
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.m(this.d, 8, 0);
        x.m(this.e, 8, 1);
        x.m(this.f, 8, 2);
        x.m(this.g, 8, 3);
        x.c(this.h, 12);
    }

    public C1523Yz0(int i) {
        super(16, i);
    }
}
