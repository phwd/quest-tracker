package defpackage;

/* renamed from: uF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5055uF implements AbstractC1555Zl {

    /* renamed from: a  reason: collision with root package name */
    public final SA f11401a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C5055uF(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f11401a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        C4715sF sFVar = new C4715sF();
        sFVar.d = ((Integer) obj).intValue();
        sFVar.e = (C3487l40[]) obj2;
        this.b.b(sFVar.c(this.f11401a, new C0942Pj0(0, 2, this.c)));
    }
}
