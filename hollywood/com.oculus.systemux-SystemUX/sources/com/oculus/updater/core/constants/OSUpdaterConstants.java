package com.oculus.updater.core.constants;

public class OSUpdaterConstants {
    public static final String ACTION_APPLY_UPDATE_FAILED = "apply_update_failed";
    public static final String ACTION_APPLY_UPDATE_STATUS = "apply_update_status";
    public static final String ACTION_APPLY_UPDATE_SUCCEEDED = "apply_update_succeeded";
    public static final String ACTION_BATTERY_ALARM = "BatteryAlarm";
    public static final String ACTION_BOOT_INTO_UPDATE = "boot_into_update";
    public static final String ACTION_CANCEL_OTA = "cancel_ota";
    public static final String ACTION_CHECK_OTA_AVAILABILITY = "check_ota_availability";
    public static final String ACTION_CHECK_PERIOD_ALARM = "CheckPeriodAlarm";
    public static final String ACTION_CHECK_UPDATES = "check_updates";
    public static final String ACTION_EXT_BOOT_INTO_UPDATE = "ext_boot_into_update";
    public static final String ACTION_EXT_CHECK_UPDATES = "ext_check_updates";
    public static final String ACTION_EXT_CHECK_UPDATES_FULL = "ext_check_updates_full";
    public static final String ACTION_GET_UPDATE_INFO = "get_update_info";
    public static final String ACTION_IDLE_ALARM = "IdleAlarm";
    public static final String ACTION_PROCESS_STARTED = "process_started";
    public static final String ACTION_SET_OTA_DISABLED_STATE = "set_ota_disabled_state";
    public static final String ACTION_SYSTEM_BATTERY_CHANGED = "system_battery_changed";
    public static final String ACTION_SYSTEM_CONNECTIVITY_CHANGED = "system_connectivity_changed";
    public static final String ACTION_SYSTEM_INTERACTIVE_CHANGED = "system_interactive_changed";
    public static final String ACTION_SYSTEM_NETWORK_CHANGED = "system_network_changed";
    public static final String ACTION_SYSTEM_REBOOTED = "system_rebooted";
    public static final String ACTION_SYSTEM_SHUTDOWN = "system_shutdown";
    public static final String ACTION_SYSTEM_UPDATE_POLICY_CHANGED = "system_update_policy_changed";
    public static final String ACTION_UPDATER_GET_CURRENT_STATE = "updater_get_current_state";
    public static final String ACTION_UPDATE_NOTIFICATION = "com.oculus.updater.STATE_NOTIFICATION";
    public static final String ACTION_UPDATE_WINDOW_ALARM = "UpdateWindowAlarm";
    public static final String ACTION_VERIFYING_UPDATE = "apply_verifying_update";
    public static final String ACTION_WIFI_WAIT_ALARM = "WifiWaitAlarm";
    public static final String CHANNEL_ID = "release_channel_id";
    public static final String CHANNEL_NAME = "release_channel_name";
    public static final String EXTRA_ERROR_DETAILS_ID = "error_details";
    public static final String EXTRA_FAILURE_CODE = "failure_code";
    public static final String EXTRA_FAILURE_MESSAGE = "failure_message";
    public static final String EXTRA_FULL_UPDATE = "full_update";
    public static final String EXTRA_PERCENT_COMPLETE = "percent_complete";
    public static final String EXTRA_PROGRESS_ID = "progress";
    public static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    public static final String EXTRA_STATE_ID = "state";
    public static final String KEY_APPLYING_UPDATE = "applying_update";
    public static final String KEY_ARE_UPDATES_AVAILABLE = "ota_are_updates_available";
    public static final String KEY_CHECKING_FOR_UPDATES = "checking_for_updates";
    public static final String KEY_CURRENT_OS_VERSION = "current_os_version";
    public static final String KEY_ERROR_MESSAGE = "error_message";
    public static final String KEY_ERROR_WHILE_APPLYING_UPDATE = "error_while_applying_update";
    public static final String KEY_ERROR_WHILE_CHECKING_FOR_UPDATES = "error_while_checking_for_updates";
    public static final String KEY_NO_UPDATES_AVAILABLE = "no_updates_available";
    public static final String KEY_TIME_SINCE_LAST_UPDATE_CHECK_MSEC = "time_since_last_update_check_msec";
    public static final String KEY_TIME_SINCE_LAST_UPDATE_MSEC = "time_since_last_update_msec";
    public static final String KEY_UPDATER_STATE = "ota_updater_state";
    public static final String KEY_UPDATE_AVAILABILITY_MESSAGE = "msg";
    public static final String KEY_UPDATE_BASE_VERSION = "ota_update_base_version";
    public static final String KEY_UPDATE_SIZE = "ota_update_size";
    public static final String KEY_UPDATE_TARGET_VERSION = "ota_update_target_version";
    public static final String KEY_VERIFYING_UPDATE = "verifying_update";
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_SUCCESS = 0;
    public static final String STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES = "Device is not configured for AB updates";
    public static final String STATE_NOT_ALLOWED_BY_SYSTEM = "state_not_allowed_by_system";
    public static final String STATE_OTA_DISABLED_BY_USER = "state_ota_disabled";
    public static final String STATE_READY_TO_CHECK_FOR_OTA = "state_ready_to_check_for_ota";
    public static final String STATE_UPDATE_IN_PROGRESS = "ota_update_in_progress";
    public static final String STATE_WAITING_FOR_REBOOT = "waiting_for_reboot";
    public static final String STATE_WIFI_DISABLED = "Check for updates stopped due to no wifi connection";
    public static final String UPDATER_PACKAGE = "com.oculus.updater";
    public static final String UPDATE_SERVICE = "com.oculus.updater.core.os.OSUpdateService";
}
