package defpackage;

/* renamed from: Dt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0230Dt extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2287e3 f7919a;

    public C0230Dt(C2287e3 e3Var) {
        this.f7919a = e3Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2287e3 e3Var = this.f7919a;
        long longValue = ((Long) obj).longValue();
        if (!e3Var.g) {
            e3Var.g = true;
            AbstractC3364kK0.j("Startup.Android.Cold.TimeToVisibleContent", longValue);
        }
    }
}
