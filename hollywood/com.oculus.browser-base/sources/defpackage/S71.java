package defpackage;

/* renamed from: S71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class S71 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2475f81 f8879a;

    public S71(C2475f81 f81) {
        this.f8879a = f81;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2475f81 f81 = this.f8879a;
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        f81.s = du0;
        ((AbstractC3838n70) du0).y0.b(f81.r);
    }
}
