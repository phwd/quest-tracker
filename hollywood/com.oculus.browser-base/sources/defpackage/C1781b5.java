package defpackage;

/* renamed from: b5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1781b5 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C1952c5 F;

    public C1781b5(C1952c5 c5Var) {
        this.F = c5Var;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 6)) {
                return false;
            }
            this.F.a(C1601a5.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
