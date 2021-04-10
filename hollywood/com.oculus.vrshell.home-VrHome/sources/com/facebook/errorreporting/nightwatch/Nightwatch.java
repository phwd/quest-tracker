package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import android.os.Build;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.File;
import javax.annotation.Nullable;

public class Nightwatch {
    private static final String LOG_TAG = Nightwatch.class.getSimpleName();
    @Nullable
    public static File currentLogFile = null;
    private static boolean isRunning = false;
    private static final Object isRunningLock = new Object();
    private static final ThreadLocal<Boolean> okToLoadNightWatch = new ThreadLocal<>();

    @DoNotStrip
    @SuppressLint({"MissingNativeLoadLibrary"})
    private static class NightwatchNative {
        private static final boolean CAN_USE_CRITICAL_NATIVE_METHODS;
        private static boolean sHasLinkedFastMethods = false;

        private NightwatchNative() {
        }

        static {
            boolean canUseCriticalNativeMethod = false;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    Class.forName("dalvik.annotation.optimization.CriticalNative");
                    canUseCriticalNativeMethod = true;
                } catch (ClassNotFoundException e) {
                    BLog.d(Nightwatch.LOG_TAG, e, "Critical Native could not be found. Using normal fastjni!");
                    canUseCriticalNativeMethod = false;
                }
            }
            CAN_USE_CRITICAL_NATIVE_METHODS = canUseCriticalNativeMethod;
            if (!((Boolean) Assertions.assumeNotNull(Boolean.TRUE)).equals(Nightwatch.okToLoadNightWatch.get())) {
                throw new RuntimeException("trying to load nightwatch before nightwatch starts!");
            }
            NativeLoader.loadLibrary("fbnightwatch");
        }
    }
}
