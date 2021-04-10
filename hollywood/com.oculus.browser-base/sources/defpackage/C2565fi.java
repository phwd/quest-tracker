package defpackage;

/* renamed from: fi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2565fi extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C2736gi F;

    public C2565fi(C2736gi giVar) {
        this.F = giVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(7, 2)) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C2394ei.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C2394ei eiVar = new C2394ei(sDVar.c(C2394ei.b).b);
                eiVar.d = sDVar.v(8, false);
                sDVar.a();
                this.F.a(eiVar.d);
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
