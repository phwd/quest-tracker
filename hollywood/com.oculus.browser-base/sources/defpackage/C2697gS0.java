package defpackage;

/* renamed from: gS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2697gS0 extends AbstractC2185dS0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2185dS0 f10000a;
    public final AbstractC2185dS0 b;

    public C2697gS0(AbstractC2185dS0 ds0, AbstractC2185dS0 ds02, AbstractC2526fS0 fs0) {
        this.f10000a = ds0;
        this.b = ds02;
    }

    @Override // defpackage.AbstractC2185dS0
    public void a() {
        this.f10000a.a();
        this.b.a();
    }

    @Override // defpackage.AbstractC2185dS0
    public ZX0 c() {
        return this.f10000a.c();
    }

    @Override // defpackage.AbstractC2185dS0
    public void d(String str) {
        this.f10000a.d(str);
        this.b.d(str);
    }

    @Override // defpackage.AbstractC2185dS0
    public void e(int i, float f, float f2) {
        this.f10000a.e(i, f, f2);
        this.b.e(i, f, f2);
    }

    @Override // defpackage.AbstractC2185dS0
    public boolean f(boolean z) {
        return this.f10000a.f(z);
    }

    @Override // defpackage.AbstractC2185dS0
    public void g(boolean z, int i, int i2) {
        this.f10000a.g(z, i, i2);
        this.b.g(z, i, i2);
    }
}
