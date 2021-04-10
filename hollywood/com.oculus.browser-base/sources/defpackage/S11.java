package defpackage;

/* renamed from: S11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class S11 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Z11 f8869a;

    public S11(Z11 z11) {
        this.f8869a = z11;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Z11 z11 = this.f8869a;
        z11.f.f(null);
        z11.j.onResult((Runnable) obj);
    }
}
