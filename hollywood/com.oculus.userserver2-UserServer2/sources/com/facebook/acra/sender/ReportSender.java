package com.facebook.acra.sender;

import com.facebook.acra.CrashReportData;

public interface ReportSender {
    void send(CrashReportData crashReportData) throws ReportSenderException;

    boolean supportsMultipart();
}
