package java.util.concurrent.locks;

import sun.misc.Unsafe;

public class LockSupport {
    private static final long PARKBLOCKER;
    private static final long SECONDARY;
    private static final Unsafe U = Unsafe.getUnsafe();

    private static void setBlocker(Thread thread, Object obj) {
        U.putObject(thread, PARKBLOCKER, obj);
    }

    public static void unpark(Thread thread) {
        if (thread != null) {
            U.unpark(thread);
        }
    }

    public static void park(Object obj) {
        Thread currentThread = Thread.currentThread();
        setBlocker(currentThread, obj);
        U.park(false, 0);
        setBlocker(currentThread, null);
    }

    public static void parkNanos(Object obj, long j) {
        if (j > 0) {
            Thread currentThread = Thread.currentThread();
            setBlocker(currentThread, obj);
            U.park(false, j);
            setBlocker(currentThread, null);
        }
    }

    public static void parkUntil(Object obj, long j) {
        Thread currentThread = Thread.currentThread();
        setBlocker(currentThread, obj);
        U.park(true, j);
        setBlocker(currentThread, null);
    }

    static {
        try {
            PARKBLOCKER = U.objectFieldOffset(Thread.class.getDeclaredField("parkBlocker"));
            SECONDARY = U.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
