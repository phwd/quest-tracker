package defpackage;

/* renamed from: ai  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1702ai extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C1882bi F;

    public C1702ai(C1882bi biVar) {
        this.F = biVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(6, 6)) {
                return false;
            }
            C1545Zh d = C1545Zh.d(a2.b());
            this.F.a(Long.valueOf(d.d), d.e);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
