package defpackage;

import java.util.concurrent.Executor;

/* renamed from: ON0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ON0 implements AbstractC3255jk0 {
    public boolean F;
    public final /* synthetic */ PN0 G;

    public ON0(PN0 pn0) {
        this.G = pn0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        this.F = true;
        return this.G.F.b(gj0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        this.G.F.close();
    }

    @Override // java.lang.Object
    public void finalize() {
        PN0 pn0;
        Executor executor;
        if (!this.F && (executor = (pn0 = this.G).f8687J) != null) {
            executor.execute(new MN0(pn0));
        }
        super.finalize();
    }
}
