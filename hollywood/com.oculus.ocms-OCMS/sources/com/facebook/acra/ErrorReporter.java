package com.facebook.acra;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.PowerManager;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
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
import com.facebook.annotations.DoNotOptimize;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.exceptionhandler.CustomStackTracerInterface;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.common.exceptionhandler.ManagedExceptionHandler;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.blackbox.BlackBoxRecorderControl;
import com.facebook.errorreporting.appstate.blackbox.BlackBoxState;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.ipc.activity.ActivityConstants;
import com.oculus.util.constants.OculusConstants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.apache.commons.cli.HelpFormatter;

@DoNotOptimize
public final class ErrorReporter implements ManagedExceptionHandler {
    public static final String ACRA_DIRNAME = "acra-reports";
    private static final int ACTION_DISCARD_AND_DONT_SEND = 1;
    private static final int ACTION_SEND = 2;
    private static final String ANR_EXTRA_PROPERTIES_EXTENSION = ".upd";
    private static final Object ANR_REPORTING_LOCK = new Object();
    public static final String ANR_TRACES_FILE_PATH = "/data/anr/traces.txt";
    public static final String CACHED_REPORTFILE_EXTENSION = ".cachedreport";
    public static final String CRASH_ATTACHMENT_DUMMY_STACKTRACE = "crash attachment";
    public static final String CRASH_DUMP_SYS_LIBS_FILE = "crash_dump_sys_libs";
    public static final long DEFAULT_MAX_REPORT_SIZE = 1572864;
    public static final int DEFAULT_OOM_RESERVATION = 65536;
    public static final String DUMPFILE_EXTENSION = ".dmp";
    public static final String DUMP_DIR = "minidumps";
    private static final String FILE_IAB_OPEN_TIMES = "iab_open_times";
    private static final String FILE_LAST_ACTIVITY = "last_activity_opened";
    private static final int HANDLE_EXCEPTION_NEVER_SEND_IMMEDIATELY = 4;
    private static final int HANDLE_EXCEPTION_SEND_IMMEDIATELY = 1;
    private static final int HANDLE_EXCEPTION_SEND_SYNCHRONOUSLY = 2;
    public static final int MAX_ACRA_REPORTS_ON_DISK = 5;
    public static final int MAX_ANR_TRACES_TIME_DELTA_MS = 15000;
    public static final int MAX_SEND_REPORTS = 5;
    static final int MAX_STACK_LENGTH_FOR_OVERFLOW = 800000;
    private static final int MAX_TRACE_COUNT_LIMIT = 20;
    private static final int MAX_TRANSLATION_HOOK_RUNS = 4;
    private static final int MAX_VERSION_LINE_LENGTH = 20;
    public static final long NATIVE_MAX_REPORT_SIZE = 8388608;
    private static final String NO_FILE = "NO_FILE";
    public static final long NUM_NATIVE_CRASHES_SAVED = 5;
    public static final String PREALLOCATED_REPORTFILE = "reportfile.prealloc";
    public static final String REPORTED_CRASH_DIR = "reported_crashes";
    public static final String REPORTFILE_EXTENSION = ".stacktrace";
    private static final CrashReportType[] REPORTS_TO_CHECK_ON_STARTUP = {CrashReportType.ACRA_CRASH_REPORT, CrashReportType.NATIVE_CRASH_REPORT};
    public static final String SIGQUIT_DIR = "traces";
    public static final long SIGQUIT_MAX_REPORT_SIZE = 524288;
    private static final int SIGQUIT_PROCESS_NAME_BUFFER_SIZE = 1024;
    static final String STACK_TRIMMED_MESSAGE = "\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxSTACK_FRAMES_TRIMMED_FOR_OVERFLOWxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static final String TAG = "ErrorReporter";
    private static final Object UNCAUGHT_EXCEPTION_LOCK = new Object();
    public static final String UNKNOWN_FIELD_VALUE = "unknown";
    private static final String mInternalException = "ACRA_INTERNAL=java.lang.Exception: An exception occurred while trying to collect data about an ACRA internal error\n\tat com.facebook.acra.ErrorReporter.handleException(ErrorReporter.java:810)\n\tat com.facebook.acra.ErrorReporter.handleException(ErrorReporter.java:866)\n\tat com.facebook.acra.ErrorReporter.uncaughtException(ErrorReporter.java:666)\n\tat java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:693)\n\tat java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:690)\n";
    private static final AtomicInteger mSendAttempts = new AtomicInteger();
    private static final Pattern mSigquitCmdLinePattern = Pattern.compile("^Cmd line: (.*)", 8);
    @VisibleForTesting
    static SecureSettingsResolver sSecureSettingsResolver = new DefaultSecureSettingsResolver();
    public static final ReentrantReadWriteLock sSystemLibFileLock = new ReentrantReadWriteLock();
    private volatile ANRDataProvider mANRDataProvider;
    private final SimpleTraceLogger mActivityLogger;
    private final Set<String> mAnrFilesInProgress;
    private final Time mAppStartDate;
    private volatile long mAppStartTickTimeMs;
    private volatile String mAppVersionCode;
    private volatile String mAppVersionName;
    private BatchUploader mBatchUploader;
    private volatile Thread.UncaughtExceptionHandler mChainedHandler;
    private volatile String mClientUserId;
    private volatile AcraReportingConfig mConfig;
    private volatile Map<String, String> mConstantFields;
    private volatile Context mContext;
    private final AtomicReference<CrashReportedObserver> mCrashReportedObserver;
    private final AtomicReference<ExceptionTranslationHook> mExceptionTranslationHook;
    @Nullable
    private volatile ExcludedReportObserver mExcludedReportObserver;
    private volatile boolean mFinishedCheckingReports;
    private volatile boolean mInitializationComplete;
    private volatile long mInstallTime;
    private final Map<String, CustomReportDataSupplier> mInstanceLazyCustomParameters;
    @Nullable
    private volatile LogBridge mLogBridge;
    private volatile long mMaxReportSize;
    @Nullable
    private volatile byte[] mOomReservation;
    @GuardedBy("this")
    private int mPendingReportWriters;
    @Nullable
    private volatile File mPreallocFileName;
    private final ArrayList<ReportSender> mReportSenders;
    private volatile boolean mStartedBatchUploader;
    @Nullable
    private volatile String mUserId;

    public interface CrashReportedObserver {
        void onCrashReported(CrashReportData crashReportData);
    }

    @ThreadSafe
    public interface ExcludedReportObserver {
        void onExclude(CrashReportData crashReportData);
    }

    /* access modifiers changed from: private */
    public interface ReportHandler {
        boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z);
    }

    /* access modifiers changed from: package-private */
    public interface SecureSettingsResolver {
        String getString(ContentResolver contentResolver, String str);
    }

    public String getSigquitTracesExtension() {
        return REPORTFILE_EXTENSION;
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

    public synchronized void setBatchUploader(BatchUploader batchUploader) {
        if (this.mConfig != null) {
            if (this.mConfig.shouldUseUploadService()) {
                this.mBatchUploader = batchUploader;
                startUploadIfReady();
            }
        }
    }

    public long getAppStartTickTimeMs() {
        return this.mAppStartTickTimeMs;
    }

    public void setAppStartTickTimeMs(long j) {
        this.mAppStartTickTimeMs = j;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getConstantFields() {
        return this.mConstantFields;
    }

    public String getAppStartDateFormat3339() {
        String format3339;
        synchronized (this.mAppStartDate) {
            format3339 = this.mAppStartDate.format3339(false);
        }
        return format3339;
    }

    private void reportSoftError(Throwable th, String str, String str2, ErrorReporter errorReporter) {
        reportSoftError(th, str, str2, this.mConfig == null ? null : this.mConfig.getSessionId(), errorReporter);
    }

    private static void reportSoftError(Throwable th, String str, String str2, @Nullable String str3, ErrorReporter errorReporter) {
        CrashReportData crashReportData = new CrashReportData();
        crashReportData.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, str);
        crashReportData.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, str2);
        if (str3 != null) {
            crashReportData.put(ReportField.SESSION_ID, str3);
        }
        errorReporter.handleException(th, crashReportData);
    }

    /* access modifiers changed from: private */
    public static class SigquitFileHeader {
        @Nullable
        String commandLine;
        @Nullable
        String versionCode;
        @Nullable
        String versionName;

        private SigquitFileHeader() {
        }
    }

    private static class AcraReportHandler implements ReportHandler {
        private AcraReportHandler() {
        }

        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z) {
            File file = fileBeingConsumed.fileName;
            String name = file.getName();
            BLog.d(ACRA.LOG_TAG, "Loading file %s", name);
            try {
                CrashReportData loadAcraCrashReport = errorReporter.loadAcraCrashReport(fileBeingConsumed);
                if (loadAcraCrashReport != null) {
                    loadAcraCrashReport.put(ReportField.ACRA_REPORT_TYPE, CrashReportType.ACRA_CRASH_REPORT.name());
                    loadAcraCrashReport.put(ReportField.ACRA_REPORT_FILENAME, name);
                    loadAcraCrashReport.put(ReportField.UPLOADED_BY_PROCESS, str);
                    BLog.i(ACRA.LOG_TAG, "Sending file %s", name);
                    errorReporter.sendCrashReport(loadAcraCrashReport);
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
    public static void purgeDirectory(File file, @Nullable final String str) {
        File[] listFiles;
        AnonymousClass1 r0 = str != null ? new FileFilter() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass1 */

            public boolean accept(File file) {
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

    @VisibleForTesting
    static class CachedANRReportHandler implements ReportHandler {
        CachedANRReportHandler() {
        }

        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public boolean handleReport(ErrorReporter errorReporter, Spool.FileBeingConsumed fileBeingConsumed, String str, boolean z) {
            if (!errorReporter.shouldReportANRs()) {
                ErrorReporter.purgeDirectory(errorReporter.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0), null);
                return true;
            }
            File file = fileBeingConsumed.fileName;
            String name = file.getName();
            try {
                String canonicalPath = file.getCanonicalPath();
                File file2 = new File(canonicalPath + ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION);
                try {
                    InputStream fileInputStream = new FileInputStream(file);
                    try {
                        CrashReportData crashReportData = new CrashReportData();
                        crashReportData.load(fileInputStream);
                        CrashReportData crashReportData2 = new CrashReportData();
                        if (file2.exists()) {
                            InputStream fileInputStream2 = new FileInputStream(file2);
                            try {
                                crashReportData2.load(fileInputStream2);
                                fileInputStream2.close();
                            } catch (Throwable unused) {
                            }
                        }
                        addUpdatedProperties(crashReportData, crashReportData2, str, file2);
                        crashReportData.merge((Map<String, String>) crashReportData2, true, (Writer) null);
                        BLog.i(ACRA.LOG_TAG, "Sending file %s", name);
                        if (z) {
                            crashReportData.put(ReportField.SENT_IMMEDIATELY, "true");
                        }
                        errorReporter.sendCrashReport(crashReportData);
                        ErrorReporter.deleteFile(file);
                        ErrorReporter.deleteFile(file2);
                        fileInputStream.close();
                        return true;
                    } catch (Throwable unused2) {
                    }
                } catch (RuntimeException e) {
                    BLog.e(ACRA.LOG_TAG, e, "Failed to send crash reports");
                    ErrorReporter.deleteFile(file);
                    ErrorReporter.deleteFile(file2);
                    return false;
                } catch (IOException e2) {
                    BLog.e(ACRA.LOG_TAG, e2, "Failed to load crash report for %s", name);
                    ErrorReporter.deleteFile(file);
                    ErrorReporter.deleteFile(file2);
                    return false;
                } catch (ReportSenderException e3) {
                    BLog.e(ACRA.LOG_TAG, e3, "Failed to send crash report for %s", name);
                    return false;
                }
            } catch (IOException e4) {
                BLog.e(ACRA.LOG_TAG, e4, "Failed to get full path of crash report for %s", name);
                return false;
            }
            throw th;
            throw th;
        }

        private void addUpdatedProperties(CrashReportData crashReportData, CrashReportData crashReportData2, String str, File file) throws IOException {
            boolean z;
            String customData;
            String customData2;
            String str2;
            HashMap hashMap = new HashMap();
            int i = -1;
            if (!ACRA.getCachedShouldUploadSystemANRTraces() || (((str2 = (String) crashReportData.get(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES)) != null && !str2.equals(OculusConstants.DEFAULT_ENTERPRISE_USER_ID)) || ((String) crashReportData2.get(ReportField.SIGQUIT)) != null)) {
                z = false;
            } else {
                BLog.i(ACRA.LOG_TAG, "Looking for system ANR traces");
                StringBuilder sb = new StringBuilder();
                int findANRTraces = findANRTraces(crashReportData.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReportData.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, sb);
                String str3 = null;
                if (findANRTraces > -1) {
                    str3 = sb.toString();
                }
                if (str3 != null) {
                    BLog.i(ACRA.LOG_TAG, "Found and attaching system ANR traces");
                    hashMap.put(ReportField.SIGQUIT, AttachmentUtil.compressToBase64String(str3.getBytes()));
                    hashMap.put(ErrorReportingConstants.ANR_SYSTEM_TRACES_PRESENT, "true");
                } else {
                    hashMap.put(ErrorReportingConstants.ANR_SYSTEM_TRACES_PRESENT, "false");
                }
                i = findANRTraces;
                z = true;
            }
            if (ACRA.getCachedShouldLogProcessPositionInAnrTraceFile()) {
                if (!z) {
                    i = findANRTraces(crashReportData.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReportData.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, null);
                }
                hashMap.put(ErrorReportingConstants.ANR_TRACE_POSITION, String.valueOf(i));
            }
            hashMap.put(ReportField.UPLOADED_BY_PROCESS, str);
            if (((String) crashReportData2.get(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG)) == null) {
                hashMap.put(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReporter.getCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG));
            }
            if (((String) crashReportData2.get(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG)) == null && (customData2 = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG)) != null) {
                hashMap.put(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, customData2);
            }
            if (((String) crashReportData2.get(ErrorReportingConstants.ANR_SYSTEM_TAG)) == null && (customData = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_TAG)) != null) {
                hashMap.put(ErrorReportingConstants.ANR_SYSTEM_TAG, customData);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            crashReportData2.putAll(hashMap, CrashReportData.getWriter(fileOutputStream));
            fileOutputStream.close();
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public int findANRTraces(String str, long j, String str2, @Nullable StringBuilder sb) {
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
        ACRA_CRASH_REPORT(ErrorReporter.ACRA_DIRNAME, ErrorReporter.DEFAULT_MAX_REPORT_SIZE, null, new AcraReportHandler(), null, ErrorReporter.REPORTFILE_EXTENSION),
        NATIVE_CRASH_REPORT("minidumps", 8388608, ReportField.MINIDUMP, null, null, ErrorReporter.DUMPFILE_EXTENSION),
        ANR_REPORT(ErrorReporter.SIGQUIT_DIR, 524288, ReportField.SIGQUIT, null, null, ErrorReporter.REPORTFILE_EXTENSION),
        CACHED_ANR_REPORT(ErrorReporter.SIGQUIT_DIR, 524288, ReportField.SIGQUIT, new CachedANRReportHandler(), new String[]{ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION}, ".cachedreport");
        
        @Nullable
        private final String attachmentField;
        private final long defaultMaxSize;
        private final String directory;
        @Nullable
        private final String[] extraFileExtensions;
        private final String[] fileExtensions;
        @Nullable
        private final ReportHandler handler;
        private final Object mLock = new Object();
        private Spool mSpool;

        private CrashReportType(String str, long j, @Nullable String str2, @Nullable ReportHandler reportHandler, @Nullable String[] strArr, String... strArr2) {
            this.directory = str;
            this.defaultMaxSize = j;
            this.attachmentField = str2;
            this.handler = reportHandler;
            this.extraFileExtensions = strArr;
            this.fileExtensions = strArr2;
        }

        public Spool getSpool(Context context) {
            Spool spool;
            synchronized (this.mLock) {
                if (this.mSpool == null) {
                    this.mSpool = new Spool(context.getDir(this.directory, 0));
                }
                spool = this.mSpool;
            }
            return spool;
        }

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

        @Nullable
        public ReportHandler getHandler() {
            return this.handler;
        }
    }

    /* access modifiers changed from: package-private */
    public final class ReportsSenderWorker extends Thread {
        @Nullable
        private Throwable exception;
        @Nullable
        private FileGenerator mGenerator;
        @Nullable
        private final CrashReportData mInMemoryReportToSend;
        @Nullable
        private final Spool.FileBeingProduced mReportFileUnderConstruction;
        @Nullable
        private final CrashReportType[] mReportTypesToSend;

        public ReportsSenderWorker(ErrorReporter errorReporter, @Nullable CrashReportData crashReportData, Spool.FileBeingProduced fileBeingProduced) {
            this(crashReportData, fileBeingProduced, null);
        }

        public ReportsSenderWorker(ErrorReporter errorReporter, CrashReportType... crashReportTypeArr) {
            this(null, null, crashReportTypeArr);
        }

        private ReportsSenderWorker(@Nullable CrashReportData crashReportData, @Nullable Spool.FileBeingProduced fileBeingProduced, @Nullable CrashReportType[] crashReportTypeArr) {
            super("ReportsSenderWorker");
            this.exception = null;
            this.mGenerator = null;
            this.mInMemoryReportToSend = crashReportData;
            this.mReportFileUnderConstruction = fileBeingProduced;
            this.mReportTypesToSend = crashReportTypeArr;
        }

        public void routeReportToFile(FileGenerator fileGenerator) {
            this.mGenerator = fileGenerator;
        }

        /* access modifiers changed from: package-private */
        public void doSend() throws ReportSenderException {
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
                        crashReportData.put(ReportField.UPLOADED_BY_PROCESS, CrashTimeDataCollector.getProcessNameFromAms(ErrorReporter.this.mContext));
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

        @Nullable
        public Throwable getException() {
            return this.exception;
        }

        @Nullable
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

    /* access modifiers changed from: private */
    public static class Holder {
        public static final ErrorReporter ERROR_REPORTER = new ErrorReporter();

        private Holder() {
        }
    }

    private ErrorReporter() {
        this.mReportSenders = new ArrayList<>();
        this.mOomReservation = null;
        this.mMaxReportSize = DEFAULT_MAX_REPORT_SIZE;
        this.mAnrFilesInProgress = new HashSet();
        this.mInstanceLazyCustomParameters = new HashMap();
        this.mPreallocFileName = null;
        this.mActivityLogger = new SimpleTraceLogger(20);
        this.mAppStartDate = new Time();
        this.mExceptionTranslationHook = new AtomicReference<>();
        this.mExcludedReportObserver = null;
        this.mCrashReportedObserver = new AtomicReference<>();
    }

    public void addExceptionTranslationHook(ExceptionTranslationHook exceptionTranslationHook) {
        exceptionTranslationHook.next = this.mExceptionTranslationHook.getAndSet(exceptionTranslationHook);
    }

    @Nullable
    public String getUserId() {
        return this.mUserId;
    }

    public String getClientUserId() {
        return this.mClientUserId;
    }

    public void setUserId(@Nullable String str) {
        this.mUserId = str;
    }

    public void setClientUserId(String str) {
        this.mClientUserId = str;
    }

    public String getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public String getSigquitTracesPath() {
        return this.mContext.getDir(SIGQUIT_DIR, 0).getPath();
    }

    /* access modifiers changed from: package-private */
    public SimpleTraceLogger getActivityLogger() {
        return this.mActivityLogger;
    }

    public static int getSendAttempts() {
        return mSendAttempts.get();
    }

    public boolean addToAnrInProgressUpdateFile(Map<String, String> map) throws IOException {
        synchronized (this.mAnrFilesInProgress) {
            if (this.mAnrFilesInProgress.isEmpty()) {
                return false;
            }
            String next = this.mAnrFilesInProgress.iterator().next();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(next + ANR_EXTRA_PROPERTIES_EXTENSION), true);
            new CrashReportData(map).store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return true;
        }
    }

    public static void putCustomData(String str, @Nullable String str2) {
        ProxyCustomDataStore.getInstance().setCustomData(str, str2, new Object[0]);
    }

    public static void removeCustomData(@Nullable String str) {
        ProxyCustomDataStore.getInstance().removeCustomData(str);
    }

    @Nullable
    public static String getCustomData(String str) {
        return ProxyCustomDataStore.getInstance().getCustomData(str);
    }

    public static boolean containsKeyInCustomData(String str) {
        return ProxyCustomDataStore.getInstance().containsKey(str);
    }

    public void putLazyCustomData(String str, CustomReportDataSupplier customReportDataSupplier) {
        synchronized (this.mInstanceLazyCustomParameters) {
            this.mInstanceLazyCustomParameters.put(str, customReportDataSupplier);
        }
    }

    public void setExcludedReportObserver(@Nullable ExcludedReportObserver excludedReportObserver) {
        this.mExcludedReportObserver = excludedReportObserver;
    }

    @Nullable
    public CustomReportDataSupplier removeLazyCustomData(String str) {
        CustomReportDataSupplier remove;
        if (str == null) {
            return null;
        }
        synchronized (this.mInstanceLazyCustomParameters) {
            remove = this.mInstanceLazyCustomParameters.remove(str);
        }
        return remove;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getCustomFieldsSnapshot() {
        return ProxyCustomDataStore.getInstance().getSnapshot();
    }

    /* access modifiers changed from: package-private */
    public Map<String, CustomReportDataSupplier> getLazyCustomFieldsSnapshot() {
        TreeMap treeMap;
        synchronized (this.mInstanceLazyCustomParameters) {
            treeMap = new TreeMap(this.mInstanceLazyCustomParameters);
        }
        return treeMap;
    }

    public static ErrorReporter getInstance() {
        return Holder.ERROR_REPORTER;
    }

    public void init(AcraReportingConfig acraReportingConfig) {
        if (!this.mInitializationComplete) {
            this.mContext = acraReportingConfig.getApplicationContext();
            if (this.mContext != null) {
                this.mInstallTime = new File(this.mContext.getApplicationInfo().sourceDir).lastModified();
                if (this.mInstallTime == 0) {
                    BLog.w(ACRA.LOG_TAG, "could not retrieve APK mod time");
                }
                this.mConfig = acraReportingConfig;
                this.mChainedHandler = acraReportingConfig.getDefaultUncaughtExceptionHandler();
                if (this.mConfig.getSessionId() != null) {
                    putCustomData(ReportField.SESSION_ID, this.mConfig.getSessionId());
                }
                addCriticalData();
                this.mInitializationComplete = true;
                return;
            }
            throw new AssertionError("context must be non-null");
        }
        throw new IllegalStateException("ErrorReporter already initialized");
    }

    public void initFallible() {
        int oomReservationOverride = this.mConfig.getOomReservationOverride();
        if (oomReservationOverride <= 0) {
            oomReservationOverride = 65536;
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
            putCustomData(ErrorReportingConstants.DEVICE_ID_KEY, deviceId);
        }
        for (Map.Entry<String, String> entry : CriticalAppData.getAdditionalParamValues(this.mContext).entrySet()) {
            String value = entry.getValue();
            if (!TextUtils.isEmpty(value)) {
                putCustomData(entry.getKey(), value);
            }
        }
    }

    @SuppressLint({"HardwareIds"})
    private void populateConstantFields() {
        String str;
        int aPKVersionCode = BuildConstants.getAPKVersionCode();
        this.mAppVersionCode = Integer.toString(BuildConstants.getBuildID());
        PackageManagerWrapper packageManagerWrapper = new PackageManagerWrapper(this.mContext, ACRA.LOG_TAG);
        PackageInfo packageInfo = packageManagerWrapper.getPackageInfo();
        if (packageInfo != null && aPKVersionCode == 1) {
            this.mAppVersionCode = Integer.toString(packageInfo.versionCode);
        }
        if (packageInfo == null || (!(packageInfo.versionCode == aPKVersionCode || aPKVersionCode == 1) || packageInfo.versionName == null)) {
            this.mAppVersionName = "not set";
        } else {
            this.mAppVersionName = packageInfo.versionName;
        }
        TreeMap treeMap = new TreeMap();
        if (this.mConfig.shouldReportField(ReportField.ANDROID_ID)) {
            try {
                str = sSecureSettingsResolver.getString(this.mContext.getContentResolver(), "android_id");
            } catch (Exception e) {
                BLog.e(TAG, "Failed to fetch the constant field ANDROID_ID", e);
                str = "unknown";
            }
            treeMap.put(ReportField.ANDROID_ID, str);
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
            treeMap.put(ReportField.FILE_PATH, filesDir != null ? filesDir.getAbsolutePath() : "n/a");
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_INSTALL_TIME, CrashTimeDataCollectorHelper.formatAppInstallTime(packageInfo.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_EPOCH_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_INSTALL_EPOCH_TIME, String.valueOf(packageInfo.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_UPGRADE_TIME, CrashTimeDataCollectorHelper.formatAppUpgradeTime(packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_EPOCH_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_UPGRADE_EPOCH_TIME, String.valueOf(packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_SINCE_UPGRADE_TIME) && packageInfo != null) {
            treeMap.put(ReportField.APP_SINCE_UPGRADE_TIME, Long.toString(System.currentTimeMillis() - packageInfo.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.PUBLIC_SOURCE_DIR)) {
            if (this.mContext.getApplicationInfo() != null) {
                treeMap.put(ReportField.PUBLIC_SOURCE_DIR, this.mContext.getApplicationInfo().publicSourceDir);
            } else {
                treeMap.put(ReportField.PUBLIC_SOURCE_DIR, "null application info");
            }
        }
        if (this.mConfig.shouldReportField(ReportField.IS_RELABELED)) {
            treeMap.put(ReportField.IS_RELABELED, String.valueOf(BuildConstants.isRelabeled()));
        }
        this.mConstantFields = Collections.unmodifiableMap(treeMap);
    }

    private void preallocateReportFile(File file, long j) throws IOException {
        Spool.FileBeingProduced produce = CrashReportType.ACRA_CRASH_REPORT.getSpool(this.mContext).produce(genCrashReportFileName(ErrorReporter.class, UUID.randomUUID(), REPORTFILE_EXTENSION));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(produce.fileName);
            try {
                byte[] bArr = new byte[32768];
                for (long j2 = 0; j2 < j; j2 += (long) bArr.length) {
                    fileOutputStream.write(bArr);
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                renameOrThrow(produce.fileName, file);
                try {
                    produce.close();
                    return;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } finally {
            produce.fileName.delete();
        }
    }

    private static void renameOrThrow(File file, File file2) throws IOException {
        if (!file.renameTo(file2)) {
            throw new IOException(String.format("rename of %s to %s failed", file, file2));
        }
    }

    @Nullable
    private Throwable translateException(Throwable th, Map<String, String> map) {
        Throwable th2;
        ExceptionTranslationHook exceptionTranslationHook = this.mExceptionTranslationHook.get();
        int i = 0;
        while (true) {
            th2 = th;
            for (ExceptionTranslationHook exceptionTranslationHook2 = exceptionTranslationHook; exceptionTranslationHook2 != null && th2 != null; exceptionTranslationHook2 = exceptionTranslationHook2.next) {
                try {
                    th2 = exceptionTranslationHook2.translate(th2, map);
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
        this.mOomReservation = null;
        discardOverlappingReports(CrashReportType.ACRA_CRASH_REPORT);
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                Api9Utils.disableStrictMode();
            }
        } catch (Throwable th2) {
            tryLogInternalError(th2);
        }
        int i = 3;
        try {
            BLog.e(ACRA.LOG_TAG, "ACRA caught a %s exception for %s version %s. Building report.", th.getClass().getSimpleName(), this.mContext.getPackageName(), this.mAppVersionCode);
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
                BlackBoxRecorderControl blackBoxRecorderControl = BlackBoxState.getInstance().getBlackBoxRecorderControl();
                Object captureBlackBoxTrace = blackBoxRecorderControl != null ? blackBoxRecorderControl.captureBlackBoxTrace(treeMap, 1) : null;
                handleExceptionInternal(translateException, new CrashReportData(treeMap), null, i);
                if (blackBoxRecorderControl != null) {
                    blackBoxRecorderControl.awaitForBlackBoxTraceCompletion(captureBlackBoxTrace);
                }
            } catch (Throwable th4) {
                if (!z) {
                    BLog.e(ACRA.LOG_TAG, th4, "error during error reporting: will attempt to report error");
                    uncaughtExceptionImpl(thread, th4, true);
                    return;
                }
                throw th4;
            }
        }
    }

    private void tryLogInternalError(Throwable th) {
        tryLogInternalError(null, th);
    }

    private void tryLogInternalError(@Nullable String str, Throwable th) {
        if (str == null) {
            str = "???";
        }
        try {
            BLog.e(ACRA.LOG_TAG, th, "internal ACRA error: %s: ", str);
        } catch (Throwable unused) {
        }
    }

    public void reportErrorAndTerminate(Thread thread, Throwable th) {
        ExceptionHandlerManager.handleThrowableAndTerminate(thread, th);
    }

    @Deprecated
    public void uncaughtException(Thread thread, Throwable th) {
        reportErrorAndTerminate(thread, th);
    }

    @Override // com.facebook.common.exceptionhandler.ManagedExceptionHandler
    public void handleUncaughtException(Thread thread, Throwable th, @Nullable CustomStackTracerInterface.CustomStackTrace customStackTrace) {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.ACRA_CRASH_REPORT);
        int maxPendingJavaCrashReports = this.mConfig.getMaxPendingJavaCrashReports(roughlyCountPendingReportsOfType);
        if (maxPendingJavaCrashReports < Integer.MAX_VALUE) {
            if (roughlyCountPendingReportsOfType >= maxPendingJavaCrashReports) {
                BLog.e(TAG, th, "Maximum pending Java crash threshold reached, Not storing count=%d", Integer.valueOf(roughlyCountPendingReportsOfType));
                return;
            }
            BLog.i(TAG, "Current pending Java crash report count = %d", Integer.valueOf(roughlyCountPendingReportsOfType));
        }
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            try {
                uncaughtExceptionImpl(thread, th, false);
            } catch (Throwable th2) {
                tryLogInternalError(th2);
            }
        }
    }

    static void trimStackBuffer(StringBuffer stringBuffer, int i) {
        int i2;
        int lastIndexOf;
        int indexOf;
        if (stringBuffer.length() > i && (lastIndexOf = stringBuffer.lastIndexOf("\n", (i2 = i / 2))) >= 0 && (indexOf = stringBuffer.indexOf("\n", stringBuffer.length() - i2)) >= 0) {
            stringBuffer.replace(lastIndexOf, indexOf, STACK_TRIMMED_MESSAGE);
        }
    }

    static String throwableToString(Throwable th) {
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

    static void put(String str, String str2, CrashReportData crashReportData, @Nullable Writer writer) {
        if (crashReportData.generatingIoError != null) {
            writer = null;
        }
        try {
            crashReportData.put(str, str2, writer);
        } catch (IOException e) {
            crashReportData.generatingIoError = e;
        }
    }

    public void writeReportToStream(Throwable th, OutputStream outputStream) throws Exception {
        CrashReportData crashReportData = new CrashReportData();
        Writer writer = CrashReportData.getWriter(outputStream);
        String throwableToString = throwableToString(th);
        crashReportData.put(ReportField.REPORT_ID, CrashTimeDataCollectorHelper.generateReportUuid().toString(), writer);
        CrashTimeDataCollector.gatherCrashData(this, this.mConfig, throwableToString, th, crashReportData, writer, null, true, false);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable th) {
        return handleException(th, (CrashReportData) null);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable th, @Nullable CrashReportData crashReportData) {
        return handleExceptionInternal(th, crashReportData, null, 1);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable th, @Nullable Map<String, String> map) {
        return handleException(th, map != null ? new CrashReportData(map) : null);
    }

    public void handleExceptionDelayed(Throwable th, @Nullable CrashReportData crashReportData) {
        handleExceptionInternal(th, crashReportData, null, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void safeClose(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                tryLogInternalError("safeClose", th);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x01d6 A[Catch:{ all -> 0x020e }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0244 A[SYNTHETIC, Splitter:B:146:0x0244] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x026e A[Catch:{ all -> 0x02e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0276 A[Catch:{ all -> 0x02e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x029b  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f A[SYNTHETIC, Splitter:B:36:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e1 A[SYNTHETIC, Splitter:B:58:0x00e1] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fb A[SYNTHETIC, Splitter:B:68:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0111 A[SYNTHETIC, Splitter:B:79:0x0111] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x013b A[Catch:{ all -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x014e A[Catch:{ all -> 0x0219 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0159 A[SYNTHETIC, Splitter:B:92:0x0159] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0162 A[SYNTHETIC, Splitter:B:98:0x0162] */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.ErrorReporter.ReportsSenderWorker handleExceptionInternal(java.lang.Throwable r24, com.facebook.acra.CrashReportData r25, @javax.annotation.Nullable java.lang.String r26, int r27) {
        /*
        // Method dump skipped, instructions count: 774
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.handleExceptionInternal(java.lang.Throwable, com.facebook.acra.CrashReportData, java.lang.String, int):com.facebook.acra.ErrorReporter$ReportsSenderWorker");
    }

    private static boolean objectsEqual(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void setCrashReportedObserver(CrashReportedObserver crashReportedObserver) {
        this.mCrashReportedObserver.set(crashReportedObserver);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable th, String str, @Nullable CrashReportData crashReportData) {
        return handleExceptionInternal(th, crashReportData, str, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendCrashReport(CrashReportData crashReportData) throws ReportSenderException {
        ArrayList arrayList;
        if (!this.mConfig.isZeroCrashlogBlocked()) {
            if (this.mConfig.shouldOnlyWriteReport()) {
                BLog.i(ACRA.LOG_TAG, "Writing report to file");
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
                            BLog.w(ACRA.LOG_TAG, e, "ReportSender of class %s failed but other senders completed their task. ACRA will not send this report again.", reportSender.getClass().getName());
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
    }

    private boolean writeJsonReport(CrashReportData crashReportData) {
        String str;
        SecureRandom secureRandom = new SecureRandom();
        BLog.d(ACRA.LOG_TAG, "Writing report");
        try {
            str = new UUID(secureRandom.nextLong() ^ this.mAppStartTickTimeMs, secureRandom.nextLong() ^ (this.mUserId == null ? 0 : Long.parseLong(this.mUserId))).toString();
        } catch (NumberFormatException unused) {
            str = UUID.randomUUID().toString();
        }
        File dir = this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0);
        File file = new File(dir, str + ".gz");
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, InputStreamField> entry : crashReportData.getInputStreamFields().entrySet()) {
            if (!entry.getKey().equals(ReportField.MINIDUMP)) {
                BLog.d(ACRA.LOG_TAG, "Ignoring field %s", entry.getKey());
            } else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (!JsonReportWriter.writeGzipJsonReport(crashReportData, hashMap, file)) {
            return false;
        }
        BLog.d(TAG, "Wrote file for uploader %s", file.getName());
        return true;
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
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(cls.getSimpleName());
        if (this.mAppVersionCode != null) {
            str2 = HelpFormatter.DEFAULT_OPT_PREFIX + this.mAppVersionCode;
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(str);
        return sb.toString();
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

    public void deletePartialANRReports() {
        synchronized (ANR_REPORTING_LOCK) {
            purgeDirectory(this.mContext.getDir(SIGQUIT_DIR, 0), REPORTFILE_EXTENSION);
        }
    }

    public void prepareANRReport(File file, FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        maybeRemoveAnrReports();
        synchronized (ANR_REPORTING_LOCK) {
            buildCachedCrashReport(CrashReportType.ANR_REPORT, null, file, fileGenerator);
        }
    }

    public void prepareANRReport(FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        synchronized (ANR_REPORTING_LOCK) {
            prepareReports(Integer.MAX_VALUE, fileGenerator, false, CrashReportType.ANR_REPORT);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003b, code lost:
        if (r10 == null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        r0.put(com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT.attachmentField, com.facebook.acra.util.AttachmentUtil.compressToBase64String(r10.getBytes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        r10 = new java.io.File(r11);
        r7 = r10.length();
        r2 = new java.io.FileInputStream(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r5 = new java.io.BufferedInputStream(r2);
        r11 = readSigquitFileHeader(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0068, code lost:
        if (r11.commandLine == null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006a, code lost:
        r0.put(com.facebook.acra.constants.ReportField.PROCESS_NAME, r11.commandLine);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0077, code lost:
        if (android.text.TextUtils.isEmpty(r11.versionCode) != false) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0079, code lost:
        r0.put(com.facebook.acra.constants.ReportField.APP_VERSION_CODE, r11.versionCode);
        r0.put(com.facebook.acra.constants.ReportField.APP_VERSION_NAME, r11.versionName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0087, code lost:
        slurpAttachment(r0, r5, com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008e, code lost:
        r2.close();
        deleteFile(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0094, code lost:
        r0.store(r1, (java.lang.String) null);
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r1 = new java.io.FileOutputStream(new java.io.File(r1 + com.facebook.acra.ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION), true);
        r0 = new com.facebook.acra.CrashReportData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void amendANRReportWithSigquitData(java.lang.String r10, java.lang.String r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 164
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.amendANRReportWithSigquitData(java.lang.String, java.lang.String):void");
    }

    public int prepareReports(int i, FileGenerator fileGenerator, boolean z, CrashReportType... crashReportTypeArr) {
        int i2;
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - start");
        discardOverlappingReports(crashReportTypeArr);
        int i3 = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            int max = Math.max(0, i - i3);
            if (crashReportType.getHandler() != null) {
                i2 = checkAndHandleReportsLocked(max, crashReportType, false);
            } else {
                i2 = processCrashAttachmentsLocked(max, crashReportType, fileGenerator, z);
            }
            i3 += i2;
        }
        if (!shouldSkipReport(CrashReportType.NATIVE_CRASH_REPORT)) {
            NativeCrashDumpReporterUtil.cleanupHeapDump(this.mContext);
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - finish");
        return i3;
    }

    public int prepareANRReport(String str, FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        return buildCachedCrashReport(CrashReportType.ANR_REPORT, str, null, fileGenerator);
    }

    public int prepareCachedANRReports(int i) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        maybeRemoveAnrReports();
        return checkAndHandleReportsLocked(i, CrashReportType.CACHED_ANR_REPORT, true);
    }

    @Nullable
    public String getLogcatOutputIfPidFound(boolean z, @Nullable Integer num) {
        String collectLogCat = LogCatCollector.collectLogCat(this.mContext, this.mConfig, null, null, false, z, this.mConfig.allowCollectionOfMaxNumberOfLinesInLogcat());
        if (collectLogCat == null) {
            return null;
        }
        if (num == null) {
            return collectLogCat;
        }
        if (Pattern.compile("^\\d+-\\d+\\s+\\d+:\\d+:\\d+\\.\\d+\\s+" + num.toString() + "\\s+\\d+\\s+[A-Z]\\s+(.*?)$", 8).matcher(collectLogCat).find()) {
            return collectLogCat;
        }
        return null;
    }

    @Nullable
    public String getEventsLog() {
        if (Build.VERSION.SDK_INT >= 19) {
            return null;
        }
        return LogCatCollector.collectLogCat(this.mContext, this.mConfig, "events", null, false, true, this.mConfig.allowCollectionOfMaxNumberOfLinesInLogcat());
    }

    private int checkAndHandleReportsLocked(int i, CrashReportType crashReportType, boolean z) {
        boolean z2;
        if (crashReportType.getHandler() != null) {
            String processNameFromAms = CrashTimeDataCollector.getProcessNameFromAms(this.mContext);
            Spool.Snapshot pendingCrashReports = crashReportType.getPendingCrashReports(this.mContext);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                try {
                    if (!pendingCrashReports.hasNext() || i2 >= i) {
                        break;
                    }
                    Spool.FileBeingConsumed next = pendingCrashReports.next();
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
                                if (!this.mAnrFilesInProgress.contains(str)) {
                                    z2 = false;
                                } else if (!z) {
                                    this.mAnrFilesInProgress.remove(str);
                                    z2 = true;
                                } else if (next == null) {
                                    i3 = i4;
                                }
                            }
                        } else {
                            z2 = false;
                        }
                        if (crashReportType.getHandler().handleReport(this, next, processNameFromAms, z2)) {
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
            pendingCrashReports.close();
            return i2;
        }
        throw new NullPointerException("ErrorReporter::checkAndHandleReportsLocked report type requires a handler");
        throw th;
        throw th;
    }

    /* access modifiers changed from: private */
    public class CrashAttachmentException extends Throwable {
        private CrashAttachmentException() {
        }
    }

    /* access modifiers changed from: private */
    public static final class FifoSpoolComparator implements Comparator<Spool.Descriptor> {
        private FifoSpoolComparator() {
        }

        public int compare(Spool.Descriptor descriptor, Spool.Descriptor descriptor2) {
            if (descriptor.lastModifiedTime == descriptor2.lastModifiedTime) {
                return 0;
            }
            return descriptor.lastModifiedTime < descriptor2.lastModifiedTime ? -1 : 1;
        }
    }

    private void buildAttachmentWrapperCrashReport(CrashReportData crashReportData, CrashReportType crashReportType, @Nullable Spool.FileBeingConsumed fileBeingConsumed, @Nullable Writer writer, boolean z) {
        try {
            crashReportData.put(ReportField.ACRA_REPORT_TYPE, crashReportType.name(), writer);
            CrashTimeDataCollector.gatherCrashData(this, this.mConfig, CRASH_ATTACHMENT_DUMMY_STACKTRACE, new CrashAttachmentException(), crashReportData, writer, crashReportType == CrashReportType.NATIVE_CRASH_REPORT ? fileBeingConsumed : null, z, false);
        } catch (Throwable th) {
            put(ReportField.REPORT_LOAD_THROW, "retrieve exception: " + th.getMessage(), crashReportData, writer);
        }
    }

    private boolean shouldSkipReport(CrashReportType crashReportType) {
        return new File(this.mContext.getDir(crashReportType.directory, 0), ".noupload").exists();
    }

    @Nullable
    private File createBackUpDirectory(CrashReportType crashReportType) {
        File file;
        NullPointerException e;
        try {
            file = new File(this.mContext.getDir(crashReportType.directory, 0).getParent(), REPORTED_CRASH_DIR);
            try {
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (NullPointerException e2) {
                e = e2;
                BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
                return file;
            }
        } catch (NullPointerException e3) {
            e = e3;
            file = null;
            BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
            return file;
        }
        return file;
    }

    private int keepNLatestDumpFiles(@Nullable File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return 0;
        }
        Arrays.sort(listFiles, new Comparator<File>() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass2 */

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

    public void writeLibsToFile(File file, @Nullable HashSet<String> hashSet) {
        Throwable th;
        IOException e;
        if (hashSet != null && !hashSet.isEmpty()) {
            Closeable closeable = null;
            try {
                sSystemLibFileLock.writeLock().lock();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true), hashSet.size());
                try {
                    Iterator<String> it = hashSet.iterator();
                    while (it.hasNext()) {
                        bufferedWriter.write(it.next() + "\n");
                    }
                    safeClose(bufferedWriter);
                } catch (IOException e2) {
                    e = e2;
                    closeable = bufferedWriter;
                    try {
                        BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
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
                BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
                safeClose(closeable);
                sSystemLibFileLock.writeLock().unlock();
            }
            sSystemLibFileLock.writeLock().unlock();
        }
    }

    @Nullable
    public HashSet<String> getNewLibs(File file, @Nullable HashSet<String> hashSet) {
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

    public static File getCrashDumpSysLibPath(Context context) {
        return context.getFileStreamPath(CRASH_DUMP_SYS_LIBS_FILE);
    }

    public void updateGLCwithSystemLibs(Spool.FileBeingConsumed fileBeingConsumed) {
        File crashDumpSysLibPath = getCrashDumpSysLibPath(this.mContext);
        if (!crashDumpSysLibPath.exists()) {
            try {
                crashDumpSysLibPath.createNewFile();
            } catch (IOException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to create GLC Lib file");
                return;
            }
        }
        try {
            writeLibsToFile(crashDumpSysLibPath, getNewLibs(crashDumpSysLibPath, new MinidumpReader(fileBeingConsumed.file).getModuleList()));
        } catch (IOException e2) {
            BLog.e(ACRA.LOG_TAG, e2, "Failed to create GLC Lib file");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        if (r15 != null) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00da A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int processCrashAttachmentsLocked(int r18, com.facebook.acra.ErrorReporter.CrashReportType r19, @javax.annotation.Nullable com.facebook.acra.FileGenerator r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 329
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.processCrashAttachmentsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, com.facebook.acra.FileGenerator, boolean):int");
    }

    @Nullable
    private static String getLogcatFileName(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType) {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                return new MinidumpReader(fileBeingConsumed.file).getCustomData(ACRA.LOGCAT_FILE_KEY);
            } catch (IOException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to find logcat file %s", fileBeingConsumed.fileName);
            }
        }
        return null;
    }

    private void processCrashAttachmentBeforeSend(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, CrashReportData crashReportData, boolean z) throws IOException {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            if (!z) {
                NativeCrashDumpReporterUtil.processHeapDump(this.mContext, crashReportData, fileBeingConsumed, this.mConfig);
            }
            foregroundNativeCrashDetect(fileBeingConsumed);
            MinidumpReader minidumpReader = new MinidumpReader(fileBeingConsumed.file);
            String customData = minidumpReader.getCustomData("session_id");
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

    private void processCrashAttachmentAfterSend(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, @Nullable File file, @Nullable String str) throws IOException {
        if (crashReportType != CrashReportType.NATIVE_CRASH_REPORT || file == null || !file.exists()) {
            BLog.d(ACRA.LOG_TAG, "No need to backup. Deleting minidump %s", fileBeingConsumed.fileName.getCanonicalPath());
            deleteFile(fileBeingConsumed.fileName);
        } else {
            File file2 = new File(file, fileBeingConsumed.fileName.getName());
            file2.delete();
            fileBeingConsumed.fileName.renameTo(file2);
            BLog.d(ACRA.LOG_TAG, "Backup minidump file from %s to %s", fileBeingConsumed.fileName.getCanonicalPath(), file2.getCanonicalPath());
        }
        if (str != null) {
            deleteFile(new File(str));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r11 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        deleteFile(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
        if (r11 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009e, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildCachedCrashReport(com.facebook.acra.ErrorReporter.CrashReportType r9, @javax.annotation.Nullable java.lang.String r10, @javax.annotation.Nullable java.io.File r11, com.facebook.acra.FileGenerator r12) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.buildCachedCrashReport(com.facebook.acra.ErrorReporter$CrashReportType, java.lang.String, java.io.File, com.facebook.acra.FileGenerator):int");
    }

    private int maybeSendCrashReport(CrashReportType crashReportType, CrashReportData crashReportData, @Nullable Spool.FileBeingConsumed fileBeingConsumed, @Nullable FileGenerator fileGenerator, boolean z) throws Throwable {
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
                    } catch (IOException e) {
                        BLog.e(ACRA.LOG_TAG, e, "IO Exception");
                        reportSoftError(e, "ANRValidationError<IOException::Non-cached>", e.toString(), this);
                    }
                }
                throw th;
            }
        } else {
            str = fileBeingConsumed.fileName.getName();
        }
        CrashReportData crashReportData2 = new CrashReportData();
        if (crashReportType.attachmentField != null) {
            crashReportData2.put(crashReportType.attachmentField, (String) crashReportData.get(crashReportType.attachmentField), writer);
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
            crashReportData2.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, ActivityConstants.Extras.WATCH_FEED_INJECTION, writer);
        }
        crashReportData2.put(ReportField.REPORT_ID, str.substring(0, str.lastIndexOf(46)), writer);
        crashReportData2.merge((Map<String, String>) crashReportData, false, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.APP_VERSION_CODE, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.APP_VERSION_NAME, writer);
        crashReportData2.mergeFieldOverwrite(crashReportData, ReportField.SESSION_ID, writer);
        crashReportData2.put(ReportField.EXCEPTION_CAUSE, CRASH_ATTACHMENT_DUMMY_STACKTRACE, writer);
        if (writer == null) {
            sendCrashReport(crashReportData2);
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "IO Exception");
                reportSoftError(e2, "ANRValidationError<IOException::Non-cached>", e2.toString(), this);
            }
        }
        return 1;
    }

    private void closeInputStreamFields(CrashReportData crashReportData) {
        for (Map.Entry<String, InputStreamField> entry : crashReportData.getInputStreamFields().entrySet()) {
            InputStreamField value = entry.getValue();
            if (!(value == null || value.getInputStream() == null)) {
                try {
                    value.getInputStream().close();
                } catch (IOException unused) {
                }
            }
        }
    }

    private void foregroundNativeCrashDetect(Spool.FileBeingConsumed fileBeingConsumed) {
        try {
            MinidumpReader minidumpReader = new MinidumpReader(fileBeingConsumed.file);
            String customData = minidumpReader.getCustomData("APP_STARTED_IN_BACKGROUND");
            boolean z = customData != null && customData.equals("false");
            String string = minidumpReader.getString(MinidumpReader.MD_FB_APP_STATE_LOG);
            if ((string != null && string.indexOf("Resumed") != -1) || (z && string != null && string.indexOf("\"activities\":[]") != -1)) {
                this.mContext.getSharedPreferences("FacebookApplication", 0).edit().putLong("crash_foreground_timestamp", fileBeingConsumed.fileName.lastModified()).commit();
            }
        } catch (Exception e) {
            writeToLogBridge(ACRA.LOG_TAG, "Error writing into the SharedPreferences", e, null);
        }
    }

    public void setANRDataProvider(ANRDataProvider aNRDataProvider) {
        this.mANRDataProvider = aNRDataProvider;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean shouldReportANRs() {
        ANRDataProvider aNRDataProvider = this.mANRDataProvider;
        return aNRDataProvider != null && aNRDataProvider.shouldANRDetectorRun();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private CrashReportData loadAcraCrashReport(Spool.FileBeingConsumed fileBeingConsumed) throws IOException {
        return loadCrashReport(fileBeingConsumed, CrashReportType.ACRA_CRASH_REPORT, this.mMaxReportSize, true);
    }

    private CrashReportData loadCrashAttachment(File file, CrashReportType crashReportType, boolean z) throws IOException {
        return loadCrashReport(file, null, crashReportType, crashReportType.defaultMaxSize, z);
    }

    private CrashReportData loadCrashAttachment(Spool.FileBeingConsumed fileBeingConsumed, CrashReportType crashReportType, boolean z) throws IOException {
        return loadCrashReport(fileBeingConsumed, crashReportType, crashReportType.defaultMaxSize, z);
    }

    private void attachLastActivityInfo(CrashReportData crashReportData) throws IOException {
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

    private void attachIabInfo(CrashReportData crashReportData) throws IOException {
        File file = new File(this.mContext.getFilesDir(), FILE_IAB_OPEN_TIMES);
        String readFile = readFile(file);
        if (NO_FILE.equals(readFile)) {
            crashReportData.put(ReportField.IAB_OPEN_TIMES, OculusConstants.DEFAULT_ENTERPRISE_USER_ID);
        } else if (readFile != null) {
            crashReportData.put(ReportField.IAB_OPEN_TIMES, readFile);
        }
        if (file.exists()) {
            file.delete();
        }
    }

    private void slurpAttachment(CrashReportData crashReportData, InputStream inputStream, CrashReportType crashReportType, long j) throws IOException {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                attachLastActivityInfo(crashReportData);
            } catch (IOException e) {
                BLog.w(TAG, e, "error attaching activity information");
            }
            try {
                attachIabInfo(crashReportData);
            } catch (IOException e2) {
                BLog.w(TAG, e2, "error attaching IAB information");
            }
        }
        crashReportData.put(crashReportType.attachmentField, AttachmentUtil.loadAttachment(inputStream, (int) j));
        crashReportData.put(ReportField.ATTACHMENT_ORIGINAL_SIZE, String.valueOf(j));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009a A[Catch:{ IOException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f1  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.acra.CrashReportData loadCrashReport(java.io.File r18, @javax.annotation.Nullable java.io.RandomAccessFile r19, com.facebook.acra.ErrorReporter.CrashReportType r20, long r21, boolean r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 420
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.loadCrashReport(java.io.File, java.io.RandomAccessFile, com.facebook.acra.ErrorReporter$CrashReportType, long, boolean):com.facebook.acra.CrashReportData");
    }

    private static SigquitFileHeader readSigquitFileHeader(BufferedInputStream bufferedInputStream) throws IOException {
        String str;
        String readVersionLine = readVersionLine(bufferedInputStream);
        String readVersionLine2 = !TextUtils.isEmpty(readVersionLine) ? readVersionLine(bufferedInputStream) : null;
        if (bufferedInputStream.markSupported()) {
            bufferedInputStream.mark(1024);
            byte[] bArr = new byte[1024];
            int read = bufferedInputStream.read(bArr, 0, 1024);
            if (read > 0) {
                Matcher matcher = mSigquitCmdLinePattern.matcher(new String(bArr, 0, read));
                if (matcher.find()) {
                    str = matcher.group(1);
                    bufferedInputStream.reset();
                }
            }
            str = null;
            bufferedInputStream.reset();
        } else {
            str = null;
        }
        SigquitFileHeader sigquitFileHeader = new SigquitFileHeader();
        sigquitFileHeader.versionCode = readVersionLine;
        sigquitFileHeader.versionName = readVersionLine2;
        sigquitFileHeader.commandLine = str;
        return sigquitFileHeader;
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
        crashReportData.put(ReportField.TIME_OF_CRASH, Long.toString(System.currentTimeMillis()));
        try {
            crashReportData.put(crashReportType.attachmentField, AttachmentUtil.compressToBase64String(str.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            crashReportData.put(ReportField.REPORT_LOAD_THROW, "throwable: " + e.getMessage());
            BLog.e(ACRA.LOG_TAG, e, "Could not load crash report. File will be deleted.");
        }
        backfillCrashReportData(crashReportData);
        return crashReportData;
    }

    @Nullable
    private static String readFile(File file) {
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

    /* access modifiers changed from: package-private */
    public void backfillCrashReportData(CrashReportData crashReportData) {
        String str = (String) crashReportData.get(ReportField.REPORT_ID);
        if (str == null || str.length() == 0) {
            for (Map.Entry<String, String> entry : this.mConstantFields.entrySet()) {
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void startUploadIfReady() {
        if (this.mConfig.shouldUseUploadService() && this.mInitializationComplete && this.mBatchUploader != null && this.mFinishedCheckingReports && this.mPendingReportWriters <= 0 && !this.mConfig.shouldOnlyWriteReport()) {
            if (!this.mStartedBatchUploader) {
                final File[] listFiles = this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0).listFiles();
                this.mStartedBatchUploader = true;
                new Thread(new Runnable() {
                    /* class com.facebook.acra.ErrorReporter.AnonymousClass3 */

                    public void run() {
                        ErrorReporter.this.mBatchUploader.uploadReports(listFiles);
                    }
                }).start();
            }
        }
    }

    @ThreadSafe(enableChecks = false)
    public void checkNativeReports() {
        if (roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT) > 0) {
            checkReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        }
    }

    private void checkNativeReportsOnApplicationStart() {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        int maxPendingMiniDumpReports = this.mConfig.getMaxPendingMiniDumpReports(roughlyCountPendingReportsOfType);
        if (maxPendingMiniDumpReports < Integer.MAX_VALUE && roughlyCountPendingReportsOfType > maxPendingMiniDumpReports) {
            BLog.w(TAG, "Minidump count above threshold %d", Integer.valueOf(roughlyCountPendingReportsOfType));
            removeCrashFiles(CrashReportType.NATIVE_CRASH_REPORT, roughlyCountPendingReportsOfType - maxPendingMiniDumpReports);
        }
        if (roughlyCountPendingReportsOfType > 5) {
            ReportsSenderWorker reportsSenderWorker = new ReportsSenderWorker(this, CrashReportType.NATIVE_CRASH_REPORT);
            Object obj = null;
            if (Build.VERSION.SDK_INT >= 9) {
                obj = Api9Utils.saveStrictMode();
                Api9Utils.disableStrictMode();
            }
            try {
                reportsSenderWorker.doSend();
                if (obj == null) {
                    return;
                }
            } catch (Throwable th) {
                if (obj != null) {
                    Api9Utils.restoreStrictMode(obj);
                }
                throw th;
            }
            Api9Utils.restoreStrictMode(obj);
        }
    }

    public int roughlyCountPendingReportsOfType(CrashReportType... crashReportTypeArr) {
        if (this.mContext == null) {
            BLog.e(ACRA.LOG_TAG, "Trying to get ACRA reports but ACRA is not initialized.");
            return 0;
        }
        int i = 0;
        for (CrashReportType crashReportType : crashReportTypeArr) {
            i += crashReportType.getPendingCrashReports(this.mContext).getEstimate();
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        android.util.Log.e(com.facebook.acra.ACRA.LOG_TAG, "interrupted while waiting for error reports to upload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        com.facebook.acra.StartTimeBlockedRecorder.setDurationStartupBlocked(android.os.SystemClock.uptimeMillis() - r2);
        com.facebook.acra.StartTimeBlockedRecorder.setTotalCrashesUploaded(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0075, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0057 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.acra.ErrorReporter.ReportsSenderWorker checkReportsOfType(com.facebook.acra.ErrorReporter.CrashReportType... r9) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.checkReportsOfType(com.facebook.acra.ErrorReporter$CrashReportType[]):com.facebook.acra.ErrorReporter$ReportsSenderWorker");
    }

    @SuppressLint({"ImprovedNewApi", "CatchGeneralException", "DeprecatedMethod"})
    private void showBlockingNotification(StartupBlockingConfig startupBlockingConfig) {
        try {
            Notification.Builder smallIcon = new Notification.Builder(this.mContext).setContentTitle(this.mContext.getString(startupBlockingConfig.notificationTitle)).setContentText(this.mContext.getString(startupBlockingConfig.notificationText)).setSmallIcon(17301543);
            if (Build.VERSION.SDK_INT >= 16) {
                Api16Utils.applyBigTextStyle(smallIcon, this.mContext.getString(startupBlockingConfig.notificationText));
            }
            ((NotificationManager) this.mContext.getSystemService("notification")).notify(2, smallIcon.getNotification());
        } catch (Throwable th) {
            Log.d(ACRA.LOG_TAG, "error showing notification", th);
        }
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    public static final class Api16Utils {
        private Api16Utils() {
        }

        @TargetApi(16)
        static void applyBigTextStyle(Notification.Builder builder, String str) {
            builder.setStyle(new Notification.BigTextStyle().bigText(str));
        }
    }

    private void cancelBlockingNotification() {
        ((NotificationManager) this.mContext.getSystemService("notification")).cancel(2);
    }

    public void addReportSender(ReportSender reportSender) {
        synchronized (this.mReportSenders) {
            this.mReportSenders.add(reportSender);
        }
    }

    public void removeAllReportSenders() {
        synchronized (this.mReportSenders) {
            this.mReportSenders.clear();
        }
    }

    public void setMaxReportSize(long j) {
        this.mMaxReportSize = j;
    }

    public void setReportSender(ReportSender reportSender) {
        synchronized (this.mReportSenders) {
            removeAllReportSenders();
            addReportSender(reportSender);
        }
    }

    public void setReportProxy(Proxy proxy) {
        synchronized (this.mReportSenders) {
            Iterator<ReportSender> it = this.mReportSenders.iterator();
            while (it.hasNext()) {
                ReportSender next = it.next();
                if (next instanceof FlexibleReportSender) {
                    ((FlexibleReportSender) next).setProxy(proxy);
                }
            }
        }
    }

    public void registerActivity(String str) {
        if (str != null) {
            this.mActivityLogger.append(str);
        }
    }

    static Throwable getMostSignificantCause(Throwable th) {
        if (th instanceof NonCrashException) {
            return th;
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    @Nullable
    public LogBridge getLogBridge() {
        return this.mLogBridge;
    }

    public void setLogBridge(@Nullable LogBridge logBridge) {
        this.mLogBridge = logBridge;
    }

    private void writeToLogBridge(String str, String str2, Throwable th, @Nullable String str3) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[0];
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (StackTraceElement stackTraceElement2 : stackTrace) {
            if (stackTraceElement2.getClassName().equals(stackTraceElement.getClassName()) && stackTraceElement2.getMethodName().equals(stackTraceElement.getMethodName())) {
                BLog.e(TAG, th, "Unable to log over log bridge due to exception.");
                return;
            }
        }
        LogBridge logBridge = getLogBridge();
        if (logBridge != null) {
            if (str3 != null) {
                logBridge.log(str, str2 + "\n" + str3, null);
                return;
            }
            logBridge.log(str, str2, th);
        } else if (str3 != null) {
            BLog.e(str, "%s\n%s", str2, str3);
        } else {
            BLog.e(str, th, "%s", str2);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private void removeCrashFiles(final CrashReportType crashReportType, int i) {
        if (i > 0) {
            try {
                BLog.i(TAG, "removeCrashFiles count=%d", Integer.valueOf(i));
                Spool.Snapshot snapshot = crashReportType.getSpool(this.mContext).snapshot(new FifoSpoolComparator(), new FilenameFilter() {
                    /* class com.facebook.acra.ErrorReporter.AnonymousClass4 */

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
                    BLog.w(TAG, "removeCrashFiles no snapshot");
                    return;
                }
                while (snapshot.hasNext() && i > 0) {
                    Spool.FileBeingConsumed next = snapshot.next();
                    if (next.fileName != null) {
                        if (crashReportType.extraFileExtensions != null) {
                            for (String str : crashReportType.extraFileExtensions) {
                                new File(next.fileName.getCanonicalPath() + str).delete();
                            }
                        }
                        if (!next.fileName.delete()) {
                            BLog.w(TAG, "removeCrashFiles Crash file not deleted %s", next.fileName.getAbsolutePath());
                        } else {
                            i--;
                        }
                    }
                }
            } catch (Throwable th) {
                BLog.e(TAG, th, "removeCrashFiles");
            }
        }
    }

    private void maybeRemoveAnrReports() {
        int roughlyCountPendingReportsOfType = roughlyCountPendingReportsOfType(CrashReportType.CACHED_ANR_REPORT);
        int maxPendingAnrReports = this.mConfig.getMaxPendingAnrReports(roughlyCountPendingReportsOfType);
        if (maxPendingAnrReports < Integer.MAX_VALUE && roughlyCountPendingReportsOfType > maxPendingAnrReports) {
            BLog.w(TAG, "Anr count %d above threshold %d", Integer.valueOf(roughlyCountPendingReportsOfType), Integer.valueOf(maxPendingAnrReports));
            removeCrashFiles(CrashReportType.CACHED_ANR_REPORT, roughlyCountPendingReportsOfType - maxPendingAnrReports);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(9)
    @DoNotOptimize
    public static final class Api9Utils {
        private Api9Utils() {
        }

        static Object saveStrictMode() {
            return StrictMode.getThreadPolicy();
        }

        static void restoreStrictMode(Object obj) {
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) obj);
        }

        static void disableStrictMode() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
    }

    static class DefaultSecureSettingsResolver implements SecureSettingsResolver {
        DefaultSecureSettingsResolver() {
        }

        @Override // com.facebook.acra.ErrorReporter.SecureSettingsResolver
        public String getString(ContentResolver contentResolver, String str) {
            return Settings.Secure.getString(contentResolver, str);
        }
    }
}
