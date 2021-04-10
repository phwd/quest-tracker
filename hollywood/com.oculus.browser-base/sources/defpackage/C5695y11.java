package defpackage;

/* renamed from: y11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5695y11 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0956Pq0 f11656a;
    public final /* synthetic */ D11 b;

    public C5695y11(D11 d11, AbstractC0956Pq0 pq0) {
        this.b = d11;
        this.f11656a = pq0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC2400ek ekVar = (AbstractC2400ek) obj;
        D11 d11 = this.b;
        C1551Zj zj = (C1551Zj) ekVar;
        d11.Z.k(J70.X, (float) zj.T);
        d11.i0 = ekVar;
        B11 b11 = new B11(d11, ekVar);
        d11.j0 = b11;
        zj.Y.b(b11);
        ((C1078Rq0) this.f11656a).I.c(this);
    }
}
