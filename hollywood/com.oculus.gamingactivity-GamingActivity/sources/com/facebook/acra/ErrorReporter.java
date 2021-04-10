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
import android.os.SystemClock;
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
import com.facebook.acra.sender.FlexibleReportSender;
import com.facebook.acra.sender.HttpPostSender;
import com.facebook.acra.sender.ReportSender;
import com.facebook.acra.sender.ReportSenderException;
import com.facebook.acra.util.AttachmentUtil;
import com.facebook.acra.util.CrashTimeDataCollectorHelper;
import com.facebook.acra.util.InputStreamField;
import com.facebook.acra.util.JsonReportWriter;
import com.facebook.acra.util.PackageManagerWrapper;
import com.facebook.acra.util.SimpleTraceLogger;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.acraconfig.AcraConfig;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.exceptionhandler.CustomStackTracerInterface;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.common.exceptionhandler.ManagedExceptionHandler;
import com.facebook.debug.log.BLog;
import com.facebook.errorreporting.appstate.blackbox.BlackBoxRecorderControl;
import com.facebook.errorreporting.appstate.blackbox.BlackBoxState;
import com.facebook.infer.annotation.ThreadSafe;
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
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;
import java.net.Proxy;
import java.nio.channels.FileChannel;
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

    static /* synthetic */ int access$908(ErrorReporter x0) {
        int i = x0.mPendingReportWriters;
        x0.mPendingReportWriters = i + 1;
        return i;
    }

    static /* synthetic */ int access$910(ErrorReporter x0) {
        int i = x0.mPendingReportWriters;
        x0.mPendingReportWriters = i - 1;
        return i;
    }

    public synchronized void setBatchUploader(BatchUploader batchUploader) {
        if (this.mConfig != null && this.mConfig.shouldUseUploadService()) {
            this.mBatchUploader = batchUploader;
            startUploadIfReady();
        }
    }

    public long getAppStartTickTimeMs() {
        return this.mAppStartTickTimeMs;
    }

    public void setAppStartTickTimeMs(long appStartTickTimeMs) {
        this.mAppStartTickTimeMs = appStartTickTimeMs;
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

    private void reportSoftError(Throwable throwable, String category, String message, ErrorReporter reporter) {
        reportSoftError(throwable, category, message, this.mConfig == null ? null : this.mConfig.getSessionId(), reporter);
    }

    private static void reportSoftError(Throwable throwable, String category, String message, @Nullable String sessionId, ErrorReporter reporter) {
        CrashReportData draft = new CrashReportData();
        draft.put(ErrorReportingConstants.SOFT_ERROR_CATEGORY, category);
        draft.put(ErrorReportingConstants.SOFT_ERROR_MESSAGE, message);
        if (sessionId != null) {
            draft.put(ReportField.SESSION_ID, sessionId);
        }
        reporter.handleException(throwable, draft);
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
        public boolean handleReport(ErrorReporter reporter, Spool.FileBeingConsumed dumpFile, String uploadedByProcess, boolean inProgress) {
            File curFileName = dumpFile.fileName;
            String curFileNameString = curFileName.getName();
            BLog.d(ACRA.LOG_TAG, "Loading file %s", curFileNameString);
            try {
                CrashReportData crashReport = reporter.loadAcraCrashReport(dumpFile);
                if (crashReport == null) {
                    return true;
                }
                crashReport.put(ReportField.ACRA_REPORT_TYPE, CrashReportType.ACRA_CRASH_REPORT.name());
                crashReport.put(ReportField.ACRA_REPORT_FILENAME, curFileNameString);
                crashReport.put(ReportField.UPLOADED_BY_PROCESS, uploadedByProcess);
                BLog.i(ACRA.LOG_TAG, "Sending file %s", curFileNameString);
                reporter.sendCrashReport(crashReport);
                ErrorReporter.deleteFile(curFileName);
                return true;
            } catch (RuntimeException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to send crash reports");
                ErrorReporter.deleteFile(curFileName);
                return false;
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "Failed to load crash report for %s", curFileNameString);
                ErrorReporter.deleteFile(curFileName);
                return false;
            } catch (ReportSenderException e3) {
                BLog.e(ACRA.LOG_TAG, e3, "Failed to send crash report for %s", curFileNameString);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void purgeDirectory(File dir, @Nullable final String extension) {
        File[] files;
        FileFilter filter = null;
        if (extension != null) {
            filter = new FileFilter() {
                /* class com.facebook.acra.ErrorReporter.AnonymousClass1 */

                public boolean accept(File file) {
                    return file.getName().endsWith(extension);
                }
            };
        }
        if (!(dir == null || (files = dir.listFiles(filter)) == null)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    purgeDirectory(file, extension);
                }
                deleteFile(file);
            }
        }
    }

    @VisibleForTesting
    static class CachedANRReportHandler implements ReportHandler {
        CachedANRReportHandler() {
        }

        @Override // com.facebook.acra.ErrorReporter.ReportHandler
        public boolean handleReport(ErrorReporter reporter, Spool.FileBeingConsumed dumpFile, String uploadedByProcess, boolean inProgress) {
            if (!reporter.shouldReportANRs()) {
                ErrorReporter.purgeDirectory(reporter.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0), null);
                return true;
            }
            File curFileName = dumpFile.fileName;
            String curFileNameString = curFileName.getName();
            try {
                File updateFile = new File(curFileName.getCanonicalPath() + ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION);
                try {
                    InputStream inStream = new FileInputStream(curFileName);
                    try {
                        CrashReportData crashReport = new CrashReportData();
                        crashReport.load(inStream);
                        CrashReportData updatedData = new CrashReportData();
                        if (updateFile.exists()) {
                            InputStream is = new FileInputStream(updateFile);
                            try {
                                updatedData.load(is);
                                is.close();
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                        }
                        addUpdatedProperties(crashReport, updatedData, uploadedByProcess, updateFile);
                        crashReport.merge((Map<String, String>) updatedData, true, (Writer) null);
                        BLog.i(ACRA.LOG_TAG, "Sending file %s", curFileNameString);
                        if (inProgress) {
                            crashReport.put(ReportField.SENT_IMMEDIATELY, "true");
                        }
                        reporter.sendCrashReport(crashReport);
                        ErrorReporter.deleteFile(curFileName);
                        ErrorReporter.deleteFile(updateFile);
                        inStream.close();
                        return true;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } catch (RuntimeException e) {
                    BLog.e(ACRA.LOG_TAG, e, "Failed to send crash reports");
                    ErrorReporter.deleteFile(curFileName);
                    ErrorReporter.deleteFile(updateFile);
                    return false;
                } catch (IOException e2) {
                    BLog.e(ACRA.LOG_TAG, e2, "Failed to load crash report for %s", curFileNameString);
                    ErrorReporter.deleteFile(curFileName);
                    ErrorReporter.deleteFile(updateFile);
                    return false;
                } catch (ReportSenderException e3) {
                    BLog.e(ACRA.LOG_TAG, e3, "Failed to send crash report for %s", curFileNameString);
                    return false;
                }
            } catch (IOException e4) {
                BLog.e(ACRA.LOG_TAG, e4, "Failed to get full path of crash report for %s", curFileNameString);
                return false;
            }
            throw th;
            throw th;
        }

        private void addUpdatedProperties(CrashReportData crashReport, CrashReportData updatedData, String uploadedByProcess, File updateFile) throws IOException {
            String systemTag;
            String systemErrorMsg;
            String hasSigquit;
            Map<String, String> updates = new HashMap<>();
            int processTracePosition = -1;
            boolean triedToLookForTraces = false;
            if (ACRA.getCachedShouldUploadSystemANRTraces() && (((hasSigquit = (String) crashReport.get(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES)) == null || hasSigquit.equals("0")) && ((String) updatedData.get(ReportField.SIGQUIT)) == null)) {
                BLog.i(ACRA.LOG_TAG, "Looking for system ANR traces");
                StringBuilder sb = new StringBuilder();
                processTracePosition = findANRTraces(crashReport.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReport.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, sb);
                triedToLookForTraces = true;
                String anrTracesData = null;
                if (processTracePosition > -1) {
                    anrTracesData = sb.toString();
                }
                if (anrTracesData != null) {
                    BLog.i(ACRA.LOG_TAG, "Found and attaching system ANR traces");
                    updates.put(ReportField.SIGQUIT, AttachmentUtil.compressToBase64String(anrTracesData.getBytes()));
                    updates.put(ErrorReportingConstants.ANR_SYSTEM_TRACES_PRESENT, "true");
                } else {
                    updates.put(ErrorReportingConstants.ANR_SYSTEM_TRACES_PRESENT, "false");
                }
            }
            if (ACRA.getCachedShouldLogProcessPositionInAnrTraceFile()) {
                if (!triedToLookForTraces) {
                    processTracePosition = findANRTraces(crashReport.getProperty(ReportField.PROCESS_NAME), Long.parseLong(crashReport.getProperty(ReportField.TIME_OF_CRASH)), ErrorReporter.ANR_TRACES_FILE_PATH, null);
                }
                updates.put(ErrorReportingConstants.ANR_TRACE_POSITION, String.valueOf(processTracePosition));
            }
            updates.put(ReportField.UPLOADED_BY_PROCESS, uploadedByProcess);
            if (((String) updatedData.get(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG)) == null) {
                updates.put(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReporter.getCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG));
            }
            if (((String) updatedData.get(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG)) == null && (systemErrorMsg = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG)) != null) {
                updates.put(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, systemErrorMsg);
            }
            if (((String) updatedData.get(ErrorReportingConstants.ANR_SYSTEM_TAG)) == null && (systemTag = ErrorReporter.getCustomData(ErrorReportingConstants.ANR_SYSTEM_TAG)) != null) {
                updates.put(ErrorReportingConstants.ANR_SYSTEM_TAG, systemTag);
            }
            FileOutputStream updateFileStream = new FileOutputStream(updateFile, true);
            updatedData.putAll(updates, CrashReportData.getWriter(updateFileStream));
            updateFileStream.close();
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public int findANRTraces(String processName, long crashTime, String anrTracesFilePath, @Nullable StringBuilder sb) {
            if (processName == null) {
                return -1;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(anrTracesFilePath));
                int currentPos = 0;
                try {
                    Pattern anrTraceStartPattern = Pattern.compile("----- pid (\\d+) at (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}).* -----");
                    Pattern anrTraceEndPattern = Pattern.compile("----- end (\\d+) -----");
                    Pattern anrCmdLineProcessNamePattern = Pattern.compile("Cmd line: (.*)");
                    int startPid = -1;
                    boolean captureTrace = false;
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            br.close();
                            break;
                        } else if (captureTrace) {
                            sb.append(line).append("\n");
                            Matcher matcher = anrTraceEndPattern.matcher(line);
                            if (matcher.find()) {
                                if (startPid != Integer.parseInt(matcher.group(1))) {
                                    sb.setLength(0);
                                }
                                br.close();
                                return currentPos;
                            }
                        } else {
                            Matcher matcher2 = anrTraceStartPattern.matcher(line);
                            if (!matcher2.find()) {
                                continue;
                            } else {
                                if (sb != null) {
                                    sb.setLength(0);
                                }
                                startPid = Integer.parseInt(matcher2.group(1));
                                long epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(matcher2.group(2)).getTime();
                                String line2 = br.readLine();
                                Matcher matcher3 = anrCmdLineProcessNamePattern.matcher(line2);
                                if (matcher3.find()) {
                                    String currProcessName = matcher3.group(1);
                                    if (!currProcessName.equals(processName) || sb != null) {
                                        if (currProcessName.equals(processName) && Math.abs(crashTime - epoch) < 15000 && sb != null) {
                                            sb.append(line).append("\n");
                                            sb.append(line2).append("\n");
                                            captureTrace = true;
                                        }
                                        if (!captureTrace && !currProcessName.startsWith("/")) {
                                            currentPos++;
                                        }
                                    } else {
                                        br.close();
                                        return currentPos;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                    return -1;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } catch (ParseException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to parse ANR timestamp.");
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "Failed to read ANR traces.");
            } catch (NumberFormatException e3) {
                BLog.e(ACRA.LOG_TAG, e3, "Failed to extract pid from ANR traces.");
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

        private CrashReportType(String directory2, long maxSize, @Nullable String attachmentField2, @Nullable ReportHandler handler2, @Nullable String[] extraFileExtensions2, String... fileExtensions2) {
            this.directory = directory2;
            this.defaultMaxSize = maxSize;
            this.attachmentField = attachmentField2;
            this.handler = handler2;
            this.extraFileExtensions = extraFileExtensions2;
            this.fileExtensions = fileExtensions2;
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
            final String[] extensions = this.fileExtensions;
            return getSpool(context).snapshot(new FifoSpoolComparator(), new FilenameFilter() {
                /* class com.facebook.acra.ErrorReporter.CrashReportType.AnonymousClass1 */

                public boolean accept(File dir, String name) {
                    for (String extension : extensions) {
                        if (name.endsWith(extension)) {
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

        public ReportsSenderWorker(ErrorReporter this$02, @Nullable CrashReportData inMemoryReportToSend, Spool.FileBeingProduced reportFileUnderConstruction) {
            this(inMemoryReportToSend, reportFileUnderConstruction, null);
        }

        public ReportsSenderWorker(ErrorReporter this$02, CrashReportType... reportTypesToSend) {
            this(null, null, reportTypesToSend);
        }

        private ReportsSenderWorker(@Nullable CrashReportData inMemoryReportToSend, @Nullable Spool.FileBeingProduced reportFileUnderConstruction, @Nullable CrashReportType[] reportTypesToSend) {
            super("ReportsSenderWorker");
            this.exception = null;
            this.mGenerator = null;
            this.mInMemoryReportToSend = inMemoryReportToSend;
            this.mReportFileUnderConstruction = reportFileUnderConstruction;
            this.mReportTypesToSend = reportTypesToSend;
        }

        public void routeReportToFile(FileGenerator generator) {
            this.mGenerator = generator;
        }

        /* access modifiers changed from: package-private */
        public void doSend() throws ReportSenderException {
            ErrorReporter.mSendAttempts.getAndIncrement();
            PowerManager.WakeLock wakeLock = null;
            try {
                wakeLock = acquireWakeLock();
                if (this.mInMemoryReportToSend == null) {
                    ErrorReporter.this.prepareReports(Integer.MAX_VALUE, this.mGenerator, true, this.mReportTypesToSend);
                } else {
                    CrashReportData crashReport = this.mInMemoryReportToSend;
                    crashReport.put(ReportField.UPLOADED_BY_PROCESS, CrashTimeDataCollector.getProcessNameFromAms(ErrorReporter.this.mContext));
                    ErrorReporter.this.sendCrashReport(crashReport);
                    if (this.mReportFileUnderConstruction != null) {
                        this.mReportFileUnderConstruction.fileName.delete();
                    }
                }
            } finally {
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        }

        public void run() {
            synchronized (ErrorReporter.this) {
                ErrorReporter.access$908(ErrorReporter.this);
            }
            try {
                doSend();
            } catch (Throwable ex) {
                this.exception = ex;
            } finally {
                ErrorReporter.this.safeClose(this.mReportFileUnderConstruction);
            }
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
            PowerManager.WakeLock wakeLock = ((PowerManager) ErrorReporter.this.mContext.getSystemService("power")).newWakeLock(1, "ACRA wakelock");
            wakeLock.setReferenceCounted(false);
            wakeLock.acquire();
            return wakeLock;
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

    public void addExceptionTranslationHook(ExceptionTranslationHook hook) {
        hook.next = this.mExceptionTranslationHook.getAndSet(hook);
    }

    @Nullable
    public String getUserId() {
        return this.mUserId;
    }

    public String getClientUserId() {
        return this.mClientUserId;
    }

    public void setUserId(@Nullable String userId) {
        this.mUserId = userId;
    }

    public void setClientUserId(String userId) {
        this.mClientUserId = userId;
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

    public String getSigquitTracesExtension() {
        return REPORTFILE_EXTENSION;
    }

    /* access modifiers changed from: package-private */
    public SimpleTraceLogger getActivityLogger() {
        return this.mActivityLogger;
    }

    public static int getSendAttempts() {
        return mSendAttempts.get();
    }

    public boolean addToAnrInProgressUpdateFile(Map<String, String> properties) throws IOException {
        boolean z = true;
        synchronized (this.mAnrFilesInProgress) {
            if (this.mAnrFilesInProgress.isEmpty()) {
                z = false;
            } else {
                FileOutputStream updateFileStream = new FileOutputStream(new File(this.mAnrFilesInProgress.iterator().next() + ANR_EXTRA_PROPERTIES_EXTENSION), true);
                new CrashReportData(properties).store(updateFileStream, (String) null);
                updateFileStream.close();
            }
        }
        return z;
    }

    public static void putCustomData(String key, @Nullable String value) {
        ProxyCustomDataStore.getInstance().setCustomData(key, value, new Object[0]);
    }

    public static void removeCustomData(@Nullable String key) {
        ProxyCustomDataStore.getInstance().removeCustomData(key);
    }

    @Nullable
    public static String getCustomData(String key) {
        return ProxyCustomDataStore.getInstance().getCustomData(key);
    }

    public static boolean containsKeyInCustomData(String key) {
        return ProxyCustomDataStore.getInstance().containsKey(key);
    }

    public void putLazyCustomData(String key, CustomReportDataSupplier valueSupplier) {
        synchronized (this.mInstanceLazyCustomParameters) {
            this.mInstanceLazyCustomParameters.put(key, valueSupplier);
        }
    }

    public void setExcludedReportObserver(@Nullable ExcludedReportObserver observer) {
        this.mExcludedReportObserver = observer;
    }

    @Nullable
    public CustomReportDataSupplier removeLazyCustomData(String key) {
        CustomReportDataSupplier remove;
        if (key == null) {
            return null;
        }
        synchronized (this.mInstanceLazyCustomParameters) {
            remove = this.mInstanceLazyCustomParameters.remove(key);
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

    public void init(AcraReportingConfig config) {
        if (this.mInitializationComplete) {
            throw new IllegalStateException("ErrorReporter already initialized");
        }
        this.mContext = config.getApplicationContext();
        if (this.mContext == null) {
            throw new AssertionError("context must be non-null");
        }
        this.mInstallTime = new File(this.mContext.getApplicationInfo().sourceDir).lastModified();
        if (this.mInstallTime == 0) {
            BLog.w(ACRA.LOG_TAG, "could not retrieve APK mod time");
        }
        this.mConfig = config;
        this.mChainedHandler = config.getDefaultUncaughtExceptionHandler();
        if (this.mConfig.getSessionId() != null) {
            putCustomData(ReportField.SESSION_ID, this.mConfig.getSessionId());
        }
        addCriticalData();
        this.mInitializationComplete = true;
    }

    public void initFallible() {
        int oomReservationSize = this.mConfig.getOomReservationOverride();
        if (oomReservationSize <= 0) {
            oomReservationSize = 65536;
        }
        this.mOomReservation = new byte[oomReservationSize];
        synchronized (this.mAppStartDate) {
            this.mAppStartDate.setToNow();
            this.mOomReservation[0] = 1;
        }
        populateConstantFields();
        File preallocFileName = new File(this.mContext.getDir(ACRA_DIRNAME, 0), PREALLOCATED_REPORTFILE);
        long preallocatedFileSize = this.mConfig.getPreallocatedFileSizeOverride();
        if (preallocatedFileSize <= 0) {
            preallocatedFileSize = DEFAULT_MAX_REPORT_SIZE;
        }
        if (preallocFileName.length() < preallocatedFileSize) {
            try {
                preallocateReportFile(preallocFileName, preallocatedFileSize);
            } catch (Throwable ex) {
                tryLogInternalError(ex);
                preallocFileName = null;
            }
        }
        this.mPreallocFileName = preallocFileName;
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
        String androidId;
        int versionCode = BuildConstants.getAPKVersionCode();
        this.mAppVersionCode = Integer.toString(BuildConstants.getBuildID());
        PackageManagerWrapper pm = new PackageManagerWrapper(this.mContext, ACRA.LOG_TAG);
        PackageInfo pi = pm.getPackageInfo();
        if (pi != null && versionCode == 1) {
            this.mAppVersionCode = Integer.toString(pi.versionCode);
        }
        if (pi == null || (!(pi.versionCode == versionCode || versionCode == 1) || pi.versionName == null)) {
            this.mAppVersionName = "not set";
        } else {
            this.mAppVersionName = pi.versionName;
        }
        TreeMap<String, String> constantFieldBuilder = new TreeMap<>();
        if (this.mConfig.shouldReportField(ReportField.ANDROID_ID)) {
            try {
                androidId = sSecureSettingsResolver.getString(this.mContext.getContentResolver(), "android_id");
            } catch (Exception e) {
                BLog.e(TAG, "Failed to fetch the constant field ANDROID_ID", e);
                androidId = "unknown";
            }
            constantFieldBuilder.put(ReportField.ANDROID_ID, androidId);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_VERSION_CODE)) {
            constantFieldBuilder.put(ReportField.APP_VERSION_CODE, this.mAppVersionCode);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_VERSION_NAME)) {
            constantFieldBuilder.put(ReportField.APP_VERSION_NAME, this.mAppVersionName);
        }
        if (this.mConfig.shouldReportField(ReportField.PACKAGE_NAME)) {
            constantFieldBuilder.put(ReportField.PACKAGE_NAME, this.mContext.getPackageName());
        }
        if (this.mConfig.shouldReportField(ReportField.INSTALLER_NAME)) {
            constantFieldBuilder.put(ReportField.INSTALLER_NAME, pm.getInstallerPackageName());
        }
        if (this.mConfig.shouldReportField(ReportField.PHONE_MODEL)) {
            constantFieldBuilder.put(ReportField.PHONE_MODEL, Build.MODEL);
        }
        if (this.mConfig.shouldReportField(ReportField.DEVICE)) {
            constantFieldBuilder.put(ReportField.DEVICE, Build.DEVICE);
        }
        if (this.mConfig.shouldReportField(ReportField.ANDROID_VERSION)) {
            constantFieldBuilder.put(ReportField.ANDROID_VERSION, Build.VERSION.RELEASE);
        }
        if (this.mConfig.shouldReportField(ReportField.OS_VERSION)) {
            constantFieldBuilder.put(ReportField.OS_VERSION, System.getProperty("os.version"));
        }
        if (this.mConfig.shouldReportField(ReportField.BUILD_HOST)) {
            constantFieldBuilder.put(ReportField.BUILD_HOST, Build.HOST);
        }
        if (this.mConfig.shouldReportField(ReportField.BRAND)) {
            constantFieldBuilder.put(ReportField.BRAND, Build.BRAND);
        }
        if (this.mConfig.shouldReportField(ReportField.PRODUCT)) {
            constantFieldBuilder.put(ReportField.PRODUCT, Build.PRODUCT);
        }
        if (this.mConfig.shouldReportField(ReportField.FILE_PATH)) {
            String absolutePath = "n/a";
            File filesDir = this.mContext.getFilesDir();
            if (filesDir != null) {
                absolutePath = filesDir.getAbsolutePath();
            }
            constantFieldBuilder.put(ReportField.FILE_PATH, absolutePath);
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_TIME) && pi != null) {
            constantFieldBuilder.put(ReportField.APP_INSTALL_TIME, CrashTimeDataCollectorHelper.formatAppInstallTime(pi.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_INSTALL_EPOCH_TIME) && pi != null) {
            constantFieldBuilder.put(ReportField.APP_INSTALL_EPOCH_TIME, String.valueOf(pi.firstInstallTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_TIME) && pi != null) {
            constantFieldBuilder.put(ReportField.APP_UPGRADE_TIME, CrashTimeDataCollectorHelper.formatAppUpgradeTime(pi.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_UPGRADE_EPOCH_TIME) && pi != null) {
            constantFieldBuilder.put(ReportField.APP_UPGRADE_EPOCH_TIME, String.valueOf(pi.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.APP_SINCE_UPGRADE_TIME) && pi != null) {
            constantFieldBuilder.put(ReportField.APP_SINCE_UPGRADE_TIME, Long.toString(System.currentTimeMillis() - pi.lastUpdateTime));
        }
        if (this.mConfig.shouldReportField(ReportField.PUBLIC_SOURCE_DIR)) {
            if (this.mContext.getApplicationInfo() != null) {
                constantFieldBuilder.put(ReportField.PUBLIC_SOURCE_DIR, this.mContext.getApplicationInfo().publicSourceDir);
            } else {
                constantFieldBuilder.put(ReportField.PUBLIC_SOURCE_DIR, "null application info");
            }
        }
        if (this.mConfig.shouldReportField(ReportField.IS_RELABELED)) {
            constantFieldBuilder.put(ReportField.IS_RELABELED, String.valueOf(BuildConstants.isRelabeled()));
        }
        this.mConstantFields = Collections.unmodifiableMap(constantFieldBuilder);
    }

    private void preallocateReportFile(File preallocFileName, long preallocatedFileSize) throws IOException {
        Spool.FileBeingProduced dumpFile = CrashReportType.ACRA_CRASH_REPORT.getSpool(this.mContext).produce(genCrashReportFileName(ErrorReporter.class, UUID.randomUUID(), REPORTFILE_EXTENSION));
        try {
            FileOutputStream fos = new FileOutputStream(dumpFile.fileName);
            try {
                byte[] buf = new byte[32768];
                for (long fileSize = 0; fileSize < preallocatedFileSize; fileSize += (long) buf.length) {
                    fos.write(buf);
                }
                fos.getFD().sync();
                fos.close();
                renameOrThrow(dumpFile.fileName, preallocFileName);
                try {
                    if (dumpFile != null) {
                        dumpFile.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
                throw th;
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } finally {
            dumpFile.fileName.delete();
        }
    }

    private static void renameOrThrow(File src, File dst) throws IOException {
        if (!src.renameTo(dst)) {
            throw new IOException(String.format("rename of %s to %s failed", src, dst));
        }
    }

    @Nullable
    private Throwable translateException(Throwable ex, Map<String, String> extra) {
        Throwable translatedEx = ex;
        int nrIterations = 0;
        ExceptionTranslationHook startHook = this.mExceptionTranslationHook.get();
        do {
            for (ExceptionTranslationHook hook = startHook; hook != null && translatedEx != null; hook = hook.next) {
                try {
                    translatedEx = hook.translate(translatedEx, extra);
                } catch (Throwable ex2) {
                    BLog.w(ACRA.LOG_TAG, ex2, "ignoring error in exception translation hook %s", hook);
                }
            }
            if (translatedEx == translatedEx) {
                break;
            }
            nrIterations++;
        } while (nrIterations < 4);
        return translatedEx;
    }

    private void uncaughtExceptionImpl(Thread t, Throwable ex, boolean recursive) throws Throwable {
        int reportFlags;
        this.mOomReservation = null;
        discardOverlappingReports(CrashReportType.ACRA_CRASH_REPORT);
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                Api9Utils.disableStrictMode();
            }
        } catch (Throwable internalEx) {
            tryLogInternalError(internalEx);
        }
        try {
            BLog.e(ACRA.LOG_TAG, "ACRA caught a %s exception for %s version %s. Building report.", ex.getClass().getSimpleName(), this.mContext.getPackageName(), this.mAppVersionCode);
        } catch (Throwable internalEx2) {
            tryLogInternalError(internalEx2);
        }
        Map<String, String> extra = new TreeMap<>();
        extra.put(ReportField.THROWING_THREAD_NAME, String.valueOf(t.getName()));
        Throwable ex2 = translateException(ex, extra);
        if (ex2 != null) {
            if (recursive) {
                reportFlags = 4;
            } else {
                reportFlags = 3;
            }
            if (!this.mConfig.shouldImmediatelyUpload()) {
                reportFlags = 4;
            }
            if (getMostSignificantCause(ex2) instanceof OutOfMemoryError) {
                reportFlags &= -2;
            }
            Object context = null;
            try {
                BlackBoxRecorderControl bbRecorderControl = BlackBoxState.getInstance().getBlackBoxRecorderControl();
                if (bbRecorderControl != null) {
                    context = bbRecorderControl.captureBlackBoxTrace(extra, 1);
                }
                handleExceptionInternal(ex2, new CrashReportData(extra), null, reportFlags);
                if (bbRecorderControl != null) {
                    bbRecorderControl.awaitForBlackBoxTraceCompletion(context);
                }
            } catch (Throwable reportEx) {
                if (recursive) {
                    throw reportEx;
                }
                BLog.e(ACRA.LOG_TAG, reportEx, "error during error reporting: will attempt to report error");
                uncaughtExceptionImpl(t, reportEx, true);
            }
        }
    }

    private void tryLogInternalError(Throwable ex) {
        tryLogInternalError(null, ex);
    }

    private void tryLogInternalError(@Nullable String what, Throwable ex) {
        if (what == null) {
            what = "???";
        }
        try {
            BLog.e(ACRA.LOG_TAG, ex, "internal ACRA error: %s: ", what);
        } catch (Throwable th) {
        }
    }

    public void reportErrorAndTerminate(Thread thread, Throwable throwable) {
        ExceptionHandlerManager.handleThrowableAndTerminate(thread, throwable);
    }

    @Deprecated
    public void uncaughtException(Thread t, Throwable e) {
        reportErrorAndTerminate(t, e);
    }

    @Override // com.facebook.common.exceptionhandler.ManagedExceptionHandler
    public void handleUncaughtException(Thread t, Throwable e, @Nullable CustomStackTracerInterface.CustomStackTrace customStackTrace) {
        int currentJavaCrashCount = roughlyCountPendingReportsOfType(CrashReportType.ACRA_CRASH_REPORT);
        int maxPendingJavaCrashReports = this.mConfig.getMaxPendingJavaCrashReports(currentJavaCrashCount);
        if (maxPendingJavaCrashReports < Integer.MAX_VALUE) {
            if (currentJavaCrashCount >= maxPendingJavaCrashReports) {
                BLog.e(TAG, e, "Maximum pending Java crash threshold reached, Not storing count=%d", Integer.valueOf(currentJavaCrashCount));
                return;
            }
            BLog.i(TAG, "Current pending Java crash report count = %d", Integer.valueOf(currentJavaCrashCount));
        }
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            try {
                uncaughtExceptionImpl(t, e, false);
            } catch (Throwable internalEx) {
                tryLogInternalError(internalEx);
            }
        }
    }

    static void trimStackBuffer(StringBuffer buffer, int maxLength) {
        int halfMaxLength;
        int trimStart;
        int trimEnd;
        if (buffer.length() > maxLength && (trimStart = buffer.lastIndexOf("\n", (halfMaxLength = maxLength / 2))) >= 0 && (trimEnd = buffer.indexOf("\n", buffer.length() - halfMaxLength)) >= 0) {
            buffer.replace(trimStart, trimEnd, STACK_TRIMMED_MESSAGE);
        }
    }

    static String throwableToString(Throwable e) {
        if (e == null) {
            e = new Exception("Report requested by developer");
        }
        StringWriter result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        printWriter.close();
        if (e instanceof StackOverflowError) {
            trimStackBuffer(result.getBuffer(), MAX_STACK_LENGTH_FOR_OVERFLOW);
        }
        return result.toString();
    }

    static void put(String key, String value, CrashReportData crashReport, @Nullable Writer writer) {
        if (crashReport.generatingIoError != null) {
            writer = null;
        }
        try {
            crashReport.put(key, value, writer);
        } catch (IOException ex) {
            crashReport.generatingIoError = ex;
        }
    }

    public void writeReportToStream(Throwable e, OutputStream os) throws Exception {
        CrashReportData crashReport = new CrashReportData();
        Writer w = CrashReportData.getWriter(os);
        String stackTrace = throwableToString(e);
        crashReport.put(ReportField.REPORT_ID, CrashTimeDataCollectorHelper.generateReportUuid().toString(), w);
        CrashTimeDataCollector.gatherCrashData(this, this.mConfig, stackTrace, e, crashReport, w, null, true, false);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable e) {
        return handleException(e, (CrashReportData) null);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable e, @Nullable CrashReportData draft) {
        return handleExceptionInternal(e, draft, null, 1);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable e, @Nullable Map<String, String> extras) {
        return handleException(e, extras != null ? new CrashReportData(extras) : null);
    }

    public void handleExceptionDelayed(Throwable e, @Nullable CrashReportData draft) {
        handleExceptionInternal(e, draft, null, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void safeClose(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable ex) {
                tryLogInternalError("safeClose", ex);
            }
        }
    }

    @Nullable
    private ReportsSenderWorker handleExceptionInternal(Throwable e, CrashReportData draft, @Nullable String overrideStackTrace, int flags) {
        if (!this.mInitializationComplete) {
            return null;
        }
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        if (!(draft == null || draft.getProperty(ErrorReportingConstants.SOFT_ERROR_CATEGORY) == null || !this.mConfig.shouldSkipSoftErrorReport())) {
            return null;
        }
        Class causeClass = getMostSignificantCause(e).getClass();
        CrashReportData crashReport = new CrashReportData();
        boolean skipWritingDumpToDisk = false;
        if (this.mConfig.strictEnforceAcraDumpFileMax() && CrashReportType.ACRA_CRASH_REPORT.getPendingCrashReports(this.mContext).getEstimate() > 5) {
            skipWritingDumpToDisk = true;
        }
        boolean isNonCrash = e instanceof NonCrashException;
        if (isNonCrash) {
            int reportImmediately = this.mConfig.reportSoftErrorsImmediately();
            if (reportImmediately == 0) {
                flags |= 4;
            } else if (reportImmediately == 2) {
                flags |= 1;
            } else if (reportImmediately == 1) {
                flags |= 3;
            }
        }
        String description = isNonCrash ? ((NonCrashException) e).getExceptionFriendlyName() : "crash";
        writeToLogBridge(ACRA.LOG_TAG, "Handling exception for " + description, e, overrideStackTrace);
        BLog.d(ACRA.LOG_TAG, "Generating report file for %s", description);
        UUID reportUuid = null;
        String reportBaseFileName = null;
        Spool spool = null;
        Spool.FileBeingProduced fbp = null;
        FileOutputStream outputStream = null;
        Writer reportWriter = null;
        if (!skipWritingDumpToDisk) {
            try {
                spool = CrashReportType.ACRA_CRASH_REPORT.getSpool(this.mContext);
            } catch (Throwable th) {
                safeClose(null);
                safeClose(null);
                safeClose(null);
                throw th;
            }
        }
        if (spool != null) {
            while (fbp == null) {
                try {
                    reportUuid = CrashTimeDataCollectorHelper.generateReportUuid();
                    reportBaseFileName = genCrashReportFileName(causeClass, reportUuid, REPORTFILE_EXTENSION);
                    fbp = spool.produceWithDonorFile(reportBaseFileName, this.mPreallocFileName);
                } catch (Throwable ex) {
                    crashReport.generatingIoError = ex;
                    tryLogInternalError(ex);
                    reportBaseFileName = null;
                }
            }
        }
        if (fbp != null) {
            try {
                outputStream = new FileOutputStream(fbp.file.getFD());
            } catch (Throwable ex2) {
                crashReport.generatingIoError = ex2;
                tryLogInternalError(ex2);
            }
        }
        if (outputStream != null) {
            try {
                reportWriter = CrashReportData.getWriter(outputStream);
            } catch (Throwable ex3) {
                crashReport.generatingIoError = ex3;
                tryLogInternalError(ex3);
            }
        }
        if (reportUuid == null) {
            try {
                reportUuid = CrashTimeDataCollectorHelper.generateReportUuid();
            } catch (Throwable ex1) {
                tryLogInternalError(ex1);
                put(ReportField.ACRA_INTERNAL, mInternalException, crashReport, reportWriter);
            }
        }
        put(ReportField.ACRA_REPORT_TYPE, CrashReportType.ACRA_CRASH_REPORT.name(), crashReport, reportWriter);
        if (this.mConfig.shouldReportField(ReportField.ACRA_REPORT_FILENAME) && reportBaseFileName != null) {
            put(ReportField.ACRA_REPORT_FILENAME, reportBaseFileName, crashReport, reportWriter);
        }
        if (this.mConfig.shouldReportField(ReportField.REPORT_ID)) {
            put(ReportField.REPORT_ID, reportUuid.toString(), crashReport, reportWriter);
        }
        if (this.mConfig.shouldReportField(ReportField.EXCEPTION_CAUSE)) {
            put(ReportField.EXCEPTION_CAUSE, causeClass.getName(), crashReport, reportWriter);
        }
        if (overrideStackTrace == null) {
            try {
                overrideStackTrace = throwableToString(e);
            } catch (Throwable th2) {
            }
        }
        if (draft != null) {
            try {
                for (Map.Entry<String, String> entry : draft.entrySet()) {
                    if (CrashTimeDataCollector.shouldAddField(entry.getKey(), crashReport, this.mConfig)) {
                        try {
                            put(entry.getKey(), entry.getValue(), crashReport, reportWriter);
                        } catch (Throwable ex4) {
                            CrashTimeDataCollector.noteReportFieldFailure(crashReport, entry.getKey(), ex4);
                        }
                    }
                }
            } catch (Throwable ex5) {
                tryLogInternalError("pre-populating fields from draft", ex5);
            }
        }
        CrashTimeDataCollector.gatherCrashData(this, this.mConfig, overrideStackTrace, e, crashReport, reportWriter, null, true, isNonCrash);
        if (draft != null && BuildConstants.isDebugBuild()) {
            try {
                for (Map.Entry<String, String> entry2 : draft.entrySet()) {
                    if (this.mConfig.shouldReportField(entry2.getKey()) && !objectsEqual(entry2.getValue(), crashReport.getProperty(entry2.getKey()))) {
                        BLog.e(ACRA.LOG_TAG, "Crash report field %s overwritten in CrashTimeDataCollector", entry2.getKey());
                    }
                }
            } catch (Throwable ex6) {
                tryLogInternalError("error while validating crash report fields", ex6);
            }
        }
        if (reportWriter != null) {
            try {
                reportWriter.flush();
                if (outputStream != null) {
                    outputStream.flush();
                }
                FileChannel chan = fbp.file.getChannel();
                chan.truncate(chan.position());
            } catch (Throwable ex7) {
                crashReport.generatingIoError = ex7;
                tryLogInternalError(ex7);
            }
        }
        if (crashReport.generatingIoError != null) {
            put(ReportField.GENERATING_IO_ERROR, crashReport.generatingIoError.toString(), crashReport, reportWriter);
        }
        if (crashReport.generatingIoError != null && (flags & 4) == 0 && this.mConfig.shouldImmediatelyUpload()) {
            flags |= 1;
        }
        if (skipWritingDumpToDisk) {
            flags |= 1;
        }
        ReportsSenderWorker wk = null;
        if ((flags & 1) != 0) {
            try {
                crashReport.put(ReportField.SENT_IMMEDIATELY, "true");
                ReportsSenderWorker wk2 = new ReportsSenderWorker(this, crashReport, fbp);
                if ((flags & 2) != 0) {
                    try {
                        wk2.doSend();
                        CrashReportedObserver observer = this.mCrashReportedObserver.get();
                        if (!(observer == null || wk2.mInMemoryReportToSend == null)) {
                            observer.onCrashReported(wk2.mInMemoryReportToSend);
                        }
                        wk = wk2;
                    } catch (Throwable th3) {
                        ex = th3;
                        wk = wk2;
                        tryLogInternalError("sending in-memory report", ex);
                        safeClose(reportWriter);
                        safeClose(outputStream);
                        safeClose(fbp);
                        return wk;
                    }
                } else {
                    wk2.start();
                    fbp = null;
                    wk = wk2;
                }
            } catch (Throwable th4) {
                ex = th4;
                tryLogInternalError("sending in-memory report", ex);
                safeClose(reportWriter);
                safeClose(outputStream);
                safeClose(fbp);
                return wk;
            }
        }
        safeClose(reportWriter);
        safeClose(outputStream);
        safeClose(fbp);
        return wk;
    }

    private static boolean objectsEqual(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public void setCrashReportedObserver(CrashReportedObserver observer) {
        this.mCrashReportedObserver.set(observer);
    }

    @Nullable
    public ReportsSenderWorker handleException(Throwable e, String stackTrace, @Nullable CrashReportData draft) {
        return handleExceptionInternal(e, draft, stackTrace, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendCrashReport(CrashReportData errorContent) throws ReportSenderException {
        ArrayList<ReportSender> reportSenders;
        if (!this.mConfig.isZeroCrashlogBlocked()) {
            if (this.mConfig.shouldOnlyWriteReport()) {
                BLog.i(ACRA.LOG_TAG, "Writing report to file");
                writeJsonReport(errorContent);
                return;
            }
            boolean sentAtLeastOnce = false;
            synchronized (this.mReportSenders) {
                reportSenders = new ArrayList<>(this.mReportSenders);
            }
            if (reportSenders.isEmpty()) {
                throw new ReportSenderException("no configured report senders", null);
            }
            Iterator<ReportSender> it = reportSenders.iterator();
            while (it.hasNext()) {
                ReportSender sender = it.next();
                try {
                    sender.send(errorContent);
                    sentAtLeastOnce = true;
                } catch (ReportSenderException e) {
                    if (sentAtLeastOnce) {
                        BLog.w(ACRA.LOG_TAG, e, "ReportSender of class %s failed but other senders completed their task. ACRA will not send this report again.", sender.getClass().getName());
                    } else if (!(sender instanceof HttpPostSender) || !this.mConfig.shouldUseUploadService()) {
                        throw e;
                    } else if (!writeJsonReport(errorContent)) {
                        throw e;
                    }
                }
            }
        }
    }

    private boolean writeJsonReport(CrashReportData errorContent) {
        String reportFileId;
        SecureRandom secureRandom = new SecureRandom();
        BLog.d(ACRA.LOG_TAG, "Writing report");
        try {
            reportFileId = new UUID(this.mAppStartTickTimeMs ^ secureRandom.nextLong(), (this.mUserId == null ? 0 : Long.parseLong(this.mUserId)) ^ secureRandom.nextLong()).toString();
        } catch (NumberFormatException e) {
            reportFileId = UUID.randomUUID().toString();
        }
        File uploadFile = new File(this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0), reportFileId + ".gz");
        Map<String, InputStreamField> inputStreamFields = new HashMap<>();
        for (Map.Entry<String, InputStreamField> param : errorContent.getInputStreamFields().entrySet()) {
            if (!param.getKey().equals(ReportField.MINIDUMP)) {
                BLog.d(ACRA.LOG_TAG, "Ignoring field %s", param.getKey());
            } else {
                inputStreamFields.put(param.getKey(), param.getValue());
            }
        }
        if (!JsonReportWriter.writeGzipJsonReport(errorContent, inputStreamFields, uploadFile)) {
            return false;
        }
        BLog.d(TAG, "Wrote file for uploader %s", uploadFile.getName());
        return true;
    }

    private boolean mustEmbedAttachments(CrashReportType type) {
        if (type != CrashReportType.NATIVE_CRASH_REPORT) {
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

    private String genCrashReportFileName(Class cause, UUID reportUuid, String fileExtension) {
        return reportUuid.toString() + "-" + cause.getSimpleName() + (this.mAppVersionCode != null ? "-" + this.mAppVersionCode : "") + fileExtension;
    }

    private void discardOverlappingReports(CrashReportType... types) {
        for (CrashReportType type : types) {
            if (type == CrashReportType.NATIVE_CRASH_REPORT || type == CrashReportType.ACRA_CRASH_REPORT) {
                if (roughlyCountPendingReportsOfType(type) > 0) {
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

    public void prepareANRReport(File traceFile, FileGenerator fileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        maybeRemoveAnrReports();
        synchronized (ANR_REPORTING_LOCK) {
            buildCachedCrashReport(CrashReportType.ANR_REPORT, null, traceFile, fileGenerator);
        }
    }

    public void prepareANRReport(FileGenerator outFileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        synchronized (ANR_REPORTING_LOCK) {
            prepareReports(Integer.MAX_VALUE, outFileGenerator, false, CrashReportType.ANR_REPORT);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0043, code lost:
        if (r17 == null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0045, code lost:
        r3.put(com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT.attachmentField, com.facebook.acra.util.AttachmentUtil.compressToBase64String(r17.getBytes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0056, code lost:
        r3.store(r15, (java.lang.String) null);
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        r13 = new java.io.File(r18);
        r6 = r13.length();
        r11 = new java.io.FileInputStream(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4 = new java.io.BufferedInputStream(r11);
        r10 = readSigquitFileHeader(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007e, code lost:
        if (r10.commandLine == null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0080, code lost:
        r3.put(com.facebook.acra.constants.ReportField.PROCESS_NAME, r10.commandLine);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
        if (android.text.TextUtils.isEmpty(r10.versionCode) != false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008f, code lost:
        r3.put(com.facebook.acra.constants.ReportField.APP_VERSION_CODE, r10.versionCode);
        r3.put(com.facebook.acra.constants.ReportField.APP_VERSION_NAME, r10.versionName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009d, code lost:
        slurpAttachment(r3, r4, com.facebook.acra.ErrorReporter.CrashReportType.ANR_REPORT, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a4, code lost:
        r11.close();
        deleteFile(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ab, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b0, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b1, code lost:
        r2.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        r15 = new java.io.FileOutputStream(new java.io.File(r9 + com.facebook.acra.ErrorReporter.ANR_EXTRA_PROPERTIES_EXTENSION), true);
        r3 = new com.facebook.acra.CrashReportData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void amendANRReportWithSigquitData(java.lang.String r17, java.lang.String r18) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.amendANRReportWithSigquitData(java.lang.String, java.lang.String):void");
    }

    public int prepareReports(int max, FileGenerator outFileGenerator, boolean recordInProgress, CrashReportType... types) {
        int processCrashAttachmentsLocked;
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - start");
        int nr = 0;
        discardOverlappingReports(types);
        for (CrashReportType reportType : types) {
            int remaining = Math.max(0, max - nr);
            if (reportType.getHandler() != null) {
                processCrashAttachmentsLocked = checkAndHandleReportsLocked(remaining, reportType, false);
            } else {
                processCrashAttachmentsLocked = processCrashAttachmentsLocked(remaining, reportType, outFileGenerator, recordInProgress);
            }
            nr += processCrashAttachmentsLocked;
        }
        if (!shouldSkipReport(CrashReportType.NATIVE_CRASH_REPORT)) {
            NativeCrashDumpReporterUtil.cleanupHeapDump(this.mContext);
        }
        BLog.d(ACRA.LOG_TAG, "#prepareReports - finish");
        return nr;
    }

    public int prepareANRReport(String stackTrace, FileGenerator outFileGenerator) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        return buildCachedCrashReport(CrashReportType.ANR_REPORT, stackTrace, null, outFileGenerator);
    }

    public int prepareCachedANRReports(int max) {
        synchronized (UNCAUGHT_EXCEPTION_LOCK) {
            UNCAUGHT_EXCEPTION_LOCK.notify();
        }
        maybeRemoveAnrReports();
        return checkAndHandleReportsLocked(max, CrashReportType.CACHED_ANR_REPORT, true);
    }

    @Nullable
    public String getLogcatOutputIfPidFound(boolean collectLogcatFromExternalProcess, @Nullable Integer pid) {
        String logcat = LogCatCollector.collectLogCat(this.mContext, this.mConfig, null, null, false, collectLogcatFromExternalProcess, this.mConfig.allowCollectionOfMaxNumberOfLinesInLogcat());
        if (logcat == null || (pid != null && !Pattern.compile("^\\d+-\\d+\\s+\\d+:\\d+:\\d+\\.\\d+\\s+" + pid.toString() + "\\s+\\d+\\s+[A-Z]\\s+(.*?)$", 8).matcher(logcat).find())) {
            return null;
        }
        return logcat;
    }

    @Nullable
    public String getEventsLog() {
        if (Build.VERSION.SDK_INT >= 19) {
            return null;
        }
        return LogCatCollector.collectLogCat(this.mContext, this.mConfig, "events", null, false, true, this.mConfig.allowCollectionOfMaxNumberOfLinesInLogcat());
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0084 A[SYNTHETIC, Splitter:B:52:0x0084] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int checkAndHandleReportsLocked(int r11, com.facebook.acra.ErrorReporter.CrashReportType r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.checkAndHandleReportsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, boolean):int");
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

        public int compare(Spool.Descriptor a, Spool.Descriptor b) {
            if (a.lastModifiedTime == b.lastModifiedTime) {
                return 0;
            }
            return a.lastModifiedTime < b.lastModifiedTime ? -1 : 1;
        }
    }

    private void buildAttachmentWrapperCrashReport(CrashReportData wrapperCrashReport, CrashReportType type, @Nullable Spool.FileBeingConsumed dumpFile, @Nullable Writer writer, boolean captureProcessName) {
        Spool.FileBeingConsumed fileBeingConsumed;
        try {
            wrapperCrashReport.put(ReportField.ACRA_REPORT_TYPE, type.name(), writer);
            AcraReportingConfig acraReportingConfig = this.mConfig;
            CrashAttachmentException crashAttachmentException = new CrashAttachmentException();
            if (type == CrashReportType.NATIVE_CRASH_REPORT) {
                fileBeingConsumed = dumpFile;
            } else {
                fileBeingConsumed = null;
            }
            CrashTimeDataCollector.gatherCrashData(this, acraReportingConfig, CRASH_ATTACHMENT_DUMMY_STACKTRACE, crashAttachmentException, wrapperCrashReport, writer, fileBeingConsumed, captureProcessName, false);
        } catch (Throwable e) {
            put(ReportField.REPORT_LOAD_THROW, "retrieve exception: " + e.getMessage(), wrapperCrashReport, writer);
        }
    }

    private boolean shouldSkipReport(CrashReportType type) {
        return new File(this.mContext.getDir(type.directory, 0), ".noupload").exists();
    }

    @Nullable
    private File createBackUpDirectory(CrashReportType type) {
        NullPointerException e;
        File mStagingDir = null;
        try {
            File mStagingDir2 = new File(this.mContext.getDir(type.directory, 0).getParent(), REPORTED_CRASH_DIR);
            try {
                if (!mStagingDir2.exists()) {
                    mStagingDir2.mkdir();
                }
                return mStagingDir2;
            } catch (NullPointerException e2) {
                e = e2;
                mStagingDir = mStagingDir2;
                BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
                return mStagingDir;
            }
        } catch (NullPointerException e3) {
            e = e3;
            BLog.e(ACRA.LOG_TAG, e, "Failed to create backup directory %s", REPORTED_CRASH_DIR);
            return mStagingDir;
        }
    }

    private int keepNLatestDumpFiles(@Nullable File directory) {
        if (directory == null || !directory.exists()) {
            return 0;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return 0;
        }
        Arrays.sort(files, new Comparator<File>() {
            /* class com.facebook.acra.ErrorReporter.AnonymousClass2 */

            public int compare(File logFile1, File logFile2) {
                return Long.valueOf(logFile2.lastModified()).compareTo(Long.valueOf(logFile1.lastModified()));
            }
        });
        int fileCount = 0;
        int numFilesDeleted = 0;
        for (File file : files) {
            fileCount++;
            if (((long) fileCount) > 5) {
                file.delete();
                numFilesDeleted++;
            }
        }
        return numFilesDeleted;
    }

    public void writeLibsToFile(File syslibfile, @Nullable HashSet<String> moduleList) {
        Throwable th;
        if (moduleList != null && !moduleList.isEmpty()) {
            BufferedWriter bw = null;
            try {
                sSystemLibFileLock.writeLock().lock();
                BufferedWriter bw2 = new BufferedWriter(new FileWriter(syslibfile, true), moduleList.size());
                try {
                    Iterator<String> it = moduleList.iterator();
                    while (it.hasNext()) {
                        bw2.write(it.next() + "\n");
                    }
                    safeClose(bw2);
                    sSystemLibFileLock.writeLock().unlock();
                } catch (IOException e) {
                    e = e;
                    bw = bw2;
                    try {
                        BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
                        safeClose(bw);
                        sSystemLibFileLock.writeLock().unlock();
                    } catch (Throwable th2) {
                        th = th2;
                        safeClose(bw);
                        sSystemLibFileLock.writeLock().unlock();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bw = bw2;
                    safeClose(bw);
                    sSystemLibFileLock.writeLock().unlock();
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                BLog.e(ACRA.LOG_TAG, e, "GLC file to write Exception");
                safeClose(bw);
                sSystemLibFileLock.writeLock().unlock();
            }
        }
    }

    @Nullable
    public HashSet<String> getNewLibs(File syslibfile, @Nullable HashSet<String> moduleList) {
        Throwable th;
        if (moduleList == null || moduleList.isEmpty()) {
            return null;
        }
        BufferedReader br = null;
        try {
            sSystemLibFileLock.readLock().lock();
            BufferedReader br2 = new BufferedReader(new FileReader(syslibfile));
            while (true) {
                try {
                    String module = br2.readLine();
                    if (module == null) {
                        safeClose(br2);
                        sSystemLibFileLock.readLock().unlock();
                        return moduleList;
                    } else if (moduleList.contains(module)) {
                        moduleList.remove(module);
                    }
                } catch (IOException e) {
                    e = e;
                    br = br2;
                    try {
                        BLog.e(ACRA.LOG_TAG, e, "GLC getNewLibs IO Exception");
                        safeClose(br);
                        sSystemLibFileLock.readLock().unlock();
                        return moduleList;
                    } catch (Throwable th2) {
                        th = th2;
                        safeClose(br);
                        sSystemLibFileLock.readLock().unlock();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    br = br2;
                    safeClose(br);
                    sSystemLibFileLock.readLock().unlock();
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            BLog.e(ACRA.LOG_TAG, e, "GLC getNewLibs IO Exception");
            safeClose(br);
            sSystemLibFileLock.readLock().unlock();
            return moduleList;
        }
    }

    public static File getCrashDumpSysLibPath(Context ctx) {
        return ctx.getFileStreamPath(CRASH_DUMP_SYS_LIBS_FILE);
    }

    public void updateGLCwithSystemLibs(Spool.FileBeingConsumed dumpFile) {
        File syslibfile = getCrashDumpSysLibPath(this.mContext);
        if (!syslibfile.exists()) {
            try {
                syslibfile.createNewFile();
            } catch (IOException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to create GLC Lib file");
                return;
            }
        }
        try {
            writeLibsToFile(syslibfile, getNewLibs(syslibfile, new MinidumpReader(dumpFile.file).getModuleList()));
        } catch (IOException e2) {
            BLog.e(ACRA.LOG_TAG, e2, "Failed to create GLC Lib file");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x016a A[SYNTHETIC, Splitter:B:61:0x016a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int processCrashAttachmentsLocked(int r19, com.facebook.acra.ErrorReporter.CrashReportType r20, @javax.annotation.Nullable com.facebook.acra.FileGenerator r21, boolean r22) {
        /*
        // Method dump skipped, instructions count: 401
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.ErrorReporter.processCrashAttachmentsLocked(int, com.facebook.acra.ErrorReporter$CrashReportType, com.facebook.acra.FileGenerator, boolean):int");
    }

    @Nullable
    private static String getLogcatFileName(Spool.FileBeingConsumed dumpFile, CrashReportType type) {
        if (type != CrashReportType.NATIVE_CRASH_REPORT) {
            return null;
        }
        try {
            return new MinidumpReader(dumpFile.file).getCustomData(ACRA.LOGCAT_FILE_KEY);
        } catch (IOException e) {
            BLog.e(ACRA.LOG_TAG, e, "Failed to find logcat file %s", dumpFile.fileName);
            return null;
        }
    }

    private void processCrashAttachmentBeforeSend(Spool.FileBeingConsumed dumpFile, CrashReportType type, CrashReportData reportData, boolean mustEmbedAttachments) throws IOException {
        if (type == CrashReportType.NATIVE_CRASH_REPORT) {
            if (!mustEmbedAttachments) {
                NativeCrashDumpReporterUtil.processHeapDump(this.mContext, reportData, dumpFile, this.mConfig);
            }
            foregroundNativeCrashDetect(dumpFile);
            MinidumpReader reader = new MinidumpReader(dumpFile.file);
            String sessionId = reader.getCustomData(ACRA.SESSION_ID_KEY);
            if (!TextUtils.isEmpty(sessionId)) {
                reportData.put(ReportField.SESSION_ID, sessionId);
            }
            String firstRun = reader.getCustomData("is_first_run_after_upgrade");
            if (!TextUtils.isEmpty(firstRun)) {
                reportData.put("is_first_run_after_upgrade", firstRun);
            }
            String shouldReportDiskInfoNative = reader.getCustomData(ReportField.SHOULD_REPORT_DISK_INFO_NATIVE);
            if (!TextUtils.isEmpty(shouldReportDiskInfoNative)) {
                reportData.put(ReportField.SHOULD_REPORT_DISK_INFO_NATIVE, shouldReportDiskInfoNative);
            }
            String noDeviceMemory = reader.getCustomData(ReportField.NO_DEVICE_MEMORY);
            if (!TextUtils.isEmpty(noDeviceMemory)) {
                reportData.put(ReportField.NO_DEVICE_MEMORY, noDeviceMemory);
            }
            Integer appVersionCode = reader.getInt(MinidumpReader.MD_FB_APP_VERSION_CODE);
            if (appVersionCode != null) {
                reportData.put(ReportField.APP_VERSION_CODE, appVersionCode.toString());
            }
            String appVersionName = reader.getString(MinidumpReader.MD_FB_APP_VERSION_NAME);
            if (!TextUtils.isEmpty(appVersionName)) {
                reportData.put(ReportField.APP_VERSION_NAME, appVersionName);
            }
            String breakpadLibName = reader.getCustomData(ACRA.BREAKPAD_LIB_NAME);
            if (!TextUtils.isEmpty(breakpadLibName)) {
                reportData.put(ReportField.BREAKPAD_LIB_NAME, breakpadLibName);
            }
        }
    }

    private void processCrashAttachmentAfterSend(Spool.FileBeingConsumed dumpFile, CrashReportType type, @Nullable File backUpDir, @Nullable String logcatFilePath) throws IOException {
        if (type != CrashReportType.NATIVE_CRASH_REPORT || backUpDir == null || !backUpDir.exists()) {
            BLog.d(ACRA.LOG_TAG, "No need to backup. Deleting minidump %s", dumpFile.fileName.getCanonicalPath());
            deleteFile(dumpFile.fileName);
        } else {
            File destFile = new File(backUpDir, dumpFile.fileName.getName());
            destFile.delete();
            dumpFile.fileName.renameTo(destFile);
            BLog.d(ACRA.LOG_TAG, "Backup minidump file from %s to %s", dumpFile.fileName.getCanonicalPath(), destFile.getCanonicalPath());
        }
        if (logcatFilePath != null) {
            deleteFile(new File(logcatFilePath));
        }
    }

    private int buildCachedCrashReport(CrashReportType type, @Nullable String stackTrace, @Nullable File traceFile, FileGenerator outFileGenerator) {
        int nrSendAttempts = 0;
        if (!shouldSkipReport(type)) {
            if (stackTrace == null && traceFile == null) {
                throw new IllegalArgumentException("stackTrace and traceFile can't be null at the same time");
            }
            nrSendAttempts = 0;
            CrashReportData reportData = null;
            if (stackTrace != null) {
                try {
                    reportData = createCrashReportFromStackTrace(stackTrace, type);
                } catch (Throwable th) {
                    if (0 != 0) {
                        closeInputStreamFields(null);
                    }
                    if (traceFile != null) {
                        deleteFile(traceFile);
                    }
                    throw th;
                }
            } else if (traceFile != null && (reportData = loadCrashAttachment(traceFile, type, mustEmbedAttachments(type))) == null) {
                BLog.e(ACRA.LOG_TAG, "Failed to load crash attachment report %s", traceFile);
                if (reportData != null) {
                    closeInputStreamFields(reportData);
                }
                if (traceFile != null) {
                    deleteFile(traceFile);
                }
            }
            nrSendAttempts = 0 + maybeSendCrashReport(type, reportData, null, outFileGenerator, true);
            if (reportData != null) {
                closeInputStreamFields(reportData);
            }
            if (traceFile != null) {
                deleteFile(traceFile);
            }
        }
        return nrSendAttempts;
    }

    private int maybeSendCrashReport(CrashReportType type, CrashReportData reportData, @Nullable Spool.FileBeingConsumed dumpFile, @Nullable FileGenerator outFileGenerator, boolean recordInProgress) throws Throwable {
        String fname;
        String processName;
        Writer writer = null;
        if (outFileGenerator != null) {
            try {
                File outFile = outFileGenerator.generate();
                fname = outFile.getCanonicalPath();
                if (type == CrashReportType.ANR_REPORT && recordInProgress) {
                    synchronized (this.mAnrFilesInProgress) {
                        this.mAnrFilesInProgress.add(fname);
                    }
                }
                writer = CrashReportData.getWriter(new FileOutputStream(outFile));
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
            fname = dumpFile.fileName.getName();
        }
        CrashReportData wrapperCrashReport = new CrashReportData();
        if (type.attachmentField != null) {
            wrapperCrashReport.put(type.attachmentField, (String) reportData.get(type.attachmentField), writer);
        }
        boolean captureProcessName = true;
        if (type == CrashReportType.ANR_REPORT && (processName = (String) reportData.get(ReportField.PROCESS_NAME)) != null) {
            wrapperCrashReport.put(ReportField.PROCESS_NAME, processName, writer);
            captureProcessName = false;
        }
        buildAttachmentWrapperCrashReport(wrapperCrashReport, type, dumpFile, writer, captureProcessName);
        if (type == CrashReportType.ANR_REPORT && !recordInProgress) {
            wrapperCrashReport.put(ErrorReportingConstants.ANR_PARTIAL_REPORT, "true", writer);
            wrapperCrashReport.put(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL, writer);
            wrapperCrashReport.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, "1", writer);
        }
        wrapperCrashReport.put(ReportField.REPORT_ID, fname.substring(0, fname.lastIndexOf(46)), writer);
        wrapperCrashReport.merge((Map<String, String>) reportData, false, writer);
        wrapperCrashReport.mergeFieldOverwrite(reportData, ReportField.APP_VERSION_CODE, writer);
        wrapperCrashReport.mergeFieldOverwrite(reportData, ReportField.APP_VERSION_NAME, writer);
        wrapperCrashReport.mergeFieldOverwrite(reportData, ReportField.SESSION_ID, writer);
        wrapperCrashReport.put(ReportField.EXCEPTION_CAUSE, CRASH_ATTACHMENT_DUMMY_STACKTRACE, writer);
        int reportProcessed = 0 + 1;
        if (writer == null) {
            sendCrashReport(wrapperCrashReport);
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e2) {
                BLog.e(ACRA.LOG_TAG, e2, "IO Exception");
                reportSoftError(e2, "ANRValidationError<IOException::Non-cached>", e2.toString(), this);
            }
        }
        return reportProcessed;
    }

    private void closeInputStreamFields(CrashReportData reportData) {
        for (Map.Entry<String, InputStreamField> entry : reportData.getInputStreamFields().entrySet()) {
            InputStreamField inputStreamField = entry.getValue();
            if (!(inputStreamField == null || inputStreamField.getInputStream() == null)) {
                try {
                    inputStreamField.getInputStream().close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void foregroundNativeCrashDetect(Spool.FileBeingConsumed dumpFile) {
        boolean appStartedInForeground = false;
        try {
            MinidumpReader minidumpReader = new MinidumpReader(dumpFile.file);
            String appStartedInBackgroundStr = minidumpReader.getCustomData("APP_STARTED_IN_BACKGROUND");
            if (appStartedInBackgroundStr != null && appStartedInBackgroundStr.equals("false")) {
                appStartedInForeground = true;
            }
            String appState = minidumpReader.getString(MinidumpReader.MD_FB_APP_STATE_LOG);
            if ((appState != null && appState.indexOf("Resumed") != -1) || (appStartedInForeground && appState != null && appState.indexOf("\"activities\":[]") != -1)) {
                this.mContext.getSharedPreferences("FacebookApplication", 0).edit().putLong("crash_foreground_timestamp", dumpFile.fileName.lastModified()).commit();
            }
        } catch (Exception e) {
            writeToLogBridge(ACRA.LOG_TAG, "Error writing into the SharedPreferences", e, null);
        }
    }

    public void setANRDataProvider(ANRDataProvider anrDataProvider) {
        this.mANRDataProvider = anrDataProvider;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean shouldReportANRs() {
        ANRDataProvider p = this.mANRDataProvider;
        return p != null && p.shouldANRDetectorRun();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private CrashReportData loadAcraCrashReport(Spool.FileBeingConsumed dumpFile) throws IOException {
        return loadCrashReport(dumpFile, CrashReportType.ACRA_CRASH_REPORT, this.mMaxReportSize, true);
    }

    private CrashReportData loadCrashAttachment(File traceFile, CrashReportType type, boolean slurpAttachment) throws IOException {
        return loadCrashReport(traceFile, null, type, type.defaultMaxSize, slurpAttachment);
    }

    private CrashReportData loadCrashAttachment(Spool.FileBeingConsumed dumpFile, CrashReportType type, boolean slurpAttachment) throws IOException {
        return loadCrashReport(dumpFile, type, type.defaultMaxSize, slurpAttachment);
    }

    private void attachLastActivityInfo(CrashReportData crashReport) throws IOException {
        File activityFile = new File(this.mContext.getFilesDir(), FILE_LAST_ACTIVITY);
        if (!activityFile.exists()) {
            crashReport.put(ReportField.LAST_ACTIVITY_LOGGED, NO_FILE);
            return;
        }
        FileReader freader = new FileReader(activityFile);
        try {
            BufferedReader breader = new BufferedReader(freader, 1024);
            try {
                String activityName = breader.readLine();
                if (activityName != null) {
                    crashReport.put(ReportField.LAST_ACTIVITY_LOGGED, activityName);
                    crashReport.put(ReportField.LAST_ACTIVITY_LOGGED_TIME, Long.toString(activityFile.lastModified()));
                }
                activityFile.delete();
                breader.close();
                freader.close();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
        throw th;
    }

    private void attachIabInfo(CrashReportData crashReport) throws IOException {
        File iabOpenTimesFile = new File(this.mContext.getFilesDir(), FILE_IAB_OPEN_TIMES);
        String iabOpenTimes = readFile(iabOpenTimesFile);
        if (NO_FILE.equals(iabOpenTimes)) {
            crashReport.put(ReportField.IAB_OPEN_TIMES, "0");
        } else if (iabOpenTimes != null) {
            crashReport.put(ReportField.IAB_OPEN_TIMES, iabOpenTimes);
        }
        if (iabOpenTimesFile.exists()) {
            iabOpenTimesFile.delete();
        }
    }

    private void slurpAttachment(CrashReportData crashReport, InputStream input, CrashReportType crashReportType, long nbytes) throws IOException {
        if (crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                attachLastActivityInfo(crashReport);
            } catch (IOException ex) {
                BLog.w(TAG, ex, "error attaching activity information");
            }
            try {
                attachIabInfo(crashReport);
            } catch (IOException ex2) {
                BLog.w(TAG, ex2, "error attaching IAB information");
            }
        }
        crashReport.put(crashReportType.attachmentField, AttachmentUtil.loadAttachment(input, (int) nbytes));
        crashReport.put(ReportField.ATTACHMENT_ORIGINAL_SIZE, String.valueOf(nbytes));
    }

    @Nullable
    private CrashReportData loadCrashReport(File traceFile, @Nullable RandomAccessFile file, CrashReportType crashReportType, long maxSize, boolean slurpAttachment) throws IOException {
        boolean tooOld;
        String fileNameString = traceFile.getName();
        long currentTime = System.currentTimeMillis();
        long fileModifiedTime = traceFile.lastModified();
        long nbytes = traceFile.length();
        String javaStack = null;
        CrashReportData crashReport = new CrashReportData();
        crashReport.put(ReportField.TIME_OF_CRASH, Long.toString(fileModifiedTime));
        long maxReportAgeMs = ((long) AcraConfig.getMaximumReportAgeSeconds(this.mContext)) * 1000;
        if (crashReportType != CrashReportType.ANR_REPORT || !AcraConfig.shouldReportOldANRs(this.mContext)) {
            tooOld = currentTime - fileModifiedTime > maxReportAgeMs;
        } else {
            tooOld = false;
        }
        String excludeReason = null;
        boolean dumpTooOldReason = false;
        int actionOnOldReports = AcraConfig.getActionOnOldReports(this.mContext);
        if (ReportField.MINIDUMP.equals(crashReportType.attachmentField) && !this.mConfig.shouldReportField(ReportField.MINIDUMP)) {
            excludeReason = "CONFIG_DONT_REPORT_DUMP";
        } else if (tooOld && actionOnOldReports != 2) {
            excludeReason = "DUMP_TOO_OLD";
            dumpTooOldReason = true;
        } else if (nbytes > maxSize) {
            crashReport.put(ReportField.ATTACHMENT_ORIGINAL_SIZE, String.valueOf(nbytes));
            excludeReason = "DUMP_TOO_BIG";
        }
        if (AcraConfig.shouldDeleteCorruptedMinidumps(this.mContext) && file != null && crashReportType == CrashReportType.NATIVE_CRASH_REPORT) {
            try {
                MinidumpReader reader = new MinidumpReader(file);
                if (reader.checkIfMinidumpCorrupted()) {
                    excludeReason = "CORRUPTED_MINIDUMP";
                    javaStack = reader.getJavaStack();
                }
            } catch (IOException e) {
                BLog.e(ACRA.LOG_TAG, e, "Failed to read minidump file");
            }
        }
        if (excludeReason != null) {
            BLog.w(ACRA.LOG_TAG, "deleting crash report %s: %s", fileNameString, excludeReason);
            deleteFile(traceFile);
            boolean doReport = true;
            if (!dumpTooOldReason || actionOnOldReports != 1) {
                if (crashReportType.attachmentField != null) {
                    crashReport.put(crashReportType.attachmentField, excludeReason);
                }
                crashReport.put(ReportField.MINIDUMP_EXCLUDE_REASON, excludeReason);
                if (javaStack != null) {
                    crashReport.put(ReportField.JAVA_STACK_FROM_DUMP, javaStack);
                }
            } else {
                doReport = false;
            }
            ExcludedReportObserver obs = this.mExcludedReportObserver;
            if (obs != null) {
                try {
                    obs.onExclude(crashReport);
                } catch (Throwable t) {
                    BLog.e(TAG, "Exception in observer", t);
                }
            }
            if (doReport) {
                return crashReport;
            }
            return null;
        }
        if (slurpAttachment) {
            InputStream inputRaw = new FileInputStream(traceFile);
            try {
                BufferedInputStream input = new BufferedInputStream(inputRaw);
                try {
                    if (crashReportType == CrashReportType.ACRA_CRASH_REPORT) {
                        crashReport.load(input);
                    } else {
                        String versionCode = null;
                        String versionName = null;
                        if (crashReportType == CrashReportType.ANR_REPORT) {
                            SigquitFileHeader header = readSigquitFileHeader(input);
                            if (header.commandLine != null) {
                                crashReport.put(ReportField.PROCESS_NAME, header.commandLine);
                            }
                            versionCode = header.versionCode;
                            versionName = header.versionName;
                        }
                        slurpAttachment(crashReport, input, crashReportType, nbytes);
                        if (!TextUtils.isEmpty(versionCode)) {
                            crashReport.put(ReportField.APP_VERSION_CODE, versionCode);
                            crashReport.put(ReportField.APP_VERSION_NAME, versionName);
                        }
                    }
                } catch (Throwable th) {
                    try {
                        input.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
                input.close();
                inputRaw.close();
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        } else {
            crashReport.getInputStreamFields().put(crashReportType.attachmentField, new InputStreamField(new FileInputStream(traceFile), true, false, traceFile.length()));
        }
        crashReport.put(ReportField.ACRA_REPORT_FILENAME, fileNameString);
        backfillCrashReportData(crashReport);
        return crashReport;
        throw th;
    }

    private static SigquitFileHeader readSigquitFileHeader(BufferedInputStream input) throws IOException {
        String versionCode = readVersionLine(input);
        String versionName = null;
        String cmdLine = null;
        if (!TextUtils.isEmpty(versionCode)) {
            versionName = readVersionLine(input);
        }
        if (input.markSupported()) {
            input.mark(1024);
            byte[] tempBuffer = new byte[1024];
            int amountRead = input.read(tempBuffer, 0, 1024);
            if (amountRead > 0) {
                Matcher m = mSigquitCmdLinePattern.matcher(new String(tempBuffer, 0, amountRead));
                if (m.find()) {
                    cmdLine = m.group(1);
                }
            }
            input.reset();
        }
        SigquitFileHeader header = new SigquitFileHeader();
        header.versionCode = versionCode;
        header.versionName = versionName;
        header.commandLine = cmdLine;
        return header;
    }

    private CrashReportData loadCrashReport(Spool.FileBeingConsumed dumpFile, CrashReportType crashReportType, long maxSize, boolean slurpAttachment) throws IOException {
        return loadCrashReport(dumpFile.fileName, dumpFile.file, crashReportType, maxSize, slurpAttachment);
    }

    private static String readVersionLine(BufferedInputStream input) throws IOException {
        int i;
        input.mark(21);
        int c = input.read();
        char[] versionChars = new char[20];
        int i2 = 0;
        while (true) {
            if (c != -1 && i2 < 20) {
                if (c != 10) {
                    if (!Character.isDigit(c) && c != 46) {
                        i = 0;
                        input.reset();
                        break;
                    }
                    i2++;
                    versionChars[i2] = (char) c;
                    c = input.read();
                } else {
                    i = i2;
                    break;
                }
            } else {
                i = i2;
            }
        }
        return new String(versionChars, 0, i);
    }

    private CrashReportData createCrashReportFromStackTrace(String stackTrace, CrashReportType crashReportType) {
        CrashReportData crashReport = new CrashReportData();
        crashReport.put(ReportField.TIME_OF_CRASH, Long.toString(System.currentTimeMillis()));
        try {
            crashReport.put(crashReportType.attachmentField, AttachmentUtil.compressToBase64String(stackTrace.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            crashReport.put(ReportField.REPORT_LOAD_THROW, "throwable: " + e.getMessage());
            BLog.e(ACRA.LOG_TAG, e, "Could not load crash report. File will be deleted.");
        }
        backfillCrashReportData(crashReport);
        return crashReport;
    }

    @Nullable
    private static String readFile(File file) {
        if (!file.exists()) {
            return NO_FILE;
        }
        try {
            FileReader freader = new FileReader(file);
            try {
                BufferedReader breader = new BufferedReader(freader, 1024);
                try {
                    String value = breader.readLine();
                    if (value != null) {
                        breader.close();
                        freader.close();
                        return value;
                    }
                    breader.close();
                    freader.close();
                    return null;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception e) {
        }
        throw th;
        throw th;
    }

    /* access modifiers changed from: package-private */
    public void backfillCrashReportData(CrashReportData crashReport) {
        String reportID = (String) crashReport.get(ReportField.REPORT_ID);
        if (reportID == null || reportID.length() == 0) {
            for (Map.Entry<String, String> e : this.mConstantFields.entrySet()) {
                if (crashReport.get(e.getKey()) == null) {
                    crashReport.put(e.getKey(), e.getValue());
                }
            }
        }
        String currentUserId = getUserId();
        String previousUid = (String) crashReport.get(ReportField.UID);
        if (!TextUtils.isEmpty(currentUserId) && TextUtils.isEmpty(previousUid)) {
            crashReport.put(ReportField.UID, currentUserId);
        }
    }

    /* access modifiers changed from: private */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean deleted = file.delete();
        if (!deleted && !file.exists()) {
            deleted = true;
        }
        BLog.d(ACRA.LOG_TAG, "Deleting error report: %s", file.getName());
        if (deleted) {
            return deleted;
        }
        BLog.w(ACRA.LOG_TAG, "Could not delete error report: %s", file.getName());
        return deleted;
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
        if (this.mConfig.shouldUseUploadService() && this.mInitializationComplete && this.mBatchUploader != null && this.mFinishedCheckingReports && this.mPendingReportWriters <= 0 && !this.mConfig.shouldOnlyWriteReport() && !this.mStartedBatchUploader) {
            final File[] traceFiles = this.mContext.getDir(ErrorReportingConstants.TRACE_UPLOAD_DIR, 0).listFiles();
            this.mStartedBatchUploader = true;
            new Thread(new Runnable() {
                /* class com.facebook.acra.ErrorReporter.AnonymousClass3 */

                public void run() {
                    ErrorReporter.this.mBatchUploader.uploadReports(traceFiles);
                }
            }).start();
        }
    }

    @ThreadSafe(enableChecks = false)
    public void checkNativeReports() {
        if (roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT) > 0) {
            checkReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        }
    }

    private void checkNativeReportsOnApplicationStart() {
        int pending = roughlyCountPendingReportsOfType(CrashReportType.NATIVE_CRASH_REPORT);
        int maxPendingMiniDumpReports = this.mConfig.getMaxPendingMiniDumpReports(pending);
        if (maxPendingMiniDumpReports < Integer.MAX_VALUE && pending > maxPendingMiniDumpReports) {
            BLog.w(TAG, "Minidump count above threshold %d", Integer.valueOf(pending));
            removeCrashFiles(CrashReportType.NATIVE_CRASH_REPORT, pending - maxPendingMiniDumpReports);
        }
        if (pending > 5) {
            ReportsSenderWorker worker = new ReportsSenderWorker(this, CrashReportType.NATIVE_CRASH_REPORT);
            Object savedStrictMode = null;
            if (Build.VERSION.SDK_INT >= 9) {
                savedStrictMode = Api9Utils.saveStrictMode();
                Api9Utils.disableStrictMode();
            }
            try {
                worker.doSend();
                if (savedStrictMode != null) {
                    Api9Utils.restoreStrictMode(savedStrictMode);
                }
            } catch (Throwable th) {
                if (savedStrictMode != null) {
                    Api9Utils.restoreStrictMode(savedStrictMode);
                }
                throw th;
            }
        }
    }

    public int roughlyCountPendingReportsOfType(CrashReportType... types) {
        if (this.mContext == null) {
            BLog.e(ACRA.LOG_TAG, "Trying to get ACRA reports but ACRA is not initialized.");
            return 0;
        }
        int count = 0;
        for (CrashReportType type : types) {
            count += type.getPendingCrashReports(this.mContext).getEstimate();
        }
        return count;
    }

    public ReportsSenderWorker checkReportsOfType(CrashReportType... types) {
        ReportsSenderWorker worker = new ReportsSenderWorker(this, types);
        worker.start();
        int roughCrashCount = roughlyCountPendingReportsOfType(types);
        StartupBlockingConfig config = this.mConfig.getStartupBlockingConfig();
        if (config != null && roughCrashCount > config.minNumQueuedReportsToBlockStartup) {
            long startTime = SystemClock.uptimeMillis();
            try {
                Log.d(ACRA.LOG_TAG, "blocking for " + config.maxTimeSpentBlockedOnUploadMs + "s to upload errors");
                if (config.notifyWhileBlockingStartup) {
                    showBlockingNotification(config);
                }
                worker.join(config.maxTimeSpentBlockedOnUploadMs);
                if (config.notifyWhileBlockingStartup) {
                    cancelBlockingNotification();
                }
                Log.d(ACRA.LOG_TAG, "done blocking");
            } catch (InterruptedException e) {
                Log.e(ACRA.LOG_TAG, "interrupted while waiting for error reports to upload");
            } finally {
                StartTimeBlockedRecorder.setDurationStartupBlocked(SystemClock.uptimeMillis() - startTime);
                StartTimeBlockedRecorder.setTotalCrashesUploaded(roughCrashCount);
            }
        }
        return worker;
    }

    @SuppressLint({"ImprovedNewApi", "CatchGeneralException", "DeprecatedMethod"})
    private void showBlockingNotification(StartupBlockingConfig config) {
        try {
            Notification.Builder builder = new Notification.Builder(this.mContext).setContentTitle(this.mContext.getString(config.notificationTitle)).setContentText(this.mContext.getString(config.notificationText)).setSmallIcon(17301543);
            if (Build.VERSION.SDK_INT >= 16) {
                Api16Utils.applyBigTextStyle(builder, this.mContext.getString(config.notificationText));
            }
            ((NotificationManager) this.mContext.getSystemService("notification")).notify(2, builder.getNotification());
        } catch (Throwable t) {
            Log.d(ACRA.LOG_TAG, "error showing notification", t);
        }
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    public static final class Api16Utils {
        private Api16Utils() {
        }

        @TargetApi(16)
        static void applyBigTextStyle(Notification.Builder nb, String text) {
            nb.setStyle(new Notification.BigTextStyle().bigText(text));
        }
    }

    private void cancelBlockingNotification() {
        ((NotificationManager) this.mContext.getSystemService("notification")).cancel(2);
    }

    public void addReportSender(ReportSender sender) {
        synchronized (this.mReportSenders) {
            this.mReportSenders.add(sender);
        }
    }

    public void removeAllReportSenders() {
        synchronized (this.mReportSenders) {
            this.mReportSenders.clear();
        }
    }

    public void setMaxReportSize(long size) {
        this.mMaxReportSize = size;
    }

    public void setReportSender(ReportSender sender) {
        synchronized (this.mReportSenders) {
            removeAllReportSenders();
            addReportSender(sender);
        }
    }

    public void setReportProxy(Proxy proxy) {
        synchronized (this.mReportSenders) {
            Iterator<ReportSender> it = this.mReportSenders.iterator();
            while (it.hasNext()) {
                ReportSender sender = it.next();
                if (sender instanceof FlexibleReportSender) {
                    ((FlexibleReportSender) sender).setProxy(proxy);
                }
            }
        }
    }

    public void registerActivity(String activityName) {
        if (activityName != null) {
            this.mActivityLogger.append(activityName);
        }
    }

    static Throwable getMostSignificantCause(Throwable e) {
        if (e instanceof NonCrashException) {
            return e;
        }
        Throwable cause = e;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }

    @Nullable
    public LogBridge getLogBridge() {
        return this.mLogBridge;
    }

    public void setLogBridge(@Nullable LogBridge bridge) {
        this.mLogBridge = bridge;
    }

    private void writeToLogBridge(String tag, String message, Throwable t, @Nullable String overrideStackTrace) {
        StackTraceElement thisMethodTrace = new Throwable().getStackTrace()[0];
        StackTraceElement[] stackTrace = t.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().equals(thisMethodTrace.getClassName()) && element.getMethodName().equals(thisMethodTrace.getMethodName())) {
                BLog.e(TAG, t, "Unable to log over log bridge due to exception.");
                return;
            }
        }
        LogBridge logBridge = getLogBridge();
        if (logBridge != null) {
            if (overrideStackTrace != null) {
                logBridge.log(tag, message + "\n" + overrideStackTrace, null);
            } else {
                logBridge.log(tag, message, t);
            }
        } else if (overrideStackTrace != null) {
            BLog.e(tag, "%s\n%s", message, overrideStackTrace);
        } else {
            BLog.e(tag, t, "%s", message);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private void removeCrashFiles(final CrashReportType reportType, int trimCount) {
        if (trimCount > 0) {
            try {
                BLog.i(TAG, "removeCrashFiles count=%d", Integer.valueOf(trimCount));
                Spool.Snapshot snapshot = reportType.getSpool(this.mContext).snapshot(new FifoSpoolComparator(), new FilenameFilter() {
                    /* class com.facebook.acra.ErrorReporter.AnonymousClass4 */

                    public boolean accept(File dir, String name) {
                        for (String extension : reportType.fileExtensions) {
                            if (name.endsWith(extension)) {
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
                while (snapshot.hasNext() && trimCount > 0) {
                    Spool.FileBeingConsumed fbc = snapshot.next();
                    if (fbc.fileName != null) {
                        if (reportType.extraFileExtensions != null) {
                            String[] strArr = reportType.extraFileExtensions;
                            int length = strArr.length;
                            for (int i = 0; i < length; i++) {
                                new File(fbc.fileName.getCanonicalPath() + strArr[i]).delete();
                            }
                        }
                        if (!fbc.fileName.delete()) {
                            BLog.w(TAG, "removeCrashFiles Crash file not deleted %s", fbc.fileName.getAbsolutePath());
                        } else {
                            trimCount--;
                        }
                    }
                }
            } catch (Throwable ex) {
                BLog.e(TAG, ex, "removeCrashFiles");
            }
        }
    }

    private void maybeRemoveAnrReports() {
        int pending = roughlyCountPendingReportsOfType(CrashReportType.CACHED_ANR_REPORT);
        int maxPendingAnrReports = this.mConfig.getMaxPendingAnrReports(pending);
        if (maxPendingAnrReports < Integer.MAX_VALUE && pending > maxPendingAnrReports) {
            BLog.w(TAG, "Anr count %d above threshold %d", Integer.valueOf(pending), Integer.valueOf(maxPendingAnrReports));
            removeCrashFiles(CrashReportType.CACHED_ANR_REPORT, pending - maxPendingAnrReports);
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

        static void restoreStrictMode(Object saved) {
            StrictMode.setThreadPolicy((StrictMode.ThreadPolicy) saved);
        }

        static void disableStrictMode() {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        }
    }

    static class DefaultSecureSettingsResolver implements SecureSettingsResolver {
        DefaultSecureSettingsResolver() {
        }

        @Override // com.facebook.acra.ErrorReporter.SecureSettingsResolver
        public String getString(ContentResolver resolver, String name) {
            return Settings.Secure.getString(resolver, name);
        }
    }
}
