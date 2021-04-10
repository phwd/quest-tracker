package defpackage;

/* renamed from: jf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3239jf implements AbstractC1538Ze1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f10224a;

    public C3239jf(C3581lf lfVar, int i) {
        this.f10224a = i;
    }

    @Override // defpackage.AbstractC1538Ze1
    public void a(C1477Ye1 ye1) {
        C5116uf.f().g(this.f10224a, ye1.d);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void b(C1355We1 we1) {
        C5116uf.f().g(this.f10224a, we1.d);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void c(C1233Ue1 ue1) {
        C5116uf.f().c("Android.BackgroundTaskScheduler.ExactTaskCreated", AbstractC2556ff.b(this.f10224a));
    }
}
