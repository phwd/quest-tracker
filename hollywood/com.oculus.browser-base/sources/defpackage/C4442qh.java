package defpackage;

/* renamed from: qh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4442qh extends AbstractC5658xp1 {
    public byte[] b;
    public C4612rh c;
    public boolean d;

    public static final C4442qh b(C4709sD sDVar, int i) {
        CC j = sDVar.j(i);
        C4612rh rhVar = null;
        if (j.f7794a == 0) {
            return null;
        }
        C4442qh qhVar = new C4442qh();
        int i2 = j.b;
        if (i2 == 0) {
            qhVar.b = sDVar.e(i + 8, 0, -1);
            qhVar.f11638a = 0;
        } else if (i2 == 1) {
            C4709sD s = sDVar.s(i + 8, false);
            CC[] ccArr = C4612rh.b;
            if (s != null) {
                s.b();
                try {
                    rhVar = new C4612rh(s.c(C4612rh.b).b);
                    rhVar.d = s.w(8, false).t();
                    rhVar.e = s.n(12);
                } finally {
                    s.a();
                }
            }
            qhVar.c = rhVar;
            qhVar.f11638a = 1;
        } else if (i2 == 2) {
            qhVar.d = sDVar.d(i + 8, 0);
            qhVar.f11638a = 2;
        }
        return qhVar;
    }

    @Override // defpackage.AbstractC5658xp1
    public final void a(C1648aL aLVar, int i) {
        aLVar.c(16, i);
        aLVar.c(this.f11638a, i + 4);
        int i2 = this.f11638a;
        if (i2 == 0) {
            aLVar.n(this.b, i + 8, 0, -1);
        } else if (i2 == 1) {
            aLVar.i(this.c, i + 8, false);
        } else if (i2 == 2) {
            aLVar.m(this.d, i + 8, 0);
        }
    }
}
