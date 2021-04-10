package com.facebook.acra.sender;

public interface FlexibleReportSender extends ReportSender {
    boolean setHost(String str);

    void setSkipSslCertsChecks(boolean z);
}
