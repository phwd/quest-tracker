package java.lang;

import java.lang.ThreadLocal;
import java.security.AccessControlContext;
import java.security.AccessController;
import sun.nio.ch.Interruptible;

public class Thread implements Runnable {
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    private static final RuntimePermission SUBCLASS_IMPLEMENTATION_PERMISSION = new RuntimePermission("enableContextClassLoaderOverride");
    private static int threadInitNumber;
    private static long threadSeqNumber;
    private volatile Interruptible blocker;
    private final Object blockerLock = new Object();
    private ClassLoader contextClassLoader;
    private boolean daemon = false;
    private ThreadGroup group;
    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
    private AccessControlContext inheritedAccessControlContext;
    private final Object lock = new Object();
    private volatile String name;
    private volatile long nativePeer;
    volatile Object parkBlocker;
    private int priority;
    private long stackSize;
    boolean started = false;
    private boolean stillborn = false;
    private boolean systemDaemon = false;
    private Runnable target;
    int threadLocalRandomProbe;
    int threadLocalRandomSecondarySeed;
    long threadLocalRandomSeed;
    ThreadLocal.ThreadLocalMap threadLocals = null;
    private long tid;
    private volatile UncaughtExceptionHandler uncaughtExceptionHandler;

    public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED
    }

    public interface UncaughtExceptionHandler {
    }

    public static native Thread currentThread();

    private native void interrupt0();

    public static native boolean interrupted();

    private static native void nativeCreate(Thread thread, long j, boolean z);

    private native int nativeGetStatus(boolean z);

    private native void setNativeName(String str);

    private static native void sleep(Object obj, long j, int i);

    public static native void yield();

    public final void checkAccess() {
    }

    public native boolean isInterrupted();

    private static synchronized int nextThreadNum() {
        int i;
        synchronized (Thread.class) {
            i = threadInitNumber;
            threadInitNumber = i + 1;
        }
        return i;
    }

    private static synchronized long nextThreadID() {
        long j;
        synchronized (Thread.class) {
            j = threadSeqNumber + 1;
            threadSeqNumber = j;
        }
        return j;
    }

    public void blockedOn(Interruptible interruptible) {
        synchronized (this.blockerLock) {
            this.blocker = interruptible;
        }
    }

    public static void sleep(long j) {
        sleep(j, 0);
    }

    public static void sleep(long j, int i) {
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i2 < 0) {
            throw new IllegalArgumentException("millis < 0: " + j);
        } else if (i < 0) {
            throw new IllegalArgumentException("nanos < 0: " + i);
        } else if (i > 999999) {
            throw new IllegalArgumentException("nanos > 999999: " + i);
        } else if (i2 != 0 || i != 0) {
            long nanoTime = System.nanoTime();
            long j2 = (j * 1000000) + ((long) i);
            Object obj = currentThread().lock;
            synchronized (obj) {
                while (true) {
                    sleep(obj, j, i);
                    long nanoTime2 = System.nanoTime();
                    long j3 = nanoTime2 - nanoTime;
                    if (j3 < j2) {
                        j2 -= j3;
                        i = (int) (j2 % 1000000);
                        j = j2 / 1000000;
                        nanoTime = nanoTime2;
                    }
                }
            }
        } else if (interrupted()) {
            throw new InterruptedException();
        }
    }

    private void init(ThreadGroup threadGroup, Runnable runnable, String str, long j) {
        init(threadGroup, runnable, str, j, null);
    }

    private void init(ThreadGroup threadGroup, Runnable runnable, String str, long j, AccessControlContext accessControlContext) {
        if (str != null) {
            this.name = str;
            Thread currentThread = currentThread();
            if (threadGroup == null) {
                threadGroup = currentThread.getThreadGroup();
            }
            threadGroup.addUnstarted();
            this.group = threadGroup;
            this.daemon = currentThread.isDaemon();
            this.priority = currentThread.getPriority();
            this.target = runnable;
            init2(currentThread);
            this.stackSize = j;
            this.tid = nextThreadID();
            return;
        }
        throw new NullPointerException("name cannot be null");
    }

    /* access modifiers changed from: protected */
    public Object clone() {
        throw new CloneNotSupportedException();
    }

    public Thread() {
        init(null, null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(Runnable runnable) {
        init(null, runnable, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(String str) {
        init(null, null, str, 0);
    }

    private void init2(Thread thread) {
        this.contextClassLoader = thread.getContextClassLoader();
        this.inheritedAccessControlContext = AccessController.getContext();
        ThreadLocal.ThreadLocalMap threadLocalMap = thread.inheritableThreadLocals;
        if (threadLocalMap != null) {
            this.inheritableThreadLocals = ThreadLocal.createInheritedMap(threadLocalMap);
        }
    }

    public Thread(Runnable runnable, String str) {
        init(null, runnable, str, 0);
    }

    public Thread(ThreadGroup threadGroup, Runnable runnable, String str) {
        init(threadGroup, runnable, str, 0);
    }

    public synchronized void start() {
        if (!this.started) {
            this.group.add(this);
            this.started = false;
            try {
                nativeCreate(this, this.stackSize, this.daemon);
                this.started = true;
                try {
                    if (!this.started) {
                        this.group.threadStartFailed(this);
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
        } else {
            throw new IllegalThreadStateException();
        }
        return;
        throw th;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.target;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void interrupt() {
        if (this != currentThread()) {
            checkAccess();
        }
        synchronized (this.blockerLock) {
            Interruptible interruptible = this.blocker;
            if (interruptible != null) {
                interrupt0();
                interruptible.interrupt(this);
                return;
            }
            interrupt0();
        }
    }

    public final boolean isAlive() {
        return this.nativePeer != 0;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final synchronized void setName(String str) {
        checkAccess();
        if (str != null) {
            this.name = str;
            if (isAlive()) {
                setNativeName(str);
            }
        } else {
            throw new NullPointerException("name cannot be null");
        }
    }

    public final String getName() {
        return this.name;
    }

    public final ThreadGroup getThreadGroup() {
        if (getState() == State.TERMINATED) {
            return null;
        }
        return this.group;
    }

    public final void join(long j) {
        synchronized (this.lock) {
            long currentTimeMillis = System.currentTimeMillis();
            int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
            if (i >= 0) {
                if (i != 0) {
                    long j2 = 0;
                    while (true) {
                        if (!isAlive()) {
                            break;
                        }
                        long j3 = j - j2;
                        if (j3 <= 0) {
                            break;
                        }
                        this.lock.wait(j3);
                        j2 = System.currentTimeMillis() - currentTimeMillis;
                    }
                } else {
                    while (isAlive()) {
                        this.lock.wait(0);
                    }
                }
            } else {
                throw new IllegalArgumentException("timeout value is negative");
            }
        }
    }

    public final void join() {
        join(0);
    }

    public static void dumpStack() {
        new Exception("Stack trace").printStackTrace();
    }

    public final void setDaemon(boolean z) {
        checkAccess();
        if (!isAlive()) {
            this.daemon = z;
            return;
        }
        throw new IllegalThreadStateException();
    }

    public final boolean isDaemon() {
        return this.daemon;
    }

    public String toString() {
        ThreadGroup threadGroup = getThreadGroup();
        if (threadGroup != null) {
            return "Thread[" + getName() + "," + getPriority() + "," + threadGroup.getName() + "]";
        }
        return "Thread[" + getName() + "," + getPriority() + ",]";
    }

    public ClassLoader getContextClassLoader() {
        return this.contextClassLoader;
    }

    public void setContextClassLoader(ClassLoader classLoader) {
        this.contextClassLoader = classLoader;
    }

    public long getId() {
        return this.tid;
    }

    public State getState() {
        return State.values()[nativeGetStatus(this.started)];
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler2) {
        checkAccess();
        this.uncaughtExceptionHandler = uncaughtExceptionHandler2;
    }
}
