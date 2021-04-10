package defpackage;

/* renamed from: tr1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4984tr1 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C0026Ai1 d;
    public C0026Ai1 e;
    public C2708gY f;
    public String g;
    public String h;
    public int i;
    public long j = -1;
    public long k = -1;
    public long l = -1;
    public int m = 2;

    static {
        CC[] ccArr = {new CC(248, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4984tr1(int i2) {
        super(248, i2);
    }

    /* JADX INFO: finally extract failed */
    public static C4984tr1 d(C4709sD sDVar) {
        C2708gY gYVar = null;
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4984tr1 tr1 = new C4984tr1(sDVar.c(b).b);
            tr1.d = C0026Ai1.d(sDVar.s(8, false));
            tr1.e = C0026Ai1.d(sDVar.s(16, false));
            C4709sD s = sDVar.s(24, false);
            CC[] ccArr = C2708gY.b;
            if (s != null) {
                s.b();
                try {
                    C2708gY gYVar2 = new C2708gY(s.c(C2708gY.b).b);
                    s.a();
                    gYVar = gYVar2;
                } catch (Throwable th) {
                    s.a();
                    throw th;
                }
            }
            tr1.f = gYVar;
            tr1.g = sDVar.v(32, false);
            tr1.h = sDVar.v(40, false);
            tr1.i = sDVar.n(48);
            throw new C4200pE("Invalid enum value.");
        } catch (Throwable th2) {
            sDVar.a();
            throw th2;
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.i(this.e, 16, false);
        x.i(this.f, 24, false);
        x.k(this.g, 32, false);
        x.k(this.h, 40, false);
        x.c(this.i, 48);
        x.m(false, 52, 0);
        x.m(false, 52, 1);
        x.m(false, 52, 2);
        x.m(false, 52, 3);
        x.m(false, 52, 4);
        x.m(false, 52, 5);
        x.m(false, 52, 6);
        x.m(false, 52, 7);
        x.m(false, 53, 0);
        x.m(false, 53, 1);
        x.m(false, 53, 2);
        x.m(false, 53, 3);
        x.m(false, 53, 4);
        x.m(false, 53, 5);
        x.m(false, 53, 6);
        x.m(false, 53, 7);
        x.m(false, 54, 0);
        x.m(false, 54, 1);
        x.d(this.j, 56);
        x.d(this.k, 64);
        x.d(this.l, 72);
        x.d(0, 80);
        x.i(null, 88, false);
        x.i(null, 96, false);
        x.i(null, 104, true);
        x.c(0, 112);
        x.c(0, 116);
        x.k(null, 120, false);
        x.i(null, 128, false);
        x.i(null, 136, false);
        x.s(144, false);
        x.c(this.m, 152);
        x.c(0, 156);
        x.k(null, 160, false);
        x.i(null, 168, true);
        x.s(176, false);
        x.i(null, 184, true);
        x.i(null, 192, false);
        x.i(null, 200, false);
        x.i(null, 208, true);
        x.i(null, 216, true);
        x.i(null, 224, true);
        x.s(232, false);
        x.i(null, 240, false);
    }
}
