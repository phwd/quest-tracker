package dalvik.system;

import android.icu.impl.CacheValue;
import android.icu.text.DecimalFormatSymbols;
import android.icu.util.ULocale;
import java.io.File;

public final class ZygoteHooks {
    private static long token;

    private static native void nativePostForkChild(long j, int i, boolean z, boolean z2, String str);

    private static native void nativePostForkSystemServer();

    private static native void nativePostZygoteFork();

    private static native long nativePreFork();

    public static native void startZygoteNoThreadCreation();

    public static native void stopZygoteNoThreadCreation();

    private ZygoteHooks() {
    }

    public static void onBeginPreload() {
        CacheValue.setStrength(CacheValue.Strength.STRONG);
        for (ULocale uLocale : new ULocale[]{ULocale.ROOT, ULocale.US, ULocale.getDefault()}) {
            new DecimalFormatSymbols(uLocale);
        }
    }

    public static void onEndPreload() {
        CacheValue.setStrength(CacheValue.Strength.SOFT);
    }

    public static void gcAndFinalize() {
        VMRuntime runtime = VMRuntime.getRuntime();
        System.gc();
        runtime.runFinalizationSync();
        System.gc();
    }

    public static void preFork() {
        Daemons.stop();
        token = nativePreFork();
        waitUntilAllThreadsStopped();
    }

    public static void postForkSystemServer() {
        nativePostForkSystemServer();
    }

    public static void postForkChild(int runtimeFlags, boolean isSystemServer, boolean isZygote, String instructionSet) {
        nativePostForkChild(token, runtimeFlags, isSystemServer, isZygote, instructionSet);
        Math.setRandomSeedInternal(System.currentTimeMillis());
    }

    public static void postForkCommon() {
        Daemons.startPostZygoteFork();
        nativePostZygoteFork();
    }

    private static void waitUntilAllThreadsStopped() {
        File tasks = new File("/proc/self/task");
        while (tasks.list().length > 1) {
            Thread.yield();
        }
    }
}
