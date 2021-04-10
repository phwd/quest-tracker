package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import oculus.internal.Gatekeeper;
import oculus.internal.IBugReportService;

public class BugReportClient {
    private static final String OCULUS_OS_BUG_REPORT_SERVICE = "com.oculus.bugreportservice";
    private static final String OCULUS_OS_BUG_REPORT_SERVICE_CLASS = "com.oculus.bugreportservice.BugReportService";
    private static final String OCULUS_OS_BUG_REPORT_SERVICE_NAME = ".BugReportService";
    private static final String TAG = BugReportClient.class.getSimpleName();
    private static final Gatekeeper mGatekeeper = new Gatekeeper(22);
    private String mBugReportId;
    private boolean mCollectLogs;
    private Context mContext;
    private IBugReportService service;
    private RemoteServiceConnection serviceConnection;

    public static boolean isBugReportServiceEnabled() {
        return mGatekeeper.isEnabled();
    }

    public BugReportClient(Context context, boolean collectLogs) {
        this.mContext = context;
        this.mCollectLogs = collectLogs;
        connectService();
    }

    public synchronized boolean submitBugReport(BugReport bugReport) {
        try {
            if ("0".equals(this.mBugReportId)) {
                String str = TAG;
                Log.e(str, "Failed to submit bug report Id = " + this.mBugReportId);
                return false;
            }
            bugReport.id = this.mBugReportId;
            boolean submitBugReport = this.service.submitBugReport(bugReport);
            disconnectService();
            return submitBugReport;
        } catch (RemoteException ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        } finally {
            disconnectService();
        }
    }

    public synchronized boolean cancelBugReport(BugReport bugReport) {
        try {
            bugReport.id = this.mBugReportId;
        } catch (RemoteException ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        } finally {
            disconnectService();
        }
        return this.service.cancelBugReport(bugReport);
    }

    private void connectService() {
        this.serviceConnection = new RemoteServiceConnection();
        Intent i = new Intent(OCULUS_OS_BUG_REPORT_SERVICE_CLASS);
        i.setPackage(OCULUS_OS_BUG_REPORT_SERVICE);
        this.mContext.startService(i);
        this.mContext.bindService(i, this.serviceConnection, 1);
    }

    private void disconnectService() {
        Intent i = new Intent(OCULUS_OS_BUG_REPORT_SERVICE_CLASS);
        i.setPackage(OCULUS_OS_BUG_REPORT_SERVICE);
        this.mContext.unbindService(this.serviceConnection);
        this.mContext.stopService(i);
    }

    /* access modifiers changed from: package-private */
    public class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            BugReportClient.this.service = IBugReportService.Stub.asInterface(boundService);
            Log.d(BugReportClient.TAG, "Service Connected");
            try {
                BugReportClient.this.mBugReportId = BugReportClient.this.service.createBugReport(BugReportClient.this.mCollectLogs);
            } catch (RemoteException ex) {
                Log.e(BugReportClient.TAG, ex.getMessage());
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            BugReportClient.this.service = null;
            Log.d(BugReportClient.TAG, "Service Disconnected");
        }
    }
}
