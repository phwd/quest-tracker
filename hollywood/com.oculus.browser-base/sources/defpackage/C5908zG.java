package defpackage;

/* renamed from: zG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5908zG extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final AbstractC5058uG F;

    public C5908zG(AbstractC5058uG uGVar) {
        this.F = uGVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(C5738yG.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
