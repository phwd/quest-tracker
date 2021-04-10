package com.facebook.errorreporting.nightwatch;

import android.os.Build;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Nightwatch {
    private static final String LOG_TAG = "Nightwatch";
    public static File currentLogFile = null;
    private static boolean isRunning = false;
    private static final Object isRunningLock = new Object();

    @DoNotStrip
    static class NightwatchNative {
        private static final boolean CAN_USE_CRITICAL_NATIVE_METHODS;
        private static boolean sHasLinkedFastMethods = false;
        private static final NightwatchNative sNightwatchNativeImpl = new NightwatchNative();
        private static boolean sTryLoadedLib = false;
        private static boolean sUsingFastJniNativeMethodsOverride = true;

        static {
            boolean z;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    Class.forName("dalvik.annotation.optimization.CriticalNative");
                    z = true;
                } catch (ClassNotFoundException e) {
                    BLog.d(Nightwatch.LOG_TAG, e, "Critical Native could not be found. Using normal fastjni!");
                }
                CAN_USE_CRITICAL_NATIVE_METHODS = z;
            }
            z = false;
            CAN_USE_CRITICAL_NATIVE_METHODS = z;
        }

        NightwatchNative() {
        }
    }
}
