package defpackage;

/* renamed from: Qb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0978Qb extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final AbstractC5104ub F;

    public C0978Qb(AbstractC5104ub ubVar) {
        this.F = ubVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            C0917Pb d = C0917Pb.d(a2.b());
            this.F.a(Integer.valueOf(d.d), d.e);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
