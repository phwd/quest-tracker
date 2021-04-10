package com.facebook.jni;

import X.C0431hn;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        C0431hn.A00("fbjni");
    }

    @DoNotStrip
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
