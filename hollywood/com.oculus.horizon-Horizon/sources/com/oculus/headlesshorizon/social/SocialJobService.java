package com.oculus.headlesshorizon.social;

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
import com.oculus.horizon.api.common.user.User;
import com.oculus.job.NonRetryingJobService;
import javax.inject.Provider;

public class SocialJobService extends NonRetryingJobService {
    public static final String TAG = "SocialJobService";
    @Inject
    public Provider<Credentials> mCredentials;
    @Inject
    @Eager
    public MeManager mMeManager;
    @Inject
    @Eager
    public SocialJobScheduler mSocialJobScheduler;

    public final boolean onStartJob(final JobParameters jobParameters) {
        OculusThreadExecutor.A00().mUiThreadExecutor.execute(new Runnable() {
            /* class com.oculus.headlesshorizon.social.SocialJobService.AnonymousClass1 */

            public final void run() {
                SocialJobService.this.mMeManager.A00().A09(new AnonymousClass0D4<User, Void>() {
                    /* class com.oculus.headlesshorizon.social.SocialJobService.AnonymousClass1.AnonymousClass1 */

                    /* Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                    @Override // X.AnonymousClass0D4
                    public final Void then(AnonymousClass0DC<User> r6) throws Exception {
                        SocialJobService socialJobService;
                        JobParameters jobParameters;
                        String str;
                        Throwable runtimeException;
                        if (r6.A0K()) {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            socialJobService = SocialJobService.this;
                            jobParameters = jobParameters;
                            runtimeException = r6.A0F();
                        } else {
                            if (r6.A0I()) {
                                AnonymousClass1 r02 = AnonymousClass1.this;
                                socialJobService = SocialJobService.this;
                                jobParameters = jobParameters;
                                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_CANCELLED;
                            } else if (r6.A0J()) {
                                AnonymousClass1 r03 = AnonymousClass1.this;
                                SocialJobService.this.jobFinished(jobParameters, false);
                                return null;
                            } else {
                                AnonymousClass1 r04 = AnonymousClass1.this;
                                socialJobService = SocialJobService.this;
                                jobParameters = jobParameters;
                                str = "Unexpected job state!";
                            }
                            runtimeException = new RuntimeException(str);
                        }
                        AnonymousClass0NO.A0B(SocialJobService.TAG, "onJobFailed()", runtimeException);
                        if (socialJobService.mCredentials.get() == null) {
                            socialJobService.mSocialJobScheduler.A01();
                        }
                        socialJobService.jobFinished(jobParameters, false);
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
        this.mMeManager = (MeManager) AnonymousClass117.A00(547, r1);
        this.mCredentials = CredentialsModule._UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(r1);
        this.mSocialJobScheduler = (SocialJobScheduler) AnonymousClass117.A00(402, r1);
    }
}
