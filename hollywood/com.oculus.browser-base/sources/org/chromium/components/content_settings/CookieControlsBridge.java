package org.chromium.components.content_settings;

import J.N;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CookieControlsBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10833a;
    public EA b;

    public CookieControlsBridge(EA ea, WebContents webContents, BrowserContextHandle browserContextHandle) {
        this.b = ea;
        this.f10833a = N.Ma648rK8(this, webContents, browserContextHandle);
    }

    public final void onCookieBlockingStatusChanged(int i, int i2) {
        this.b.e(i, i2);
    }

    public final void onCookiesCountChanged(int i, int i2) {
        this.b.a(i, i2);
    }
}
