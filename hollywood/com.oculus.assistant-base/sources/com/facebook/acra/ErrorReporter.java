package com.facebook.acra;

import X.AbstractC0123Bt;
import X.AbstractC0463a6;
import X.AnonymousClass08;
import X.Bp;
import X.C0122Bs;
import X.C0139Dd;
import X.C0150Dr;
import X.C0153Dv;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import com.facebook.acra.Spool;
import com.facebook.acra.anr.ANRDataProvider;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.acra.constants.ReportField;
import com.facebook.acra.criticaldata.CriticalAppData;
import com.facebook.acra.customdata.ProxyCustomDataStore;
import com.facebook.acra.sender.BaseHttpPostSender;
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.ReportSender;
import com.facebook.acra.sender.ReportSenderException;
import com.facebook.acra.util.AttachmentUtil;
import com.facebook.acra.util.CrashTimeDataCollectorHelper;
import com.facebook.acra.util.InputStreamField;
import com.facebook.acra.util.JsonReportWriter;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.SimpleTraceLogger;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.common.build.BuildConstants;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;
import java.net.Proxy;
import java.security.SecureRandom;
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

public final class ErrorReporter implements AbstractC0123Bt {
    public static final String ACRA_DIRNAME = "acra-reports";
    public static final int ACTION_DISCARD_AND_DONT_SEND = 1;
    public static final int ACTION_SEND = 2;
    public static final String ANR_EXTRA_PROPERTIES_EXTENSION = ".upd";
    public static final Object ANR_REPORTING_LOCK = new Object();
    public static final String ANR_TRACES_FILE_PATH = "/data/anr/traces.txt";
    public static final String CACHED_REPORTFILE_EXTENSION = ".cachedreport";
    public static final String CRASH_ATTACHMENT_DUMMY_STACKTRACE = "crash attachment";
    public static final String CRASH_DUMP_SYS_LIBS_FILE = "crash_dump_sys_libs";
    public static final long DEFAULT_MAX_REPORT_SIZE = 1572864;
    public static final int DEFAULT_OOM_RESERVATION = 65536;
    public static final String DUMPFILE_EXTENSION = ".dmp";
    public static final String DUMP_DIR = "minidumps";
    public static final String FILE_IAB_OPEN_TIMES = "iab_open_times";
    public static final String FILE_LAST_ACTIVITY = "last_activity_opened";
    public static final int HANDLE_EXCEPTION_NEVER_SEND_IMMEDIATELY = 4;
    public static final int HANDLE_EXCEPTION_SEND_IMMEDIATELY = 1;
    public static final int HANDLE_EXCEPTION_SEND_SYNCHRONOUSLY = 2;
    public static final int MAX_ACRA_REPORTS_ON_DISK = 5;
    public static final int MAX_ANR_TRACES_TIME_DELTA_MS = 15000;
    public static final int MAX_SEND_REPORTS = 5;
    public static final int MAX_STACK_LENGTH_FOR_OVERFLOW = 800000;
    public static final int MAX_TRACE_COUNT_LIMIT = 20;
    public static final int MAX_TRANSLATION_HOOK_RUNS = 4;
    public static final int MAX_VERSION_LINE_LENGTH = 20;
    public static final long NATIVE_MAX_REPORT_SIZE = 8388608;
    public static final String NO_FILE = "NO_FILE";
    public static final long NUM_NATIVE_CRASHES_SAVED = 5;
    public static final String PREALLOCATED_REPORTFILE = "reportfile.prealloc";
    public static final String REPORTED_CRASH_DIR = "reported_crashes";
    public static final String REPORTFILE_EXTENSION = ".stacktrace";
    public static final CrashReportType[] REPORTS_TO_CHECK_ON_STARTUP = {CrashReportType.ACRA_CRASH_REPORT, CrashReportType.NATIVE_CRASH_REPORT};
    public static final String SIGQUIT_DIR = "traces";
    public static final long SIGQUIT_MAX_REPORT_SIZE = 524288;
    public static final int SIGQUIT_PROCESS_NAME_BUFFER_SIZE = 1024;
    public static final String STACK_TRIMMED_MESSAGE = "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxSTACK_FRAMES_TRIMMED_FOR_OVERFLOWxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static final String TAG = "ErrorReporter";
    public static final Object UNCAUGHT_EXCEPTION_LOCK = new Object();
    public static final String UNKNOWN_FIELD_VALUE = "unknown";
    public static final String mInternalException = "ACRA_INTERNAL=java.lang.Exception: An exception occurred while trying to collect data about an ACRA internal error\n\tat com.facebook.acra.ErrorReporter.handleException(ErrorReporter.java:810)\n\tat com.facebook.acra.ErrorReporter.handleException(ErrorReporter.java:866)\n\tat com.facebook.acra.ErrorReporter.uncaughtException(ErrorReporter.java:666)\n\tat java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:693)\n\tat java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:690)\n";
    public static final AtomicInteger mSendAttempts = new AtomicInteger();
    public static final Pattern mSigquitCmdLinePattern = Pattern.compile("^Cmd line: (.*)", 8);
    public static SecureSettingsResolver sSecureSettingsResolver = new DefaultSecureSettingsResolver();
    public static final ReentrantReadWriteLock sSystemLibFileLock = new ReentrantReadWriteLock();
    public volatile ANRDataProvider mANRDataProvider;
    public final SimpleTraceLogger mActivityLogger;
    public final Set mAnrFilesInProgress;
    public final Time mAppStartDate;
    public volatile long mAppStartTickTimeMs;
    public volatile String mAppVersionCode;
    public volatile String mAppVersionName;
    public BatchUploader mBatchUploader;
    public volatile Thread.UncaughtExceptionHandler mChainedHandler;
    public volatile String mClientUserId;
    public volatile AcraReportingConfig mConfig;
    public volatile Map mConstantFields;
    public volatile Context mContext;
    public final AtomicReference mCrashReportedObserver;
    public final AtomicReference mExceptionTranslationHook;
    public volatile ExcludedReportObserver mExcludedReportObserver;
    public volatile boolean mFinishedCheckingReports;
    public volatile boolean mInitializationComplete;
    public volatile long mInstallTime;
    public final Map mInstanceLazyCustomParameters;
    public volatile LogBridge mLogBridge;
    public volatile long mMaxReportSize;
    public volatile byte[] mOomReservation;
    public int mPendingReportWriters;
    public volatile File mPreallocFileName;
    public final ArrayList mReportSenders;
    public volatile boolean mStartedBatchUploader;
    public volatile String mUserId;

    public class AcraReportHandler implements ReportHandler {
        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z) {
            File file = fileBeingConsumed.fileName;
            String name = file.getName();
            C0139Dd.A0F("ACRA", "Loading file %s", name);
            try {
                CrashReportData loadAcraCrashReport = errorReporter.loadAcraCrashReport(fileBeingConsumed);
                if (loadAcraCrashReport != null) {
                    loadAcraCrashReport.put(ReportField.ACRA_REPORT_TYPE, CrashReportType.ACRA_CRASH_REPORT.name());
                    loadAcraCrashReport.put(ReportField.ACRA_REPORT_FILENAME, name);
                    loadAcraCrashReport.put(ReportField.UPLOADED_BY_PROCESS, str);
                    C0139Dd.A0G("ACRA", "Sending file %s", name);
                    errorReporter.sendCrashReport(loadAcraCrashReport);
                    ErrorReporter.deleteFile(file);
                }
                return true;
            } catch (RuntimeException e) {
                C0139Dd.A0S("ACRA", e, "Failed to send crash reports");
                ErrorReporter.deleteFile(file);
                return false;
            } catch (IOException e2) {
                C0139Dd.A0V("ACRA", e2, "Failed to load crash report for %s", name);
                ErrorReporter.deleteFile(file);
                return false;
            } catch (ReportSenderException e3) {
                C0139Dd.A0V("ACRA", e3, "Failed to send crash report for %s", name);
                return false;
            }
        }

        public AcraReportHandler() {
        }

        public /* synthetic */ AcraReportHandler(AnonymousClass1 r1) {
        }
    }

    public final class Api16Utils {
        public static void applyBigTextStyle(Notification.Builder builder, String str) {
            builder.setStyle(new Notification.BigTextStyle().bigText(str));
        }
    }

    public final class Api9Utils {
        public static void disableStrictMode() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }

        public static void restoreStrictMode(Object obj) {
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) obj);
        }

        public static Object saveStrictMode() {
            return StrictMode.getThreadPolicy();
        }
    }

    public class CachedANRReportHandler implements ReportHandler {
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c0, code lost:
            r10.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c3, code lost:
            return r17;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int findANRTraces(java.lang.String r19, long r20, java.lang.String r22, java.lang.StringBuilder r23) {
            /*
            // Method dump skipped, instructions count: 233
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.CachedANRReportHandler.findANRTraces(java.lang.String, long, java.lang.String, java.lang.StringBuilder):int");
        }

        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z) {
            ErrorReporter.purgeDirectory(errorReporter.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0), null);
            return true;
        }

        private void addUpdatedProperties(CrashReportData crashReportData, CrashReportData crashReportData2, String str, File file) {
            boolean z;
            String customData;
            String customData2;
            String str2;
            String str3;
            HashMap hashMap = new HashMap();
            int i = -1;
            if (!ACRA.getFlagValue(ACRA.SHOULD_UPLOAD_SYSTEM_ANR_TRACES_GK_CACHED) || (((str2 = (String) crashReportData.get(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES)) != null && !str2.equals("0")) || crashReportData2.get(ReportField.SIGQUIT) != null)) {
                z = false;
            } else {
                C0139Dd.A0B("ACRA", "Looking for system ANR traces");
                StringBuilder sb = new StringBuilder();
                int findANRTraces = findANRTraces(crashReportData.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReportData.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, sb);
                String str4 = null;
                if (findANRTraces > -1) {
                    str4 = sb.toString();
                }
                if (str4 != null) {
                    C0139Dd.A0B("ACRA", "Found and attaching system ANR traces");
                    hashMap.put(ReportField.SIGQUIT, AttachmentUtil.compressToBase64String(str4.getBytes()));
                    str3 = "true";
                } else {
                    str3 = "false";
                }
                hashMap.put(ErrorReportingConstants.ANR_SYSTEM_TRACES_PRESENT, str3);
                i = findANRTraces;
                z = true;
            }
            if (ACRA.getFlagValue(ACRA.SHOULD_LOG_PROCESS_POSITION_IN_ANR_TRACE_FILE)) {
                if (!z) {
                    i = findANRTraces(crashReportData.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReportData.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, null);
                }
                hashMap.put(ErrorReportingConstants.ANR_TRACE_POSITION, String.valueOf(i));
            }
            hashMap.put(ReportField.UPLOADED_BY_PROCESS, str);
            if (crashReportData2.get(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG) == null) {
                hashMap.put(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReporter.getCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG));
            }
            if (crashReportData2.get(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG) == null && (customData2 = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG)) != null) {
                hashMap.put(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, customData2);
            }
            if (crashReportData2.get(ErrorReportingConstants.ANR_SYSTEM_TAG) == null && (customData = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_TAG)) != null) {
                hashMap.put(ErrorReportingConstants.ANR_SYSTEM_TAG, customData);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            crashReportData2.putAll(hashMap, CrashReportData.getWriter(fileOutputStream));
            fileOutputStream.close();
        }
    }

    public enum CrashReportType {
        ACRA_CRASH_REPORT(ErrorReporter.ACRA_DIRNAME, ErrorReporter.DEFAULT_MAX_REPORT_SIZE, null, new AcraReportHandler(), null, ErrorReporter.REPORTFILE_EXTENSION),
        NATIVE_CRASH_REPORT("minidumps", ErrorReporter.NATIVE_MAX_REPORT_SIZE, ReportField.MINIDUMP, null, null, ErrorReporter.DUMPFILE_EXTENSION),
        ANR_REPORT(ErrorReporter.SIGQUIT_DIR, ErrorReporter.SIGQUIT_MAX_REPORT_SIZE, ReportField.SIGQUIT, null, null, ErrorReporter.REPORTFILE_EXTENSION),
        CACHED_ANR_REPORT(ErrorReporter.SIGQUIT_DIR, ErrorReporter.SIGQUIT_MAX_REPORT_SIZE, ReportField.SIGQUIT, new CachedANRReportHandler(), new String[]{ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION}, ".cachedreport");
        
        public final String attachmentField;
        public final long defaultMaxSize;
        public final String directory;
        public final String[] extraFileExtensions;
        public final String[] fileExtensions;
        public final ReportHandler handler;
        public final Object mLock = new Object();
        public Spool mSpool;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Spool.Snapshot getPendingCrashReports(Context context) {
            final String[] strArr = this.fileExtensions;
            return getSpool(context).snapshot(new FifoSpoolComparator(), new FilenameFilter() {
                /* class com.facebook.acra.ErrorReporter.CrashReportType.AnonymousClass1 */

                public boolean accept(File file, String str) {
                    for (String str2 : strArr) {
                        if (str.endsWith(str2)) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        public Spool getSpool(Context context) {
            Spool spool;
            synchronized (this.mLock) {
                spool = this.mSpool;
                if (spool == null) {
                    spool = new Spool(context.getDir(this.directory, 0));
                    this.mSpool = spool;
                }
            }
            return spool;
        }

        /* access modifiers changed from: public */
        CrashReportType(String str, long j, String str2, ReportHandler reportHandler, String[] strArr, String... strArr2) {
            this.directory = str;
            this.defaultMaxSize = j;
            this.attachmentField = str2;
            this.handler = reportHandler;
            this.extraFileExtensions = strArr;
            this.fileExtensions = strArr2;
        }

        public ReportHandler getHandler() {
            return this.handler;
        }
    }

    public interface CrashReportedObserver {
        void onCrashReported(CrashReportData crashReportData);
    }

    public interface ExcludedReportObserver {
        void onExclude(CrashReportData crashReportData);
    }

    public class Holder {
        public static final ErrorReporter ERROR_REPORTER = new ErrorReporter();
    }

    public interface ReportHandler {
        boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z);
    }

    public final class ReportsSenderWorker extends Thread {
        public Throwable exception;
        public FileGenerator mGenerator;
        public final CrashReportData mInMemoryReportToSend;
        public final Spool.FileBeingProduced mReportFileUnderConstruction;
        public final CrashReportType[] mReportTypesToSend;

        private PowerManager.WakeLock acquireWakeLock() {
            if (!new PackageManagerWrapper(ErrorReporter.this.mContext, "ACRA").hasPermission("android.permission.WAKE_LOCK")) {
                return null;
            }
            PowerManager.WakeLock newWakeLock = ((PowerManager) ErrorReporter.this.mContext.getSystemService("power")).newWakeLock(1, "ACRA wakelock");
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire();
            return newWakeLock;
        }

        public void run() {
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

        public void doSend() {
            ErrorReporter.mSendAttempts.getAndIncrement();
            PowerManager.WakeLock acquireWakeLock = acquireWakeLock();
            try {
                CrashReportData crashReportData = this.mInMemoryReportToSend;
                if (crashReportData == null) {
                    ErrorReporter.this.prepareReports(Integer.MAX_VALUE, this.mGenerator, true, this.mReportTypesToSend);
                } else {
                    crashReportData.put(ReportField.UPLOADED_BY_PROCESS, CrashTimeDataCollector.getProcessNameFromAms(ErrorReporter.this.mContext));
                    ErrorReporter.this.sendCrashReport(crashReportData);
                    Spool.FileBeingProduced fileBeingProduced = this.mReportFileUnderConstruction;
                    if (fileBeingProduced != null) {
                        fileBeingProduced.fileName.delete();
                    }
                }
            } finally {
                if (acquireWakeLock != null && acquireWakeLock.isHeld()) {
                    acquireWakeLock.release();
                }
            }
        }

        public Throwable getException() {
            return this.exception;
        }

        public void routeReportToFile(FileGenerator fileGenerator) {
            this.mGenerator = fileGenerator;
        }

        public ReportsSenderWorker(ErrorReporter errorReporter, CrashReportData crashReportData, Spool.FileBeingProduced fileBeingProduced) {
            this(crashReportData, fileBeingProduced, null);
        }

        public ReportsSenderWorker(CrashReportData crashReportData, Spool.FileBeingProduced fileBeingProduced, CrashReportType[] crashReportTypeArr) {
            super("ReportsSenderWorker");
            this.exception = null;
            this.mGenerator = null;
            this.mInMemoryReportToSend = crashReportData;
            this.mReportFileUnderConstruction = fileBeingProduced;
            this.mReportTypesToSend = crashReportTypeArr;
        }

        public ReportsSenderWorker(ErrorReporter errorReporter, CrashReportType... crashReportTypeArr) {
            this(null, null, crashReportTypeArr);
        }
    }

    public interface SecureSettingsResolver {
        String getString(ContentResolver contentResolver, String str);
    }

    private void buildAttachmentWrapperCrashReport(CrashReportData crashReportData, CrashReportType crashReportType, Spool.FileBeingConsumed fileBeingConsumed, Writer writer, boolean z) {
        try {
            crashReportData.put(ReportField.ACRA_REPORT_TYPE, crashReportType.name(), writer);
            AcraReportingConfig acraReportingConfig = this.mConfig;
            Spool.FileBeingConsumed fileBeingConsumed2 = null;
            CrashAttachmentException crashAttachmentException = new CrashAttachmentException();
            if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
                fileBeingConsumed2 = fileBeingConsumed;
            }
            CrashTimeDataCollector.gatherCrashData(this, acraReportingConfig, CRASH_ATTACHMENT_DUMMY_STACKTRACE, crashAttachmentException, crashReportData, writer, fileBeingConsumed2, z, false);
        } catch (Throwable th) {
            put(ReportField.REPORT_LOAD_THROW, AnonymousClass08.A04("retrieve exception: ", th.getMessage()), crashReportData, writer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        if (r14 != null) goto L_0x0078;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildCachedCrashReport(com.facebook.acra.ErrorReporter.CrashReportType r12, java.lang.String r13, java.io.File r14, com.facebook.acra.FileGenerator r15) {
        /*
        // Method dump skipped, instructions count: 137
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.buildCachedCrashReport(com.facebook.acra.ErrorReporter$CrashReportType, java.lang.String, java.io.File, com.facebook.acra.FileGenerator):int");
    }

    private void checkNativeReportsOnApplicationStart() {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        int maxPendingMiniDumpReports = this.mConfig.getMaxPendingMiniDumpReports(roughlyCountPendingReportsOfType);
        if (maxPendingMiniDumpReports < Integer.MAX_VALUE && roughlyCountPendingReportsOfType > maxPendingMiniDumpReports) {
            C0139Dd.A0P(TAG, "Minidump count above threshold %d", Integer.valueOf(roughlyCountPendingReportsOfType));
            removeCrashFiles(CrashReportType.NATIVE_CRASH_REPORT, roughlyCountPendingReportsOfType - maxPendingMiniDumpReports);
        }
        if (roughlyCountPendingReportsOfType > 5) {
            ReportsSenderWorker reportsSenderWorker = new ReportsSenderWorker(this, CrashReportType.NATIVE_CRASH_REPORT);
            Object saveStrictMode = Api9Utils.saveStrictMode();
            Api9Utils.disableStrictMode();
            try {
                reportsSenderWorker.doSend();
            } catch (Throwable th) {
                if (saveStrictMode != null) {
                    Api9Utils.restoreStrictMode(saveStrictMode);
                }
                throw th;
            }
            if (saveStrictMode != null) {
                Api9Utils.restoreStrictMode(saveStrictMode);
            }
        }
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean delete = file.delete();
        if (!delete && !file.exists()) {
            delete = true;
        }
        C0139Dd.A0F("ACRA", "Deleting error report: %s", file.getName());
        if (!delete) {
            C0139Dd.A0P("ACRA", "Could not delete error report: %s", file.getName());
        }
        return delete;
    }

    private void discardOverlappingReports(CrashReportType... crashReportTypeArr) {
        for (CrashReportType crashReportType : crashReportTypeArr) {
            if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT || crashReportType == CrashReportType.ACRA_CRASH_REPORT) {
                if (roughlyCountPendingReportsOfType(crashReportType) > 0) {
                    purgeDirectory(this.mContext.getDir(SIGQUIT_DIR, 0), null);
                    return;
                }
            }
        }
    }

    private int keepNLatestDumpFiles(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return 0;
        }
        Arrays.sort(listFiles, new Comparator() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass3 */

            public int compare(File file, File file2) {
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

    private void maybeRemoveAnrReports() {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.CACHED_ANR_REPORT);
        int maxPendingAnrReports = this.mConfig.getMaxPendingAnrReports(roughlyCountPendingReportsOfType);
        if (maxPendingAnrReports < Integer.MAX_VALUE && roughlyCountPendingReportsOfType > maxPendingAnrReports) {
            C0139Dd.A0P(TAG, "Anr count %d above threshold %d", Integer.valueOf(roughlyCountPendingReportsOfType), Integer.valueOf(maxPendingAnrReports));
            removeCrashFiles(CrashReportType.CACHED_ANR_REPORT, roughlyCountPendingReportsOfType - maxPendingAnrReports);
        }
    }

    private int maybeSendCrashReport(CrashReportType crashReportType, CrashReportData crashReportData, Spool.FileBeingConsumed fileBeingConsumed, FileGenerator fileGenerator, boolean z) {
        String str;
        boolean z2;
        String str2;
        Writer writer = null;
        if (fileGenerator != null) {
            try {
                File generate = fileGenerator.generate();
                str = generate.getCanonicalPath();
                if (crashReportType == CrashReportType.ANR_REPORT && z) {
                    synchronized (this.mAnrFilesInProgress) {
                        this.mAnrFilesInProgress.add(str);
                    }
                }
                writer = CrashReportData.getWriter(new FileOutputStream(generate));
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        writer.close();
                        throw th;
                    } catch (IOException e) {
                        C0139Dd.A0S("ACRA", e, "IO Exception");
                        reportSoftError(e, "ANRValidationError<IOException::Non-cached>", e.toString(), this);
                        throw th;
                    }
                }
                throw th;
            }
        } else {
            str = fileBeingConsumed.fileName.getName();
        }
        CrashReportData crashReportData2 = new CrashReportData();
        String str3 = crashReportType.attachmentField;
        if (str3 != null) {
            crashReportData2.put(crashReportType.attachmentField, (String) crashReportData.get(str3), writer);
        }
        if (crashReportType != CrashReportType.ANR_REPORT || (str2 = (String) crashReportData.get(ReportField.PROCESS_NAME)) == null) {
            z2 = true;
        } else {
            crashReportData2.put(ReportField.PROCESS_NAME, str2, writer);
            z2 = false;
        }
        buildAttachmentWrapperCrashReport(crashReportData2, crashReportType, fileBeingConsumed, writer, z2);
        if (crashReportType == CrashReportType.ANR_REPORT && !z) {
            crashReportData2.put(ErrorReportingConstants.ANR_PARTIAL_REPORT, "true", writer);
            crashReportData2.put(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL, writer);
            crashReportData2.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, "1", writer);
        }
        crashReportData2.put(ReportField.REPORT_ID, str.substring(0, str.lastIndexOf(46)), writer);
        crashReportData2.merge((Map) crashReportData, false, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.APP_VERSION_CODE, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.APP_VERSION_NAME, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.SESSION_ID, writer);
        crashReportData2.put(ReportField.EXCEPTION_CAUSE, CRASH_ATTACHMENT_DUMMY_STACKTRACE, writer);
        if (writer == null) {
            sendCrashReport(crashReportData2);
            return 1;
        }
        try {
            writer.close();
            return 1;
        } catch (IOException e2) {
            C0139Dd.A0S("ACRA", e2, "IO Exception");
            reportSoftError(e2, "ANRValidationError<IOException::Non-cached>", e2.toString(), this);
            return 1;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void startUploadIfReady() {
        if (this.mConfig.shouldUseUploadService() && this.mInitializationComplete && this.mBatchUploader != null && this.mFinishedCheckingReports && this.mPendingReportWriters <= 0 && !this.mConfig.shouldOnlyWriteReport() && !this.mStartedBatchUploader) {
            final File[] listFiles = this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0).listFiles();
            this.mStartedBatchUploader = true;
            new Thread(new Runnable() {
                /* class com.facebook.acra.ErrorReporter.AnonymousClass4 */

                public void run() {
                    ErrorReporter.this.mBatchUploader.uploadReports(listFiles);
                }
            }).start();
        }
    }

    private void uncaughtExceptionImpl(Thread thread, Throwable th, boolean z) {
        C0153Dv dv;
        this.mOomReservation = null;
        discardOverlappingReports(CrashReportType.ACRA_CRASH_REPORT);
        try {
            Api9Utils.disableStrictMode();
        } catch (Throwable th2) {
            tryLogInternalError(th2);
        }
        int i = 3;
        try {
            C0139Dd.A0O("ACRA", "ACRA caught a %s exception for %s version %s. Building report.", th.getClass().getSimpleName(), this.mContext.getPackageName(), this.mAppVersionCode);
        } catch (Throwable th3) {
            tryLogInternalError(th3);
        }
        TreeMap treeMap = new TreeMap();
        treeMap.put(ReportField.THROWING_THREAD_NAME, String.valueOf(thread.getName()));
        Throwable translateException = translateException(th, treeMap);
        if (translateException != null) {
            if (z) {
                i = 4;
            }
            if (!this.mConfig.shouldImmediatelyUpload()) {
                i = 4;
            }
            if (getMostSignificantCause(translateException) instanceof OutOfMemoryError) {
                i &= -2;
            }
            try {
                synchronized (C0153Dv.class) {
                    dv = C0153Dv.A00;
                    if (dv == null) {
                        dv = new C0153Dv();
                        C0153Dv.A00 = dv;
                    }
                }
                synchronized (dv) {
                }
                handleExceptionInternal(translateException, new CrashReportData(treeMap), null, i);
            } catch (Throwable th4) {
                if (!z) {
                    C0139Dd.A0S("ACRA", th4, "error during error reporting: will attempt to report error");
                    uncaughtExceptionImpl(thread, th4, true);
                    return;
                }
                throw th4;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r13 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r7.put(com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT.attachmentField, com.facebook.acra.util.AttachmentUtil.compressToBase64String(r13.getBytes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        r7.store(r3, (java.lang.String) null);
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        r4 = new java.io.File(r14);
        r10 = r4.length();
        r2 = new java.io.FileInputStream(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r8 = new java.io.BufferedInputStream(r2);
        r5 = readSigquitFileHeader(r8);
        r1 = r5.commandLine;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        if (r1 == null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0065, code lost:
        r7.put(com.facebook.acra.constants.ReportField.PROCESS_NAME, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        if (android.text.TextUtils.isEmpty(r5.versionCode) != false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0072, code lost:
        r7.put(com.facebook.acra.constants.ReportField.APP_VERSION_CODE, r5.versionCode);
        r7.put(com.facebook.acra.constants.ReportField.APP_VERSION_NAME, r5.versionName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0080, code lost:
        slurpAttachment(r7, r8, com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
        r2.close();
        deleteFile(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r3 = new java.io.FileOutputStream(new java.io.File(X.AnonymousClass08.A04(r1, com.facebook.acra.ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION)), true);
        r7 = new com.facebook.acra.CrashReportData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void amendANRReportWithSigquitData(java.lang.String r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 148
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.amendANRReportWithSigquitData(java.lang.String, java.lang.String):void");
    }

    public void checkNativeReports() {
        CrashReportType crashReportType = CrashReportType.NATIVE_CRASH_REPORT;
        if (roughlyCountPendingReportsOfType(crashReportType) > 0) {
            checkReportsOfType(crashReportType);
        }
    }

    public HashSet getNewLibs(File file, HashSet hashSet) {
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
                        C0139Dd.A0S("ACRA", e, "GLC getNewLibs IO Exception");
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
            C0139Dd.A0S("ACRA", e, "GLC getNewLibs IO Exception");
            safeClose(closeable);
            sSystemLibFileLock.readLock().unlock();
            return hashSet;
        }
        sSystemLibFileLock.readLock().unlock();
        return hashSet;
    }

    public String getSigquitTracesExtension() {
        return REPORTFILE_EXTENSION;
    }

    public void handleExceptionDelayed(Throwable th, CrashReportData crashReportData) {
        handleExceptionInternal(th, crashReportData, null, 0);
    }

    @Override // X.AbstractC0123Bt
    public void handleUncaughtException(Thread thread, Throwable th, Bp bp) {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.ACRA_CRASH_REPORT);
        int maxPendingJavaCrashReports = this.mConfig.getMaxPendingJavaCrashReports(roughlyCountPendingReportsOfType);
        if (maxPendingJavaCrashReports < Integer.MAX_VALUE) {
            if (roughlyCountPendingReportsOfType >= maxPendingJavaCrashReports) {
                C0139Dd.A0V(TAG, th, "Maximum pending Java crash threshold reached, Not storing count=%d", Integer.valueOf(roughlyCountPendingReportsOfType));
                return;
            }
            C0139Dd.A0G(TAG, "Current pending Java crash report count = %d", Integer.valueOf(roughlyCountPendingReportsOfType));
        }
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            try {
                uncaughtExceptionImpl(thread, th, false);
            } catch (Throwable th2) {
                tryLogInternalError(th2);
            }
        }
    }

    public synchronized void setBatchUploader(BatchUploader batchUploader) {
        if (this.mConfig != null && this.mConfig.shouldUseUploadService()) {
            this.mBatchUploader = batchUploader;
            startUploadIfReady();
        }
    }

    public class DefaultSecureSettingsResolver implements SecureSettingsResolver {
        @Override // com.facebook.acra.ErrorReporter.SecureSettingsResolver
        public String getString(ContentResolver contentResolver, String str) {
            return Settings.Secure.getString(contentResolver, str);
        }
    }

    public static /* synthetic */ int access$908(ErrorReporter errorReporter) {
        int i = errorReporter.mPendingReportWriters;
        errorReporter.mPendingReportWriters = i + 1;
        return i;
    }

    public static /* synthetic */ int access$910(ErrorReporter errorReporter) {
        int i = errorReporter.mPendingReportWriters;
        errorReporter.mPendingReportWriters = i - 1;
        return i;
    }

    private void addCriticalData() {
        String userId = CriticalAppData.getUserId(this.mContext);
        String clientUserId = CriticalAppData.getClientUserId(this.mContext);
        String deviceId = CriticalAppData.getDeviceId(this.mContext);
        if (userId.length() > 0) {
            setUserId(userId);
        }
        if (clientUserId.length() > 0) {
            setClientUserId(clientUserId);
        }
        if (deviceId.length() > 0) {
            putCustomDataInternal(ErrorReportingConstants.DEVICE_ID_KEY, deviceId);
        }
        for (Map.Entry entry : CriticalAppData.getAdditionalParamValues(this.mContext).entrySet()) {
            String str = (String) entry.getValue();
            if (!TextUtils.isEmpty(str)) {
                putCustomDataInternal((String) entry.getKey(), str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void attachIabInfo(com.facebook.acra.CrashReportData r5) {
        /*
            r4 = this;
            android.content.Context r0 = r4.mContext
            java.io.File r1 = r0.getFilesDir()
            java.lang.String r0 = "iab_open_times"
            java.io.File r3 = new java.io.File
            r3.<init>(r1, r0)
            java.lang.String r2 = readFile(r3)
            java.lang.String r0 = "NO_FILE"
            boolean r1 = r0.equals(r2)
            java.lang.String r0 = "IAB_OPEN_TIMES"
            if (r1 == 0) goto L_0x002a
            java.lang.String r2 = "0"
        L_0x001d:
            r5.put(r0, r2)
        L_0x0020:
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x0029
            r3.delete()
        L_0x0029:
            return
        L_0x002a:
            if (r2 == 0) goto L_0x0020
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.attachIabInfo(com.facebook.acra.CrashReportData):void");
    }

    private void attachLastActivityInfo(CrashReportData crashReportData) {
        File file = new File(this.mContext.getFilesDir(), FILE_LAST_ACTIVITY);
        if (!file.exists()) {
            crashReportData.put(ReportField.LAST_ACTIVITY_LOGGED, NO_FILE);
            return;
        }
        FileReader fileReader = new FileReader(file);
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    crashReportData.put(ReportField.LAST_ACTIVITY_LOGGED, readLine);
                    crashReportData.put(ReportField.LAST_ACTIVITY_LOGGED_TIME, Long.toString(file.lastModified()));
                }
                file.delete();
                bufferedReader.close();
                fileReader.close();
                return;
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
        }
        throw th;
        throw th;
    }

    private void cancelBlockingNotification() {
        ((NotificationManager) this.mContext.getSystemService("notification")).cancel(2);
    }

    private void closeInputStreamFields(CrashReportData crashReportData) {
        InputStream inputStream;
        for (Map.Entry entry : crashReportData.mInputStreamFields.entrySet()) {
            InputStreamField inputStreamField = (InputStreamField) entry.getValue();
            if (!(inputStreamField == null || (inputStream = inputStreamField.mInputStream) == null)) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
        }
    }

    public static boolean containsKeyInCustomData(String str) {
        return ProxyCustomDataStore.Holder.CUSTOM_DATA.containsKey(str);
    }

    private File createBackUpDirectory(CrashReportType crashReportType) {
        File file;
        NullPointerException e;
        try {
            file = new File(this.mContext.getDir(crashReportType.directory, 0).getParent(), REPORTED_CRASH_DIR);
            try {
                if (!file.exists()) {
                    file.mkdir();
                    return file;
                }
            } catch (NullPointerException e2) {
                e = e2;
                C0139Dd.A0V("ACRA", e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
                return file;
            }
        } catch (NullPointerException e3) {
            e = e3;
            file = null;
            C0139Dd.A0V("ACRA", e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
            return file;
        }
        return file;
    }

    private CrashReportData createCrashReportFromStackTrace(String str, CrashReportType crashReportType) {
        CrashReportData crashReportData = new CrashReportData();
        crashReportData.put(ReportField.TIME_OF_CRASH, Long.toString(System.currentTimeMillis()));
        try {
            crashReportData.put(crashReportType.attachmentField, AttachmentUtil.compressToBase64String(str.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            crashReportData.put(ReportField.REPORT_LOAD_THROW, AnonymousClass08.A04("throwable: ", e.getMessage()));
            C0139Dd.A0S("ACRA", e, "Could not load crash report. File will be deleted.");
        }
        backfillCrashReportData(crashReportData);
        return crashReportData;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0017, code lost:
        if (r1.equals("false") == false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void foregroundNativeCrashDetect(com.facebook.acra.Spool.FileBeingConsumed r6) {
        /*
            r5 = this;
            java.io.RandomAccessFile r0 = r6.file     // Catch:{ Exception -> 0x0054 }
            com.facebook.acra.util.minidump.MinidumpReader r2 = new com.facebook.acra.util.minidump.MinidumpReader     // Catch:{ Exception -> 0x0054 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r0 = "APP_STARTED_IN_BACKGROUND"
            java.lang.String r1 = r2.getCustomData(r0)     // Catch:{ Exception -> 0x0054 }
            r4 = 0
            if (r1 == 0) goto L_0x0019
            java.lang.String r0 = "false"
            boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x0054 }
            r3 = 1
            if (r0 != 0) goto L_0x001a
        L_0x0019:
            r3 = 0
        L_0x001a:
            r0 = -87110452(0xfffffffffacecccc, float:-5.3688346E35)
            java.lang.String r2 = r2.getString(r0)     // Catch:{ Exception -> 0x0054 }
            r1 = -1
            if (r2 == 0) goto L_0x002c
            java.lang.String r0 = "Resumed"
            int r0 = r2.indexOf(r0)     // Catch:{ Exception -> 0x0054 }
            if (r0 != r1) goto L_0x0038
        L_0x002c:
            if (r3 == 0) goto L_0x005d
            if (r2 == 0) goto L_0x005d
            java.lang.String r0 = "\"activities\":[]"
            int r0 = r2.indexOf(r0)     // Catch:{ Exception -> 0x0054 }
            if (r0 == r1) goto L_0x005d
        L_0x0038:
            android.content.Context r1 = r5.mContext     // Catch:{ Exception -> 0x0054 }
            java.lang.String r0 = "FacebookApplication"
            android.content.SharedPreferences r0 = r1.getSharedPreferences(r0, r4)     // Catch:{ Exception -> 0x0054 }
            android.content.SharedPreferences$Editor r3 = r0.edit()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r2 = "crash_foreground_timestamp"
            java.io.File r0 = r6.fileName     // Catch:{ Exception -> 0x0054 }
            long r0 = r0.lastModified()     // Catch:{ Exception -> 0x0054 }
            android.content.SharedPreferences$Editor r0 = r3.putLong(r2, r0)     // Catch:{ Exception -> 0x0054 }
            r0.commit()     // Catch:{ Exception -> 0x0054 }
            return
        L_0x0054:
            r3 = move-exception
            java.lang.String r2 = "ACRA"
            r1 = 0
            java.lang.String r0 = "Error writing into the SharedPreferences"
            r5.writeToLogBridge(r2, r0, r3, r1)
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.foregroundNativeCrashDetect(com.facebook.acra.Spool$FileBeingConsumed):void");
    }

    public static File getCrashDumpSysLibPath(Context context) {
        return context.getFileStreamPath(CRASH_DUMP_SYS_LIBS_FILE);
    }

    public static String getCustomData(String str) {
        return ProxyCustomDataStore.Holder.CUSTOM_DATA.getCustomData(str);
    }

    public static String getLogcatFileName(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType) {
        if (crashReportType != CrashReportType.NATIVE_CRASH_REPORT) {
            return null;
        }
        try {
            return new MinidumpReader(fileBeingConsumed.file).getCustomData(ACRA.LOGCAT_FILE_KEY);
        } catch (IOException e) {
            C0139Dd.A0V("ACRA", e, "Failed to find logcat file %s", fileBeingConsumed.fileName);
            return null;
        }
    }

    public static Throwable getMostSignificantCause(Throwable th) {
        if (!(th instanceof NonCrashException)) {
            while (th.getCause() != null) {
                th = th.getCause();
            }
        }
        return th;
    }

    public static int getSendAttempts() {
        return mSendAttempts.get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        if (com.facebook.acra.ErrorReporter.CrashReportType.ACRA_CRASH_REPORT.getPendingCrashReports(r24.mContext).mDescriptors.length <= 5) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0085 A[SYNTHETIC, Splitter:B:33:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00db A[SYNTHETIC, Splitter:B:60:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0103 A[Catch:{ all -> 0x017c, all -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0114 A[Catch:{ all -> 0x017c, all -> 0x018c }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x011d A[SYNTHETIC, Splitter:B:73:0x011d] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0123 A[SYNTHETIC, Splitter:B:77:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0199 A[SYNTHETIC, Splitter:B:97:0x0199] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.ErrorReporter.ReportsSenderWorker handleExceptionInternal(java.lang.Throwable r25, com.facebook.acra.CrashReportData r26, java.lang.String r27, int r28) {
        /*
        // Method dump skipped, instructions count: 560
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.handleExceptionInternal(java.lang.Throwable, com.facebook.acra.CrashReportData, java.lang.String, int):com.facebook.acra.ErrorReporter$ReportsSenderWorker");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private CrashReportData loadAcraCrashReport(Spool.FileBeingConsumed fileBeingConsumed) {
        return loadCrashReport(fileBeingConsumed, CrashReportType.ACRA_CRASH_REPORT, this.mMaxReportSize, true);
    }

    private boolean mustEmbedAttachments(CrashReportType crashReportType) {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            Iterator it = this.mReportSenders.iterator();
            while (it.hasNext()) {
                if (!((ReportSender) it.next()).supportsMultipart()) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean objectsEqual(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    private void preallocateReportFile(File file, long j) {
        Spool.FileBeingProduced produceWithDonorFile = CrashReportType.ACRA_CRASH_REPORT.getSpool(this.mContext).produceWithDonorFile(genCrashReportFileName(ErrorReporter.class, UUID.randomUUID(), REPORTFILE_EXTENSION), null);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(produceWithDonorFile.fileName);
            try {
                byte[] bArr = new byte[32768];
                for (long j2 = 0; j2 < j; j2 += (long) 32768) {
                    fileOutputStream.write(bArr);
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                renameOrThrow(produceWithDonorFile.fileName, file);
                try {
                    produceWithDonorFile.close();
                    return;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } finally {
            produceWithDonorFile.fileName.delete();
        }
    }

    private void processCrashAttachmentAfterSend(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, File file, String str) {
        if (crashReportType != CrashReportType.NATIVE_CRASH_REPORT || file == null || !file.exists()) {
            C0139Dd.A0F("ACRA", "No need to backup. Deleting minidump %s", fileBeingConsumed.fileName.getCanonicalPath());
            deleteFile(fileBeingConsumed.fileName);
        } else {
            File file2 = new File(file, fileBeingConsumed.fileName.getName());
            file2.delete();
            fileBeingConsumed.fileName.renameTo(file2);
            C0139Dd.A0H("ACRA", "Backup minidump file from %s to %s", fileBeingConsumed.fileName.getCanonicalPath(), file2.getCanonicalPath());
        }
        if (str != null) {
            deleteFile(new File(str));
        }
    }

    private void processCrashAttachmentBeforeSend(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, CrashReportData crashReportData, boolean z) {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            if (!z) {
                NativeCrashDumpReporterUtil.processHeapDump(this.mContext, crashReportData, fileBeingConsumed, this.mConfig);
            }
            foregroundNativeCrashDetect(fileBeingConsumed);
            MinidumpReader minidumpReader = new MinidumpReader(fileBeingConsumed.file);
            String customData = minidumpReader.getCustomData(ACRA.SESSION_ID_KEY);
            if (!TextUtils.isEmpty(customData)) {
                crashReportData.put(ReportField.SESSION_ID, customData);
            }
            String customData2 = minidumpReader.getCustomData("is_first_run_after_upgrade");
            if (!TextUtils.isEmpty(customData2)) {
                crashReportData.put("is_first_run_after_upgrade", customData2);
            }
            String customData3 = minidumpReader.getCustomData(ReportField.SHOULD_REPORT_DISK_INFO_NATIVE);
            if (!TextUtils.isEmpty(customData3)) {
                crashReportData.put(ReportField.SHOULD_REPORT_DISK_INFO_NATIVE, customData3);
            }
            String customData4 = minidumpReader.getCustomData(ReportField.NO_DEVICE_MEMORY);
            if (!TextUtils.isEmpty(customData4)) {
                crashReportData.put(ReportField.NO_DEVICE_MEMORY, customData4);
            }
            Integer num = minidumpReader.getInt(MinidumpReader.MD_FB_APP_VERSION_CODE);
            if (num != null) {
                crashReportData.put(ReportField.APP_VERSION_CODE, num.toString());
            }
            String string = minidumpReader.getString(MinidumpReader.MD_FB_APP_VERSION_NAME);
            if (!TextUtils.isEmpty(string)) {
                crashReportData.put(ReportField.APP_VERSION_NAME, string);
            }
            String customData5 = minidumpReader.getCustomData(ACRA.BREAKPAD_LIB_NAME);
            if (!TextUtils.isEmpty(customData5)) {
                crashReportData.put(ReportField.BREAKPAD_LIB_NAME, customData5);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int processCrashAttachmentsLocked(int r18, com.facebook.acra.ErrorReporter.CrashReportType r19, com.facebook.acra.FileGenerator r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.processCrashAttachmentsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, com.facebook.acra.FileGenerator, boolean):int");
    }

    public static void purgeDirectory(File file, final String str) {
        AnonymousClass1 r0;
        File[] listFiles;
        if (str != null) {
            r0 = new FileFilter() {
                /* class com.facebook.acra.ErrorReporter.AnonymousClass1 */

                public boolean accept(File file) {
                    return file.getName().endsWith(str);
                }
            };
        } else {
            r0 = null;
        }
        if (!(file == null || (listFiles = file.listFiles(r0)) == null)) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    purgeDirectory(file2, str);
                }
                deleteFile(file2);
            }
        }
    }

    public static void put(String str, String str2, CrashReportData crashReportData, Writer writer) {
        if (crashReportData.generatingIoError != null) {
            writer = null;
        }
        try {
            crashReportData.put(str, str2, writer);
        } catch (IOException e) {
            crashReportData.generatingIoError = e;
        }
    }

    public static void putCustomDataInternal(String str, String str2) {
        ProxyCustomDataStore.Holder.CUSTOM_DATA.setCustomData(str, str2, new Object[0]);
    }

    public static String readVersionLine(BufferedInputStream bufferedInputStream) {
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

    private void removeCrashFiles(final CrashReportType crashReportType, int i) {
        if (i > 0) {
            try {
                C0139Dd.A0G(TAG, "removeCrashFiles count=%d", Integer.valueOf(i));
                Spool.Snapshot snapshot = crashReportType.getSpool(this.mContext).snapshot(new FifoSpoolComparator(), new FilenameFilter() {
                    /* class com.facebook.acra.ErrorReporter.AnonymousClass5 */

                    public boolean accept(File file, String str) {
                        for (String str2 : crashReportType.fileExtensions) {
                            if (str.endsWith(str2)) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
                if (snapshot == null) {
                    C0139Dd.A0D(TAG, "removeCrashFiles no snapshot");
                    return;
                }
                while (snapshot.hasNext() && i > 0) {
                    Spool.FileBeingConsumed next = snapshot.next();
                    if (next.fileName != null) {
                        String[] strArr = crashReportType.extraFileExtensions;
                        if (strArr != null) {
                            for (String str : strArr) {
                                new File(AnonymousClass08.A04(next.fileName.getCanonicalPath(), str)).delete();
                            }
                        }
                        if (!next.fileName.delete()) {
                            C0139Dd.A0P(TAG, "removeCrashFiles Crash file not deleted %s", next.fileName.getAbsolutePath());
                        } else {
                            i--;
                        }
                    }
                }
            } catch (Throwable th) {
                C0139Dd.A0S(TAG, th, "removeCrashFiles");
            }
        }
    }

    public static void removeCustomDataInternal(String str) {
        ProxyCustomDataStore.Holder.CUSTOM_DATA.removeCustomData(str);
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendCrashReport(CrashReportData crashReportData) {
        ArrayList arrayList;
        if (this.mConfig.isZeroCrashlogBlocked()) {
            return;
        }
        if (this.mConfig.shouldOnlyWriteReport()) {
            C0139Dd.A0B("ACRA", "Writing report to file");
            writeJsonReport(crashReportData);
            return;
        }
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
                    if (z) {
                        C0139Dd.A0W("ACRA", e, "ReportSender of class %s failed but other senders completed their task. ACRA will not send this report again.", reportSender.getClass().getName());
                    } else if (!(reportSender instanceof BaseHttpPostSender) || !this.mConfig.shouldUseUploadService()) {
                        throw e;
                    } else if (!writeJsonReport(crashReportData)) {
                        throw e;
                    }
                }
            }
            return;
        }
        throw new ReportSenderException("no configured report senders", null);
    }

    private boolean shouldSkipReport(CrashReportType crashReportType) {
        return new File(this.mContext.getDir(crashReportType.directory, 0), ".noupload").exists();
    }

    private void showBlockingNotification(StartupBlockingConfig startupBlockingConfig) {
        try {
            Notification.Builder smallIcon = new Notification.Builder(this.mContext).setContentTitle(this.mContext.getString(startupBlockingConfig.notificationTitle)).setContentText(this.mContext.getString(startupBlockingConfig.notificationText)).setSmallIcon(17301543);
            Api16Utils.applyBigTextStyle(smallIcon, this.mContext.getString(startupBlockingConfig.notificationText));
            ((NotificationManager) this.mContext.getSystemService("notification")).notify(2, smallIcon.getNotification());
        } catch (Throwable th) {
            Log.d("ACRA", "error showing notification", th);
        }
    }

    private void slurpAttachment(CrashReportData crashReportData, InputStream inputStream, CrashReportType crashReportType, long j) {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                attachLastActivityInfo(crashReportData);
            } catch (IOException e) {
                C0139Dd.A0T(TAG, e, "error attaching activity information");
            }
            try {
                attachIabInfo(crashReportData);
            } catch (IOException e2) {
                C0139Dd.A0T(TAG, e2, "error attaching IAB information");
            }
        }
        crashReportData.put(crashReportType.attachmentField, AttachmentUtil.loadAttachment(inputStream, (int) j));
        crashReportData.put(ReportField.ATTACHMENT_ORIGINAL_SIZE, String.valueOf(j));
    }

    public static String throwableToString(Throwable th) {
        if (th == null) {
            th = new Exception("Report requested by developer");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        if (th instanceof StackOverflowError) {
            trimStackBuffer(stringWriter.getBuffer(), MAX_STACK_LENGTH_FOR_OVERFLOW);
        }
        return stringWriter.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r10 == r10) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r6 = r6 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Throwable translateException(java.lang.Throwable r10, java.util.Map r11) {
        /*
            r9 = this;
            java.util.concurrent.atomic.AtomicReference r0 = r9.mExceptionTranslationHook
            java.lang.Object r8 = r0.get()
            com.facebook.acra.ExceptionTranslationHook r8 = (com.facebook.acra.ExceptionTranslationHook) r8
            r7 = 0
            r6 = 0
        L_0x000a:
            r5 = r8
        L_0x000b:
            r4 = 1
            if (r5 == 0) goto L_0x0027
            if (r10 == 0) goto L_0x002d
            java.lang.String r1 = "translate"
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0018 }
            r0.<init>(r1)     // Catch:{ all -> 0x0018 }
            throw r0     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r3 = move-exception
            java.lang.String r2 = "ACRA"
            java.lang.Object[] r1 = new java.lang.Object[r4]
            r1[r7] = r5
            java.lang.String r0 = "ignoring error in exception translation hook %s"
            X.C0139Dd.A0W(r2, r3, r0, r1)
            com.facebook.acra.ExceptionTranslationHook r5 = r5.next
            goto L_0x000b
        L_0x0027:
            if (r10 == r10) goto L_0x002d
            int r6 = r6 + r4
            r0 = 4
            if (r6 < r0) goto L_0x000a
        L_0x002d:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.translateException(java.lang.Throwable, java.util.Map):java.lang.Throwable");
    }

    private boolean writeJsonReport(CrashReportData crashReportData) {
        String str;
        long j;
        SecureRandom secureRandom = new SecureRandom();
        C0139Dd.A09("ACRA", "Writing report");
        try {
            long nextLong = secureRandom.nextLong() ^ this.mAppStartTickTimeMs;
            long nextLong2 = secureRandom.nextLong();
            if (this.mUserId == null) {
                j = 0;
            } else {
                j = Long.parseLong(this.mUserId);
            }
            str = new UUID(nextLong, nextLong2 ^ j).toString();
        } catch (NumberFormatException unused) {
            str = UUID.randomUUID().toString();
        }
        File file = new File(this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0), AnonymousClass08.A04(str, ".gz"));
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : crashReportData.mInputStreamFields.entrySet()) {
            if (!((String) entry.getKey()).equals(ReportField.MINIDUMP)) {
                C0139Dd.A0F("ACRA", "Ignoring field %s", entry.getKey());
            } else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!JsonReportWriter.writeGzipJsonReport(crashReportData, hashMap, file)) {
            return false;
        }
        C0139Dd.A0F(TAG, "Wrote file for uploader %s", file.getName());
        return true;
    }

    private void writeToLogBridge(String str, String str2, Throwable th, String str3) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[0];
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (StackTraceElement stackTraceElement2 : stackTrace) {
            if (stackTraceElement2.getClassName().equals(stackTraceElement.getClassName()) && stackTraceElement2.getMethodName().equals(stackTraceElement.getMethodName())) {
                C0139Dd.A0S(TAG, th, "Unable to log over log bridge due to exception.");
                return;
            }
        }
        LogBridge logBridge = getLogBridge();
        if (logBridge != null) {
            if (str3 != null) {
                str2 = AnonymousClass08.A05(str2, "\n", str3);
                th = null;
            }
            logBridge.log(str, str2, th);
        } else if (str3 != null) {
            C0139Dd.A0O(str, "%s\n%s", str2, str3);
        } else {
            C0139Dd.A0V(str, th, "%s", str2);
        }
    }

    public void addExceptionTranslationHook(ExceptionTranslationHook exceptionTranslationHook) {
        exceptionTranslationHook.next = (ExceptionTranslationHook) this.mExceptionTranslationHook.getAndSet(exceptionTranslationHook);
    }

    public void addReportSender(ReportSender reportSender) {
        synchronized (this.mReportSenders) {
            this.mReportSenders.add(reportSender);
        }
    }

    public boolean addToAnrInProgressUpdateFile(Map map) {
        synchronized (this.mAnrFilesInProgress) {
            if (this.mAnrFilesInProgress.isEmpty()) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(AnonymousClass08.A04((String) this.mAnrFilesInProgress.iterator().next(), ANR_EXTRA_PROPERTIES_EXTENSION)), true);
            new CrashReportData(map).store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return true;
        }
    }

    public void backfillCrashReportData(CrashReportData crashReportData) {
        String str = (String) crashReportData.get(ReportField.REPORT_ID);
        if (str == null || str.length() == 0) {
            for (Map.Entry entry : this.mConstantFields.entrySet()) {
                if (crashReportData.get(entry.getKey()) == null) {
                    crashReportData.put(entry.getKey(), entry.getValue());
                }
            }
        }
        String userId = getUserId();
        String str2 = (String) crashReportData.get(ReportField.UID);
        if (!TextUtils.isEmpty(userId) && TextUtils.isEmpty(str2)) {
            crashReportData.put(ReportField.UID, userId);
        }
    }

    public ReportsSenderWorker checkReportsOfType(CrashReportType... crashReportTypeArr) {
        ReportsSenderWorker reportsSenderWorker = new ReportsSenderWorker(this, crashReportTypeArr);
        reportsSenderWorker.start();
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(crashReportTypeArr);
        StartupBlockingConfig startupBlockingConfig = this.mConfig.getStartupBlockingConfig();
        if (startupBlockingConfig != null && roughlyCountPendingReportsOfType > startupBlockingConfig.minNumQueuedReportsToBlockStartup) {
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("blocking for ");
                sb.append(startupBlockingConfig.maxTimeSpentBlockedOnUploadMs);
                sb.append("s to upload errors");
                Log.d("ACRA", sb.toString());
                if (startupBlockingConfig.notifyWhileBlockingStartup) {
                    showBlockingNotification(startupBlockingConfig);
                }
                reportsSenderWorker.join(startupBlockingConfig.maxTimeSpentBlockedOnUploadMs);
                if (startupBlockingConfig.notifyWhileBlockingStartup) {
                    cancelBlockingNotification();
                }
                Log.d("ACRA", "done blocking");
            } catch (InterruptedException unused) {
                Log.e("ACRA", "interrupted while waiting for error reports to upload");
            } catch (Throwable th) {
                StartTimeBlockedRecorder.sDurationStartupBlocked = SystemClock.uptimeMillis() - uptimeMillis;
                StartTimeBlockedRecorder.sTotalCrashesUploaded = roughlyCountPendingReportsOfType;
                throw th;
            }
            StartTimeBlockedRecorder.sDurationStartupBlocked = SystemClock.uptimeMillis() - uptimeMillis;
            StartTimeBlockedRecorder.sTotalCrashesUploaded = roughlyCountPendingReportsOfType;
        }
        return reportsSenderWorker;
    }

    public void deletePartialANRReports() {
        synchronized (ANR_REPORTING_LOCK) {
            purgeDirectory(this.mContext.getDir(SIGQUIT_DIR, 0), REPORTFILE_EXTENSION);
        }
    }

    public String getAppStartDateFormat3339() {
        String format3339;
        synchronized (this.mAppStartDate) {
            format3339 = this.mAppStartDate.format3339(false);
        }
        return format3339;
    }

    public Map getCustomFieldsSnapshot() {
        return ProxyCustomDataStore.Holder.CUSTOM_DATA.getSnapshot();
    }

    public Map getLazyCustomFieldsSnapshot() {
        TreeMap treeMap;
        synchronized (this.mInstanceLazyCustomParameters) {
            treeMap = new TreeMap(this.mInstanceLazyCustomParameters);
        }
        return treeMap;
    }

    public String getLogcatOutputIfPidFound(boolean z, Integer num) {
        String collectLogCat = LogCatCollector.collectLogCat(this.mContext, this.mConfig, null, null, false, z, this.mConfig.allowCollectionOfMaxNumberOfLinesInLogcat());
        if (collectLogCat == null || (num != null && !Pattern.compile(AnonymousClass08.A05("^\\d+-\\d+\\s+\\d+:\\d+:\\d+\\.\\d+\\s+", num.toString(), "\\s+\\d+\\s+[A-Z]\\s+(.*?)$"), 8).matcher(collectLogCat).find())) {
            return null;
        }
        return collectLogCat;
    }

    public String getSigquitTracesPath() {
        return this.mContext.getDir(SIGQUIT_DIR, 0).getPath();
    }

    public void initFallible() {
        int oomReservationOverride = this.mConfig.getOomReservationOverride();
        if (oomReservationOverride <= 0) {
            oomReservationOverride = DEFAULT_OOM_RESERVATION;
        }
        this.mOomReservation = new byte[oomReservationOverride];
        synchronized (this.mAppStartDate) {
            this.mAppStartDate.setToNow();
            this.mOomReservation[0] = 1;
        }
        populateConstantFields();
        File file = new File(this.mContext.getDir(ACRA_DIRNAME, 0), PREALLOCATED_REPORTFILE);
        long preallocatedFileSizeOverride = this.mConfig.getPreallocatedFileSizeOverride();
        if (preallocatedFileSizeOverride <= 0) {
            preallocatedFileSizeOverride = DEFAULT_MAX_REPORT_SIZE;
        }
        if (file.length() < preallocatedFileSizeOverride) {
            try {
                preallocateReportFile(file, preallocatedFileSizeOverride);
            } catch (Throwable th) {
                tryLogInternalError(th);
                file = null;
            }
        }
        this.mPreallocFileName = file;
    }

    public int prepareCachedANRReports(int i) {
        Object obj = UNCAUGHT_EXCEPTION_LOCK;
        synchronized (obj) {
            obj.notify();
        }
        maybeRemoveAnrReports();
        return checkAndHandleReportsLocked(i, CrashReportType.CACHED_ANR_REPORT, true);
    }

    public int prepareReports(int i, FileGenerator fileGenerator, boolean z, CrashReportType... crashReportTypeArr) {
        int processCrashAttachmentsLocked;
        Object obj = UNCAUGHT_EXCEPTION_LOCK;
        synchronized (obj) {
            obj.notify();
        }
        C0139Dd.A09("ACRA", "#prepareReports - start");
        discardOverlappingReports(crashReportTypeArr);
        int i2 = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            int max = Math.max(0, i - i2);
            if (crashReportType.getHandler() != null) {
                processCrashAttachmentsLocked = checkAndHandleReportsLocked(max, crashReportType, false);
            } else {
                processCrashAttachmentsLocked = processCrashAttachmentsLocked(max, crashReportType, fileGenerator, z);
            }
            i2 += processCrashAttachmentsLocked;
        }
        if (!shouldSkipReport(CrashReportType.NATIVE_CRASH_REPORT)) {
            NativeCrashDumpReporterUtil.cleanupHeapDump(this.mContext);
        }
        C0139Dd.A09("ACRA", "#prepareReports - finish");
        return i2;
    }

    public void putLazyCustomDataInternal(String str, CustomReportDataSupplier customReportDataSupplier) {
        synchronized (this.mInstanceLazyCustomParameters) {
            this.mInstanceLazyCustomParameters.put(str, customReportDataSupplier);
        }
    }

    public void registerActivity(String str) {
        if (str != null) {
            this.mActivityLogger.append(str);
        }
    }

    public void removeAllReportSenders() {
        synchronized (this.mReportSenders) {
            this.mReportSenders.clear();
        }
    }

    public CustomReportDataSupplier removeLazyCustomDataInternal(String str) {
        CustomReportDataSupplier customReportDataSupplier;
        if (str == null) {
            return null;
        }
        synchronized (this.mInstanceLazyCustomParameters) {
            customReportDataSupplier = (CustomReportDataSupplier) this.mInstanceLazyCustomParameters.remove(str);
        }
        return customReportDataSupplier;
    }

    public void reportErrorAndTerminate(Thread thread, Throwable th) {
        synchronized (C0122Bs.class) {
            C0122Bs.A00().uncaughtException(thread, th);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public int roughlyCountPendingReportsOfType(CrashReportType... crashReportTypeArr) {
        if (this.mContext == null) {
            C0139Dd.A0A("ACRA", "Trying to get ACRA reports but ACRA is not initialized.");
            return 0;
        }
        int i = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            i += crashReportType.getPendingCrashReports(this.mContext).mDescriptors.length;
        }
        return i;
    }

    public void setCrashReportedObserver(CrashReportedObserver crashReportedObserver) {
        this.mCrashReportedObserver.set(crashReportedObserver);
    }

    public void setReportProxy(Proxy proxy) {
        synchronized (this.mReportSenders) {
            Iterator it = this.mReportSenders.iterator();
            while (it.hasNext()) {
                ReportSender reportSender = (ReportSender) it.next();
                if (reportSender instanceof FlexibleReportSender) {
                    ((FlexibleReportSender) reportSender).setProxy(proxy);
                }
            }
        }
    }

    public void setReportSender(ReportSender reportSender) {
        synchronized (this.mReportSenders) {
            removeAllReportSenders();
            addReportSender(reportSender);
        }
    }

    public void updateGLCwithSystemLibs(Spool.FileBeingConsumed fileBeingConsumed) {
        File crashDumpSysLibPath = getCrashDumpSysLibPath(this.mContext);
        if (!crashDumpSysLibPath.exists()) {
            try {
                crashDumpSysLibPath.createNewFile();
            } catch (IOException e) {
                C0139Dd.A0S("ACRA", e, "Failed to create GLC Lib file");
                return;
            }
        }
        try {
            writeLibsToFile(crashDumpSysLibPath, getNewLibs(crashDumpSysLibPath, new MinidumpReader(fileBeingConsumed.file).getModuleList()));
        } catch (IOException e2) {
            C0139Dd.A0S("ACRA", e2, "Failed to create GLC Lib file");
        }
    }

    public void writeLibsToFile(File file, HashSet hashSet) {
        Throwable th;
        IOException e;
        if (hashSet != null && !hashSet.isEmpty()) {
            Closeable closeable = null;
            try {
                sSystemLibFileLock.writeLock().lock();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true), hashSet.size());
                try {
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        bufferedWriter.write(AnonymousClass08.A04((String) it.next(), "\n"));
                    }
                    safeClose(bufferedWriter);
                } catch (IOException e2) {
                    e = e2;
                    closeable = bufferedWriter;
                    try {
                        C0139Dd.A0S("ACRA", e, "GLC file to write Exception");
                        safeClose(closeable);
                        sSystemLibFileLock.writeLock().unlock();
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
            } catch (IOException e3) {
                e = e3;
                C0139Dd.A0S("ACRA", e, "GLC file to write Exception");
                safeClose(closeable);
                sSystemLibFileLock.writeLock().unlock();
            }
            sSystemLibFileLock.writeLock().unlock();
        }
    }

    public void writeReportToStream(Throwable th, OutputStream outputStream) {
        CrashReportData crashReportData = new CrashReportData();
        Writer writer = CrashReportData.getWriter(outputStream);
        String throwableToString = throwableToString(th);
        crashReportData.put(ReportField.REPORT_ID, CrashTimeDataCollectorHelper.generateReportUuid().toString(), writer);
        CrashTimeDataCollector.gatherCrashData(this, this.mConfig, throwableToString, th, crashReportData, writer, null, true, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0062 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int checkAndHandleReportsLocked(int r9, com.facebook.acra.ErrorReporter.CrashReportType r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.checkAndHandleReportsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, boolean):int");
    }

    private String genCrashReportFileName(Class cls, UUID uuid, String str) {
        String str2;
        String obj = uuid.toString();
        String simpleName = cls.getSimpleName();
        if (this.mAppVersionCode != null) {
            str2 = AnonymousClass08.A04("-", this.mAppVersionCode);
        } else {
            str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        return AnonymousClass08.A07(obj, "-", simpleName, str2, str);
    }

    public static ErrorReporter getInstance() {
        return Holder.ERROR_REPORTER;
    }

    private void populateConstantFields() {
        String str;
        String str2;
        String str3;
        String str4;
        int aPKVersionCode = BuildConstants.getAPKVersionCode();
        this.mAppVersionCode = Integer.toString(BuildConstants.getBuildID());
        PackageManagerWrapper packageManagerWrapper = new PackageManagerWrapper(this.mContext, "ACRA");
        PackageInfo packageInfo = packageManagerWrapper.getPackageInfo();
        if (packageInfo == null || packageInfo.versionCode != aPKVersionCode || (str = packageInfo.versionName) == null) {
            str = "not set";
        }
        this.mAppVersionName = str;
        TreeMap treeMap = new TreeMap();
        if (this.mConfig.shouldReportField(ReportField.ANDROID_ID)) {
            try {
                str4 = sSecureSettingsResolver.getString(this.mContext.getContentResolver(), "android_id");
            } catch (Exception e) {
                C0139Dd.A0L(TAG, "Failed to fetch the constant field ANDROID_ID", e);
                str4 = "unknown";
            }
            treeMap.put(ReportField.ANDROID_ID, str4);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_VERSION_CODE)) {
            treeMap.put(ReportField.APP_VERSION_CODE, this.mAppVersionCode);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_VERSION_NAME)) {
            treeMap.put(ReportField.APP_VERSION_NAME, this.mAppVersionName);
        }
        if (this.mConfig.shouldReportField(ReportField.PACKAGE_NAME)) {
            treeMap.put(ReportField.PACKAGE_NAME, this.mContext.getPackageName());
        }
        if (this.mConfig.shouldReportField(ReportField.INSTALLER_NAME)) {
            treeMap.put(ReportField.INSTALLER_NAME, packageManagerWrapper.getInstallerPackageName());
        }
        if (this.mConfig.shouldReportField(ReportField.PHONE_MODEL)) {
            treeMap.put(ReportField.PHONE_MODEL, Build.MODEL);
        }
        if (this.mConfig.shouldReportField(ReportField.DEVICE)) {
            treeMap.put(ReportField.DEVICE, Build.DEVICE);
        }
        if (this.mConfig.shouldReportField(ReportField.ANDROID_VERSION)) {
            treeMap.put(ReportField.ANDROID_VERSION, Build.VERSION.RELEASE);
        }
        if (this.mConfig.shouldReportField(ReportField.OS_VERSION)) {
            treeMap.put(ReportField.OS_VERSION, System.getProperty("os.version"));
        }
        if (this.mConfig.shouldReportField(ReportField.BUILD_HOST)) {
            treeMap.put(ReportField.BUILD_HOST, Build.HOST);
        }
        if (this.mConfig.shouldReportField(ReportField.BRAND)) {
            treeMap.put(ReportField.BRAND, Build.BRAND);
        }
        if (this.mConfig.shouldReportField(ReportField.PRODUCT)) {
            treeMap.put(ReportField.PRODUCT, Build.PRODUCT);
        }
        if (this.mConfig.shouldReportField(ReportField.FILE_PATH)) {
            File filesDir = this.mContext.getFilesDir();
            if (filesDir != null) {
                str3 = filesDir.getAbsolutePath();
            } else {
                str3 = "n/a";
            }
            treeMap.put(ReportField.FILE_PATH, str3);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_INSTALL_TIME, CrashTimeDataCollectorHelper.formatTimestamp(packageInfo.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_EPOCH_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_INSTALL_EPOCH_TIME, String.valueOf(packageInfo.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_UPGRADE_TIME, CrashTimeDataCollectorHelper.formatTimestamp(packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_EPOCH_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_UPGRADE_EPOCH_TIME, String.valueOf(packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_SINCE_UPGRADE_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_SINCE_UPGRADE_TIME, Long.toString(System.currentTimeMillis() - packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.PUBLIC_SOURCE_DIR)) {
            if (this.mContext.getApplicationInfo() != null) {
                str2 = this.mContext.getApplicationInfo().publicSourceDir;
            } else {
                str2 = "null application info";
            }
            treeMap.put(ReportField.PUBLIC_SOURCE_DIR, str2);
        }
        if (this.mConfig.shouldReportField(ReportField.IS_RELABELED)) {
            treeMap.put(ReportField.IS_RELABELED, String.valueOf(BuildConstants.isRelabeled()));
        }
        this.mConstantFields = Collections.unmodifiableMap(treeMap);
    }

    public static String readFile(File file) {
        if (!file.exists()) {
            return NO_FILE;
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

    public static SigquitFileHeader readSigquitFileHeader(BufferedInputStream bufferedInputStream) {
        String str;
        String readVersionLine = readVersionLine(bufferedInputStream);
        String str2 = null;
        if (!TextUtils.isEmpty(readVersionLine)) {
            str = readVersionLine(bufferedInputStream);
        } else {
            str = null;
        }
        if (bufferedInputStream.markSupported()) {
            bufferedInputStream.mark(1024);
            byte[] bArr = new byte[1024];
            int read = bufferedInputStream.read(bArr, 0, 1024);
            if (read > 0) {
                Matcher matcher = mSigquitCmdLinePattern.matcher(new String(bArr, 0, read));
                if (matcher.find()) {
                    str2 = matcher.group(1);
                }
            }
            bufferedInputStream.reset();
        }
        SigquitFileHeader sigquitFileHeader = new SigquitFileHeader();
        sigquitFileHeader.versionCode = readVersionLine;
        sigquitFileHeader.versionName = str;
        sigquitFileHeader.commandLine = str2;
        return sigquitFileHeader;
    }

    public static void renameOrThrow(File file, File file2) {
        if (!file.renameTo(file2)) {
            throw new IOException(String.format("rename of %s to %s failed", file, file2));
        }
    }

    /* access modifiers changed from: private */
    public boolean shouldReportANRs() {
        return false;
    }

    public static void trimStackBuffer(StringBuffer stringBuffer, int i) {
        int i2;
        int lastIndexOf;
        int indexOf;
        if (stringBuffer.length() > i && (lastIndexOf = stringBuffer.lastIndexOf("\n", (i2 = i >> 1))) >= 0 && (indexOf = stringBuffer.indexOf("\n", stringBuffer.length() - i2)) >= 0) {
            stringBuffer.replace(lastIndexOf, indexOf, STACK_TRIMMED_MESSAGE);
        }
    }

    public void checkReportsOnApplicationStart() {
        checkNativeReportsOnApplicationStart();
        if (roughlyCountPendingReportsOfType(REPORTS_TO_CHECK_ON_STARTUP) > 0) {
            checkReportsOfType(REPORTS_TO_CHECK_ON_STARTUP);
        }
        synchronized (this) {
            this.mFinishedCheckingReports = true;
        }
        startUploadIfReady();
    }

    public SimpleTraceLogger getActivityLogger() {
        return this.mActivityLogger;
    }

    public long getAppStartTickTimeMs() {
        return this.mAppStartTickTimeMs;
    }

    public String getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public String getClientUserId() {
        return this.mClientUserId;
    }

    public Map getConstantFields() {
        return this.mConstantFields;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getEventsLog() {
        return null;
    }

    public LogBridge getLogBridge() {
        return this.mLogBridge;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public CustomReportDataSupplier removeLazyCustomData(String str) {
        return removeLazyCustomDataInternal(str);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        reportErrorAndTerminate(thread, th);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static void removeCustomData(String str) {
        removeCustomDataInternal(str);
    }

    public void setANRDataProvider(ANRDataProvider aNRDataProvider) {
        this.mANRDataProvider = aNRDataProvider;
    }

    public void setAppStartTickTimeMs(long j) {
        this.mAppStartTickTimeMs = j;
    }

    public void setClientUserId(String str) {
        this.mClientUserId = str;
    }

    public void setExcludedReportObserver(ExcludedReportObserver excludedReportObserver) {
        this.mExcludedReportObserver = excludedReportObserver;
    }

    public void setLogBridge(LogBridge logBridge) {
        this.mLogBridge = logBridge;
    }

    public void setMaxReportSize(long j) {
        this.mMaxReportSize = j;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public static void putCustomData(String str, String str2) {
        putCustomDataInternal(str, str2);
    }

    public void putLazyCustomData(String str, CustomReportDataSupplier customReportDataSupplier) {
        putLazyCustomDataInternal(str, customReportDataSupplier);
    }

    public class CrashAttachmentException extends Throwable {
        public CrashAttachmentException() {
        }
    }

    public final class FifoSpoolComparator implements Comparator {
        public FifoSpoolComparator() {
        }

        public /* synthetic */ FifoSpoolComparator(AnonymousClass1 r1) {
        }

        public int compare(Spool.Descriptor descriptor, Spool.Descriptor descriptor2) {
            long j = descriptor.lastModifiedTime;
            long j2 = descriptor2.lastModifiedTime;
            if (j == j2) {
                return 0;
            }
            return j < j2 ? -1 : 1;
        }
    }

    public class SigquitFileHeader {
        public String commandLine;
        public String versionCode;
        public String versionName;

        public SigquitFileHeader() {
        }

        public /* synthetic */ SigquitFileHeader(AnonymousClass1 r1) {
        }
    }

    public ErrorReporter() {
        this.mReportSenders = new ArrayList();
        this.mOomReservation = null;
        this.mMaxReportSize = DEFAULT_MAX_REPORT_SIZE;
        this.mAnrFilesInProgress = new HashSet();
        this.mInstanceLazyCustomParameters = new HashMap();
        this.mPreallocFileName = null;
        this.mActivityLogger = new SimpleTraceLogger(20);
        this.mAppStartDate = new Time();
        this.mExceptionTranslationHook = new AtomicReference();
        this.mExcludedReportObserver = null;
        this.mCrashReportedObserver = new AtomicReference();
        init();
    }

    public static void init() {
        if (C0150Dr.A00 != null) {
            C0139Dd.A0D(TAG, "ErrorReportingDiagnosticData.setInstance already set.");
            return;
        }
        C0139Dd.A09(TAG, "Using ErrorReporter for ErrorReportingDiagnosticData");
        C0150Dr.A00 = new Object() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass2 */

            public void putLazyCustomData(String str, final AbstractC0463a6 a6Var) {
                ErrorReporter.getInstance().putLazyCustomDataInternal(str, new CustomReportDataSupplier() {
                    /* class com.facebook.acra.ErrorReporter.AnonymousClass2.AnonymousClass1 */

                    @Override // com.facebook.acra.CustomReportDataSupplier
                    public String getCustomData(Throwable th) {
                        return (String) a6Var.get();
                    }
                });
            }

            public void removeLazyCustomData(String str) {
                ErrorReporter.getInstance().removeLazyCustomDataInternal(str);
            }

            public void removeCustomData(String str) {
                ErrorReporter.removeCustomDataInternal(str);
            }

            public void putCustomData(String str, String str2) {
                ErrorReporter.putCustomDataInternal(str, str2);
            }
        };
    }

    private CrashReportData loadCrashAttachment(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, boolean z) {
        return loadCrashReport(fileBeingConsumed, crashReportType, crashReportType.defaultMaxSize, z);
    }

    private CrashReportData loadCrashAttachment(File file, CrashReportType crashReportType, boolean z) {
        return loadCrashReport(file, null, crashReportType, crashReportType.defaultMaxSize, z);
    }

    private CrashReportData loadCrashReport(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, long j, boolean z) {
        return loadCrashReport(fileBeingConsumed.fileName, fileBeingConsumed.file, crashReportType, j, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0094, code lost:
        if ((r12 - r8) <= r0) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a2 A[Catch:{ IOException -> 0x00aa }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016b A[SYNTHETIC, Splitter:B:79:0x016b] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0179 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0185 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.CrashReportData loadCrashReport(java.io.File r21, java.io.RandomAccessFile r22, com.facebook.acra.ErrorReporter.CrashReportType r23, long r24, boolean r26) {
        /*
        // Method dump skipped, instructions count: 390
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.loadCrashReport(java.io.File, java.io.RandomAccessFile, com.facebook.acra.ErrorReporter$CrashReportType, long, boolean):com.facebook.acra.CrashReportData");
    }

    private void reportSoftError(Throwable th, String str, String str2, ErrorReporter errorReporter) {
        String sessionId;
        if (this.mConfig == null) {
            sessionId = null;
        } else {
            sessionId = this.mConfig.getSessionId();
        }
        reportSoftError(th, str, str2, sessionId, errorReporter);
    }

    public static void reportSoftError(Throwable th, String str, String str2, String str3, ErrorReporter errorReporter) {
        CrashReportData crashReportData = new CrashReportData();
        crashReportData.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, str);
        crashReportData.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, str2);
        if (str3 != null) {
            crashReportData.put(ReportField.SESSION_ID, str3);
        }
        errorReporter.handleException(th, crashReportData);
    }

    private void tryLogInternalError(String str, Throwable th) {
        if (str == null) {
            str = "???";
        }
        try {
            C0139Dd.A0V("ACRA", th, "internal ACRA error: %s: ", str);
        } catch (Throwable unused) {
        }
    }

    private void tryLogInternalError(Throwable th) {
        tryLogInternalError(null, th);
    }

    public ReportsSenderWorker handleException(Throwable th) {
        return handleException(th, (CrashReportData) null);
    }

    public ReportsSenderWorker handleException(Throwable th, CrashReportData crashReportData) {
        return handleExceptionInternal(th, crashReportData, null, 1);
    }

    public ReportsSenderWorker handleException(Throwable th, String str, CrashReportData crashReportData) {
        return handleExceptionInternal(th, crashReportData, str, 1);
    }

    public ReportsSenderWorker handleException(Throwable th, Map map) {
        return handleException(th, map != null ? new CrashReportData(map) : null);
    }

    public void init(AcraReportingConfig acraReportingConfig) {
        if (!this.mInitializationComplete) {
            this.mContext = acraReportingConfig.getApplicationContext();
            if (this.mContext != null) {
                this.mInstallTime = new File(this.mContext.getApplicationInfo().sourceDir).lastModified();
                if (this.mInstallTime == 0) {
                    C0139Dd.A0D("ACRA", "could not retrieve APK mod time");
                }
                this.mConfig = acraReportingConfig;
                this.mChainedHandler = acraReportingConfig.getDefaultUncaughtExceptionHandler();
                if (this.mConfig.getSessionId() != null) {
                    putCustomDataInternal(ReportField.SESSION_ID, this.mConfig.getSessionId());
                }
                addCriticalData();
                this.mInitializationComplete = true;
                return;
            }
            throw new AssertionError("context must be non-null");
        }
        throw new IllegalStateException("ErrorReporter already initialized");
    }

    public int prepareANRReport(String str, FileGenerator fileGenerator) {
        Object obj = UNCAUGHT_EXCEPTION_LOCK;
        synchronized (obj) {
            obj.notify();
        }
        return buildCachedCrashReport(CrashReportType.ANR_REPORT, str, null, fileGenerator);
    }

    public void prepareANRReport(FileGenerator fileGenerator) {
        Object obj = UNCAUGHT_EXCEPTION_LOCK;
        synchronized (obj) {
            obj.notify();
        }
        synchronized (ANR_REPORTING_LOCK) {
            prepareReports(Integer.MAX_VALUE, fileGenerator, false, CrashReportType.ANR_REPORT);
        }
    }

    public void prepareANRReport(File file, FileGenerator fileGenerator) {
        Object obj = UNCAUGHT_EXCEPTION_LOCK;
        synchronized (obj) {
            obj.notify();
        }
        maybeRemoveAnrReports();
        synchronized (ANR_REPORTING_LOCK) {
            buildCachedCrashReport(CrashReportType.ANR_REPORT, null, file, fileGenerator);
        }
    }
}
