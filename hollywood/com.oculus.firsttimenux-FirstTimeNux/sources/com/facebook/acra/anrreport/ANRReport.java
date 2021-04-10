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
    private static final String LOG_TAG = ANRReport.class.getSimpleName();
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

        public UUIDFileGenerator(Context context, String extension, String directory) {
            this.mContext = context;
            this.mExtension = extension;
            this.mDirectory = directory;
        }

        @Override // com.facebook.acra.FileGenerator
        public File generate() {
            return new File(this.mContext.getDir(this.mDirectory, 0), UUID.randomUUID().toString() + this.mExtension);
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
    public void startReport(boolean startupANRDetected, @Nullable String loomTraceId, @Nullable String longStallTraceId, int anrDetectorId, boolean inForegroundV1, boolean inForegroundV2, long detectionUptimeMillis, long detectorStartTime, long actualStartTime, long switchTime, @Nullable String systemTraces, @Nullable String tracesFileName, boolean tracesAreSigquitProduced, boolean collectSigquitRecords, @Nullable Long javaCallbackUptime) throws IOException {
        long timeSinceAppStart = detectionUptimeMillis - this.mErrorReporter.getAppStartTickTimeMs();
        long detectorStartTime2 = detectorStartTime - this.mErrorReporter.getAppStartTickTimeMs();
        if (this.mPerformanceMarker != null) {
            this.mPerformanceMarker.markerStart();
        }
        if (this.mANRDataProvider != null) {
            this.mANRDataProvider.provideStats();
            this.mANRDataProvider.provideLooperProfileInfo();
            this.mANRDataProvider.provideDexStatus();
            this.mANRDataProvider.provideLooperMonitorInfo();
        }
        initializeProcessErrorPropertiesOnErrorReport();
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTED_UPTIME, String.valueOf(detectionUptimeMillis));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECT_TIME_TAG, String.valueOf(timeSinceAppStart));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL);
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTED_PRE_GKSTORE, String.valueOf(startupANRDetected));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_ID, String.valueOf(anrDetectorId));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_START_TIME, String.valueOf(detectorStartTime2));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_STARTED_IN_FOREGROUND, String.valueOf(inForegroundV1));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_STARTED_IN_FOREGROUND_V2, String.valueOf(inForegroundV2));
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_JAVA_CALLBACK_UPTIME, String.valueOf(javaCallbackUptime));
        if (actualStartTime > 0) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_ACTUAL_START_TIME, String.valueOf(actualStartTime - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        if (switchTime > 0) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_DETECTOR_SWITCH_TIME, String.valueOf(switchTime - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        ErrorReporter.putCustomData(ErrorReportingConstants.BLACK_BOX_TRACE_ID, loomTraceId);
        ErrorReporter.putCustomData(ErrorReportingConstants.LONG_STALL_TRACE_ID, longStallTraceId);
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_ASYNC_BROADCAST_RECEIVERS, AsyncBroadcastReceiverObserver.blameActiveReceivers());
        ErrorReporter.putCustomData(ErrorReportingConstants.FIRST_SIGQUIT_FROM_PROCESSES, null);
        if (collectSigquitRecords) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_SIGQUIT_RECORDS, SigquitRecord.getRecordsJson(javaCallbackUptime));
        }
        boolean dedupDiskPersistence = ACRA.getCachedShouldDedupDiskPersistence();
        OutputStream outStream = null;
        try {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, tracesAreSigquitProduced ? "1" : "0");
            if (tracesFileName == null) {
                if (dedupDiskPersistence) {
                    outStream = new ByteArrayOutputStream();
                } else {
                    if (this.mTracesFile == null) {
                        this.mTracesFile = new UUIDFileGenerator(this.mContext, ErrorReporter.REPORTFILE_EXTENSION, ErrorReporter.SIGQUIT_DIR).generate();
                    }
                    outStream = new FileOutputStream(this.mTracesFile);
                }
                if (systemTraces != null) {
                    PrintWriter writer = new PrintWriter(outStream);
                    if (!dedupDiskPersistence) {
                        writer.println(this.mErrorReporter.getAppVersionCode());
                        writer.println(this.mErrorReporter.getAppVersionName());
                    }
                    writer.write(systemTraces);
                    writer.flush();
                }
                if (dedupDiskPersistence) {
                    this.mErrorReporter.prepareANRReport(outStream.toString(), this.mFileGenerator);
                } else {
                    BLog.i(LOG_TAG, "Dumped traces to: %s (%d bytes)", this.mTracesFile.getCanonicalPath(), Long.valueOf(this.mTracesFile.length()));
                    this.mErrorReporter.prepareANRReport(this.mTracesFile, this.mFileGenerator);
                }
            } else {
                this.mErrorReporter.prepareANRReport(new File(tracesFileName), this.mFileGenerator);
            }
            if (this.mPerformanceMarker != null) {
                this.mPerformanceMarker.markerEnd(2);
            }
            BLog.i(LOG_TAG, "ANR Report done");
            if (outStream != null) {
                outStream.close();
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
                outStream.close();
            }
            throw th;
        }
    }

    public FileGenerator getFileGenerator() {
        return this.mFileGenerator;
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logAmExpiration(long expirationUptime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_AM_CONFIRMATION_EXPIRED_UPTIME, Long.toString(expirationUptime));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logMainThreadUnblocked(long timestamp) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_MAIN_THREAD_UNBLOCKED_UPTIME, Long.toString(timestamp));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logOtherProcessAnr(String processName, @Nullable String msg, @Nullable String tag, long detectionTime) {
        synchronized (this.mANRProcessErrorProperties) {
            if (this.mCurrentAnrProcessStateIndex < 5) {
                StringBuilder builder = new StringBuilder();
                builder.append(processName).append(',').append(detectionTime).append(',').append(msg).append(',').append(tag);
                this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX + this.mCurrentAnrProcessStateIndex, builder.toString());
                addProcessErrorPropertiesToErrorReport();
                if (this.mCurrentAnrProcessStateIndex > this.mMaxUsedAnrProcessStateIndex) {
                    this.mMaxUsedAnrProcessStateIndex = this.mCurrentAnrProcessStateIndex;
                }
                this.mCurrentAnrProcessStateIndex++;
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSigquitData(@Nullable String sigquitData, @Nullable String sigquitFileName, long sigquitCallbackTime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_JAVA_CALLBACK_UPTIME, Long.toString(sigquitCallbackTime));
            if (!(sigquitData == null && sigquitFileName == null)) {
                try {
                    this.mErrorReporter.amendANRReportWithSigquitData(sigquitData, sigquitFileName);
                    this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, "1");
                } catch (IOException e) {
                    BLog.e(LOG_TAG, "Failed to save SIGQUIT", e);
                }
            }
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logExtraSigquit(long uptime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_EXTRA_SIGQUIT_UPTIME, Long.toString(uptime));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSystemInfo(@Nullable String systemErrorMsg, @Nullable String systemTag, long detectionTime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTED, Long.toString(detectionTime));
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_SYSTEM_ERROR_MSG, systemErrorMsg);
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_SYSTEM_TAG, systemTag);
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorStart(long startTime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_START_TIME, Long.toString(startTime));
            this.mCurrentAnrProcessStateIndex = 1;
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorFailure(long failureTime, int cause) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_TIME, Long.toString(failureTime));
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_FAILURE_CAUSE, Integer.toString(cause));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    private void addProcessErrorPropertiesToErrorReport() {
        boolean success;
        synchronized (this.mANRProcessErrorProperties) {
            try {
                success = this.mErrorReporter.addToAnrInProgressUpdateFile(this.mANRProcessErrorProperties);
            } catch (IOException e) {
                success = false;
            }
            if (success) {
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
    public void finalizeAndTryToSendReport(long recoveryDelayMs) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.clear();
            this.mCurrentAnrProcessStateIndex = 1;
        }
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, String.valueOf(recoveryDelayMs));
        if (this.mANRDataProvider == null || this.mANRDataProvider.shouldANRDetectorRun()) {
            this.mErrorReporter.prepareReports(Integer.MAX_VALUE, null, true, ErrorReporter.CrashReportType.CACHED_ANR_REPORT);
            BLog.i(LOG_TAG, "ANR Reports sent");
            return;
        }
        purgeDirectory(this.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0));
        BLog.i(LOG_TAG, "ANR Reports purged");
    }

    public void setANRDataProvider(ANRDataProvider anrDataProvider) {
        this.mANRDataProvider = anrDataProvider;
    }

    public void setPerformanceMarker(PerformanceMarker performanceMarker) {
        this.mPerformanceMarker = performanceMarker;
    }

    private static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean deleted = file.delete();
        if (!deleted && !file.exists()) {
            deleted = true;
        }
        if (deleted) {
            return deleted;
        }
        BLog.w(LOG_TAG, "Could not delete error report: %s", file.getName());
        return deleted;
    }

    private static void purgeDirectory(File dir) {
        if (!(dir == null || dir.listFiles() == null)) {
            File[] listFiles = dir.listFiles();
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    purgeDirectory(file);
                }
                deleteFile(file);
            }
        }
    }
}
