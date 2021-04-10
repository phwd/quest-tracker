package com.facebook.acra.util;

import android.annotation.SuppressLint;
import com.facebook.acra.util.ProcFileReader;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"MissingNativeLoadLibrary"})
public class NativeProcFileReader extends ProcFileReader {
    private static final String TAG = NativeProcFileReader.class.getSimpleName();
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
        if (!isReady()) {
            throw new IllegalStateException("Class is not ready");
        }
    }

    public static boolean isReady() {
        return sReadyToUse.get();
    }

    @Override // com.facebook.acra.util.ProcFileReader
    public ProcFileReader.OpenFDLimits getOpenFDLimits() {
        int[] limits = getOpenFDLimitsNative();
        return new ProcFileReader.OpenFDLimits(limits[0], limits[1]);
    }
}
