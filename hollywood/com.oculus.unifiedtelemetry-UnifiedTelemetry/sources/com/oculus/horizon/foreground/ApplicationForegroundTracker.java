package com.oculus.horizon.foreground;

import X.AbstractC0247Xu;
import X.C0078Cx;
import X.C0079Cy;
import X.C0080Cz;
import X.D1;
import X.D3;
import X.D7;
import X.D8;
import X.DB;
import X.DC;
import X.QC;
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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_BINDING_ID"})
@ApplicationScoped
public class ApplicationForegroundTracker implements Application.ActivityLifecycleCallbacks {
    public static final long APP_LAUNCH_TO_BACKGROUNDED_THRESHOLD_MS = TimeUnit.SECONDS.toMillis(4);
    public static final int DELAY_MS = 1000;
    public static final String TAG = "ApplicationForegroundTracker";
    public static volatile ApplicationForegroundTracker _UL__ULSEP_com_oculus_horizon_foreground_ApplicationForegroundTracker_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public D1 mCancellationTokenSource;
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
        C0079Cy cy;
        DB db;
        int i = this.mCountOfResumedActivities - 1;
        this.mCountOfResumedActivities = i;
        if (i <= 0) {
            final long j = this.mUptimeMillisOfLastResume;
            D1 d1 = this.mCancellationTokenSource;
            synchronized (d1.A02) {
                D1.A00(d1);
                cy = new C0079Cy(d1);
            }
            ScheduledExecutorService scheduledExecutorService = C0078Cx.A03.A02;
            D1 d12 = cy.A00;
            if (d12.A01()) {
                db = DB.A06;
            } else {
                DC dc = new DC();
                D8 d8 = new D8(scheduledExecutorService.schedule(new D7(dc), 1000, TimeUnit.MILLISECONDS), dc);
                synchronized (d12.A02) {
                    D1.A00(d12);
                    C0080Cz cz = new C0080Cz(d12, d8);
                    if (d12.A00) {
                        cz.A00();
                    } else {
                        d12.A03.add(cz);
                    }
                }
                db = dc.A00;
            }
            db.A02(new D3<Void, DB<Void>>() {
                /* class com.oculus.horizon.foreground.ApplicationForegroundTracker.AnonymousClass1 */

                /* Return type fixed from 'java.lang.Object' to match base method */
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.DB] */
                @Override // X.D3
                public final DB<Void> A5U(DB<Void> db) throws Exception {
                    boolean z;
                    synchronized (db.A05) {
                        z = db.A03;
                    }
                    if (!z) {
                        ApplicationForegroundTracker applicationForegroundTracker = ApplicationForegroundTracker.this;
                        applicationForegroundTracker.mUptimeMillisOfLastResume = 0;
                        long uptimeMillis = SystemClock.uptimeMillis() - j;
                        for (ApplicationForegroundListener applicationForegroundListener : applicationForegroundTracker.mListeners) {
                            applicationForegroundListener.A3a(uptimeMillis);
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
        D1 d1 = this.mCancellationTokenSource;
        if (d1 != null) {
            synchronized (d1.A02) {
                D1.A00(d1);
                if (!d1.A00) {
                    d1.A00 = true;
                    for (C0080Cz cz : new ArrayList(d1.A03)) {
                        cz.A00();
                    }
                }
            }
        }
        this.mCancellationTokenSource = new D1();
        if (this.mUptimeMillisOfLastResume <= 0) {
            this.mUptimeMillisOfLastResume = SystemClock.uptimeMillis();
            for (ApplicationForegroundListener applicationForegroundListener : this.mListeners) {
                applicationForegroundListener.A3b();
            }
        }
    }

    @Inject
    public ApplicationForegroundTracker(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(1, xu);
        this.mTaskDelayFactory = TaskDelayFactory.A01(xu);
        this.mUptimeMillisOfAppInit = SystemClock.uptimeMillis();
    }
}
