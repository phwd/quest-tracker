package defpackage;

/* renamed from: hW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2875hW extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;

    static {
        CC[] ccArr = {new CC(24, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2875hW(int i) {
        super(24, i);
    }

    public static C2875hW d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C2875hW hWVar = new C2875hW(sDVar.c(b).b);
            hWVar.d = sDVar.v(8, false);
            hWVar.e = sDVar.d(16, 0);
            hWVar.f = sDVar.d(16, 1);
            hWVar.g = sDVar.d(16, 2);
            hWVar.h = sDVar.d(16, 3);
            return hWVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.m(this.e, 16, 0);
        x.m(this.f, 16, 1);
        x.m(this.g, 16, 2);
        x.m(this.h, 16, 3);
    }
}
