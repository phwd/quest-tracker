package defpackage;

/* renamed from: c5  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1952c5 implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9582a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C1952c5(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9582a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C1601a5 a5Var = new C1601a5();
        a5Var.d = (String[]) obj;
        this.b.b(a5Var.c(this.f9582a, new C0942Pj0(0, 6, this.c)));
    }
}
