package defpackage;

/* renamed from: uE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5053uE0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5563xE0 f11400a;

    public C5053uE0(C5563xE0 xe0) {
        this.f11400a = xe0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5563xE0 xe0 = this.f11400a;
        xe0.f11599J = Boolean.valueOf(((Boolean) obj).booleanValue());
        xe0.a();
    }
}
