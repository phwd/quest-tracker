package com.oculus.runtimeipcapi;

import android.content.Context;
import android.util.Log;

public class RuntimeIPCApi {
    private static final String TAG = "RuntimeIPCApiJava";

    private static native byte[] nativeCallServerRPC(int i, byte[] bArr);

    private static native int nativeConnectToServer(String str, String str2, String str3);

    private static native int nativeEnsureServiceStarted(String str, String str2);

    private static native boolean nativeInitialize(Context context);

    static {
        System.loadLibrary("runtimeipcapi");
    }

    public static boolean ipc_Initialize(Context inContext) {
        Log.d(TAG, "ipc_Initialize");
        return nativeInitialize(inContext);
    }

    public static int ipc_EnsureServiceStarted(String packageName, String serviceCompName) {
        Log.d(TAG, "ipc_EnsureServiceStarted: " + packageName + ", " + serviceCompName);
        return nativeEnsureServiceStarted(packageName, serviceCompName);
    }

    public static int ipc_ConnectToServer(String packageName, String processName, String serverName) {
        Log.d(TAG, "ipc_ConnectToServer");
        return nativeConnectToServer(packageName, processName, serverName);
    }

    public static byte[] ipc_CallServerRPC(int clientId, byte[] request) {
        return nativeCallServerRPC(clientId, request);
    }
}
