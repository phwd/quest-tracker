package defpackage;

/* renamed from: bi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1882bi implements AbstractC1555Zl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f9558a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C1882bi(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f9558a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C1545Zh zh = new C1545Zh();
        zh.d = ((Long) obj).longValue();
        zh.e = (C0026Ai1) obj2;
        this.b.b(zh.c(this.f9558a, new C0942Pj0(6, 6, this.c)));
    }
}
