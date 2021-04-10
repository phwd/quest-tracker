package defpackage;

/* renamed from: I1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class I1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final J1 f8195a;
    public final boolean b;

    public I1(J1 j1, boolean z) {
        this.f8195a = j1;
        this.b = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        J1 j1 = this.f8195a;
        j1.b.r(((C3522lG) obj).f10337a, this.b);
    }
}
