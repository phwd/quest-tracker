package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0MD;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ultralight.Eager;
import com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager;
import com.oculus.horizon.vr_lifecycle.query.GraphQLVRLifecycleResponse;
import com.oculus.http.core.base.ApiError;
import javax.inject.Inject;

@TargetApi(21)
public class VRLifecycleJobService extends JobService {
    public static final String TAG = "VRLifecycleJobService";
    @Inject
    @Eager
    public VRLifecycleSessionManager mVRLifecycleSessionManager;

    private void onJobSucceed(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public static /* synthetic */ String access$300() {
        return TAG;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void markSessionEnd(final JobParameters jobParameters) {
        this.mVRLifecycleSessionManager.markSessionEndIfNotRecentAndInfoExists(new VRLifecycleSessionManager.MarkSessionEndCallback() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleJobService.AnonymousClass2 */

            @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
            public void onError(ApiError apiError) {
                AnonymousClass0MD.A09(VRLifecycleJobService.TAG, "onError(ApiError = %s)", apiError.getMessage());
                VRLifecycleJobService.this.onJobFailed(jobParameters, apiError);
            }

            @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
            public void onNoSessionInfo() {
                VRLifecycleJobService.this.jobFinished(jobParameters, false);
            }

            @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
            public void onSuccess(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse) {
                VRLifecycleJobService.this.jobFinished(jobParameters, false);
            }

            @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
            public void onTooRecent() {
                VRLifecycleJobService.this.jobFinished(jobParameters, false);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onJobFailed(JobParameters jobParameters, Exception exc) {
        AnonymousClass0MD.A07(TAG, "onJobFailed()", exc);
        jobFinished(jobParameters, true);
    }

    public static final void _UL_injectMe(Context context, VRLifecycleJobService vRLifecycleJobService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), vRLifecycleJobService);
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r0, VRLifecycleJobService vRLifecycleJobService) {
        vRLifecycleJobService.mVRLifecycleSessionManager = VRLifecycleSessionManager._UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULSEP_ACCESS_METHOD(r0);
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
        postOnUiThread(new Runnable() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleJobService.AnonymousClass1 */

            public void run() {
                if (jobParameters.getJobId() == 1828726824) {
                    VRLifecycleJobService.this.markSessionEnd(jobParameters);
                } else {
                    VRLifecycleJobService.this.onJobFailed(jobParameters, new RuntimeException("Unknown job id."));
                }
            }
        });
        return true;
    }
}
