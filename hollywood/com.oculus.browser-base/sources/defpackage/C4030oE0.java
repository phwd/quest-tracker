package defpackage;

/* renamed from: oE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4030oE0 implements J41 {

    /* renamed from: a  reason: collision with root package name */
    public final C4372qE0 f10539a;

    public C4030oE0(C4372qE0 qe0) {
        this.f10539a = qe0;
    }

    @Override // defpackage.J41
    public void a() {
        C4372qE0 qe0 = this.f10539a;
        qe0.f11125a.postDelayed(new RunnableC4201pE0(qe0), 500);
        qe0.b.run();
    }
}
