package com.facebook.jni;

import X.AnonymousClass0lD;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        AnonymousClass0lD.A01("fbjni");
    }

    @DoNotStrip
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
