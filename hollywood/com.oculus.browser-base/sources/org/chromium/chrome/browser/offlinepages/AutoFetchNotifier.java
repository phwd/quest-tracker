package org.chromium.chrome.browser.offlinepages;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutoFetchNotifier {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CompleteNotificationReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            int h = U20.h(intent, "notification_action", 5);
            if (h == 4 || h == 3) {
                AbstractC3364kK0.g("OfflinePages.AutoFetch.CompleteNotificationAction", h, 5);
                if (h == 4) {
                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(U20.n(intent, "org.chromium.chrome.browser.offlinepages.URL")));
                    intent2.putExtras(intent);
                    intent2.setPackage(context.getPackageName());
                    intent2.setFlags(268435456);
                    S20.y(intent2, null);
                }
            }
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class InProgressCancelReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int f10715a = 0;

        public void onReceive(Context context, Intent intent) {
            int h = U20.h(intent, "notification_action", 5);
            if (h == 2 || h == 3) {
                NU0.f8549a.n("offline_auto_fetch_user_cancel_action_in_progress", h);
                C1321Vq.b().i(new RunnableC3230jc());
                AutoFetchNotifier.a();
            }
        }
    }

    public static void a() {
        ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("OfflinePageAutoFetchInProgressNotification", 0);
        NU0.f8549a.m("offline_auto_fetch_showing_in_progress", false);
    }

    public static boolean autoFetchInProgressNotificationCanceled() {
        return NU0.f8549a.f("offline_auto_fetch_user_cancel_action_in_progress", 5) != 5;
    }

    public static void b(int i) {
        AbstractC3364kK0.g("OfflinePages.AutoFetch.InProgressNotificationAction", i, 5);
    }

    public static void cancellationComplete() {
        PU0 pu0 = NU0.f8549a;
        int f = pu0.f("offline_auto_fetch_user_cancel_action_in_progress", 5);
        if (f != 5) {
            b(f);
            pu0.l("offline_auto_fetch_user_cancel_action_in_progress");
        }
    }

    public static void showCompleteNotification(String str, String str2, String str3, int i, long j) {
        AbstractC2254ds0.c(j, 1, new C3060ic(str, i, j, str2, str3), Profile.b());
    }

    public static void showInProgressNotification(int i) {
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, InProgressCancelReceiver.class);
        intent.putExtra("com.android.browser.application_id", applicationContext.getPackageName());
        intent.putExtra("notification_action", 2);
        intent.setPackage(applicationContext.getPackageName());
        Intent intent2 = new Intent(applicationContext, InProgressCancelReceiver.class);
        intent2.putExtra("com.android.browser.application_id", applicationContext.getPackageName());
        intent2.putExtra("notification_action", 3);
        intent2.setPackage(applicationContext.getPackageName());
        Notification c = AbstractC3786mq0.a(true, "downloads").H(applicationContext.getResources().getQuantityString(R.plurals.f42800_resource_name_obfuscated_RES_2131820572, i)).r("OfflinePageAutoFetchNotification").o(-1).A(R.drawable.f29770_resource_name_obfuscated_RES_2131231017).l(0, applicationContext.getString(R.string.f48470_resource_name_obfuscated_RES_2131952164), PendingIntent.getBroadcast(applicationContext, 0, intent, 0)).K(PendingIntent.getBroadcast(applicationContext, 0, intent2, 0)).c();
        ((NotificationManager) applicationContext.getSystemService("notification")).notify("OfflinePageAutoFetchInProgressNotification", 0, c);
        AbstractC3102iq0.f10166a.b(14, c);
        b(0);
    }

    public static void updateInProgressNotificationCountIfShowing(int i) {
        if (i == 0) {
            if (NU0.f8549a.d("offline_auto_fetch_showing_in_progress", false)) {
                b(1);
            }
            a();
        } else if (NU0.f8549a.d("offline_auto_fetch_showing_in_progress", false)) {
            showInProgressNotification(i);
        }
    }
}
