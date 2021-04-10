package com.facebook.acra;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import com.facebook.acra.AppComponentStats;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.StatFsUtil;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class CrashTimeDataCollector {
    private static volatile String processNameByAms = "!";
    private static final Map<String, String> sDeviceSpecificFields = Collections.synchronizedMap(new TreeMap());
    private static volatile PackageManagerWrapper sPackageManagerWrapper;

    static boolean shouldAddField(String str, CrashReportData crashReportData, AcraReportingConfig acraReportingConfig) {
        return !crashReportData.containsKey(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x0171 A[SYNTHETIC, Splitter:B:102:0x0171] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x019e A[SYNTHETIC, Splitter:B:111:0x019e] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x041a A[Catch:{ all -> 0x0435 }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x042f A[Catch:{ all -> 0x0435 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0446 A[SYNTHETIC, Splitter:B:154:0x0446] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0475 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x04ca  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x04f6  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x0532 A[SYNTHETIC, Splitter:B:215:0x0532] */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x054c  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x059f  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x05ea A[Catch:{ all -> 0x061e }] */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x062a  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x062c  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x062f  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x067b  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0688  */
    /* JADX WARNING: Removed duplicated region for block: B:314:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bd A[SYNTHETIC, Splitter:B:50:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d4 A[Catch:{ all -> 0x00ee }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x012d A[SYNTHETIC, Splitter:B:83:0x012d] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0145 A[SYNTHETIC, Splitter:B:89:0x0145] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0159  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void gatherCrashData(com.facebook.acra.ErrorReporter r20, com.facebook.acra.config.AcraReportingConfig r21, java.lang.String r22, java.lang.Throwable r23, com.facebook.acra.CrashReportData r24, java.io.Writer r25, com.facebook.acra.Spool.FileBeingConsumed r26, boolean r27, boolean r28) throws java.lang.Exception {
        /*
        // Method dump skipped, instructions count: 1705
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.gatherCrashData(com.facebook.acra.ErrorReporter, com.facebook.acra.config.AcraReportingConfig, java.lang.String, java.lang.Throwable, com.facebook.acra.CrashReportData, java.io.Writer, com.facebook.acra.Spool$FileBeingConsumed, boolean, boolean):void");
    }

    private static void attachComponentStats(Context context, AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, Writer writer) {
        if (shouldAddField("COMPONENTS_TOTAL", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_ENABLED", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_DISABLED", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_DEFAULT", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_DISABLED_NAMES", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_DEFAULT_NAMES", crashReportData, acraReportingConfig) || shouldAddField("COMPONENTS_FLAG_STATE", crashReportData, acraReportingConfig)) {
            try {
                AppComponentStats.Stats stats = new AppComponentStats(context).getStats();
                if (shouldAddField("COMPONENTS_TOTAL", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_TOTAL", Integer.toString(stats.totalCount), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_ENABLED", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_ENABLED", Integer.toString(stats.enabledCount), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_DISABLED", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_DISABLED", Integer.toString(stats.disabledCount), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_DEFAULT", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_DEFAULT", Integer.toString(stats.defaultCount), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_DEFAULT_NAMES", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_DEFAULT_NAMES", stats.defaultComponents.toString(), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_DISABLED_NAMES", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_DISABLED_NAMES", stats.disabledComponents.toString(), crashReportData, writer);
                }
                if (shouldAddField("COMPONENTS_FLAG_STATE", crashReportData, acraReportingConfig)) {
                    ErrorReporter.put("COMPONENTS_FLAG_STATE", Integer.toString(stats.flagState), crashReportData, writer);
                }
            } catch (Throwable th) {
                ErrorReporter.put("COMPONENTS_TOTAL", th.toString(), crashReportData, writer);
                noteReportFieldFailure(crashReportData, "COMPONENTS_TOTAL", th);
            }
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02e3  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02e9 A[SYNTHETIC] */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void populateConstantDeviceData(com.facebook.acra.config.AcraReportingConfig r23, com.facebook.acra.CrashReportData r24, java.io.Writer r25) {
        /*
        // Method dump skipped, instructions count: 842
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashTimeDataCollector.populateConstantDeviceData(com.facebook.acra.config.AcraReportingConfig, com.facebook.acra.CrashReportData, java.io.Writer):void");
    }

    private static void populateCustomData(ErrorReporter errorReporter, AcraReportingConfig acraReportingConfig, Throwable th, CrashReportData crashReportData, Writer writer) {
        for (Map.Entry<String, String> entry : ErrorReporter.getCustomFieldsSnapshot().entrySet()) {
            if (shouldAddField(entry.getKey(), crashReportData, acraReportingConfig)) {
                try {
                    ErrorReporter.put(entry.getKey(), entry.getValue(), crashReportData, writer);
                } catch (Throwable th2) {
                    noteReportFieldFailure(crashReportData, entry.getKey(), th2);
                }
            }
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : errorReporter.getLazyCustomFieldsSnapshot().entrySet()) {
            if (shouldAddField(entry2.getKey(), crashReportData, acraReportingConfig)) {
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

    private static String getProcessNameFromAmsOrNull(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str = processNameByAms;
        if (!"!".equals(str)) {
            return str;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(FitnessTrackerMoveContract.Session.ACTIVITY);
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

    static String getProcessNameFromAms(Context context) {
        String processNameFromAmsOrNull = getProcessNameFromAmsOrNull(context);
        return processNameFromAmsOrNull == null ? "n/a" : processNameFromAmsOrNull;
    }

    private static String getPackageManagerVersionCode(Context context) {
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

    private static String getPublicSourceDir(Context context) {
        try {
            return context.createPackageContext(context.getPackageName(), 0).getApplicationInfo().publicSourceDir;
        } catch (PackageManager.NameNotFoundException unused) {
            return "package name not found";
        }
    }

    private static void reportInternalStorageUsage(AcraReportingConfig acraReportingConfig, CrashReportData crashReportData, Writer writer) {
        if (shouldAddField("DISK_SIZE_TOTAL", crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put("DISK_SIZE_TOTAL", Long.toString(StatFsUtil.getTotalInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReportData, writer);
            } catch (Exception e) {
                noteReportFieldFailure(crashReportData, "DISK_SIZE_TOTAL", e);
            }
        }
        if (shouldAddField("DISK_SIZE_AVAILABLE", crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put("DISK_SIZE_AVAILABLE", Long.toString(StatFsUtil.getAvailableInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReportData, writer);
            } catch (Exception e2) {
                noteReportFieldFailure(crashReportData, "DISK_SIZE_AVAILABLE", e2);
            }
        }
        if (shouldAddField("DISK_SIZE_USED", crashReportData, acraReportingConfig)) {
            try {
                ErrorReporter.put("DISK_SIZE_USED", Long.toString(StatFsUtil.getUsedInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReportData, writer);
            } catch (Exception e3) {
                noteReportFieldFailure(crashReportData, "DISK_SIZE_USED", e3);
            }
        }
    }

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
            if (sPackageManagerWrapper == null) {
                sPackageManagerWrapper = new PackageManagerWrapper(context, ACRA.LOG_TAG);
            }
            return sPackageManagerWrapper.getPackageInfo(str, 0);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}
