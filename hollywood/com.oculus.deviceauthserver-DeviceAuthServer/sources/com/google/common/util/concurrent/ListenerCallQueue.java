package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

/* access modifiers changed from: package-private */
public final class ListenerCallQueue<L> implements Runnable {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final Executor executor;
    @GuardedBy("this")
    private boolean isThreadScheduled;
    private final L listener;
    @GuardedBy("this")
    private final Queue<Callback<L>> waitQueue = Queues.newArrayDeque();

    /* access modifiers changed from: package-private */
    public static abstract class Callback<L> {
        private final String methodCall;

        /* access modifiers changed from: package-private */
        public abstract void call(L l);

        Callback(String methodCall2) {
            this.methodCall = methodCall2;
        }

        /* access modifiers changed from: package-private */
        public void enqueueOn(Iterable<ListenerCallQueue<L>> queues) {
            for (ListenerCallQueue<L> queue : queues) {
                queue.add(this);
            }
        }
    }

    ListenerCallQueue(L listener2, Executor executor2) {
        this.listener = (L) Preconditions.checkNotNull(listener2);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void add(Callback<L> callback) {
        this.waitQueue.add(callback);
    }

    /* access modifiers changed from: package-private */
    public void execute() {
        boolean scheduleTaskRunner = false;
        synchronized (this) {
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
                scheduleTaskRunner = true;
            }
        }
        if (scheduleTaskRunner) {
            try {
                this.executor.execute(this);
            } catch (RuntimeException e) {
                synchronized (this) {
                    this.isThreadScheduled = false;
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    logger2.log(level, "Exception while running callbacks for " + ((Object) this.listener) + " on " + this.executor, (Throwable) e);
                    throw e;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r8.isThreadScheduled = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r2.call(r8.listener);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0028, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        r4 = com.google.common.util.concurrent.ListenerCallQueue.logger;
        r5 = java.util.logging.Level.SEVERE;
        r4.log(r5, "Exception while executing callback: " + ((java.lang.Object) r8.listener) + "." + ((com.google.common.util.concurrent.ListenerCallQueue.Callback) r2).methodCall, (java.lang.Throwable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (0 == 0) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r8 = this;
            r0 = 1
        L_0x0001:
            r1 = 0
            monitor-enter(r8)     // Catch:{ all -> 0x0053 }
            boolean r2 = r8.isThreadScheduled     // Catch:{ all -> 0x0050 }
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0050 }
            java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Callback<L>> r2 = r8.waitQueue     // Catch:{ all -> 0x0050 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0050 }
            com.google.common.util.concurrent.ListenerCallQueue$Callback r2 = (com.google.common.util.concurrent.ListenerCallQueue.Callback) r2     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0021
            r8.isThreadScheduled = r1     // Catch:{ all -> 0x0050 }
            r0 = 0
            monitor-exit(r8)     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0020
            monitor-enter(r8)
            r8.isThreadScheduled = r1     // Catch:{ all -> 0x001d }
            monitor-exit(r8)     // Catch:{ all -> 0x001d }
            goto L_0x0020
        L_0x001d:
            r1 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x001d }
            throw r1
        L_0x0020:
            return
        L_0x0021:
            monitor-exit(r8)
            L r3 = r8.listener     // Catch:{ RuntimeException -> 0x0028 }
            r2.call(r3)     // Catch:{ RuntimeException -> 0x0028 }
            goto L_0x004f
        L_0x0028:
            r3 = move-exception
            java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.logger
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Exception while executing callback: "
            r6.append(r7)
            L r7 = r8.listener
            r6.append(r7)
            java.lang.String r7 = "."
            r6.append(r7)
            java.lang.String r7 = com.google.common.util.concurrent.ListenerCallQueue.Callback.access$000(r2)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.log(r5, r6, r3)
        L_0x004f:
            goto L_0x0001
        L_0x0050:
            r2 = move-exception
            monitor-exit(r8)
            throw r2
        L_0x0053:
            r2 = move-exception
            if (r0 == 0) goto L_0x005e
            monitor-enter(r8)
            r8.isThreadScheduled = r1     // Catch:{ all -> 0x005b }
            monitor-exit(r8)     // Catch:{ all -> 0x005b }
            goto L_0x005e
        L_0x005b:
            r1 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x005b }
            throw r1
        L_0x005e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.run():void");
    }
}
