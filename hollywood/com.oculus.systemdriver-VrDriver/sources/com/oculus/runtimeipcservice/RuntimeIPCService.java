package com.oculus.runtimeipcservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.oculus.runtimeipcservice.IRuntimeIPCService;
import com.oculus.runtimeipcservice.IRuntimeIPCServiceClient;

public class RuntimeIPCService extends Service {
    private static final String TAG = "RuntimeIPCService";
    private final IRuntimeIPCService.Stub binder = new IRuntimeIPCService.Stub() {
        /* class com.oculus.runtimeipcservice.RuntimeIPCService.AnonymousClass1 */

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCService
        public IRuntimeIPCServiceClient createClient(IBinder token, String processName) {
            synchronized (RuntimeIPCService.class) {
                try {
                    int clientPid = Binder.getCallingPid();
                    int clientUid = Binder.getCallingUid();
                    String appPackageName = RuntimeIPCService.this.getBaseContext().getPackageManager().getNameForUid(clientUid);
                    if (processName != null) {
                        Log.d(RuntimeIPCService.TAG, "createClient: ProcessName: " + processName);
                    }
                    if (token == null) {
                        Log.d(RuntimeIPCService.TAG, "createClient: token == null. PackageName: " + appPackageName);
                        return null;
                    }
                    int clientId = RuntimeIPCService.nativeCreateClient(appPackageName, processName, clientPid, clientUid);
                    Log.d(RuntimeIPCService.TAG, "createClient: " + clientId + ", " + token + ", " + appPackageName);
                    RuntimeIPCServiceClient client = new RuntimeIPCServiceClient(appPackageName, clientId, token);
                    token.linkToDeath(client, 0);
                    return client;
                } catch (Exception ex) {
                    Log.d(RuntimeIPCService.TAG, "createClient exception: " + ex);
                    return null;
                }
            }
        }
    };

    /* access modifiers changed from: private */
    public static native int nativeCreateClient(String str, String str2, int i, int i2);

    /* access modifiers changed from: private */
    public static native void nativeDestroyClient(int i);

    /* access modifiers changed from: private */
    public static native int nativeGetFileDescriptor(int i, byte[] bArr);

    /* access modifiers changed from: private */
    public static native Surface nativeGetSurface(int i, byte[] bArr);

    private static native void nativeInitIPCService(Context context, RuntimeIPCService runtimeIPCService);

    /* access modifiers changed from: private */
    public static native boolean nativeResetClient(int i);

    /* access modifiers changed from: private */
    public static native int nativeSetFileDescriptor(int i, byte[] bArr, int i2);

    /* access modifiers changed from: private */
    public static native int nativeSetSurface(int i, byte[] bArr, Surface surface);

    private static native void nativeShutdownIPCService();

    static {
        System.loadLibrary("runtimeipcservice");
    }

    public void onCreate() {
        Log.d(TAG, "onCreate");
        nativeInitIPCService(getBaseContext(), this);
        super.onCreate();
        Log.d(TAG, "onCreate - finished");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        nativeShutdownIPCService();
        super.onDestroy();
        Log.d(TAG, "onDestroy - finished");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return this.binder;
    }

    public boolean checkPermission(int pid, int uid, String permission) {
        try {
            Context context = getBaseContext();
            if (context != null && context.checkPermission(permission, pid, uid) == 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            Log.e(TAG, "checkPermission exception: " + ex);
            return false;
        }
    }

    private class RuntimeIPCServiceClient extends IRuntimeIPCServiceClient.Stub implements IBinder.DeathRecipient {
        private IBinder clientBinder;
        private int clientId;
        private String packageName;

        public RuntimeIPCServiceClient(String packageName2, int clientId2, IBinder clientBinder2) {
            this.packageName = packageName2;
            this.clientBinder = clientBinder2;
            this.clientId = clientId2;
        }

        public void binderDied() {
            synchronized (RuntimeIPCService.class) {
                Log.d(RuntimeIPCService.TAG, "binderDied: " + this.packageName);
                RuntimeIPCService.nativeDestroyClient(this.clientId);
            }
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public void destroy() {
            synchronized (RuntimeIPCService.class) {
                RuntimeIPCService.nativeDestroyClient(this.clientId);
                this.clientBinder.unlinkToDeath(this, 0);
            }
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public ParcelFileDescriptor getFileDescriptor(byte[] payload) {
            int fd = RuntimeIPCService.nativeGetFileDescriptor(this.clientId, payload);
            if (fd == -1) {
                return null;
            }
            try {
                return ParcelFileDescriptor.fromFd(fd);
            } catch (Exception ex) {
                Log.e(RuntimeIPCService.TAG, "getRPCMemoryFileDescriptor " + ex);
                return null;
            }
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public int setFileDescriptor(byte[] payload, ParcelFileDescriptor pfd) {
            int fd;
            if (pfd != null) {
                try {
                    fd = pfd.detachFd();
                } catch (Exception ex) {
                    Log.e(RuntimeIPCService.TAG, "setFileDescriptor " + ex);
                    return 0;
                }
            } else {
                fd = -1;
            }
            return RuntimeIPCService.nativeSetFileDescriptor(this.clientId, payload, fd);
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public int setSurface(byte[] payload, Surface surf) {
            return RuntimeIPCService.nativeSetSurface(this.clientId, payload, surf);
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public Surface getSurface(byte[] payload) {
            return RuntimeIPCService.nativeGetSurface(this.clientId, payload);
        }

        @Override // com.oculus.runtimeipcservice.IRuntimeIPCServiceClient
        public boolean resetClient() {
            return RuntimeIPCService.nativeResetClient(this.clientId);
        }
    }
}
