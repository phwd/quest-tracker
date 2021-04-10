package com.facebook.acra.anrreport;

import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.FileGenerator;
import com.facebook.acra.PerformanceMarker;
import com.facebook.acra.anr.ANRDataProvider;
import com.facebook.acra.anr.IANRReport;
import com.facebook.acra.anr.SigquitRecord;
import com.facebook.acra.asyncbroadcastreceiver.AsyncBroadcastReceiverObserver;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.debug.log.BLog;
import com.facebook.ipc.activity.ActivityConstants;
import com.oculus.util.constants.OculusConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;

public class ANRReport implements IANRReport {
    private static final String LOG_TAG = "ANRReport";
    private static final int MAXIMUM_NUMBER_OF_OTHER_PROCESSES_TO_REPORT = 5;
    private ANRDataProvider mANRDataProvider;
    private final Map<String, String> mANRProcessErrorProperties = new HashMap();
    private Context mContext;
    private int mCurrentAnrProcessStateIndex;
    private final ErrorReporter mErrorReporter;
    private final UUIDFileGenerator mFileGenerator;
    private int mMaxUsedAnrProcessStateIndex;
    private PerformanceMarker mPerformanceMarker;
    private File mTracesFile;

    static class UUIDFileGenerator implements FileGenerator {
        private final Context mContext;
        private final String mDirectory;
        private final String mExtension;

        public UUIDFileGenerator(Context context, String str, String str2) {
            this.mContext = context;
            this.mExtension = str;
            this.mDirectory = str2;
        }

        @Override // com.facebook.acra.FileGenerator
        public File generate() {
            File dir = this.mContext.getDir(this.mDirectory, 0);
            return new File(dir, UUID.randomUUID().toString() + this.mExtension);
        }
    }

    public ANRReport(Context context, ErrorReporter errorReporter) {
        this.mContext = context;
        this.mErrorReporter = errorReporter;
        this.mFileGenerator = new UUIDFileGenerator(context, ".cachedreport", ErrorReporter.SIGQUIT_DIR);
        this.mCurrentAnrProcessStateIndex = 1;
        this.mMaxUsedAnrProcessStateIndex = 0;
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void startReport(boolean z, @Nullable String str, @Nullable String str2, int i, boolean z2, boolean z3, long j, long j2, long j3, long j4, @Nullable String str3, @Nullable String str4, boolean z4, boolean z5, @Nullable Long l) throws IOException {
        OutputStream fileOutputStream;
        long appStartTickTimeMs = j - this.mErrorReporter.getAppStartTickTimeMs();
        long appStartTickTimeMs2 = j2 - this.mErrorReporter.getAppStartTickTimeMs();
        PerformanceMarker performanceMarker = this.mPerformanceMarker;
        if (performanceMarker != null) {
            performanceMarker.markerStart();
        }
        ANRDataProvider aNRDataProvider = this.mANRDataProvider;
        if (aNRDataProvider != null) {
            aNRDataProvider.provideStats();
            this.mANRDataProvider.provideLooperProfileInfo();
            this.mANRDataProvider.provideDexStatus();
            this.mANRDataProvider.provideLooperMonitorInfo();
        }
        initializeProcessErrorPropertiesOnErrorReport();
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTED_UPTIME, String.valueOf(j));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECT_TIME_TAG, String.valueOf(appStartTickTimeMs));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL);
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTED_PRE_GKSTORE, String.valueOf(z));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_ID, String.valueOf(i));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_START_TIME, String.valueOf(appStartTickTimeMs2));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_STARTED_IN_FOREGROUND, String.valueOf(z2));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_STARTED_IN_FOREGROUND_V2, String.valueOf(z3));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_JAVA_CALLBACK_UPTIME, String.valueOf(l));
        if (j3 > 0) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_ACTUAL_START_TIME, String.valueOf(j3 - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        if (j4 > 0) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_SWITCH_TIME, String.valueOf(j4 - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        ErrorReporter.putCustomData(ErrorReportingConstants.BLACK_BOX_TRACE_ID, str);
        ErrorReporter.putCustomData(ErrorReportingConstants.LONG_STALL_TRACE_ID, str2);
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_ASYNC_BROADCAST_RECEIVERS, AsyncBroadcastReceiverObserver.blameActiveReceivers());
        OutputStream outputStream = null;
        ErrorReporter.putCustomData(ErrorReportingConstants.FIRST_SIGQUIT_FROM_PROCESSES, null);
        if (z5) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_SIGQUIT_RECORDS, SigquitRecord.getRecordsJson(l));
        }
        boolean cachedShouldDedupDiskPersistence = ACRA.getCachedShouldDedupDiskPersistence();
        try {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, z4 ? ActivityConstants.Extras.WATCH_FEED_INJECTION : OculusConstants.DEFAULT_ENTERPRISE_USER_ID);
            if (str4 == null) {
                if (cachedShouldDedupDiskPersistence) {
                    fileOutputStream = new ByteArrayOutputStream();
                } else {
                    if (this.mTracesFile == null) {
                        this.mTracesFile = new UUIDFileGenerator(this.mContext, ErrorReporter.REPORTFILE_EXTENSION, ErrorReporter.SIGQUIT_DIR).generate();
                    }
                    fileOutputStream = new FileOutputStream(this.mTracesFile);
                }
                outputStream = fileOutputStream;
                if (str3 != null) {
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    if (!cachedShouldDedupDiskPersistence) {
                        printWriter.println(this.mErrorReporter.getAppVersionCode());
                        printWriter.println(this.mErrorReporter.getAppVersionName());
                    }
                    printWriter.write(str3);
                    printWriter.flush();
                }
                if (cachedShouldDedupDiskPersistence) {
                    this.mErrorReporter.prepareANRReport(outputStream.toString(), this.mFileGenerator);
                } else {
                    BLog.i(LOG_TAG, "Dumped traces to: %s (%d bytes)", this.mTracesFile.getCanonicalPath(), Long.valueOf(this.mTracesFile.length()));
                    this.mErrorReporter.prepareANRReport(this.mTracesFile, this.mFileGenerator);
                }
            } else {
                this.mErrorReporter.prepareANRReport(new File(str4), this.mFileGenerator);
            }
            if (this.mPerformanceMarker != null) {
                this.mPerformanceMarker.markerEnd(2);
            }
            BLog.i(LOG_TAG, "ANR Report done");
            if (outputStream != null) {
                outputStream.close();
            }
            synchronized (this.mANRProcessErrorProperties) {
                addProcessErrorPropertiesToErrorReport();
            }
        } catch (IOException e) {
            if (this.mPerformanceMarker != null) {
                this.mPerformanceMarker.markerEnd(3);
            }
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                outputStream.close();
            }
            throw th;
        }
    }

    public FileGenerator getFileGenerator() {
        return this.mFileGenerator;
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logAmExpiration(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_AM_CONFIRMATION_EXPIRED_UPTIME, Long.toString(j));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logMainThreadUnblocked(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_MAIN_THREAD_UNBLOCKED_UPTIME, Long.toString(j));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logOtherProcessAnr(String str, @Nullable String str2, @Nullable String str3, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            if (this.mCurrentAnrProcessStateIndex < 5) {
                this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX + this.mCurrentAnrProcessStateIndex, str + ',' + j + ',' + str2 + ',' + str3);
                addProcessErrorPropertiesToErrorReport();
                if (this.mCurrentAnrProcessStateIndex > this.mMaxUsedAnrProcessStateIndex) {
                    this.mMaxUsedAnrProcessStateIndex = this.mCurrentAnrProcessStateIndex;
                }
                this.mCurrentAnrProcessStateIndex++;
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSigquitData(@Nullable String str, @Nullable String str2, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_JAVA_CALLBACK_UPTIME, Long.toString(j));
            if (!(str == null && str2 == null)) {
                try {
                    this.mErrorReporter.amendANRReportWithSigquitData(str, str2);
                    this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, ActivityConstants.Extras.WATCH_FEED_INJECTION);
                } catch (IOException e) {
                    BLog.e(LOG_TAG, "Failed to save SIGQUIT", e);
                }
            }
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logExtraSigquit(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_EXTRA_SIGQUIT_UPTIME, Long.toString(j));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSystemInfo(@Nullable String str, @Nullable String str2, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTED, Long.toString(j));
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, str);
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_SYSTEM_TAG, str2);
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorStart(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_START_TIME, Long.toString(j));
            this.mCurrentAnrProcessStateIndex = 1;
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorFailure(long j, int i) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_TIME, Long.toString(j));
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_CAUSE, Integer.toString(i));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    private void addProcessErrorPropertiesToErrorReport() {
        boolean z;
        synchronized (this.mANRProcessErrorProperties) {
            try {
                z = this.mErrorReporter.addToAnrInProgressUpdateFile(this.mANRProcessErrorProperties);
            } catch (IOException unused) {
                z = false;
            }
            if (z) {
                this.mANRProcessErrorProperties.clear();
            }
        }
    }

    private void initializeProcessErrorPropertiesOnErrorReport() {
        synchronized (this.mANRProcessErrorProperties) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTED, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTED));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_TIME, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_TIME));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_CAUSE, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_CAUSE));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_SYSTEM_TAG, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_SYSTEM_TAG));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_START_TIME, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_START_TIME));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_MAIN_THREAD_UNBLOCKED_UPTIME, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_MAIN_THREAD_UNBLOCKED_UPTIME));
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_AM_CONFIRMATION_EXPIRED_UPTIME, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_AM_CONFIRMATION_EXPIRED_UPTIME));
            for (int i = 1; i <= this.mMaxUsedAnrProcessStateIndex; i++) {
                ErrorReporter.putCustomData(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX + i, this.mANRProcessErrorProperties.get(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX + i));
            }
            this.mANRProcessErrorProperties.clear();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void finalizeAndTryToSendReport(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.clear();
            this.mCurrentAnrProcessStateIndex = 1;
        }
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, String.valueOf(j));
        ANRDataProvider aNRDataProvider = this.mANRDataProvider;
        if (aNRDataProvider == null || aNRDataProvider.shouldANRDetectorRun()) {
            this.mErrorReporter.prepareReports(Integer.MAX_VALUE, null, true, ErrorReporter.CrashReportType.CACHED_ANR_REPORT);
            BLog.i(LOG_TAG, "ANR Reports sent");
            return;
        }
        purgeDirectory(this.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0));
        BLog.i(LOG_TAG, "ANR Reports purged");
    }

    public void setANRDataProvider(ANRDataProvider aNRDataProvider) {
        this.mANRDataProvider = aNRDataProvider;
    }

    public void setPerformanceMarker(PerformanceMarker performanceMarker) {
        this.mPerformanceMarker = performanceMarker;
    }

    private static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean delete = file.delete();
        if (!delete && !file.exists()) {
            delete = true;
        }
        if (!delete) {
            BLog.w(LOG_TAG, "Could not delete error report: %s", file.getName());
        }
        return delete;
    }

    private static void purgeDirectory(File file) {
        if (!(file == null || file.listFiles() == null)) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    purgeDirectory(file2);
                }
                deleteFile(file2);
            }
        }
    }
}
