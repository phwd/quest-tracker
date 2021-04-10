package defpackage;

/* renamed from: wA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5382wA1 extends AbstractRunnableC4702sA1 {
    public final /* synthetic */ C4531rA1 G;

    public C5382wA1(C4531rA1 ra1) {
        this.G = ra1;
    }

    @Override // defpackage.AbstractRunnableC4702sA1
    public final void a() {
        C4531rA1 ra1 = this.G;
        if (ra1.l != null) {
            ra1.c.a(4, "Unbind from service.", new Object[0]);
            C4531rA1 ra12 = this.G;
            ra12.b.unbindService(ra12.k);
            C4531rA1 ra13 = this.G;
            ra13.f = false;
            ra13.l = null;
            ra13.k = null;
        }
    }
}
