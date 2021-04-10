package defpackage;

import java.util.concurrent.Executor;

/* renamed from: hc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2889hc implements LN0 {
    public final LN0 F;
    public final Executor G;
    public final Exception H = new Exception("AutocloseableRouter allocated at:");
    public boolean I;

    public C2889hc(SA sa, LN0 ln0) {
        this.F = ln0;
        this.G = AbstractC3880nM.a(sa);
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        return this.F.b(gj0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        this.F.close();
        this.I = true;
    }

    @Override // java.lang.Object
    public void finalize() {
        if (this.I) {
            super.finalize();
        } else {
            this.G.execute(new RunnableC2718gc(this));
            throw new IllegalStateException("Warning: Router objects should be explicitly closed when no longer required otherwise you may leak handles.", this.H);
        }
    }

    @Override // defpackage.AbstractC3426kk0
    public boolean g(C2740gj0 gj0, AbstractC3255jk0 jk0) {
        return this.F.g(gj0, jk0);
    }

    @Override // defpackage.RW
    public PW x() {
        return (AbstractC1552Zj0) this.F.x();
    }
}
