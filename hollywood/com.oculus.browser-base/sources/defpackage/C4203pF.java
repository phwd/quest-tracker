package defpackage;

/* renamed from: pF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4203pF extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C4374qF F;

    public C4203pF(C4374qF qFVar) {
        this.F = qFVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(1, 2)) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C4032oF.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C4032oF oFVar = new C4032oF(sDVar.c(C4032oF.b).b);
                oFVar.d = sDVar.n(8);
                sDVar.a();
                this.F.a(Integer.valueOf(oFVar.d));
                return true;
            } catch (Throwable th) {
                sDVar.a();
                throw th;
            }
        } catch (C4200pE unused) {
            return false;
        }
    }
}
