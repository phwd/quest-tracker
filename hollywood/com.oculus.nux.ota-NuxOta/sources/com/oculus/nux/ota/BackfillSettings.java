package com.oculus.nux.ota;

import android.content.Context;
import android.util.Log;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.os.SettingsManager;

public class BackfillSettings {
    private static final String TAG = ("NuxOta" + BackfillSettings.class.getSimpleName());
    private final OVRNuxPreferences.ClientPreferenceData mClientPreferenceData;
    private final Context mContext;
    private final SecureStorage mSecureStorage;
    private final SettingsManager mSettingsManager;

    public BackfillSettings(Context context) {
        this.mContext = context;
        this.mSettingsManager = new SettingsManager(context);
        this.mClientPreferenceData = OVRNuxPreferences.getClientPreferenceData(context);
        this.mSecureStorage = new SecureStorage(context);
    }

    public void runBackfill() {
        boolean z;
        boolean z2;
        Log.d(TAG, "Backfilling settings.");
        boolean z3 = this.mSettingsManager.getBoolean("first_time_nux_complete", false);
        if (!z3 && (z2 = this.mClientPreferenceData.hasFinishedFullVrNux)) {
            this.mSettingsManager.setBoolean("first_time_nux_complete", z2);
            z3 = z2;
        }
        if (!this.mSettingsManager.getBoolean("first_time_nux_health_safety_complete", false) && (z = this.mClientPreferenceData.hasOptedoutOfHSW)) {
            this.mSettingsManager.setBoolean("first_time_nux_health_safety_complete", z);
        }
        if (z3 && !this.mSettingsManager.getBoolean("first_time_nux_allow_guardian", false)) {
            this.mSettingsManager.setBoolean("first_time_nux_allow_guardian", true);
        }
        if (z3 && !this.mSettingsManager.getBoolean("first_time_nux_guardian_setup_entered", false)) {
            this.mSettingsManager.setBoolean("first_time_nux_guardian_setup_entered", true);
        }
        if (z3 && !this.mSettingsManager.getBoolean("first_time_nux_guardian_setup_complete", false)) {
            this.mSettingsManager.setBoolean("first_time_nux_guardian_setup_complete", true);
        }
        String string = this.mSettingsManager.getString("first_time_nux_ota_state", NuxOtaState.NEW_DEVICE.toString());
        if (!NuxOtaState.isValidStringValue(string)) {
            this.mSettingsManager.setString("first_time_nux_ota_state", LegacyNuxOtaState.fromString(string).toNuxOtaState().toString().toLowerCase());
        }
        boolean legacyPreOtaComplete = this.mSecureStorage.getLegacyPreOtaComplete();
        String legacyNuxOtaState = this.mSecureStorage.getLegacyNuxOtaState();
        if (legacyNuxOtaState != null) {
            legacyPreOtaComplete |= LegacyNuxOtaState.isNuxCompleteInState(legacyNuxOtaState);
        }
        if (legacyPreOtaComplete && !this.mSettingsManager.getBoolean("first_time_nux_pre_ota_complete", false)) {
            this.mSettingsManager.setBoolean("first_time_nux_pre_ota_complete", true);
            if (legacyNuxOtaState != null) {
                this.mSettingsManager.setString("first_time_nux_ota_state", LegacyNuxOtaState.fromString(legacyNuxOtaState).toNuxOtaState().toString().toLowerCase());
            }
        }
        if ("unknown".equals(this.mSettingsManager.getString("first_time_nux_flow", "unknown"))) {
            this.mSettingsManager.setString("first_time_nux_flow", "auto_detect");
        }
    }
}
