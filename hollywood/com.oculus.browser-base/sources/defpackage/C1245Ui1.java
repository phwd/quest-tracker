package defpackage;

/* renamed from: Ui1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1245Ui1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1550Zi1 f9044a;

    public C1245Ui1(C1550Zi1 zi1) {
        this.f9044a = zi1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1550Zi1 zi1 = this.f9044a;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        zi1.j = g70;
        C1489Yi1 yi1 = new C1489Yi1(zi1);
        zi1.k = yi1;
        ((D70) g70).Q.b(yi1);
    }
}
