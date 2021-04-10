package defpackage;

/* renamed from: Nf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0807Nf extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0625Kf[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C0807Nf() {
        super(16, 0);
    }

    public static C0807Nf d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C0807Nf nf = new C0807Nf(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            nf.d = new C0625Kf[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                nf.d[i2] = C0625Kf.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return nf;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C0625Kf[] kfArr = this.d;
        if (kfArr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(kfArr.length, 8, -1);
        int i = 0;
        while (true) {
            C0625Kf[] kfArr2 = this.d;
            if (i < kfArr2.length) {
                t.i(kfArr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C0807Nf(int i) {
        super(16, i);
    }
}
