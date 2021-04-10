package com.oculus.android;

import X.AbstractC0029Ba;
import X.IX;
import android.app.job.JobScheduler;
import android.content.Context;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class JobSchedulerMethodAutoProvider extends AbstractC0029Ba<JobScheduler> {
    public final Object get() {
        return ((Context) IX.A00(9, this)).getSystemService("jobscheduler");
    }
}
