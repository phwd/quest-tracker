package defpackage;

/* renamed from: tF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4885tF extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C5055uF F;

    public C4885tF(C5055uF uFVar) {
        this.F = uFVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            C4715sF d = C4715sF.d(a2.b());
            this.F.a(Integer.valueOf(d.d), d.e);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
