package org.chromium.chrome.browser.notifications;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationServiceImpl$Receiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean z = intent.getIntExtra("notification_info_action_index", -1) != -1;
        Px1 a2 = Px1.a();
        Objects.requireNonNull(a2.b);
        a2.d = SystemClock.elapsedRealtime();
        a2.e = z;
        a2.f = false;
        a2.g = false;
        a2.h = false;
        a2.b("Click");
        Log.i(C1320Vp0.b, "Received a notification intent in the NotificationService's receiver.");
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("notification_id", intent.getStringExtra("notification_id"));
        persistableBundle.putInt("notification_type", intent.getIntExtra("notification_type", 0));
        persistableBundle.putString("notification_info_origin", intent.getStringExtra("notification_info_origin"));
        persistableBundle.putString("notification_info_scope", intent.getStringExtra("notification_info_scope"));
        persistableBundle.putString("notification_info_profile_id", intent.getStringExtra("notification_info_profile_id"));
        persistableBundle.putBoolean("notification_info_profile_incognito", intent.getBooleanExtra("notification_info_profile_incognito", false));
        persistableBundle.putInt("notification_info_action_index", intent.getIntExtra("notification_info_action_index", -1));
        persistableBundle.putString("notification_info_webapk_package", intent.getStringExtra("notification_info_webapk_package"));
        persistableBundle.putString("notification_action", intent.getAction());
        persistableBundle.putString("notification_reply", NotificationPlatformBridge.c(intent));
        persistableBundle.putLong("notification_job_scheduled_time_ms", SystemClock.elapsedRealtime());
        ((JobScheduler) context.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(21, new ComponentName(context, NotificationJobService.class)).setExtras(persistableBundle).setOverrideDeadline(0).build());
    }
}
