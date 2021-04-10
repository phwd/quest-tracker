package com.oculus.android;

import android.app.job.JobScheduler;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

public class JobSchedulerMethodAutoProvider extends AbstractProvider<JobScheduler> {
    @Override // javax.inject.Provider
    public JobScheduler get() {
        return AndroidModule.provideJobScheduler(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD(this));
    }
}
