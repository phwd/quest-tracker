package dalvik.system;

import java.lang.ref.FinalizerReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class VMRuntime {
    private static final Map ABI_TO_INSTRUCTION_SET_MAP = new HashMap(16);
    private static final VMRuntime THE_ONE = new VMRuntime();
    private static Consumer nonSdkApiUsageConsumer = null;
    private final AtomicInteger allocationCount = new AtomicInteger(0);
    private int notifyNativeInterval;
    private int targetSdkVersion = 10000;

    public interface HiddenApiUsageLogger {
    }

    private static native int getNotifyNativeInterval();

    public native long addressOf(Object obj);

    public native String bootClassPath();

    public native String classPath();

    public native Object newNonMovableArray(Class cls, int i);

    public native void notifyNativeAllocationsInternal();

    public native String[] properties();

    public native void registerNativeAllocation(long j);

    public native void registerNativeFree(long j);

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

    private VMRuntime() {
    }

    public static VMRuntime getRuntime() {
        return THE_ONE;
    }

    public synchronized int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    public void notifyNativeAllocation() {
        int i = this.notifyNativeInterval;
        if (i == 0) {
            i = getNotifyNativeInterval();
            this.notifyNativeInterval = i;
        }
        if (this.allocationCount.addAndGet(1) % i == 0) {
            notifyNativeAllocationsInternal();
        }
    }

    public static void runFinalization(long j) {
        try {
            FinalizerReference.finalizeAllEnqueued(j);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
