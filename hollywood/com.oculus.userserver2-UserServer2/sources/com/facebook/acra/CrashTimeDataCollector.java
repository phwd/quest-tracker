package com.facebook.acra;

import X.C0070Kj;
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
    public static final String ANDROID_RUNTIME_ART = "ART";
    public static final String ANDROID_RUNTIME_DALVIK = "DALVIK";
    public static final String ANDROID_RUNTIME_UNKNOWN = "UNKNOWN";
    public static final int DEFAULT_TRACE_COUNT_LIMIT = 5;
    public static final String JAVA_BOOT_CLASS_PATH = "java.boot.class.path";
    public static final String KNOWN_ART_JAR = "/system/framework/core-libart.jar";
    public static final String KNOWN_DALVIK_JAR = "/system/framework/core.jar";
    public static final String PROCESS_NAME_UNSET = "!";
    public static volatile String processNameByAms = "!";
    public static final Map<String, String> sDeviceSpecificFields = Collections.synchronizedMap(new TreeMap());
    public static volatile PackageManagerWrapper sPackageManagerWrapper;

    @TargetApi(19)
    @DoNotOptimize
    public static class Api19Utils {
        public static boolean isLowRamDevice(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY);
            if (activityManager == null || !activityManager.isLowRamDevice()) {
                return false;
            }
            return true;
        }
    }

    public static String getCpuAbis() {
        String arrays = Arrays.toString(Api21Utils.getCpuAbis());
        int length = arrays.length();
        if (length < 2 || arrays.charAt(0) != '[') {
            return arrays;
        }
        int i = length - 1;
        if (arrays.charAt(i) == ']') {
            return arrays.substring(1, i);
        }
        return arrays;
    }

    @Nullable
    public static PackageInfo getWebViewPackageInfo(Context context) {
        try {
            String str = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            if (str == null) {
                return null;
            }
            return getPackageManagerWrapper(context).getPackageInfo(str, 0);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    @TargetApi(21)
    @DoNotOptimize
    public static class Api21Utils {
        public static String[] getCpuAbis() {
            return Build.SUPPORTED_ABIS;
        }
    }

    public static void attachComponentStats(Context context, AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, Writer writer) {
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

    public static String getAndroidRuntime() {
        String property = System.getProperty(JAVA_BOOT_CLASS_PATH);
        if (property == null) {
            return ANDROID_RUNTIME_UNKNOWN;
        }
        if (property.contains(KNOWN_ART_JAR)) {
            return ANDROID_RUNTIME_ART;
        }
        return property.contains(KNOWN_DALVIK_JAR) ? ANDROID_RUNTIME_DALVIK : ANDROID_RUNTIME_UNKNOWN;
    }

    public static PackageManagerWrapper getPackageManagerWrapper(Context context) {
        if (sPackageManagerWrapper == null) {
            sPackageManagerWrapper = new PackageManagerWrapper(context, "ACRA");
        }
        return sPackageManagerWrapper;
    }

    @Nullable
    public static String getProcessNameFromAmsOrNull(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str = processNameByAms;
        if (!PROCESS_NAME_UNSET.equals(str)) {
            return str;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY);
        String str2 = null;
        if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null)) {
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
        }
        return str2;
    }

    public static void noteReportFieldFailure(CrashReportData crashReportData, String str, Throwable th) {
        try {
            ArrayList<String> arrayList = crashReportData.fieldFailures;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                crashReportData.fieldFailures = arrayList;
            }
            arrayList.add(String.format("%s: [%s]", str, th));
        } catch (Throwable unused) {
        }
    }

    public static void reportInternalStorageUsage(AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, @Nullable Writer writer) {
        if (shouldAddField(ReportField.DISK_SIZE_TOTAL, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_TOTAL, Long.toString(StatFsUtil.getTotalInternalStorageSpace(StatFsUtil.IN_KILO_BYTE)), crashReportData, writer);
            } catch (Exception e) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_TOTAL, e);
            }
        }
        if (shouldAddField(ReportField.DISK_SIZE_AVAILABLE, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_AVAILABLE, Long.toString(StatFsUtil.getAvailableInternalStorageSpace(StatFsUtil.IN_KILO_BYTE)), crashReportData, writer);
            } catch (Exception e2) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_AVAILABLE, e2);
            }
        }
        if (shouldAddField(ReportField.DISK_SIZE_USED, crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put(ReportField.DISK_SIZE_USED, Long.toString(StatFsUtil.getUsedInternalStorageSpace(StatFsUtil.IN_KILO_BYTE)), crashReportData, writer);
            } catch (Exception e3) {
                noteReportFieldFailure(crashReportData, ReportField.DISK_SIZE_USED, e3);
            }
        }
    }

    public static void resetProcessNameByAmsCache() {
        processNameByAms = PROCESS_NAME_UNSET;
    }

    public static String toString(Display display) {
        if (display == null) {
            return "";
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        StringBuilder sb = new StringBuilder("width=");
        sb.append(display.getWidth());
        sb.append('\n');
        sb.append("height=");
        sb.append(display.getHeight());
        sb.append('\n');
        sb.append("pixelFormat=");
        sb.append(display.getPixelFormat());
        sb.append('\n');
        sb.append("refreshRate=");
        sb.append(display.getRefreshRate());
        sb.append("fps");
        sb.append('\n');
        sb.append("metrics.density=x");
        sb.append(displayMetrics.density);
        sb.append('\n');
        sb.append("metrics.scaledDensity=x");
        sb.append(displayMetrics.scaledDensity);
        sb.append('\n');
        sb.append("metrics.widthPixels=");
        sb.append(displayMetrics.widthPixels);
        sb.append('\n');
        sb.append("metrics.heightPixels=");
        sb.append(displayMetrics.heightPixels);
        sb.append('\n');
        sb.append("metrics.xdpi=");
        sb.append(displayMetrics.xdpi);
        sb.append('\n');
        sb.append("metrics.ydpi=");
        sb.append(displayMetrics.ydpi);
        return sb.toString();
    }

    public static long getDeviceUptime() {
        return SystemClock.elapsedRealtime();
    }

    public static String getPackageManagerVersionCode(Context context) {
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

    public static String getProcessName(Context context) {
        String trim;
        String processNameFromAmsOrNull = getProcessNameFromAmsOrNull(context);
        if (processNameFromAmsOrNull != null) {
            return processNameFromAmsOrNull;
        }
        String[] strArr = {null};
        C0070Kj.A00.A37("/proc/self/cmdline", C0070Kj.A01, strArr, null, null);
        String str = strArr[0];
        if (str == null || (trim = str.trim()) == null) {
            return "";
        }
        return trim;
    }

    public static String getProcessNameFromAms(Context context) {
        String processNameFromAmsOrNull = getProcessNameFromAmsOrNull(context);
        if (processNameFromAmsOrNull == null) {
            return "n/a";
        }
        return processNameFromAmsOrNull;
    }

    public static long getProcessUptime(ErrorReporter errorReporter) {
        return SystemClock.uptimeMillis() - errorReporter.getAppStartTickTimeMs();
    }

    public static String getPublicSourceDir(Context context) {
        try {
            return context.createPackageContext(context.getPackageName(), 0).getApplicationInfo().publicSourceDir;
        } catch (PackageManager.NameNotFoundException unused) {
            return "package name not found";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0161, code lost:
        if (r16 == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x016c, code lost:
        if (r17 != false) goto L_0x0171;
     */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void populateConstantDeviceData(com.facebook.acra.ErrorReporter r35, com.facebook.acra.config.AcraReportingConfig r36, com.facebook.acra.CrashReportData r37, java.io.Writer r38) {
        /*
        // Method dump skipped, instructions count: 430
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.populateConstantDeviceData(com.facebook.acra.ErrorReporter, com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.CrashReportData, java.io.Writer):void");
    }

    public static void populateCustomData(ErrorReporter errorReporter, AcraReportingConfig acraReportingConfig, Throwable th, CrashReportData crashReportData, Writer writer) {
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

    public static boolean shouldAddField(String str, CrashReportData crashReportData, AcraReportingConfig acraReportingConfig) {
        if (crashReportData.containsKey(str) || !acraReportingConfig.shouldReportField(str)) {
            return false;
        }
        return true;
    }

    public static boolean shouldAddLazyField(String str, CrashReportData crashReportData, AcraReportingConfig acraReportingConfig) {
        if (acraReportingConfig.shouldLazyFieldsOverwriteExistingValues()) {
            return acraReportingConfig.shouldReportField(str);
        }
        return shouldAddField(str, crashReportData, acraReportingConfig);
    }

    public static void gatherCrashData(ErrorReporter errorReporter, AcraReportingConfig acraReportingConfig, String str, Throwable th, CrashReportData crashReportData, @Nullable Writer writer, @Nullable Spool.FileBeingConsumed fileBeingConsumed, boolean z, boolean z2) throws Exception {
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

    /* JADX WARNING: Removed duplicated region for block: B:101:0x015f A[SYNTHETIC, Splitter:B:101:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0190 A[Catch:{ all -> 0x01a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x019c A[Catch:{ all -> 0x01a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01cb A[SYNTHETIC, Splitter:B:131:0x01cb] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0226 A[SYNTHETIC, Splitter:B:154:0x0226] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0244  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0290 A[SYNTHETIC, Splitter:B:180:0x0290] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:194:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047 A[Catch:{ all -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008b A[SYNTHETIC, Splitter:B:43:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a3 A[SYNTHETIC, Splitter:B:49:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b7 A[SYNTHETIC, Splitter:B:55:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cb A[SYNTHETIC, Splitter:B:61:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f4 A[SYNTHETIC, Splitter:B:70:0x00f4] */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void populateCrashTimeData(@javax.annotation.Nullable com.facebook.acra.Spool.FileBeingConsumed r14, com.facebook.acra.ErrorReporter r15, com.facebook.acra.config.AcraReportingConfig r16, java.lang.Throwable r17, com.facebook.acra.CrashReportData r18, java.io.Writer r19, boolean r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 712
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.populateCrashTimeData(com.facebook.acra.Spool$FileBeingConsumed, com.facebook.acra.ErrorReporter, com.facebook.acra.config.AcraReportingConfig, java.lang.Throwable, com.facebook.acra.CrashReportData, java.io.Writer, boolean, boolean):void");
    }
}
