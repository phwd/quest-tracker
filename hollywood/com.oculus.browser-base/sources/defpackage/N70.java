package defpackage;

/* renamed from: N70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class N70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final P70 f8528a;

    public N70(P70 p70) {
        this.f8528a = p70;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        P70 p70 = this.f8528a;
        p70.L = obj;
        p70.K = false;
        p70.c();
    }
}
