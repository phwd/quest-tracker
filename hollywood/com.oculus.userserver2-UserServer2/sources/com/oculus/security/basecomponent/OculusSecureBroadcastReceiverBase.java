package com.oculus.security.basecomponent;

import X.AbstractC0201ew;
import X.BZ;
import X.IX;
import X.Mi;
import X.Om;
import X.SB;
import X.f5;
import X.f6;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.userserver.managerservice.OculusUserBootReceiver;
import com.oculus.userserver.managerservice.OculusUserManagerImpl;
import com.oculus.userserver.managerservice.UserSyncScheduler;
import com.oculus.userserver.usercleaner.UserCleanerBootReceiver;
import com.oculus.userserver.usercleaner.UserCleanerJobService;
import com.oculus.userserver.usercleaner.UserCleanerScheduler;
import com.oculus.util.constants.JobSchedulerIds;
import javax.annotation.Nullable;

public abstract class OculusSecureBroadcastReceiverBase extends SB {
    public Om _UL_mInjectionContext;
    public f5 mActionReceiver = new f5() {
        /* class com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase.AnonymousClass1 */

        @Override // X.f5
        public final void A2Y(Context context, Intent intent, f6 f6Var) {
            OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase = OculusSecureBroadcastReceiverBase.this;
            if (!(oculusSecureBroadcastReceiverBase instanceof UserCleanerBootReceiver)) {
                OculusUserBootReceiver oculusUserBootReceiver = (OculusUserBootReceiver) oculusSecureBroadcastReceiverBase;
                oculusUserBootReceiver._UL_mInjectionContext = new Om(1, BZ.get(context));
                if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                    ((UserSyncScheduler) BZ.A02(0, 0, oculusUserBootReceiver._UL_mInjectionContext)).A00();
                    return;
                }
                return;
            }
            UserCleanerBootReceiver userCleanerBootReceiver = (UserCleanerBootReceiver) oculusSecureBroadcastReceiverBase;
            userCleanerBootReceiver._UL_mInjectionContext = new Om(2, BZ.get(context));
            if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
                UserCleanerScheduler userCleanerScheduler = (UserCleanerScheduler) BZ.A02(0, 30, userCleanerBootReceiver._UL_mInjectionContext);
                JobScheduler jobScheduler = (JobScheduler) ((Context) BZ.A02(0, 1, userCleanerScheduler._UL_mInjectionContext)).getSystemService("jobscheduler");
                if (jobScheduler == null) {
                    Mi.A00(UserCleanerScheduler.TAG, "got null for jobScheduler in getSystemService");
                } else {
                    jobScheduler.schedule(new JobInfo.Builder(JobSchedulerIds.UNCLAIMED_USER_REMOVAL, new ComponentName((Context) BZ.A02(0, 1, userCleanerScheduler._UL_mInjectionContext), UserCleanerJobService.class.getName())).setPeriodic(userCleanerScheduler.REMOVAL_INTERVAL_MS).setRequiresDeviceIdle(true).build());
                }
                UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
                if (userManager != null && userManager.isSystemUser()) {
                    ((OculusUserManagerImpl) BZ.A02(1, 44, userCleanerBootReceiver._UL_mInjectionContext)).A05();
                }
            }
        }
    };
    @Nullable
    public IntentFilter mIntentFilter = null;
    @Inject
    @Eager
    public OculusIntentLogger mOculusIntentLogger;
    public final AbstractC0201ew mReporter = new BLogDebugReporter(getClass().getName());

    @Override // X.SB
    public final boolean A01(Context context, Intent intent) {
        IntentFilter intentFilter = this.mIntentFilter;
        if ((intentFilter == null || intentFilter.hasAction(intent.getAction())) && super.A01(context, intent)) {
            return true;
        }
        return false;
    }

    public OculusSecureBroadcastReceiverBase(String... strArr) {
        int length = strArr.length;
        if (length > 0) {
            this.mIntentFilter = new IntentFilter();
            for (String str : strArr) {
                this.mIntentFilter.addAction(str);
            }
        }
    }

    @Override // X.SB
    public final void A00(Context context) {
        super.A00(context);
        if (this.mOculusIntentLogger == null || BZ.A02(0, 78, this._UL_mInjectionContext) == null) {
            BZ bz = BZ.get(context);
            this._UL_mInjectionContext = new Om(1, bz);
            this.mOculusIntentLogger = (OculusIntentLogger) IX.A00(36, bz);
        }
    }
}
