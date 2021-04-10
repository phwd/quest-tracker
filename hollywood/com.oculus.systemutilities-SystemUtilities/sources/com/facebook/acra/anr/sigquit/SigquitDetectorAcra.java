package com.facebook.acra.anr.sigquit;

import android.annotation.SuppressLint;

@SuppressLint({"MissingNativeLoadLibrary"})
public class SigquitDetectorAcra implements SigquitDetector {
    private static final String TAG = SigquitDetectorAcra.class.getSimpleName();
    private static SigquitDetector sInstance;
    private static boolean sIsArt;
    private SigquitDetectorListener mListener;

    private static native void nativeStopDetector();

    static {
        String vmVersion = System.getProperty("java.vm.version");
        sIsArt = vmVersion != null && !vmVersion.startsWith("1.") && !vmVersion.startsWith("0.");
    }

    public static SigquitDetector getInstance(SigquitDetectorListener listener) {
        if (sInstance == null) {
            sInstance = new SigquitDetectorAcra(listener);
        }
        return sInstance;
    }

    private SigquitDetectorAcra(SigquitDetectorListener listener) {
        this.mListener = listener;
    }

    @Override // com.facebook.acra.anr.sigquit.SigquitDetector
    public void stopDetector() {
        nativeStopDetector();
    }
}
