package com.oculus.bodyapiservice;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;

public class BodyApiService extends Service {
    private static String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    private static final int REQUEST_CODE = 10101;
    private static final String TAG = "BodyApiService";
    private static ScreenStateReceiver screenStateReceiver = null;

    private static native void nativeInit(Context context, BodyApiService bodyApiService, boolean z);

    /* access modifiers changed from: private */
    public static native void nativeMountStateChanged(boolean z);

    /* access modifiers changed from: private */
    public static native void nativeScreenStateChanged(boolean z);

    private static native void nativeShutdown();

    static {
        System.loadLibrary("bodyapiservice");
    }

    private boolean getScreenState() {
        return ((WindowManager) getSystemService("window")).getDefaultDisplay().getState() != 1;
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        startForeground(1, new Notification.Builder(this).setPriority(-1).setSmallIcon(17301659).setContentTitle(TAG).build());
        nativeInit(getBaseContext(), this, getScreenState());
        screenStateReceiver = new ScreenStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction(ACTION_MOUNT_STATE_CHANGED);
        registerReceiver(screenStateReceiver, filter);
        super.onCreate();
        Log.d(TAG, "onCreate - finished");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        unregisterReceiver(screenStateReceiver);
        screenStateReceiver = null;
        super.onDestroy();
        nativeShutdown();
        Log.d(TAG, "onDestroy - finished");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return 2;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    public void stopBodyApiService() {
        Log.d(TAG, "stopBodyApiService");
        stopSelf();
    }

    public class ScreenStateReceiver extends BroadcastReceiver {
        public ScreenStateReceiver() {
        }

        public void onReceive(Context context, final Intent intent) {
            final String action = intent.getAction();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* class com.oculus.bodyapiservice.BodyApiService.ScreenStateReceiver.AnonymousClass1 */

                public void run() {
                    if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        BodyApiService.nativeScreenStateChanged(false);
                    } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                        BodyApiService.nativeScreenStateChanged(true);
                    } else if (BodyApiService.ACTION_MOUNT_STATE_CHANGED.equals(action)) {
                        BodyApiService.nativeMountStateChanged(intent.getExtras().getBoolean("state"));
                    } else {
                        Log.e(BodyApiService.TAG, "Invalid intent action");
                    }
                }
            });
        }
    }
}
