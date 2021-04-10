package com.facebook.acra;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.acra.AppComponentStats;
import com.facebook.acra.Spool;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.util.CommandOutputCollector;
import com.facebook.acra.util.CrashTimeDataCollectorHelper;
import com.facebook.acra.util.Installation;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.ProcFileReader;
import com.facebook.acra.util.StatFsUtil;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.procread.ProcReader;
import com.facebook.debug.log.BLog;
import com.oculus.os.Version;
import java.io.File;
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
    private static volatile String processNameByAms = "!";
    private static final Map<String, String> sDeviceSpecificFields = Collections.synchronizedMap(new TreeMap());
    private static volatile PackageManagerWrapper sPackageManagerWrapper;

    /* access modifiers changed from: private */
    @TargetApi(Version.VERSION_19)
    public static class Api19Utils {
        static boolean isLowRamDevice(Context context) {
            ActivityManager am = (ActivityManager) context.getSystemService("activity");
            return am != null && am.isLowRamDevice();
        }
    }

    static boolean shouldAddField(String fieldName, CrashReportData crashReport, AcraReportingConfig config) {
        return !crashReport.containsKey(fieldName) && config.shouldReportField(fieldName);
    }

    static boolean shouldAddLazyField(String fieldName, CrashReportData crashReport, AcraReportingConfig config) {
        if (config.shouldLazyFieldsOverwriteExistingValues()) {
            return config.shouldReportField(fieldName);
        }
        return shouldAddField(fieldName, crashReport, config);
    }

    static void gatherCrashData(ErrorReporter errorReporter, AcraReportingConfig config, String stackTrace, Throwable e, CrashReportData crashReport, @Nullable Writer w, @Nullable Spool.FileBeingConsumed minidump, boolean captureProcessName, boolean isNonCrash) throws Exception {
        if (shouldAddField("UID", crashReport, config)) {
            try {
                ErrorReporter.put("UID", errorReporter.getUserId(), crashReport, w);
            } catch (Throwable ex) {
                noteReportFieldFailure(crashReport, "UID", ex);
            }
        }
        if (shouldAddField("CLIENT_UID", crashReport, config) && errorReporter.getClientUserId() != null && errorReporter.getClientUserId().length() > 0) {
            try {
                ErrorReporter.put("CLIENT_UID", errorReporter.getClientUserId(), crashReport, w);
            } catch (Throwable ex2) {
                noteReportFieldFailure(crashReport, "CLIENT_UID", ex2);
            }
        }
        if (shouldAddField("STACK_TRACE", crashReport, config)) {
            try {
                ErrorReporter.put("STACK_TRACE", stackTrace, crashReport, w);
            } catch (Throwable ex3) {
                noteReportFieldFailure(crashReport, "STACK_TRACE", ex3);
            }
        }
        if (errorReporter.getConstantFields() != null) {
            for (Map.Entry<String, String> entry : errorReporter.getConstantFields().entrySet()) {
                if (shouldAddField(entry.getKey(), crashReport, config)) {
                    try {
                        ErrorReporter.put(entry.getKey(), entry.getValue(), crashReport, w);
                    } catch (Throwable ex4) {
                        noteReportFieldFailure(crashReport, entry.getKey(), ex4);
                    }
                }
            }
        }
        populateCrashTimeData(minidump, errorReporter, config, e, crashReport, w, captureProcessName, isNonCrash);
        populateConstantDeviceData(errorReporter, config, crashReport, w);
        populateCustomData(errorReporter, config, e, crashReport, w);
        if (crashReport.fieldFailures != null) {
            if (shouldAddField("FIELD_FAILURES", crashReport, config)) {
                try {
                    ErrorReporter.put("FIELD_FAILURES", TextUtils.join("\n", crashReport.fieldFailures), crashReport, w);
                } catch (Throwable th) {
                }
            }
            crashReport.fieldFailures = null;
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(Version.VERSION_21)
    public static class Api21Utils {
        static String[] getCpuAbis() {
            return Build.SUPPORTED_ABIS;
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private static void populateCrashTimeData(@Nullable Spool.FileBeingConsumed minidump, ErrorReporter reporter, AcraReportingConfig config, Throwable e, CrashReportData crashReport, Writer writer, boolean captureProcessName, boolean isNonCrash) {
        String activityLogDump;
        String processName;
        Context context = reporter.getContext();
        boolean nativeCrash = minidump != null;
        MinidumpReader minidumpReader = null;
        if (nativeCrash) {
            try {
                minidumpReader = new MinidumpReader(minidump.file);
            } catch (Exception ex) {
                BLog.e(ACRA.LOG_TAG, ex, "There was a problem reading the minidump");
            }
        }
        if (captureProcessName && shouldAddField("PROCESS_NAME", crashReport, config)) {
            if (!nativeCrash) {
                try {
                    processName = getProcessName(context);
                } catch (Throwable ex2) {
                    noteReportFieldFailure(crashReport, "PROCESS_NAME", ex2);
                }
            } else {
                processName = minidumpReader.getString(1197932550);
                if (processName == null) {
                    processName = "";
                }
                int nullPos = processName.indexOf(0);
                if (nullPos != -1) {
                    processName = processName.substring(0, nullPos);
                }
            }
            ErrorReporter.put("PROCESS_NAME", processName, crashReport, writer);
        }
        if (shouldAddField("USER_APP_START_DATE", crashReport, config) && !nativeCrash) {
            try {
                ErrorReporter.put("USER_APP_START_DATE", reporter.getAppStartDateFormat3339(), crashReport, writer);
            } catch (Throwable ex3) {
                noteReportFieldFailure(crashReport, "USER_APP_START_DATE", ex3);
            }
        }
        if (shouldAddField("PROCESS_UPTIME", crashReport, config) && !nativeCrash) {
            try {
                ErrorReporter.put("PROCESS_UPTIME", Long.toString(getProcessUptime(reporter)), crashReport, writer);
            } catch (Throwable ex4) {
                noteReportFieldFailure(crashReport, "PROCESS_UPTIME", ex4);
            }
        }
        if (shouldAddField("DEVICE_UPTIME", crashReport, config)) {
            try {
                ErrorReporter.put("DEVICE_UPTIME", Long.toString(getDeviceUptime()), crashReport, writer);
            } catch (Throwable ex5) {
                noteReportFieldFailure(crashReport, "DEVICE_UPTIME", ex5);
            }
        }
        if (shouldAddField("APP_VERSION_CODE_IN_PACKAGE_MANAGER", crashReport, config)) {
            try {
                ErrorReporter.put("APP_VERSION_CODE_IN_PACKAGE_MANAGER", getPackageManagerVersionCode(context), crashReport, writer);
            } catch (Throwable ex6) {
                noteReportFieldFailure(crashReport, "APP_VERSION_CODE_IN_PACKAGE_MANAGER", ex6);
            }
        }
        if (shouldAddField("PUBLIC_SOURCE_DIR_IN_PACKAGE_MANAGER", crashReport, config)) {
            try {
                ErrorReporter.put("PUBLIC_SOURCE_DIR_IN_PACKAGE_MANAGER", getPublicSourceDir(context), crashReport, writer);
            } catch (Throwable ex7) {
                noteReportFieldFailure(crashReport, "PUBLIC_SOURCE_DIR_IN_PACKAGE_MANAGER", ex7);
            }
        }
        if (shouldAddField("PUBLIC_SOURCE_DIR_LAST_MODIFIED", crashReport, config)) {
            try {
                File f = new File(getPublicSourceDir(context));
                String epochTime = "File doesn't exist";
                if (f.exists()) {
                    epochTime = Long.toString(f.lastModified());
                }
                ErrorReporter.put("PUBLIC_SOURCE_DIR_LAST_MODIFIED", epochTime, crashReport, writer);
            } catch (Throwable ex8) {
                noteReportFieldFailure(crashReport, "PUBLIC_SOURCE_DIR_LAST_MODIFIED", ex8);
            }
        }
        if (shouldAddField("CRASH_CONFIGURATION", crashReport, config)) {
            try {
                Resources resources = context.getResources();
                if (resources != null) {
                    ErrorReporter.put("CRASH_CONFIGURATION", ConfigurationInspector.toString(resources.getConfiguration()), crashReport, writer);
                }
            } catch (Throwable ex9) {
                noteReportFieldFailure(crashReport, "CRASH_CONFIGURATION", ex9);
            }
        }
        if (shouldAddField("AVAILABLE_MEM_SIZE", crashReport, config) && !nativeCrash) {
            try {
                ErrorReporter.put("AVAILABLE_MEM_SIZE", Long.toString(StatFsUtil.getAvailableInternalStorageSpace(1)), crashReport, writer);
            } catch (Throwable ex10) {
                noteReportFieldFailure(crashReport, "AVAILABLE_MEM_SIZE", ex10);
            }
        }
        if (shouldAddField("DUMPSYS_MEMINFO", crashReport, config) && !nativeCrash && !isNonCrash) {
            try {
                ErrorReporter.put("DUMPSYS_MEMINFO", DumpSysCollector.collectMemInfo(context), crashReport, writer);
            } catch (Throwable ex11) {
                noteReportFieldFailure(crashReport, "DUMPSYS_MEMINFO", ex11);
            }
        }
        if (shouldAddField("DUMPSYS_USERINFO", crashReport, config) && !isNonCrash) {
            try {
                ErrorReporter.put("DUMPSYS_USERINFO", DumpSysCollector.collectUserInfo(context), crashReport, writer);
            } catch (Throwable ex12) {
                noteReportFieldFailure(crashReport, "DUMPSYS_USERINFO", ex12);
            }
        }
        reportInternalStorageUsage(config, crashReport, writer);
        if (shouldAddField("USER_CRASH_DATE", crashReport, config)) {
            try {
                Time curDate = new Time();
                if (!nativeCrash) {
                    curDate.setToNow();
                } else {
                    curDate.set(minidump.fileName.lastModified());
                }
                ErrorReporter.put("USER_CRASH_DATE", curDate.format3339(false), crashReport, writer);
            } catch (Throwable ex13) {
                noteReportFieldFailure(crashReport, "USER_CRASH_DATE", ex13);
            }
        }
        if (shouldAddField("ACTIVITY_LOG", crashReport, config) && !nativeCrash) {
            try {
                if (e instanceof OutOfMemoryError) {
                    activityLogDump = reporter.getActivityLogger().toString();
                } else {
                    activityLogDump = reporter.getActivityLogger().toString(5);
                }
                ErrorReporter.put("ACTIVITY_LOG", activityLogDump, crashReport, writer);
            } catch (Throwable ex14) {
                noteReportFieldFailure(crashReport, "ACTIVITY_LOG", ex14);
            }
        }
        if (shouldAddField("PROCESS_NAME_BY_AMS", crashReport, config) && !nativeCrash) {
            try {
                ErrorReporter.put("PROCESS_NAME_BY_AMS", getProcessNameFromAms(context), crashReport, writer);
                resetProcessNameByAmsCache();
            } catch (Throwable ex15) {
                noteReportFieldFailure(crashReport, "PROCESS_NAME_BY_AMS", ex15);
            }
        }
        if (shouldAddField("OPEN_FD_COUNT", crashReport, config)) {
            try {
                ErrorReporter.put("OPEN_FD_COUNT", String.valueOf(ProcFileReader.getProcFileReader().getOpenFDCount()), crashReport, writer);
            } catch (Throwable ex16) {
                noteReportFieldFailure(crashReport, "OPEN_FD_COUNT", ex16);
            }
        }
        ProcFileReader.OpenFDLimits limits = null;
        try {
            limits = ProcFileReader.getProcFileReader().getOpenFDLimits();
        } catch (Throwable ex17) {
            BLog.w(ACRA.LOG_TAG, ex17, "unable to retrieve open FD info: not logging FD fields");
        }
        if (limits != null) {
            if (shouldAddField("OPEN_FD_SOFT_LIMIT", crashReport, config)) {
                try {
                    ErrorReporter.put("OPEN_FD_SOFT_LIMIT", Integer.toString(limits.softLimit), crashReport, writer);
                } catch (Throwable ex18) {
                    noteReportFieldFailure(crashReport, "OPEN_FD_SOFT_LIMIT", ex18);
                }
            }
            if (shouldAddField("OPEN_FD_HARD_LIMIT", crashReport, config)) {
                try {
                    ErrorReporter.put("OPEN_FD_HARD_LIMIT", Integer.toString(limits.hardLimit), crashReport, writer);
                } catch (Throwable ex19) {
                    noteReportFieldFailure(crashReport, "OPEN_FD_HARD_LIMIT", ex19);
                }
            }
        }
        if (shouldAddField("RUNTIME_PERMISSIONS", crashReport, config)) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    ErrorReporter.put("RUNTIME_PERMISSIONS", PermissionsReporter.getAppGrantedPermissions(context), crashReport, writer);
                }
            } catch (Throwable ex20) {
                noteReportFieldFailure(crashReport, "RUNTIME_PERMISSIONS", ex20);
            }
        }
        if (!nativeCrash) {
            if (shouldAddField("LOGCAT", crashReport, config)) {
                try {
                    ErrorReporter.put("LOGCAT", LogCatCollector.collectLogCat(context, config, null, false), crashReport, writer);
                } catch (Throwable ex21) {
                    noteReportFieldFailure(crashReport, "LOGCAT", ex21);
                }
            }
            if (Build.VERSION.SDK_INT < 19) {
                if (shouldAddField("EVENTSLOG", crashReport, config)) {
                    try {
                        ErrorReporter.put("EVENTSLOG", LogCatCollector.collectLogCat(context, config, "events", false), crashReport, writer);
                    } catch (Throwable ex22) {
                        noteReportFieldFailure(crashReport, "EVENTSLOG", ex22);
                    }
                }
                if (shouldAddField("RADIOLOG", crashReport, config)) {
                    try {
                        ErrorReporter.put("RADIOLOG", LogCatCollector.collectLogCat(context, config, "radio", false), crashReport, writer);
                    } catch (Throwable ex23) {
                        noteReportFieldFailure(crashReport, "RADIOLOG", ex23);
                    }
                }
            }
        } else if (config.shouldReportField("LOGCAT_NATIVE") && !crashReport.containsKey("LOGCAT")) {
            String logcatFileName = null;
            if (nativeCrash) {
                try {
                    logcatFileName = minidumpReader.getCustomData("logcatFileName");
                    BLog.w(ACRA.LOG_TAG, "logcat Logcat file name from minidump : %s", logcatFileName);
                } catch (Throwable ex24) {
                    noteReportFieldFailure(crashReport, "LOGCAT", ex24);
                }
            }
            ErrorReporter.put("LOGCAT", LogCatCollector.collectLogCat(context, config, null, logcatFileName, false, false, false), crashReport, writer);
        }
        if (shouldAddField("LARGE_MEM_HEAP", crashReport, config) && !isNonCrash) {
            try {
                if (Build.VERSION.SDK_INT >= 11) {
                    ErrorReporter.put("LARGE_MEM_HEAP", DumpSysCollector.collectLargerMemoryInfo(context), crashReport, writer);
                }
            } catch (Throwable ex25) {
                noteReportFieldFailure(crashReport, "LARGE_MEM_HEAP", ex25);
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            if (shouldAddField("OPEN_FILE_DESCRIPTORS", crashReport, config)) {
                try {
                    ErrorReporter.put("OPEN_FILE_DESCRIPTORS", ProcFileReader.getProcFileReader().getOpenFileDescriptors(), crashReport, writer);
                } catch (Throwable ex26) {
                    noteReportFieldFailure(crashReport, "OPEN_FILE_DESCRIPTORS", ex26);
                }
            }
            if (shouldAddField("DATA_FILE_LS_LR", crashReport, config)) {
                try {
                    ErrorReporter.put("DATA_FILE_LS_LR", CommandOutputCollector.collect("/system/bin/ls", "-lLR", context.getApplicationInfo().dataDir), crashReport, writer);
                } catch (Throwable ex27) {
                    noteReportFieldFailure(crashReport, "DATA_FILE_LS_LR", ex27);
                }
            }
        }
        if (!nativeCrash) {
            attachComponentStats(context, config, crashReport, writer);
        }
    }

    private static void attachComponentStats(Context context, AcraReportingConfig config, CrashReportData crashReport, Writer writer) {
        if (shouldAddField("COMPONENTS_TOTAL", crashReport, config) || shouldAddField("COMPONENTS_ENABLED", crashReport, config) || shouldAddField("COMPONENTS_DISABLED", crashReport, config) || shouldAddField("COMPONENTS_DEFAULT", crashReport, config) || shouldAddField("COMPONENTS_DISABLED_NAMES", crashReport, config) || shouldAddField("COMPONENTS_DEFAULT_NAMES", crashReport, config) || shouldAddField("COMPONENTS_FLAG_STATE", crashReport, config)) {
            try {
                AppComponentStats.Stats stats = new AppComponentStats(context).getStats();
                if (shouldAddField("COMPONENTS_TOTAL", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_TOTAL", Integer.toString(stats.totalCount), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_ENABLED", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_ENABLED", Integer.toString(stats.enabledCount), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_DISABLED", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_DISABLED", Integer.toString(stats.disabledCount), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_DEFAULT", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_DEFAULT", Integer.toString(stats.defaultCount), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_DEFAULT_NAMES", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_DEFAULT_NAMES", stats.defaultComponents.toString(), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_DISABLED_NAMES", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_DISABLED_NAMES", stats.disabledComponents.toString(), crashReport, writer);
                }
                if (shouldAddField("COMPONENTS_FLAG_STATE", crashReport, config)) {
                    ErrorReporter.put("COMPONENTS_FLAG_STATE", Integer.toString(stats.flagState), crashReport, writer);
                }
            } catch (Throwable ex) {
                ErrorReporter.put("COMPONENTS_TOTAL", ex.toString(), crashReport, writer);
                noteReportFieldFailure(crashReport, "COMPONENTS_TOTAL", ex);
            }
        }
    }

    private static String getProcessName(Context context) {
        String processName = getProcessNameFromAmsOrNull(context);
        if (processName == null && (processName = ProcReader.readProcFileEntirely("/proc/self/cmdline")) != null) {
            processName = processName.trim();
        }
        if (processName == null) {
            return "";
        }
        return processName;
    }

    @SuppressLint({"CatchGeneralException"})
    private static void populateConstantDeviceData(ErrorReporter reporter, AcraReportingConfig config, CrashReportData crashReport, Writer writer) {
        String val;
        Context context = config.getApplicationContext();
        String[] fields = {"BUILD", "JAIL_BROKEN", "INSTALLATION_ID", "TOTAL_MEM_SIZE", "ENVIRONMENT", "ANDROID_RUNTIME", "device_cpu_abis", "is_64_bit_build", "DISPLAY", "DEVICE_FEATURES", "IS_LOW_RAM_DEVICE", "WEBVIEW_VERSION", "PLAY_SERVICES_VERSION"};
        for (String field : fields) {
            if (shouldAddField(field, crashReport, config)) {
                boolean reportField = true;
                try {
                    if (sDeviceSpecificFields.containsKey(field)) {
                        val = sDeviceSpecificFields.get(field);
                    } else {
                        char c = 65535;
                        switch (field.hashCode()) {
                            case -2055404088:
                                if (field.equals("ANDROID_RUNTIME")) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case -1905220446:
                                if (field.equals("DISPLAY")) {
                                    c = '\b';
                                    break;
                                }
                                break;
                            case -1605187834:
                                if (field.equals("TOTAL_MEM_SIZE")) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case -522074816:
                                if (field.equals("INSTALLATION_ID")) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -450347134:
                                if (field.equals("PLAY_SERVICES_VERSION")) {
                                    c = '\f';
                                    break;
                                }
                                break;
                            case -94228910:
                                if (field.equals("WEBVIEW_VERSION")) {
                                    c = 11;
                                    break;
                                }
                                break;
                            case 52048902:
                                if (field.equals("DEVICE_FEATURES")) {
                                    c = '\t';
                                    break;
                                }
                                break;
                            case 63557198:
                                if (field.equals("BUILD")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 1052445527:
                                if (field.equals("IS_LOW_RAM_DEVICE")) {
                                    c = '\n';
                                    break;
                                }
                                break;
                            case 1171703578:
                                if (field.equals("JAIL_BROKEN")) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case 1460053360:
                                if (field.equals("is_64_bit_build")) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case 1730504971:
                                if (field.equals("device_cpu_abis")) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case 2067056115:
                                if (field.equals("ENVIRONMENT")) {
                                    c = 4;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                val = ReflectionCollector.collectConstants(Build.class);
                                break;
                            case 1:
                                val = CrashTimeDataCollectorHelper.getJailStatus();
                                break;
                            case 2:
                                val = Installation.id(context);
                                break;
                            case 3:
                                val = Long.toString(StatFsUtil.getTotalInternalStorageSpace(1));
                                break;
                            case 4:
                                val = ReflectionCollector.collectStaticGettersResults(Environment.class);
                                break;
                            case 5:
                                val = getAndroidRuntime();
                                break;
                            case 6:
                                val = getCpuAbis();
                                break;
                            case 7:
                                val = Boolean.toString(BuildConstants.is64BitBuild());
                                break;
                            case '\b':
                                val = toString(((WindowManager) context.getSystemService("window")).getDefaultDisplay());
                                break;
                            case '\t':
                                val = DeviceFeaturesCollector.getFeatures(context);
                                break;
                            case '\n':
                                if (Build.VERSION.SDK_INT >= 19) {
                                    val = Boolean.toString(Api19Utils.isLowRamDevice(context));
                                    break;
                                } else {
                                    val = null;
                                    reportField = false;
                                    break;
                                }
                            case 11:
                                if (Build.VERSION.SDK_INT >= 21) {
                                    PackageInfo wpi = getWebViewPackageInfo(context);
                                    if (wpi != null) {
                                        val = wpi.versionName;
                                        break;
                                    } else {
                                        val = null;
                                        reportField = false;
                                        break;
                                    }
                                } else {
                                    val = null;
                                    reportField = false;
                                    break;
                                }
                            case '\f':
                                val = ReflectionCollector.getPlayServicesVersion(context);
                                if (val != null) {
                                    reportField = true;
                                    break;
                                } else {
                                    reportField = false;
                                    break;
                                }
                            default:
                                throw new RuntimeException("Missing case for " + field);
                        }
                        sDeviceSpecificFields.put(field, val);
                    }
                    if (reportField) {
                        ErrorReporter.put(field, val, crashReport, writer);
                    }
                } catch (Throwable ex) {
                    if (0 == 0) {
                        noteReportFieldFailure(crashReport, field, ex);
                    }
                }
            }
        }
    }

    private static void populateCustomData(ErrorReporter reporter, AcraReportingConfig config, Throwable e, CrashReportData crashReport, Writer writer) {
        for (Map.Entry<String, String> entry : reporter.getCustomFieldsSnapshot().entrySet()) {
            if (shouldAddField(entry.getKey(), crashReport, config)) {
                try {
                    ErrorReporter.put(entry.getKey(), entry.getValue(), crashReport, writer);
                } catch (Throwable ex) {
                    noteReportFieldFailure(crashReport, entry.getKey(), ex);
                }
            }
        }
        for (Map.Entry<String, CustomReportDataSupplier> entry2 : reporter.getLazyCustomFieldsSnapshot().entrySet()) {
            if (shouldAddLazyField(entry2.getKey(), crashReport, config)) {
                try {
                    ErrorReporter.put(entry2.getKey(), entry2.getValue().getCustomData(e), crashReport, writer);
                } catch (Throwable ex2) {
                    noteReportFieldFailure(crashReport, entry2.getKey(), ex2);
                }
            }
        }
    }

    static void noteReportFieldFailure(CrashReportData crashReport, String fieldName, Throwable ex) {
        try {
            if (crashReport.fieldFailures == null) {
                crashReport.fieldFailures = new ArrayList<>();
            }
            crashReport.fieldFailures.add(String.format("%s: [%s]", fieldName, ex));
        } catch (Throwable th) {
        }
    }

    @Nullable
    private static String getProcessNameFromAmsOrNull(Context context) {
        List<ActivityManager.RunningAppProcessInfo> processes;
        String processName = processNameByAms;
        if (!"!".equals(processName)) {
            return processName;
        }
        String processName2 = null;
        int pid = Process.myPid();
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        if (am == null || (processes = am.getRunningAppProcesses()) == null) {
            return null;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = processes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo rai = it.next();
            if (rai.pid == pid) {
                processName2 = rai.processName;
                break;
            }
        }
        processNameByAms = processName2;
        return processName2;
    }

    private static void resetProcessNameByAmsCache() {
        processNameByAms = "!";
    }

    static String getProcessNameFromAms(Context context) {
        String processName = getProcessNameFromAmsOrNull(context);
        if (processName == null) {
            return "n/a";
        }
        return processName;
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
        } catch (PackageManager.NameNotFoundException e) {
            return "package name not found";
        }
    }

    private static long getProcessUptime(ErrorReporter reporter) {
        return SystemClock.uptimeMillis() - reporter.getAppStartTickTimeMs();
    }

    private static long getDeviceUptime() {
        return SystemClock.elapsedRealtime();
    }

    private static String getAndroidRuntime() {
        if (Build.VERSION.SDK_INT < 19) {
            return "DALVIK";
        }
        String bootClassPath = System.getProperty("java.boot.class.path");
        if (bootClassPath != null) {
            if (bootClassPath.contains("/system/framework/core-libart.jar")) {
                return "ART";
            }
            if (bootClassPath.contains("/system/framework/core.jar")) {
                return "DALVIK";
            }
        }
        return "UNKNOWN";
    }

    private static String getCpuAbis() {
        String abiStr = Arrays.toString(Build.VERSION.SDK_INT >= 21 ? Api21Utils.getCpuAbis() : new String[]{Build.CPU_ABI, Build.CPU_ABI2});
        int length = abiStr.length();
        if (length >= 2 && abiStr.charAt(0) == '[' && abiStr.charAt(length - 1) == ']') {
            return abiStr.substring(1, length - 1);
        }
        return abiStr;
    }

    private static String toString(Display display) {
        if (display == null) {
            return "";
        }
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        StringBuilder result = new StringBuilder();
        result.append("width=").append(display.getWidth()).append('\n').append("height=").append(display.getHeight()).append('\n').append("pixelFormat=").append(display.getPixelFormat()).append('\n').append("refreshRate=").append(display.getRefreshRate()).append("fps").append('\n').append("metrics.density=x").append(metrics.density).append('\n').append("metrics.scaledDensity=x").append(metrics.scaledDensity).append('\n').append("metrics.widthPixels=").append(metrics.widthPixels).append('\n').append("metrics.heightPixels=").append(metrics.heightPixels).append('\n').append("metrics.xdpi=").append(metrics.xdpi).append('\n').append("metrics.ydpi=").append(metrics.ydpi);
        return result.toString();
    }

    private static PackageManagerWrapper getPackageManagerWrapper(Context ctx) {
        if (sPackageManagerWrapper == null) {
            sPackageManagerWrapper = new PackageManagerWrapper(ctx, ACRA.LOG_TAG);
        }
        return sPackageManagerWrapper;
    }

    private static void reportInternalStorageUsage(AcraReportingConfig config, CrashReportData crashReport, @Nullable Writer writer) {
        if (shouldAddField("DISK_SIZE_TOTAL", crashReport, config)) {
            try {
                ErrorReporter.put("DISK_SIZE_TOTAL", Long.toString(StatFsUtil.getTotalInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReport, writer);
            } catch (Exception ex) {
                noteReportFieldFailure(crashReport, "DISK_SIZE_TOTAL", ex);
            }
        }
        if (shouldAddField("DISK_SIZE_AVAILABLE", crashReport, config)) {
            try {
                ErrorReporter.put("DISK_SIZE_AVAILABLE", Long.toString(StatFsUtil.getAvailableInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReport, writer);
            } catch (Exception ex2) {
                noteReportFieldFailure(crashReport, "DISK_SIZE_AVAILABLE", ex2);
            }
        }
        if (shouldAddField("DISK_SIZE_USED", crashReport, config)) {
            try {
                ErrorReporter.put("DISK_SIZE_USED", Long.toString(StatFsUtil.getUsedInternalStorageSpace(BreakpadManager.MD_FB_RECORD_ALL_LIBS)), crashReport, writer);
            } catch (Exception ex3) {
                noteReportFieldFailure(crashReport, "DISK_SIZE_USED", ex3);
            }
        }
    }

    @Nullable
    private static PackageInfo getWebViewPackageInfo(Context context) {
        String webviewPackageName;
        try {
            if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 23) {
                webviewPackageName = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            } else {
                webviewPackageName = (String) Class.forName("android.webkit.WebViewFactory").getMethod("getWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            }
            if (webviewPackageName == null) {
                return null;
            }
            return getPackageManagerWrapper(context).getPackageInfo(webviewPackageName, 0);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        } catch (NoSuchMethodException e4) {
            return null;
        }
    }
}
