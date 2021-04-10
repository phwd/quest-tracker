package defpackage;

/* renamed from: e90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2306e90 extends AbstractC2477f90 {
    public final Object F;
    public final AbstractC2819h90 G;
    public final AbstractC2136d90 H;

    public C2306e90(AbstractC2819h90 h90, Object obj, AbstractC2136d90 d90) {
        this.G = h90;
        this.F = obj;
        this.H = d90;
    }

    @Override // defpackage.AbstractC2477f90, defpackage.AbstractC2648g90
    public void a(AbstractC2819h90 h90, int i, int i2) {
        this.H.a(this.G, this.F, i, i2);
    }

    @Override // defpackage.AbstractC2477f90, defpackage.AbstractC2648g90
    public void f(AbstractC2819h90 h90, int i, int i2) {
        this.H.c(this.G, this.F, i, i2);
    }

    @Override // defpackage.AbstractC2648g90
    public void l(AbstractC2819h90 h90, int i, int i2, Object obj) {
        Void r5 = (Void) obj;
        this.H.b(this.G, this.F, i, i2);
    }
}
