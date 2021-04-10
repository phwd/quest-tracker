package com.facebook.acraconfig;

import android.content.Context;
import com.facebook.gk.coldstartbootstrap.GkBootstrap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AcraConfig {
    public static int DEFAULT_MONITOR_RESOURCES_INTERVAL_MS = 500;
    public static int DEFAULT_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS = 0;
    public static boolean DEFAULT_NIGHTWATCH_SPLIT_MMAP = false;
    public static boolean DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = false;

    public static boolean isLogcatInterceptorAfterCrashEnabled(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_logcat_interceptor_after_crash_enabled");
    }

    public static int nativeCrashPeriodicReportIntervalMins(Context context) {
        return GkBootstrap.getGkValueInt(context, "acraconfig_logcat_native_crash_periodic_interval_mins", 0);
    }

    public static boolean disableFSSyncSyscalls(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_disable_fs_sync_syscalls");
    }

    public static boolean disableFSSyncSyscallsVps(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_disable_fs_sync_syscalls_vps");
    }

    public static boolean shouldDeviceStoreReports(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "android_acra_save_native_reports");
    }

    public static boolean shouldDeleteCorruptedMinidumps(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "android_acra_delete_corrupted_minidumps");
    }

    public static boolean shouldReportOldANRs(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_report_old_anrs");
    }

    public static boolean useFSSyncFastHooks(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_use_fast_fs_sync_hooks");
    }

    public static boolean shouldEnableNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_enable_nightwatch");
    }

    public static boolean shouldNightwatchSplitMmap(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "nightwatch_split_mmap", DEFAULT_NIGHTWATCH_SPLIT_MMAP);
    }

    public static int getNightwatchMmapUpdateMinIntervalMs(Context context) {
        return GkBootstrap.getGkValueInt(context, "nightwatch_mmap_update_min_interval_ms", DEFAULT_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS);
    }

    public static boolean getShouldNightWatchUseFastOrCriticalJniMethods(Context context) {
        return !GkBootstrap.checkIfGkEnabled(context, "acra_nightwatch_turn_off_fast_jni_methods", DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS);
    }

    public static boolean shouldUseSetSidOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_nightwatch_use_setsid");
    }

    public static boolean shouldUseMmapOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "nightwatch_use_mmap");
    }

    public static boolean shouldMonitorResourcesOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "nightwatch_monitor_resources");
    }

    public static int monitorResourcesIntervalMs(Context context) {
        return GkBootstrap.getGkValueInt(context, "nightwatch_monitor_resources_interval_ms", DEFAULT_MONITOR_RESOURCES_INTERVAL_MS);
    }

    public static boolean shouldUseAslSessionIdOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_nightwatch_use_asl_session_id");
    }

    public static boolean shouldUseLssExecOnNightwatch(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_nightwatch_use_lss_on_exec");
    }

    public static boolean avoidSpawnProcessToCollectLogcat(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_avoid_spawn_process_to_collect_logcat", false);
    }

    public static int getMaximumReportAgeSeconds(Context context) {
        return GkBootstrap.getGkValueInt(context, "acraconfig_max_report_age_seconds", 604800);
    }

    public static int getActionOnOldReports(Context context) {
        return GkBootstrap.getGkValueInt(context, "acraconfig_action_on_old_reports", 0);
    }

    public static int getNightwatchTickInfoCount(Context context) {
        return GkBootstrap.getGkValueInt(context, "nightwatch_tick_info_count", 0);
    }
}
