package defpackage;

/* renamed from: eh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2391eh extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C2562fh F;

    public C2391eh(C2562fh fhVar) {
        this.F = fhVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(C2221dh.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
