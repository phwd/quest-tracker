package com.oculus.alpenglow.remotewipe;

import X.AnonymousClass0Cg;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0P6;
import X.AnonymousClass0P8;
import X.AnonymousClass0R7;
import X.AnonymousClass0bg;
import X.C03100bZ;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.enums.GraphQLRemoteWipeStatus;
import com.oculus.alpenglow.remotewipe.CurrentDevice;
import com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryImpl;
import com.oculus.alpenglow.remotewipe.RemoteWipeStatusQueryResponse;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.remotewipe.Source;
import com.oculus.remotewipe.WipeRequester;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RemoteWipePollingJobService extends JobService {
    public static final int MAX_RETRIES = 4;
    public static final String TAG = "RemoteWipePollingJobService";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public class RemoteWipeRequestPollingRunnable implements Runnable {
        public final int mCurrentAttemptNum;
        public final JobParameters mJobParameters;

        public RemoteWipeRequestPollingRunnable(JobParameters jobParameters, int i) {
            this.mCurrentAttemptNum = i;
            this.mJobParameters = jobParameters;
        }

        public final void run() {
            AnonymousClass0bg A00;
            RemoteWipeStatusQueryImpl.Builder builder = new RemoteWipeStatusQueryImpl.Builder();
            if (builder.mRequest != null) {
                AnonymousClass0NK.A03("RemoteWipeStatusQueryResponse", "Incorrect usage of query builder. Query should only be built once.");
                A00 = builder.mRequest;
            } else {
                C03100bZ r3 = new C03100bZ(RemoteWipeStatusQueryResponseImpl.class, RemoteWipeStatusQueryResponseImpl.TYPE_TAG, RemoteWipeStatusQueryResponseImpl.TREE_SHAPE_HASH_FOR_TESTS, RemoteWipeStatusQueryResponseImpl.TREE_SHAPE_HASH_FOR_TESTS);
                r3.A00(builder.mParams);
                A00 = AnonymousClass0bg.A00(r3);
                builder.mRequest = A00;
            }
            AnonymousClass0R7 r4 = RemoteWipePollingJobService.this._UL_mInjectionContext;
            ((AnonymousClass0P6) AnonymousClass0Lh.A03(0, 51, r4)).A2X(A00, new AnonymousClass0Cg<AnonymousClass0P8<RemoteWipeStatusQueryResponse>>() {
                /* class com.oculus.alpenglow.remotewipe.RemoteWipePollingJobService.RemoteWipeRequestPollingRunnable.AnonymousClass1 */

                @Override // X.AnonymousClass0Cg
                public final void onFailure(Throwable th) {
                    AnonymousClass0NK.A04(RemoteWipePollingJobService.TAG, "error calling graphql api", th);
                    RemoteWipeRequestPollingRunnable remoteWipeRequestPollingRunnable = RemoteWipeRequestPollingRunnable.this;
                    int i = remoteWipeRequestPollingRunnable.mCurrentAttemptNum;
                    if (i <= 4) {
                        RemoteWipePollingJobService remoteWipePollingJobService = RemoteWipePollingJobService.this;
                        ((OculusThreadExecutor) AnonymousClass0Lh.A03(2, 34, remoteWipePollingJobService._UL_mInjectionContext)).mExecutorService.A7Y(new RemoteWipeRequestPollingRunnable(remoteWipeRequestPollingRunnable.mJobParameters, i + 1), (long) 5000, TimeUnit.MILLISECONDS);
                    }
                    RemoteWipeRequestPollingRunnable remoteWipeRequestPollingRunnable2 = RemoteWipeRequestPollingRunnable.this;
                    JobParameters jobParameters = remoteWipeRequestPollingRunnable2.mJobParameters;
                    if (jobParameters != null && remoteWipeRequestPollingRunnable2.mCurrentAttemptNum > 4) {
                        RemoteWipePollingJobService.this.jobFinished(jobParameters, false);
                    }
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // X.AnonymousClass0Cg
                public final void onSuccess(AnonymousClass0P8<RemoteWipeStatusQueryResponse> r4) {
                    GraphQLRemoteWipeStatus graphQLRemoteWipeStatus;
                    RemoteWipeStatusQueryResponse A4R;
                    RemoteWipeStatusQueryResponse.Me A40;
                    RemoteWipeStatusQueryResponse.Me.HwmDevice A3j;
                    CurrentDevice A15;
                    CurrentDevice.PersistedConfig A4F;
                    AnonymousClass0P8<RemoteWipeStatusQueryResponse> r42 = r4;
                    if (r42 == null || (A4R = r42.A4R()) == null || (A40 = A4R.A40()) == null || (A3j = A40.A3j()) == null || (A15 = A3j.A15()) == null || (A4F = A15.A4F()) == null) {
                        graphQLRemoteWipeStatus = null;
                    } else {
                        graphQLRemoteWipeStatus = A4F.A4O();
                    }
                    if (graphQLRemoteWipeStatus == GraphQLRemoteWipeStatus.PENDING) {
                        RemoteWipeManager.A00((RemoteWipeManager) AnonymousClass0Lh.A03(1, 14, RemoteWipePollingJobService.this._UL_mInjectionContext), new WipeRequester(Source.COLD_START));
                    }
                    RemoteWipeRequestPollingRunnable remoteWipeRequestPollingRunnable = RemoteWipeRequestPollingRunnable.this;
                    JobParameters jobParameters = remoteWipeRequestPollingRunnable.mJobParameters;
                    if (jobParameters != null) {
                        RemoteWipePollingJobService.this.jobFinished(jobParameters, false);
                    }
                }
            }, (OculusThreadExecutor) AnonymousClass0Lh.A03(2, 34, r4));
        }
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        ((OculusThreadExecutor) AnonymousClass0Lh.A03(2, 34, this._UL_mInjectionContext)).execute(new RemoteWipeRequestPollingRunnable(jobParameters, 1));
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(3, AnonymousClass0Lh.get(this));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
