package com.oculus.durableiap;

import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass117;
import android.app.job.JobParameters;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.auth.credentials.Credentials;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.job.NonRetryingJobService;
import java.util.concurrent.Callable;
import javax.inject.Provider;

public class DurableIAPJobService extends NonRetryingJobService {
    public static final String TAG = "DurableIAPJobService";
    @Inject
    public Provider<Credentials> mCredentialsProvider;
    @Inject
    @Eager
    public DurableIAPJobScheduler mDurableIAPJobScheduler;
    @Inject
    @Eager
    public DurableIAPStorage mDurableIAPStorage;

    public final boolean onStartJob(final JobParameters jobParameters) {
        OculusThreadExecutor.A00().mUiThreadExecutor.execute(new Runnable() {
            /* class com.oculus.durableiap.DurableIAPJobService.AnonymousClass1 */

            public final void run() {
                AnonymousClass0DC.A06(new Callable<Void>() {
                    /* class com.oculus.durableiap.DurableIAPStorage.AnonymousClass2 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX WARNING: Removed duplicated region for block: B:31:0x010a  */
                    /* JADX WARNING: Removed duplicated region for block: B:36:0x0115  */
                    /* JADX WARNING: Removed duplicated region for block: B:48:0x001b A[SYNTHETIC] */
                    @Override // java.util.concurrent.Callable
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Void call() throws java.lang.Exception {
                        /*
                        // Method dump skipped, instructions count: 303
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.durableiap.DurableIAPStorage.AnonymousClass2.call():java.lang.Object");
                    }
                }).A09(new AnonymousClass0D4<Void, Void>() {
                    /* class com.oculus.durableiap.DurableIAPJobService.AnonymousClass1.AnonymousClass1 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<Void> r6) throws Exception {
                        DurableIAPJobService durableIAPJobService;
                        JobParameters jobParameters;
                        String str;
                        Throwable runtimeException;
                        if (r6.A0K()) {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            durableIAPJobService = DurableIAPJobService.this;
                            jobParameters = jobParameters;
                            runtimeException = r6.A0F();
                        } else {
                            if (r6.A0I()) {
                                AnonymousClass1 r02 = AnonymousClass1.this;
                                durableIAPJobService = DurableIAPJobService.this;
                                jobParameters = jobParameters;
                                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_CANCELLED;
                            } else if (r6.A0J()) {
                                AnonymousClass1 r03 = AnonymousClass1.this;
                                DurableIAPJobService.this.jobFinished(jobParameters, false);
                                return null;
                            } else {
                                AnonymousClass1 r04 = AnonymousClass1.this;
                                durableIAPJobService = DurableIAPJobService.this;
                                jobParameters = jobParameters;
                                str = "Unexpected job state!";
                            }
                            runtimeException = new RuntimeException(str);
                        }
                        AnonymousClass0NO.A0B(DurableIAPJobService.TAG, "onJobFailed()", runtimeException);
                        if (durableIAPJobService.mCredentialsProvider.get() == null) {
                            durableIAPJobService.mDurableIAPJobScheduler.A01();
                        }
                        durableIAPJobService.jobFinished(jobParameters, false);
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
        this.mDurableIAPStorage = (DurableIAPStorage) AnonymousClass117.A00(442, r1);
        this.mCredentialsProvider = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r1);
        this.mDurableIAPJobScheduler = (DurableIAPJobScheduler) AnonymousClass117.A00(170, r1);
    }
}
