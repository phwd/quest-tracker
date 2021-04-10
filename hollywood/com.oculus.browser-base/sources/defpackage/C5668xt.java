package defpackage;

/* renamed from: xt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5668xt extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractActivityC2601fu f11645a;

    public C5668xt(AbstractActivityC2601fu fuVar) {
        this.f11645a = fuVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractActivityC2601fu fuVar = this.f11645a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        C3965nt ntVar = fuVar.o1.K;
        if (ntVar != null && ntVar.G != null) {
            ntVar.l(!booleanValue);
        }
    }
}
