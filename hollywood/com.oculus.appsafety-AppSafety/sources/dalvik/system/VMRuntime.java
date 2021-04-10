package dalvik.system;

import java.lang.ref.FinalizerReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class VMRuntime {
    private static final Map<String, String> ABI_TO_INSTRUCTION_SET_MAP = new HashMap(16);
    public static final int SDK_VERSION_CUR_DEVELOPMENT = 10000;
    private static final VMRuntime THE_ONE = new VMRuntime();
    static HiddenApiUsageLogger hiddenApiUsageLogger;
    private static Consumer<String> nonSdkApiUsageConsumer = null;
    private final AtomicInteger allocationCount = new AtomicInteger(0);
    private int notifyNativeInterval;
    private int targetSdkVersion = SDK_VERSION_CUR_DEVELOPMENT;

    public interface HiddenApiUsageLogger {
        public static final int ACCESS_METHOD_JNI = 2;
        public static final int ACCESS_METHOD_LINKING = 3;
        public static final int ACCESS_METHOD_NONE = 0;
        public static final int ACCESS_METHOD_REFLECTION = 1;

        void hiddenApiUsed(int i, String str, String str2, int i2, boolean z);
    }

    public static native boolean didPruneDalvikCache();

    public static native String getCurrentInstructionSet();

    private static native int getNotifyNativeInterval();

    public static native boolean hasBootImageSpaces();

    public static native boolean isBootClassPathOnDisk(String str);

    private native void nativeSetTargetHeapUtilization(float f);

    public static native void registerAppInfo(String str, String[] strArr);

    public static native void registerSensitiveThread();

    public static native void setDedupeHiddenApiWarnings(boolean z);

    public static native void setProcessDataDirectory(String str);

    public static native void setProcessPackageName(String str);

    public static native void setSystemDaemonThreadPriority();

    private native void setTargetSdkVersionNative(int i);

    public native long addressOf(Object obj);

    public native String bootClassPath();

    public native void clampGrowthLimit();

    public native void clampGrowthLimit(long j);

    public native String classPath();

    public native void clearGrowthLimit();

    public native void concurrentGC();

    public native void disableJitCompilation();

    public native long getFinalizerTimeoutMs();

    public native float getTargetHeapUtilization();

    public native boolean is64Bit();

    public native boolean isCheckJniEnabled();

    public native boolean isDebuggerActive();

    public native boolean isJavaDebuggable();

    public native boolean isNativeDebuggable();

    public native Object newNonMovableArray(Class<?> cls, int i);

    public native Object newUnpaddedArray(Class<?> cls, int i);

    public native void notifyNativeAllocationsInternal();

    public native void notifyStartupCompleted();

    public native void preloadDexCaches();

    public native String[] properties();

    public native void registerNativeAllocation(long j);

    public native void registerNativeFree(long j);

    public native void requestConcurrentGC();

    public native void requestHeapTrim();

    public native void runHeapTasks();

    public native void setHiddenApiAccessLogSamplingRate(int i);

    public native void setHiddenApiExemptions(String[] strArr);

    public native void startHeapTaskProcessor();

    public native void startJitCompilation();

    public native void stopHeapTaskProcessor();

    public native void trimHeap();

    public native void updateProcessState(int i);

    public native String vmInstructionSet();

    public native String vmLibrary();

    public native String vmVersion();

    static {
        ABI_TO_INSTRUCTION_SET_MAP.put("armeabi", "arm");
        ABI_TO_INSTRUCTION_SET_MAP.put("armeabi-v7a", "arm");
        ABI_TO_INSTRUCTION_SET_MAP.put("mips", "mips");
        ABI_TO_INSTRUCTION_SET_MAP.put("mips64", "mips64");
        ABI_TO_INSTRUCTION_SET_MAP.put("x86", "x86");
        ABI_TO_INSTRUCTION_SET_MAP.put("x86_64", "x86_64");
        ABI_TO_INSTRUCTION_SET_MAP.put("arm64-v8a", "arm64");
    }

    public static void setHiddenApiUsageLogger(HiddenApiUsageLogger hiddenApiUsageLogger2) {
        hiddenApiUsageLogger = hiddenApiUsageLogger2;
    }

    private static void hiddenApiUsed(int sampledValue, String appPackageName, String signature, int accessType, boolean accessDenied) {
        HiddenApiUsageLogger hiddenApiUsageLogger2 = hiddenApiUsageLogger;
        if (hiddenApiUsageLogger2 != null) {
            hiddenApiUsageLogger2.hiddenApiUsed(sampledValue, appPackageName, signature, accessType, accessDenied);
        }
    }

    private VMRuntime() {
    }

    public static VMRuntime getRuntime() {
        return THE_ONE;
    }

    public float setTargetHeapUtilization(float newTarget) {
        float newTarget2;
        float oldTarget;
        if (newTarget <= 0.0f || newTarget >= 1.0f) {
            throw new IllegalArgumentException(newTarget + " out of range (0,1)");
        }
        if (newTarget < 0.1f) {
            newTarget2 = 0.1f;
        } else {
            newTarget2 = newTarget;
        }
        synchronized (this) {
            oldTarget = getTargetHeapUtilization();
            nativeSetTargetHeapUtilization(newTarget2);
        }
        return oldTarget;
    }

    public synchronized void setTargetSdkVersion(int targetSdkVersion2) {
        this.targetSdkVersion = targetSdkVersion2;
        setTargetSdkVersionNative(this.targetSdkVersion);
    }

    public synchronized int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    @Deprecated
    public long getMinimumHeapSize() {
        return 0;
    }

    @Deprecated
    public long setMinimumHeapSize(long size) {
        return 0;
    }

    @Deprecated
    public void gcSoftReferences() {
    }

    @Deprecated
    public void runFinalizationSync() {
        System.runFinalization();
    }

    @Deprecated
    public boolean trackExternalAllocation(long size) {
        return true;
    }

    @Deprecated
    public void trackExternalFree(long size) {
    }

    @Deprecated
    public long getExternalBytesAllocated() {
        return 0;
    }

    @Deprecated
    public void registerNativeAllocation(int bytes) {
        registerNativeAllocation((long) bytes);
    }

    @Deprecated
    public void registerNativeFree(int bytes) {
        registerNativeFree((long) bytes);
    }

    public void notifyNativeAllocation() {
        int myNotifyNativeInterval = this.notifyNativeInterval;
        if (myNotifyNativeInterval == 0) {
            int notifyNativeInterval2 = getNotifyNativeInterval();
            this.notifyNativeInterval = notifyNativeInterval2;
            myNotifyNativeInterval = notifyNativeInterval2;
        }
        if (this.allocationCount.addAndGet(1) % myNotifyNativeInterval == 0) {
            notifyNativeAllocationsInternal();
        }
    }

    public static void runFinalization(long timeout) {
        try {
            FinalizerReference.finalizeAllEnqueued(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static String getInstructionSet(String abi) {
        String instructionSet = ABI_TO_INSTRUCTION_SET_MAP.get(abi);
        if (instructionSet != null) {
            return instructionSet;
        }
        throw new IllegalArgumentException("Unsupported ABI: " + abi);
    }

    public static boolean is64BitInstructionSet(String instructionSet) {
        return "arm64".equals(instructionSet) || "x86_64".equals(instructionSet) || "mips64".equals(instructionSet);
    }

    public static boolean is64BitAbi(String abi) {
        return is64BitInstructionSet(getInstructionSet(abi));
    }

    public static void setNonSdkApiUsageConsumer(Consumer<String> consumer) {
        nonSdkApiUsageConsumer = consumer;
    }
}
