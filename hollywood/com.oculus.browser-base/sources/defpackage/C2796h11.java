package defpackage;

/* renamed from: h11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2796h11 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3821n11 f10043a;

    public C2796h11(C3821n11 n11) {
        this.f10043a = n11;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3821n11 n11 = this.f10043a;
        Boolean bool = (Boolean) obj;
        if (n11.a() != null) {
            n11.a().b.d = bool.booleanValue();
        }
    }
}
