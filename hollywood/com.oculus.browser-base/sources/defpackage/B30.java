package defpackage;

/* renamed from: B30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B30 implements RW {
    public final AbstractC1552Zj0 F;

    public B30(AbstractC1552Zj0 zj0) {
        this.F = zj0;
    }

    /* renamed from: Y */
    public AbstractC1552Zj0 x() {
        return this.F.N();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.F.close();
    }
}
