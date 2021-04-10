package defpackage;

import org.chromium.base.ContextUtils;

/* renamed from: uN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5075uN implements AbstractC4905tN {
    @Override // defpackage.AbstractC4905tN
    public void T(B30 b30, KN kn) {
        if (AbstractC4310pu.a(ContextUtils.getApplicationContext())) {
            int i = AbstractC4224pN.o;
            IN.f8221a.b(new C4735sN(kn), b30);
            return;
        }
        int i2 = AbstractC4224pN.o;
        IN.f8221a.b(new C4565rN(kn), b30);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }
}
