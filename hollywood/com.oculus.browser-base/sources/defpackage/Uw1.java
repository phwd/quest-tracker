package defpackage;

/* renamed from: Uw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Uw1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Zw1 f9056a;

    public Uw1(Zw1 zw1) {
        this.f9056a = zw1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Zw1 zw1 = this.f9056a;
        Boolean bool = (Boolean) obj;
        if (zw1.f.isEmpty() && zw1.e == 0 && zw1.d != null) {
            zw1.d = null;
        }
    }
}
