package defpackage;

/* renamed from: o50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4003o50 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4686s50 f10532a;

    public C4003o50(C4686s50 s50) {
        this.f10532a = s50;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4686s50 s50 = this.f10532a;
        s50.F.j(I50.k, ((Integer) obj).intValue() <= ((C1794b90) s50.F.g(I50.f8198a)).size() + -2);
    }
}
