package com.oculus.vrshell;

import android.util.Log;
import android.util.Pair;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.PreferencesManager;

class ExperimentUtil {
    private static final String FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "force_application_focus_awareness";
    private static final String SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "system_force_application_focus_awareness";
    private static final String TAG = LoggingUtil.tag(ExperimentUtil.class);

    ExperimentUtil() {
    }

    static void updateForceApplicationFocusAwarenessPreferences(PreferencesManager preferencesManager, boolean z) {
        if (z) {
            Pair integer = preferencesManager.getInteger(FORCE_APPLICATION_FOCUS_AWARENESS_KEY);
            if (((Boolean) integer.first).booleanValue()) {
                String str = TAG;
                Log.d(str, "updating force_application_focus_awareness value from user value:" + integer.second);
                preferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, ((Integer) integer.second).intValue());
                return;
            }
            Log.d(TAG, "User value not found");
            return;
        }
        Log.d(TAG, "Failed to pass oculus_vrshell_force_focus_awareness_experiment. Resetting values...");
        preferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, 0);
        preferencesManager.set(FORCE_APPLICATION_FOCUS_AWARENESS_KEY, 0);
    }
}
