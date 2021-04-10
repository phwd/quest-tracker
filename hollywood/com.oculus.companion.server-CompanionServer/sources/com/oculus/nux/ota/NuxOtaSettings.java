package com.oculus.nux.ota;

import com.oculus.os.FirstTimeNuxManager;

public class NuxOtaSettings {
    public NuxOtaState getState() {
        return NuxOtaState.fromString(FirstTimeNuxManager.getFirstTimeNuxOtaState());
    }
}
