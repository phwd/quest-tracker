package com.oculus.config.updater;

import X.AbstractC06640p5;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ultralight.Eager;
import com.oculus.util.WakeupUtil;
import javax.inject.Inject;

@TargetApi(21)
public class ConfigUpdaterJobService extends JobService {
    public static final String TAG = "ConfigUpdaterJobService";
    @Inject
    @Eager
    public ConfigUpdater mConfigUpdater;

    private void onFetchSucceed(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onFetchFailed(JobParameters jobParameters, Exception exc) {
        AnonymousClass0NO.A0B(TAG, "fetch failed", exc);
        jobFinished(jobParameters, true);
    }

    public static final void _UL_injectMe(Context context, ConfigUpdaterJobService configUpdaterJobService) {
        _UL_staticInjectMe(AnonymousClass0J2.get(context), configUpdaterJobService);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, ConfigUpdaterJobService configUpdaterJobService) {
        configUpdaterJobService.mConfigUpdater = ConfigUpdater._UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_ACCESS_METHOD(r0);
    }

    public static void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        jobParameters.getJobId();
        WakeupUtil.A00(this, WakeupUtil.Action.FETCH_CONFIG);
        postOnUiThread(new Runnable() {
            /* class com.oculus.config.updater.ConfigUpdaterJobService.AnonymousClass1 */

            public void run() {
                ConfigUpdaterJobService.this.mConfigUpdater.fetchAsync().A09(new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.config.updater.ConfigUpdaterJobService.AnonymousClass1.AnonymousClass1 */

                    @Override // X.AnonymousClass0D4
                    public Void then(AnonymousClass0DC<Void> r5) throws Exception {
                        if (r5.A0K()) {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            ConfigUpdaterJobService.this.onFetchFailed(jobParameters, r5.A0F());
                            return null;
                        }
                        AnonymousClass1 r02 = AnonymousClass1.this;
                        ConfigUpdaterJobService.this.jobFinished(jobParameters, false);
                        return null;
                    }
                });
            }
        });
        return true;
    }
}
