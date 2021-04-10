package com.oculus.bugreportservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.oculus.os.BugReport;
import oculus.internal.IBugReportService;

public class BugReportService extends Service {
    private final IBugReportService.Stub mBinder = new IBugReportService.Stub() {
        /* class com.oculus.bugreportservice.BugReportService.AnonymousClass1 */

        public String createBugReport(boolean z) {
            return BugReportService.this.mBugReportServiceUtil.createBugReport(z);
        }

        public boolean submitBugReport(BugReport bugReport) {
            return BugReportService.this.mBugReportServiceUtil.submitBugReport(bugReport);
        }

        public boolean cancelBugReport(BugReport bugReport) {
            return BugReportService.this.mBugReportServiceUtil.cancelBugReport(bugReport);
        }
    };
    private BugReportServiceUtil mBugReportServiceUtil;
    private Context mContext;

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        this.mBugReportServiceUtil = new BugReportServiceUtil(this.mContext);
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
