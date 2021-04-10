package java.lang;

import dalvik.system.VMStack;
import java.lang.ThreadLocal;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import libcore.util.EmptyArray;
import sun.nio.ch.Interruptible;
import sun.reflect.CallerSensitive;

public class Thread implements Runnable {
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    public static final int MAX_PRIORITY = 10;
    public static final int MIN_PRIORITY = 1;
    public static final int NORM_PRIORITY = 5;
    private static final RuntimePermission SUBCLASS_IMPLEMENTATION_PERMISSION = new RuntimePermission("enableContextClassLoaderOverride");
    private static volatile UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    private static int threadInitNumber;
    private static long threadSeqNumber;
    private static volatile UncaughtExceptionHandler uncaughtExceptionPreHandler;
    private volatile Interruptible blocker;
    private final Object blockerLock = new Object();
    private ClassLoader contextClassLoader;
    private boolean daemon = false;
    private long eetop;
    private ThreadGroup group;
    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
    private AccessControlContext inheritedAccessControlContext;
    private final Object lock = new Object();
    private volatile String name;
    private volatile long nativePeer;
    volatile Object parkBlocker;
    private int priority;
    private boolean single_step;
    private long stackSize;
    boolean started = false;
    private boolean stillborn = false;
    private boolean systemDaemon = false;
    private Runnable target;
    int threadLocalRandomProbe;
    int threadLocalRandomSecondarySeed;
    long threadLocalRandomSeed;
    ThreadLocal.ThreadLocalMap threadLocals = null;
    private Thread threadQ;
    private long tid;
    private volatile UncaughtExceptionHandler uncaughtExceptionHandler;
    private boolean unparkedBeforeStart;

    public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED
    }

    @FunctionalInterface
    public interface UncaughtExceptionHandler {
        void uncaughtException(Thread thread, Throwable th);
    }

    public static native Thread currentThread();

    public static native boolean holdsLock(Object obj);

    private native void interrupt0();

    public static native boolean interrupted();

    private static native void nativeCreate(Thread thread, long j, boolean z);

    private native int nativeGetStatus(boolean z);

    private native void setNativeName(String str);

    private native void setPriority0(int i);

    private static native void sleep(Object obj, long j, int i) throws InterruptedException;

    public static native void yield();

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

    public void blockedOn(Interruptible b) {
        synchronized (this.blockerLock) {
            this.blocker = b;
        }
    }

    public static void sleep(long millis) throws InterruptedException {
        sleep(millis, 0);
    }

    public static void sleep(long millis, int nanos) throws InterruptedException {
        Throwable th;
        if (millis < 0) {
            throw new IllegalArgumentException("millis < 0: " + millis);
        } else if (nanos < 0) {
            throw new IllegalArgumentException("nanos < 0: " + nanos);
        } else if (nanos > 999999) {
            throw new IllegalArgumentException("nanos > 999999: " + nanos);
        } else if (millis != 0 || nanos != 0) {
            long start = System.nanoTime();
            long duration = (millis * 1000000) + ((long) nanos);
            Object lock2 = currentThread().lock;
            synchronized (lock2) {
                long duration2 = duration;
                long start2 = start;
                int nanos2 = nanos;
                long millis2 = millis;
                while (true) {
                    try {
                        sleep(lock2, millis2, nanos2);
                        long now = System.nanoTime();
                        long elapsed = now - start2;
                        if (elapsed < duration2) {
                            duration2 -= elapsed;
                            start2 = now;
                            try {
                                nanos2 = (int) (duration2 % 1000000);
                                millis2 = duration2 / 1000000;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            }
        } else if (interrupted()) {
            throw new InterruptedException();
        }
    }

    private void init(ThreadGroup g, Runnable target2, String name2, long stackSize2) {
        init(g, target2, name2, stackSize2, null);
    }

    private void init(ThreadGroup g, Runnable target2, String name2, long stackSize2, AccessControlContext acc) {
        if (name2 != null) {
            this.name = name2;
            Thread parent = currentThread();
            if (g == null) {
                g = parent.getThreadGroup();
            }
            g.addUnstarted();
            this.group = g;
            this.daemon = parent.isDaemon();
            this.priority = parent.getPriority();
            this.target = target2;
            init2(parent);
            this.stackSize = stackSize2;
            this.tid = nextThreadID();
            return;
        }
        throw new NullPointerException("name cannot be null");
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Thread() {
        init(null, null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(Runnable target2) {
        init(null, target2, "Thread-" + nextThreadNum(), 0);
    }

    Thread(Runnable target2, AccessControlContext acc) {
        init(null, target2, "Thread-" + nextThreadNum(), 0, acc);
    }

    public Thread(ThreadGroup group2, Runnable target2) {
        init(group2, target2, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(String name2) {
        init(null, null, name2, 0);
    }

    public Thread(ThreadGroup group2, String name2) {
        init(group2, null, name2, 0);
    }

    Thread(ThreadGroup group2, String name2, int priority2, boolean daemon2) {
        this.group = group2;
        this.group.addUnstarted();
        if (name2 == null) {
            name2 = "Thread-" + nextThreadNum();
        }
        this.name = name2;
        this.priority = priority2;
        this.daemon = daemon2;
        init2(currentThread());
        this.tid = nextThreadID();
    }

    private void init2(Thread parent) {
        this.contextClassLoader = parent.getContextClassLoader();
        this.inheritedAccessControlContext = AccessController.getContext();
        ThreadLocal.ThreadLocalMap threadLocalMap = parent.inheritableThreadLocals;
        if (threadLocalMap != null) {
            this.inheritableThreadLocals = ThreadLocal.createInheritedMap(threadLocalMap);
        }
    }

    public Thread(Runnable target2, String name2) {
        init(null, target2, name2, 0);
    }

    public Thread(ThreadGroup group2, Runnable target2, String name2) {
        init(group2, target2, name2, 0);
    }

    public Thread(ThreadGroup group2, Runnable target2, String name2, long stackSize2) {
        init(group2, target2, name2, stackSize2);
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
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
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

    private void exit() {
        ThreadGroup threadGroup = this.group;
        if (threadGroup != null) {
            threadGroup.threadTerminated(this);
            this.group = null;
        }
        this.target = null;
        this.threadLocals = null;
        this.inheritableThreadLocals = null;
        this.inheritedAccessControlContext = null;
        this.blocker = null;
        this.uncaughtExceptionHandler = null;
    }

    @Deprecated
    public final void stop() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final synchronized void stop(Throwable obj) {
        throw new UnsupportedOperationException();
    }

    public void interrupt() {
        if (this != currentThread()) {
            checkAccess();
        }
        synchronized (this.blockerLock) {
            Interruptible b = this.blocker;
            if (b != null) {
                interrupt0();
                b.interrupt(this);
                return;
            }
            interrupt0();
        }
    }

    @Deprecated
    public void destroy() {
        throw new UnsupportedOperationException();
    }

    public final boolean isAlive() {
        return this.nativePeer != 0;
    }

    @Deprecated
    public final void suspend() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void resume() {
        throw new UnsupportedOperationException();
    }

    public final void setPriority(int newPriority) {
        int newPriority2;
        checkAccess();
        if (newPriority > 10 || newPriority < 1) {
            throw new IllegalArgumentException("Priority out of range: " + newPriority);
        }
        ThreadGroup g = getThreadGroup();
        if (g != null) {
            if (newPriority > g.getMaxPriority()) {
                newPriority2 = g.getMaxPriority();
            } else {
                newPriority2 = newPriority;
            }
            synchronized (this) {
                this.priority = newPriority2;
                if (isAlive()) {
                    setPriority0(newPriority2);
                }
            }
        }
    }

    public final int getPriority() {
        return this.priority;
    }

    public final synchronized void setName(String name2) {
        checkAccess();
        if (name2 != null) {
            this.name = name2;
            if (isAlive()) {
                setNativeName(name2);
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

    public static int activeCount() {
        return currentThread().getThreadGroup().activeCount();
    }

    public static int enumerate(Thread[] tarray) {
        return currentThread().getThreadGroup().enumerate(tarray);
    }

    @Deprecated
    public int countStackFrames() {
        return getStackTrace().length;
    }

    public final void join(long millis) throws InterruptedException {
        synchronized (this.lock) {
            long base = System.currentTimeMillis();
            long now = 0;
            if (millis >= 0) {
                if (millis != 0) {
                    while (true) {
                        if (!isAlive()) {
                            break;
                        }
                        long delay = millis - now;
                        if (delay <= 0) {
                            break;
                        }
                        this.lock.wait(delay);
                        now = System.currentTimeMillis() - base;
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

    public final void join(long millis, int nanos) throws InterruptedException {
        synchronized (this.lock) {
            if (millis < 0) {
                throw new IllegalArgumentException("timeout value is negative");
            } else if (nanos < 0 || nanos > 999999) {
                throw new IllegalArgumentException("nanosecond timeout value out of range");
            } else {
                if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
                    millis++;
                }
                join(millis);
            }
        }
    }

    public final void join() throws InterruptedException {
        join(0);
    }

    public static void dumpStack() {
        new Exception("Stack trace").printStackTrace();
    }

    public final void setDaemon(boolean on) {
        checkAccess();
        if (!isAlive()) {
            this.daemon = on;
            return;
        }
        throw new IllegalThreadStateException();
    }

    public final boolean isDaemon() {
        return this.daemon;
    }

    public final void checkAccess() {
    }

    public String toString() {
        ThreadGroup group2 = getThreadGroup();
        if (group2 != null) {
            return "Thread[" + getName() + "," + getPriority() + "," + group2.getName() + "]";
        }
        return "Thread[" + getName() + "," + getPriority() + ",]";
    }

    @CallerSensitive
    public ClassLoader getContextClassLoader() {
        return this.contextClassLoader;
    }

    public void setContextClassLoader(ClassLoader cl) {
        this.contextClassLoader = cl;
    }

    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] ste = VMStack.getThreadStackTrace(this);
        return ste != null ? ste : EmptyArray.STACK_TRACE_ELEMENT;
    }

    public static Map<Thread, StackTraceElement[]> getAllStackTraces() {
        int count = ThreadGroup.systemThreadGroup.activeCount();
        Thread[] threads = new Thread[((count / 2) + count)];
        int count2 = ThreadGroup.systemThreadGroup.enumerate(threads);
        Map<Thread, StackTraceElement[]> m = new HashMap<>();
        for (int i = 0; i < count2; i++) {
            m.put(threads[i], threads[i].getStackTrace());
        }
        return m;
    }

    private static class Caches {
        static final ConcurrentMap<WeakClassKey, Boolean> subclassAudits = new ConcurrentHashMap();
        static final ReferenceQueue<Class<?>> subclassAuditsQueue = new ReferenceQueue<>();

        private Caches() {
        }
    }

    private static boolean isCCLOverridden(Class<?> cl) {
        if (cl == Thread.class) {
            return false;
        }
        processQueue(Caches.subclassAuditsQueue, Caches.subclassAudits);
        WeakClassKey key = new WeakClassKey(cl, Caches.subclassAuditsQueue);
        Boolean result = Caches.subclassAudits.get(key);
        if (result == null) {
            result = Boolean.valueOf(auditSubclass(cl));
            Caches.subclassAudits.putIfAbsent(key, result);
        }
        return result.booleanValue();
    }

    private static boolean auditSubclass(final Class<?> subcl) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            /* class java.lang.Thread.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Boolean run() {
                for (Class<?> cl = Class.this; cl != Thread.class; cl = cl.getSuperclass()) {
                    try {
                        cl.getDeclaredMethod("getContextClassLoader", new Class[0]);
                        return Boolean.TRUE;
                    } catch (NoSuchMethodException e) {
                        try {
                            cl.getDeclaredMethod("setContextClassLoader", ClassLoader.class);
                            return Boolean.TRUE;
                        } catch (NoSuchMethodException e2) {
                        }
                    }
                }
                return Boolean.FALSE;
            }
        })).booleanValue();
    }

    public long getId() {
        return this.tid;
    }

    public State getState() {
        return State.values()[nativeGetStatus(this.started)];
    }

    public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        defaultUncaughtExceptionHandler = eh;
    }

    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return defaultUncaughtExceptionHandler;
    }

    public static void setUncaughtExceptionPreHandler(UncaughtExceptionHandler eh) {
        uncaughtExceptionPreHandler = eh;
    }

    public static UncaughtExceptionHandler getUncaughtExceptionPreHandler() {
        return uncaughtExceptionPreHandler;
    }

    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler != null ? this.uncaughtExceptionHandler : this.group;
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        checkAccess();
        this.uncaughtExceptionHandler = eh;
    }

    public final void dispatchUncaughtException(Throwable e) {
        UncaughtExceptionHandler initialUeh = getUncaughtExceptionPreHandler();
        if (initialUeh != null) {
            try {
                initialUeh.uncaughtException(this, e);
            } catch (Error | RuntimeException e2) {
            }
        }
        getUncaughtExceptionHandler().uncaughtException(this, e);
    }

    /* access modifiers changed from: package-private */
    public final void setSystemDaemon(boolean on) {
        checkAccess();
        if (isAlive() || !isDaemon()) {
            throw new IllegalThreadStateException();
        }
        this.systemDaemon = on;
    }

    static void processQueue(ReferenceQueue<Class<?>> queue, ConcurrentMap<? extends WeakReference<Class<?>>, ?> map) {
        while (true) {
            Reference<? extends Class<?>> ref = queue.poll();
            if (ref != null) {
                map.remove(ref);
            } else {
                return;
            }
        }
    }

    static class WeakClassKey extends WeakReference<Class<?>> {
        private final int hash;

        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            this.hash = System.identityHashCode(cl);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WeakClassKey)) {
                return false;
            }
            Object referent = get();
            if (referent == null || referent != ((WeakClassKey) obj).get()) {
                return false;
            }
            return true;
        }
    }
}
