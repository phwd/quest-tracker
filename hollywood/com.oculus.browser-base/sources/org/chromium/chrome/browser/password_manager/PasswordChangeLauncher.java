package org.chromium.chrome.browser.password_manager;

import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordChangeLauncher {
    public static void start(WindowAndroid windowAndroid, String str, String str2) {
        ChromeActivity chromeActivity = (ChromeActivity) windowAndroid.s0().get();
        if (chromeActivity != null) {
            C3233jd f = C3404kd.f();
            C3404kd kdVar = f.f10219a;
            kdVar.d = str;
            kdVar.f10291a.put("PASSWORD_CHANGE_USERNAME", str2);
            f.f10219a.f10291a.put("INTENT", "PASSWORD_CHANGE");
            f.f10219a.f10291a.put("START_IMMEDIATELY", Boolean.TRUE);
            AbstractC4088od.b(chromeActivity, f.f10219a);
        }
    }
}
