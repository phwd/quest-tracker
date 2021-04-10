package com.facebook.analytics2.logger;

import X.AnonymousClass7D;
import X.AnonymousClass7j;
import X.AnonymousClass82;
import X.AnonymousClass83;
import X.C0139Dd;
import X.C0691fS;
import X.Er;
import X.fE;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.facebook.common.build.BuildConstants;

public class LollipopUploadService extends JobService {
    public AnonymousClass83 A00;

    public final int onStartCommand(Intent intent, int i, int i2) {
        AnonymousClass83 r1 = this.A00;
        Er.A00(r1);
        return r1.A02(intent, new AnonymousClass82(this, i2));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        AnonymousClass83 r1 = this.A00;
        Er.A00(r1);
        r1.A03(jobParameters.getJobId());
        return true;
    }

    public final void onCreate() {
        this.A00 = AnonymousClass83.A00(this);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        if (extras == null) {
            C0139Dd.A0A("PostLolliopUploadService", "Job with no build ID, cancelling job");
        } else {
            try {
                int i = extras.getInt("__VERSION_CODE", 0);
                if (BuildConstants.getBuildID() != i) {
                    C0139Dd.A0G("PostLolliopUploadService", "Job with old build ID: %d, cancelling job", Integer.valueOf(i));
                } else {
                    try {
                        AnonymousClass83 r4 = this.A00;
                        Er.A00(r4);
                        r4.A04(jobParameters.getJobId(), jobParameters.getExtras().getString("action"), new AnonymousClass7j(new C0691fS(new Bundle(jobParameters.getExtras()))), new fE(this, jobParameters));
                        return true;
                    } catch (AnonymousClass7D e) {
                        C0139Dd.A0M("PostLolliopUploadService", "Misunderstood job service extras: %s", e);
                        return false;
                    }
                }
            } catch (Exception e2) {
                C0139Dd.A0S("PostLolliopUploadService", e2, "Corrupt bundle, cancelling job");
            }
        }
        C0139Dd.A0G("PostLolliopUploadService", "Stale job parameters, cancelling jobId: %d", Integer.valueOf(jobParameters.getJobId()));
        return false;
    }
}
