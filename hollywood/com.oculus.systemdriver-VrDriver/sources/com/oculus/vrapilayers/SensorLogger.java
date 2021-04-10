package com.oculus.vrapilayers;

import android.content.Context;
import android.util.Log;

public class SensorLogger {
    private static final String TAG = "SensorLogger,";

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        Log.d(TAG, "SensorLogger StartLayer: Loading libsensorlogger.so");
        System.loadLibrary("sensorlogger");
        nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, SensorLogger.class.getClassLoader());
    }
}
