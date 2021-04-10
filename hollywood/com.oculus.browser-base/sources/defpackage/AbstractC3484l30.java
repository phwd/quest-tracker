package defpackage;

/* renamed from: l30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3484l30 {
    /* JADX INFO: finally extract failed */
    public static boolean a(SA sa, AbstractC2630g30 g30, IS0 is0, AbstractC3255jk0 jk0) {
        C2740gj0 b = is0.b();
        CC[] ccArr = SN0.b;
        C4709sD sDVar = new C4709sD(b);
        sDVar.b();
        try {
            SN0 sn0 = new SN0(sDVar.c(SN0.b).b);
            sn0.d = RN0.b(sDVar, 8);
            sDVar.a();
            WN0 wn0 = new WN0();
            VN0 vn0 = new VN0();
            wn0.d = vn0;
            if (sn0.d.f11638a == 0) {
                C5918zJ0 zj0 = new C5918zJ0();
                vn0.f11638a = 0;
                vn0.b = zj0;
                wn0.d.b.d = g30.g();
            } else {
                wn0.d = null;
            }
            return jk0.b(wn0.c(sa, new C0942Pj0(-1, 2, is0.d.f)));
        } catch (Throwable th) {
            sDVar.a();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean b(AbstractC2630g30 g30, IS0 is0) {
        C2740gj0 b = is0.b();
        CC[] ccArr = UN0.b;
        C4709sD sDVar = new C4709sD(b);
        sDVar.b();
        try {
            UN0 un0 = new UN0(sDVar.c(UN0.b).b);
            un0.d = TN0.b(sDVar, 8);
            sDVar.a();
            TN0 tn0 = un0.d;
            if (tn0.f11638a != 0 || tn0.b.d > g30.g()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            sDVar.a();
            throw th;
        }
    }
}
