package com.facebook.acraconfig;

import android.content.Context;
import com.facebook.gk.coldstartbootstrap.GkBootstrap;

public class AcraConfig {
    public static int DEFAULT_MONITOR_RESOURCES_INTERVAL_MS = 500;
    public static int DEFAULT_NIGHTWATCH_MMAP_UPDATE_MIN_INTERVAL_MS = 0;
    public static boolean DEFAULT_NIGHTWATCH_SPLIT_MMAP = false;
    public static boolean DEFAULT_NIGHTWATCH_TURN_OFF_FAST_CRITICAL_JNI_METHODS = false;

    public static boolean isLogcatInterceptorAfterCrashEnabled(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, "acraconfig_logcat_interceptor_after_crash_enabled");
    }

    public static int nativeCrashPeriodicReportIntervalMins(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, "acraconfig_logcat_native_crash_periodic_interval_mins", 0);
    }

    public static boolean shouldDeviceStoreReports(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, "android_acra_save_native_reports");
    }

    public static boolean shouldDeleteCorruptedMinidumps(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, "android_acra_delete_corrupted_minidumps");
    }

    public static boolean shouldReportOldANRs(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, "acraconfig_report_old_anrs");
    }

    public static boolean avoidSpawnProcessToCollectLogcat(Context ctx) {
        return GkBootstrap.checkIfGkEnabled(ctx, "acraconfig_avoid_spawn_process_to_collect_logcat", false);
    }

    public static int getMaximumReportAgeSeconds(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, "acraconfig_max_report_age_seconds", 604800);
    }

    public static int getActionOnOldReports(Context ctx) {
        return GkBootstrap.getGkValueInt(ctx, "acraconfig_action_on_old_reports", 0);
    }
}
