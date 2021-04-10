package com.facebook.acra.util;

import android.annotation.SuppressLint;
import com.facebook.acra.util.ProcFileReader;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@SuppressLint({"MissingNativeLoadLibrary"})
public class NativeProcFileReader extends ProcFileReader {
    public static final String TAG = "NativeProcFileReader";
    public static NativeProcFileReader sInstance;
    @GuardedBy("NativeProcFileReader.class")
    @Nullable
    public static Thread sLoadSoThread;
    public static final AtomicBoolean sReadyToUse = new AtomicBoolean(false);

    private native int[] getOpenFDLimitsNative();

    @Override // com.facebook.acra.util.ProcFileReader
    public native int getOpenFDCount();

    @Override // com.facebook.acra.util.ProcFileReader
    public native String getOpenFileDescriptors();

    public static synchronized NativeProcFileReader getInstance() {
        NativeProcFileReader nativeProcFileReader;
        synchronized (NativeProcFileReader.class) {
            nativeProcFileReader = sInstance;
            if (nativeProcFileReader == null) {
                nativeProcFileReader = new NativeProcFileReader();
                sInstance = nativeProcFileReader;
            }
        }
        return nativeProcFileReader;
    }

    public static boolean isReady() {
        return sReadyToUse.get();
    }

    public static void nativeLibraryLoaded() {
        synchronized (NativeProcFileReader.class) {
            sReadyToUse.set(true);
            NativeProcFileReader.class.notifyAll();
        }
    }

    public NativeProcFileReader() {
        if (!sReadyToUse.get()) {
            throw new IllegalStateException("Class is not ready");
        }
    }

    @Override // com.facebook.acra.util.ProcFileReader
    @Nullable
    public ProcFileReader.OpenFDLimits getOpenFDLimits() {
        int[] openFDLimitsNative = getOpenFDLimitsNative();
        return new ProcFileReader.OpenFDLimits(openFDLimitsNative[0], openFDLimitsNative[1]);
    }
}
