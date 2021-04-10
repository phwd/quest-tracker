package defpackage;

/* renamed from: j30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3142j30 implements AbstractC3426kk0 {
    public final SA F;
    public final AbstractC3313k30 G;

    public AbstractC3142j30(SA sa, AbstractC3313k30 k30) {
        this.F = sa;
        this.G = k30;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        this.G.close();
    }
}
