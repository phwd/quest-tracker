package defpackage;

/* renamed from: zA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5892zA1 extends AbstractRunnableC4702sA1 {
    public final /* synthetic */ ServiceConnectionC5552xA1 G;

    public C5892zA1(ServiceConnectionC5552xA1 xa1) {
        this.G = xa1;
    }

    @Override // defpackage.AbstractRunnableC4702sA1
    public final void a() {
        C4531rA1 ra1 = this.G.f11597a;
        ra1.c.a(4, "unlinkToDeath", new Object[0]);
        ra1.l.asBinder().unlinkToDeath(ra1.j, 0);
        C4531rA1 ra12 = this.G.f11597a;
        ra12.l = null;
        ra12.f = false;
    }
}
