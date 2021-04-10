package com.oculus.runtimeipcserviceclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.runtimeipcservice.IRuntimeIPCService;
import com.oculus.runtimeipcservice.IRuntimeIPCServiceClient;
import com.oculus.systemdriver.BuildConfig;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RuntimeIPCServiceClient {
    private static final String TAG = "RuntimeIPCServiceClient";
    private static WeakReference<Context> cachedContext = null;
    private static IBinder clientToken = new Binder();
    private static Handler handler = null;
    private static HandlerThread handlerThread = null;
    private static long ipcHandlerPtrLong = 0;
    private static IRuntimeIPCService ipcService = null;
    private static IRuntimeIPCServiceClient ipcServiceClient = null;
    private static IPCServiceConnection ipcServiceConnection = null;
    private static LinkDeathHandler linkDeathHandler = new LinkDeathHandler();
    private static String processName = "";

    private static native void nativeConnected(long j, boolean z);

    static {
        System.loadLibrary("runtimeipcserviceclient");
    }

    public static boolean connectToService() {
        Log.d(TAG, "connectToService");
        return ensureConnection();
    }

    private static Context getContext() {
        WeakReference<Context> weakReference = cachedContext;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return cachedContext.get();
    }

    /* access modifiers changed from: private */
    public static class LinkDeathHandler implements IBinder.DeathRecipient {
        private LinkDeathHandler() {
        }

        public void binderDied() {
            Log.e(RuntimeIPCServiceClient.TAG, "ipc service has died, finishing");
            RuntimeIPCServiceClient.doDisconnect();
        }
    }

    /* access modifiers changed from: package-private */
    public static class IPCServiceConnection implements ServiceConnection {
        CountDownLatch mSignalOnConnected = null;

        public IPCServiceConnection(CountDownLatch signalOnConnected) {
            this.mSignalOnConnected = signalOnConnected;
        }

        public void onServiceConnected(ComponentName name, IBinder binder) {
            synchronized (RuntimeIPCServiceClient.class) {
                Log.d(RuntimeIPCServiceClient.TAG, "Service connected: " + binder);
                if (RuntimeIPCServiceClient.ipcServiceConnection != this) {
                    Log.e(RuntimeIPCServiceClient.TAG, "onServiceConnected with stale ipcServiceConnection object!!!");
                }
                IRuntimeIPCService unused = RuntimeIPCServiceClient.ipcService = IRuntimeIPCService.Stub.asInterface(binder);
                try {
                    binder.linkToDeath(RuntimeIPCServiceClient.linkDeathHandler, 0);
                } catch (Exception ex) {
                    Log.d(RuntimeIPCServiceClient.TAG, "onServiceConnected: linkToDeath Exception: " + ex);
                }
                try {
                    IRuntimeIPCServiceClient unused2 = RuntimeIPCServiceClient.ipcServiceClient = RuntimeIPCServiceClient.ipcService.createClient(RuntimeIPCServiceClient.clientToken, RuntimeIPCServiceClient.processName);
                } catch (Exception ex2) {
                    Log.d(RuntimeIPCServiceClient.TAG, "createClient exception " + ex2);
                }
                if (this.mSignalOnConnected != null) {
                    this.mSignalOnConnected.countDown();
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            synchronized (RuntimeIPCServiceClient.class) {
                if (RuntimeIPCServiceClient.ipcServiceConnection == this) {
                    Log.d(RuntimeIPCServiceClient.TAG, "Service disconnected.");
                    RuntimeIPCServiceClient.doDisconnect();
                } else {
                    Log.e(RuntimeIPCServiceClient.TAG, "Service disconnected (ignoring from older service connection).");
                }
            }
        }
    }

    private static Intent CreateIPCServiceIntent() {
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.oculus.runtimeipcservice.RuntimeIPCService"));
        return serviceIntent;
    }

    public static void initialize(Context inContext, String inProcessName, long inIpcHandlerPtrLong) {
        if (cachedContext != null) {
            Log.d(TAG, "initialize. cachedContext != nul");
            return;
        }
        cachedContext = new WeakReference<>(inContext);
        processName = inProcessName;
        ipcHandlerPtrLong = inIpcHandlerPtrLong;
        try {
            BindServiceWrapper.init();
        } catch (Exception ex) {
            Log.e(TAG, "BindServiceWrapper.Init exception: " + ex);
        }
        Log.d(TAG, "initialize. inContext: " + inContext + "processName: " + processName);
        handlerThread = new HandlerThread("RuntimeIPCServiceClientConnectionThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        Context context = getContext();
        if (context != null) {
            try {
                context.startService(CreateIPCServiceIntent());
            } catch (Exception ex2) {
                Log.e(TAG, "initialize: startService exception: " + ex2);
            }
        }
    }

    public static void shutdown() {
        Log.d(TAG, "shutdown.");
        doDisconnect();
        HandlerThread handlerThread2 = handlerThread;
        if (handlerThread2 != null) {
            handlerThread2.quitSafely();
            handlerThread = null;
        }
        handler = null;
        cachedContext = null;
    }

    /* access modifiers changed from: private */
    public static void doDisconnect() {
        synchronized (RuntimeIPCServiceClient.class) {
            Log.d(TAG, "Performing disconnect");
            if (ipcService != null) {
                try {
                    ipcServiceClient.destroy();
                } catch (Exception ex) {
                    Log.d(TAG, "destroyClient: Exception: " + ex);
                }
                try {
                    ipcService.asBinder().unlinkToDeath(linkDeathHandler, 0);
                } catch (Exception ex2) {
                    Log.d(TAG, "unlinkToDeath: Exception: " + ex2);
                }
                ipcService = null;
                ipcServiceClient = null;
            }
            if (ipcServiceConnection != null) {
                Context context = getContext();
                if (context != null) {
                    try {
                        context.unbindService(ipcServiceConnection);
                    } catch (Exception ex3) {
                        Log.d(TAG, "unbindService: Exception: " + ex3);
                    }
                }
                ipcServiceConnection = null;
            }
            nativeConnected(ipcHandlerPtrLong, false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isConnected() {
        /*
            java.lang.Class<com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient> r0 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.class
            monitor-enter(r0)
            com.oculus.runtimeipcservice.IRuntimeIPCServiceClient r1 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcServiceClient     // Catch:{ all -> 0x004e }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0014
            com.oculus.runtimeipcservice.IRuntimeIPCService r1 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcService     // Catch:{ all -> 0x004e }
            if (r1 != 0) goto L_0x0014
            com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient$IPCServiceConnection r1 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcServiceConnection     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = 0
            goto L_0x0015
        L_0x0014:
            r1 = 1
        L_0x0015:
            if (r1 == 0) goto L_0x004c
            com.oculus.runtimeipcservice.IRuntimeIPCServiceClient r4 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcServiceClient     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x0028
            java.lang.String r3 = "RuntimeIPCServiceClient"
            java.lang.String r4 = "ipcServiceClient is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x0028:
            com.oculus.runtimeipcservice.IRuntimeIPCService r4 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcService     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x0039
            java.lang.String r3 = "RuntimeIPCServiceClient"
            java.lang.String r4 = "ipcService is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x0039:
            com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient$IPCServiceConnection r4 = com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.ipcServiceConnection     // Catch:{ all -> 0x004e }
            if (r4 != 0) goto L_0x004a
            java.lang.String r3 = "RuntimeIPCServiceClient"
            java.lang.String r4 = "ipcServiceConnection is invalid but shouldBeValid is true"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x004e }
            doDisconnect()     // Catch:{ all -> 0x004e }
            goto L_0x004c
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r3
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            return r2
        L_0x004e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.runtimeipcserviceclient.RuntimeIPCServiceClient.isConnected():boolean");
    }

    public static boolean ensureConnection() {
        if (isConnected()) {
            return true;
        }
        Log.d(TAG, "creating new service");
        Context context = getContext();
        if (context == null) {
            Log.w(TAG, "No context.");
            return false;
        }
        for (int numTries = 0; numTries < 5; numTries++) {
            if (numTries > 0) {
                Log.e(TAG, "EnsureConnection: Trying again: " + numTries);
            }
            Intent ipcServiceIntent = CreateIPCServiceIntent();
            try {
                context.startService(ipcServiceIntent);
            } catch (Exception ex) {
                Log.e(TAG, "EnsureConnection: ipc startService exception: " + ex);
            }
            CountDownLatch ipcConnectedSignal = new CountDownLatch(1);
            ipcServiceConnection = new IPCServiceConnection(ipcConnectedSignal);
            try {
                if (BindServiceWrapper.bindServiceWithHandler(context, ipcServiceIntent, ipcServiceConnection, 0, handler)) {
                    Log.d(TAG, "Waiting on ipc connection...");
                    boolean connectionIsReady = ipcConnectedSignal.await(5, TimeUnit.SECONDS);
                    Log.d(TAG, "ipc connectionIsReady: " + connectionIsReady);
                } else {
                    Log.e(TAG, "ipc bindServiceWithHandler failed.");
                    ipcServiceConnection = null;
                    ipcService = null;
                    ipcServiceClient = null;
                }
            } catch (Exception ex2) {
                Log.e(TAG, "ipc EnsureConnection. bindServiceWithHandler exception:" + ex2);
                ipcServiceConnection = null;
                ipcService = null;
                ipcServiceClient = null;
            }
            if (ipcService != null && ipcServiceClient != null) {
                Log.d(TAG, "ipc context.bindService SUCCESS");
                nativeConnected(ipcHandlerPtrLong, true);
                return true;
            }
        }
        Log.e(TAG, "ipc context.bindService FAILURE.");
        return false;
    }

    public static boolean resetClient() {
        Log.d(TAG, "resetClient");
        if (!ensureConnection()) {
            return false;
        }
        try {
            if (ipcServiceClient != null) {
                return ipcServiceClient.resetClient();
            }
        } catch (Exception ex) {
            Log.d(TAG, "resetClient exception " + ex);
        }
        return false;
    }

    public static int getFileDescriptor(byte[] payload) {
        int fd = -1;
        if (!ensureConnection()) {
            return -1;
        }
        try {
            if (ipcServiceClient == null) {
                return -1;
            }
            ParcelFileDescriptor pfd = ipcServiceClient.getFileDescriptor(payload);
            if (pfd != null) {
                fd = pfd.detachFd();
            }
            return fd;
        } catch (Exception ex) {
            Log.d(TAG, "getFileDescriptor exception " + ex);
            return -1;
        }
    }

    public static int setFileDescriptor(byte[] payload, int fd) {
        if (!ensureConnection()) {
            return 0;
        }
        try {
            if (ipcServiceClient != null) {
                ParcelFileDescriptor pfd = fd != -1 ? ParcelFileDescriptor.fromFd(fd) : null;
                int result = ipcServiceClient.setFileDescriptor(payload, pfd);
                if (pfd != null) {
                    pfd.close();
                }
                return result;
            }
        } catch (Exception ex) {
            Log.d(TAG, "setFileDescriptor exception " + ex);
        }
        return 0;
    }

    public static int setSurface(byte[] payload, Surface surf) {
        if (!ensureConnection()) {
            return 0;
        }
        try {
            if (ipcServiceClient != null) {
                return ipcServiceClient.setSurface(payload, surf);
            }
        } catch (Exception ex) {
            Log.d(TAG, "setSurface exception " + ex);
        }
        return 0;
    }

    public static Surface getSurface(byte[] payload) {
        if (!ensureConnection()) {
            return null;
        }
        try {
            if (ipcServiceClient != null) {
                return ipcServiceClient.getSurface(payload);
            }
        } catch (Exception ex) {
            Log.d(TAG, "getSurface exception " + ex);
        }
        return null;
    }

    private static Intent CreateStartServiceIntent(String packageName, String serviceComponentName) {
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(packageName, serviceComponentName));
        return serviceIntent;
    }

    public static boolean ensureServiceStarted(String packageName, String serviceComponentName) {
        Log.d(TAG, "ensureServiceStarted: " + packageName + ", " + serviceComponentName);
        try {
            Context context = getContext();
            if (context == null) {
                return false;
            }
            context.startService(CreateStartServiceIntent(packageName, serviceComponentName));
            Log.d(TAG, "ensureServiceStarted SUCCESS: " + packageName + ", " + serviceComponentName);
            return true;
        } catch (Exception ex) {
            Log.e(TAG, "ensureServiceStarted exception: " + ex);
            return false;
        }
    }

    static boolean compareContext(Context context) {
        if (context == null) {
            Log.w(TAG, "compareContext null context");
            return false;
        }
        Context thisContext = getContext();
        if (thisContext == null) {
            Log.w(TAG, "compareContext null thisContext");
            return false;
        } else if (thisContext.getClass().getName().equals(context.getClass().getName())) {
            return true;
        } else {
            Log.v(TAG, "compareContext FAILED: " + thisContext.getClass().getName() + ", " + context.getClass().getName());
            return false;
        }
    }

    public static boolean checkPermission(int pid, int uid, String permission) {
        try {
            Context context = getContext();
            if (context != null && context.checkPermission(permission, pid, uid) == 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            Log.e(TAG, "checkPermission exception: " + ex);
            return false;
        }
    }
}
