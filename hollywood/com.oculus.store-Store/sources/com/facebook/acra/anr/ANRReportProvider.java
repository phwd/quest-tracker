package com.facebook.acra.anr;

public interface ANRReportProvider {
    void reportSoftError(String str, Throwable th);

    boolean shouldCollectAndUploadANRReports();
}
