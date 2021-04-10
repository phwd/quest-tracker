package defpackage;

/* renamed from: Ff  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0320Ff extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C0381Gf F;

    public C0320Ff(C0381Gf gf) {
        this.F = gf;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(1, 2)) {
                return false;
            }
            this.F.a(C0259Ef.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
