package com.oculus.antipiracy;

import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.antipiracy.OculusDeveloperQueryResponse;
import com.oculus.executors.ExecutorsModule;
import com.oculus.executors.ForUiThread;
import java.util.concurrent.Callable;

public class AntiPiracyJobService extends JobService {
    public static final String TAG = "AntiPiracyJobService";
    @Inject
    @Eager
    public AntiPiracyManager mAntiPiracyManager;
    @Inject
    @Eager
    @ForUiThread
    public Handler mUiHandler;

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final boolean onStartJob(final JobParameters jobParameters) {
        this.mUiHandler.post(new Runnable() {
            /* class com.oculus.antipiracy.AntiPiracyJobService.AnonymousClass1 */

            public final void run() {
                AnonymousClass0DC.A06(new Callable<Void>() {
                    /* class com.oculus.antipiracy.AntiPiracyJobService.AnonymousClass1.AnonymousClass2 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    @Override // java.util.concurrent.Callable
                    public final Void call() throws Exception {
                        OculusDeveloperQueryResponse A02;
                        OculusDeveloperQueryResponse.OculusDeveloperStatus oculusDeveloperStatus;
                        if (!AntiPiracyJobService.this.mAntiPiracyManager.A04() || (A02 = AntiPiracyJobService.this.mAntiPiracyManager.A02()) == null || (oculusDeveloperStatus = A02.mOculusDeveloperStatus) == null || !oculusDeveloperStatus.is_oculus_developer_blocked) {
                            return null;
                        }
                        AntiPiracyJobService.this.mAntiPiracyManager.A03();
                        return null;
                    }
                }).A09(new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.antipiracy.AntiPiracyJobService.AnonymousClass1.AnonymousClass1 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<Void> r6) throws Exception {
                        AntiPiracyJobService antiPiracyJobService;
                        JobParameters jobParameters;
                        String str;
                        Throwable runtimeException;
                        if (r6.A0J()) {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            AntiPiracyJobService.this.jobFinished(jobParameters, false);
                            return null;
                        }
                        if (r6.A0K()) {
                            AnonymousClass1 r02 = AnonymousClass1.this;
                            antiPiracyJobService = AntiPiracyJobService.this;
                            jobParameters = jobParameters;
                            runtimeException = r6.A0F();
                        } else {
                            if (r6.A0I()) {
                                AnonymousClass1 r03 = AnonymousClass1.this;
                                antiPiracyJobService = AntiPiracyJobService.this;
                                jobParameters = jobParameters;
                                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_CANCELLED;
                            } else {
                                AnonymousClass1 r04 = AnonymousClass1.this;
                                antiPiracyJobService = AntiPiracyJobService.this;
                                jobParameters = jobParameters;
                                str = "Unexpected job state!";
                            }
                            runtimeException = new RuntimeException(str);
                        }
                        AnonymousClass0NO.A0B(AntiPiracyJobService.TAG, "onJobFailed()", runtimeException);
                        antiPiracyJobService.jobFinished(jobParameters, false);
                        return null;
                    }
                });
            }
        });
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        AnonymousClass0J2 r1 = AnonymousClass0J2.get(this);
        this.mAntiPiracyManager = AntiPiracyManager.A00(r1);
        this.mUiHandler = ExecutorsModule.A00(r1);
    }
}
