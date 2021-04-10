package defpackage;

/* renamed from: ir1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3106ir1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C4814sr1 d;
    public C4984tr1 e;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C3106ir1() {
        super(24, 0);
    }

    public static C3106ir1 d(C2740gj0 gj0) {
        C4814sr1 sr1;
        C4709sD sDVar = new C4709sD(gj0);
        sDVar.b();
        try {
            C3106ir1 ir1 = new C3106ir1(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC[] ccArr = C4814sr1.b;
            if (s == null) {
                sr1 = null;
            } else {
                s.b();
                try {
                    sr1 = new C4814sr1(s.c(C4814sr1.b).b);
                } finally {
                    s.a();
                }
            }
            ir1.d = sr1;
            C4984tr1.d(sDVar.s(16, false));
            ir1.e = null;
            return ir1;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.i(this.e, 16, false);
    }

    public C3106ir1(int i) {
        super(24, i);
    }
}
