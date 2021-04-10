package defpackage;

/* renamed from: ob1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4084ob1 {

    /* renamed from: a  reason: collision with root package name */
    public final C4596rb1 f10561a;
    public C3229jb1 b;
    public C1128Sl c = new C1128Sl();
    public final /* synthetic */ C4766sb1 d;

    public C4084ob1(C4766sb1 sb1, C4596rb1 rb1) {
        this.d = sb1;
        this.f10561a = rb1;
    }

    public void a(boolean z) {
        C3229jb1 jb1 = this.b;
        if (jb1 != null) {
            jb1.g.set(true);
            jb1.e.cancel(z);
        }
        this.c.a();
    }

    public final void b() {
        C3229jb1 jb1 = new C3229jb1(this.d, this.f10561a);
        this.b = jb1;
        jb1.e(this.d.q);
    }
}
