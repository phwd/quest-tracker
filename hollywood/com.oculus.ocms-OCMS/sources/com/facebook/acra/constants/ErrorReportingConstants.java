package com.facebook.acra.constants;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ErrorReportingConstants {
    public static final String ACTIVE_PROCESSES = "active_processes";
    public static final String ALL_PROCESS_RESIDENT_ANONYMOUS_MEMORY_KB = "all_process_resident_anonymous_kb";
    public static final String ANR_AM_CONFIRMATION_EXPIRED_UPTIME = "anr_am_expired_uptime";
    public static final String ANR_ASYNC_BROADCAST_RECEIVERS = "anr_async_broadcast_receivers";
    public static final String ANR_DEFAULT_RECOVERY_DELAY_VAL = "-1";
    public static final String ANR_DETECTED_PRE_GKSTORE = "anr_detected_pre_gkstore";
    public static final String ANR_DETECTED_UPTIME = "anr_detected_uptime";
    public static final String ANR_DETECTOR_ACTUAL_START_TIME = "anr_detector_actual_start_time";
    public static final String ANR_DETECTOR_ID = "anr_detector_id";
    public static final String ANR_DETECTOR_START_TIME = "anr_detector_start_time";
    public static final String ANR_DETECTOR_SWITCH_TIME = "anr_detector_switch_time";
    public static final String ANR_DETECT_TIME_TAG = "anr_detect_time_tag";
    public static final String ANR_EXTRA_SIGQUIT_UPTIME = "anr_extra_sigquit_uptime";
    public static final String ANR_JAVA_CALLBACK_UPTIME = "anr_java_callback_uptime";
    public static final String ANR_LOOPER_MONITOR_STACKS = "anr_looper_monitor_stacks";
    public static final String ANR_LOOPER_PROFILER_DATA = "anr_looper_profiler_data";
    public static final String ANR_LOOPER_PROFILER_TIME_SINCE_LAST_STALL = "anr_looper_profiler_time_since_last_stall";
    public static final String ANR_MAIN_THREAD_UNBLOCKED_UPTIME = "anr_main_thread_unblocked_uptime";
    public static final String ANR_OTHER_PROCESS_ERROR_PREFIX = "anr_other_process_error_state_";
    public static final String ANR_PARTIAL_REPORT = "partial_anr_report";
    public static final String ANR_PROCESS_ERROR_DETECTED = "anr_process_error_detected_time";
    public static final String ANR_PROCESS_ERROR_DETECTION_FAILURE_CAUSE = "anr_process_error_detection_failure_cause";
    public static final String ANR_PROCESS_ERROR_DETECTION_FAILURE_TIME = "anr_process_error_detection_failure_time";
    public static final String ANR_PROCESS_ERROR_DETECTION_START_TIME = "anr_process_error_started_time";
    public static final String ANR_PROC_STAT_STATE_TAG = "anr_proc_stat_state_tag";
    public static final String ANR_PROC_STAT_TAG = "anr_proc_stat_tag";
    public static final String ANR_RECOVERY_DELAY_TAG = "anr_recovery_delay";
    public static final String ANR_SIGQUIT_RECORDS = "anr_sigquit_records";
    public static final String ANR_STARTED_IN_FOREGROUND = "anr_started_in_foreground";
    public static final String ANR_STARTED_IN_FOREGROUND_V2 = "anr_started_in_foreground_v2";
    public static final String ANR_SYSTEM_ERROR_MSG = "anr_system_error_msg";
    public static final String ANR_SYSTEM_TAG = "anr_system_tag";
    public static final String ANR_SYSTEM_TRACES_PRESENT = "anr_system_traces_present";
    public static final String ANR_TIMEOUT_DELAY = "anr_timeout_delay";
    public static final String ANR_TRACE_POSITION = "anr_trace_position";
    public static final String ANR_WITH_SIGQUIT_TRACES = "anr_with_sigquit_traces";
    public static final String APP_BACKGROUNDED = "app_backgrounded";
    public static final String APP_ERROR_REPORTING_DIR_NAME = "errorreporting";
    public static final String APP_NAME_KEY = "app";
    public static final String ASL_APP_IN_FOREGROUND = "asl_app_in_foreground";
    public static final String ASL_APP_IN_FOREGROUND_V2 = "asl_app_in_foreground_v2";
    public static final String BLACK_BOX_TRACE_ID = "black_box_trace";
    public static final String BREAKPAD_DIRECTORY = "minidumps";
    public static final String BUILD_TYPE = "build_type";
    public static final String CACHED_REPORTFILE_EXTENSION = ".cachedreport";
    public static final String CUSTOM_DELAYED_MESSAGES_SENT = "custom_delayed_messages_sent";
    public static final String DEVICE_ID_KEY = "marauder_device_id";
    public static final String ENDPOINT = "endpoint";
    public static final String FACEBOOK_APP_ID_KEY = "fb_app_id";
    public static final String FIRST_SIGQUIT_FROM_PROCESSES = "first_sigquit";
    public static final String GRANULAR_EXPOSURES = "granular_exposures";
    public static final String GRAPHQLSTORY_STORY_ID = "graphql_story_id";
    public static final String INSTALLED_FACEBOOK_APKS = "installed_fb_apks";
    public static final String IS_JS_ERROR = "is_js_error";
    public static final String LAST_START_TEMP = "last_start_temp";
    public static final String LONG_STALL_TRACE_ID = "long_stall_trace";
    public static final int MAX_TRACE_UPLOAD = 5;
    public static final String NAV_MODULE = "nav_module";
    public static final String PREV_APP_VERSION_STR = "app_version_name_prev";
    public static final String PREV_APP_VERSION_UNKNOWN = "unknown";
    public static final String REPORT_FURY_TRACES_FILE_PROP = "fb.fury_stacktraces_filename";
    public static final String REPORT_SOURCE = "report_source";
    public static final String REPORT_SOURCE_OVERRIDE_PROP = "fb.report_source";
    public static final String REPORT_SOURCE_REF = "report_source_ref";
    public static final String REPORT_SOURCE_REF_DIR_NAME = "report_source";
    public static final String REPORT_SOURCE_REF_FILE_NAME = "report_source_ref.txt";
    public static final String REPORT_SOURCE_REF_LACRIMA_PREFIX = "report_source_ref=";
    public static final String SOFT_ERROR_CATEGORY = "soft_error_category";
    public static final String SOFT_ERROR_MESSAGE = "soft_error_message";
    public static final String SOFT_ERROR_OCCURRENCE_COUNT = "sample_rate";
    public static final String SPLASH_SCREEN_DISMISSED = "splash_screen_dismissed";
    public static final String STALL_DURATION_KEY = "stall_duration";
    public static final String STRICT_MODE_CATEGORY = "strict_mode_category";
    public static final String STRICT_MODE_MESSAGE = "strict_mode_message";
    public static final int STRICT_MODE_REPORTING_FREQUENCY = 1;
    public static final String SYS_SHUTDOWN_REQUESTED = "sys_shutdown_requested";
    public static final String TRACE_UPLOAD_DIR = "traces_upload";
    @Deprecated
    public static final String USER_ID_KEY = "uid";

    private ErrorReportingConstants() {
    }
}
