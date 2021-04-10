package defpackage;

/* renamed from: Sw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Sw1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Xw1 f8927a;

    public Sw1(Xw1 xw1) {
        this.f8927a = xw1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Xw1 xw1 = this.f8927a;
        if (!((Boolean) obj).booleanValue()) {
            xw1.onServiceConnected(null, null);
        }
    }
}
