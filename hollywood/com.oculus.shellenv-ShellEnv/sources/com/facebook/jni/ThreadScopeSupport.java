package com.facebook.jni;

import com.facebook.soloader.a.a;

public class ThreadScopeSupport {
    static {
        a.a("fbjni");
    }

    private static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }

    private static native void runStdFunctionImpl(long j);
}
