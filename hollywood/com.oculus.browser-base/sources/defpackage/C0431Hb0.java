package defpackage;

/* renamed from: Hb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0431Hb0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C1753aw d;
    public byte[] e;
    public int[] f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public byte[] k;
    public int l;
    public boolean m;
    public C3622ls1[] n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;

    static {
        CC[] ccArr = {new CC(56, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0431Hb0() {
        super(56, 0);
    }

    public static C0431Hb0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C0431Hb0 hb0 = new C0431Hb0(sDVar.c(b).b);
            hb0.d = C1753aw.d(sDVar.s(8, false));
            hb0.e = sDVar.e(16, 0, -1);
            hb0.f = sDVar.p(24, 0, -1);
            int i2 = 0;
            while (true) {
                int[] iArr = hb0.f;
                if (i2 >= iArr.length) {
                    break;
                }
                AbstractC0186Db.a(iArr[i2]);
                i2++;
            }
            hb0.g = sDVar.d(32, 0);
            hb0.h = sDVar.d(32, 1);
            hb0.i = sDVar.d(32, 2);
            hb0.j = sDVar.d(32, 3);
            hb0.m = sDVar.d(32, 4);
            hb0.o = sDVar.d(32, 5);
            hb0.p = sDVar.d(32, 6);
            hb0.q = sDVar.d(32, 7);
            hb0.r = sDVar.d(33, 0);
            hb0.s = sDVar.d(33, 1);
            hb0.l = sDVar.n(36);
            hb0.k = sDVar.e(40, 1, -1);
            C4709sD s2 = sDVar.s(48, true);
            if (s2 == null) {
                hb0.n = null;
            } else {
                CC i3 = s2.i(-1);
                hb0.n = new C3622ls1[i3.b];
                for (int i4 = 0; i4 < i3.b; i4++) {
                    hb0.n[i4] = C3622ls1.d(AbstractC2531fV.n(i4, 8, 8, s2, false));
                }
            }
            return hb0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.n(this.e, 16, 0, -1);
        x.p(this.f, 24, 0, -1);
        x.m(this.g, 32, 0);
        x.m(this.h, 32, 1);
        x.m(this.i, 32, 2);
        x.m(this.j, 32, 3);
        x.m(this.m, 32, 4);
        x.m(this.o, 32, 5);
        x.m(this.p, 32, 6);
        x.m(this.q, 32, 7);
        x.m(this.r, 33, 0);
        x.m(this.s, 33, 1);
        x.c(this.l, 36);
        x.n(this.k, 40, 1, -1);
        C3622ls1[] ls1Arr = this.n;
        if (ls1Arr == null) {
            x.s(48, true);
            return;
        }
        C1648aL t = x.t(ls1Arr.length, 48, -1);
        int i2 = 0;
        while (true) {
            C3622ls1[] ls1Arr2 = this.n;
            if (i2 < ls1Arr2.length) {
                t.i(ls1Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            } else {
                return;
            }
        }
    }

    public C0431Hb0(int i2) {
        super(56, i2);
    }
}
