package defpackage;

/* renamed from: ap0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1733ap0 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9492a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C1733ap0(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9492a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C1501Yo0 yo0 = new C1501Yo0();
        yo0.d = (C3093in0) obj;
        this.b.b(yo0.c(this.f9492a, new C0942Pj0(1, 2, this.c)));
    }
}
