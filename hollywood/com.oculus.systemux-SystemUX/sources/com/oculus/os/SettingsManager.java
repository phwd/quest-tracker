package com.oculus.os;

import android.content.Context;
import android.os.Handler;

public class SettingsManager {
    public static final String ASSISTANT_WAKEWORD_ENABLED = "assistant_wakeword_enabled";
    public static final String AUI_V2_USER_PREFERENCE = "auiv2_user_preference";
    public static final String AUTOSLEEP_TIME = "autosleep_time";
    public static final String AUTOTRANSITION_HANDS_CONTROLLERS = "autotransition_hands_controllers";
    public static final String AUTOWAKE = "autowake";
    public static final String BATTERY_DRAIN_MAX_DISCHARGE = "battery_drain_max_discharge";
    public static final String BATTERY_DRAIN_MIN_TIME = "battery_drain_min_time";
    public static final String BRIGHTNESS_SLIDER_ENABLED = "brightness_slider_enabled";
    public static final String BUG_REPORT_ADD_VISION_LOGS = "bug_report_add_vision_logs";
    public static final String CAPSENSE_HANDS_ENABLED = "capsense_hands_enabled";
    public static final String CAPTURE_INDICATOR_ENABLED = "capture_indicator_enabled";
    public static final String CONTROLLER_LASER_BEAM_EFFECTS = "controller_laser_beam_effects";
    public static final String CONTROLLER_TRACKING_EXTENDED_DEADRECKONING = "controller_tracking_extended_deadreckoning";
    public static final String CRASH_REPORTS_BROWSER_ENABLED = "browser_crash_reports_enabled";
    public static final String CRASH_REPORTS_ENABLED = "crash_reports_enabled";
    @Deprecated
    public static final String CURRENT_USER_ID = "current_user_id";
    public static final String DETECTED_CONTROLLER_TRANSMIT_POWER_BOOST = "detected_controller_transmit_power_boost";
    public static final String DETECTED_ELECTRIC_GRID_LINE_FREQUENCY = "detected_electric_grid_line_frequency";
    public static final String DEVICE_ENT_ID = "device_ent_id";
    public static final String DOUBLE_PRESS_POWER_ACTION = "double_press_power_action";
    public static final String DOUBLE_VOLUME_PRESS_ENABLED = "double_volume_press_enabled";
    public static final String DO_NOT_DISTURB = "do_not_disturb";
    public static final String ELECTRIC_GRID_LINE_FREQUENCY = "electric_grid_line_frequency";
    public static final String ENTERPRISE_AUTO_OTA = "enterprise_auto_ota";
    public static final String ENVIRONMENT_DEFAULT = "environment_default";
    public static final String ENVIRONMENT_SELECTED = "environment_selected";
    public static final String FIRST_TIME_NUX_ALLOW_GUARDIAN = "first_time_nux_allow_guardian";
    public static final String FIRST_TIME_NUX_COMPLETE = "first_time_nux_complete";
    public static final String FIRST_TIME_NUX_FLOW = "first_time_nux_flow";
    public static final String FIRST_TIME_NUX_GUARDIAN_SETUP_COMPLETE = "first_time_nux_guardian_setup_complete";
    public static final String FIRST_TIME_NUX_GUARDIAN_SETUP_ENTERED = "first_time_nux_guardian_setup_entered";
    public static final String FIRST_TIME_NUX_HEALTH_SAFETY_COMPLETE = "first_time_nux_health_safety_complete";
    public static final String FIRST_TIME_NUX_OTA_STATE = "first_time_nux_ota_state";
    public static final String FIRST_TIME_NUX_PRE_OTA_COMPLETE = "first_time_nux_pre_ota_complete";
    public static final String FITNESS_TRACKING_ENABLED = "fitness_tracking_enabled";
    public static final String FITNESS_TRACKING_NOTIFICATIONS_ENABLED = "fitness_tracking_notifications_enabled";
    public static final String FITNESS_TRACKING_OVERLAY_ENABLED = "fitness_tracking_overlay_enabled";
    public static final String FITNESS_TRACKING_OVERLAY_POSITION = "fitness_tracking_overlay_position";
    public static final String FONT_SIZE = "font_size";
    public static final String GUARDIAN_PAUSED = "guardian_paused";
    public static final String HAND_TRACKING_ENABLED = "hand_tracking_enabled";
    public static final String HAND_TRACKING_OPT_IN = "hand_tracking_opt_in";
    public static final String HAND_TRACKING_USE_IOT = "hand_tracking_use_iot";
    public static final String HAPTICS_AMPLITUDE = "input_haptics_amplitude";
    public static final String INPUT_TRANSFORMATION_TOGGLES = "input_transformation_toggles";
    public static final String INSTANT_REPLAY_ENABLED = "instant_replay_enabled";
    public static final String INTRUSION_DETECTION_ENABLED = "intrusion_detection_enabled";
    public static final String IN_APP_VOICE_COMMANDS_ENABLED = "in_app_voice_commands_enabled_v2";
    public static final String JS_BUNDLES_ENABLED = "js_bundles_enabled";
    public static final String KEYBOARD_FEDERATED_LEARNING_ENABLED = "keyboard_federated_learning_enabled";
    public static final String KEYBOARD_TRACKING_OPT_IN = "keyboard_tracking_opt_in";
    public static final String LICENSING_MODE = "licensing_mode";
    public static final String MANAGED_DEVICE = "managed_device";
    public static final String MARAUDER_DEVICE_ID = "marauder_device_id";
    public static final String MIC_MUTED = "mic_muted";
    public static final String MTP_DIALOG_ENABLED = "mtp_dialog_enabled";
    public static final String MULTI_APP_OPT_IN = "multi_app_opt_in";
    public static final String MULTI_USER_ENABLED = "multi_user_enabled";
    public static final String NEW_NOTIFICATIONS_SOUND = "new_notifications_sound";
    public static final String OCULUS_BUTTON_DOUBLEPRESS_BEHAVIOR = "oculus_button_doublepress_behavior";
    public static final String OCULUS_BUTTON_MAPPING = "oculus_button_mapping";
    public static final String PASSTHROUGH_ON_DEMAND_ENABLED = "passthrough_on_demand_enabled";
    public static final String PHONE_NOTIFICATION_ALLOW_ALL_APPS = "phone_notification_allow_all_apps";
    public static final String PHONE_NOTIFICATION_APPS = "phone_notification_apps";
    public static final String PHONE_NOTIFICATION_ENABLED = "phone_notification_enabled";
    public static final String POINTER_FILTER_SELECTION = "pointer_filter_selection";
    public static final String PRODUCTIVITY_MODE_ENABLED = "productivity_mode_enabled";
    public static final String PROX_SENSOR_DISABLED = "prox_sensor_disabled";
    public static final String SHELL_OVERLAY_ENABLED = "shell_overlay_enabled";
    public static final String SWAP_SYSTEM_BTN_HANDEDNESS = "swap_system_button_handedness";
    public static final String TELEMETRY_STATE = "telemetry_state";
    public static final String THUMBSTICK_ORIENTATION = "thumbstick_orientation";
    public static final String TRACKPAD_SENSITIVITY = "trackpad_sensitivity";
    public static final String VOICE_INTERACTION_STORAGE_ENABLED = "voice_interaction_storage_enabled";
    public static final String WAKEUP_DIALOG_ENABLED = "wakeup_dialog_enabled";
    public static final String WORLD_FROM_IMU_Y_OFFSET = "world_from_imu_y_offset";

    public SettingsManager() {
        throw new RuntimeException("Stub!");
    }

    public SettingsManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public boolean setBoolean(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean getBoolean(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDouble(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLong(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public long getLong(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public boolean setFloat(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public float getFloat(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public boolean setInt(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean setString(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public String getString(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean registerSettingsObserver(String str, SettingsObserverCallback settingsObserverCallback, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public synchronized void unregisterSettingsObserver(String str, SettingsObserverCallback settingsObserverCallback) {
        throw new RuntimeException("Stub!");
    }
}
