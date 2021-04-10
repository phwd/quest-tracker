package defpackage;

/* renamed from: ni  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3932ni implements AbstractC1494Yl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f10509a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C3932ni(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f10509a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C3590li liVar = new C3590li();
        liVar.d = (C4442qh) obj;
        this.b.b(liVar.c(this.f10509a, new C0942Pj0(5, 2, this.c)));
    }
}
