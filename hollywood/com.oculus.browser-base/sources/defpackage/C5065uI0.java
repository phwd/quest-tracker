package defpackage;

/* renamed from: uI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5065uI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public byte[] d;
    public C0087Bi1 e;
    public String f;
    public C4725sI0[] g;
    public int h;
    public String i;
    public C1492Yk[] j;
    public boolean k;
    public boolean l;
    public C5906zF0[] m;
    public boolean n;
    public byte[] o;

    static {
        CC[] ccArr = {new CC(80, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C5065uI0(int i2) {
        super(80, i2);
    }

    public static C5065uI0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C5065uI0 ui0 = new C5065uI0(sDVar.c(b).b);
            ui0.d = sDVar.e(8, 0, -1);
            ui0.e = C0087Bi1.d(sDVar.s(16, true));
            ui0.f = sDVar.v(24, false);
            C4709sD s = sDVar.s(32, false);
            CC i2 = s.i(-1);
            ui0.g = new C4725sI0[i2.b];
            for (int i3 = 0; i3 < i2.b; i3++) {
                ui0.g[i3] = C4725sI0.d(AbstractC2531fV.n(i3, 8, 8, s, false));
            }
            int n2 = sDVar.n(40);
            ui0.h = n2;
            AbstractC2085cs1.a(n2);
            ui0.k = sDVar.d(44, 0);
            ui0.l = sDVar.d(44, 1);
            ui0.n = sDVar.d(44, 2);
            ui0.i = sDVar.v(48, true);
            C4709sD s2 = sDVar.s(56, false);
            CC i4 = s2.i(-1);
            ui0.j = new C1492Yk[i4.b];
            for (int i5 = 0; i5 < i4.b; i5++) {
                ui0.j[i5] = C1492Yk.d(AbstractC2531fV.n(i5, 8, 8, s2, false));
            }
            C4709sD s3 = sDVar.s(64, false);
            CC i6 = s3.i(-1);
            ui0.m = new C5906zF0[i6.b];
            for (int i7 = 0; i7 < i6.b; i7++) {
                ui0.m[i7] = C5906zF0.d(AbstractC2531fV.n(i7, 8, 8, s3, false));
            }
            ui0.o = sDVar.e(72, 1, -1);
            return ui0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.n(this.d, 8, 0, -1);
        x.i(this.e, 16, true);
        x.k(this.f, 24, false);
        C4725sI0[] si0Arr = this.g;
        if (si0Arr != null) {
            C1648aL t = x.t(si0Arr.length, 32, -1);
            int i2 = 0;
            while (true) {
                C4725sI0[] si0Arr2 = this.g;
                if (i2 >= si0Arr2.length) {
                    break;
                }
                t.i(si0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(32, false);
        }
        x.c(this.h, 40);
        x.m(this.k, 44, 0);
        x.m(this.l, 44, 1);
        x.m(this.n, 44, 2);
        x.k(this.i, 48, true);
        C1492Yk[] ykArr = this.j;
        if (ykArr != null) {
            C1648aL t2 = x.t(ykArr.length, 56, -1);
            int i3 = 0;
            while (true) {
                C1492Yk[] ykArr2 = this.j;
                if (i3 >= ykArr2.length) {
                    break;
                }
                t2.i(ykArr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            x.s(56, false);
        }
        C5906zF0[] zf0Arr = this.m;
        if (zf0Arr != null) {
            C1648aL t3 = x.t(zf0Arr.length, 64, -1);
            int i4 = 0;
            while (true) {
                C5906zF0[] zf0Arr2 = this.m;
                if (i4 >= zf0Arr2.length) {
                    break;
                }
                t3.i(zf0Arr2[i4], (i4 * 8) + 8, false);
                i4++;
            }
        } else {
            x.s(64, false);
        }
        x.n(this.o, 72, 1, -1);
    }
}
