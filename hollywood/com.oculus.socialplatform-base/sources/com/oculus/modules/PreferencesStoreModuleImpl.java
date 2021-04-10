package com.oculus.modules;

import android.util.Log;
import android.util.Pair;
import com.oculus.modules.codegen.PreferencesStoreModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.PreferencesManager;

public class PreferencesStoreModuleImpl extends PreferencesStoreModule {
    public static final String FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "force_application_focus_awareness";
    public static final String HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY = "hand_tracking_override_frequency";
    public static final String MOUSE_SENSITIVITY_KEY = "mouse_sensitivity";
    public static final String SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "system_force_application_focus_awareness";
    public static final String TAG = "PreferencesStoreModuleImpl";
    public final PreferencesManager mPreferencesManager = new PreferencesManager();

    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getForceApplicationFocusAwarenessImpl(Resolver<Double> resolver) {
        Pair integer = this.mPreferencesManager.getInteger(FORCE_APPLICATION_FOCUS_AWARENESS_KEY);
        if (((Boolean) integer.first).booleanValue()) {
            resolver.resolve(Double.valueOf((double) ((Number) integer.second).intValue()));
            return;
        }
        Log.e(TAG, "FAILED fetch of force_application_focus_awareness");
        resolver.reject(new Exception("Unable to retrieve force_application_focus_awareness"));
    }

    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getHandTrackingOverrideFrequencyImpl(Resolver<Double> resolver) {
        Pair integer = this.mPreferencesManager.getInteger(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY);
        if (((Boolean) integer.first).booleanValue()) {
            resolver.resolve(new Double((double) ((Number) integer.second).intValue()));
            return;
        }
        Log.e(TAG, "FAILED fetch of hand_tracking_override_frequency");
        resolver.reject(new Exception("Unable to retrieve hand_tracking_override_frequency"));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Double> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getMouseSensitivityImpl(Resolver<Double> resolver) {
        Pair pair = this.mPreferencesManager.getDouble(MOUSE_SENSITIVITY_KEY);
        if (((Boolean) pair.first).booleanValue()) {
            resolver.resolve(pair.second);
            return;
        }
        Log.e(TAG, "FAILED fetch of mouse_sensitivity");
        resolver.reject(new Exception("Unable to retrieve mouse_sensitivity"));
    }

    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setForceApplicationFocusAwarenessImpl(double d) {
        int i = (int) d;
        this.mPreferencesManager.set(FORCE_APPLICATION_FOCUS_AWARENESS_KEY, i);
        this.mPreferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, i);
    }

    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setHandTrackingOverrideFrequencyImpl(double d) {
        this.mPreferencesManager.set(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY, (int) d);
    }

    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setMouseSensitivityImpl(double d) {
        this.mPreferencesManager.set(MOUSE_SENSITIVITY_KEY, d);
        this.mPreferencesManager.set(MOUSE_SENSITIVITY_KEY, d);
    }
}
