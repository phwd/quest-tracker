package com.facebook.jni;

import com.facebook.common.build.config.BuildConfig;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@DoNotStrip
public class Countable {
    @DoNotStrip
    private long mInstance = 0;

    public native void dispose();

    static {
        NativeLoader.loadLibrary(BuildConfig.FB_URL_SCHEME);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
