package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;

@DoNotStrip
public class ThreadScopeSupport {
    private static native void runStdFunctionImpl(long j);

    static {
        NativeLoader.loadLibrary("fbjni");
    }

    @DoNotStrip
    private static void runStdFunction(long ptr) {
        runStdFunctionImpl(ptr);
    }
}
