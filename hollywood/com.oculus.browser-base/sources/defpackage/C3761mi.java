package defpackage;

/* renamed from: mi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3761mi extends AbstractC3557lV0 implements AbstractC3255jk0 {
    public final C3932ni F;

    public C3761mi(C3932ni niVar) {
        this.F = niVar;
    }

    /* JADX INFO: finally extract failed */
    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            IS0 a2 = gj0.a();
            if (!a2.d.d(5, 2)) {
                return false;
            }
            C2740gj0 b = a2.b();
            CC[] ccArr = C3590li.b;
            C4709sD sDVar = new C4709sD(b);
            sDVar.b();
            try {
                C3590li liVar = new C3590li(sDVar.c(C3590li.b).b);
                liVar.d = C4442qh.b(sDVar, 8);
                sDVar.a();
                this.F.a(liVar.d);
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
