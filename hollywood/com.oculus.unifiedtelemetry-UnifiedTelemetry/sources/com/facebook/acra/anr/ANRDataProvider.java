package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ANRDataProvider implements ANRReportProvider {
    public int detectionIntervalTimeMs() {
        return 0;
    }

    public int detectorToUse() {
        return 0;
    }

    public int getForegroundCheckPeriod() {
        return 5;
    }

    public int getRecoveryTimeout() {
        return -1;
    }

    public void provideDexStatus() {
    }

    public void provideLooperMonitorInfo() {
    }

    public void provideLooperProfileInfo() {
    }

    public void provideStats() {
    }

    @Override // com.facebook.acra.anr.ANRReportProvider
    public void reportSoftError(String str, Throwable th) {
    }

    public boolean shouldANRDetectorRun() {
        return false;
    }

    public boolean shouldAvoidMutexOnSignalHandler() {
        return false;
    }

    @Override // com.facebook.acra.anr.ANRReportProvider
    public boolean shouldCollectAndUploadANRReports() {
        return false;
    }

    public boolean shouldDedupDiskPersistence() {
        return false;
    }

    public boolean shouldLogOnSignalHandler() {
        return false;
    }

    public boolean shouldLogProcessPositionInAnrTraceFile() {
        return false;
    }

    public boolean shouldRecordSignalTime() {
        return false;
    }

    public boolean shouldReportSoftErrors() {
        return false;
    }

    public boolean shouldRunANRDetectorOnBrowserProcess() {
        return false;
    }

    public boolean shouldUploadSystemANRTraces() {
        return false;
    }
}
