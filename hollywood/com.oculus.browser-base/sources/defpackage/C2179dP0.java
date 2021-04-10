package defpackage;

/* renamed from: dP0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2179dP0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2691gP0 f9780a;

    public C2179dP0(C2691gP0 gp0) {
        this.f9780a = gp0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2691gP0 gp0 = this.f9780a;
        Runnable runnable = (Runnable) obj;
        HZ hz = gp0.f;
        if (hz != null) {
            if (hz.a()) {
                gp0.c();
            } else {
                gp0.b(false, runnable);
            }
        }
    }
}
