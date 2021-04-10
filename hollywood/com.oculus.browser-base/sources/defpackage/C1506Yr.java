package defpackage;

/* renamed from: Yr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1506Yr extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2253ds f9301a;

    public C1506Yr(C2253ds dsVar) {
        this.f9301a = dsVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2253ds dsVar = this.f9301a;
        AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) obj;
        dsVar.h = ca1;
        ((AbstractC0246Ea1) ca1).c.a(dsVar.k);
    }
}
