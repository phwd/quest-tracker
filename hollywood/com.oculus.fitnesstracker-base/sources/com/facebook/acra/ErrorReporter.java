package com.facebook.acra;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Time;
import com.facebook.acra.Spool;
import com.facebook.acra.anr.ANRDataProvider;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.customdata.ProxyCustomDataStore;
import com.facebook.acra.sender.BaseHttpPostSender;
import com.facebook.acra.sender.ReportSender;
import com.facebook.acra.sender.ReportSenderException;
import com.facebook.acra.util.AttachmentUtil;
import com.facebook.acra.util.CrashTimeDataCollectorHelper;
import com.facebook.acra.util.InputStreamField;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.SimpleTraceLogger;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.common.exceptionhandler.ManagedExceptionHandler;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.ErrorReportingDiagnosticData;
import com.facebook.errorreporting.appstate.GlobalErrorReporter;
import com.facebook.errorreporting.appstate.blackbox.BlackBoxState;
import com.facebook.errorreporting.common.exception.NonCrashException;
import com.facebook.reliability.anr.AnrState;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ErrorReporter implements ManagedExceptionHandler {
    private static final Object ANR_REPORTING_LOCK = new Object();
    static final CrashReportType[] REPORTS_TO_CHECK_ON_STARTUP = {CrashReportType.ACRA_CRASH_REPORT, CrashReportType.NATIVE_CRASH_REPORT};
    private static final Object UNCAUGHT_EXCEPTION_LOCK = new Object();
    private static final AtomicInteger mSendAttempts = new AtomicInteger();
    private static final Pattern mSigquitCmdLinePattern = Pattern.compile("^Cmd line: (.*)", 8);
    static SecureSettingsResolver sSecureSettingsResolver = new DefaultSecureSettingsResolver();
    public static final ReentrantReadWriteLock sSystemLibFileLock = new ReentrantReadWriteLock();
    private volatile ANRDataProvider mANRDataProvider;
    final SimpleTraceLogger mActivityLogger;
    private final Set<String> mAnrFilesInProgress;
    private final Time mAppStartDate;
    public volatile long mAppStartTickTimeMs;
    public volatile String mAppVersionCode;
    public volatile String mAppVersionName;
    volatile String mClientUserId;
    volatile AcraReportingConfig mConfig;
    volatile Map<String, String> mConstantFields;
    volatile Context mContext;
    private final AtomicReference<CrashReportedObserver> mCrashReportedObserver;
    private final AtomicReference<ExceptionTranslationHook> mExceptionTranslationHook;
    private volatile ExcludedReportObserver mExcludedReportObserver;
    volatile boolean mFinishedCheckingReports;
    volatile boolean mInitializationComplete;
    volatile long mInstallTime;
    public final Map<String, CustomReportDataSupplier> mInstanceLazyCustomParameters;
    private volatile LogBridge mLogBridge;
    private volatile long mMaxReportSize;
    private volatile byte[] mOomReservation;
    private int mPendingReportWriters;
    private volatile File mPreallocFileName;
    final ArrayList<ReportSender> mReportSenders;
    volatile String mUserId;

    public interface CrashReportedObserver {
    }

    public interface ExcludedReportObserver {
    }

    /* access modifiers changed from: package-private */
    public static class Holder {
        public static final ErrorReporter ERROR_REPORTER = new ErrorReporter((byte) 0);
    }

    /* access modifiers changed from: package-private */
    public interface ReportHandler {
        boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z);
    }

    /* access modifiers changed from: package-private */
    public interface SecureSettingsResolver {
        String getString(ContentResolver contentResolver, String str);
    }

    public static String getSigquitTracesExtension() {
        return ".stacktrace";
    }

    /* synthetic */ ErrorReporter(byte b) {
        this();
    }

    static /* synthetic */ int access$908(ErrorReporter errorReporter) {
        int i = errorReporter.mPendingReportWriters;
        errorReporter.mPendingReportWriters = i + 1;
        return i;
    }

    static /* synthetic */ int access$910(ErrorReporter errorReporter) {
        int i = errorReporter.mPendingReportWriters;
        errorReporter.mPendingReportWriters = i - 1;
        return i;
    }

    public final String getAppStartDateFormat3339() {
        String format3339;
        synchronized (this.mAppStartDate) {
            format3339 = this.mAppStartDate.format3339(false);
        }
        return format3339;
    }

    private void reportSoftError(Throwable th, String str, String str2, ErrorReporter errorReporter) {
        String sessionId = this.mConfig == null ? null : this.mConfig.getSessionId();
        CrashReportData crashReportData = new CrashReportData();
        crashReportData.put("soft_error_category", str);
        crashReportData.put("soft_error_message", str2);
        if (sessionId != null) {
            crashReportData.put("ASL_SESSION_ID", sessionId);
        }
        errorReporter.handleException(th, crashReportData);
    }

    /* access modifiers changed from: package-private */
    public static class SigquitFileHeader {
        String commandLine;
        String versionCode;
        String versionName;

        private SigquitFileHeader() {
        }

        /* synthetic */ SigquitFileHeader(byte b) {
            this();
        }
    }

    static class AcraReportHandler implements ReportHandler {
        private AcraReportHandler() {
        }

        /* synthetic */ AcraReportHandler(byte b) {
            this();
        }

        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public final boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z) {
            File file = fileBeingConsumed.fileName;
            String name = file.getName();
            BLog.d(ACRA.LOG_TAG, "Loading file %s", name);
            try {
                CrashReportData access$000 = ErrorReporter.access$000(errorReporter, fileBeingConsumed);
                if (access$000 != null) {
                    access$000.put("ACRA_REPORT_TYPE", CrashReportType.ACRA_CRASH_REPORT.name());
                    access$000.put("ACRA_REPORT_FILENAME", name);
                    access$000.put("UPLOADED_BY_PROCESS", str);
                    BLog.i(ACRA.LOG_TAG, "Sending file %s", name);
                    errorReporter.sendCrashReport(access$000);
                    ErrorReporter.deleteFile(file);
                }
                return true;
            } catch (RuntimeException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to send crash reports");
                ErrorReporter.deleteFile(file);
                return false;
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "Failed to load crash report for %s", name);
                ErrorReporter.deleteFile(file);
                return false;
            } catch (ReportSenderException e3) {
                BLog.e(ACRA.LOG_TAG, e3, "Failed to send crash report for %s", name);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void purgeDirectory(File file, final String str) {
        File[] listFiles;
        AnonymousClass1 r0 = str != null ? new FileFilter() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass1 */

            public final boolean accept(File file) {
                return file.getName().endsWith(str);
            }
        } : null;
        if (!(file == null || (listFiles = file.listFiles(r0)) == null)) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    purgeDirectory(file2, str);
                }
                deleteFile(file2);
            }
        }
    }

    static class CachedANRReportHandler implements ReportHandler {
        CachedANRReportHandler() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2 A[Catch:{ all -> 0x0188 }] */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x00fe  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0129  */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x0173  */
        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean handleReport(com.facebook.acra.ErrorReporter r20, com.facebook.acra.Spool.FileBeingConsumed r21, java.lang.String r22, boolean r23) {
            /*
            // Method dump skipped, instructions count: 469
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.CachedANRReportHandler.handleReport(com.facebook.acra.ErrorReporter, com.facebook.acra.Spool$FileBeingConsumed, java.lang.String, boolean):boolean");
        }

        private static int findANRTraces(String str, long j, String str2, StringBuilder sb) {
            if (str == null) {
                return -1;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(str2));
                try {
                    Pattern compile = Pattern.compile("----- pid (\\d+) at (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}).* -----");
                    Pattern compile2 = Pattern.compile("----- end (\\d+) -----");
                    Pattern compile3 = Pattern.compile("Cmd line: (.*)");
                    int i = 0;
                    boolean z = false;
                    int i2 = -1;
                    int i3 = 0;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            boolean z2 = true;
                            if (z) {
                                sb.append(readLine);
                                sb.append("\n");
                                Matcher matcher = compile2.matcher(readLine);
                                if (matcher.find()) {
                                    if (i2 != Integer.parseInt(matcher.group(1))) {
                                        sb.setLength(i);
                                    }
                                    bufferedReader.close();
                                    return i3;
                                }
                            } else {
                                Matcher matcher2 = compile.matcher(readLine);
                                if (!matcher2.find()) {
                                    continue;
                                } else {
                                    if (sb != null) {
                                        sb.setLength(i);
                                    }
                                    i2 = Integer.parseInt(matcher2.group(1));
                                    long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(matcher2.group(2)).getTime();
                                    String readLine2 = bufferedReader.readLine();
                                    Matcher matcher3 = compile3.matcher(readLine2);
                                    if (matcher3.find()) {
                                        String group = matcher3.group(1);
                                        if (!group.equals(str) || sb != null) {
                                            if (!group.equals(str) || Math.abs(j - time) >= 15000 || sb == null) {
                                                z2 = z;
                                            } else {
                                                sb.append(readLine);
                                                sb.append("\n");
                                                sb.append(readLine2);
                                                sb.append("\n");
                                            }
                                            if (!z2 && !group.startsWith("/")) {
                                                i3++;
                                            }
                                            z = z2;
                                        } else {
                                            bufferedReader.close();
                                            return i3;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                            }
                            i = 0;
                        } else {
                            bufferedReader.close();
                            return -1;
                        }
                    }
                } catch (Throwable unused) {
                }
            } catch (ParseException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to parse ANR timestamp.");
                return -1;
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "Failed to read ANR traces.");
                return -1;
            } catch (NumberFormatException e3) {
                BLog.e(ACRA.LOG_TAG, e3, "Failed to extract pid from ANR traces.");
                return -1;
            }
            throw th;
        }
    }

    public enum CrashReportType {
        ACRA_CRASH_REPORT("acra-reports", 1572864, null, new AcraReportHandler((byte) 0), null, ".stacktrace"),
        NATIVE_CRASH_REPORT("minidumps", 8388608, "MINIDUMP", null, null, ".dmp"),
        ANR_REPORT("traces", 524288, "SIGQUIT", null, null, ".stacktrace"),
        CACHED_ANR_REPORT("traces", 524288, "SIGQUIT", new CachedANRReportHandler(), new String[]{".upd"}, ".cachedreport");
        
        private final String attachmentField;
        private final long defaultMaxSize;
        private final String directory;
        private final String[] extraFileExtensions;
        private final String[] fileExtensions;
        final ReportHandler handler;
        private final Object mLock = new Object();
        private Spool mSpool;

        private CrashReportType(String str, long j, String str2, ReportHandler reportHandler, String[] strArr, String... strArr2) {
            this.directory = str;
            this.defaultMaxSize = j;
            this.attachmentField = str2;
            this.handler = reportHandler;
            this.extraFileExtensions = strArr;
            this.fileExtensions = strArr2;
        }

        public final Spool getSpool(Context context) {
            Spool spool;
            synchronized (this.mLock) {
                if (this.mSpool == null) {
                    this.mSpool = new Spool(context.getDir(this.directory, 0));
                }
                spool = this.mSpool;
            }
            return spool;
        }

        static /* synthetic */ Spool.Snapshot access$1300(CrashReportType crashReportType, Context context) {
            final String[] strArr = crashReportType.fileExtensions;
            return crashReportType.getSpool(context).snapshot(new FifoSpoolComparator((byte) 0), new FilenameFilter() {
                /* class com.facebook.acra.ErrorReporter.CrashReportType.AnonymousClass1 */

                public final boolean accept(File file, String str) {
                    for (String str2 : strArr) {
                        if (str.endsWith(str2)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }

    public final class ReportsSenderWorker extends Thread {
        private Throwable exception;
        private FileGenerator mGenerator;
        private final CrashReportData mInMemoryReportToSend;
        private final Spool.FileBeingProduced mReportFileUnderConstruction;
        private final CrashReportType[] mReportTypesToSend;

        public ReportsSenderWorker(ErrorReporter errorReporter, CrashReportData crashReportData, Spool.FileBeingProduced fileBeingProduced) {
            this(crashReportData, fileBeingProduced, null);
        }

        public ReportsSenderWorker(ErrorReporter errorReporter, CrashReportType... crashReportTypeArr) {
            this(null, null, crashReportTypeArr);
        }

        private ReportsSenderWorker(CrashReportData crashReportData, Spool.FileBeingProduced fileBeingProduced, CrashReportType[] crashReportTypeArr) {
            super("ReportsSenderWorker");
            this.exception = null;
            this.mGenerator = null;
            this.mInMemoryReportToSend = crashReportData;
            this.mReportFileUnderConstruction = fileBeingProduced;
            this.mReportTypesToSend = crashReportTypeArr;
        }

        /* access modifiers changed from: package-private */
        public final void doSend() throws ReportSenderException {
            Throwable th;
            PowerManager.WakeLock wakeLock;
            ErrorReporter.mSendAttempts.getAndIncrement();
            try {
                wakeLock = acquireWakeLock();
                try {
                    if (this.mInMemoryReportToSend == null) {
                        ErrorReporter.this.prepareReports(Integer.MAX_VALUE, this.mGenerator, true, this.mReportTypesToSend);
                    } else {
                        CrashReportData crashReportData = this.mInMemoryReportToSend;
                        crashReportData.put("UPLOADED_BY_PROCESS", CrashTimeDataCollector.getProcessNameFromAms(ErrorReporter.this.mContext));
                        ErrorReporter.this.sendCrashReport(crashReportData);
                        if (this.mReportFileUnderConstruction != null) {
                            this.mReportFileUnderConstruction.fileName.delete();
                        }
                    }
                    if (wakeLock != null && wakeLock.isHeld()) {
                        wakeLock.release();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (wakeLock != null && wakeLock.isHeld()) {
                        wakeLock.release();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                wakeLock = null;
                wakeLock.release();
                throw th;
            }
        }

        public final void run() {
            synchronized (ErrorReporter.this) {
                ErrorReporter.access$908(ErrorReporter.this);
            }
            try {
                doSend();
            } catch (Throwable th) {
                ErrorReporter.this.safeClose(this.mReportFileUnderConstruction);
                throw th;
            }
            ErrorReporter.this.safeClose(this.mReportFileUnderConstruction);
            synchronized (ErrorReporter.this) {
                ErrorReporter.access$910(ErrorReporter.this);
            }
            ErrorReporter.this.startUploadIfReady();
        }

        private PowerManager.WakeLock acquireWakeLock() {
            if (!new PackageManagerWrapper(ErrorReporter.this.mContext, ACRA.LOG_TAG).hasPermission("android.permission.WAKE_LOCK")) {
                return null;
            }
            PowerManager.WakeLock newWakeLock = ((PowerManager) ErrorReporter.this.mContext.getSystemService("power")).newWakeLock(1, "ACRA wakelock");
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire();
            return newWakeLock;
        }
    }

    private ErrorReporter() {
        this.mReportSenders = new ArrayList<>();
        this.mOomReservation = null;
        this.mMaxReportSize = 1572864;
        this.mAnrFilesInProgress = new HashSet();
        this.mInstanceLazyCustomParameters = new HashMap();
        this.mPreallocFileName = null;
        this.mActivityLogger = new SimpleTraceLogger(20);
        this.mAppStartDate = new Time();
        this.mExceptionTranslationHook = new AtomicReference<>();
        this.mExcludedReportObserver = null;
        this.mCrashReportedObserver = new AtomicReference<>();
        if (ErrorReportingDiagnosticData.sInstance != null) {
            BLog.w("ErrorReporter", "ErrorReportingDiagnosticData.setInstance already set.");
            return;
        }
        BLog.d("ErrorReporter", "Using ErrorReporter for ErrorReportingDiagnosticData");
        ErrorReportingDiagnosticData.sInstance = new ErrorReportingDiagnosticData.ICustomData() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass2 */
        };
        GlobalErrorReporter.setInstance(new GlobalErrorReporter.IReporter() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass3 */
        });
    }

    public final String getSigquitTracesPath() {
        return this.mContext.getDir("traces", 0).getPath();
    }

    public final boolean addToAnrInProgressUpdateFile(Map<String, String> map) throws IOException {
        synchronized (this.mAnrFilesInProgress) {
            if (this.mAnrFilesInProgress.isEmpty()) {
                return false;
            }
            String next = this.mAnrFilesInProgress.iterator().next();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(next + ".upd"), true);
            new CrashReportData(map).store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return true;
        }
    }

    @Deprecated
    public static void putCustomData(String str, String str2) {
        putCustomDataInternal(str, str2);
    }

    public static void putCustomDataInternal(String str, String str2) {
        ProxyCustomDataStore.getInstance().setCustomData(str, str2, new Object[0]);
    }

    public static String getCustomData(String str) {
        return ProxyCustomDataStore.getInstance().getCustomData(str);
    }

    static Map<String, String> getCustomFieldsSnapshot() {
        return ProxyCustomDataStore.getInstance().getSnapshot();
    }

    /* access modifiers changed from: package-private */
    public final Map<String, CustomReportDataSupplier> getLazyCustomFieldsSnapshot() {
        TreeMap treeMap;
        synchronized (this.mInstanceLazyCustomParameters) {
            treeMap = new TreeMap(this.mInstanceLazyCustomParameters);
        }
        return treeMap;
    }

    public static ErrorReporter getInstance() {
        return Holder.ERROR_REPORTER;
    }

    public final void initFallible() {
        String str;
        String str2;
        AcraReportingConfig acraReportingConfig = this.mConfig;
        this.mOomReservation = new byte[65536];
        synchronized (this.mAppStartDate) {
            this.mAppStartDate.setToNow();
            this.mOomReservation[0] = 1;
        }
        int aPKVersionCode = BuildConstants.getAPKVersionCode();
        this.mAppVersionCode = Integer.toString(BuildConstants.getBuildID());
        PackageManagerWrapper packageManagerWrapper = new PackageManagerWrapper(this.mContext, ACRA.LOG_TAG);
        PackageInfo packageInfo = packageManagerWrapper.getPackageInfo(packageManagerWrapper.context.getPackageName(), 0);
        if (packageInfo != null && aPKVersionCode == 1) {
            this.mAppVersionCode = Integer.toString(packageInfo.versionCode);
        }
        if (packageInfo == null || (!(packageInfo.versionCode == aPKVersionCode || aPKVersionCode == 1) || packageInfo.versionName == null)) {
            this.mAppVersionName = "not set";
        } else {
            this.mAppVersionName = packageInfo.versionName;
        }
        TreeMap treeMap = new TreeMap();
        AcraReportingConfig acraReportingConfig2 = this.mConfig;
        try {
            str = sSecureSettingsResolver.getString(this.mContext.getContentResolver(), "android_id");
        } catch (Exception e) {
            BLog.e("ErrorReporter", "Failed to fetch the constant field ANDROID_ID", e);
            str = BuildConfig.VERSION_NAME;
        }
        treeMap.put("ANDROID_ID", str);
        AcraReportingConfig acraReportingConfig3 = this.mConfig;
        treeMap.put("APP_VERSION_CODE", this.mAppVersionCode);
        AcraReportingConfig acraReportingConfig4 = this.mConfig;
        treeMap.put("APP_VERSION_NAME", this.mAppVersionName);
        AcraReportingConfig acraReportingConfig5 = this.mConfig;
        treeMap.put("PACKAGE_NAME", this.mContext.getPackageName());
        AcraReportingConfig acraReportingConfig6 = this.mConfig;
        PackageManager packageManager = packageManagerWrapper.context.getPackageManager();
        if (packageManager == null) {
            str2 = null;
        } else {
            str2 = packageManager.getInstallerPackageName(packageManagerWrapper.context.getPackageName());
        }
        treeMap.put("INSTALLER_NAME", str2);
        AcraReportingConfig acraReportingConfig7 = this.mConfig;
        treeMap.put("PHONE_MODEL", Build.MODEL);
        AcraReportingConfig acraReportingConfig8 = this.mConfig;
        treeMap.put("DEVICE", Build.DEVICE);
        AcraReportingConfig acraReportingConfig9 = this.mConfig;
        treeMap.put("ANDROID_VERSION", Build.VERSION.RELEASE);
        AcraReportingConfig acraReportingConfig10 = this.mConfig;
        treeMap.put("OS_VERSION", System.getProperty("os.version"));
        AcraReportingConfig acraReportingConfig11 = this.mConfig;
        treeMap.put("BUILD_HOST", Build.HOST);
        AcraReportingConfig acraReportingConfig12 = this.mConfig;
        treeMap.put("BRAND", Build.BRAND);
        AcraReportingConfig acraReportingConfig13 = this.mConfig;
        treeMap.put("PRODUCT", Build.PRODUCT);
        AcraReportingConfig acraReportingConfig14 = this.mConfig;
        File filesDir = this.mContext.getFilesDir();
        treeMap.put("FILE_PATH", filesDir != null ? filesDir.getAbsolutePath() : "n/a");
        AcraReportingConfig acraReportingConfig15 = this.mConfig;
        if (packageInfo != null) {
            treeMap.put("APP_INSTALL_TIME", CrashTimeDataCollectorHelper.formatTimestamp(packageInfo.firstInstallTime));
        }
        AcraReportingConfig acraReportingConfig16 = this.mConfig;
        if (packageInfo != null) {
            treeMap.put("APP_INSTALL_EPOCH_TIME", String.valueOf(packageInfo.firstInstallTime));
        }
        AcraReportingConfig acraReportingConfig17 = this.mConfig;
        if (packageInfo != null) {
            treeMap.put("APP_UPGRADE_TIME", CrashTimeDataCollectorHelper.formatTimestamp(packageInfo.lastUpdateTime));
        }
        AcraReportingConfig acraReportingConfig18 = this.mConfig;
        if (packageInfo != null) {
            treeMap.put("APP_UPGRADE_EPOCH_TIME", String.valueOf(packageInfo.lastUpdateTime));
        }
        AcraReportingConfig acraReportingConfig19 = this.mConfig;
        if (packageInfo != null) {
            treeMap.put("APP_SINCE_UPGRADE_TIME", Long.toString(System.currentTimeMillis() - packageInfo.lastUpdateTime));
        }
        AcraReportingConfig acraReportingConfig20 = this.mConfig;
        if (this.mContext.getApplicationInfo() != null) {
            treeMap.put("PUBLIC_SOURCE_DIR", this.mContext.getApplicationInfo().publicSourceDir);
        } else {
            treeMap.put("PUBLIC_SOURCE_DIR", "null application info");
        }
        AcraReportingConfig acraReportingConfig21 = this.mConfig;
        treeMap.put("is_relabeled", String.valueOf(BuildConstants.isRelabeled()));
        this.mConstantFields = Collections.unmodifiableMap(treeMap);
        File file = new File(this.mContext.getDir("acra-reports", 0), "reportfile.prealloc");
        AcraReportingConfig acraReportingConfig22 = this.mConfig;
        if (file.length() < 1572864) {
            try {
                preallocateReportFile(file, 1572864);
            } catch (Throwable th) {
                tryLogInternalError(null, th);
                file = null;
            }
        }
        this.mPreallocFileName = file;
    }

    /* access modifiers changed from: package-private */
    public void addCriticalData() {
        String string = this.mContext.getSharedPreferences("acra_criticaldata_store", 0).getString("USER_ID", com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX);
        String string2 = this.mContext.getSharedPreferences("acra_criticaldata_store", 0).getString("CLIENT_USER_ID", com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX);
        String string3 = this.mContext.getSharedPreferences("acra_criticaldata_store", 0).getString("DEVICE_ID", com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX);
        if (string.length() > 0) {
            this.mUserId = string;
        }
        if (string2.length() > 0) {
            this.mClientUserId = string2;
        }
        if (string3.length() > 0) {
            putCustomDataInternal("marauder_device_id", string3);
        }
        Context context = this.mContext;
        HashMap hashMap = new HashMap();
        Set<String> set = null;
        if (Build.VERSION.SDK_INT >= 11) {
            set = context.getSharedPreferences("acra_criticaldata_store", 0).getStringSet("ADDITIONAL_PARAMS", null);
        }
        if (set != null && !set.isEmpty()) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("acra_criticaldata_store", 0);
            for (String str : set) {
                String string4 = sharedPreferences.getString(str, com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX);
                if (!TextUtils.isEmpty(string4)) {
                    hashMap.put(str, string4);
                }
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str2 = (String) entry.getValue();
            if (!TextUtils.isEmpty(str2)) {
                putCustomDataInternal((String) entry.getKey(), str2);
            }
        }
    }

    private void preallocateReportFile(File file, long j) throws IOException {
        Spool.FileBeingProduced produceWithDonorFile = CrashReportType.ACRA_CRASH_REPORT.getSpool(this.mContext).produceWithDonorFile(genCrashReportFileName(ErrorReporter.class, UUID.randomUUID(), ".stacktrace"), null);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(produceWithDonorFile.fileName);
            try {
                byte[] bArr = new byte[32768];
                for (long j2 = 0; j2 < j; j2 += 32768) {
                    fileOutputStream.write(bArr);
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                File file2 = produceWithDonorFile.fileName;
                if (file2.renameTo(file)) {
                    try {
                        produceWithDonorFile.close();
                        return;
                    } catch (Throwable unused) {
                    }
                } else {
                    throw new IOException(String.format("rename of %s to %s failed", file2, file));
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } finally {
            produceWithDonorFile.fileName.delete();
        }
    }

    private Throwable translateException(Throwable th, Map<String, String> map) {
        Throwable th2;
        ExceptionTranslationHook exceptionTranslationHook = this.mExceptionTranslationHook.get();
        int i = 0;
        while (true) {
            th2 = th;
            for (ExceptionTranslationHook exceptionTranslationHook2 = exceptionTranslationHook; exceptionTranslationHook2 != null && th2 != null; exceptionTranslationHook2 = exceptionTranslationHook2.next) {
                try {
                    th2 = exceptionTranslationHook2.translate$467bebb6();
                } catch (Throwable th3) {
                    BLog.w(ACRA.LOG_TAG, th3, "ignoring error in exception translation hook %s", exceptionTranslationHook2);
                }
            }
            if (th2 == th || (i = i + 1) >= 4) {
                return th2;
            }
            th = th2;
        }
        return th2;
    }

    private void uncaughtExceptionImpl(Thread thread, Throwable th, boolean z) throws Throwable {
        while (true) {
            this.mOomReservation = null;
            discardOverlappingReports(CrashReportType.ACRA_CRASH_REPORT);
            try {
                if (Build.VERSION.SDK_INT >= 9) {
                    Api9Utils.disableStrictMode();
                }
            } catch (Throwable th2) {
                tryLogInternalError(null, th2);
            }
            int i = 3;
            try {
                BLog.e(ACRA.LOG_TAG, "ACRA caught a %s exception for %s version %s. Building report.", th.getClass().getSimpleName(), this.mContext.getPackageName(), this.mAppVersionCode);
            } catch (Throwable th3) {
                tryLogInternalError(null, th3);
            }
            TreeMap treeMap = new TreeMap();
            treeMap.put("java_throwing_thread_name", String.valueOf(thread.getName()));
            Throwable translateException = translateException(th, treeMap);
            if (translateException != null) {
                if (z) {
                    i = 4;
                }
                AcraReportingConfig acraReportingConfig = this.mConfig;
                if (getMostSignificantCause(translateException) instanceof OutOfMemoryError) {
                    i &= -2;
                }
                try {
                    BlackBoxState.getInstance().getBlackBoxRecorderControl();
                    handleExceptionInternal(translateException, new CrashReportData(treeMap), null, i);
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    if (!z) {
                        BLog.e(ACRA.LOG_TAG, th, "error during error reporting: will attempt to report error");
                        z = true;
                    } else {
                        throw th;
                    }
                }
            } else {
                return;
            }
        }
    }

    private static void tryLogInternalError(String str, Throwable th) {
        if (str == null) {
            str = "???";
        }
        try {
            BLog.e(ACRA.LOG_TAG, th, "internal ACRA error: %s: ", str);
        } catch (Throwable unused) {
        }
    }

    public static void reportErrorAndTerminate(Thread thread, Throwable th) {
        ExceptionHandlerManager.handleThrowableAndTerminate(thread, th);
    }

    @Override // com.facebook.common.exceptionhandler.ManagedExceptionHandler
    public final void handleUncaughtException$257867d4(Thread thread, Throwable th) {
        roughlyCountPendingReportsOfType(CrashReportType.ACRA_CRASH_REPORT);
        AcraReportingConfig acraReportingConfig = this.mConfig;
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            try {
                uncaughtExceptionImpl(thread, th, false);
            } catch (Throwable th2) {
                tryLogInternalError(null, th2);
            }
        }
    }

    private static String throwableToString(Throwable th) {
        int lastIndexOf;
        int indexOf;
        if (th == null) {
            th = new Exception("Report requested by developer");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        if (th instanceof StackOverflowError) {
            StringBuffer buffer = stringWriter.getBuffer();
            if (buffer.length() > 800000 && (lastIndexOf = buffer.lastIndexOf("\n", 400000)) >= 0 && (indexOf = buffer.indexOf("\n", buffer.length() - 400000)) >= 0) {
                buffer.replace(lastIndexOf, indexOf, "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxSTACK_FRAMES_TRIMMED_FOR_OVERFLOWxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            }
        }
        return stringWriter.toString();
    }

    static void put(String str, String str2, CrashReportData crashReportData, Writer writer) {
        if (crashReportData.generatingIoError != null) {
            writer = null;
        }
        try {
            crashReportData.put(str, str2, writer);
        } catch (IOException e) {
            crashReportData.generatingIoError = e;
        }
    }

    public final ReportsSenderWorker handleException(Throwable th, CrashReportData crashReportData) {
        return handleExceptionInternal(th, crashReportData, null, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                tryLogInternalError("safeClose", th);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v5, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v9, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v27, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v28, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v29, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v30, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX DEBUG: Multi-variable search result rejected for r3v31, resolved type: com.facebook.acra.ErrorReporter$ReportsSenderWorker */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x019e A[Catch:{ all -> 0x01ae }] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01d8 A[SYNTHETIC, Splitter:B:119:0x01d8] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01fc A[Catch:{ all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0203 A[Catch:{ all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x016d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b8 A[SYNTHETIC, Splitter:B:50:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cd A[SYNTHETIC, Splitter:B:61:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00e0 A[Catch:{ all -> 0x01b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0105 A[SYNTHETIC, Splitter:B:73:0x0105] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0173 A[Catch:{ all -> 0x01ae }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.ErrorReporter.ReportsSenderWorker handleExceptionInternal(java.lang.Throwable r19, com.facebook.acra.CrashReportData r20, java.lang.String r21, int r22) {
        /*
        // Method dump skipped, instructions count: 636
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.handleExceptionInternal(java.lang.Throwable, com.facebook.acra.CrashReportData, java.lang.String, int):com.facebook.acra.ErrorReporter$ReportsSenderWorker");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendCrashReport(CrashReportData crashReportData) throws ReportSenderException {
        ArrayList arrayList;
        if (!this.mConfig.isZeroCrashlogBlocked()) {
            AcraReportingConfig acraReportingConfig = this.mConfig;
            synchronized (this.mReportSenders) {
                arrayList = new ArrayList(this.mReportSenders);
            }
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    ReportSender reportSender = (ReportSender) it.next();
                    try {
                        reportSender.send(crashReportData);
                        z = true;
                    } catch (ReportSenderException e) {
                        if (!z) {
                            if (reportSender instanceof BaseHttpPostSender) {
                                AcraReportingConfig acraReportingConfig2 = this.mConfig;
                            }
                            throw e;
                        }
                        BLog.w(ACRA.LOG_TAG, e, "ReportSender of class %s failed but other senders completed their task. ACRA will not send this report again.", reportSender.getClass().getName());
                    }
                }
                return;
            }
            throw new ReportSenderException("no configured report senders", null);
        }
    }

    private boolean mustEmbedAttachments(CrashReportType crashReportType) {
        if (crashReportType != CrashReportType.NATIVE_CRASH_REPORT) {
            return true;
        }
        Iterator<ReportSender> it = this.mReportSenders.iterator();
        while (it.hasNext()) {
            if (!it.next().supportsMultipart()) {
                return true;
            }
        }
        return false;
    }

    private String genCrashReportFileName(Class cls, UUID uuid, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(uuid.toString());
        sb.append("-");
        sb.append(cls.getSimpleName());
        if (this.mAppVersionCode != null) {
            str2 = "-" + this.mAppVersionCode;
        } else {
            str2 = com.oculus.common.build.BuildConfig.PROVIDER_SUFFIX;
        }
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    private void discardOverlappingReports(CrashReportType... crashReportTypeArr) {
        for (CrashReportType crashReportType : crashReportTypeArr) {
            if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT || crashReportType == CrashReportType.ACRA_CRASH_REPORT) {
                if (roughlyCountPendingReportsOfType(crashReportType) > 0) {
                    purgeDirectory(this.mContext.getDir("traces", 0), null);
                    return;
                }
            }
        }
    }

    public final void prepareANRReport(File file, FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        roughlyCountPendingReportsOfType(CrashReportType.CACHED_ANR_REPORT);
        AcraReportingConfig acraReportingConfig = this.mConfig;
        synchronized (ANR_REPORTING_LOCK) {
            buildCachedCrashReport(CrashReportType.ANR_REPORT, null, file, fileGenerator);
        }
    }

    public final int prepareReports(int i, FileGenerator fileGenerator, boolean z, CrashReportType... crashReportTypeArr) {
        int i2;
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - start");
        discardOverlappingReports(crashReportTypeArr);
        int i3 = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            int max = Math.max(0, Integer.MAX_VALUE - i3);
            if (crashReportType.handler != null) {
                i2 = checkAndHandleReportsLocked(max, crashReportType, false);
            } else {
                i2 = processCrashAttachmentsLocked(max, crashReportType, fileGenerator, true);
            }
            i3 += i2;
        }
        if (!shouldSkipReport(CrashReportType.NATIVE_CRASH_REPORT)) {
            Context context = this.mContext;
            new File(context.getApplicationInfo().dataDir, "core").delete();
            NativeCrashDumpReporterUtil.removeFatMinidump(context);
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - finish");
        return i3;
    }

    public final int prepareANRReport(String str, FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        return buildCachedCrashReport(CrashReportType.ANR_REPORT, str, null, fileGenerator);
    }

    public final String getLogcatOutputIfPidFound(boolean z, Integer num) {
        Context context = this.mContext;
        AcraReportingConfig acraReportingConfig = this.mConfig;
        AcraReportingConfig acraReportingConfig2 = this.mConfig;
        String collectLogCat = LogCatCollector.collectLogCat(context, acraReportingConfig, null, null, false, true, false);
        if (collectLogCat != null) {
            return collectLogCat;
        }
        return null;
    }

    public final String getEventsLog() {
        if (Build.VERSION.SDK_INT >= 19) {
            return null;
        }
        Context context = this.mContext;
        AcraReportingConfig acraReportingConfig = this.mConfig;
        AcraReportingConfig acraReportingConfig2 = this.mConfig;
        return LogCatCollector.collectLogCat(context, acraReportingConfig, "events", null, false, true, false);
    }

    /* access modifiers changed from: package-private */
    public class CrashAttachmentException extends Throwable {
        private CrashAttachmentException() {
        }

        /* synthetic */ CrashAttachmentException(ErrorReporter errorReporter, byte b) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class FifoSpoolComparator implements Comparator<Spool.Descriptor> {
        private FifoSpoolComparator() {
        }

        /* synthetic */ FifoSpoolComparator(byte b) {
            this();
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Spool.Descriptor descriptor, Spool.Descriptor descriptor2) {
            Spool.Descriptor descriptor3 = descriptor;
            Spool.Descriptor descriptor4 = descriptor2;
            if (descriptor3.lastModifiedTime == descriptor4.lastModifiedTime) {
                return 0;
            }
            return descriptor3.lastModifiedTime < descriptor4.lastModifiedTime ? -1 : 1;
        }
    }

    private boolean shouldSkipReport(CrashReportType crashReportType) {
        return new File(this.mContext.getDir(crashReportType.directory, 0), ".noupload").exists();
    }

    private File createBackUpDirectory(CrashReportType crashReportType) {
        File file;
        NullPointerException e;
        try {
            file = new File(this.mContext.getDir(crashReportType.directory, 0).getParent(), "reported_crashes");
            try {
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (NullPointerException e2) {
                e = e2;
                BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", "reported_crashes");
                return file;
            }
        } catch (NullPointerException e3) {
            e = e3;
            file = null;
            BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", "reported_crashes");
            return file;
        }
        return file;
    }

    private int keepNLatestDumpFiles(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return 0;
        }
        Arrays.sort(listFiles, new Comparator<File>() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass4 */

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            @Override // java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(File file, File file2) {
                return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
            }
        });
        int i = 0;
        int i2 = 0;
        for (File file2 : listFiles) {
            i2++;
            if (((long) i2) > 5) {
                file2.delete();
                i++;
            }
        }
        return i;
    }

    private HashSet<String> getNewLibs(File file, HashSet<String> hashSet) {
        Throwable th;
        IOException e;
        Closeable closeable = null;
        if (hashSet == null || hashSet.isEmpty()) {
            return null;
        }
        try {
            sSystemLibFileLock.readLock().lock();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (hashSet.contains(readLine)) {
                        hashSet.remove(readLine);
                    }
                } catch (IOException e2) {
                    e = e2;
                    closeable = bufferedReader;
                    try {
                        BLog.e(ACRA.LOG_TAG, e, "GLC getNewLibs IO Exception");
                        safeClose(closeable);
                        sSystemLibFileLock.readLock().unlock();
                        return hashSet;
                    } catch (Throwable th2) {
                        th = th2;
                        safeClose(closeable);
                        sSystemLibFileLock.readLock().unlock();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable = bufferedReader;
                    safeClose(closeable);
                    sSystemLibFileLock.readLock().unlock();
                    throw th;
                }
            }
            safeClose(bufferedReader);
        } catch (IOException e3) {
            e = e3;
            BLog.e(ACRA.LOG_TAG, e, "GLC getNewLibs IO Exception");
            safeClose(closeable);
            sSystemLibFileLock.readLock().unlock();
            return hashSet;
        }
        sSystemLibFileLock.readLock().unlock();
        return hashSet;
    }

    private void updateGLCwithSystemLibs(Spool.FileBeingConsumed fileBeingConsumed) {
        Throwable th;
        IOException e;
        ReentrantReadWriteLock.WriteLock writeLock;
        File fileStreamPath = this.mContext.getFileStreamPath("crash_dump_sys_libs");
        if (!fileStreamPath.exists()) {
            try {
                fileStreamPath.createNewFile();
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "Failed to create GLC Lib file");
                return;
            }
        }
        try {
            HashSet<String> newLibs = getNewLibs(fileStreamPath, new MinidumpReader(fileBeingConsumed.file).getModuleList());
            if (newLibs != null && !newLibs.isEmpty()) {
                Closeable closeable = null;
                try {
                    sSystemLibFileLock.writeLock().lock();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileStreamPath, true), newLibs.size());
                    try {
                        Iterator<String> it = newLibs.iterator();
                        while (it.hasNext()) {
                            bufferedWriter.write(it.next() + "\n");
                        }
                        safeClose(bufferedWriter);
                        writeLock = sSystemLibFileLock.writeLock();
                    } catch (IOException e3) {
                        e = e3;
                        closeable = bufferedWriter;
                        try {
                            BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
                            safeClose(closeable);
                            writeLock = sSystemLibFileLock.writeLock();
                            writeLock.unlock();
                        } catch (Throwable th2) {
                            th = th2;
                            safeClose(closeable);
                            sSystemLibFileLock.writeLock().unlock();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = bufferedWriter;
                        safeClose(closeable);
                        sSystemLibFileLock.writeLock().unlock();
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
                    safeClose(closeable);
                    writeLock = sSystemLibFileLock.writeLock();
                    writeLock.unlock();
                }
                writeLock.unlock();
            }
        } catch (IOException e5) {
            BLog.e(ACRA.LOG_TAG, e5, "Failed to create GLC Lib file");
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:238:0x003a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:118:0x0262 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0092 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:241:0x003a */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.lang.String */
    /* JADX DEBUG: Multi-variable search result rejected for r2v45, resolved type: java.lang.String */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24, types: [com.facebook.acra.Spool$FileBeingConsumed] */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v29, types: [java.util.Map<java.lang.String, com.facebook.acra.util.InputStreamField>, java.util.Map] */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f0, code lost:
        if (r2 == false) goto L_0x027a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0264 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0297 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02a6 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02b3 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02c0 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02cc A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02e6 A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02fc A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x030d A[Catch:{ ReportSenderException -> 0x0316, all -> 0x0313 }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0405 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0425  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0433 A[SYNTHETIC, Splitter:B:225:0x0433] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int processCrashAttachmentsLocked(int r38, com.facebook.acra.ErrorReporter.CrashReportType r39, com.facebook.acra.FileGenerator r40, boolean r41) {
        /*
        // Method dump skipped, instructions count: 1109
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.processCrashAttachmentsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, com.facebook.acra.FileGenerator, boolean):int");
    }

    private static String getLogcatFileName(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType) {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                return new MinidumpReader(fileBeingConsumed.file).getCustomData("logcatFileName");
            } catch (IOException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to find logcat file %s", fileBeingConsumed.fileName);
            }
        }
        return null;
    }

    private int buildCachedCrashReport(CrashReportType crashReportType, String str, File file, FileGenerator fileGenerator) {
        if (shouldSkipReport(crashReportType)) {
            return 0;
        }
        if (str == null && file == null) {
            throw new IllegalArgumentException("stackTrace and traceFile can't be null at the same time");
        }
        CrashReportData crashReportData = null;
        if (str != null) {
            try {
                crashReportData = createCrashReportFromStackTrace(str, crashReportType);
            } catch (Throwable th) {
                if (0 != 0) {
                    closeInputStreamFields(null);
                }
                if (file != null) {
                    deleteFile(file);
                }
                throw th;
            }
        } else if (file != null && (crashReportData = loadCrashReport(file, null, crashReportType, crashReportType.defaultMaxSize, mustEmbedAttachments(crashReportType))) == null) {
            BLog.e(ACRA.LOG_TAG, "Failed to load crash attachment report %s", file);
            if (crashReportData != null) {
                closeInputStreamFields(crashReportData);
            }
            deleteFile(file);
            return 0;
        }
        maybeSendCrashReport(crashReportType, crashReportData, null, fileGenerator, true);
        if (crashReportData != null) {
            closeInputStreamFields(crashReportData);
        }
        if (file != null) {
            deleteFile(file);
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010c A[SYNTHETIC, Splitter:B:61:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0130 A[SYNTHETIC, Splitter:B:73:0x0130] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int maybeSendCrashReport(com.facebook.acra.ErrorReporter.CrashReportType r18, com.facebook.acra.CrashReportData r19, com.facebook.acra.Spool.FileBeingConsumed r20, com.facebook.acra.FileGenerator r21, boolean r22) throws java.lang.Throwable {
        /*
        // Method dump skipped, instructions count: 329
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.maybeSendCrashReport(com.facebook.acra.ErrorReporter$CrashReportType, com.facebook.acra.CrashReportData, com.facebook.acra.Spool$FileBeingConsumed, com.facebook.acra.FileGenerator, boolean):int");
    }

    private void foregroundNativeCrashDetect(Spool.FileBeingConsumed fileBeingConsumed) {
        try {
            MinidumpReader minidumpReader = new MinidumpReader(fileBeingConsumed.file);
            String customData = minidumpReader.getCustomData("APP_STARTED_IN_BACKGROUND");
            boolean z = customData != null && customData.equals("false");
            String string = minidumpReader.getString(-87110452);
            if ((string != null && string.indexOf("Resumed") != -1) || (z && string != null && string.indexOf("\"activities\":[]") != -1)) {
                this.mContext.getSharedPreferences("FacebookApplication", 0).edit().putLong("crash_foreground_timestamp", fileBeingConsumed.fileName.lastModified()).commit();
            }
        } catch (Exception e) {
            writeToLogBridge(ACRA.LOG_TAG, "Error writing into the SharedPreferences", e, null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:135:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0215  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00bd A[Catch:{ IOException -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.CrashReportData loadCrashReport(java.io.File r24, java.io.RandomAccessFile r25, com.facebook.acra.ErrorReporter.CrashReportType r26, long r27, boolean r29) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 694
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.loadCrashReport(java.io.File, java.io.RandomAccessFile, com.facebook.acra.ErrorReporter$CrashReportType, long, boolean):com.facebook.acra.CrashReportData");
    }

    private CrashReportData loadCrashReport(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, long j, boolean z) throws IOException {
        return loadCrashReport(fileBeingConsumed.fileName, fileBeingConsumed.file, crashReportType, j, z);
    }

    private static String readVersionLine(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(21);
        int read = bufferedInputStream.read();
        char[] cArr = new char[20];
        int i = 0;
        while (true) {
            if (read != -1 && i < 20 && read != 10) {
                if (!Character.isDigit(read) && read != 46) {
                    bufferedInputStream.reset();
                    i = 0;
                    break;
                }
                cArr[i] = (char) read;
                read = bufferedInputStream.read();
                i++;
            } else {
                break;
            }
        }
        return new String(cArr, 0, i);
    }

    private CrashReportData createCrashReportFromStackTrace(String str, CrashReportType crashReportType) {
        CrashReportData crashReportData = new CrashReportData();
        crashReportData.put("TIME_OF_CRASH", Long.toString(System.currentTimeMillis()));
        try {
            crashReportData.put(crashReportType.attachmentField, AttachmentUtil.compressToBase64String(str.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            crashReportData.put("REPORT_LOAD_THROW", "throwable: " + e.getMessage());
            BLog.e(ACRA.LOG_TAG, e, "Could not load crash report. File will be deleted.");
        }
        backfillCrashReportData(crashReportData);
        return crashReportData;
    }

    private static String readFile(File file) {
        if (!file.exists()) {
            return "NO_FILE";
        }
        try {
            FileReader fileReader = new FileReader(file);
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        bufferedReader.close();
                        fileReader.close();
                        return readLine;
                    }
                    bufferedReader.close();
                    fileReader.close();
                    return null;
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
        } catch (Exception unused3) {
            return null;
        }
        throw th;
        throw th;
    }

    private void backfillCrashReportData(CrashReportData crashReportData) {
        String str = (String) crashReportData.get("REPORT_ID");
        if (str == null || str.length() == 0) {
            for (Map.Entry<String, String> entry : this.mConstantFields.entrySet()) {
                if (crashReportData.get(entry.getKey()) == null) {
                    crashReportData.put(entry.getKey(), entry.getValue());
                }
            }
        }
        String str2 = this.mUserId;
        String str3 = (String) crashReportData.get("UID");
        if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            crashReportData.put("UID", str2);
        }
    }

    /* access modifiers changed from: private */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean delete = file.delete();
        if (!delete && !file.exists()) {
            delete = true;
        }
        BLog.d(ACRA.LOG_TAG, "Deleting error report: %s", file.getName());
        if (!delete) {
            BLog.w(ACRA.LOG_TAG, "Could not delete error report: %s", file.getName());
        }
        return delete;
    }

    /* access modifiers changed from: package-private */
    public synchronized void startUploadIfReady() {
        AcraReportingConfig acraReportingConfig = this.mConfig;
    }

    /* access modifiers changed from: package-private */
    public void checkNativeReportsOnApplicationStart() {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        AcraReportingConfig acraReportingConfig = this.mConfig;
        if (roughlyCountPendingReportsOfType > 5) {
            ReportsSenderWorker reportsSenderWorker = new ReportsSenderWorker(this, CrashReportType.NATIVE_CRASH_REPORT);
            StrictMode.ThreadPolicy threadPolicy = null;
            if (Build.VERSION.SDK_INT >= 9) {
                threadPolicy = StrictMode.getThreadPolicy();
                Api9Utils.disableStrictMode();
            }
            try {
                reportsSenderWorker.doSend();
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
            } catch (Throwable th) {
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                throw th;
            }
        }
    }

    public final int roughlyCountPendingReportsOfType(CrashReportType... crashReportTypeArr) {
        if (this.mContext == null) {
            BLog.e(ACRA.LOG_TAG, "Trying to get ACRA reports but ACRA is not initialized.");
            return 0;
        }
        int i = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            i += CrashReportType.access$1300(crashReportType, this.mContext).mDescriptors.length;
        }
        return i;
    }

    public final ReportsSenderWorker checkReportsOfType(CrashReportType... crashReportTypeArr) {
        ReportsSenderWorker reportsSenderWorker = new ReportsSenderWorker(this, crashReportTypeArr);
        reportsSenderWorker.start();
        roughlyCountPendingReportsOfType(crashReportTypeArr);
        AcraReportingConfig acraReportingConfig = this.mConfig;
        return reportsSenderWorker;
    }

    public final void addReportSender(ReportSender reportSender) {
        synchronized (this.mReportSenders) {
            this.mReportSenders.add(reportSender);
        }
    }

    public final void removeAllReportSenders() {
        synchronized (this.mReportSenders) {
            this.mReportSenders.clear();
        }
    }

    private static Throwable getMostSignificantCause(Throwable th) {
        if (th instanceof NonCrashException) {
            return th;
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    private void writeToLogBridge(String str, String str2, Throwable th, String str3) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[0];
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (StackTraceElement stackTraceElement2 : stackTrace) {
            if (stackTraceElement2.getClassName().equals(stackTraceElement.getClassName()) && stackTraceElement2.getMethodName().equals(stackTraceElement.getMethodName())) {
                BLog.e("ErrorReporter", th, "Unable to log over log bridge due to exception.");
                return;
            }
        }
        if (this.mLogBridge != null) {
            if (str3 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append("\n");
                sb.append(str3);
            }
        } else if (str3 != null) {
            BLog.e(str, "%s\n%s", str2, str3);
        } else {
            BLog.e(str, th, "%s", str2);
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(AnrState.NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED$65befc1)
    public static final class Api9Utils {
        static void disableStrictMode() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
    }

    static class DefaultSecureSettingsResolver implements SecureSettingsResolver {
        DefaultSecureSettingsResolver() {
        }

        @Override // com.facebook.acra.ErrorReporter.SecureSettingsResolver
        public final String getString(ContentResolver contentResolver, String str) {
            return Settings.Secure.getString(contentResolver, str);
        }
    }

    private int checkAndHandleReportsLocked(int i, CrashReportType crashReportType, boolean z) {
        boolean z2;
        if (crashReportType.handler != null) {
            String processNameFromAms = CrashTimeDataCollector.getProcessNameFromAms(this.mContext);
            Spool.Snapshot access$1300 = CrashReportType.access$1300(crashReportType, this.mContext);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                try {
                    if (!access$1300.hasNext() || i2 >= i) {
                        break;
                    }
                    Spool.FileBeingConsumed next = access$1300.next();
                    int i4 = i3 + 1;
                    if (i3 >= 5) {
                        try {
                            deleteFile(next.fileName);
                        } catch (Throwable unused) {
                        }
                    } else {
                        String str = null;
                        try {
                            str = next.fileName.getCanonicalPath();
                        } catch (IOException unused2) {
                        }
                        if (crashReportType == CrashReportType.CACHED_ANR_REPORT) {
                            synchronized (this.mAnrFilesInProgress) {
                                if (this.mAnrFilesInProgress.contains(str)) {
                                    this.mAnrFilesInProgress.remove(str);
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                            }
                        } else {
                            z2 = false;
                        }
                        if (crashReportType.handler.handleReport(this, next, processNameFromAms, z2)) {
                            i2++;
                            if (next == null) {
                                i3 = i4;
                            }
                        } else if (next != null) {
                            next.close();
                        }
                    }
                    next.close();
                    i3 = i4;
                } catch (Throwable unused3) {
                }
            }
            access$1300.close();
            return i2;
        }
        throw new NullPointerException("ErrorReporter::checkAndHandleReportsLocked report type requires a handler");
        throw th;
        throw th;
    }

    private static void closeInputStreamFields(CrashReportData crashReportData) {
        for (Map.Entry<String, InputStreamField> entry : crashReportData.mInputStreamFields.entrySet()) {
            InputStreamField value = entry.getValue();
            if (!(value == null || value.mInputStream == null)) {
                try {
                    value.mInputStream.close();
                } catch (IOException unused) {
                }
            }
        }
    }

    static /* synthetic */ CrashReportData access$000(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        return errorReporter.loadCrashReport(fileBeingConsumed, CrashReportType.ACRA_CRASH_REPORT, errorReporter.mMaxReportSize, true);
    }
}
