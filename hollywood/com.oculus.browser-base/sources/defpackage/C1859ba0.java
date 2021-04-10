package defpackage;

/* renamed from: ba0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1859ba0 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9547a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C1859ba0(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9547a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        Z90 z90 = new Z90();
        z90.d = (JT) obj;
        this.b.b(z90.c(this.f9547a, new C0942Pj0(0, 2, this.c)));
    }
}
