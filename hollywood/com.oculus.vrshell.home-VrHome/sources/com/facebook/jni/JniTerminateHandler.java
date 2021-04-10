package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.Thread;

@DoNotStrip
public class JniTerminateHandler {
    @DoNotStrip
    public static void handleTerminate(Throwable t) throws Throwable {
        Thread.UncaughtExceptionHandler h = Thread.getDefaultUncaughtExceptionHandler();
        if (h != null) {
            h.uncaughtException(Thread.currentThread(), t);
        }
    }
}
