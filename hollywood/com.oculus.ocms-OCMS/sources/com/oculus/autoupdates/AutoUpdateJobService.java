package com.oculus.autoupdates;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.installer.events.InstallerEventBus;
import com.oculus.autoupdates.AutoUpdatesModule;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.executors.ExecutorsModule;

public class AutoUpdateJobService extends JobService {
    private static final String TAG = "AutoUpdateJobService";
    private InjectionContext _UL_mInjectionContext;

    private static final void _UL_injectMe(Context context, AutoUpdateJobService autoUpdateJobService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), autoUpdateJobService);
        } else {
            FbInjector.injectMe(AutoUpdateJobService.class, autoUpdateJobService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, AutoUpdateJobService autoUpdateJobService) {
        autoUpdateJobService._UL_mInjectionContext = new InjectionContext(3, injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
        InstallerEventBus.getInstance().register((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, this._UL_mInjectionContext));
    }

    public void onDestroy() {
        super.onDestroy();
        InstallerEventBus.getInstance().unregister((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, this._UL_mInjectionContext));
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        BLog.d(TAG, "onStartJob");
        ((Handler) FbInjector.lazyInstance(1, ExecutorsModule.UL_id._UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID, this._UL_mInjectionContext)).post(new Runnable() {
            /* class com.oculus.autoupdates.AutoUpdateJobService.AnonymousClass1 */

            public void run() {
                ((AutoUpdatesManager) FbInjector.lazyInstance(0, AutoUpdatesModule.UL_id._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID, AutoUpdateJobService.this._UL_mInjectionContext)).checkForUpdatesAndInstallIfNecessary().continueWithTask(new Continuation<Integer, Task<Void>>() {
                    /* class com.oculus.autoupdates.AutoUpdateJobService.AnonymousClass1.AnonymousClass1 */

                    @Override // bolts.Continuation
                    public Task<Void> then(Task<Integer> task) throws Exception {
                        if (task.isFaulted()) {
                            ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, AutoUpdateJobService.this._UL_mInjectionContext)).softError(AutoUpdateJobService.TAG, "unexpected exception in auto update", task.getError());
                        } else {
                            BLog.d(AutoUpdateJobService.TAG, "enqueued %d updates", Integer.valueOf(task.getResult().intValue()));
                        }
                        AutoUpdateJobService.this.rescheduleJob(jobParameters);
                        return null;
                    }
                });
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void rescheduleJob(JobParameters jobParameters) {
        jobFinished(jobParameters, true);
    }

    public boolean onStopJob(JobParameters jobParameters) {
        BLog.d(TAG, "onStopJob");
        return true;
    }
}
