package com.oculus.os;

import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import oculus.internal.Gatekeeper;
import oculus.internal.ISettingsObserver;
import oculus.internal.SettingsService;

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
    private static final String FALSE = "0";
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
    public static final String HAND_TRACKING_OPT_IN_V20 = "hand_tracking_opt_in_v20";
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
    private static final String TAG = SettingsManager.class.getSimpleName();
    public static final String TELEMETRY_STATE = "telemetry_state";
    public static final String THUMBSTICK_ORIENTATION = "thumbstick_orientation";
    public static final String TRACKPAD_SENSITIVITY = "trackpad_sensitivity";
    private static final String TRUE = "1";
    public static final String VOICE_INTERACTION_STORAGE_ENABLED = "voice_interaction_storage_enabled";
    public static final String WAKEUP_DIALOG_ENABLED = "wakeup_dialog_enabled";
    public static final String WORLD_FROM_IMU_Y_OFFSET = "world_from_imu_y_offset";
    private final Context mContext;
    private final List<SettingsObserverWrapper> mObserverWrapperList;

    public SettingsManager() {
        this(null);
    }

    public SettingsManager(Context context) {
        this.mObserverWrapperList = new ArrayList();
        this.mContext = context != null ? context.getApplicationContext() : null;
    }

    public boolean setBoolean(String name, boolean value) {
        return setSetting(name, booleanToString(value));
    }

    public boolean setBoolean(String name, boolean value, int userId) {
        return setUserSetting(name, booleanToString(value), userId);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        if (MULTI_USER_ENABLED.equals(name)) {
            return new Gatekeeper(3).isEnabled();
        }
        return TRUE.equals(getSetting(name, booleanToString(defaultValue)));
    }

    public boolean getBoolean(String name, boolean defaultValue, int userId) {
        return TRUE.equals(getUserSetting(name, booleanToString(defaultValue), userId));
    }

    public boolean setDouble(String name, double value) {
        return setSetting(name, Double.toString(value));
    }

    public boolean setDouble(String name, double value, int userId) {
        return setUserSetting(name, Double.toString(value), userId);
    }

    public double getDouble(String name, double defaultValue) {
        return Double.parseDouble(getSetting(name, Double.toString(defaultValue)));
    }

    public double getDouble(String name, double defaultValue, int userId) {
        return Double.parseDouble(getUserSetting(name, Double.toString(defaultValue), userId));
    }

    public boolean setLong(String name, long value) {
        return setSetting(name, Long.toString(value));
    }

    public boolean setLong(String name, long value, int userId) {
        return setUserSetting(name, Long.toString(value), userId);
    }

    public long getLong(String name, long defaultValue) {
        return Long.parseLong(getSetting(name, Long.toString(defaultValue)));
    }

    public long getLong(String name, long defaultValue, int userId) {
        return Long.parseLong(getUserSetting(name, Long.toString(defaultValue), userId));
    }

    public boolean setFloat(String name, float value) {
        return setSetting(name, Float.toString(value));
    }

    public boolean setFloat(String name, float value, int userId) {
        return setUserSetting(name, Float.toString(value), userId);
    }

    public float getFloat(String name, float defaultValue) {
        return Float.parseFloat(getSetting(name, Float.toString(defaultValue)));
    }

    public float getFloat(String name, float defaultValue, int userId) {
        return Float.parseFloat(getUserSetting(name, Float.toString(defaultValue), userId));
    }

    public boolean setInt(String name, int value) {
        return setSetting(name, Integer.toString(value));
    }

    public boolean setInt(String name, int value, int userId) {
        return setUserSetting(name, Integer.toString(value), userId);
    }

    public int getInt(String name, int defaultValue) {
        return Integer.parseInt(getSetting(name, Integer.toString(defaultValue)));
    }

    public int getInt(String name, int defaultValue, int userId) {
        return Integer.parseInt(getUserSetting(name, Integer.toString(defaultValue), userId));
    }

    public boolean setString(String name, String value) {
        return setSetting(name, value);
    }

    public boolean setString(String name, String value, int userId) {
        return setUserSetting(name, value, userId);
    }

    public String getString(String name, String defaultValue) {
        return getSetting(name, defaultValue);
    }

    public String getString(String name, String defaultValue, int userId) {
        return getUserSetting(name, defaultValue, userId);
    }

    public void createUserDatabase(int userId) {
        SettingsService.getInstance().createUserDatabase(userId);
    }

    public void deleteUserDatabase(int userId) {
        SettingsService.getInstance().deleteUserDatabase(userId);
    }

    public synchronized boolean registerSettingsObserver(String settingName, SettingsObserverCallback observer, Handler handler) {
        for (SettingsObserverWrapper wrapper : this.mObserverWrapperList) {
            if (isObserverRegistered(wrapper, settingName, observer)) {
                return false;
            }
        }
        SettingsObserverWrapper settingsObserverWrapper = new SettingsObserverWrapper(settingName, observer, handler);
        SettingsService.getInstance().registerSettingsObserver(settingName, settingsObserverWrapper);
        this.mObserverWrapperList.add(settingsObserverWrapper);
        return true;
    }

    public synchronized void unregisterSettingsObserver(String settingName, SettingsObserverCallback observer) {
        int i = 0;
        while (true) {
            if (i >= this.mObserverWrapperList.size()) {
                break;
            }
            SettingsObserverWrapper wrapper = this.mObserverWrapperList.get(i);
            if (isObserverRegistered(wrapper, settingName, observer)) {
                SettingsService.getInstance().unregisterSettingsObserver(wrapper);
                this.mObserverWrapperList.remove(i);
                break;
            }
            i++;
        }
    }

    private boolean isObserverRegistered(SettingsObserverWrapper settingsObserverWrapper, String settingName, SettingsObserverCallback observer) {
        return settingsObserverWrapper.getSettingsObserver() == observer && settingsObserverWrapper.getSettingName().equals(settingName);
    }

    private String booleanToString(boolean b) {
        return b ? TRUE : FALSE;
    }

    private String getSetting(String name, String defaultValue) {
        return SettingsService.getInstance().getSetting(name, defaultValue);
    }

    private String getUserSetting(String name, String defaultValue, int userId) {
        return SettingsService.getInstance().getUserSetting(name, defaultValue, userId);
    }

    private boolean setSetting(String name, String value) {
        return SettingsService.getInstance().setSetting(name, value);
    }

    private boolean setUserSetting(String name, String value, int userId) {
        return SettingsService.getInstance().setUserSetting(name, value, userId);
    }

    /* access modifiers changed from: private */
    public static class SettingsObserverWrapper extends ISettingsObserver.Stub {
        private final Handler mHandler;
        private final SettingsObserverCallback mObserver;
        private final String mSettingName;

        private SettingsObserverWrapper(String settingName, SettingsObserverCallback observer, Handler handler) {
            this.mSettingName = settingName;
            this.mObserver = observer;
            this.mHandler = handler;
        }

        @Override // oculus.internal.ISettingsObserver
        public void onSettingChange(final String settingName) {
            this.mHandler.post(new Runnable() {
                /* class com.oculus.os.SettingsManager.SettingsObserverWrapper.AnonymousClass1 */

                public void run() {
                    SettingsObserverWrapper.this.mObserver.onSettingChange(settingName);
                }
            });
        }

        public String getSettingName() {
            return this.mSettingName;
        }

        public SettingsObserverCallback getSettingsObserver() {
            return this.mObserver;
        }
    }
}
