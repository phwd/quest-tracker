package com.oculus.android;

import X.AbstractC0097Hv;
import X.C00208d;
import android.app.job.JobScheduler;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class JobSchedulerMethodAutoProvider extends AbstractC0097Hv<JobScheduler> {
    public final Object get() {
        return C00208d.A02(this).getSystemService("jobscheduler");
    }
}
