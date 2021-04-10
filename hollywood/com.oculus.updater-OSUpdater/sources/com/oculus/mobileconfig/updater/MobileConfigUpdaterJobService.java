package com.oculus.mobileconfig.updater;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import com.oculus.mobileconfig.init.MobileConfigInitOptions;
import com.oculus.util.network.NetworkUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MobileConfigUpdaterJobService extends JobService {
    private static String TAG;
    private InjectionContext _UL_mInjectionContext;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private static final void _UL_injectMe(Context context, MobileConfigUpdaterJobService mobileConfigUpdaterJobService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), mobileConfigUpdaterJobService);
        } else {
            FbInjector.injectMe(MobileConfigUpdaterJobService.class, mobileConfigUpdaterJobService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, MobileConfigUpdaterJobService mobileConfigUpdaterJobService) {
        mobileConfigUpdaterJobService._UL_mInjectionContext = new InjectionContext(3, injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
        TAG = ((MobileConfigInitOptions) FbInjector.lazyInstance(2, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, this._UL_mInjectionContext)).mTagPrefix + MobileConfigUpdaterJobService.class.getSimpleName();
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        BLog.v(TAG, "Job started");
        ((EventManager) FbInjector.lazyInstance(1, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent("oc_mobileconfig_refresh_started").logAndRelease();
        verifyNetworkConnectivity(getApplicationContext()).continueWith(new Continuation<Void, Void>() {
            /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass1 */

            @Override // bolts.Continuation
            public Void then(Task<Void> task) {
                ((MobileConfigConfiguration) FbInjector.lazyInstance(0, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID, MobileConfigUpdaterJobService.this._UL_mInjectionContext)).fetchAsync().continueWith(new Continuation<Void, Void>() {
                    /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass1.AnonymousClass1 */

                    @Override // bolts.Continuation
                    public Void then(Task<Void> task) {
                        MobileConfigUpdaterJobService.this.jobFinished(jobParameters, false);
                        return null;
                    }
                });
                return null;
            }
        });
        return true;
    }

    private Task<Void> verifyNetworkConnectivity(final Context context) {
        return Task.call(new Callable<Void>() {
            /* class com.oculus.mobileconfig.updater.MobileConfigUpdaterJobService.AnonymousClass2 */

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (NetworkUtils.isWifiConnected(MobileConfigUpdaterJobService.this.getApplicationContext())) {
                    BLog.d(MobileConfigUpdaterJobService.TAG, "isWifiConnected is true");
                    if (NetworkUtils.isOculusAddressReachable()) {
                        BLog.d(MobileConfigUpdaterJobService.TAG, "isOculusAddressReachable is true");
                        return null;
                    }
                    BLog.d(MobileConfigUpdaterJobService.TAG, "isOculusAddressReachable is false");
                    new MobileConfigConnectivityChecker(((MobileConfigInitOptions) FbInjector.lazyInstance(2, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, MobileConfigUpdaterJobService.this._UL_mInjectionContext)).mTagPrefix).updateCheckWithRetry(context, new Handler(Looper.getMainLooper()), 5);
                    MobileConfigConnectivityChecker.wifiCheckCountDownLatch.await();
                } else {
                    BLog.d(MobileConfigUpdaterJobService.TAG, "register connectivity broadcast listener");
                    MobileConfigConnectivityChecker.registerReceiver(context, ((MobileConfigInitOptions) FbInjector.lazyInstance(2, MobileConfigInitModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID, MobileConfigUpdaterJobService.this._UL_mInjectionContext)).mTagPrefix);
                    MobileConfigConnectivityChecker.wifiCheckCountDownLatch.await(30, TimeUnit.SECONDS);
                }
                return null;
            }
        }, Task.BACKGROUND_EXECUTOR);
    }
}
