package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.bb  reason: case insensitive filesystem */
public final class C0538bb {
    public ExecutorService A00;
    public final Deque A01 = new ArrayDeque();
    public final Deque A02 = new ArrayDeque();
    public final Deque A03 = new ArrayDeque();

    public static final synchronized ExecutorService A01(C0538bb bbVar) {
        ExecutorService executorService;
        synchronized (bbVar) {
            executorService = bbVar.A00;
            if (executorService == null) {
                executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC0560bx("OkHttp Dispatcher", false));
                bbVar.A00 = executorService;
            }
        }
        return executorService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        if (r4.size() >= 64) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A02(X.C1158ty r7) {
        /*
            r6 = this;
            r5 = r6
            java.util.Deque r4 = r6.A02
            monitor-enter(r5)
            boolean r0 = r4.remove(r7)     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0052
            int r1 = r4.size()     // Catch:{ all -> 0x005a }
            r0 = 64
            if (r1 >= r0) goto L_0x0046
            java.util.Deque r1 = r6.A01     // Catch:{ all -> 0x005a }
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x005a }
            if (r0 != 0) goto L_0x0046
            java.util.Iterator r3 = r1.iterator()     // Catch:{ all -> 0x005a }
        L_0x001e:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0046
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x005a }
            X.ty r2 = (X.C1158ty) r2     // Catch:{ all -> 0x005a }
            int r1 = A00(r6, r2)     // Catch:{ all -> 0x005a }
            r0 = 5
            if (r1 >= r0) goto L_0x003e
            r3.remove()     // Catch:{ all -> 0x005a }
            r4.add(r2)     // Catch:{ all -> 0x005a }
            java.util.concurrent.ExecutorService r0 = A01(r6)     // Catch:{ all -> 0x005a }
            r0.execute(r2)     // Catch:{ all -> 0x005a }
        L_0x003e:
            int r1 = r4.size()     // Catch:{ all -> 0x005a }
            r0 = 64
            if (r1 < r0) goto L_0x001e
        L_0x0046:
            r4.size()     // Catch:{ all -> 0x0050 }
            java.util.Deque r0 = r6.A03     // Catch:{ all -> 0x0050 }
            r0.size()     // Catch:{ all -> 0x0050 }
            monitor-exit(r5)
            return
        L_0x0050:
            r0 = move-exception
            throw r0
        L_0x0052:
            java.lang.String r1 = "Call wasn't in-flight!"
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r1)
            throw r0
        L_0x005a:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0538bb.A02(X.ty):void");
    }

    public static int A00(C0538bb bbVar, C1158ty tyVar) {
        int i = 0;
        for (C1158ty tyVar2 : bbVar.A02) {
            if (tyVar2.A01.A02.A03.A02.equals(tyVar.A01.A02.A03.A02)) {
                i++;
            }
        }
        return i;
    }
}
