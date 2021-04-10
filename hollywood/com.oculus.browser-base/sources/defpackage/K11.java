package defpackage;

/* renamed from: K11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class K11 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final P11 f8336a;

    public K11(P11 p11) {
        this.f8336a = p11;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        P11 p11 = this.f8336a;
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        p11.I = du0;
        N11 n11 = new N11(p11);
        p11.T = n11;
        ((AbstractC3838n70) du0).y0.b(n11);
    }
}
