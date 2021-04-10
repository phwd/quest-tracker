package defpackage;

/* renamed from: qH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4381qH0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1564Zp0 f11130a;
    public final C5232vH0 b;

    public C4381qH0(C1564Zp0 zp0, C5232vH0 vh0) {
        this.f11130a = zp0;
        this.b = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1564Zp0 zp0 = this.f11130a;
        C5232vH0 vh0 = this.b;
        try {
            C5232vH0 vh02 = (C5232vH0) zp0.apply(obj);
            vh0.getClass();
            C4722sH0 sh0 = new C4722sH0(vh0);
            C4892tH0 th0 = new C4892tH0(vh0);
            vh02.h(sh0);
            vh02.a(th0);
        } catch (Exception e) {
            vh0.e(e);
        }
    }
}
