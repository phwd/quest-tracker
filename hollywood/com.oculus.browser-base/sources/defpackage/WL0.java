package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: WL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WL0 extends Pr1 {
    public final Tab F;

    public WL0(Tab tab) {
        this.F = tab;
    }

    public static WL0 e(Tab tab) {
        WL0 wl0 = (WL0) tab.M().c(WL0.class);
        return wl0 == null ? (WL0) tab.M().e(WL0.class, new WL0(tab)) : wl0;
    }

    public void c() {
        WebContents l = this.F.l();
        if (l != null) {
            l.e0(null);
        }
        this.F.O(null, null);
    }
}
