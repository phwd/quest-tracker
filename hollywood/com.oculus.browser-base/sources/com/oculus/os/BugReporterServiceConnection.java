package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BugReporterServiceConnection implements ServiceConnection {
    public BugReporterServiceConnection(Context context, Bitmap bitmap) {
        throw new RuntimeException("Stub!");
    }

    public static Intent getLaunchIntent(String str) {
        throw new RuntimeException("Stub!");
    }

    public abstract void onReportGenerated(String str);

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        throw new RuntimeException("Stub!");
    }

    public void onServiceDisconnected(ComponentName componentName) {
        throw new RuntimeException("Stub!");
    }
}
