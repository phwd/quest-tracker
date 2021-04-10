package defpackage;

/* renamed from: GR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GR0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public byte[][] d;
    public byte[] e;
    public C0087Bi1 f;

    static {
        CC[] ccArr = {new CC(32, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public GR0(int i) {
        super(32, i);
    }

    public static GR0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            GR0 gr0 = new GR0(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            gr0.d = new byte[i.b][];
            for (int i2 = 0; i2 < i.b; i2++) {
                gr0.d[i2] = s.e((i2 * 8) + 8, 0, -1);
            }
            gr0.e = sDVar.e(16, 0, -1);
            gr0.f = C0087Bi1.d(sDVar.s(24, true));
            return gr0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        byte[][] bArr = this.d;
        if (bArr != null) {
            C1648aL t = x.t(bArr.length, 8, -1);
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.d;
                if (i >= bArr2.length) {
                    break;
                }
                t.n(bArr2[i], (i * 8) + 8, 0, -1);
                i++;
            }
        } else {
            x.s(8, false);
        }
        x.n(this.e, 16, 0, -1);
        x.i(this.f, 24, true);
    }
}
