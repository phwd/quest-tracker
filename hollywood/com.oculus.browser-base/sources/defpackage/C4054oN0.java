package defpackage;

/* renamed from: oN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4054oN0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final GN0 f10545a;

    public C4054oN0(GN0 gn0) {
        this.f10545a = gn0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        GN0 gn0 = this.f10545a;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        gn0.R = g70;
        DN0 dn0 = new DN0(gn0);
        gn0.S = dn0;
        ((D70) g70).Q.b(dn0);
    }
}
