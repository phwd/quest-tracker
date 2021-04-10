package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20d  reason: invalid class name and case insensitive filesystem */
public final class C137120d<T, R> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<T> {
    public static final long serialVersionUID = -4823716997131257941L;
    public final int index;
    public final AnonymousClass20Z<T, R> parent;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r1 == r4.length) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r2 == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        X.AnonymousClass20Z.A00(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        X.AnonymousClass20Z.A01(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        return;
     */
    @Override // X.AnonymousClass1yM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete() {
        /*
            r6 = this;
            X.20Z<T, R> r5 = r6.parent
            int r0 = r6.index
            monitor-enter(r5)
            java.lang.Object[] r4 = r5.latest     // Catch:{ all -> 0x0026 }
            if (r4 != 0) goto L_0x000b
            monitor-exit(r5)     // Catch:{ all -> 0x0026 }
            return
        L_0x000b:
            r0 = r4[r0]     // Catch:{ all -> 0x0026 }
            r3 = 1
            r2 = 1
            if (r0 == 0) goto L_0x001a
            r2 = 0
            int r1 = r5.complete     // Catch:{ all -> 0x0026 }
            int r1 = r1 + r3
            r5.complete = r1     // Catch:{ all -> 0x0026 }
            int r0 = r4.length     // Catch:{ all -> 0x0026 }
            if (r1 != r0) goto L_0x001c
        L_0x001a:
            r5.done = r3     // Catch:{ all -> 0x0026 }
        L_0x001c:
            monitor-exit(r5)     // Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x0022
            X.AnonymousClass20Z.A00(r5)
        L_0x0022:
            X.AnonymousClass20Z.A01(r5)
            return
        L_0x0026:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C137120d.onComplete():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        if (r1 == r3.length) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        if (r2 == false) goto L_0x0032;
     */
    @Override // X.AnonymousClass1yM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onError(java.lang.Throwable r7) {
        /*
            r6 = this;
            X.20Z<T, R> r5 = r6.parent
            int r1 = r6.index
            X.20G r0 = r5.errors
            boolean r0 = r0.A01(r7)
            if (r0 == 0) goto L_0x0036
            boolean r0 = r5.delayError
            r4 = 1
            if (r0 == 0) goto L_0x002f
            monitor-enter(r5)
            java.lang.Object[] r3 = r5.latest     // Catch:{ all -> 0x002a }
            if (r3 != 0) goto L_0x0018
            monitor-exit(r5)     // Catch:{ all -> 0x002a }
            return
        L_0x0018:
            r0 = r3[r1]     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r0 == 0) goto L_0x0026
            r2 = 0
            int r1 = r5.complete     // Catch:{ all -> 0x002a }
            int r1 = r1 + r4
            r5.complete = r1     // Catch:{ all -> 0x002a }
            int r0 = r3.length     // Catch:{ all -> 0x002a }
            if (r1 != r0) goto L_0x0028
        L_0x0026:
            r5.done = r4     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r5)     // Catch:{ all -> 0x002a }
            goto L_0x002d
        L_0x002a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x002a }
            throw r0
        L_0x002d:
            if (r2 == 0) goto L_0x0032
        L_0x002f:
            X.AnonymousClass20Z.A00(r5)
        L_0x0032:
            X.AnonymousClass20Z.A01(r5)
            return
        L_0x0036:
            X.AnonymousClass1y3.A01(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C137120d.onError(java.lang.Throwable):void");
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        this.parent.A03(this.index, t);
    }

    public C137120d(AnonymousClass20Z<T, R> r1, int i) {
        this.parent = r1;
        this.index = i;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r1) {
        EnumC12511xi.setOnce(this, r1);
    }
}
