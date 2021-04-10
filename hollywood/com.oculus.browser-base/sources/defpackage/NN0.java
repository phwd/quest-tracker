package defpackage;

import java.util.Objects;

/* renamed from: NN0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NN0 implements AbstractC3255jk0 {
    public final /* synthetic */ PN0 F;

    public NN0(PN0 pn0, MN0 mn0) {
        this.F = pn0;
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        PN0 pn0 = this.F;
        Objects.requireNonNull(pn0);
        C0942Pj0 pj0 = gj0.a().d;
        if (pj0.a(1)) {
            AbstractC3426kk0 kk0 = pn0.G;
            if (kk0 != null) {
                return kk0.g(gj0, new ON0(pn0));
            }
            pn0.F.close();
            return false;
        } else if (pj0.a(2)) {
            long j = pj0.f;
            AbstractC3255jk0 jk0 = (AbstractC3255jk0) pn0.I.get(Long.valueOf(j));
            if (jk0 == null) {
                return false;
            }
            pn0.I.remove(Long.valueOf(j));
            return jk0.b(gj0);
        } else {
            AbstractC3426kk0 kk02 = pn0.G;
            if (kk02 != null) {
                return kk02.b(gj0);
            }
            return false;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        AbstractC3426kk0 kk0 = this.F.G;
        if (kk0 != null) {
            kk0.close();
        }
    }
}
