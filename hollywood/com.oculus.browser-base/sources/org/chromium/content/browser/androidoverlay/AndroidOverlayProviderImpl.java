package org.chromium.content.browser.androidoverlay;

import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidOverlayProviderImpl implements A5 {
    public static final /* synthetic */ int F = 0;
    public int G;
    public Runnable H = new B5(this);

    public static boolean areOverlaysSupported() {
        return true;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.A5
    public void e(B30 b30, AbstractC3831n5 n5Var, C5875z5 z5Var) {
        Object obj = ThreadUtils.f10596a;
        int i = this.G;
        if (i >= 1) {
            C5365w5 w5Var = (C5365w5) n5Var;
            w5Var.f0();
            w5Var.close();
            return;
        }
        this.G = i + 1;
        DialogOverlayImpl dialogOverlayImpl = new DialogOverlayImpl(n5Var, z5Var, this.H, false);
        int i2 = AbstractC3660m5.b;
        M5.f8459a.b(dialogOverlayImpl, b30);
    }
}
