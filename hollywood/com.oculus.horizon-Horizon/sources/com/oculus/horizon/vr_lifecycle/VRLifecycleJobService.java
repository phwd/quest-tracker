package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
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

    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        this.mVRLifecycleSessionManager = (VRLifecycleSessionManager) AnonymousClass117.A00(8, AnonymousClass0J2.get(this));
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        jobParameters.getJobId();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            /* class com.oculus.horizon.vr_lifecycle.VRLifecycleJobService.AnonymousClass1 */

            public final void run() {
                if (jobParameters.getJobId() == 1828726824) {
                    VRLifecycleJobService vRLifecycleJobService = VRLifecycleJobService.this;
                    vRLifecycleJobService.mVRLifecycleSessionManager.A02(new VRLifecycleSessionManager.MarkSessionEndCallback(jobParameters) {
                        /* class com.oculus.horizon.vr_lifecycle.VRLifecycleJobService.AnonymousClass2 */
                        public final /* synthetic */ JobParameters val$params;

                        {
                            this.val$params = r2;
                        }

                        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                        public final void A5z(ApiError apiError) {
                            AnonymousClass0NO.A0E(VRLifecycleJobService.TAG, "onError(ApiError = %s)", apiError.getMessage());
                            VRLifecycleJobService vRLifecycleJobService = VRLifecycleJobService.this;
                            JobParameters jobParameters = this.val$params;
                            AnonymousClass0NO.A0B(VRLifecycleJobService.TAG, "onJobFailed()", apiError);
                            vRLifecycleJobService.jobFinished(jobParameters, true);
                        }

                        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                        public final void A6L() {
                            VRLifecycleJobService.this.jobFinished(this.val$params, false);
                        }

                        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                        public final void A75(GraphQLVRLifecycleResponse graphQLVRLifecycleResponse) {
                            VRLifecycleJobService.this.jobFinished(this.val$params, false);
                        }

                        @Override // com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager.MarkSessionEndCallback
                        public final void A76() {
                            VRLifecycleJobService.this.jobFinished(this.val$params, false);
                        }
                    });
                    return;
                }
                VRLifecycleJobService vRLifecycleJobService2 = VRLifecycleJobService.this;
                JobParameters jobParameters = jobParameters;
                AnonymousClass0NO.A0B(VRLifecycleJobService.TAG, "onJobFailed()", new RuntimeException("Unknown job id."));
                vRLifecycleJobService2.jobFinished(jobParameters, true);
            }
        });
        return true;
    }
}
