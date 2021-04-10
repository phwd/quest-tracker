package defpackage;

/* renamed from: at1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1746at1 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C1917bt1 F;

    public C1746at1(C1917bt1 bt1) {
        this.F = bt1;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            if (!gj0.a().d.d(0, 2)) {
                return false;
            }
            this.F.a();
            return true;
        } catch (C4200pE unused) {
            return false;
        }
    }
}
