package com.oculus.android;

import android.app.job.JobScheduler;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class JobSchedulerMethodAutoProvider extends AbstractProvider<JobScheduler> {
    @Override // javax.inject.Provider
    public JobScheduler get() {
        return AndroidModule.provideJobScheduler(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
