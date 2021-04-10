package org.chromium.chrome.browser.tab;

import J.N;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TrustedCdn extends AbstractC0499Id1 {
    public final Tab G;
    public final long H = N.M1Q9lmqc(this);
    public Mn1 I;

    /* renamed from: J  reason: collision with root package name */
    public String f10775J;

    public TrustedCdn(Tab tab) {
        super(tab);
        this.G = tab;
        WindowAndroid i = tab.i();
        this.I = i != null ? (Mn1) Mn1.C.e(i.U) : null;
        tab.A(new Ln1(this));
    }

    public static String j(Tab tab) {
        Mn1 mn1;
        TrustedCdn trustedCdn = tab != null ? (TrustedCdn) tab.M().c(TrustedCdn.class) : null;
        if (trustedCdn == null || (mn1 = trustedCdn.I) == null || !mn1.h(trustedCdn.G) || LR0.a(trustedCdn.G.l()) == 5) {
            return null;
        }
        return trustedCdn.f10775J;
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
        N.M003oy2o(this.H, this);
        this.f10775J = null;
    }

    @Override // defpackage.AbstractC0499Id1
    public void e() {
        N.MM2LHRfv(this.H, this);
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        N.MyyZwXPU(this.H, this, webContents);
    }

    public final void setPublisherUrl(String str) {
        this.f10775J = str;
    }
}
