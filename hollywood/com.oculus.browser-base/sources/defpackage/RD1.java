package defpackage;

/* renamed from: RD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RD1 extends MD1 {
    public final transient int H;
    public final transient int I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ MD1 f8818J;

    public RD1(MD1 md1, int i, int i2) {
        this.f8818J = md1;
        this.H = i;
        this.I = i2;
    }

    @Override // defpackage.PD1
    public final Object[] b() {
        return this.f8818J.b();
    }

    @Override // defpackage.PD1
    public final int c() {
        return this.f8818J.c() + this.H;
    }

    @Override // defpackage.PD1
    public final int e() {
        return this.f8818J.c() + this.H + this.I;
    }

    @Override // defpackage.MD1
    /* renamed from: f */
    public final MD1 subList(int i, int i2) {
        DD1.c(i, i2, this.I);
        MD1 md1 = this.f8818J;
        int i3 = this.H;
        return (MD1) md1.subList(i + i3, i2 + i3);
    }

    @Override // java.util.List
    public final Object get(int i) {
        DD1.a(i, this.I);
        return this.f8818J.get(i + this.H);
    }

    public final int size() {
        return this.I;
    }
}
