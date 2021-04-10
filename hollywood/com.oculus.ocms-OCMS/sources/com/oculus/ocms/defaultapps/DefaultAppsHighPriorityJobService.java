package com.oculus.ocms.defaultapps;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import bolts.Continuation;
import bolts.Task;
import com.facebook.debug.log.BLog;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.UL;
import com.oculus.coreapps.CoreApp;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.ocms.defaultapps.DefaultAppsModule;
import com.oculus.os.SettingsManager;
import com.oculus.ossdk.inject.OsSdkModule;
import com.oculus.util.constants.OculusConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class DefaultAppsHighPriorityJobService extends JobService {
    private static final String ERROR_COMPLETION_SET = "standalone_setup_completion_already_set";
    private static final String ERROR_SETUP_EXCEPTION = "standalone_setup_exception";
    static final int HIGH_PRI_APPS_MAX_RESCHEDULES = 5;
    private static final String TAG = "DefaultAppsHighPriorityJobService";
    private InjectionContext _UL_mInjectionContext;

    @VisibleForTesting(otherwise = 2)
    public void startSetupInstallJob(Context context) {
    }

    private static final void _UL_injectMe(Context context, DefaultAppsHighPriorityJobService defaultAppsHighPriorityJobService) {
        if (UL.USE_STATIC_DI) {
            _UL_staticInjectMe(FbInjector.get(context), defaultAppsHighPriorityJobService);
        } else {
            FbInjector.injectMe(DefaultAppsHighPriorityJobService.class, defaultAppsHighPriorityJobService, context);
        }
    }

    public static final void _UL_staticInjectMe(InjectorLike injectorLike, DefaultAppsHighPriorityJobService defaultAppsHighPriorityJobService) {
        defaultAppsHighPriorityJobService._UL_mInjectionContext = new InjectionContext(3, injectorLike);
    }

    public void onCreate() {
        super.onCreate();
        _UL_injectMe(this, this);
    }

    public boolean onStartJob(final JobParameters jobParameters) {
        OculusThreadExecutor.getInstance().execute(new Runnable() {
            /* class com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass1 */

            public void run() {
                ((DefaultAppsInstaller) FbInjector.lazyInstance(0, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsInstaller_ULSEP_BINDING_ID, DefaultAppsHighPriorityJobService.this._UL_mInjectionContext)).installHighPriorityApps().continueWithTask(new Continuation<CoreApp[], Task<Void>>() {
                    /* class com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass1.AnonymousClass1 */

                    @Override // bolts.Continuation
                    @Nullable
                    public Task<Void> then(Task<CoreApp[]> task) {
                        DefaultAppsHighPriorityJobService.this.execute(task, jobParameters);
                        return null;
                    }
                });
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return tearDown();
    }

    @VisibleForTesting(otherwise = 2)
    public void execute(Task<CoreApp[]> task, JobParameters jobParameters) {
        if (task.getError() != null) {
            BLog.e(TAG, task.getError(), "Failed to run setup job");
            ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ERROR_SETUP_EXCEPTION, "Error while running High priority Apps Install", task.getError());
        } else {
            handleInstallResult(task.getResult(), jobParameters);
        }
        finishJob(jobParameters);
    }

    private void handleInstallResult(CoreApp[] coreAppArr, JobParameters jobParameters) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        boolean z = false;
        for (CoreApp coreApp : coreAppArr) {
            int i = AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status[coreApp.status.ordinal()];
            if (i == 1) {
                arrayList.add(coreApp.entry.package_name);
            } else if (i == 2) {
                arrayList4.add(coreApp.entry.package_name);
            } else if (i == 3) {
                arrayList3.add(coreApp.entry.package_name);
            } else if (i != 4) {
                arrayList5.add(coreApp.entry.package_name);
            } else {
                arrayList2.add(coreApp.entry.package_name);
            }
        }
        Set<String> hashSet = new HashSet<>();
        Set<String> completedSetupInstalls = ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getCompletedSetupInstalls();
        hashSet.addAll(completedSetupInstalls);
        hashSet.addAll(arrayList);
        hashSet.addAll(arrayList4);
        if (hashSet.size() != completedSetupInstalls.size()) {
            ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setCompletedSetupInstalls(hashSet);
        }
        maybeSelectDefaultEnvironment(hashSet);
        int length = coreAppArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = true;
                break;
            } else if (!hashSet.contains(coreAppArr[i2].entry.package_name)) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setHighPriAppsSetupComplete(true);
        }
        BLog.i(TAG, "Completed setup job. Success: %d Ignored: %d Download Failed: %d Install Failed: %d", Integer.valueOf(arrayList.size()), Integer.valueOf(arrayList4.size()), Integer.valueOf(arrayList3.size()), Integer.valueOf(arrayList2.size()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$coreapps$CoreApp$Status = new int[CoreApp.Status.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.coreapps.CoreApp$Status[] r0 = com.oculus.coreapps.CoreApp.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status = r0
                int[] r0 = com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.coreapps.CoreApp$Status r1 = com.oculus.coreapps.CoreApp.Status.INSTALL_SUCCESS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.coreapps.CoreApp$Status r1 = com.oculus.coreapps.CoreApp.Status.UP_TO_DATE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.coreapps.CoreApp$Status r1 = com.oculus.coreapps.CoreApp.Status.DOWNLOAD_FAILED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.$SwitchMap$com$oculus$coreapps$CoreApp$Status     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.coreapps.CoreApp$Status r1 = com.oculus.coreapps.CoreApp.Status.INSTALL_FAILED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.ocms.defaultapps.DefaultAppsHighPriorityJobService.AnonymousClass2.<clinit>():void");
        }
    }

    private void maybeSelectDefaultEnvironment(Set<String> set) {
        SettingsManager settingsManager = (SettingsManager) FbInjector.localInstance(OsSdkModule.UL_id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID, this._UL_mInjectionContext);
        if (settingsManager.getString(OculusConstants.SETTINGS_KEY_ENVIRONMENT_SELECTED, "").equals("")) {
            String string = settingsManager.getString(OculusConstants.SETTINGS_KEY_ENVIRONMENT_DEFAULT, "");
            if (!string.equals("")) {
                for (String str : set) {
                    if (string.contains(str)) {
                        settingsManager.setString(OculusConstants.SETTINGS_KEY_ENVIRONMENT_SELECTED, string);
                        return;
                    }
                }
            }
        }
    }

    private void finishJob(JobParameters jobParameters) {
        try {
            jobFinished(jobParameters, tearDown());
        } catch (IllegalStateException e) {
            ((IErrorReporter) FbInjector.lazyInstance(2, InterfacesModule.UL_id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID, this._UL_mInjectionContext)).softError(ERROR_COMPLETION_SET, "Exception while setting job completion", e);
        }
    }

    private boolean tearDown() {
        if (((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).isHighPriAppsSetupComplete()) {
            BLog.i(TAG, "High pri apps installation completed.");
            startSetupInstallJob(this);
            return false;
        }
        int highPriAppsRescheduleAttempts = ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).getHighPriAppsRescheduleAttempts();
        if (highPriAppsRescheduleAttempts >= 5) {
            BLog.w(TAG, "Maximum reschedule attempts reached. Bailing.");
            ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setHighPriAppsSetupComplete(true);
            startSetupInstallJob(this);
            return false;
        }
        ((DefaultAppsPrefs) FbInjector.lazyInstance(1, DefaultAppsModule.UL_id._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID, this._UL_mInjectionContext)).setHighPriAppsRescheduleAttempts(highPriAppsRescheduleAttempts + 1);
        return true;
    }
}
