package com.facebook.acra.anr.sigquit;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SigquitDetectorAcra implements SigquitDetector {
    private static final String TAG = "SigquitDetectorAcra";
    private static SigquitDetector sInstance;
    private static boolean sIsArt;
    private SigquitDetectorListener mListener;

    static {
        String property = System.getProperty("java.vm.version");
        sIsArt = property != null && !property.startsWith("1.") && !property.startsWith("0.");
    }

    public static SigquitDetector getInstance(SigquitDetectorListener sigquitDetectorListener) {
        if (sInstance == null) {
            sInstance = new SigquitDetectorAcra(sigquitDetectorListener);
        }
        return sInstance;
    }

    private SigquitDetectorAcra(SigquitDetectorListener sigquitDetectorListener) {
        this.mListener = sigquitDetectorListener;
    }

    @DoNotStrip
    private void sigquitDetected(String str, String str2, boolean z, boolean z2) {
        BLog.w(TAG, "sigquitDetected inFgV1: %b inFgV2: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
        this.mListener.sigquitDetected(str, str2, z, z2);
    }
}
