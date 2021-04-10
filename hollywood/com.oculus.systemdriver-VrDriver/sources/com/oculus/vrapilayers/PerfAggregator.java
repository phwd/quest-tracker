package com.oculus.vrapilayers;

import android.content.Context;
import android.util.Log;

public class PerfAggregator {
    private static final String TAG = "PerfAggregator";

    private static native void nativeStartLayer(long j, long j2, ClassLoader classLoader);

    public static void StartLayer(long vrapi_fnptr, long vrapi_priv_fnptr, Context appContext) {
        Log.d(TAG, "StartLayer: Loading perfaggregator.so");
        System.loadLibrary("perfaggregator");
        nativeStartLayer(vrapi_fnptr, vrapi_priv_fnptr, PerfAggregator.class.getClassLoader());
    }
}
