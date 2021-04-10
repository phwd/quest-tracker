package com.oculus.vrapilayers;

import android.content.Context;
import android.util.Log;

public class ApiLog {
    private static final String TAG = "ApiLog";

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        Log.d(TAG, "ApiLog StartLayer: Loading libapilog.so");
        System.loadLibrary("apilog");
        nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, ApiLog.class.getClassLoader());
    }
}
