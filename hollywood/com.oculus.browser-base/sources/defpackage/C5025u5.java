package defpackage;

/* renamed from: u5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5025u5 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C5195v5 F;

    public C5025u5(C5195v5 v5Var) {
        this.F = v5Var;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            if (!gj0.a().d.d(2, 6)) {
                return false;
            }
            this.F.a();
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
