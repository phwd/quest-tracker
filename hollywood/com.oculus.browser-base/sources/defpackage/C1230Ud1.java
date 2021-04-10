package defpackage;

/* renamed from: Ud1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1230Ud1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1413Xd1 f9037a;

    public C1230Ud1(C1413Xd1 xd1) {
        this.f9037a = xd1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1413Xd1 xd1 = this.f9037a;
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        AbstractC2260du0 du02 = xd1.L;
        if (du02 != null) {
            ((AbstractC3838n70) du02).y0.c(xd1.M);
        }
        xd1.L = du0;
        C1352Wd1 wd1 = new C1352Wd1(xd1);
        xd1.M = wd1;
        ((AbstractC3838n70) du0).y0.b(wd1);
        xd1.b();
    }
}
