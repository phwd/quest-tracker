package defpackage;

/* renamed from: aa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1679aa0 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C1859ba0 F;

    public C1679aa0(C1859ba0 ba0) {
        this.F = ba0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(Z90.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
