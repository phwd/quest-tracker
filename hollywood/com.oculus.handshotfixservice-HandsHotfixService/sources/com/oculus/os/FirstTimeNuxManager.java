package com.oculus.os;

import android.util.Log;

public class FirstTimeNuxManager {
    private static final String COMPLETE_OTA_STATE = "complete";
    private static final String NEW_DEVICE_OTA_STATE = "new_device";
    private static final String TAG = FirstTimeNuxManager.class.getSimpleName();
    private static final String UNKNOWN_NUX_FLOW = "unknown";
    private static final String VALID_NUX_FLOW_REGEX = "twilight|full_vr|unknown";
    private static final String VALID_OTA_STATE_REGEX = "new_device|safe_mode|no_ota|ota_ready|high_pri_apps_download_complete|okay_to_reboot|waiting_for_reboot|rebooting|waiting_for_high_pri_apps_download|notify_endpoint|complete";

    public static String getFirstTimeNuxFlow() {
        return getStringSetting(SettingsManager.FIRST_TIME_NUX_FLOW, "unknown");
    }

    public static boolean setFirstTimeNuxFlow(String newValue) {
        if (isValidNuxFlow(newValue)) {
            return setStringSetting(SettingsManager.FIRST_TIME_NUX_FLOW, newValue);
        }
        String str = TAG;
        Log.e(str, newValue + " is not a valid First Time NUX flow");
        return false;
    }

    public static boolean getFirstTimeNuxAllowGuardian(int userId) {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_ALLOW_GUARDIAN, userId);
    }

    public static boolean setFirstTimeNuxAllowGuardian(boolean newValue, int userId) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_ALLOW_GUARDIAN, newValue, userId);
    }

    public static boolean getFirstTimeNuxGuardianSetupEntered(int userId) {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_GUARDIAN_SETUP_ENTERED, userId);
    }

    public static boolean setFirstTimeNuxGuardianSetupEntered(boolean newValue, int userId) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_GUARDIAN_SETUP_ENTERED, newValue, userId);
    }

    public static boolean getFirstTimeNuxGuardianSetupComplete(int userId) {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_GUARDIAN_SETUP_COMPLETE, userId);
    }

    public static boolean setFirstTimeNuxGuardianSetupComplete(boolean newValue, int userId) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_GUARDIAN_SETUP_COMPLETE, newValue, userId);
    }

    public static boolean getFirstTimeNuxPreOtaComplete() {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_PRE_OTA_COMPLETE);
    }

    public static boolean setFirstTimeNuxPreOtaComplete(boolean newValue) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_PRE_OTA_COMPLETE, newValue);
    }

    public static boolean getFirstTimeNuxHealthSafetyComplete(int userId) {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE, userId);
    }

    public static boolean setFirstTimeNuxHealthSafetyComplete(boolean newValue, int userId) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE, newValue, userId);
    }

    public static boolean getFirstTimeNuxComplete(int userId) {
        return getBooleanSetting(SettingsManager.FIRST_TIME_NUX_COMPLETE, userId);
    }

    public static boolean setFirstTimeNuxComplete(boolean newValue, int userId) {
        return setBooleanSetting(SettingsManager.FIRST_TIME_NUX_COMPLETE, newValue, userId);
    }

    public static String getFirstTimeNuxOtaState() {
        return getStringSetting(SettingsManager.FIRST_TIME_NUX_OTA_STATE, NEW_DEVICE_OTA_STATE);
    }

    public static boolean setFirstTimeNuxOtaState(String newValue) {
        if (isValidOtaState(newValue)) {
            return setStringSetting(SettingsManager.FIRST_TIME_NUX_OTA_STATE, newValue);
        }
        String str = TAG;
        Log.e(str, newValue + " is not a valid First Time NUX OTA State");
        return false;
    }

    public static boolean isOtaComplete() {
        return COMPLETE_OTA_STATE.equals(getFirstTimeNuxOtaState());
    }

    public static boolean setOtaComplete() {
        return setFirstTimeNuxOtaState(COMPLETE_OTA_STATE);
    }

    private static boolean getBooleanSetting(String key) {
        return new SettingsManager().getBoolean(key, false);
    }

    private static boolean getBooleanSetting(String key, int userId) {
        return new SettingsManager().getBoolean(key, false, userId);
    }

    private static boolean setBooleanSetting(String key, boolean newValue) {
        return new SettingsManager().setBoolean(key, newValue);
    }

    private static boolean setBooleanSetting(String key, boolean newValue, int userId) {
        return new SettingsManager().setBoolean(key, newValue, userId);
    }

    private static String getStringSetting(String key, String defaultValue) {
        return new SettingsManager().getString(key, defaultValue);
    }

    private static boolean setStringSetting(String key, String newValue) {
        return new SettingsManager().setString(key, newValue);
    }

    private static boolean isValidNuxFlow(String value) {
        return value.matches(VALID_NUX_FLOW_REGEX);
    }

    private static boolean isValidOtaState(String value) {
        return value.matches(VALID_OTA_STATE_REGEX);
    }

    private FirstTimeNuxManager() {
        throw new AssertionError();
    }
}
