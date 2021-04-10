package com.oculus.vrshell.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.panellib.CrashReporting;
import com.oculus.secure.trustedapp.AllFamilyPackageNames;
import com.oculus.secure.trustedapp.AllFamilyTrustedSignatures;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import com.oculus.secure.trustedapp.TrustedApp;
import com.oculus.secure.trustedapp.TrustedAppHelper;
import com.oculus.secure.unlockulus.UnlockulusHelper;
import com.oculus.vrshell.home.config.ConfigUpdaterJobService;
import java.util.Arrays;
import java.util.HashSet;

public class WakeupBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = WakeupBroadcastReceiver.class.getSimpleName();
    private static final TrustedApp mHorizonTrustedApp = TrustedAppHelper.createTrustedApp(AllFamilyTrustedSignatures.OCULUS_PROD, new HashSet(Arrays.asList("com.oculus.horizon", "com.oculus.ocms")));

    public enum Action {
        DO_LOGGING("com.oculus.home.wakeup.logging"),
        FETCH_GK("com.oculus.home.wakeup.gkfetch"),
        FETCH_QE("com.oculus.home.wakeup.qefetch"),
        ON_LOGOUT("com.oculus.home.wakeup.logout"),
        FETCH_CONFIG("com.oculus.home.wakeup.configfetch");
        
        public final String extraKey;

        private Action(String value) {
            this.extraKey = value;
        }
    }

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        try {
            onHandleIntentWithThrows(context, intent);
        } catch (Exception e) {
            Log.e(TAG, "Error handling intent for onReceive.", e);
        }
    }

    private void onHandleIntentWithThrows(Context context, Intent intent) {
        throwIfNotAllowed(context, intent);
        CrashReporting.initialize(context, null, "", HomeApplication.FB_HOME_APP_ID, HomeApplication.FB_HOME_APP_NAME);
        if (intent.getBooleanExtra(Action.FETCH_CONFIG.extraKey, false)) {
            ConfigUpdaterJobService.schedule(context);
        } else if (intent.getBooleanExtra(Action.ON_LOGOUT.extraKey, false)) {
            ConfigUpdaterJobService.unschedule(context);
        } else if (!intent.getBooleanExtra(Action.DO_LOGGING.extraKey, false)) {
            Log.w(TAG, "Missing or unknown Action");
        }
    }

    private void throwIfNotAllowed(Context context, Intent intent) {
        boolean isIntentFromHorizonOrOCMS = false;
        CallerInfoHelper.CallerInfo callerInfo = CallerInfoHelper.getCallerInfo(intent, false);
        String callerPackageName = callerInfo.callerPackageName;
        if (!callerInfo.isValid || callerPackageName == null) {
            throw new SecurityException("Calling package could not be identified.");
        }
        Log.v(TAG, "Checking package signature for package: " + callerPackageName);
        if (mHorizonTrustedApp.checkTrustedApp(callerInfo.callerUid, context)) {
            Log.v(TAG, "Allowing caller intent because it is release signed.");
            return;
        }
        if ("com.oculus.ocms".equals(callerPackageName) || "com.oculus.horizon".equals(callerPackageName) || AllFamilyPackageNames.OCULUS_HORIZON_DEV.equals(callerPackageName)) {
            isIntentFromHorizonOrOCMS = true;
        }
        boolean isIntentFromDebugPackage = TrustedApp.checkDebugSignatureFromPackageName(context, callerPackageName);
        if (isIntentFromHorizonOrOCMS && isIntentFromDebugPackage && 0 != 0) {
            Log.v(TAG, "Allowing caller intent because it and this package are debug.");
        } else if (UnlockulusHelper.isEmployeeWithWhitelistedDevice(context)) {
            Log.v(TAG, "Allowing caller intent because device passes unlockulus.");
        } else {
            throw new SecurityException("Calling package is not release signed and this package is not debug.");
        }
    }
}
