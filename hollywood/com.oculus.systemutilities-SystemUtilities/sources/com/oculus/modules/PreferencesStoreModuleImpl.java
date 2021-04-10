package com.oculus.modules;

import android.util.Log;
import android.util.Pair;
import com.oculus.modules.codegen.PreferencesStoreModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.PreferencesManager;

public class PreferencesStoreModuleImpl extends PreferencesStoreModule {
    private static final String FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "force_application_focus_awareness";
    private static final String HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY = "hand_tracking_override_frequency";
    private static final String MOUSE_SENSITIVITY_KEY = "mouse_sensitivity";
    private static final String SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "system_force_application_focus_awareness";
    private static final String TAG = PreferencesStoreModuleImpl.class.getSimpleName();
    private final PreferencesManager mPreferencesManager = new PreferencesManager();

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getHandTrackingOverrideFrequencyImpl(Resolver<Double> resolver) {
        Pair<Boolean, Integer> data = this.mPreferencesManager.getInteger(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY);
        if (((Boolean) data.first).booleanValue()) {
            Log.i(TAG, "Fetched hand_tracking_override_frequency = " + data.second);
            resolver.resolve(new Double((double) ((Integer) data.second).intValue()));
            return;
        }
        Log.e(TAG, "FAILED fetch of hand_tracking_override_frequency");
        resolver.reject(new Exception("Unable to retrieve hand_tracking_override_frequency"));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setHandTrackingOverrideFrequencyImpl(double value) {
        Log.i(TAG, "SETTING hand_tracking_override_frequency to " + value);
        this.mPreferencesManager.set(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY, (int) value);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getForceApplicationFocusAwarenessImpl(Resolver<Double> resolver) {
        Pair<Boolean, Integer> data = this.mPreferencesManager.getInteger(FORCE_APPLICATION_FOCUS_AWARENESS_KEY);
        if (((Boolean) data.first).booleanValue()) {
            Log.i(TAG, "Fetched force_application_focus_awareness = " + data.second);
            resolver.resolve(Double.valueOf((double) ((Integer) data.second).intValue()));
            return;
        }
        Log.e(TAG, "FAILED fetch of force_application_focus_awareness");
        resolver.reject(new Exception("Unable to retrieve force_application_focus_awareness"));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setForceApplicationFocusAwarenessImpl(double value) {
        Log.i(TAG, "SETTING force_application_focus_awareness to " + value);
        Log.i(TAG, "SETTING system_force_application_focus_awareness to " + value);
        this.mPreferencesManager.set(FORCE_APPLICATION_FOCUS_AWARENESS_KEY, (int) value);
        this.mPreferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, (int) value);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.oculus.modules.codegen.Resolver<java.lang.Double> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void getMouseSensitivityImpl(Resolver<Double> resolver) {
        Pair<Boolean, Double> data = this.mPreferencesManager.getDouble(MOUSE_SENSITIVITY_KEY);
        if (((Boolean) data.first).booleanValue()) {
            Log.i(TAG, "Fetched mouse_sensitivity = " + data.second);
            resolver.resolve(data.second);
            return;
        }
        Log.e(TAG, "FAILED fetch of mouse_sensitivity");
        resolver.reject(new Exception("Unable to retrieve mouse_sensitivity"));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PreferencesStoreModule
    public void setMouseSensitivityImpl(double value) {
        Log.i(TAG, "SETTING mouse_sensitivity to " + value);
        Log.i(TAG, "SETTING mouse_sensitivity to " + value);
        this.mPreferencesManager.set(MOUSE_SENSITIVITY_KEY, value);
        this.mPreferencesManager.set(MOUSE_SENSITIVITY_KEY, value);
    }
}
