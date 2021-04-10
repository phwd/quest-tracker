package defpackage;

/* renamed from: Aj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0027Aj extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0515Ij f7689a;

    public C0027Aj(C0515Ij ij) {
        this.f7689a = ij;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0515Ij ij = this.f7689a;
        AbstractC2451f01 f01 = (AbstractC2451f01) obj;
        C0454Hj hj = new C0454Hj(ij, f01);
        ij.O = hj;
        ((C3818n01) f01).c.L.b(hj);
    }
}
