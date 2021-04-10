package com.facebook.jni;

import X.C03250cX;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        C03250cX.A01("fbjni");
    }

    @DoNotStrip
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
