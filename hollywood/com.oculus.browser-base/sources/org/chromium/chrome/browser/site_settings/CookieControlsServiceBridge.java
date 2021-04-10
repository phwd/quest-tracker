package org.chromium.chrome.browser.site_settings;

import J.N;
import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CookieControlsServiceBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10767a = N.MDQjbYOx(this);
    public View$OnClickListenerC2109d00 b;

    public CookieControlsServiceBridge(View$OnClickListenerC2109d00 d00) {
        this.b = d00;
    }

    public final void sendCookieControlsUIChanges(boolean z, int i) {
        View$OnClickListenerC2109d00 d00 = this.b;
        d00.f9738J = z;
        Iterator it = d00.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                C5458wf1 wf1 = (C5458wf1) uq0.next();
                wf1.f11559a.I.l(AbstractC5798yf1.l, i);
                wf1.f11559a.I.j(AbstractC5798yf1.j, z);
            } else {
                return;
            }
        }
    }
}
