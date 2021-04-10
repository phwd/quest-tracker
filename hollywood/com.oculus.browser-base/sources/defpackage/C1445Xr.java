package defpackage;

/* renamed from: Xr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1445Xr extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2253ds f9239a;

    public C1445Xr(C2253ds dsVar) {
        this.f9239a = dsVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2253ds dsVar = this.f9239a;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        dsVar.g = g70;
        ((D70) g70).Q.b(dsVar.j);
    }
}
