package com.facebook.papaya.client.platform;

import X.HB;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.os.PowerManager;
import com.google.common.base.Preconditions;

public final class PlatformJobScheduler$SchedulingGuard {
    public static final PlatformJobScheduler$SchedulingGuard A08 = new PlatformJobScheduler$SchedulingGuard();
    public int A00 = 0;
    public long A01;
    public ComponentName A02;
    public Context A03;
    public Long A04;
    public boolean A05;
    public boolean A06;
    public boolean A07;

    public final synchronized void A01() {
        ComponentName componentName;
        int i = this.A00;
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        Preconditions.checkState(z);
        int i2 = i - 1;
        this.A00 = i2;
        if (i2 == 0) {
            Context context = this.A03;
            if (context != null) {
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                Long l = this.A04;
                if (l == null || (componentName = this.A02) == null) {
                    jobScheduler.cancel(775184377);
                    PlatformLog.A00(HB.class, "Cancelled Papaya execution with job scheduler", new Object[0]);
                } else {
                    this.A04 = Long.valueOf(Math.max(HB.A00, l.longValue()));
                    JobInfo.Builder builder = new JobInfo.Builder(775184377, componentName);
                    builder.setRequiresCharging(this.A07);
                    if (this.A06) {
                        builder.setRequiredNetworkType(2);
                    } else {
                        builder.setRequiredNetworkType(1);
                    }
                    if (this.A05) {
                        builder.setRequiresDeviceIdle(true);
                    } else if (this.A01 > 0) {
                        if (((PowerManager) this.A03.getSystemService("power")).isInteractive()) {
                            this.A04 = Long.valueOf(Math.max(this.A01, this.A04.longValue()));
                        }
                        builder.setBackoffCriteria(this.A01, 0);
                        PersistableBundle persistableBundle = new PersistableBundle();
                        persistableBundle.putInt("check_idle", 1);
                        builder.setExtras(persistableBundle);
                    }
                    builder.setMinimumLatency(this.A04.longValue());
                    jobScheduler.schedule(builder.build());
                    PlatformLog.A00(HB.class, "Scheduled Papaya execution with job scheduler", new Object[0]);
                }
            }
            this.A03 = null;
            this.A02 = null;
            this.A04 = null;
        }
    }

    public static synchronized PlatformJobScheduler$SchedulingGuard A00() {
        PlatformJobScheduler$SchedulingGuard platformJobScheduler$SchedulingGuard;
        synchronized (PlatformJobScheduler$SchedulingGuard.class) {
            platformJobScheduler$SchedulingGuard = A08;
            synchronized (platformJobScheduler$SchedulingGuard) {
                platformJobScheduler$SchedulingGuard.A00++;
            }
        }
        return platformJobScheduler$SchedulingGuard;
    }
}
