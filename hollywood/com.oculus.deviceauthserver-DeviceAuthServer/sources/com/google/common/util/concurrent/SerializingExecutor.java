package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

final class SerializingExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    private final Object internalLock = new Object() {
        /* class com.google.common.util.concurrent.SerializingExecutor.AnonymousClass1 */

        public String toString() {
            return "SerializingExecutor lock: " + super.toString();
        }
    };
    @GuardedBy("internalLock")
    private boolean isThreadScheduled = false;
    private final TaskRunner taskRunner = new TaskRunner();
    @GuardedBy("internalLock")
    private final Queue<Runnable> waitQueue = new ArrayDeque();

    public SerializingExecutor(Executor executor2) {
        Preconditions.checkNotNull(executor2, "'executor' must not be null.");
        this.executor = executor2;
    }

    public void execute(Runnable r) {
        Preconditions.checkNotNull(r, "'r' must not be null.");
        boolean scheduleTaskRunner = false;
        synchronized (this.internalLock) {
            this.waitQueue.add(r);
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
                scheduleTaskRunner = true;
            }
        }
        if (scheduleTaskRunner) {
            try {
                this.executor.execute(this.taskRunner);
                if (0 != 0) {
                    synchronized (this.internalLock) {
                        this.isThreadScheduled = false;
                    }
                }
            } catch (Throwable th) {
                if (1 != 0) {
                    synchronized (this.internalLock) {
                        this.isThreadScheduled = false;
                    }
                }
                throw th;
            }
        }
    }

    private class TaskRunner implements Runnable {
        private TaskRunner() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
            if (0 == 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
            r2 = r8.this$0.internalLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            r8.this$0.isThreadScheduled = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r3.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
            r4 = com.google.common.util.concurrent.SerializingExecutor.log;
            r5 = java.util.logging.Level.SEVERE;
            r4.log(r5, "Exception while executing runnable " + r3, (java.lang.Throwable) r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            // Method dump skipped, instructions count: 116
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SerializingExecutor.TaskRunner.run():void");
        }
    }
}
