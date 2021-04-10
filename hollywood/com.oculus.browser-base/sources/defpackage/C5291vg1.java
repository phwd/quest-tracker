package defpackage;

/* renamed from: vg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5291vg1 extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final AbstractC4099og1 F;

    public C5291vg1(AbstractC4099og1 og1) {
        this.F = og1;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(0, 2)) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C5121ug1.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C5121ug1 ug1 = new C5121ug1(sDVar.c(C5121ug1.b).b);
                ug1.d = sDVar.v(8, false);
                sDVar.a();
                this.F.a(ug1.d);
                return true;
            } catch (Throwable th) {
                sDVar.a();
                throw th;
            }
        } catch (C4200pE unused) {
            return false;
        }
    }
}
