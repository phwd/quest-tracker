package defpackage;

/* renamed from: f30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2459f30 implements AbstractC2972i30 {
    public final C2288e30 F;

    public AbstractC2459f30(SA sa, AbstractC3426kk0 kk0) {
        this.F = new C2288e30(sa, kk0);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.F.Y(wl0);
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.F.G.close();
    }
}
