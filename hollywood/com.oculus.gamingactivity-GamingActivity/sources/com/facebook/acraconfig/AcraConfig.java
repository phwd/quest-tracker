package com.facebook.acraconfig;

import android.content.Context;
import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
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
    private static final String ACRACONFIG_NIGHTWATCH_PROCESS_TYPE = "acraconfig_nightwatch_process_type";
    private static final String ACRACONFIG_USE_FAST_FS_SYNC_HOOKS = "acraconfig_use_fast_fs_sync_hooks";
    private static final String ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME = "acraconfig_use_multipart_enabled";
    private static final String ACRACONFIG_USE_PINNED_SSL_PROVIDER = "acraconfig_use_pinned_ssl_provider";
    private static final String ACRACONFIG_USE_ZSTD_ENABLED_FILENAME = "acraconfig_use_zstd_enabled";
    private static final String ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME = "acraconfig_zero_crashlog_blocked";
    private static final String ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT = "acraconfig_avoid_spawn_process_to_collect_logcat";
    private static final String ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD = "acraconfig_logcat_max_number_of_lines_ufad";
    private static final String ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS = "android_acra_delete_corrupted_minidumps";
    private static final String ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES = "nightwatch_monitor_resources";
    private static final String ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS = "nightwatch_monitor_resources_interval_ms";
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
    public static int DEFAULT_MONITOR_RESOURCES_INTERVAL_MS = ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS;
    private static final int DEFAULT_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS = 0;
    public static int DEFAULT_NIGHTWATCH_PROCESS_TYPE = 0;
    public static boolean DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = false;
    private static final String NIGHTWATCH_TICK_INFO_COUNT = "nightwatch_tick_info_count";
    private static final String NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = "acra_nightwatch_turn_off_fast_jni_methods";

    public static boolean isMultipartEnabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME);
    }

    public static boolean isCallingExternalProcessesForCrashReportsDisabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_DISABLE_CALLING_EXTERNAL_PROCS_DURING_REPORTING);
    }

    public static boolean isZstdEnabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_USE_ZSTD_ENABLED_FILENAME);
    }

    public static boolean isLogCatNativeCrashEnabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_LOGCAT_NATIVE_CRASH_ENABLED_FILENAME);
    }

    public static boolean isLogcatInterceptorEnabled(Context ctx) {
        return GkBootstrap.checkAndClearGk(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_ENABLED_FILENAME, false);
    }

    public static boolean isLogcatInterceptorAfterCrashEnabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_AFTER_CRASH_ENABLED_FILENAME);
    }

    public static int nativeCrashPeriodicReportIntervalMins(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS, 0);
    }

    public static int logcatInterceptorRingSize(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_RING_SIZE, DEFAULT_LOGCAT_INTERCEPTOR_RING_SIZE);
    }

    public static int logcatNumberOfLines(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_LOGCAT_NUMBER_OF_LINES, DEFAULT_LOGCAT_NUMBER_OF_LINES);
    }

    public static boolean isZeroCrashlogBlocked(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME);
    }

    public static boolean disableFSSyncSyscalls(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS);
    }

    public static boolean disableFSSyncSyscallsVps(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS_VPS);
    }

    public static boolean shouldDeviceStoreReports(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_SAVE_NATIVE_REPORTS);
    }

    public static boolean shouldDeleteCorruptedMinidumps(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS);
    }

    public static boolean shouldCollectMaxNumberOfLinesOnUfad(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD);
    }

    public static boolean shouldStopAnrDetectorWhenReporting(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_STOP_ANR_DETECTOR_DURING_ERROR_REPORTING);
    }

    public static boolean shouldReportOldANRs(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_SHOULD_REPORT_OLD_ANRS);
    }

    public static boolean shouldSkipReportOnSocketTimeout(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_SKIP_REPORT_ON_SOCKET_TIMEOUT);
    }

    public static boolean useFSSyncFastHooks(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_USE_FAST_FS_SYNC_HOOKS);
    }

    public static boolean shouldEnableNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_ENABLE_NIGHTWATCH);
    }

    public static int getNightwatchProcessType(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_NIGHTWATCH_PROCESS_TYPE, DEFAULT_NIGHTWATCH_PROCESS_TYPE);
    }

    public static boolean getShouldNightWatchUseFastOrCriticalJniMethods(Context ctx) {
        return !GkBootstrap.checkIfGkEnabled(ctx, NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS, DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS);
    }

    public static boolean shouldUseUploadService(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_USE_UPLOAD_SERVICE);
    }

    public static boolean shouldUseSetSidOnNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_NIGHTWATCH_USE_SETSID);
    }

    public static boolean shouldUseMmapOnNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_NIGHTWATCH_USE_MMAP);
    }

    public static boolean shouldMonitorResourcesOnNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES);
    }

    public static int monitorResourcesIntervalMs(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS, DEFAULT_MONITOR_RESOURCES_INTERVAL_MS);
    }

    public static boolean shouldLazyFieldsOverwriteExistingValues(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_SHOULD_LAZY_FIELDS_OVERWRITE_EXISTING_VALUES);
    }

    public static boolean shouldEnableAnrDetector(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_SHOULD_ENABLE_ANR_DETECTOR, true);
    }

    public static boolean shouldUseAslSessionIdOnNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_NIGHTWATCH_USE_ASL_SESSION_ID);
    }

    public static boolean shouldUseLssExecOnNightwatch(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_NIGHTWATCH_USE_LSS_ON_EXEC);
    }

    public static boolean avoidSpawnProcessToCollectLogcat(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT, false);
    }

    public static int getMaximumReportAgeSeconds(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_MAX_REPORT_AGE_SECONDS, 604800);
    }

    public static int getActionOnOldReports(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, ACRACONFIG_ACTION_ON_OLD_REPORTS, 0);
    }

    public static boolean shouldUsePinnedSSLProvider(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, ACRACONFIG_USE_PINNED_SSL_PROVIDER, false);
    }

    public static int getNightwatchTickInfoCount(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, NIGHTWATCH_TICK_INFO_COUNT, 0);
    }

    public static void setMultipartEnabled(Context ctx, boolean useMultipart) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_USE_MULTIPART_ENABLED_FILENAME, useMultipart);
    }

    public static void setCallingExternalProcessesForCrashReportsDisabled(Context ctx, boolean value) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_DISABLE_CALLING_EXTERNAL_PROCS_DURING_REPORTING, value);
    }

    public static void setZstdEnabled(Context ctx, boolean zstdEnabled) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_USE_ZSTD_ENABLED_FILENAME, zstdEnabled);
    }

    public static void setLogCatNativeCrashEnabled(Context ctx, boolean enableLogcat) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_LOGCAT_NATIVE_CRASH_ENABLED_FILENAME, enableLogcat);
    }

    public static void setLogcatInterceptorEnabled(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_ENABLED_FILENAME, enable);
    }

    public static void setLogcatInterceptorAfterCrashEnabled(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_AFTER_CRASH_ENABLED_FILENAME, enable);
    }

    public static void setNativeCrashPeriodicReportIntervalMins(Context ctx, int interval) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_NATIVE_CRASH_PERIODIC_REPORT_INTERVAL_MINS, interval);
    }

    public static void setLogcatInterceptorRingSize(Context ctx, int size) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_LOGCAT_INTERCEPTOR_RING_SIZE, size);
    }

    public static void setLogcatNumberOfLines(Context ctx, int numLines) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_LOGCAT_NUMBER_OF_LINES, numLines);
    }

    public static void setZeroCrashlogBlocked(Context ctx, boolean crashlogBlocked) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_ZERO_CRASHLOG_BLOCKED_FILENAME, crashlogBlocked);
    }

    public static void setDisableFSSyncSyscalls(Context ctx, boolean disableFSSyncSyscalls) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS, disableFSSyncSyscalls);
    }

    public static void setDisableFSSyncSyscallsVps(Context ctx, boolean disableFSSyncSyscalls) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_DISABLE_FS_SYNC_SYSCALLS_VPS, disableFSSyncSyscalls);
    }

    public static void setShouldDeviceStoreReports(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_SAVE_NATIVE_REPORTS, enable);
    }

    public static void setShouldDeleteCorruptedMinidumps(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_DELETE_CORRUPTED_MINIDUMPS, enable);
    }

    public static void setShouldCollectMaxNumberOfLinesOnUfad(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_COLLECT_MAX_NUMBER_OF_LINES_IN_UFAD, enable);
    }

    public static void setShouldStopAnrDetectorWhenReporting(Context ctx, boolean stop) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_STOP_ANR_DETECTOR_DURING_ERROR_REPORTING, stop);
    }

    public static void setShouldReportOldAnrs(Context ctx, boolean report) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_SHOULD_REPORT_OLD_ANRS, report);
    }

    public static void setShouldSkipReportOnSocketTimeout(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_SKIP_REPORT_ON_SOCKET_TIMEOUT, enable);
    }

    public static void setFSSyncFastHooks(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_USE_FAST_FS_SYNC_HOOKS, enable);
    }

    public static void setShouldEnableNightwatch(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_ENABLE_NIGHTWATCH, enable);
    }

    public static void setNightwatchProcessType(Context ctx, int processType) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_NIGHTWATCH_PROCESS_TYPE, processType);
    }

    public static void setShouldTurnOffFastOrCriticalJniMethods(Context ctx, boolean shouldTurnOffFastOrCriticalJniMethod) {
        GkBootstrap.updateGKFlagFile(ctx, NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS, shouldTurnOffFastOrCriticalJniMethod);
    }

    public static void setShouldUseUploadService(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_USE_UPLOAD_SERVICE, enable);
    }

    public static void setShouldNightwatchUseSetSid(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_NIGHTWATCH_USE_SETSID, enable);
    }

    public static void setMonitorResourcesIntervalMs(Context ctx, int interval_ms) {
        GkBootstrap.updateGKValueInt(ctx, ANDROID_ACRA_NIGHTWATCH_MONITOR_RESOURCES_INTERVAL_MS, interval_ms);
    }

    public static void setShouldLazyFieldsOverwriteExistingValues(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_SHOULD_LAZY_FIELDS_OVERWRITE_EXISTING_VALUES, enable);
    }

    public static void setShouldEnableAnrDetector(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_SHOULD_ENABLE_ANR_DETECTOR, enable);
    }

    public static void setAvoidSpawnProcessToCollectLogcat(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_AVOID_SPAWN_PROCESS_TO_COLLECT_LOGCAT, enable);
    }

    public static void setShouldUseAslSessionIdOnNightwatch(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_NIGHTWATCH_USE_ASL_SESSION_ID, enable);
    }

    public static void setShouldUseLssExecOnNightwatch(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ANDROID_ACRA_NIGHTWATCH_USE_LSS_ON_EXEC, enable);
    }

    public static void setMaximumReportAgeSeconds(Context ctx, int ageSeconds) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_MAX_REPORT_AGE_SECONDS, ageSeconds);
    }

    public static void setActionOnOldReports(Context ctx, int action) {
        GkBootstrap.updateGKValueInt(ctx, ACRACONFIG_ACTION_ON_OLD_REPORTS, action);
    }

    public static void setShouldUsePinnedSSLProvider(Context ctx, boolean enable) {
        GkBootstrap.updateGKFlagFile(ctx, ACRACONFIG_USE_PINNED_SSL_PROVIDER, enable);
    }
}
