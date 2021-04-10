package defpackage;

/* renamed from: iF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3007iF implements AbstractC1555Zl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f10126a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C3007iF(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f10126a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C2665gF gFVar = new C2665gF();
        gFVar.d = ((Integer) obj).intValue();
        gFVar.e = (YE) obj2;
        this.b.b(gFVar.c(this.f10126a, new C0942Pj0(0, 2, this.c)));
    }
}
