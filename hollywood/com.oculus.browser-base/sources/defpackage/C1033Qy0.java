package defpackage;

/* renamed from: Qy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1033Qy0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String[] e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;

    static {
        CC[] ccArr = {new CC(88, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C1033Qy0() {
        super(88, 0);
    }

    public static C1033Qy0 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C1033Qy0 qy0 = new C1033Qy0(sDVar.c(b).b);
            qy0.d = sDVar.v(8, false);
            C4709sD s = sDVar.s(16, false);
            CC i2 = s.i(-1);
            qy0.e = new String[i2.b];
            for (int i3 = 0; i3 < i2.b; i3++) {
                qy0.e[i3] = s.v((i3 * 8) + 8, false);
            }
            qy0.f = sDVar.v(24, false);
            qy0.g = sDVar.v(32, false);
            qy0.h = sDVar.v(40, false);
            qy0.i = sDVar.v(48, false);
            qy0.j = sDVar.v(56, false);
            qy0.k = sDVar.v(64, false);
            qy0.l = sDVar.v(72, false);
            qy0.m = sDVar.v(80, false);
            return qy0;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        String[] strArr = this.e;
        if (strArr != null) {
            C1648aL t = x.t(strArr.length, 16, -1);
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.e;
                if (i2 >= strArr2.length) {
                    break;
                }
                t.k(strArr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(16, false);
        }
        x.k(this.f, 24, false);
        x.k(this.g, 32, false);
        x.k(this.h, 40, false);
        x.k(this.i, 48, false);
        x.k(this.j, 56, false);
        x.k(this.k, 64, false);
        x.k(this.l, 72, false);
        x.k(this.m, 80, false);
    }

    public C1033Qy0(int i2) {
        super(88, i2);
    }
}
