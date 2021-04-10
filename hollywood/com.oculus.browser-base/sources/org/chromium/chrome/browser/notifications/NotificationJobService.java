package org.chromium.chrome.browser.notifications;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationJobService extends JobService {
    public String F = "Jp0";
    public C0589Jp0 G;

    public NotificationJobService() {
        C5271va vaVar = AbstractC2369eZ0.f9859a;
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        C0589Jp0 jp0 = (C0589Jp0) AbstractC2369eZ0.b(a2, this.F);
        this.G = jp0;
        jp0.b(this);
        super.attachBaseContext(a2);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        Objects.requireNonNull(this.G);
        PersistableBundle extras = jobParameters.getExtras();
        extras.putLong("notification_job_started_time_ms", SystemClock.elapsedRealtime());
        if (!extras.containsKey("notification_id") || !extras.containsKey("notification_info_origin")) {
            return false;
        }
        Intent intent = new Intent(extras.getString("notification_action"));
        intent.putExtras(new Bundle(extras));
        Object obj = ThreadUtils.f10596a;
        C1320Vp0.c(intent);
        return false;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return this.G.a();
    }
}
