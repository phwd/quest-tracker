package defpackage;

/* renamed from: cg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2049cg1 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C2220dg1 F;

    public C2049cg1(C2220dg1 dg1) {
        this.F = dg1;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            this.F.a(C1878bg1.d(a2.b()).d);
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
