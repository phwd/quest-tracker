package defpackage;

/* renamed from: O30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O30 implements AbstractC0523In {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0523In f8595a;
    public float b;

    public O30(AbstractC0523In in) {
        this.f8595a = in;
    }

    @Override // defpackage.AbstractC0523In
    public boolean a(long j, long j2) {
        return this.f8595a.a(j, j2);
    }

    @Override // defpackage.AbstractC0523In
    public float b(long j) {
        return this.b - this.f8595a.b(j);
    }

    @Override // defpackage.AbstractC0523In
    public boolean c(float f) {
        this.b = f;
        return this.f8595a.c(f);
    }

    @Override // defpackage.AbstractC0523In
    public long d(float f) {
        return this.f8595a.d(this.b - f);
    }
}
