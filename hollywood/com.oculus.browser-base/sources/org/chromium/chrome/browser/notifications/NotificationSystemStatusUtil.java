package org.chromium.chrome.browser.notifications;

import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationSystemStatusUtil {
    public static int getAppNotificationStatus() {
        return new C0650Kp0(ContextUtils.getApplicationContext()).a() ? 2 : 3;
    }
}
