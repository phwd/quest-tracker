package defpackage;

/* renamed from: q  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4328q extends AbstractC3302k {
    public C4328q() {
        super(null);
    }

    @Override // defpackage.AbstractC3302k
    public boolean a(AbstractC4669s sVar, C3986o oVar, C3986o oVar2) {
        synchronized (sVar) {
            if (sVar.f11242J != oVar) {
                return false;
            }
            sVar.f11242J = oVar2;
            return true;
        }
    }

    @Override // defpackage.AbstractC3302k
    public boolean b(AbstractC4669s sVar, Object obj, Object obj2) {
        synchronized (sVar) {
            if (sVar.I != obj) {
                return false;
            }
            sVar.I = obj2;
            return true;
        }
    }

    @Override // defpackage.AbstractC3302k
    public boolean c(AbstractC4669s sVar, r rVar, r rVar2) {
        synchronized (sVar) {
            if (sVar.K != rVar) {
                return false;
            }
            sVar.K = rVar2;
            return true;
        }
    }

    @Override // defpackage.AbstractC3302k
    public void d(r rVar, r rVar2) {
        rVar.c = rVar2;
    }

    @Override // defpackage.AbstractC3302k
    public void e(r rVar, Thread thread) {
        rVar.b = thread;
    }
}
