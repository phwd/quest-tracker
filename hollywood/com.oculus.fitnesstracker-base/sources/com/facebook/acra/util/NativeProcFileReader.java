package com.facebook.acra.util;

import com.facebook.acra.util.ProcFileReader;
import java.util.concurrent.atomic.AtomicBoolean;

public class NativeProcFileReader extends ProcFileReader {
    private static final String TAG = "NativeProcFileReader";
    private static NativeProcFileReader sInstance = null;
    private static Thread sLoadSoThread = null;
    private static final AtomicBoolean sReadyToUse = new AtomicBoolean(false);

    private native int[] getOpenFDLimitsNative();

    @Override // com.facebook.acra.util.ProcFileReader
    public native int getOpenFDCount();

    @Override // com.facebook.acra.util.ProcFileReader
    public native String getOpenFileDescriptors();

    public static synchronized NativeProcFileReader getInstance() {
        NativeProcFileReader nativeProcFileReader;
        synchronized (NativeProcFileReader.class) {
            if (sInstance == null) {
                sInstance = new NativeProcFileReader();
            }
            nativeProcFileReader = sInstance;
        }
        return nativeProcFileReader;
    }

    private NativeProcFileReader() {
        if (!sReadyToUse.get()) {
            throw new IllegalStateException("Class is not ready");
        }
    }

    public static boolean isReady() {
        return sReadyToUse.get();
    }

    @Override // com.facebook.acra.util.ProcFileReader
    public final ProcFileReader.OpenFDLimits getOpenFDLimits() {
        int[] openFDLimitsNative = getOpenFDLimitsNative();
        return new ProcFileReader.OpenFDLimits(openFDLimitsNative[0], openFDLimitsNative[1]);
    }
}
