package com.facebook.acraconfig;

import android.content.Context;
import com.facebook.gk.coldstartbootstrap.GkBootstrap;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AcraConfig {
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

    public static boolean shouldDeviceStoreReports(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "android_acra_save_native_reports");
    }

    public static boolean shouldDeleteCorruptedMinidumps(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "android_acra_delete_corrupted_minidumps");
    }

    public static boolean shouldReportOldANRs(Context context) {
        return GkBootstrap.checkIfGkEnabled(context, "acraconfig_report_old_anrs");
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
}
