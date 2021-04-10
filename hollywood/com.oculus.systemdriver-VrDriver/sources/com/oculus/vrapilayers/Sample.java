package com.oculus.vrapilayers;

import android.content.Context;
import android.util.Log;

public class Sample {
    private static final String TAG = "SampleJava";

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        Log.d(TAG, "Sample StartLayer: Loading vrapilayers.so");
        System.loadLibrary("vrapilayers");
        nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, Sample.class.getClassLoader());
    }
}
