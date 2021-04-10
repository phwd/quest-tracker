package defpackage;

/* renamed from: Zo0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1562Zo0 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C1733ap0 F;

    public C1562Zo0(C1733ap0 ap0) {
        this.F = ap0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(1, 2)) {
                return false;
            }
            this.F.a(C1501Yo0.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
