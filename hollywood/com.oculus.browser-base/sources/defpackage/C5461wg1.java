package defpackage;

/* renamed from: wg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5461wg1 implements AbstractC4099og1 {

    /* renamed from: a  reason: collision with root package name */
    public final SA f11561a;
    public final AbstractC3255jk0 b;
    public final long c;

    public C5461wg1(SA sa, AbstractC3255jk0 jk0, long j) {
        this.f11561a = sa;
        this.b = jk0;
        this.c = j;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C5121ug1 ug1 = new C5121ug1();
        ug1.d = (String) obj;
        this.b.b(ug1.c(this.f11561a, new C0942Pj0(0, 2, this.c)));
    }
}
