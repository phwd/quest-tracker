package defpackage;

/* renamed from: s60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4689s60 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4883tE0[] d;
    public int e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4689s60() {
        super(24, 0);
    }

    public static C4689s60 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4689s60 s60 = new C4689s60(sDVar.c(b).b);
            boolean z = false;
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            s60.d = new C4883tE0[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                s60.d[i2] = C4883tE0.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            int n = sDVar.n(16);
            s60.e = n;
            if (n >= 0 && n <= 2) {
                z = true;
            }
            if (z) {
                return s60;
            }
            throw new C4200pE("Invalid enum value.");
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C4883tE0[] te0Arr = this.d;
        if (te0Arr != null) {
            C1648aL t = x.t(te0Arr.length, 8, -1);
            int i = 0;
            while (true) {
                C4883tE0[] te0Arr2 = this.d;
                if (i >= te0Arr2.length) {
                    break;
                }
                t.i(te0Arr2[i], (i * 8) + 8, false);
                i++;
            }
        } else {
            x.s(8, false);
        }
        x.c(this.e, 16);
    }

    public C4689s60(int i) {
        super(24, i);
    }
}
