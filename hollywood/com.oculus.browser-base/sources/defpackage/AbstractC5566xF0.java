package defpackage;

import J.N;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: xF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5566xF0 {
    public static boolean a(Tab tab) {
        WebContents l;
        if (tab == null || tab.isNativePage() || (l = tab.l()) == null) {
            return false;
        }
        if (LR0.a(l) == 5) {
            return false;
        }
        PreviewsAndroidBridge a2 = PreviewsAndroidBridge.a();
        if (N.MMwFZLsG(a2.b, a2, l)) {
            return true;
        }
        return false;
    }
}
