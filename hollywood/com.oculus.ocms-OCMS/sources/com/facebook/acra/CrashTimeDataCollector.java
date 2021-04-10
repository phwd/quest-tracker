package com.facebook.acra;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.facebook.acra.AppComponentStats;
import com.facebook.acra.Spool;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.StatFsUtil;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.common.procread.ProcReader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

public class CrashTimeDataCollector {
    private static final String ANDROID_RUNTIME_ART = "ART";
    private static final String ANDROID_RUNTIME_DALVIK = "DALVIK";
    private static final String ANDROID_RUNTIME_UNKNOWN = "UNKNOWN";
    private static final int DEFAULT_TRACE_COUNT_LIMIT = 5;
    private static final String JAVA_BOOT_CLASS_PATH = "java.boot.class.path";
    private static final String KNOWN_ART_JAR = "/system/framework/core-libart.jar";
    private static final String KNOWN_DALVIK_JAR = "/system/framework/core.jar";
    private static final String PROCESS_NAME_UNSET = "!";
    private static volatile String processNameByAms = "!";
    private static final Map<String, String> sDeviceSpecificFields = Collections.synchronizedMap(new TreeMap());
    private static volatile PackageManagerWrapper sPackageManagerWrapper;

    /* access modifiers changed from: private */
    @TargetApi(19)
    @DoNotOptimize
    public static class Api19Utils {
        private Api19Utils() {
        }

        static boolean isLowRamDevice(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return activityManager != null && activityManager.isLowRamDevice();
        }
    }

    static boolean shouldAddField(String str, CrashReportData crashReportData, AcraReportingConfig acraReportingConfig) {
        return !crashReportData.containsKey(str) && acraReportingConfig.shouldReportField(str);
    }

    static boolean shouldAddLazyField(String str, CrashReportData crashReportData, AcraReportingConfig acraReportingConfig) {
        if (acraReportingConfig.shouldLazyFieldsOverwriteExistingValues()) {
            return acraReportingConfig.shouldReportField(str);
        }
        return shouldAddField(str, crashReportData, acraReportingConfig);
    }

    static void gatherCrashData(ErrorReporter errorReporter, AcraReportingConfig acraReportingConfig, String str, Throwable th, CrashReportData crashReportData, @Nullable Writer writer, @Nullable Spool.FileBeingConsumed fileBeingConsumed, boolean z, boolean z2) throws Exception {
        if (shouldAddField(ReportField.UID, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.UID, errorReporter.getUserId(), crashReportData, writer);
            } catch (Throwable th2) {
                noteReportFieldFailure(crashReportData, ReportField.UID, th2);
            }
        }
        if (shouldAddField(ReportField.CLIENT_UID, crashReportData, acraReportingConfig) && errorReporter.getClientUserId() != null && errorReporter.getClientUserId().length() > 0) {
            try {
                ErrorReporter.put(ReportField.CLIENT_UID, errorReporter.getClientUserId(), crashReportData, writer);
            } catch (Throwable th3) {
                noteReportFieldFailure(crashReportData, ReportField.CLIENT_UID, th3);
            }
        }
        if (shouldAddField(ReportField.STACK_TRACE, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.STACK_TRACE, str, crashReportData, writer);
            } catch (Throwable th4) {
                noteReportFieldFailure(crashReportData, ReportField.STACK_TRACE, th4);
            }
        }
        if (errorReporter.getConstantFields() != null) {
            for (Map.Entry<String, String> entry : errorReporter.getConstantFields().entrySet()) {
                if (shouldAddField(entry.getKey(), crashReportData, acraReportingConfig)) {
                    try {
                        ErrorReporter.put(entry.getKey(), entry.getValue(), crashReportData, writer);
                    } catch (Throwable th5) {
                        noteReportFieldFailure(crashReportData, entry.getKey(), th5);
                    }
                }
            }
        }
        populateCrashTimeData(fileBeingConsumed, errorReporter, acraReportingConfig, th, crashReportData, writer, z, z2);
        populateConstantDeviceData(errorReporter, acraReportingConfig, crashReportData, writer);
        populateCustomData(errorReporter, acraReportingConfig, th, crashReportData, writer);
        if (crashReportData.fieldFailures != null) {
            if (shouldAddField(ReportField.FIELD_FAILURES, crashReportData, acraReportingConfig)) {
                try {
                    ErrorReporter.put(ReportField.FIELD_FAILURES, TextUtils.join("\n", crashReportData.fieldFailures), crashReportData, writer);
                } catch (Throwable unused) {
                }
            }
            crashReportData.fieldFailures = null;
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(21)
    @DoNotOptimize
    public static class Api21Utils {
        private Api21Utils() {
        }

        static String[] getCpuAbis() {
            return Build.SUPPORTED_ABIS;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0163 A[SYNTHETIC, Splitter:B:101:0x0163] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0197 A[Catch:{ all -> 0x01af }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01a0 A[Catch:{ all -> 0x01af }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0244 A[SYNTHETIC, Splitter:B:158:0x0244] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034 A[SYNTHETIC, Splitter:B:16:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039 A[Catch:{ all -> 0x0053 }] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x02f3 A[Catch:{ all -> 0x02fd }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0309  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x030e  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008f A[SYNTHETIC, Splitter:B:43:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[SYNTHETIC, Splitter:B:49:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bb A[SYNTHETIC, Splitter:B:55:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cf A[SYNTHETIC, Splitter:B:61:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f8 A[SYNTHETIC, Splitter:B:70:0x00f8] */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void populateCrashTimeData(@javax.annotation.Nullable com.facebook.acra.Spool.FileBeingConsumed r16, com.facebook.acra.ErrorReporter r17, com.facebook.acra.config.AcraReportingConfig r18, java.lang.Throwable r19, com.facebook.acra.CrashReportData r20, java.io.Writer r21, boolean r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 860
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.populateCrashTimeData(com.facebook.acra.Spool$FileBeingConsumed, com.facebook.acra.ErrorReporter, com.facebook.acra.config.AcraReportingConfig, java.lang.Throwable, com.facebook.acra.CrashReportData, java.io.Writer, boolean, boolean):void");
    }

    private static void attachComponentStats(Context context, AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, Writer writer) {
        if (shouldAddField(ReportField.COMPONENTS_TOTAL, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_ENABLED, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_DISABLED, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_DEFAULT, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_DISABLED_NAMES, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_DEFAULT_NAMES, crashReportData, acraReportingConfig) || shouldAddField(ReportField.COMPONENTS_FLAG_STATE, crashReportData, acraReportingConfig)) {
            try {
                AppComponentStats.Stats stats = new AppComponentStats(context).getStats();
                if (shouldAddField(ReportField.COMPONENTS_TOTAL, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_TOTAL, Integer.toString(stats.totalCount), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_ENABLED, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_ENABLED, Integer.toString(stats.enabledCount), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_DISABLED, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_DISABLED, Integer.toString(stats.disabledCount), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_DEFAULT, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_DEFAULT, Integer.toString(stats.defaultCount), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_DEFAULT_NAMES, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_DEFAULT_NAMES, stats.defaultComponents.toString(), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_DISABLED_NAMES, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_DISABLED_NAMES, stats.disabledComponents.toString(), crashReportData, writer);
                }
                if (shouldAddField(ReportField.COMPONENTS_FLAG_STATE, crashReportData, acraReportingConfig)) {
                    ErrorReporter.put(ReportField.COMPONENTS_FLAG_STATE, Integer.toString(stats.flagState), crashReportData, writer);
                }
            } catch (Throwable th) {
                ErrorReporter.put(ReportField.COMPONENTS_TOTAL, th.toString(), crashReportData, writer);
                noteReportFieldFailure(crashReportData, ReportField.COMPONENTS_TOTAL, th);
            }
        }
    }

    private static String getProcessName(Context context) {
        String processNameFromAmsOrNull = getProcessNameFromAmsOrNull(context);
        if (processNameFromAmsOrNull == null && (processNameFromAmsOrNull = ProcReader.readProcFileEntirely("/proc/self/cmdline")) != null) {
            processNameFromAmsOrNull = processNameFromAmsOrNull.trim();
        }
        return processNameFromAmsOrNull == null ? "" : processNameFromAmsOrNull;
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01e8 A[SYNTHETIC] */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void populateConstantDeviceData(com.facebook.acra.ErrorReporter r22, com.facebook.acra.config.AcraReportingConfig r23, com.facebook.acra.CrashReportData r24, java.io.Writer r25) {
        /*
        // Method dump skipped, instructions count: 582
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.populateConstantDeviceData(com.facebook.acra.ErrorReporter, com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.CrashReportData, java.io.Writer):void");
    }

    private static void populateCustomData(ErrorReporter errorReporter, AcraReportingConfig acraReportingConfig, Throwable th, CrashReportData crashReportData, Writer writer) {
        for (Map.Entry<String, String> entry : errorReporter.getCustomFieldsSnapshot().entrySet()) {
            if (shouldAddField(entry.getKey(), crashReportData, acraReportingConfig)) {
                try {
                    ErrorReporter.put(entry.getKey(), entry.getValue(), crashReportData, writer);
                } catch (Throwable th2) {
                    noteReportFieldFailure(crashReportData, entry.getKey(), th2);
                }
            }
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : errorReporter.getLazyCustomFieldsSnapshot().entrySet()) {
            if (shouldAddLazyField(entry2.getKey(), crashReportData, acraReportingConfig)) {
                try {
                    ErrorReporter.put(entry2.getKey(), entry2.getValue().getCustomData(th), crashReportData, writer);
                } catch (Throwable th3) {
                    noteReportFieldFailure(crashReportData, entry2.getKey(), th3);
                }
            }
        }
    }

    static void noteReportFieldFailure(CrashReportData crashReportData, String str, Throwable th) {
        try {
            if (crashReportData.fieldFailures == null) {
                crashReportData.fieldFailures = new ArrayList<>();
            }
            crashReportData.fieldFailures.add(String.format("%s: [%s]", str, th));
        } catch (Throwable unused) {
        }
    }

    @Nullable
    private static String getProcessNameFromAmsOrNull(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str = processNameByAms;
        if (!PROCESS_NAME_UNSET.equals(str)) {
            return str;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str2 = null;
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return null;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                str2 = next.processName;
                break;
            }
        }
        processNameByAms = str2;
        return str2;
    }

    private static void resetProcessNameByAmsCache() {
        processNameByAms = PROCESS_NAME_UNSET;
    }

    static String getProcessNameFromAms(Context context) {
        String processNameFromAmsOrNull = getProcessNameFromAmsOrNull(context);
        return processNameFromAmsOrNull == null ? "n/a" : processNameFromAmsOrNull;
    }

    static String getPackageManagerVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "no package manager";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getApplicationInfo().packageName, 0);
            if (packageInfo == null) {
                return "no package info";
            }
            return Long.toString((long) packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return e.toString();
        }
    }

    static String getPublicSourceDir(Context context) {
        try {
            return context.createPackageContext(context.getPackageName(), 0).getApplicationInfo().publicSourceDir;
        } catch (PackageManager.NameNotFoundException unused) {
            return "package name not found";
        }
    }

    private static long getProcessUptime(ErrorReporter errorReporter) {
        return SystemClock.uptimeMillis() - errorReporter.getAppStartTickTimeMs();
    }

    private static long getDeviceUptime() {
        return SystemClock.elapsedRealtime();
    }

    private static String getAndroidRuntime() {
        if (Build.VERSION.SDK_INT < 19) {
            return ANDROID_RUNTIME_DALVIK;
        }
        String property = System.getProperty(JAVA_BOOT_CLASS_PATH);
        if (property == null) {
            return ANDROID_RUNTIME_UNKNOWN;
        }
        if (property.contains(KNOWN_ART_JAR)) {
            return ANDROID_RUNTIME_ART;
        }
        if (property.contains(KNOWN_DALVIK_JAR)) {
            return ANDROID_RUNTIME_DALVIK;
        }
        return ANDROID_RUNTIME_UNKNOWN;
    }

    private static String getCpuAbis() {
        String arrays = Arrays.toString(Build.VERSION.SDK_INT >= 21 ? Api21Utils.getCpuAbis() : new String[]{Build.CPU_ABI, Build.CPU_ABI2});
        int length = arrays.length();
        if (length < 2 || arrays.charAt(0) != '[') {
            return arrays;
        }
        int i = length - 1;
        return arrays.charAt(i) == ']' ? arrays.substring(1, i) : arrays;
    }

    private static String toString(Display display) {
        if (display == null) {
            return "";
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return "width=" + display.getWidth() + '\n' + "height=" + display.getHeight() + '\n' + "pixelFormat=" + display.getPixelFormat() + '\n' + "refreshRate=" + display.getRefreshRate() + "fps" + '\n' + "metrics.density=x" + displayMetrics.density + '\n' + "metrics.scaledDensity=x" + displayMetrics.scaledDensity + '\n' + "metrics.widthPixels=" + displayMetrics.widthPixels + '\n' + "metrics.heightPixels=" + displayMetrics.heightPixels + '\n' + "metrics.xdpi=" + displayMetrics.xdpi + '\n' + "metrics.ydpi=" + displayMetrics.ydpi;
    }

    private static PackageManagerWrapper getPackageManagerWrapper(Context context) {
        if (sPackageManagerWrapper == null) {
            sPackageManagerWrapper = new PackageManagerWrapper(context, ACRA.LOG_TAG);
        }
        return sPackageManagerWrapper;
    }

    private static void reportInternalStorageUsage(AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, @Nullable Writer writer) {
        if (shouldAddField(ReportField.DISK_SIZE_TOTAL, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_TOTAL, Long.toString(StatFsUtil.getTotalInternalStorageSpace(1024)), crashReportData, writer);
            } catch (Exception e) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_TOTAL, e);
            }
        }
        if (shouldAddField(ReportField.DISK_SIZE_AVAILABLE, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_AVAILABLE, Long.toString(StatFsUtil.getAvailableInternalStorageSpace(1024)), crashReportData, writer);
            } catch (Exception e2) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_AVAILABLE, e2);
            }
        }
        if (shouldAddField(ReportField.DISK_SIZE_USED, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_USED, Long.toString(StatFsUtil.getUsedInternalStorageSpace(1024)), crashReportData, writer);
            } catch (Exception e3) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_USED, e3);
            }
        }
    }

    @Nullable
    private static PackageInfo getWebViewPackageInfo(Context context) {
        String str;
        try {
            if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 23) {
                str = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            } else {
                str = (String) Class.forName("android.webkit.WebViewFactory").getMethod("getWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            }
            if (str == null) {
                return null;
            }
            return getPackageManagerWrapper(context).getPackageInfo(str, 0);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}
