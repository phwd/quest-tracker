package com.oculus.os;

import android.content.Context;
import android.os.Handler;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SettingsManager {
    public static final String AUI_V2_USER_PREFERENCE = "auiv2_user_preference";
    public static final String AUTOSLEEP_TIME = "autosleep_time";
    public static final String AUTOTRANSITION_HANDS_CONTROLLERS = "autotransition_hands_controllers";
    public static final String AUTOWAKE = "autowake";
    public static final String BUG_REPORT_ADD_VISION_LOGS = "bug_report_add_vision_logs";
    public static final String CONTROLLER_LASER_BEAM_EFFECTS = "controller_laser_beam_effects";
    public static final String CRASH_REPORTS_ENABLED = "crash_reports_enabled";
    public static final String DEVICE_ENT_ID = "device_ent_id";
    public static final String DOUBLE_PRESS_POWER_ACTION = "double_press_power_action";
    public static final String DOUBLE_VOLUME_PRESS_ENABLED = "double_volume_press_enabled";
    public static final String ELECTRIC_GRID_LINE_FREQUENCY = "electric_grid_line_frequency";
    public static final String ENTERPRISE_AUTO_OTA = "enterprise_auto_ota";
    public static final String FIRST_TIME_NUX_COMPLETE = "first_time_nux_complete";
    public static final String FIRST_TIME_NUX_FLOW = "first_time_nux_flow";
    public static final String FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE = "first_time_nux_health_safety_complete";
    public static final String FIRST_TIME_NUX_OTA_STATE = "first_time_nux_ota_state";
    public static final String FIRST_TIME_NUX_PRE_OTA_COMPLETE = "first_time_nux_pre_ota_complete";
    public static final String HAND_TRACKING_ENABLED = "hand_tracking_enabled";
    public static final String HAND_TRACKING_OPT_IN = "hand_tracking_opt_in";
    public static final String HAPTICS_AMPLITUDE = "input_haptics_amplitude";
    public static final String IN_APP_VOICE_COMMANDS_ENABLED = "in_app_voice_commands_enabled";
    public static final String JS_BUNDLES_ENABLED = "js_bundles_enabled";
    public static final String KEYBOARD_TRACKING_OPT_IN = "keyboard_tracking_opt_in";
    public static final String MANAGED_DEVICE = "managed_device";
    public static final String MARAUDER_DEVICE_ID = "marauder_device_id";
    public static final String MIC_MUTED = "mic_muted";
    public static final String MTP_DIALOG_ENABLED = "mtp_dialog_enabled";
    public static final String MULTI_APP_OPT_IN = "multi_app_opt_in";
    public static final String MULTI_USER_ENABLED = "multi_user_enabled";
    public static final String OCULUS_BUTTON_DOUBLEPRESS_BEHAVIOR = "oculus_button_doublepress_behavior";
    public static final String OCULUS_BUTTON_MAPPING = "oculus_button_mapping";
    public static final String PASSTHROUGH_ON_DEMAND_ENABLED = "passthrough_on_demand_enabled";
    public static final String PROX_SENSOR_DISABLED = "prox_sensor_disabled";
    public static final String SHELL_OVERLAY_ENABLED = "shell_overlay_enabled";
    public static final String SWAP_SYSTEM_BTN_HANDEDNESS = "swap_system_button_handedness";
    public static final String TELEMETRY_STATE = "telemetry_state";
    public static final String VOICE_INTERACTION_STORAGE_ENABLED = "voice_interaction_storage_enabled";
    public static final String WAKEUP_DIALOG_ENABLED = "wakeup_dialog_enabled";
    public static final String WORLD_FROM_IMU_Y_OFFSET = "world_from_imu_y_offset";

    public SettingsManager() {
        throw new RuntimeException("Stub!");
    }

    public boolean getBoolean(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public float getFloat(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public long getLong(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public String getString(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean registerSettingsObserver(String str, SettingsObserverCallback settingsObserverCallback, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public boolean setBoolean(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDouble(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public boolean setFloat(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public boolean setInt(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLong(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean setString(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public synchronized void unregisterSettingsObserver(String str, SettingsObserverCallback settingsObserverCallback) {
        throw new RuntimeException("Stub!");
    }

    public SettingsManager(Context context) {
        throw new RuntimeException("Stub!");
    }
}
