package defpackage;

/* renamed from: w20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5357w20 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C5527x20 F;

    public C5357w20(C5527x20 x20) {
        this.F = x20;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(C5187v20.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
