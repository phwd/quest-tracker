package defpackage;

/* renamed from: Mb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0734Mb extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final AbstractC4934tb F;

    public C0734Mb(AbstractC4934tb tbVar) {
        this.F = tbVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(2, 2)) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C0674Lb.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C0674Lb lb = new C0674Lb(sDVar.c(C0674Lb.b).b);
                lb.d = sDVar.d(8, 0);
                sDVar.a();
                this.F.a(Boolean.valueOf(lb.d));
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
