package X;

import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

/* renamed from: X.211  reason: invalid class name */
public final class AnonymousClass211<T> implements AbstractC12271xB, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
    public boolean A00;
    public long A01;
    public C139921f<Object> A02;
    public boolean A03;
    public boolean A04;
    public final AnonymousClass1yM<? super T> A05;
    public final AnonymousClass210<T> A06;
    public volatile boolean A07;

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002f, code lost:
        r3.A00 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A00(java.lang.Object r4, long r5) {
        /*
            r3 = this;
            boolean r0 = r3.A07
            if (r0 != 0) goto L_0x0034
            boolean r0 = r3.A00
            if (r0 != 0) goto L_0x0031
            monitor-enter(r3)
            boolean r0 = r3.A07     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0025
            long r1 = r3.A01     // Catch:{ all -> 0x002c }
            int r0 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0025
            boolean r0 = r3.A03     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0027
            X.21f<java.lang.Object> r0 = r3.A02     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0022
            X.21f r0 = new X.21f     // Catch:{ all -> 0x002c }
            r0.<init>()     // Catch:{ all -> 0x002c }
            r3.A02 = r0     // Catch:{ all -> 0x002c }
        L_0x0022:
            r0.A00(r4)     // Catch:{ all -> 0x002c }
        L_0x0025:
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            return
        L_0x0027:
            r0 = 1
            r3.A04 = r0     // Catch:{ all -> 0x002c }
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            goto L_0x002f
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x002f:
            r3.A00 = r0
        L_0x0031:
            r3.test(r4)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass211.A00(java.lang.Object, long):void");
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A07) {
            this.A07 = true;
            this.A06.A0L(this);
        }
    }

    public final boolean test(Object obj) {
        if (this.A07 || EnumC139220y.accept(obj, this.A05)) {
            return true;
        }
        return false;
    }

    public AnonymousClass211(AnonymousClass1yM<? super T> r1, AnonymousClass210<T> r2) {
        this.A05 = r1;
        this.A06 = r2;
    }
}
