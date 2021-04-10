package com.oculus.mrservice;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import java.io.File;

public class MrService extends Service {
    public static final String BOOT = "BOOT_COMPLETED";
    private static final String TAG = "MrService";

    private static native void nativeInitMrService(Context context, MrService mrService);

    private static native void nativeShutdownMrService();

    static {
        try {
            System.loadLibrary("mrservice");
            Log.i(TAG, "mrservice successfully loaded");
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "mrservice library not found");
        }
    }

    public void onCreate() {
        Log.i(TAG, "onCreate");
        startForeground(1, new Notification.Builder(this).setPriority(-1).setSmallIcon(17301659).setContentTitle("MR Service").build());
        File externalDir = getExternalFilesDir(null);
        if (externalDir == null) {
            Log.e(TAG, "onCreate: external not available or ejected.");
        } else {
            String externalDirStr = externalDir.toString();
            String extStorageState = Environment.getExternalStorageState();
            if ("mounted".equals(extStorageState)) {
                Log.i(TAG, "onCreate: external storage available read/write. Location: " + externalDirStr);
            } else if ("mounted_ro".equals(extStorageState)) {
                Log.e(TAG, "onCreate: external storage read only. Location: " + externalDirStr);
            } else {
                Log.e(TAG, "onCreate: external not available. Storage state: " + extStorageState + ", Location: " + externalDirStr);
            }
        }
        nativeInitMrService(getBaseContext(), this);
        super.onCreate();
        Log.i(TAG, "onCreate: finished.");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        nativeShutdownMrService();
        super.onDestroy();
        Log.d(TAG, "onDestroy: finished.");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }
}
