package defpackage;

/* renamed from: ZA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public AbstractC1797bA0 d;
    public C1401Wz0[] e;
    public C2788gz0 f;
    public C1523Yz0 g;
    public boolean h;

    static {
        CC[] ccArr = {new CC(48, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public ZA0() {
        super(48, 0);
    }

    public static ZA0 d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            ZA0 za0 = new ZA0(sDVar.c(b).b);
            za0.d = (AbstractC1797bA0) sDVar.t(8, false, AbstractC4360qA0.f11121a);
            C4709sD s = sDVar.s(16, false);
            CC i = s.i(-1);
            za0.e = new C1401Wz0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                za0.e[i2] = C1401Wz0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            za0.f = C2788gz0.d(sDVar.s(24, false));
            za0.g = C1523Yz0.d(sDVar.s(32, false));
            za0.h = sDVar.d(40, 0);
            return za0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        AbstractC1797bA0 ba0 = this.d;
        int i = AbstractC1797bA0.y;
        x.g(ba0, 8, false, AbstractC4360qA0.f11121a);
        C1401Wz0[] wz0Arr = this.e;
        if (wz0Arr != null) {
            C1648aL t = x.t(wz0Arr.length, 16, -1);
            int i2 = 0;
            while (true) {
                C1401Wz0[] wz0Arr2 = this.e;
                if (i2 >= wz0Arr2.length) {
                    break;
                }
                t.i(wz0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(16, false);
        }
        x.i(this.f, 24, false);
        x.i(this.g, 32, false);
        x.m(this.h, 40, 0);
    }

    public ZA0(int i) {
        super(48, i);
    }
}
