package com.oculus.bugreportservice;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import oculus.internal.ILogCollector;

public class BugReportLogService extends IntentService {
    private static final Uri AUTHORITY = Uri.parse("content://com.oculus.bugreportservice.provider");
    private static final Uri LOGFILE = Uri.parse(AUTHORITY + "/report/logfile/id/");
    private static final Uri REPORT = Uri.parse(AUTHORITY + "/report/id/");
    ILogCollector mCollector = null;
    private final ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.bugreportservice.BugReportLogService.AnonymousClass1 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("BugReportLogService", "connected with the log collector");
            BugReportLogService.this.mCollector = ILogCollector.Stub.asInterface(iBinder);
            BugReportLogService.this.mConnectionLatch.countDown();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("BugReportLogService", "disconnected from log collector");
            BugReportLogService.this.mConnectionLatch.countDown();
        }
    };
    private CountDownLatch mConnectionLatch;

    public BugReportLogService() {
        super("BugReportLogService");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|(1:2)|3|4|5|6|7|8|(5:10|11|(1:27)|13|28)(2:14|15)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c0, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        android.util.Log.e("BugReportLogService", "Failed to collect bug report logs", r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00c6, code lost:
        r12 = com.oculus.bugreportservice.BugReportLogService.REPORT.buildUpon().appendPath(r12).build();
        r0 = new android.content.ContentValues();
        r0.put("logs", java.lang.String.valueOf(false));
        getContentResolver().update(r12, r0, null, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00e9, code lost:
        if (r11.mCollector == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ed, code lost:
        r12 = com.oculus.bugreportservice.BugReportLogService.REPORT.buildUpon().appendPath(r12).build();
        r3 = new android.content.ContentValues();
        r3.put("logs", java.lang.String.valueOf(false));
        getContentResolver().update(r12, r3, null, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0110, code lost:
        if (r11.mCollector != null) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0112, code lost:
        unbindService(r11.mConnection);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0117, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x004c */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c0 A[ExcHandler: RemoteException | IOException | IllegalArgumentException (r3v9 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onHandleIntent(android.content.Intent r12) {
        /*
        // Method dump skipped, instructions count: 280
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.bugreportservice.BugReportLogService.onHandleIntent(android.content.Intent):void");
    }
}
