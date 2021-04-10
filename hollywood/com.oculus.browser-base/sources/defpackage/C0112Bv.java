package defpackage;

import java.nio.charset.Charset;
import java.util.Objects;

/* renamed from: Bv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0112Bv {

    /* renamed from: a  reason: collision with root package name */
    public final C6014zv f7771a;

    public C0112Bv(C6014zv zvVar) {
        Charset charset = F30.f7992a;
        this.f7771a = zvVar;
        zvVar.b = this;
    }

    public void a(int i, boolean z) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 0);
        zvVar.D(z ? (byte) 1 : 0);
    }

    public void b(int i, AbstractC1248Uk uk) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 2);
        zvVar.F(uk);
    }

    public void c(int i, double d) {
        C6014zv zvVar = this.f7771a;
        Objects.requireNonNull(zvVar);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        zvVar.M((i << 3) | 1);
        zvVar.I(doubleToRawLongBits);
    }

    public void d(int i, int i2) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 5);
        zvVar.H(i2);
    }

    public void e(int i, long j) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 1);
        zvVar.I(j);
    }

    public void f(int i, float f) {
        C6014zv zvVar = this.f7771a;
        Objects.requireNonNull(zvVar);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        zvVar.M((i << 3) | 5);
        zvVar.H(floatToRawIntBits);
    }

    public void g(int i, Object obj, UO0 uo0) {
        C6014zv zvVar = this.f7771a;
        int i2 = i << 3;
        zvVar.M(i2 | 3);
        uo0.a((AbstractC1125Sj0) obj, zvVar.b);
        zvVar.M(i2 | 4);
    }

    public void h(int i, long j) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 0);
        zvVar.N(j);
    }

    public void i(int i, Object obj, UO0 uo0) {
        C6014zv zvVar = this.f7771a;
        AbstractC1125Sj0 sj0 = (AbstractC1125Sj0) obj;
        zvVar.M((i << 3) | 2);
        zvVar.M(((AbstractC2790h) sj0).b(uo0));
        uo0.a(sj0, zvVar.b);
    }

    public void j(int i, int i2) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 5);
        zvVar.H(i2);
    }

    public void k(int i, long j) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 1);
        zvVar.I(j);
    }

    public void l(int i, int i2) {
        C6014zv zvVar = this.f7771a;
        int A = C6014zv.A(i2);
        zvVar.M((i << 3) | 0);
        zvVar.M(A);
    }

    public void m(int i, long j) {
        C6014zv zvVar = this.f7771a;
        long B = C6014zv.B(j);
        zvVar.M((i << 3) | 0);
        zvVar.N(B);
    }

    public void n(int i, int i2) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 0);
        zvVar.M(i2);
    }

    public void o(int i, long j) {
        C6014zv zvVar = this.f7771a;
        zvVar.M((i << 3) | 0);
        zvVar.N(j);
    }
}
