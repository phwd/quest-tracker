package com.oculus.alpenglow.state;

import com.oculus.alpenglow.graphql.calls.HWMOculusProvisioningState;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;

public class ProvisioningStateCollector {
    @HWMOculusProvisioningState
    public static String A00(SettingsManager settingsManager) {
        boolean z = false;
        if (settingsManager.getInt("managed_device", -1) == 2) {
            z = true;
        }
        boolean isOtaComplete = FirstTimeNuxManager.isOtaComplete();
        if (!z || !isOtaComplete) {
            return HWMOculusProvisioningState.PROVISIONING_STARTED;
        }
        return HWMOculusProvisioningState.PROVISIONING_COMPLETE;
    }
}
