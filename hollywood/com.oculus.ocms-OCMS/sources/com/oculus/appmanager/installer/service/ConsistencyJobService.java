package com.oculus.appmanager.installer.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;
import com.oculus.appmanager.installer.contract.errors.ErrorCategories;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@TargetApi(21)
public class ConsistencyJobService extends JobService {
    private static final String TAG = "ConsistencyJobService";
    private InjectionContext _UL_mInjectionContext;
    @Inject
    @Eager
    private InstallerServiceContract mInstallerServiceContract;

    private static final void _UL_injectMe(Context context, ConsistencyJobService consistencyJobService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), consistencyJobService);
        } else {
            FbInjector.injectMe(ConsistencyJobService.class, consistencyJobService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, ConsistencyJobService consistencyJobService) {
        consistencyJobService._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        consistencyJobService.mInstallerServiceContract = InstallerServiceContract._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        long j = jobParameters.getExtras().getLong("update_id", -1);
        BLog.d(TAG, "onStartJob: id = %d", Long.valueOf(j));
        if (j != -1) {
            this.mInstallerServiceContract.performConsistencyCheckForId(j);
        } else {
            ((IErrorReporter) FbInjector.lazyInstance(0, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ErrorCategories.CONSISTENCY_JOB_SCHEDULER_NO_ID_AVAILABLE, "JobScheduler called but update id not found");
        }
        jobFinished(jobParameters, false);
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        BLog.d(TAG, "onStopJob: called.");
        return false;
    }
}
