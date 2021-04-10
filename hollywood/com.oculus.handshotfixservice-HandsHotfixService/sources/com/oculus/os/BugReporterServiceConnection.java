package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.aidl.IBugReporterService;
import java.io.IOException;
import oculus.internal.Constants;

public abstract class BugReporterServiceConnection implements ServiceConnection {
    private static final int SCREENSHOT_COMPRESS_QUALITY = 100;
    private static final Bitmap.CompressFormat SCREENSHOT_DST_FORMAT = Bitmap.CompressFormat.PNG;
    private static final String SHELL_HOME_URI_KEY = "uri";
    private static final String TAG = BugReporterServiceConnection.class.getSimpleName();
    private final Context mContext;
    private final Bitmap mScreenshot;

    /* access modifiers changed from: protected */
    public abstract void onReportGenerated(String str);

    public BugReporterServiceConnection(Context context, Bitmap screenshot) {
        this.mContext = context;
        this.mScreenshot = screenshot;
    }

    public void onServiceDisconnected(ComponentName name) {
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "onServiceConnected()");
        IBugReporterService bugReporterService = IBugReporterService.Stub.asInterface(service);
        if (bugReporterService != null) {
            onBugReporterServiceConnected(bugReporterService);
        } else {
            Log.e(TAG, "could not fetch interface for bug reporter service!");
        }
    }

    private void onBugReporterServiceConnected(IBugReporterService service) {
        try {
            String reportId = service.getReportId();
            if (reportId == null) {
                Log.e(TAG, "Could not file bug report: getReportId() returned a null ID!");
                return;
            }
            try {
                final ParcelFileDescriptor screenshotFd = service.openScreenshotFile();
                if (screenshotFd == null) {
                    Log.e(TAG, "Could not file bug report: openScreenshotFile() returned null!");
                    return;
                }
                AsyncTask.execute(new Runnable() {
                    /* class com.oculus.os.BugReporterServiceConnection.AnonymousClass1 */

                    public void run() {
                        BugReporterServiceConnection.writeScreenshotToFile(screenshotFd, BugReporterServiceConnection.this.mScreenshot, BugReporterServiceConnection.SCREENSHOT_DST_FORMAT, 100);
                        BugReporterServiceConnection.this.mContext.unbindService(BugReporterServiceConnection.this);
                    }
                });
                onReportGenerated(reportId);
            } catch (RemoteException e) {
                String str = TAG;
                Log.e(str, "Could not get screenshot file: " + e.getMessage());
            }
        } catch (RemoteException e2) {
            String str2 = TAG;
            Log.e(str2, "Could not get report Id: " + e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static void writeScreenshotToFile(ParcelFileDescriptor dstFd, Bitmap screenshot, Bitmap.CompressFormat format, int compressQuality) {
        ParcelFileDescriptor.AutoCloseOutputStream dstStream = new ParcelFileDescriptor.AutoCloseOutputStream(dstFd);
        if (screenshot != null) {
            screenshot.compress(format, compressQuality, dstStream);
        } else {
            Log.w(TAG, "A null screenshot was received by BugReporter.");
        }
        try {
            dstFd.close();
        } catch (IOException e) {
            String str = TAG;
            Log.w(str, "Error while saving screenshot: " + e.getMessage());
        }
    }

    protected static Intent getLaunchIntent(String reportId) {
        ComponentName shell = new ComponentName(Constants.SHELL_PACKAGE, "com.oculus.vrshell.MainActivity");
        StringBuilder sb = new StringBuilder();
        sb.append("/system_utilities/bugReport/");
        sb.append("com.oculus.unknow/");
        StringBuilder uri = sb.append(reportId);
        Intent startIntent = new Intent();
        startIntent.addFlags(65536);
        startIntent.setComponent(shell);
        startIntent.setData(Uri.parse("com.oculus.vrshell.home/com.oculus.vrshell.home.SystemUtilitiesService"));
        startIntent.putExtra(SHELL_HOME_URI_KEY, uri.toString());
        return startIntent;
    }
}
