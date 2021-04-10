package com.google.common.util.concurrent;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class ExecutionList {
    @VisibleForTesting
    static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    @GuardedBy("this")
    private boolean executed;
    @GuardedBy("this")
    private RunnableExecutorPair runnables;

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            if (!this.executed) {
                this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
            } else {
                executeListener(runnable, executor);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        r0 = r0.next;
        r0.next = r1;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r1 == null) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        executeListener(r1.runnable, r1.executor);
        r1 = r1.next;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r0 == null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.executed     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r4)     // Catch:{ all -> 0x0027 }
            return
        L_0x0007:
            r0 = 1
            r4.executed = r0     // Catch:{ all -> 0x0027 }
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r4.runnables     // Catch:{ all -> 0x0027 }
            r1 = 0
            r4.runnables = r1     // Catch:{ all -> 0x0027 }
            monitor-exit(r4)     // Catch:{ all -> 0x0027 }
            r1 = 0
        L_0x0011:
            if (r0 == 0) goto L_0x001a
            r2 = r0
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r0.next
            r2.next = r1
            r1 = r2
            goto L_0x0011
        L_0x001a:
            if (r1 == 0) goto L_0x0026
            java.lang.Runnable r2 = r1.runnable
            java.util.concurrent.Executor r3 = r1.executor
            executeListener(r2, r3)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r1 = r1.next
            goto L_0x001a
        L_0x0026:
            return
        L_0x0027:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.execute():void");
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public static final class RunnableExecutorPair {
        final Executor executor;
        @Nullable
        RunnableExecutorPair next;
        final Runnable runnable;

        RunnableExecutorPair(Runnable runnable2, Executor executor2, RunnableExecutorPair next2) {
            this.runnable = runnable2;
            this.executor = executor2;
            this.next = next2;
        }
    }
}
