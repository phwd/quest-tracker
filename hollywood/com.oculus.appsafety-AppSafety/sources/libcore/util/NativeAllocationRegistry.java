package libcore.util;

import dalvik.system.VMRuntime;
import java.lang.ref.Reference;
import sun.misc.Cleaner;

public class NativeAllocationRegistry {
    private static final long IS_MALLOCED = 1;
    private final ClassLoader classLoader;
    private final long freeFunction;
    private final long size;

    public static native void applyFreeFunction(long j, long j2);

    public static NativeAllocationRegistry createNonmalloced(ClassLoader classLoader2, long freeFunction2, long size2) {
        return new NativeAllocationRegistry(classLoader2, freeFunction2, size2, false);
    }

    public static NativeAllocationRegistry createMalloced(ClassLoader classLoader2, long freeFunction2, long size2) {
        return new NativeAllocationRegistry(classLoader2, freeFunction2, size2, true);
    }

    public static NativeAllocationRegistry createMalloced(ClassLoader classLoader2, long freeFunction2) {
        return new NativeAllocationRegistry(classLoader2, freeFunction2, 0, true);
    }

    private NativeAllocationRegistry(ClassLoader classLoader2, long freeFunction2, long size2, boolean mallocAllocation) {
        if (size2 >= 0) {
            this.classLoader = classLoader2;
            this.freeFunction = freeFunction2;
            this.size = mallocAllocation ? IS_MALLOCED | size2 : -2 & size2;
            return;
        }
        throw new IllegalArgumentException("Invalid native allocation size: " + size2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NativeAllocationRegistry(ClassLoader classLoader2, long freeFunction2, long size2) {
        this(classLoader2, freeFunction2, size2, size2 == 0);
    }

    public Runnable registerNativeAllocation(Object referent, long nativePtr) {
        if (referent == null) {
            throw new IllegalArgumentException("referent is null");
        } else if (nativePtr != 0) {
            try {
                CleanerThunk thunk = new CleanerThunk();
                CleanerRunner result = new CleanerRunner(Cleaner.create(referent, thunk));
                registerNativeAllocation(this.size);
                thunk.setNativePtr(nativePtr);
                Reference.reachabilityFence(referent);
                return result;
            } catch (VirtualMachineError vme) {
                applyFreeFunction(this.freeFunction, nativePtr);
                throw vme;
            }
        } else {
            throw new IllegalArgumentException("nativePtr is null");
        }
    }

    /* access modifiers changed from: private */
    public class CleanerThunk implements Runnable {
        private long nativePtr = 0;

        public CleanerThunk() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.nativePtr != 0) {
                NativeAllocationRegistry.applyFreeFunction(NativeAllocationRegistry.this.freeFunction, this.nativePtr);
                NativeAllocationRegistry.registerNativeFree(NativeAllocationRegistry.this.size);
            }
        }

        public void setNativePtr(long nativePtr2) {
            this.nativePtr = nativePtr2;
        }
    }

    /* access modifiers changed from: private */
    public static class CleanerRunner implements Runnable {
        private final Cleaner cleaner;

        public CleanerRunner(Cleaner cleaner2) {
            this.cleaner = cleaner2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.cleaner.clean();
        }
    }

    private static void registerNativeAllocation(long size2) {
        VMRuntime runtime = VMRuntime.getRuntime();
        if ((IS_MALLOCED & size2) == 0) {
            runtime.registerNativeAllocation(size2);
        } else if (size2 >= 300000) {
            runtime.notifyNativeAllocationsInternal();
        } else {
            runtime.notifyNativeAllocation();
        }
    }

    /* access modifiers changed from: private */
    public static void registerNativeFree(long size2) {
        if ((IS_MALLOCED & size2) == 0) {
            VMRuntime.getRuntime().registerNativeFree(size2);
        }
    }
}
