package com.facebook.acra.anrreport;

import android.content.Context;
import com.facebook.acra.ACRA;
import com.facebook.acra.ErrorReporter;
import com.facebook.acra.FileGenerator;
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
    private static final String LOG_TAG = "ANRReport";
    private ANRDataProvider mANRDataProvider;
    private final Map<String, String> mANRProcessErrorProperties = new HashMap();
    private Context mContext;
    private int mCurrentAnrProcessStateIndex;
    private final ErrorReporter mErrorReporter;
    private final UUIDFileGenerator mFileGenerator;
    private int mMaxUsedAnrProcessStateIndex;
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
        public final File generate() {
            File dir = this.mContext.getDir(this.mDirectory, 0);
            return new File(dir, UUID.randomUUID().toString() + this.mExtension);
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
    public final void startReport(boolean z, String str, String str2, int i, boolean z2, boolean z3, long j, long j2, long j3, long j4, String str3, String str4, boolean z4, boolean z5, Long l) throws IOException {
        OutputStream fileOutputStream;
        long j5 = j - this.mErrorReporter.mAppStartTickTimeMs;
        long j6 = j2 - this.mErrorReporter.mAppStartTickTimeMs;
        synchronized (this.mANRProcessErrorProperties) {
            ErrorReporter.putCustomDataInternal("anr_process_error_detected_time", this.mANRProcessErrorProperties.get("anr_process_error_detected_time"));
            ErrorReporter.putCustomDataInternal("anr_process_error_detection_failure_time", this.mANRProcessErrorProperties.get("anr_process_error_detection_failure_time"));
            ErrorReporter.putCustomDataInternal("anr_process_error_detection_failure_cause", this.mANRProcessErrorProperties.get("anr_process_error_detection_failure_cause"));
            ErrorReporter.putCustomDataInternal("anr_system_error_msg", this.mANRProcessErrorProperties.get("anr_system_error_msg"));
            ErrorReporter.putCustomDataInternal("anr_system_tag", this.mANRProcessErrorProperties.get("anr_system_tag"));
            ErrorReporter.putCustomDataInternal("anr_process_error_started_time", this.mANRProcessErrorProperties.get("anr_process_error_started_time"));
            ErrorReporter.putCustomDataInternal("anr_main_thread_unblocked_uptime", this.mANRProcessErrorProperties.get("anr_main_thread_unblocked_uptime"));
            ErrorReporter.putCustomDataInternal("anr_am_expired_uptime", this.mANRProcessErrorProperties.get("anr_am_expired_uptime"));
            for (int i2 = 1; i2 <= this.mMaxUsedAnrProcessStateIndex; i2++) {
                ErrorReporter.putCustomDataInternal("anr_other_process_error_state_" + i2, this.mANRProcessErrorProperties.get("anr_other_process_error_state_" + i2));
            }
            this.mANRProcessErrorProperties.clear();
        }
        ErrorReporter.putCustomDataInternal("anr_detected_uptime", String.valueOf(j));
        ErrorReporter.putCustomDataInternal("anr_detect_time_tag", String.valueOf(j5));
        ErrorReporter.putCustomDataInternal("anr_recovery_delay", "-1");
        ErrorReporter.putCustomDataInternal("anr_detected_pre_gkstore", String.valueOf(z));
        ErrorReporter.putCustomDataInternal("anr_detector_id", String.valueOf(i));
        ErrorReporter.putCustomDataInternal("anr_detector_start_time", String.valueOf(j6));
        ErrorReporter.putCustomDataInternal("anr_started_in_foreground", String.valueOf(z2));
        ErrorReporter.putCustomDataInternal("anr_started_in_foreground_v2", String.valueOf(z3));
        ErrorReporter.putCustomDataInternal("anr_java_callback_uptime", String.valueOf(l));
        if (j3 > 0) {
            ErrorReporter.putCustomDataInternal("anr_detector_actual_start_time", String.valueOf(j3 - this.mErrorReporter.mAppStartTickTimeMs));
        }
        if (j4 > 0) {
            ErrorReporter.putCustomDataInternal("anr_detector_switch_time", String.valueOf(j4 - this.mErrorReporter.mAppStartTickTimeMs));
        }
        ErrorReporter.putCustomDataInternal("black_box_trace", str);
        ErrorReporter.putCustomDataInternal("long_stall_trace", str2);
        ErrorReporter.putCustomDataInternal("anr_async_broadcast_receivers", AsyncBroadcastReceiverObserver.blameActiveReceivers());
        OutputStream outputStream = null;
        ErrorReporter.putCustomDataInternal("first_sigquit", null);
        if (z5) {
            ErrorReporter.putCustomDataInternal("anr_sigquit_records", SigquitRecord.getRecordsJson(l));
        }
        boolean cachedShouldDedupDiskPersistence = ACRA.getCachedShouldDedupDiskPersistence();
        try {
            ErrorReporter.putCustomDataInternal("anr_with_sigquit_traces", z4 ? "1" : "0");
            if (str4 == null) {
                if (cachedShouldDedupDiskPersistence) {
                    fileOutputStream = new ByteArrayOutputStream();
                } else {
                    if (this.mTracesFile == null) {
                        this.mTracesFile = new UUIDFileGenerator(this.mContext, ".stacktrace", "traces").generate();
                    }
                    fileOutputStream = new FileOutputStream(this.mTracesFile);
                }
                outputStream = fileOutputStream;
                if (str3 != null) {
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    if (!cachedShouldDedupDiskPersistence) {
                        printWriter.println(this.mErrorReporter.mAppVersionCode);
                        printWriter.println(this.mErrorReporter.mAppVersionName);
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
            BLog.i(LOG_TAG, "ANR Report done");
            if (outputStream != null) {
                outputStream.close();
            }
            synchronized (this.mANRProcessErrorProperties) {
                addProcessErrorPropertiesToErrorReport();
            }
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                outputStream.close();
            }
            throw th;
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public final void logMainThreadUnblocked(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_main_thread_unblocked_uptime", Long.toString(j));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public final void logOtherProcessAnr(String str, String str2, String str3, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            if (this.mCurrentAnrProcessStateIndex < 5) {
                this.mANRProcessErrorProperties.put("anr_other_process_error_state_" + this.mCurrentAnrProcessStateIndex, str + ',' + j + ',' + str2 + ',' + str3);
                addProcessErrorPropertiesToErrorReport();
                if (this.mCurrentAnrProcessStateIndex > this.mMaxUsedAnrProcessStateIndex) {
                    this.mMaxUsedAnrProcessStateIndex = this.mCurrentAnrProcessStateIndex;
                }
                this.mCurrentAnrProcessStateIndex++;
            }
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public final void logSystemInfo(String str, String str2, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_detected_time", Long.toString(j));
            this.mANRProcessErrorProperties.put("anr_system_error_msg", str);
            this.mANRProcessErrorProperties.put("anr_system_tag", str2);
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public final void logProcessMonitorStart(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_started_time", Long.toString(j));
            this.mCurrentAnrProcessStateIndex = 1;
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public final void logProcessMonitorFailure(long j, int i) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put("anr_process_error_detection_failure_time", Long.toString(j));
            this.mANRProcessErrorProperties.put("anr_process_error_detection_failure_cause", Integer.toString(i));
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

    @Override // com.facebook.acra.anr.IANRReport
    public final void finalizeAndTryToSendReport(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.clear();
            this.mCurrentAnrProcessStateIndex = 1;
        }
        ErrorReporter.putCustomDataInternal("anr_recovery_delay", String.valueOf(j));
        if (this.mANRDataProvider != null) {
            purgeDirectory(this.mContext.getDir("traces", 0));
            BLog.i(LOG_TAG, "ANR Reports purged");
            return;
        }
        this.mErrorReporter.prepareReports(Integer.MAX_VALUE, null, true, ErrorReporter.CrashReportType.CACHED_ANR_REPORT);
        BLog.i(LOG_TAG, "ANR Reports sent");
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
