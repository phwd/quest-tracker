package org.chromium.chrome.browser.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationService extends YY0 {
    public static final String H = NotificationService.class.getSimpleName();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            ((BroadcastReceiver) AbstractC2369eZ0.b(context, "org.chromium.chrome.browser.notifications.NotificationServiceImpl$Receiver")).onReceive(context, intent);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationService() {
        super(C1320Vp0.b, H);
        C5271va vaVar = AbstractC2369eZ0.f9859a;
    }
}
