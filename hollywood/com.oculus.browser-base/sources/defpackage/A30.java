package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: A30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A30 implements AbstractC3997o30 {
    public final Map F = new HashMap();

    public static A30 f0(AbstractC1552Zj0 zj0) {
        A30 a30 = new A30();
        PN0 pn0 = new PN0(zj0);
        CoreImpl coreImpl = ((QW) zj0).G;
        pn0.F.f10037J = a30;
        pn0.G = new C4680s30(coreImpl, a30);
        pn0.g0();
        return a30;
    }

    @Override // defpackage.AbstractC3997o30
    public void C(String str, AbstractC1552Zj0 zj0) {
        C5870z30 z30 = (C5870z30) this.F.get(str);
        if (z30 != null) {
            AbstractC3313k30 a2 = z30.b.a();
            if (a2 == null) {
                zj0.close();
            } else {
                z30.f11719a.c(a2, zj0);
            }
        }
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.F.clear();
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.F.clear();
    }
}
