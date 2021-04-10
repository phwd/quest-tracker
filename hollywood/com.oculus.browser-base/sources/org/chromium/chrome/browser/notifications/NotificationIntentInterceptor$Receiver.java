package org.chromium.chrome.browser.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NotificationIntentInterceptor$Receiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_INTENT_TYPE", -1);
        int intExtra2 = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_NOTIFICATION_TYPE", -1);
        long longExtra = intent.getLongExtra("notifications.NotificationIntentInterceptor.EXTRA_CREATE_TIME", -1);
        if (intExtra == 0) {
            Objects.requireNonNull(AbstractC3102iq0.f10166a);
            if (intExtra2 != -1) {
                AbstractC3364kK0.g("Mobile.SystemNotification.Content.Click", intExtra2, 28);
                if (intExtra2 == 0) {
                    AbstractC3535lK0.a("Mobile.SystemNotification.Content.Click.Downloads_Files");
                }
                C3273jq0.d("Mobile.SystemNotification.Content.Click.Age", longExtra);
                if (intExtra2 == 15) {
                    C3273jq0.d("Mobile.SystemNotification.Content.Click.Age.SendTabToSelf", longExtra);
                } else if (intExtra2 == 17) {
                    C3273jq0.d("Mobile.SystemNotification.Content.Click.Age.ClickToCall", longExtra);
                } else if (intExtra2 == 18) {
                    C3273jq0.d("Mobile.SystemNotification.Content.Click.Age.SharedClipboard", longExtra);
                }
            }
        } else if (intExtra == 1) {
            int intExtra3 = intent.getIntExtra("notifications.NotificationIntentInterceptor.EXTRA_ACTION_TYPE", -1);
            Objects.requireNonNull(AbstractC3102iq0.f10166a);
            if (intExtra3 != -1) {
                AbstractC3364kK0.g("Mobile.SystemNotification.Action.Click", intExtra3, 16);
                C3273jq0.d("Mobile.SystemNotification.Action.Click.Age", longExtra);
                if (intExtra2 == 15) {
                    C3273jq0.d("Mobile.SystemNotification.Action.Click.Age.SendTabToSelf", longExtra);
                } else if (intExtra2 == 17) {
                    C3273jq0.d("Mobile.SystemNotification.Action.Click.Age.ClickToCall", longExtra);
                } else if (intExtra2 == 18) {
                    C3273jq0.d("Mobile.SystemNotification.Action.Click.Age.SharedClipboard", longExtra);
                }
            }
        } else if (intExtra == 2) {
            Objects.requireNonNull(AbstractC3102iq0.f10166a);
            if (intExtra2 != -1) {
                AbstractC3364kK0.g("Mobile.SystemNotification.Dismiss", intExtra2, 28);
                C3273jq0.d("Mobile.SystemNotification.Dismiss.Age", longExtra);
                if (intExtra2 == 15) {
                    C3273jq0.d("Mobile.SystemNotification.Dismiss.Age.SendTabToSelf", longExtra);
                } else if (intExtra2 == 17) {
                    C3273jq0.d("Mobile.SystemNotification.Dismiss.Age.ClickToCall", longExtra);
                } else if (intExtra2 == 18) {
                    C3273jq0.d("Mobile.SystemNotification.Dismiss.Age.SharedClipboard", longExtra);
                }
            }
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("notifications.NotificationIntentInterceptor.EXTRA_PENDING_INTENT");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                AbstractC1220Ua0.a("IntentInterceptor", "The PendingIntent to fire is canceled.", new Object[0]);
                AbstractC0754Mh1.f8495a.b(e);
            }
        }
    }
}
