package defpackage;

/* renamed from: Gj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0393Gj extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public int f8105a;
    public final /* synthetic */ C5638xj b;

    public C0393Gj(C0515Ij ij, C5638xj xjVar) {
        this.b = xjVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            this.f8105a = this.b.z(0);
            return;
        }
        C5638xj xjVar = this.b;
        xjVar.N.c(this.f8105a);
    }
}
