package defpackage;

/* renamed from: hF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2836hF extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C3007iF F;

    public C2836hF(C3007iF iFVar) {
        this.F = iFVar;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            C2665gF d = C2665gF.d(a2.b());
            this.F.a(Integer.valueOf(d.d), d.e);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
