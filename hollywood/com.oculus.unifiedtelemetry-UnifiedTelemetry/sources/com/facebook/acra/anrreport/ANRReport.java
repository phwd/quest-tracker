package com.facebook.acra.anrreport;

import X.AnonymousClass06;
import X.Mu;
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
import com.squareup.okhttp.internal.DiskLruCache;
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
    public static final String LOG_TAG = "ANRReport";
    public static final int MAXIMUM_NUMBER_OF_OTHER_PROCESSES_TO_REPORT = 5;
    public ANRDataProvider mANRDataProvider;
    public final Map<String, String> mANRProcessErrorProperties = new HashMap();
    public Context mContext;
    public int mCurrentAnrProcessStateIndex;
    public final ErrorReporter mErrorReporter;
    public final UUIDFileGenerator mFileGenerator;
    public int mMaxUsedAnrProcessStateIndex;
    public PerformanceMarker mPerformanceMarker;
    public File mTracesFile;

    public static class UUIDFileGenerator implements FileGenerator {
        public final Context mContext;
        public final String mDirectory;
        public final String mExtension;

        @Override // com.facebook.acra.FileGenerator
        public File generate() {
            return new File(this.mContext.getDir(this.mDirectory, 0), AnonymousClass06.A04(UUID.randomUUID().toString(), this.mExtension));
        }

        public UUIDFileGenerator(Context context, String str, String str2) {
            this.mContext = context;
            this.mExtension = str;
            this.mDirectory = str2;
        }
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        boolean delete = file.delete();
        if (delete) {
            return delete;
        }
        if (!file.exists()) {
            return true;
        }
        Mu.A06(LOG_TAG, "Could not delete error report: %s", file.getName());
        return delete;
    }

    private void addProcessErrorPropertiesToErrorReport() {
        synchronized (this.mANRProcessErrorProperties) {
            try {
                if (this.mErrorReporter.addToAnrInProgressUpdateFile(this.mANRProcessErrorProperties)) {
                    this.mANRProcessErrorProperties.clear();
                }
            } catch (IOException unused) {
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
                ErrorReporter.putCustomData(AnonymousClass06.A01(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX, i), this.mANRProcessErrorProperties.get(AnonymousClass06.A01(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX, i)));
            }
            this.mANRProcessErrorProperties.clear();
        }
    }

    public static void purgeDirectory(File file) {
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

    @Override // com.facebook.acra.anr.IANRReport
    public void finalizeAndTryToSendReport(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.clear();
            this.mCurrentAnrProcessStateIndex = 1;
        }
        ErrorReporter.putCustomData(ErrorReportingConstants.ANR_RECOVERY_DELAY_TAG, String.valueOf(j));
        if (this.mANRDataProvider != null) {
            purgeDirectory(this.mContext.getDir(ErrorReporter.SIGQUIT_DIR, 0));
        } else {
            this.mErrorReporter.prepareReports(Integer.MAX_VALUE, null, true, ErrorReporter.CrashReportType.CACHED_ANR_REPORT);
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logAmExpiration(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_AM_CONFIRMATION_EXPIRED_UPTIME, Long.toString(j));
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
    public void logMainThreadUnblocked(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_MAIN_THREAD_UNBLOCKED_UPTIME, Long.toString(j));
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logOtherProcessAnr(String str, @Nullable String str2, @Nullable String str3, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            int i = this.mCurrentAnrProcessStateIndex;
            if (i < 5) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(',');
                sb.append(j);
                sb.append(',');
                sb.append(str2);
                sb.append(',');
                sb.append(str3);
                this.mANRProcessErrorProperties.put(AnonymousClass06.A01(ErrorReportingConstants.ANR_OTHER_PROCESS_ERROR_PREFIX, i), sb.toString());
                addProcessErrorPropertiesToErrorReport();
                int i2 = this.mCurrentAnrProcessStateIndex;
                if (i2 > this.mMaxUsedAnrProcessStateIndex) {
                    this.mMaxUsedAnrProcessStateIndex = i2;
                }
                this.mCurrentAnrProcessStateIndex = i2 + 1;
            }
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

    @Override // com.facebook.acra.anr.IANRReport
    public void logProcessMonitorStart(long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_PROCESS_ERROR_DETECTION_START_TIME, Long.toString(j));
            this.mCurrentAnrProcessStateIndex = 1;
            addProcessErrorPropertiesToErrorReport();
        }
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void logSigquitData(@Nullable String str, @Nullable String str2, long j) {
        synchronized (this.mANRProcessErrorProperties) {
            this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_JAVA_CALLBACK_UPTIME, Long.toString(j));
            if (!(str == null && str2 == null)) {
                try {
                    this.mErrorReporter.amendANRReportWithSigquitData(str, str2);
                    this.mANRProcessErrorProperties.put(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, DiskLruCache.VERSION_1);
                } catch (IOException e) {
                    Mu.A02(LOG_TAG, "Failed to save SIGQUIT", e);
                }
            }
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

    public ANRReport(Context context, ErrorReporter errorReporter) {
        this.mContext = context;
        this.mErrorReporter = errorReporter;
        this.mFileGenerator = new UUIDFileGenerator(context, ".cachedreport", ErrorReporter.SIGQUIT_DIR);
        this.mCurrentAnrProcessStateIndex = 1;
        this.mMaxUsedAnrProcessStateIndex = 0;
    }

    public FileGenerator getFileGenerator() {
        return this.mFileGenerator;
    }

    public void setANRDataProvider(ANRDataProvider aNRDataProvider) {
        this.mANRDataProvider = aNRDataProvider;
    }

    public void setPerformanceMarker(PerformanceMarker performanceMarker) {
        this.mPerformanceMarker = performanceMarker;
    }

    @Override // com.facebook.acra.anr.IANRReport
    public void startReport(boolean z, @Nullable String str, @Nullable String str2, int i, boolean z2, boolean z3, long j, long j2, long j3, long j4, @Nullable String str3, @Nullable String str4, boolean z4, boolean z5, @Nullable Long l) throws IOException {
        String str5;
        OutputStream outputStream;
        long appStartTickTimeMs = j - this.mErrorReporter.getAppStartTickTimeMs();
        long appStartTickTimeMs2 = j2 - this.mErrorReporter.getAppStartTickTimeMs();
        PerformanceMarker performanceMarker = this.mPerformanceMarker;
        if (performanceMarker != null) {
            performanceMarker.markerStart();
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
        OutputStream outputStream2 = null;
        ErrorReporter.putCustomData(ErrorReportingConstants.FIRST_SIGQUIT_FROM_PROCESSES, null);
        if (z5) {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_SIGQUIT_RECORDS, SigquitRecord.getRecordsJson(l));
        }
        boolean flagValue = ACRA.getFlagValue(ACRA.SHOULD_DEDUP_DISK_PERSISTENCE_GK_CACHED);
        if (z4) {
            str5 = DiskLruCache.VERSION_1;
        } else {
            str5 = "0";
        }
        try {
            ErrorReporter.putCustomData(ErrorReportingConstants.ANR_WITH_SIGQUIT_TRACES, str5);
            if (str4 == null) {
                if (flagValue) {
                    outputStream = new ByteArrayOutputStream();
                } else {
                    File file = this.mTracesFile;
                    if (file == null) {
                        file = new UUIDFileGenerator(this.mContext, ErrorReporter.REPORTFILE_EXTENSION, ErrorReporter.SIGQUIT_DIR).generate();
                        this.mTracesFile = file;
                    }
                    outputStream = new FileOutputStream(file);
                }
                outputStream2 = outputStream;
                if (str3 != null) {
                    PrintWriter printWriter = new PrintWriter(outputStream2);
                    if (!flagValue) {
                        printWriter.println(this.mErrorReporter.getAppVersionCode());
                        printWriter.println(this.mErrorReporter.getAppVersionName());
                    }
                    printWriter.write(str3);
                    printWriter.flush();
                }
                if (flagValue) {
                    this.mErrorReporter.prepareANRReport(outputStream2.toString(), this.mFileGenerator);
                } else {
                    this.mTracesFile.getCanonicalPath();
                    this.mTracesFile.length();
                    this.mErrorReporter.prepareANRReport(this.mTracesFile, this.mFileGenerator);
                }
            } else {
                this.mErrorReporter.prepareANRReport(new File(str4), this.mFileGenerator);
            }
            PerformanceMarker performanceMarker2 = this.mPerformanceMarker;
            if (performanceMarker2 != null) {
                performanceMarker2.markerEnd(2);
            }
            if (outputStream2 != null) {
                outputStream2.close();
            }
            synchronized (this.mANRProcessErrorProperties) {
                addProcessErrorPropertiesToErrorReport();
            }
        } catch (IOException e) {
            PerformanceMarker performanceMarker3 = this.mPerformanceMarker;
            if (performanceMarker3 != null) {
                performanceMarker3.markerEnd(3);
            }
            throw e;
        } catch (Throwable th) {
            if (0 != 0) {
                outputStream2.close();
            }
            throw th;
        }
    }
}
