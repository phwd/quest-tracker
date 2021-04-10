package org.chromium.chrome.browser.app.send_tab_to_self;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SendTabToSelfNotificationReceiver extends BroadcastReceiver {
    public static Class getSendTabToSelfNotificationReciever() {
        return SendTabToSelfNotificationReceiver.class;
    }

    public void onReceive(Context context, Intent intent) {
        C5936zS0 zs0 = new C5936zS0(this, intent);
        C1321Vq.b().d(zs0);
        C1321Vq.b().c(true, zs0, null);
    }
}
