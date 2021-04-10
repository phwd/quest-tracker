package defpackage;

/* renamed from: T3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class T3 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public String d;
    public String e;
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

    public T3(int i2) {
        super(88, i2);
    }

    public static T3 d(C4709sD sDVar) {
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            T3 t3 = new T3(sDVar.c(b).b);
            t3.d = sDVar.v(8, false);
            t3.e = sDVar.v(16, false);
            t3.f = sDVar.v(24, false);
            t3.g = sDVar.v(32, false);
            t3.h = sDVar.v(40, false);
            t3.i = sDVar.v(48, false);
            t3.j = sDVar.v(56, false);
            t3.k = sDVar.v(64, false);
            t3.l = sDVar.v(72, false);
            t3.m = sDVar.v(80, false);
            return t3;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.k(this.d, 8, false);
        x.k(this.e, 16, false);
        x.k(this.f, 24, false);
        x.k(this.g, 32, false);
        x.k(this.h, 40, false);
        x.k(this.i, 48, false);
        x.k(this.j, 56, false);
        x.k(this.k, 64, false);
        x.k(this.l, 72, false);
        x.k(this.m, 80, false);
    }
}
