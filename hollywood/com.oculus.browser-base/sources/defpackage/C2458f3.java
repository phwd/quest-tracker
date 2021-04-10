package defpackage;

/* renamed from: f3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2458f3 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2800h3 f9894a;

    public C2458f3(C2800h3 h3Var) {
        this.f9894a = h3Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2800h3 h3Var = this.f9894a;
        Integer num = (Integer) obj;
        if (h3Var.b.isInitialized()) {
            int intValue = num.intValue();
            if (intValue == 1) {
                h3Var.b.l().f().w();
            } else if (intValue != 7 && intValue != 8) {
                h3Var.b.l().f().u();
            }
        }
    }
}
