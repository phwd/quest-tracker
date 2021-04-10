package X;

/* renamed from: X.1rC  reason: invalid class name */
public class AnonymousClass1rC extends AnonymousClass1qD<T> {
    public final /* synthetic */ AnonymousClass1r0 A00;

    public AnonymousClass1rC(AnonymousClass1r0 r1) {
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r3 = r4.A06.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r3.hasNext() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r0 = r3.next();
        ((java.util.concurrent.Executor) r0.second).execute(new X.AnonymousClass1tU(r4, (X.AnonymousClass1tB) r0.first));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    @Override // X.AnonymousClass1qD
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(float r6) {
        /*
            r5 = this;
            X.1r0 r4 = r5.A00
            monitor-enter(r4)
            boolean r0 = r4.A05     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0039
            java.lang.Integer r1 = r4.A01     // Catch:{ all -> 0x003b }
            java.lang.Integer r0 = X.AnonymousClass007.A00     // Catch:{ all -> 0x003b }
            if (r1 != r0) goto L_0x0039
            float r0 = r4.A00     // Catch:{ all -> 0x003b }
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0039
            r4.A00 = r6     // Catch:{ all -> 0x003b }
            monitor-exit(r4)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<X.1tB<T>, java.util.concurrent.Executor>> r0 = r4.A06
            java.util.Iterator r3 = r0.iterator()
        L_0x001c:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r3.next()
            android.util.Pair r0 = (android.util.Pair) r0
            java.lang.Object r2 = r0.first
            X.1tB r2 = (X.AnonymousClass1tB) r2
            java.lang.Object r1 = r0.second
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            X.1tU r0 = new X.1tU
            r0.<init>(r4, r2)
            r1.execute(r0)
            goto L_0x001c
        L_0x0039:
            monitor-exit(r4)
        L_0x003a:
            return
        L_0x003b:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rC.A04(float):void");
    }
}
