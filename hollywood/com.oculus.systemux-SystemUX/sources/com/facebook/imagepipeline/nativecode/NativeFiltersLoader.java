package com.facebook.imagepipeline.nativecode;

import com.facebook.soloader.nativeloader.NativeLoader;

public class NativeFiltersLoader {
    public static void load() {
        NativeLoader.loadLibrary("native-filters");
    }
}
