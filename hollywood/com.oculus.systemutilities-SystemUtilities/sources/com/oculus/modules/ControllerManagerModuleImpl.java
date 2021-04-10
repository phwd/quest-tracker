package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.ControllerManagerModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.SettingsManager;

public class ControllerManagerModuleImpl extends ControllerManagerModule {
    private static final int INPUT_HAPTICS_AMPLITUDE_DEFAULT_VALUE = 50;
    private static final String INPUT_HAPTICS_AMPLITUDE_SETTING_NAME = "input_haptics_amplitude";
    private static final String TAG = ControllerManagerModuleImpl.class.getSimpleName();
    private final SettingsManager mSettingsManager = new SettingsManager();

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void getInputHapticsAmplitudeImpl(Resolver<Double> resolver) {
        int inputHapticsAmplitude = this.mSettingsManager.getInt(INPUT_HAPTICS_AMPLITUDE_SETTING_NAME, (int) INPUT_HAPTICS_AMPLITUDE_DEFAULT_VALUE);
        Log.d(TAG, String.format("Got input haptics amplitude: %d", Integer.valueOf(inputHapticsAmplitude)));
        resolver.resolve(new Double((double) inputHapticsAmplitude));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void setInputHapticsAmplitudeImpl(double value, Resolver<Boolean> resolver) {
        int intValue = (int) value;
        if (this.mSettingsManager.setInt(INPUT_HAPTICS_AMPLITUDE_SETTING_NAME, intValue)) {
            Log.d(TAG, String.format("Set input haptics amplitude: %d", Integer.valueOf(intValue)));
            resolver.resolve(true);
            return;
        }
        Log.e(TAG, "Failed to set input haptics amplitudeDatabase transaction was unsuccessful.");
        resolver.reject(new Exception("Failed to set input haptics amplitude"));
    }

    private enum OculusButtonMapping {
        DEFAULT(0),
        LONG_PRESS(1),
        FLAIL_LOCKOUT_DISABLED(2);
        
        private final int mButtonMapping;
        private final SettingsManager mSettingsManager = new SettingsManager();

        private OculusButtonMapping(int buttonMapping) {
            this.mButtonMapping = buttonMapping;
        }

        public int toInt() {
            return this.mButtonMapping;
        }

        public boolean set(boolean enabled) {
            int buttonMappingSetting = this.mSettingsManager.getInt("oculus_button_mapping", DEFAULT.toInt());
            return this.mSettingsManager.setInt("oculus_button_mapping", enabled ? buttonMappingSetting | this.mButtonMapping : buttonMappingSetting & (this.mButtonMapping ^ -1));
        }

        public boolean isSet() {
            return (this.mButtonMapping & this.mSettingsManager.getInt("oculus_button_mapping", DEFAULT.toInt())) == this.mButtonMapping;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void getOculusButtonLongPressImpl(Resolver<Boolean> resolver) {
        boolean longPress = OculusButtonMapping.LONG_PRESS.isSet();
        Log.d(TAG, String.format("Got Oculus button long press status: %b", Boolean.valueOf(longPress)));
        resolver.resolve(Boolean.valueOf(longPress));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void setOculusButtonLongPressImpl(boolean enabled, Resolver<Boolean> resolver) {
        boolean success = OculusButtonMapping.LONG_PRESS.set(enabled);
        if (success) {
            Log.d(TAG, String.format("Set Oculus button long press status: %b", Boolean.valueOf(enabled)));
        } else {
            Log.e(TAG, "Failed to set Oculus button mapping. Database transaction was unsuccessful.");
            resolver.reject(new Exception("Failed to set Oculus button mapping"));
        }
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void getOculusButtonMovementDetectionImpl(Resolver<Boolean> resolver) {
        boolean movementDetection;
        if (!OculusButtonMapping.FLAIL_LOCKOUT_DISABLED.isSet()) {
            movementDetection = true;
        } else {
            movementDetection = false;
        }
        Log.d(TAG, String.format("Got Oculus button movement detection status: %b", Boolean.valueOf(movementDetection)));
        resolver.resolve(Boolean.valueOf(movementDetection));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.ControllerManagerModule
    public void setOculusButtonMovementDetectionImpl(boolean enabled, Resolver<Boolean> resolver) {
        boolean z;
        OculusButtonMapping oculusButtonMapping = OculusButtonMapping.FLAIL_LOCKOUT_DISABLED;
        if (!enabled) {
            z = true;
        } else {
            z = false;
        }
        boolean success = oculusButtonMapping.set(z);
        if (success) {
            Log.d(TAG, String.format("Set Oculus button movement detection status: %b", Boolean.valueOf(enabled)));
        } else {
            Log.e(TAG, "Failed to set Oculus button mapping. Database transaction was unsuccessful.");
            resolver.reject(new Exception("Failed to set Oculus button mapping"));
        }
        resolver.resolve(Boolean.valueOf(success));
    }
}
