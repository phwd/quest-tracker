package defpackage;

/* renamed from: mU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3726mU0 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C3897nU0 F;

    public C3726mU0(C3897nU0 nu0) {
        this.F = nu0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(Integer.valueOf(C3555lU0.d(a2.b()).d));
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
