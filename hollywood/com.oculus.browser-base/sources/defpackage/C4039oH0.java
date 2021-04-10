package defpackage;

/* renamed from: oH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4039oH0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5232vH0 f10540a;
    public final WT b;

    public C4039oH0(C5232vH0 vh0, WT wt) {
        this.f10540a = vh0;
        this.b = wt;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5232vH0 vh0 = this.f10540a;
        try {
            vh0.b(this.b.apply(obj));
        } catch (Exception e) {
            vh0.e(e);
        }
    }
}
