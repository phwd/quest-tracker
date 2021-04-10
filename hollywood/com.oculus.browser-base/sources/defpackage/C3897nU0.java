package defpackage;

/* renamed from: nU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3897nU0 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f10493a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C3897nU0(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f10493a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C3555lU0 lu0 = new C3555lU0();
        lu0.d = ((Integer) obj).intValue();
        this.b.b(lu0.c(this.f10493a, new C0942Pj0(0, 2, this.c)));
    }
}
