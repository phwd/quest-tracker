package defpackage;

/* renamed from: zV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5941zV extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1753aw d;
    public byte[] e;
    public byte[] f;
    public boolean g;
    public boolean h;
    public boolean i;
    public C3622ls1[] j;
    public boolean k;
    public C5906zF0 l;
    public boolean m;
    public boolean n;
    public byte[] o;
    public boolean p;
    public boolean q;

    static {
        CC[] ccArr = {new CC(64, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5941zV() {
        super(64, 0);
    }

    public static C5941zV d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5941zV zVVar = new C5941zV(sDVar.c(b).b);
            zVVar.d = C1753aw.d(sDVar.s(8, false));
            zVVar.e = sDVar.e(16, 0, -1);
            zVVar.f = sDVar.e(24, 1, -1);
            zVVar.g = sDVar.d(32, 0);
            zVVar.h = sDVar.d(32, 1);
            zVVar.i = sDVar.d(32, 2);
            zVVar.k = sDVar.d(32, 3);
            zVVar.m = sDVar.d(32, 4);
            zVVar.n = sDVar.d(32, 5);
            zVVar.p = sDVar.d(32, 6);
            zVVar.q = sDVar.d(32, 7);
            C4709sD s = sDVar.s(40, true);
            if (s == null) {
                zVVar.j = null;
            } else {
                CC i2 = s.i(-1);
                zVVar.j = new C3622ls1[i2.b];
                for (int i3 = 0; i3 < i2.b; i3++) {
                    zVVar.j[i3] = C3622ls1.d(AbstractC2531fV.n(i3, 8, 8, s, false));
                }
            }
            zVVar.l = C5906zF0.d(sDVar.s(48, true));
            zVVar.o = sDVar.e(56, 1, -1);
            return zVVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.n(this.e, 16, 0, -1);
        x.n(this.f, 24, 1, -1);
        x.m(this.g, 32, 0);
        x.m(this.h, 32, 1);
        x.m(this.i, 32, 2);
        x.m(this.k, 32, 3);
        x.m(this.m, 32, 4);
        x.m(this.n, 32, 5);
        x.m(this.p, 32, 6);
        x.m(this.q, 32, 7);
        C3622ls1[] ls1Arr = this.j;
        if (ls1Arr != null) {
            C1648aL t = x.t(ls1Arr.length, 40, -1);
            int i2 = 0;
            while (true) {
                C3622ls1[] ls1Arr2 = this.j;
                if (i2 >= ls1Arr2.length) {
                    break;
                }
                t.i(ls1Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(40, true);
        }
        x.i(this.l, 48, true);
        x.n(this.o, 56, 1, -1);
    }

    public C5941zV(int i2) {
        super(64, i2);
    }
}
