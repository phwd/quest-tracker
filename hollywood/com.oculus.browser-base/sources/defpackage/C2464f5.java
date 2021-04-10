package defpackage;

/* renamed from: f5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2464f5 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C2635g5 F;

    public C2464f5(C2635g5 g5Var) {
        this.F = g5Var;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(1, 6)) {
                return false;
            }
            this.F.a(C2293e5.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
