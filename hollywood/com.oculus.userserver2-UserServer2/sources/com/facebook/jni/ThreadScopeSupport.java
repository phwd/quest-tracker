package com.facebook.jni;

import X.g6;
import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class ThreadScopeSupport {
    public static native void runStdFunctionImpl(long j);

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @DoNotStrip
    public static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }
}
