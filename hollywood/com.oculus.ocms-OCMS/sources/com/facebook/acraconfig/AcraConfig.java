package com.facebook.acraconfig;

import android.content.Context;
import com.facebook.gk.coldstartbootstrap.GkBootstrap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AcraConfig {
    private static final String ACRACONFIG_ACTION_ON_OLD_REPORTS = "acraconfig_action_on_old_reports";
    private static final String ACRACONFIG_DISABLE_CALLING_EXTERNAL_PROCS_DURING_REPORTING = "acraconfig_disable_calling_external_procs_during_reporting";
    private static final String ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS = "acraconfig_disable_fs_sync_syscalls";
    private static final String ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS_VPS = "acraconfig_disable_fs_sync_syscalls_vps";
    private static final String ACRACONFIG_ENABLE_NIGHTWATCH = "acraconfig_enable_nightwatch";
    private static final String ACRACONFIG_LOGCAT_INTERCEPTOR_AFTER_CRASH_ENABLED_FILENAME = "acraconfig_logcat_interceptor_after_crash_enabled";
    private static final String ACRACONFIG_LOGCAT_INTERCEPTOR_ENABLED_FILENAME = "acraconfig_logcat_interceptor_enabled";
    private static final String ACRACONFIG_LOGCAT_INTERCEPTOR_RING_SIZE = "acraconfig_logcat_interceptor_ring_size";
    private static final String ACRACONFIG_LOGCAT_NATIVE_CRASH_ENABLED_FILENAME = "acraconfig_logcat_native_crash_enabled_enabled";
    private static final String ACRACONFIG_LOGCAT_NUMBER_OF_LINES = "acraconfig_logcat_number_of_lines";
    private static final String ACRACONFIG_MAX_REPORT_AGE_SECONDS = "acraconfig_max_report_age_seconds";
    private static final String ACRACONFIG_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS = "acraconfig_logcat_native_crash_periodic_interval_mins";
    private static final String ACRACONFIG_USE_FAST_FS_SYNC_HOOKS = "acraconfig_use_fast_fs_sync_hooks";
    private static final String ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME = "acraconfig_use_multipart_enabled";
    private static final String ACRACONFIG_USE_PINNED_SSL_PROVIDER = "acraconfig_use_pinned_ssl_provider";
    private static final String ACRACONFIG_USE_ZSTD_ENABLED_FILENAME = "acraconfig_use_zstd_enabled";
    private static final String ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME = "acraconfig_zero_crashlog_blocked";
    private static final String ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT = "acraconfig_avoid_spawn_process_to_collect_logcat";
    private static final String ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD = "acraconfig_logcat_max_number_of_lines_ufad";
    private static final String ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS = "android_acra_delete_corrupted_minidumps";
    private static final String ANDROID_ACRA_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS = "nightwatch_mmap_update_min_interval_ms";
    private static final String ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES = "nightwatch_monitor_resources";
    private static final String ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS = "nightwatch_monitor_resources_interval_ms";
    private static final String ANDROID_ACRA_NIGHTWATCH_SPLIT_MMAP = "nightwatch_split_mmap";
    private static final String ANDROID_ACRA_NIGHTWATCH_USE_ASL_SESSION_ID = "acraconfig_nightwatch_use_asl_session_id";
    private static final String ANDROID_ACRA_NIGHTWATCH_USE_LSS_ON_EXEC = "acraconfig_nightwatch_use_lss_on_exec";
    private static final String ANDROID_ACRA_NIGHTWATCH_USE_MMAP = "nightwatch_use_mmap";
    private static final String ANDROID_ACRA_NIGHTWATCH_USE_SETSID = "acraconfig_nightwatch_use_setsid";
    private static final String ANDROID_ACRA_SAVE_NATIVE_REPORTS = "android_acra_save_native_reports";
    private static final String ANDROID_ACRA_SHOULD_ENABLE_ANR_DETECTOR = "acraconfig_enable_anr_detector";
    private static final String ANDROID_ACRA_SHOULD_LAZY_FIELDS_OVERWRITE_EXISTING_VALUES = "should_lazy_fields_overwrite_existing_values";
    private static final String ANDROID_ACRA_SHOULD_REPORT_OLD_ANRS = "acraconfig_report_old_anrs";
    private static final String ANDROID_ACRA_SKIP_REPORT_ON_SOCKET_TIMEOUT = "acraconfig_skip_report_on_socket_timeout";
    private static final String ANDROID_ACRA_STOP_ANR_DETECTOR_DURING_ERROR_REPORTING = "acraconfig_stop_anr_detector_on_error_reporting";
    private static final String ANDROID_ACRA_USE_UPLOAD_SERVICE = "acraconfig_use_upload_service";
    public static final int DEFAULT_LOGCAT_INTERCEPTOR_RING_SIZE = 204800;
    public static final int DEFAULT_LOGCAT_NUMBER_OF_LINES = 200;
    public static int DEFAULT_MONITOR_RESOURCES_INTERVAL_MS = 500;
    private static final int DEFAULT_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS = 0;
    public static int DEFAULT_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS = 0;
    public static boolean DEFAULT_NIGHTWATCH_SPLIT_MMAP = false;
    public static boolean DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = false;
    private static final String NIGHTWATCH_TICK_INFO_COUNT = "nightwatch_tick_info_count";
    private static final String NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = "acra_nightwatch_turn_off_fast_jni_methods";

    public static boolean isMultipartEnabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME);
    }

    public static boolean isCallingExternalProcessesForCrashReportsDisabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_DISABLE_CALLING_EXTERNAL_PROCS_DURING_REPORTING);
    }

    public static boolean isZstdEnabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_USE_ZSTD_ENABLED_FILENAME);
    }

    public static boolean isLogCatNativeCrashEnabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_LOGCAT_NATIVE_CRASH_ENABLED_FILENAME);
    }

    public static boolean isLogcatInterceptorEnabled(Context context) {
        return GkBootstrap.checkAndClearGk(context, ACRACONFIG_LOGCAT_INTERCEPTOR_ENABLED_FILENAME, false);
    }

    public static boolean isLogcatInterceptorAfterCrashEnabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_LOGCAT_INTERCEPTOR_AFTER_CRASH_ENABLED_FILENAME);
    }

    public static int nativeCrashPeriodicReportIntervalMins(Context context) {
        return GkBootstrap.getGkValueInt(context, ACRACONFIG_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS, 0);
    }

    public static int logcatInterceptorRingSize(Context context) {
        return GkBootstrap.getGkValueInt(context, ACRACONFIG_LOGCAT_INTERCEPTOR_RING_SIZE, DEFAULT_LOGCAT_INTERCEPTOR_RING_SIZE);
    }

    public static int logcatNumberOfLines(Context context) {
        return GkBootstrap.getGkValueInt(context, ACRACONFIG_LOGCAT_NUMBER_OF_LINES, 200);
    }

    public static boolean isZeroCrashlogBlocked(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME);
    }

    public static boolean disableFSSyncSyscalls(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS);
    }

    public static boolean disableFSSyncSyscallsVps(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS_VPS);
    }

    public static boolean shouldDeviceStoreReports(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_SAVE_NATIVE_REPORTS);
    }

    public static boolean shouldDeleteCorruptedMinidumps(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS);
    }

    public static boolean shouldCollectMaxNumberOfLinesOnUfad(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD);
    }

    public static boolean shouldStopAnrDetectorWhenReporting(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_STOP_ANR_DETECTOR_DURING_ERROR_REPORTING);
    }

    public static boolean shouldReportOldANRs(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_SHOULD_REPORT_OLD_ANRS);
    }

    public static boolean shouldSkipReportOnSocketTimeout(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_SKIP_REPORT_ON_SOCKET_TIMEOUT);
    }

    public static boolean useFSSyncFastHooks(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_USE_FAST_FS_SYNC_HOOKS);
    }

    public static boolean shouldEnableNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_ENABLE_NIGHTWATCH);
    }

    public static boolean shouldNightwatchSplitMmap(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_SPLIT_MMAP, DEFAULT_NIGHTWATCH_SPLIT_MMAP);
    }

    public static int getNightwatchMmapUpdateMinIntervalMs(Context context) {
        return GkBootstrap.getGkValueInt(context, ANDROID_ACRA_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS, DEFAULT_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS);
    }

    public static boolean getShouldNightWatchUseFastOrCriticalJniMethods(Context context) {
        return !GkBootstrap.checkIfGkEnabled(context, NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS, DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS);
    }

    public static boolean shouldUseUploadService(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_USE_UPLOAD_SERVICE);
    }

    public static boolean shouldUseSetSidOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_USE_SETSID);
    }

    public static boolean shouldUseMmapOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_USE_MMAP);
    }

    public static boolean shouldMonitorResourcesOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES);
    }

    public static int monitorResourcesIntervalMs(Context context) {
        return GkBootstrap.getGkValueInt(context, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS, DEFAULT_MONITOR_RESOURCES_INTERVAL_MS);
    }

    public static boolean shouldLazyFieldsOverwriteExistingValues(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_SHOULD_LAZY_FIELDS_OVERWRITE_EXISTING_VALUES);
    }

    public static boolean shouldEnableAnrDetector(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_SHOULD_ENABLE_ANR_DETECTOR, true);
    }

    public static boolean shouldUseAslSessionIdOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_USE_ASL_SESSION_ID);
    }

    public static boolean shouldUseLssExecOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_NIGHTWATCH_USE_LSS_ON_EXEC);
    }

    public static boolean avoidSpawnProcessToCollectLogcat(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT, false);
    }

    public static int getMaximumReportAgeSeconds(Context context) {
        return GkBootstrap.getGkValueInt(context, ACRACONFIG_MAX_REPORT_AGE_SECONDS, 604800);
    }

    public static int getActionOnOldReports(Context context) {
        return GkBootstrap.getGkValueInt(context, ACRACONFIG_ACTION_ON_OLD_REPORTS, 0);
    }

    public static boolean shouldUsePinnedSSLProvider(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, ACRACONFIG_USE_PINNED_SSL_PROVIDER, false);
    }

    public static int getNightwatchTickInfoCount(Context context) {
        return GkBootstrap.getGkValueInt(context, NIGHTWATCH_TICK_INFO_COUNT, 0);
    }

    public static void setMultipartEnabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME, z);
    }

    public static void setCallingExternalProcessesForCrashReportsDisabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_DISABLE_CALLING_EXTERNAL_PROCS_DURING_REPORTING, z);
    }

    public static void setZstdEnabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_USE_ZSTD_ENABLED_FILENAME, z);
    }

    public static void setLogCatNativeCrashEnabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_LOGCAT_NATIVE_CRASH_ENABLED_FILENAME, z);
    }

    public static void setLogcatInterceptorEnabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_LOGCAT_INTERCEPTOR_ENABLED_FILENAME, z);
    }

    public static void setLogcatInterceptorAfterCrashEnabled(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_LOGCAT_INTERCEPTOR_AFTER_CRASH_ENABLED_FILENAME, z);
    }

    public static void setNativeCrashPeriodicReportIntervalMins(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ACRACONFIG_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS, i);
    }

    public static void setLogcatInterceptorRingSize(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ACRACONFIG_LOGCAT_INTERCEPTOR_RING_SIZE, i);
    }

    public static void setLogcatNumberOfLines(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ACRACONFIG_LOGCAT_NUMBER_OF_LINES, i);
    }

    public static void setZeroCrashlogBlocked(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME, z);
    }

    public static void setDisableFSSyncSyscalls(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS, z);
    }

    public static void setDisableFSSyncSyscallsVps(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS_VPS, z);
    }

    public static void setShouldDeviceStoreReports(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_SAVE_NATIVE_REPORTS, z);
    }

    public static void setShouldDeleteCorruptedMinidumps(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS, z);
    }

    public static void setShouldCollectMaxNumberOfLinesOnUfad(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD, z);
    }

    public static void setShouldStopAnrDetectorWhenReporting(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_STOP_ANR_DETECTOR_DURING_ERROR_REPORTING, z);
    }

    public static void setShouldReportOldAnrs(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_SHOULD_REPORT_OLD_ANRS, z);
    }

    public static void setShouldSkipReportOnSocketTimeout(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_SKIP_REPORT_ON_SOCKET_TIMEOUT, z);
    }

    public static void setFSSyncFastHooks(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_USE_FAST_FS_SYNC_HOOKS, z);
    }

    public static void setShouldEnableNightwatch(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_ENABLE_NIGHTWATCH, z);
    }

    public static void setNightwatchSplitMmap(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_NIGHTWATCH_SPLIT_MMAP, z);
    }

    public static void setNightwatchMmapUpdateMinIntervalMs(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ANDROID_ACRA_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS, i);
    }

    public static void setShouldTurnOffFastOrCriticalJniMethods(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS, z);
    }

    public static void setShouldUseUploadService(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_USE_UPLOAD_SERVICE, z);
    }

    public static void setShouldNightwatchUseSetSid(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_NIGHTWATCH_USE_SETSID, z);
    }

    public static void setMonitorResourcesIntervalMs(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS, i);
    }

    public static void setShouldLazyFieldsOverwriteExistingValues(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_SHOULD_LAZY_FIELDS_OVERWRITE_EXISTING_VALUES, z);
    }

    public static void setShouldEnableAnrDetector(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_SHOULD_ENABLE_ANR_DETECTOR, z);
    }

    public static void setAvoidSpawnProcessToCollectLogcat(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT, z);
    }

    public static void setShouldUseAslSessionIdOnNightwatch(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_NIGHTWATCH_USE_ASL_SESSION_ID, z);
    }

    public static void setShouldUseLssExecOnNightwatch(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ANDROID_ACRA_NIGHTWATCH_USE_LSS_ON_EXEC, z);
    }

    public static void setMaximumReportAgeSeconds(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ACRACONFIG_MAX_REPORT_AGE_SECONDS, i);
    }

    public static void setActionOnOldReports(Context context, int i) {
        GkBootstrap.updateGKValueInt(context, ACRACONFIG_ACTION_ON_OLD_REPORTS, i);
    }

    public static void setShouldUsePinnedSSLProvider(Context context, boolean z) {
        GkBootstrap.updateGKFlagFile(context, ACRACONFIG_USE_PINNED_SSL_PROVIDER, z);
    }
}
