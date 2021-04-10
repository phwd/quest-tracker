package com.facebook.jni;

import X.KV;

public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        KV.A01("fbjni");
    }

    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
