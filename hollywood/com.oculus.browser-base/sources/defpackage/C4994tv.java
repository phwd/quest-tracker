package defpackage;

/* renamed from: tv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4994tv extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4442qh d;
    public HS0[] e;
    public C4479qt0 f;
    public long g;
    public long h;
    public long i;
    public boolean j;
    public C5318vp1 k;
    public ZP[] l;

    static {
        CC[] ccArr = {new CC(88, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4994tv() {
        super(88, 0);
    }

    public static C4994tv d(C4709sD sDVar) {
        ZP[] zpArr = null;
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4994tv tvVar = new C4994tv(sDVar.c(b).b);
            tvVar.d = C4442qh.b(sDVar, 8);
            C4709sD s = sDVar.s(24, false);
            CC i2 = s.i(-1);
            tvVar.e = new HS0[i2.b];
            for (int i3 = 0; i3 < i2.b; i3++) {
                tvVar.e[i3] = HS0.d(AbstractC2531fV.n(i3, 8, 8, s, false));
            }
            tvVar.f = C4479qt0.d(sDVar.s(32, true));
            tvVar.g = sDVar.q(40);
            tvVar.h = sDVar.q(48);
            tvVar.i = sDVar.q(56);
            tvVar.j = sDVar.d(64, 0);
            tvVar.k = C5318vp1.d(sDVar.s(72, true));
            AbstractC2630g30 g30 = AbstractC3034iQ.f10136a;
            C4709sD s2 = sDVar.s(80, AbstractC5802yh.b(0));
            if (s2 != null) {
                int i4 = s2.h(8, -1).b;
                ZP[] zpArr2 = new ZP[i4];
                for (int i5 = 0; i5 < i4; i5++) {
                    zpArr2[i5] = s2.t((i5 * 8) + 8, false, g30);
                }
                zpArr = zpArr2;
            }
            tvVar.l = zpArr;
            return tvVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.j(this.d, 8, false);
        HS0[] hs0Arr = this.e;
        if (hs0Arr != null) {
            C1648aL t = x.t(hs0Arr.length, 24, -1);
            int i2 = 0;
            while (true) {
                HS0[] hs0Arr2 = this.e;
                if (i2 >= hs0Arr2.length) {
                    break;
                }
                t.i(hs0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(24, false);
        }
        x.i(this.f, 32, true);
        x.d(this.g, 40);
        x.d(this.h, 48);
        x.d(this.i, 56);
        x.m(this.j, 64, 0);
        x.i(this.k, 72, true);
        ZP[] zpArr = this.l;
        int i3 = ZP.q;
        AbstractC2630g30 g30 = AbstractC3034iQ.f10136a;
        if (zpArr == null) {
            x.s(80, AbstractC5802yh.b(0));
            return;
        }
        C1648aL v = x.v(8, zpArr.length, 80, -1);
        for (int i4 = 0; i4 < zpArr.length; i4++) {
            v.g(zpArr[i4], (i4 * 8) + 8, false, g30);
        }
    }

    public C4994tv(int i2) {
        super(88, i2);
    }
}
