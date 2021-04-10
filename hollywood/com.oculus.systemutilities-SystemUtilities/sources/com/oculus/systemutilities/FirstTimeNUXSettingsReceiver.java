package com.oculus.systemutilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oculus.os.SettingsManager;
import com.oculus.secure.trustedapp.AllFamilyTrustedSignatures;
import com.oculus.secure.trustedapp.CallerInfoHelper;
import com.oculus.secure.trustedapp.TrustedApp;
import com.oculus.secure.trustedapp.TrustedAppHelper;
import com.oculus.secure.unlockulus.UnlockulusHelper;
import com.oculus.vrshell.panel.VerifierConstants;
import java.util.Arrays;
import java.util.HashSet;

public class FirstTimeNUXSettingsReceiver extends BroadcastReceiver {
    private static final String FIRST_TIME_NUX_ALLOW_GUARDIAN_KEY = "first_time_nux_allow_guardian";
    private static final String FIRST_TIME_NUX_COMPLETE_KEY = "first_time_nux_complete";
    private static final String FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_KEY = "first_time_nux_health_safety_complete";
    private static final String SET_FIRST_TIME_NUX_ALLOW_GUARDIAN_ACTION = "set_first_time_nux_allow_guardian";
    private static final String SET_FIRST_TIME_NUX_COMPLETE_ACTION = "set_first_time_nux_complete";
    private static final String SET_FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_ACTION = "set_first_time_nux_health_safety_complete";
    private static final String TAG = FirstTimeNUXSettingsReceiver.class.getSimpleName();
    private static final TrustedApp mTrustedApp = TrustedAppHelper.createTrustedApp(AllFamilyTrustedSignatures.OCULUS_PROD, new HashSet(Arrays.asList(VerifierConstants.OCULUS_VRSHELL_HOME_PACKAGE_NAME, "com.oculus.firsttimenux")));
    private SettingsManager mSettingsManager;

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        if (this.mSettingsManager == null) {
            this.mSettingsManager = new SettingsManager();
        }
        try {
            onHandleIntentWithThrows(context, intent);
        } catch (Exception e) {
            Log.e(TAG, "Error handling intent for onReceive.", e);
        }
    }

    private void onHandleIntentWithThrows(Context context, Intent intent) {
        throwIfNotAllowed(context, intent);
        String action = intent.getAction();
        if (action.equals(SET_FIRST_TIME_NUX_ALLOW_GUARDIAN_ACTION)) {
            Log.d(TAG, "Marking Allow Guardian as true");
            if (!this.mSettingsManager.setBoolean(FIRST_TIME_NUX_ALLOW_GUARDIAN_KEY, true)) {
                Log.e(TAG, "Failed to set first_time_nux_allow_guardian to true.");
            }
        } else if (action.equals(SET_FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_ACTION)) {
            Log.d(TAG, "Marking Health and Safety as completed");
            if (!this.mSettingsManager.setBoolean(FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE_KEY, true)) {
                Log.e(TAG, "Failed to set first_time_nux_health_safety_complete to true.");
            }
        } else if (action.equals(SET_FIRST_TIME_NUX_COMPLETE_ACTION)) {
            Log.d(TAG, "Marking First Time NUX as completed");
            if (!this.mSettingsManager.setBoolean(FIRST_TIME_NUX_COMPLETE_KEY, true)) {
                Log.e(TAG, "Failed to set first_time_nux_complete to true.");
            }
        } else {
            throw new IllegalArgumentException("Unsupported intent action: " + action + ".");
        }
    }

    private void throwIfNotAllowed(Context context, Intent intent) {
        CallerInfoHelper.CallerInfo callerInfo = CallerInfoHelper.getCallerInfo(intent, false);
        String callerPackageName = callerInfo.callerPackageName;
        if (!callerInfo.isValid || callerPackageName == null) {
            throw new SecurityException("Calling package could not be identified.");
        }
        Log.v(TAG, "Checking package signature for package: " + callerPackageName);
        if (mTrustedApp.checkTrustedApp(callerInfo.callerUid, context)) {
            Log.v(TAG, "Allowing caller intent because it is release signed.");
            return;
        }
        boolean isIntentFromOculusHomeShell = VerifierConstants.OCULUS_VRSHELL_HOME_PACKAGE_NAME.equals(callerPackageName);
        boolean isIntentFromOculusFirstTimeNux = "com.oculus.firsttimenux".equals(callerPackageName);
        boolean isIntentFromDebugPackage = TrustedApp.checkDebugSignatureFromPackageName(context, callerPackageName);
        if ((isIntentFromOculusHomeShell || isIntentFromOculusFirstTimeNux) && isIntentFromDebugPackage && 0 != 0) {
            Log.v(TAG, "Allowing caller intent because it and this package are debug.");
        } else if (UnlockulusHelper.isEmployeeWithWhitelistedDevice(context)) {
            Log.v(TAG, "Allowing caller intent because device passes unlockulus.");
        } else {
            throw new SecurityException("Calling package is not release signed and this package is not debug.");
        }
    }
}
