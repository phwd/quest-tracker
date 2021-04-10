package defpackage;

/* renamed from: Rq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Rq1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tq1 f8856a;

    public Rq1(Tq1 tq1) {
        this.f8856a = tq1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tq1 tq1 = this.f8856a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        tq1.H = booleanValue;
        if (tq1.F.h(Wq1.b)) {
            tq1.F.j(Wq1.f, tq1.H);
        }
        UH0 uh0 = tq1.F;
        TH0 th0 = Wq1.h;
        Vq1 vq1 = (Vq1) uh0.g(th0);
        tq1.G.onResult(Boolean.valueOf(booleanValue));
        boolean z = tq1.F.g(th0) != vq1;
        if (tq1.I != null && !z) {
            tq1.c();
        }
    }
}
