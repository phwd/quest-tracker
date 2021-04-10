package X;

import io.reactivex.annotations.NonNull;

/* renamed from: X.20z  reason: invalid class name and case insensitive filesystem */
public final class C139320z<T> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public AbstractC12271xB A00;
    public C139921f<Object> A01;
    public boolean A02;
    public final AnonymousClass1yM<? super T> A03;
    public volatile boolean A04;

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        r4 = r6.A03;
        r4.onNext(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2 = r6.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        if (r2 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        r6.A02 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
        r6.A01 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0046, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        r1 = r2.A03;
        r3 = r2.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004b, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004c, code lost:
        if (r1 == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004e, code lost:
        if (r2 >= r3) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0050, code lost:
        r0 = r1[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0052, code lost:
        if (r0 == null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0058, code lost:
        if (X.EnumC139220y.acceptFull(r0, r4) != false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005a, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        r1 = r1[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0063, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    @Override // X.AnonymousClass1yM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onNext(@io.reactivex.annotations.NonNull T r7) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C139320z.onNext(java.lang.Object):void");
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(@NonNull AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.A00, r2)) {
            this.A00 = r2;
            this.A03.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A00.dispose();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.A04) {
            synchronized (this) {
                if (!this.A04) {
                    if (this.A02) {
                        C139921f<Object> r1 = this.A01;
                        if (r1 == null) {
                            r1 = new C139921f<>();
                            this.A01 = r1;
                        }
                        r1.A00(EnumC139220y.complete());
                    } else {
                        this.A04 = true;
                        this.A02 = true;
                        this.A03.onComplete();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (r1 != false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        r3.A03.onError(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        return;
     */
    @Override // X.AnonymousClass1yM
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onError(@io.reactivex.annotations.NonNull java.lang.Throwable r4) {
        /*
            r3 = this;
            boolean r0 = r3.A04
            if (r0 != 0) goto L_0x0035
            monitor-enter(r3)
            boolean r0 = r3.A04     // Catch:{ all -> 0x0032 }
            r1 = 1
            if (r0 != 0) goto L_0x0029
            boolean r0 = r3.A02     // Catch:{ all -> 0x0032 }
            r3.A04 = r1
            if (r0 == 0) goto L_0x0026
            X.21f<java.lang.Object> r0 = r3.A01
            if (r0 != 0) goto L_0x001b
            X.21f r0 = new X.21f
            r0.<init>()
            r3.A01 = r0
        L_0x001b:
            java.lang.Object r2 = X.EnumC139220y.error(r4)
            java.lang.Object[] r1 = r0.A03
            r0 = 0
            r1[r0] = r2
            monitor-exit(r3)
            return
        L_0x0026:
            r3.A02 = r1
            r1 = 0
        L_0x0029:
            monitor-exit(r3)
            if (r1 != 0) goto L_0x0035
            X.1yM<? super T> r0 = r3.A03
            r0.onError(r4)
            return
        L_0x0032:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0035:
            X.AnonymousClass1y3.A01(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C139320z.onError(java.lang.Throwable):void");
    }

    public C139320z(@NonNull AnonymousClass1yM<? super T> r1) {
        this.A03 = r1;
    }
}
