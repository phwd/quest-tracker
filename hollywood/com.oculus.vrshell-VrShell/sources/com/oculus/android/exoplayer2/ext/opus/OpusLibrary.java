package com.oculus.android.exoplayer2.ext.opus;

import com.oculus.android.exoplayer2.ExoPlayerLibraryInfo;
import com.oculus.android.exoplayer2.util.LibraryLoader;

public final class OpusLibrary {
    private static final LibraryLoader LOADER = new LibraryLoader("opusLib", "opusJNI");

    public static native String opusGetVersion();

    public static native boolean opusIsSecureDecodeSupported();

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.opus");
    }

    private OpusLibrary() {
    }

    public static void setLibraries(String... strArr) {
        LOADER.setLibraries(strArr);
    }

    public static boolean isAvailable() {
        return LOADER.isAvailable();
    }

    public static String getVersion() {
        if (isAvailable()) {
            return opusGetVersion();
        }
        return null;
    }
}
