package com.oculus.os;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;

public abstract class BugReporterServiceConnection implements ServiceConnection {
    /* access modifiers changed from: protected */
    public abstract void onReportGenerated(String str);

    public BugReporterServiceConnection(Context context, Bitmap screenshot) {
        throw new RuntimeException("Stub!");
    }

    public void onServiceDisconnected(ComponentName name) {
        throw new RuntimeException("Stub!");
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        throw new RuntimeException("Stub!");
    }

    protected static Intent getLaunchIntent(String reportId) {
        throw new RuntimeException("Stub!");
    }
}
