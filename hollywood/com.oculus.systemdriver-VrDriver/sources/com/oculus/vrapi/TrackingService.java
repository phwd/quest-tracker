package com.oculus.vrapi;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.oculus.vrapi.ITrackingService;
import java.io.IOException;

public class TrackingService extends Service {
    public static final long SVC_EXIT_DELAY_MS = 60000;
    public static final String SVC_EXIT_DELAY_PROP = "debug.oculus.tsvc.exitdelayms";
    private static final String TAG = "TrackingService";
    private Handler mHandler;
    Runnable mShutdownTask = new Runnable() {
        /* class com.oculus.vrapi.TrackingService.AnonymousClass2 */

        public void run() {
            Log.i(TrackingService.TAG, "Shutting down");
            TrackingService.this.stopSelf();
        }
    };

    public static native int nativeGetSharedMemoryFd();

    private static native void nativeInitTrackingService();

    public static native void nativeSetAnyClientConnected(boolean z);

    public static native void nativeSignalFromClient(int i);

    private static native void nativeStartTrackingService(Context context);

    private static native void nativeStopTrackingService(Context context);

    static {
        Log.d(TAG, "mobile simplified is loading library");
        System.loadLibrary("mobilesimplified");
        nativeInitTrackingService();
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        this.mHandler = new Handler();
        TrackingServiceConnections.initialize();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        nativeStartTrackingService(this);
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        setBound(true);
        return new ITrackingService.Stub() {
            /* class com.oculus.vrapi.TrackingService.AnonymousClass1 */

            @Override // com.oculus.vrapi.ITrackingService
            public ParcelFileDescriptor getSharedMemoryFileDescriptor(ITrackingServiceClient client) {
                Log.d(TrackingService.TAG, "ITrackingService.Stub().getSharedMemoryFileDescriptor()");
                int fd = TrackingService.nativeGetSharedMemoryFd();
                ParcelFileDescriptor pfd = null;
                try {
                    pfd = ParcelFileDescriptor.fromFd(fd);
                } catch (IOException e) {
                    Log.e(TrackingService.TAG, "getSharedMemoryFileDescriptor " + e);
                }
                Log.d(TrackingService.TAG, "getSharedMemoryFileDescriptor fd " + fd);
                return pfd;
            }

            @Override // com.oculus.vrapi.ITrackingService
            public ParcelFileDescriptor getTrackingSocketFileDescriptor() {
                Log.e(TrackingService.TAG, "not expecting to be here ITrackingService.Stub().getTrackingSocketFileDescriptor()");
                return null;
            }

            @Override // com.oculus.vrapi.ITrackingService
            public void sendSignal(int signal) {
                TrackingService.nativeSignalFromClient(signal);
            }

            @Override // com.oculus.vrapi.ITrackingService
            public void registerClient(ITrackingServiceClient client) {
                String processName;
                Log.d(TrackingService.TAG, "ITrackingService.Stub().registerClient(client)");
                int callingPid = Binder.getCallingPid();
                String processName2 = TrackingService.this.getProcessNameByPid(callingPid);
                if (processName2 != null) {
                    processName = processName2;
                } else {
                    processName = TrackingService.this.getPackageManager().getNameForUid(Binder.getCallingUid());
                }
                TrackingServiceConnections.registerClient(client, String.format("%s(%d)", processName, Integer.valueOf(callingPid)));
            }

            @Override // com.oculus.vrapi.ITrackingService
            public void unregisterClient(ITrackingServiceClient client) {
                if (TrackingServiceConnections.isInitialized()) {
                    TrackingServiceConnections.unregisterClient(client);
                }
            }
        };
    }

    public void onRebind(Intent intent) {
        setBound(true);
    }

    public boolean onUnbind(Intent intent) {
        setBound(false);
        TrackingServiceConnections.verifyNoClientConnections();
        return true;
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        this.mHandler.removeCallbacks(this.mShutdownTask);
        this.mHandler = null;
        TrackingServiceConnections.shutdown();
        nativeStopTrackingService(this);
        Log.d(TAG, "onDestroy complete");
    }

    private String getAndroidProperty(String name) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, name);
        } catch (Exception ex) {
            Log.w(TAG, "Error getting property", ex);
            return null;
        }
    }

    private long getAndroidPropertyLong(String name, long defaultValue) {
        String prop = getAndroidProperty(name);
        if (prop != null) {
            try {
                return Long.valueOf(prop).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    private void setBound(boolean bound) {
        nativeSetAnyClientConnected(bound);
        if (bound) {
            this.mHandler.removeCallbacks(this.mShutdownTask);
            return;
        }
        long delayMs = getAndroidPropertyLong(SVC_EXIT_DELAY_PROP, SVC_EXIT_DELAY_MS);
        if (delayMs < 0) {
            Log.d(TAG, "All clients disconnected, *NOT* shutting down.");
            return;
        }
        Log.d(TAG, "All clients disconnected, shutting down in (ms): " + delayMs);
        this.mHandler.postDelayed(this.mShutdownTask, delayMs);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getProcessNameByPid(int pid) {
        for (ActivityManager.RunningAppProcessInfo processInfo : ((ActivityManager) getSystemService("activity")).getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }
}
