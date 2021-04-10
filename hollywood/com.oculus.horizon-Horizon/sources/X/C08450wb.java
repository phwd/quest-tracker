package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0wb  reason: invalid class name and case insensitive filesystem */
public final class C08450wb {
    public ExecutorService A00;
    public final Deque<AnonymousClass0N0> A01 = new ArrayDeque();
    public final Deque<AnonymousClass0N0> A02 = new ArrayDeque();
    public final Deque<AnonymousClass0Mz> A03 = new ArrayDeque();

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/util/Deque<TT;>;TT;Z)V */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        if (r4.size() >= 64) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.C08450wb r5, java.util.Deque r6, java.lang.Object r7, boolean r8) {
        /*
            monitor-enter(r5)
            boolean r0 = r6.remove(r7)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0055
            if (r8 == 0) goto L_0x0047
            java.util.Deque<X.0N0> r4 = r5.A02     // Catch:{ all -> 0x005d }
            int r1 = r4.size()     // Catch:{ all -> 0x005d }
            r0 = 64
            if (r1 >= r0) goto L_0x0047
            java.util.Deque<X.0N0> r1 = r5.A01     // Catch:{ all -> 0x005d }
            boolean r0 = r1.isEmpty()     // Catch:{ all -> 0x005d }
            if (r0 != 0) goto L_0x0047
            java.util.Iterator r3 = r1.iterator()     // Catch:{ all -> 0x005d }
        L_0x001f:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0047
            java.lang.Object r2 = r3.next()     // Catch:{ all -> 0x005d }
            X.0N0 r2 = (X.AnonymousClass0N0) r2     // Catch:{ all -> 0x005d }
            int r1 = A00(r5, r2)     // Catch:{ all -> 0x005d }
            r0 = 5
            if (r1 >= r0) goto L_0x003f
            r3.remove()     // Catch:{ all -> 0x005d }
            r4.add(r2)     // Catch:{ all -> 0x005d }
            java.util.concurrent.ExecutorService r0 = r5.A02()     // Catch:{ all -> 0x005d }
            r0.execute(r2)     // Catch:{ all -> 0x005d }
        L_0x003f:
            int r1 = r4.size()     // Catch:{ all -> 0x005d }
            r0 = 64
            if (r1 < r0) goto L_0x001f
        L_0x0047:
            java.util.Deque<X.0N0> r0 = r5.A02     // Catch:{ all -> 0x0053 }
            r0.size()     // Catch:{ all -> 0x0053 }
            java.util.Deque<X.0Mz> r0 = r5.A03     // Catch:{ all -> 0x0053 }
            r0.size()     // Catch:{ all -> 0x0053 }
            monitor-exit(r5)
            return
        L_0x0053:
            r0 = move-exception
            goto L_0x005c
        L_0x0055:
            java.lang.String r1 = "Call wasn't in-flight!"
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r1)
        L_0x005c:
            throw r0
        L_0x005d:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08450wb.A01(X.0wb, java.util.Deque, java.lang.Object, boolean):void");
    }

    public final synchronized ExecutorService A02() {
        ExecutorService executorService;
        executorService = this.A00;
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new AnonymousClass0w6("OkHttp Dispatcher", false));
            this.A00 = executorService;
        }
        return executorService;
    }

    public static int A00(C08450wb r3, AnonymousClass0N0 r4) {
        int i = 0;
        for (AnonymousClass0N0 r0 : r3.A02) {
            if (r0.A01.A02.A03.A02.equals(r4.A01.A02.A03.A02)) {
                i++;
            }
        }
        return i;
    }
}
