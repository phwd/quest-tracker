package X;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

/* renamed from: X.0n6  reason: invalid class name */
public final class AnonymousClass0n6<V> extends FutureTask<V> implements AnonymousClass0XB<V> {
    public final AnonymousClass0X8 A00 = new AnonymousClass0X8();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1.isEmpty() != false) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void done() {
        /*
            r3 = this;
            X.0X8 r2 = r3.A00
            java.util.Queue<com.facebook.rti.mqtt.common.executors.ExecutionList$RunnableExecutorPair> r1 = r2.A01
            monitor-enter(r1)
            boolean r0 = r2.A00     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x000b:
            r0 = 1
            r2.A00 = r0     // Catch:{ all -> 0x001b }
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x001a
            r1.poll()
            r0 = 0
            throw r0
        L_0x001a:
            return
        L_0x001b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0n6.done():void");
    }

    public AnonymousClass0n6(Runnable runnable, @Nullable V v) {
        super(runnable, v);
    }

    public AnonymousClass0n6(Callable<V> callable) {
        super(callable);
    }
}
