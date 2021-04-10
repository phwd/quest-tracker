package java.lang;

import android.system.Os;
import android.system.OsConstants;
import dalvik.system.VMRuntime;
import java.lang.ref.FinalizerReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import libcore.util.EmptyArray;

public final class Daemons {
    private static final Daemon[] DAEMONS = {HeapTaskDaemon.INSTANCE, ReferenceQueueDaemon.INSTANCE, FinalizerDaemon.INSTANCE, FinalizerWatchdogDaemon.INSTANCE};
    private static long MAX_FINALIZE_NANOS = 10000000000L;
    private static final int NANOS_PER_MILLI = 1000000;
    private static final CountDownLatch POST_ZYGOTE_START_LATCH = new CountDownLatch(DAEMONS.length);
    private static final CountDownLatch PRE_ZYGOTE_START_LATCH = new CountDownLatch(DAEMONS.length);
    private static boolean postZygoteFork = false;

    public static void start() {
        for (Daemon daemon : DAEMONS) {
            daemon.start();
        }
    }

    public static void startPostZygoteFork() {
        postZygoteFork = true;
        for (Daemon daemon : DAEMONS) {
            daemon.startPostZygoteFork();
        }
    }

    public static void stop() {
        for (Daemon daemon : DAEMONS) {
            daemon.stop();
        }
    }

    private static void waitForDaemonStart() throws Exception {
        if (postZygoteFork) {
            POST_ZYGOTE_START_LATCH.await();
        } else {
            PRE_ZYGOTE_START_LATCH.await();
        }
    }

    private static abstract class Daemon implements Runnable {
        private String name;
        private boolean postZygoteFork;
        private Thread thread;

        public abstract void runInternal();

        protected Daemon(String name2) {
            this.name = name2;
        }

        public synchronized void start() {
            startInternal();
        }

        public synchronized void startPostZygoteFork() {
            this.postZygoteFork = true;
            startInternal();
        }

        public void startInternal() {
            if (this.thread == null) {
                this.thread = new Thread(ThreadGroup.systemThreadGroup, this, this.name);
                this.thread.setDaemon(true);
                this.thread.setSystemDaemon(true);
                this.thread.start();
                return;
            }
            throw new IllegalStateException("already running");
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.postZygoteFork) {
                VMRuntime.getRuntime();
                VMRuntime.setSystemDaemonThreadPriority();
                Daemons.POST_ZYGOTE_START_LATCH.countDown();
            } else {
                Daemons.PRE_ZYGOTE_START_LATCH.countDown();
            }
            runInternal();
        }

        /* access modifiers changed from: protected */
        public synchronized boolean isRunning() {
            return this.thread != null;
        }

        public synchronized void interrupt() {
            interrupt(this.thread);
        }

        public synchronized void interrupt(Thread thread2) {
            if (thread2 != null) {
                thread2.interrupt();
            } else {
                throw new IllegalStateException("not running");
            }
        }

        public void stop() {
            Thread threadToStop;
            synchronized (this) {
                threadToStop = this.thread;
                this.thread = null;
            }
            if (threadToStop != null) {
                interrupt(threadToStop);
                while (true) {
                    try {
                        threadToStop.join();
                        break;
                    } catch (InterruptedException | OutOfMemoryError e) {
                    }
                }
                return;
            }
            throw new IllegalStateException("not running");
        }

        public synchronized StackTraceElement[] getStackTrace() {
            return this.thread != null ? this.thread.getStackTrace() : EmptyArray.STACK_TRACE_ELEMENT;
        }
    }

    private static class ReferenceQueueDaemon extends Daemon {
        private static final ReferenceQueueDaemon INSTANCE = new ReferenceQueueDaemon();

        ReferenceQueueDaemon() {
            super("ReferenceQueueDaemon");
        }

        @Override // java.lang.Daemons.Daemon
        public void runInternal() {
            Reference<?> list;
            while (isRunning()) {
                try {
                    synchronized (ReferenceQueue.class) {
                        while (ReferenceQueue.unenqueued == null) {
                            ReferenceQueue.class.wait();
                        }
                        list = ReferenceQueue.unenqueued;
                        ReferenceQueue.unenqueued = null;
                    }
                    ReferenceQueue.enqueuePending(list);
                } catch (InterruptedException | OutOfMemoryError e) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class FinalizerDaemon extends Daemon {
        private static final FinalizerDaemon INSTANCE = new FinalizerDaemon();
        private Object finalizingObject = null;
        private final AtomicInteger progressCounter = new AtomicInteger(0);
        private final ReferenceQueue<Object> queue = FinalizerReference.queue;

        FinalizerDaemon() {
            super("FinalizerDaemon");
        }

        @Override // java.lang.Daemons.Daemon
        public void runInternal() {
            int localProgressCounter = this.progressCounter.get();
            while (isRunning()) {
                try {
                    FinalizerReference<?> finalizingReference = (FinalizerReference) this.queue.poll();
                    if (finalizingReference != null) {
                        this.finalizingObject = finalizingReference.get();
                        localProgressCounter++;
                        this.progressCounter.lazySet(localProgressCounter);
                    } else {
                        this.finalizingObject = null;
                        int localProgressCounter2 = localProgressCounter + 1;
                        this.progressCounter.lazySet(localProgressCounter2);
                        FinalizerWatchdogDaemon.INSTANCE.goToSleep();
                        finalizingReference = (FinalizerReference) this.queue.remove();
                        this.finalizingObject = finalizingReference.get();
                        localProgressCounter = localProgressCounter2 + 1;
                        this.progressCounter.set(localProgressCounter);
                        FinalizerWatchdogDaemon.INSTANCE.wakeUp();
                    }
                    doFinalize(finalizingReference);
                } catch (InterruptedException | OutOfMemoryError e) {
                }
            }
        }

        @FindBugsSuppressWarnings({"FI_EXPLICIT_INVOCATION"})
        private void doFinalize(FinalizerReference<?> reference) {
            FinalizerReference.remove(reference);
            Object object = reference.get();
            reference.clear();
            try {
                object.finalize();
            } catch (Throwable th) {
                this.finalizingObject = null;
                throw th;
            }
            this.finalizingObject = null;
        }
    }

    private static class FinalizerWatchdogDaemon extends Daemon {
        private static final FinalizerWatchdogDaemon INSTANCE = new FinalizerWatchdogDaemon();
        private long finalizerTimeoutMs = 0;
        private boolean needToWork = true;

        FinalizerWatchdogDaemon() {
            super("FinalizerWatchdogDaemon");
        }

        @Override // java.lang.Daemons.Daemon
        public void runInternal() {
            Object finalizing;
            while (isRunning()) {
                if (sleepUntilNeeded() && (finalizing = waitForFinalization()) != null && !VMRuntime.getRuntime().isDebuggerActive()) {
                    finalizerTimedOut(finalizing);
                    return;
                }
            }
        }

        private synchronized boolean sleepUntilNeeded() {
            while (!this.needToWork) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return false;
                } catch (OutOfMemoryError e2) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized void goToSleep() {
            this.needToWork = false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized void wakeUp() {
            this.needToWork = true;
            notify();
        }

        private synchronized boolean getNeedToWork() {
            return this.needToWork;
        }

        private boolean sleepForMillis(long durationMillis) {
            long startMillis = System.currentTimeMillis();
            while (true) {
                long sleepMillis = durationMillis - (System.currentTimeMillis() - startMillis);
                if (sleepMillis <= 0) {
                    return true;
                }
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    if (!isRunning()) {
                        return false;
                    }
                } catch (OutOfMemoryError e2) {
                    if (!isRunning()) {
                        return false;
                    }
                }
            }
        }

        private Object waitForFinalization() {
            if (this.finalizerTimeoutMs == 0) {
                this.finalizerTimeoutMs = VMRuntime.getRuntime().getFinalizerTimeoutMs();
                long unused = Daemons.MAX_FINALIZE_NANOS = this.finalizerTimeoutMs * 1000000;
            }
            long startCount = (long) FinalizerDaemon.INSTANCE.progressCounter.get();
            if (sleepForMillis(this.finalizerTimeoutMs) && getNeedToWork() && ((long) FinalizerDaemon.INSTANCE.progressCounter.get()) == startCount) {
                Object finalizing = FinalizerDaemon.INSTANCE.finalizingObject;
                sleepForMillis(500);
                if (!getNeedToWork() || ((long) FinalizerDaemon.INSTANCE.progressCounter.get()) != startCount) {
                    return null;
                }
                return finalizing;
            }
            return null;
        }

        private static void finalizerTimedOut(Object object) {
            String message = object.getClass().getName() + ".finalize() timed out after " + (VMRuntime.getRuntime().getFinalizerTimeoutMs() / 1000) + " seconds";
            Exception syntheticException = new TimeoutException(message);
            syntheticException.setStackTrace(FinalizerDaemon.INSTANCE.getStackTrace());
            try {
                Os.kill(Os.getpid(), OsConstants.SIGQUIT);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.logE("failed to send SIGQUIT", e);
            } catch (OutOfMemoryError e2) {
            }
            if (Thread.getUncaughtExceptionPreHandler() == null && Thread.getDefaultUncaughtExceptionHandler() == null) {
                System.logE(message, syntheticException);
                System.exit(2);
            }
            Thread.currentThread().dispatchUncaughtException(syntheticException);
        }
    }

    public static void requestHeapTrim() {
        VMRuntime.getRuntime().requestHeapTrim();
    }

    public static void requestGC() {
        VMRuntime.getRuntime().requestConcurrentGC();
    }

    private static class HeapTaskDaemon extends Daemon {
        private static final HeapTaskDaemon INSTANCE = new HeapTaskDaemon();

        HeapTaskDaemon() {
            super("HeapTaskDaemon");
        }

        @Override // java.lang.Daemons.Daemon
        public synchronized void interrupt(Thread thread) {
            VMRuntime.getRuntime().stopHeapTaskProcessor();
        }

        @Override // java.lang.Daemons.Daemon
        public void runInternal() {
            synchronized (this) {
                if (isRunning()) {
                    VMRuntime.getRuntime().startHeapTaskProcessor();
                }
            }
            VMRuntime.getRuntime().runHeapTasks();
        }
    }
}
