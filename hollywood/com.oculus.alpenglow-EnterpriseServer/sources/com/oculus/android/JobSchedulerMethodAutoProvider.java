package com.oculus.android;

import X.AnonymousClass0Li;
import X.C01340Gg;
import android.app.job.JobScheduler;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class JobSchedulerMethodAutoProvider extends AnonymousClass0Li<JobScheduler> {
    public final Object get() {
        return C01340Gg.A02(this).getSystemService("jobscheduler");
    }
}
