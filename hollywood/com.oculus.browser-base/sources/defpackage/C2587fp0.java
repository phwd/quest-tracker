package defpackage;

/* renamed from: fp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2587fp0 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9957a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C2587fp0(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9957a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C2245dp0 dp0 = new C2245dp0();
        dp0.d = (C3093in0) obj;
        this.b.b(dp0.c(this.f9957a, new C0942Pj0(3, 2, this.c)));
    }
}
