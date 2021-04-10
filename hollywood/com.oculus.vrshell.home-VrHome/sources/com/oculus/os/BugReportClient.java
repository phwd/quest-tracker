package com.oculus.os;

import android.content.Context;

public class BugReportClient {
    public BugReportClient(Context context, boolean collectLogs) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isBugReportServiceEnabled() {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean submitBugReport(BugReport bugReport) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean cancelBugReport(BugReport bugReport) {
        throw new RuntimeException("Stub!");
    }
}
