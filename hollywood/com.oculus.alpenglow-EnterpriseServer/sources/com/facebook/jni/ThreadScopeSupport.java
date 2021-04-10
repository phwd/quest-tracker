package com.facebook.jni;

import X.C05400jG;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        C05400jG.A00("fbjni");
    }

    @DoNotStrip
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
