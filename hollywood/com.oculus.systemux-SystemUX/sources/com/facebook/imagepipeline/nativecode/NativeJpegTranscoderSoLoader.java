package com.facebook.imagepipeline.nativecode;

import android.os.Build;
import com.facebook.soloader.nativeloader.NativeLoader;

public class NativeJpegTranscoderSoLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        synchronized (NativeJpegTranscoderSoLoader.class) {
            if (!sInitialized) {
                if (Build.VERSION.SDK_INT <= 16) {
                    try {
                        NativeLoader.loadLibrary("fb_jpegturbo", 1);
                    } catch (UnsatisfiedLinkError unused) {
                    }
                }
                NativeLoader.loadLibrary("native-imagetranscoder");
                sInitialized = true;
            }
        }
    }
}
