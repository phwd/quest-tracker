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

public class ANRReport implements IANRReport {
    private static final String LOG_TAG = ANRReport.class.getSimpleName();
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
        this.mFileGenerator = new UUIDFileGenerator(context, ".cachedreport", "traces");
        this.mCurrentAnrProcessStateIndex = 1;
        this.mMaxUsedAnrProcessStateIndex = 0;
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void startReport(boolean startupANRDetected, String loomTraceId, String longStallTraceId, int anrDetectorId, boolean inForegroundV1, boolean inForegroundV2, long detectionUptimeMillis, long detectorStartTime, long actualStartTime, long switchTime, String systemTraces, String tracesFileName, boolean tracesAreSigquitProduced, boolean collectSigquitRecords, Long javaCallbackUptime) throws IOException {
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
        ErrorReporter.putCustomData("anr_detected_uptime", String.valueOf(detectionUptimeMillis));
        ErrorReporter.putCustomData("anr_detect_time_tag", String.valueOf(timeSinceAppStart));
        ErrorReporter.putCustomData("anr_recovery_delay", "-1");
        ErrorReporter.putCustomData("anr_detected_pre_gkstore", String.valueOf(startupANRDetected));
        ErrorReporter.putCustomData("anr_detector_id", String.valueOf(anrDetectorId));
        ErrorReporter.putCustomData("anr_detector_start_time", String.valueOf(detectorStartTime2));
        ErrorReporter.putCustomData("anr_started_in_foreground", String.valueOf(inForegroundV1));
        ErrorReporter.putCustomData("anr_started_in_foreground_v2", String.valueOf(inForegroundV2));
        ErrorReporter.putCustomData("anr_java_callback_uptime", String.valueOf(javaCallbackUptime));
        if (actualStartTime > 0) {
            ErrorReporter.putCustomData("anr_detector_actual_start_time", String.valueOf(actualStartTime - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        if (switchTime > 0) {
            ErrorReporter.putCustomData("anr_detector_switch_time", String.valueOf(switchTime - this.mErrorReporter.getAppStartTickTimeMs()));
        }
        ErrorReporter.putCustomData("black_box_trace", loomTraceId);
        ErrorReporter.putCustomData("long_stall_trace", longStallTraceId);
        ErrorReporter.putCustomData("anr_async_broadcast_receivers", AsyncBroadcastReceiverObserver.blameActiveReceivers());
        ErrorReporter.putCustomData("first_sigquit", null);
        if (collectSigquitRecords) {
            ErrorReporter.putCustomData("anr_sigquit_records", SigquitRecord.getRecordsJson(javaCallbackUptime));
        }
        boolean dedupDiskPersistence = ACRA.getCachedShouldDedupDiskPersistence();
        OutputStream outStream = null;
        try {
            ErrorReporter.putCustomData("anr_with_sigquit_traces", tracesAreSigquitProduced ? "1" : "0");
            if (tracesFileName == null) {
                if (dedupDiskPersistence) {
                    outStream = new ByteArrayOutputStream();
                } else {
                    if (this.mTracesFile == null) {
                        this.mTracesFile = new UUIDFileGenerator(this.mContext, ".stacktrace", "traces").generate();
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

    @Override // com.facebook.acra.anr.IANRReport
    public void logOtherProcessAnr(String processName, String msg, String tag, long detectionTime) {
        synchronized (this.mANRProcessErrorProperties) {
            if (this.mCurrentAnrProcessStateIndex < 5) {
                StringBuilder builder = new StringBuilder();
                builder.append(processName).append(',').append(detectionTime).append(',').append(msg).append(',').append(tag);
                this.mANRProcessErrorProperties.put("anr_other_process_error_state_" + this.mCurrentAnrProcessStateIndex, builder.toString());
                addProcessErrorPropertiesToErrorReport();
                if (this.mCurrentAnrProcessStateIndex > this.mMaxUsedAnrProcessStateIndex) {
                    this.mMaxUsedAnrProcessStateIndex = this.mCurrentAnrProcessStateIndex;
                }
                this.mCurrentAnrProcessStateIndex++;
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSystemInfo(String systemErrorMsg, String systemTag, long detectionTime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_detected_time", Long.toString(detectionTime));
            this.mANRProcessErrorProperties.put("anr_system_error_msg", systemErrorMsg);
            this.mANRProcessErrorProperties.put("anr_system_tag", systemTag);
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorStart(long startTime) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_started_time", Long.toString(startTime));
            this.mCurrentAnrProcessStateIndex = 1;
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorFailure(long failureTime, int cause) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_detection_failure_time", Long.toString(failureTime));
            this.mANRProcessErrorProperties.put("anr_process_error_detection_failure_cause", Integer.toString(cause));
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
            ErrorReporter.putCustomData("anr_process_error_detected_time", this.mANRProcessErrorProperties.get("anr_process_error_detected_time"));
            ErrorReporter.putCustomData("anr_process_error_detection_failure_time", this.mANRProcessErrorProperties.get("anr_process_error_detection_failure_time"));
            ErrorReporter.putCustomData("anr_process_error_detection_failure_cause", this.mANRProcessErrorProperties.get("anr_process_error_detection_failure_cause"));
            ErrorReporter.putCustomData("anr_system_error_msg", this.mANRProcessErrorProperties.get("anr_system_error_msg"));
            ErrorReporter.putCustomData("anr_system_tag", this.mANRProcessErrorProperties.get("anr_system_tag"));
            ErrorReporter.putCustomData("anr_process_error_started_time", this.mANRProcessErrorProperties.get("anr_process_error_started_time"));
            ErrorReporter.putCustomData("anr_main_thread_unblocked_uptime", this.mANRProcessErrorProperties.get("anr_main_thread_unblocked_uptime"));
            ErrorReporter.putCustomData("anr_am_expired_uptime", this.mANRProcessErrorProperties.get("anr_am_expired_uptime"));
            for (int i = 1; i <= this.mMaxUsedAnrProcessStateIndex; i++) {
                ErrorReporter.putCustomData("anr_other_process_error_state_" + i, this.mANRProcessErrorProperties.get("anr_other_process_error_state_" + i));
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
        ErrorReporter.putCustomData("anr_recovery_delay", String.valueOf(recoveryDelayMs));
        if (this.mANRDataProvider == null || this.mANRDataProvider.shouldANRDetectorRun()) {
            this.mErrorReporter.prepareReports(Integer.MAX_VALUE, null, true, ErrorReporter.CrashReportType.CACHED_ANR_REPORT);
            BLog.i(LOG_TAG, "ANR Reports sent");
            return;
        }
        purgeDirectory(this.mContext.getDir("traces", 0));
        BLog.i(LOG_TAG, "ANR Reports purged");
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
