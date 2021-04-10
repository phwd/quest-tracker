package defpackage;

/* renamed from: rF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4544rF extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4544rF() {
        super(16, 0);
    }

    public static C4544rF d(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C4544rF rFVar = new C4544rF(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            rFVar.d = new String[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                rFVar.d[i2] = s.v((i2 * 8) + 8, false);
            }
            return rFVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        String[] strArr = this.d;
        if (strArr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(strArr.length, 8, -1);
        int i = 0;
        while (true) {
            String[] strArr2 = this.d;
            if (i < strArr2.length) {
                t.k(strArr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }

    public C4544rF(int i) {
        super(16, i);
    }
}
