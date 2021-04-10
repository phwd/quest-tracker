package defpackage;

import J.N;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: xQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5592xQ0 {
    public boolean a() {
        try {
            if (LocaleManager.getInstance().b() || !AbstractC4226pO.a() || !N.M09VlOh_("OmniboxSearchEngineLogo")) {
                return false;
            }
            return true;
        } catch (SecurityException unused) {
            AbstractC1220Ua0.a("SearchLogoUtils", "Security exception thrown by failed IPC, see crbug.com/1027709", new Object[0]);
            return false;
        }
    }

    public boolean b(boolean z) {
        return !z && a() && ((BrowserStartupControllerImpl) AbstractC4280pk.a()).f();
    }
}
