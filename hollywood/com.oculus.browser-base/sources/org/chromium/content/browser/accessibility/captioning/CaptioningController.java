package org.chromium.content.browser.accessibility.captioning;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CaptioningController {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC5707y51 f10922a;
    public long b;

    public CaptioningController(WebContents webContents) {
        if (C3431km.f10301a == null) {
            C3431km.f10301a = new C3431km();
        }
        this.f10922a = C3431km.f10301a;
        this.b = N.MX95jWaj(this, webContents);
    }

    public final void onDestroy() {
        this.b = 0;
    }

    public final void onRenderProcessChange() {
        C3431km kmVar = (C3431km) this.f10922a;
        if (!kmVar.b.b()) {
            kmVar.b();
        }
        kmVar.b.c(this);
    }
}
