package defpackage;

/* renamed from: Rb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1039Rb implements AbstractC5104ub {

    /* renamed from: a  reason: collision with root package name */
    public final SA f8841a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C1039Rb(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f8841a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C0917Pb pb = new C0917Pb();
        pb.d = ((Integer) obj).intValue();
        pb.e = (C0431Hb0) obj2;
        this.b.b(pb.c(this.f8841a, new C0942Pj0(0, 2, this.c)));
    }
}
