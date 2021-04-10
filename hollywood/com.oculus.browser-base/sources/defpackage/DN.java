package defpackage;

/* renamed from: DN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DN extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public AN[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public DN() {
        super(16, 0);
    }

    public static DN d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            DN dn = new DN(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            dn.d = new AN[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                dn.d[i2] = AN.d(AbstractC2531fV.n(i2, 8, 8, s, false));
            }
            return dn;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        AN[] anArr = this.d;
        if (anArr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(anArr.length, 8, -1);
        int i = 0;
        while (true) {
            AN[] anArr2 = this.d;
            if (i < anArr2.length) {
                t.i(anArr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public DN(int i) {
        super(16, i);
    }
}
