package defpackage;

/* renamed from: an1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1719an1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4994tv d;
    public C1889bk0[] e;
    public C1889bk0[] f;
    public GS0[] g;
    public C0509Ih[] h;
    public Or1 i;

    static {
        CC[] ccArr = {new CC(56, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1719an1() {
        super(56, 0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: GS0[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v1, resolved type: GS0 */
    /* JADX WARN: Multi-variable type inference failed */
    public static C1719an1 d(C2740gj0 gj0) {
        Or1 or1;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C1719an1 an1 = new C1719an1(sDVar.c(b).b);
            an1.d = C4994tv.d(sDVar.s(8, false));
            C4709sD s = sDVar.s(16, false);
            CC i2 = s.i(-1);
            an1.e = new C1889bk0[i2.b];
            for (int i3 = 0; i3 < i2.b; i3++) {
                an1.e[i3] = C1889bk0.d(AbstractC2531fV.n(i3, 8, 8, s, false));
            }
            C4709sD s2 = sDVar.s(24, false);
            CC i4 = s2.i(-1);
            an1.f = new C1889bk0[i4.b];
            for (int i5 = 0; i5 < i4.b; i5++) {
                an1.f[i5] = C1889bk0.d(AbstractC2531fV.n(i5, 8, 8, s2, false));
            }
            C4709sD s3 = sDVar.s(32, false);
            CC i6 = s3.i(-1);
            an1.g = new GS0[i6.b];
            int i7 = 0;
            while (true) {
                or1 = null;
                if (i7 >= i6.b) {
                    break;
                }
                C4709sD n = AbstractC2531fV.n(i7, 8, 8, s3, false);
                GS0[] gs0Arr = an1.g;
                CC[] ccArr = GS0.b;
                if (n != null) {
                    n.b();
                    try {
                        GS0 gs0 = new GS0(n.c(GS0.b).b);
                        gs0.d = C4442qh.b(n, 8);
                        n.a();
                        or1 = gs0;
                    } catch (Throwable th) {
                        n.a();
                        throw th;
                    }
                }
                gs0Arr[i7] = or1;
                i7++;
            }
            C4709sD s4 = sDVar.s(40, false);
            CC i8 = s4.i(-1);
            an1.h = new C0509Ih[i8.b];
            for (int i9 = 0; i9 < i8.b; i9++) {
                an1.h[i9] = C0509Ih.d(AbstractC2531fV.n(i9, 8, 8, s4, false));
            }
            C4709sD s5 = sDVar.s(48, true);
            CC[] ccArr2 = Or1.b;
            if (s5 != null) {
                s5.b();
                try {
                    or1 = new Or1(s5.c(Or1.b).b);
                    or1.d = s5.d(8, 0);
                    or1.e = s5.d(8, 1);
                } finally {
                    s5.a();
                }
            }
            an1.i = or1;
            sDVar.a();
            return an1;
        } catch (Throwable th2) {
            sDVar.a();
            throw th2;
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        C1889bk0[] bk0Arr = this.e;
        if (bk0Arr != null) {
            C1648aL t = x.t(bk0Arr.length, 16, -1);
            int i2 = 0;
            while (true) {
                C1889bk0[] bk0Arr2 = this.e;
                if (i2 >= bk0Arr2.length) {
                    break;
                }
                t.i(bk0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(16, false);
        }
        C1889bk0[] bk0Arr3 = this.f;
        if (bk0Arr3 != null) {
            C1648aL t2 = x.t(bk0Arr3.length, 24, -1);
            int i3 = 0;
            while (true) {
                C1889bk0[] bk0Arr4 = this.f;
                if (i3 >= bk0Arr4.length) {
                    break;
                }
                t2.i(bk0Arr4[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            x.s(24, false);
        }
        GS0[] gs0Arr = this.g;
        if (gs0Arr != null) {
            C1648aL t3 = x.t(gs0Arr.length, 32, -1);
            int i4 = 0;
            while (true) {
                GS0[] gs0Arr2 = this.g;
                if (i4 >= gs0Arr2.length) {
                    break;
                }
                t3.i(gs0Arr2[i4], (i4 * 8) + 8, false);
                i4++;
            }
        } else {
            x.s(32, false);
        }
        C0509Ih[] ihArr = this.h;
        if (ihArr != null) {
            C1648aL t4 = x.t(ihArr.length, 40, -1);
            int i5 = 0;
            while (true) {
                C0509Ih[] ihArr2 = this.h;
                if (i5 >= ihArr2.length) {
                    break;
                }
                t4.i(ihArr2[i5], (i5 * 8) + 8, false);
                i5++;
            }
        } else {
            x.s(40, false);
        }
        x.i(this.i, 48, true);
    }

    public C1719an1(int i2) {
        super(56, i2);
    }
}
