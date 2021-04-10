package org.chromium.chrome.browser.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.ApplicationStatus;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountsChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.accounts.LOGIN_ACCOUNTS_CHANGED".equals(intent.getAction())) {
            if (ApplicationStatus.hasVisibleActivities()) {
                Y1 y1 = new Y1();
                C1321Vq.b().d(y1);
                C1321Vq.b().c(true, y1, null);
                return;
            }
            C4072oW0.f10556a.b.m("prefs_sync_accounts_changed", true);
        }
    }
}
