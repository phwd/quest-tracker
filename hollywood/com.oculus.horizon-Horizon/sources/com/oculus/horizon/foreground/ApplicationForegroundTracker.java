package com.oculus.horizon.foreground;

import X.AbstractC06640p5;
import X.AnonymousClass0Cy;
import X.AnonymousClass0D2;
import X.AnonymousClass0D4;
import X.AnonymousClass0DC;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.oculus.util.task.TaskDelayFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApplicationForegroundTracker implements Application.ActivityLifecycleCallbacks {
    public static final long APP_LAUNCH_TO_BACKGROUNDED_THRESHOLD_MS = TimeUnit.SECONDS.toMillis(4);
    public static final int DELAY_MS = 1000;
    public static final String TAG = "ApplicationForegroundTracker";
    public static volatile ApplicationForegroundTracker _UL__ULSEP_com_oculus_horizon_foreground_ApplicationForegroundTracker_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public AnonymousClass0D2 mCancellationTokenSource;
    public int mCountOfResumedActivities = 0;
    public final List<ApplicationForegroundListener> mListeners = new ArrayList();
    @Inject
    @Eager
    public final TaskDelayFactory mTaskDelayFactory;
    public long mUptimeMillisOfAppInit = 0;
    public long mUptimeMillisOfFirstResume = 0;
    public long mUptimeMillisOfLastResume = 0;

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
        int i = this.mCountOfResumedActivities - 1;
        this.mCountOfResumedActivities = i;
        if (i <= 0) {
            final long j = this.mUptimeMillisOfLastResume;
            AnonymousClass0DC.A00(1000, AnonymousClass0Cy.A03.A02, this.mCancellationTokenSource.A01()).A0A(new AnonymousClass0D4<Void, AnonymousClass0DC<Void>>() {
                /* class com.oculus.horizon.foreground.ApplicationForegroundTracker.AnonymousClass1 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
                @Override // X.AnonymousClass0D4
                public final AnonymousClass0DC<Void> then(AnonymousClass0DC<Void> r7) throws Exception {
                    if (!r7.A0I()) {
                        ApplicationForegroundTracker applicationForegroundTracker = ApplicationForegroundTracker.this;
                        applicationForegroundTracker.mUptimeMillisOfLastResume = 0;
                        long uptimeMillis = SystemClock.uptimeMillis() - j;
                        for (ApplicationForegroundListener applicationForegroundListener : applicationForegroundTracker.mListeners) {
                            applicationForegroundListener.A5i(uptimeMillis);
                        }
                    }
                    return null;
                }
            });
        }
    }

    public final void onActivityResumed(Activity activity) {
        this.mCountOfResumedActivities++;
        if (this.mUptimeMillisOfFirstResume == 0) {
            this.mUptimeMillisOfFirstResume = SystemClock.uptimeMillis();
        }
        AnonymousClass0D2 r0 = this.mCancellationTokenSource;
        if (r0 != null) {
            r0.A02();
        }
        this.mCancellationTokenSource = new AnonymousClass0D2();
        if (this.mUptimeMillisOfLastResume <= 0) {
            this.mUptimeMillisOfLastResume = SystemClock.uptimeMillis();
            for (ApplicationForegroundListener applicationForegroundListener : this.mListeners) {
                applicationForegroundListener.A5j();
            }
        }
    }

    @Inject
    public ApplicationForegroundTracker(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
        this.mTaskDelayFactory = (TaskDelayFactory) AnonymousClass117.A00(156, r3);
        this.mUptimeMillisOfAppInit = SystemClock.uptimeMillis();
    }
}
