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

public class HandTrackingSettingsReceiver extends BroadcastReceiver {
    private static final String ENABLE_HAND_TRACKING_ACTION = "enable_hand_tracking";
    private static final String HAND_TRACKING_ENABLED_KEY = "hand_tracking_enabled";
    private static final String HAND_TRACKING_OPT_IN_KEY = "hand_tracking_opt_in";
    private static final String TAG = HandTrackingSettingsReceiver.class.getSimpleName();
    private static final TrustedApp mHomeTrustedApp = TrustedAppHelper.createTrustedApp(AllFamilyTrustedSignatures.OCULUS_PROD, new HashSet(Arrays.asList(VerifierConstants.OCULUS_VRSHELL_HOME_PACKAGE_NAME)));
    private SettingsManager mSettingsManager;

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
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
        if (action.equals(ENABLE_HAND_TRACKING_ACTION)) {
            Log.i(TAG, "Opting-into and enabling hand tracking");
            if (!this.mSettingsManager.setBoolean(HAND_TRACKING_OPT_IN_KEY, true)) {
                Log.e(TAG, "Failed to set hand_tracking_opt_in to true.");
            }
            if (!this.mSettingsManager.setBoolean(HAND_TRACKING_ENABLED_KEY, true)) {
                Log.e(TAG, "Failed to set hand_tracking_enabled to true.");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Unsupported intent action: " + action + ".");
    }

    private void throwIfNotAllowed(Context context, Intent intent) {
        CallerInfoHelper.CallerInfo callerInfo = CallerInfoHelper.getCallerInfo(intent, false);
        String callerPackageName = callerInfo.callerPackageName;
        if (!callerInfo.isValid || callerPackageName == null) {
            throw new SecurityException("Calling package could not be identified.");
        }
        Log.v(TAG, "Checking package signature for package: " + callerPackageName);
        if (mHomeTrustedApp.checkTrustedApp(callerInfo.callerUid, context)) {
            Log.v(TAG, "Allowing caller intent because it is release signed.");
            return;
        }
        boolean isIntentFromOculusHomeShell = VerifierConstants.OCULUS_VRSHELL_HOME_PACKAGE_NAME.equals(callerPackageName);
        boolean isIntentFromDebugPackage = TrustedApp.checkDebugSignatureFromPackageName(context, callerPackageName);
        if (isIntentFromOculusHomeShell && isIntentFromDebugPackage && 0 != 0) {
            Log.v(TAG, "Allowing caller intent because it and this package are debug.");
        } else if (UnlockulusHelper.isEmployeeWithWhitelistedDevice(context)) {
            Log.v(TAG, "Allowing caller intent because device passes unlockulus.");
        } else {
            throw new SecurityException("Calling package is not release signed and this package is not debug.");
        }
    }
}
