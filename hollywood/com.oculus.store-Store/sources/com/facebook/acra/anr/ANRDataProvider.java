package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ANRDataProvider implements ANRReportProvider {
    public void provideStats() {
    }

    public void provideLooperProfileInfo() {
    }

    public void provideDexStatus() {
    }

    public void provideLooperMonitorInfo() {
    }

    public boolean shouldANRDetectorRun() {
        return false;
    }

    @Override // com.facebook.acra.anr.ANRReportProvider
    public boolean shouldCollectAndUploadANRReports() {
        return false;
    }

    public boolean shouldRunANRDetectorOnBrowserProcess() {
        return false;
    }

    public boolean shouldUploadSystemANRTraces() {
        return false;
    }

    public boolean shouldDedupDiskPersistence() {
        return false;
    }

    public int detectorToUse() {
        return 0;
    }

    public int detectionIntervalTimeMs() {
        return 0;
    }

    @Override // com.facebook.acra.anr.ANRReportProvider
    public void reportSoftError(String category, Throwable cause) {
    }

    public boolean shouldReportSoftErrors() {
        return false;
    }

    public boolean shouldLogOnSignalHandler() {
        return false;
    }

    public boolean shouldAvoidMutexOnSignalHandler() {
        return false;
    }

    public int getRecoveryTimeout() {
        return -1;
    }

    public boolean shouldRecordSignalTime() {
        return false;
    }

    public boolean shouldLogProcessPositionInAnrTraceFile() {
        return false;
    }

    public int getForegroundCheckPeriod() {
        return 5;
    }
}
