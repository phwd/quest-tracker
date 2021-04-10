package defpackage;

/* renamed from: ep0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2416ep0 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C2587fp0 F;

    public C2416ep0(C2587fp0 fp0) {
        this.F = fp0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(3, 2)) {
                return false;
            }
            this.F.a(C2245dp0.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
