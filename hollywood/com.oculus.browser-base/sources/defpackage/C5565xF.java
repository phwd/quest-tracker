package defpackage;

/* renamed from: xF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5565xF extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C5735yF F;

    public C5565xF(C5735yF yFVar) {
        this.F = yFVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(2, 2)) {
                return false;
            }
            C5395wF d = C5395wF.d(a2.b());
            this.F.a(Integer.valueOf(d.d), d.e);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
