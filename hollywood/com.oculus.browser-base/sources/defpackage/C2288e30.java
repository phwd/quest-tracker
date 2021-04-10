package defpackage;

/* renamed from: e30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2288e30 implements AbstractC2801h30, AbstractC0543Ix {
    public final SA F;
    public final AbstractC3426kk0 G;
    public AbstractC0543Ix H;
    public int I;

    public C2288e30(SA sa, AbstractC3426kk0 kk0) {
        this.F = sa;
        this.G = kk0;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        AbstractC0543Ix ix = this.H;
        if (ix != null) {
            ix.Y(wl0);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.G.close();
    }
}
