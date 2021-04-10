package X;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

/* renamed from: X.1XG  reason: invalid class name */
public final class AnonymousClass1XG<V> extends FutureTask<V> implements AnonymousClass1XI<V> {
    public final AnonymousClass1XH A00 = new AnonymousClass1XH();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1.isEmpty() != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        throw new java.lang.NullPointerException("execute");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void done() {
        /*
            r3 = this;
            X.1XH r2 = r3.A00
            java.util.Queue<com.facebook.rti.mqtt.common.executors.ExecutionList$RunnableExecutorPair> r1 = r2.A01
            monitor-enter(r1)
            boolean r0 = r2.A00     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            return
        L_0x000b:
            r0 = 1
            r2.A00 = r0     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x0020
            r1.poll()
            java.lang.String r1 = "execute"
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>(r1)
            throw r0
        L_0x0020:
            return
        L_0x0021:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1XG.done():void");
    }

    public AnonymousClass1XG(Runnable runnable, @Nullable V v) {
        super(runnable, v);
    }

    public AnonymousClass1XG(Callable<V> callable) {
        super(callable);
    }
}
