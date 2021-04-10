package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.Thread;

@DoNotStrip
public class JniTerminateHandler {
    @DoNotStrip
    public static void handleTerminate(Throwable th) throws Throwable {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
    }
}
