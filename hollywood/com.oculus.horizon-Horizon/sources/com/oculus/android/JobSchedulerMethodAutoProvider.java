package com.oculus.android;

import X.AnonymousClass0J3;
import X.C003108z;
import android.app.job.JobScheduler;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class JobSchedulerMethodAutoProvider extends AnonymousClass0J3<JobScheduler> {
    public final Object get() {
        return C003108z.A02(this).getSystemService("jobscheduler");
    }
}
