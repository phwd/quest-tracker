package com.oculus.nux.ota;

import android.content.Context;
import android.util.Log;
import com.oculus.os.FirstTimeNuxManager;
import com.oculus.os.SettingsManager;
import java.util.Locale;

public class NuxOtaSettings {
    private static final String TAG = "NuxOtaSettings";

    public NuxOtaState getState() {
        return NuxOtaState.fromString(FirstTimeNuxManager.getFirstTimeNuxOtaState());
    }

    public void setState(Context context, NuxOtaState nuxOtaState) {
        NuxOtaState state = getState();
        if (state != nuxOtaState) {
            FirstTimeNuxManager.setFirstTimeNuxOtaState(nuxOtaState.toString().toLowerCase(Locale.ENGLISH));
            Log.d(TAG, "Changing NUX OTA NuxOtaState: " + state.toString() + " --> " + nuxOtaState.toString());
            new Telemetry(context).recordStateChange(nuxOtaState, state);
        }
    }

    public boolean isInEnterpriseMode() {
        return new SettingsManager().getInt("managed_device", -1) == 2;
    }
}
