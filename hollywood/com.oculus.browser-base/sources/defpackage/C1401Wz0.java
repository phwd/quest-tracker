package defpackage;

/* renamed from: Wz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1401Wz0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
    public C2875hW f;
    public int g;
    public int h;
    public int i;
    public int[] j;
    public GR0 k;

    static {
        CC[] ccArr = {new CC(64, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1401Wz0() {
        super(64, 0);
    }

    public static C1401Wz0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1401Wz0 wz0 = new C1401Wz0(sDVar.c(b).b);
            wz0.d = sDVar.v(8, false);
            wz0.e = sDVar.v(16, false);
            wz0.f = C2875hW.d(sDVar.s(24, true));
            int n = sDVar.n(32);
            wz0.g = n;
            if (n >= 0 && n <= 1) {
                wz0.h = sDVar.n(36);
                wz0.i = sDVar.n(40);
                wz0.j = sDVar.p(48, 0, -1);
                int i2 = 0;
                while (true) {
                    int[] iArr = wz0.j;
                    if (i2 < iArr.length) {
                        int i3 = iArr[i2];
                        if (i3 >= 0 && i3 <= 7) {
                            i2++;
                        } else {
                            throw new C4200pE("Invalid enum value.");
                        }
                    } else {
                        wz0.k = GR0.d(sDVar.s(56, true));
                        return wz0;
                    }
                }
            } else {
                throw new C4200pE("Invalid enum value.");
            }
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.i(this.f, 24, true);
        x.c(this.g, 32);
        x.c(this.h, 36);
        x.c(this.i, 40);
        x.p(this.j, 48, 0, -1);
        x.i(this.k, 56, true);
    }

    public C1401Wz0(int i2) {
        super(64, i2);
    }
}
