package com.oculus.vrruntimeservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class ShellOverlayClient {
    private static final String OVERLAY_SERVICE_CLASS = "com.oculus.vrshell.ShellOverlayService";
    private static final String SHELL_PACKAGE = "com.oculus.vrshell";
    private static final String TAG = "ShellOverlayClient";
    private IBinder mBinder;
    private Context mContext;
    private ServiceConnection mServiceConnection;

    public ShellOverlayClient(Context context) {
        this.mContext = context;
        connect();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void connect() {
        Intent overlayIntent = new Intent();
        overlayIntent.setComponent(new ComponentName("com.oculus.vrshell", OVERLAY_SERVICE_CLASS));
        this.mServiceConnection = new ServiceConnection() {
            /* class com.oculus.vrruntimeservice.ShellOverlayClient.AnonymousClass1 */

            public void onServiceConnected(ComponentName className, IBinder service) {
                Log.d(ShellOverlayClient.TAG, "onServiceConnected className = " + className + "   service = " + service);
                ShellOverlayClient.this.mBinder = service;
            }

            public void onServiceDisconnected(ComponentName className) {
                Log.d(ShellOverlayClient.TAG, "onServiceDisconnected");
                ShellOverlayClient.this.disconnect();
                ShellOverlayClient.this.connect();
            }
        };
        Log.i(TAG, "Attempting to bind to ShellOverlayService");
        this.mContext.bindService(overlayIntent, this.mServiceConnection, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void disconnect() {
        ServiceConnection serviceConnection;
        Context context = this.mContext;
        if (!(context == null || (serviceConnection = this.mServiceConnection) == null)) {
            context.unbindService(serviceConnection);
        }
        this.mBinder = null;
        this.mServiceConnection = null;
    }
}
