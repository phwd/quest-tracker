package X;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

/* renamed from: X.1gO  reason: invalid class name and case insensitive filesystem */
public final class C09061gO implements AnonymousClass1h5, AbstractC08811fX {
    @GuardedBy("requestLock")
    public AnonymousClass1gZ A00;
    @GuardedBy("requestLock")
    public AnonymousClass1gZ A01;
    @Nullable
    public final AbstractC08811fX A02;
    public final Object A03;
    public volatile AnonymousClass1h5 A04;
    public volatile AnonymousClass1h5 A05;

    @GuardedBy("requestLock")
    private boolean A00(AnonymousClass1h5 r3) {
        if (r3.equals(this.A05)) {
            return true;
        }
        if (this.A01 != AnonymousClass1gZ.FAILED || !r3.equals(this.A04)) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1h5
    public final void A1a() {
        synchronized (this.A03) {
            AnonymousClass1gZ r1 = this.A01;
            AnonymousClass1gZ r0 = AnonymousClass1gZ.RUNNING;
            if (r1 != r0) {
                this.A01 = r0;
                this.A05.A1a();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (A00(r4) == false) goto L_0x0014;
     */
    @Override // X.AbstractC08811fX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A1w(X.AnonymousClass1h5 r4) {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A03
            monitor-enter(r2)
            X.1fX r0 = r3.A02     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r0.A1w(r3)     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0014
        L_0x000d:
            boolean r1 = r3.A00(r4)     // Catch:{ all -> 0x0017 }
            r0 = 1
            if (r1 != 0) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r0
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.A1w(X.1h5):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (A00(r4) == false) goto L_0x0014;
     */
    @Override // X.AbstractC08811fX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A1x(X.AnonymousClass1h5 r4) {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A03
            monitor-enter(r2)
            X.1fX r0 = r3.A02     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r0.A1x(r3)     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0014
        L_0x000d:
            boolean r1 = r3.A00(r4)     // Catch:{ all -> 0x0017 }
            r0 = 1
            if (r1 != 0) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r0
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.A1x(X.1h5):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (A00(r4) == false) goto L_0x0014;
     */
    @Override // X.AbstractC08811fX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A1y(X.AnonymousClass1h5 r4) {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A03
            monitor-enter(r2)
            X.1fX r0 = r3.A02     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r0.A1y(r3)     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0014
        L_0x000d:
            boolean r1 = r3.A00(r4)     // Catch:{ all -> 0x0017 }
            r0 = 1
            if (r1 != 0) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r0
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.A1y(X.1h5):boolean");
    }

    @Override // X.AbstractC08811fX
    public final AbstractC08811fX A4q() {
        AbstractC08811fX r0;
        synchronized (this.A03) {
            AbstractC08811fX r02 = this.A02;
            if (r02 != null) {
                r0 = r02.A4q();
            } else {
                r0 = this;
            }
        }
        return r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r3.A04.A5q() != false) goto L_0x0014;
     */
    @Override // X.AbstractC08811fX, X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A5q() {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A03
            monitor-enter(r2)
            X.1h5 r0 = r3.A05     // Catch:{ all -> 0x0017 }
            boolean r0 = r0.A5q()     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0014
            X.1h5 r0 = r3.A04     // Catch:{ all -> 0x0017 }
            boolean r1 = r0.A5q()     // Catch:{ all -> 0x0017 }
            r0 = 0
            if (r1 == 0) goto L_0x0015
        L_0x0014:
            r0 = 1
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            return r0
        L_0x0017:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.A5q():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r4.A00 != r2) goto L_0x000e;
     */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A5s() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A03
            monitor-enter(r3)
            X.1gZ r0 = r4.A01     // Catch:{ all -> 0x0011 }
            X.1gZ r2 = X.AnonymousClass1gZ.CLEARED     // Catch:{ all -> 0x0011 }
            if (r0 != r2) goto L_0x000e
            X.1gZ r1 = r4.A00     // Catch:{ all -> 0x0011 }
            r0 = 1
            if (r1 == r2) goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.A5s():boolean");
    }

    @Override // X.AnonymousClass1h5
    public final boolean A5v(AnonymousClass1h5 r4) {
        if (!(r4 instanceof C09061gO)) {
            return false;
        }
        C09061gO r42 = (C09061gO) r4;
        if (!this.A05.A5v(r42.A05) || !this.A04.A5v(r42.A04)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC08811fX
    public final void A7p(AnonymousClass1h5 r4) {
        synchronized (this.A03) {
            if (!r4.equals(this.A04)) {
                this.A01 = AnonymousClass1gZ.FAILED;
                AnonymousClass1gZ r1 = this.A00;
                AnonymousClass1gZ r0 = AnonymousClass1gZ.RUNNING;
                if (r1 != r0) {
                    this.A00 = r0;
                    this.A04.A1a();
                }
            } else {
                this.A00 = AnonymousClass1gZ.FAILED;
                AbstractC08811fX r02 = this.A02;
                if (r02 != null) {
                    r02.A7p(this);
                }
            }
        }
    }

    @Override // X.AbstractC08811fX
    public final void A7u(AnonymousClass1h5 r3) {
        synchronized (this.A03) {
            if (r3.equals(this.A05)) {
                this.A01 = AnonymousClass1gZ.SUCCESS;
            } else if (r3.equals(this.A04)) {
                this.A00 = AnonymousClass1gZ.SUCCESS;
            }
            AbstractC08811fX r0 = this.A02;
            if (r0 != null) {
                r0.A7u(this);
            }
        }
    }

    @Override // X.AnonymousClass1h5
    public final void clear() {
        synchronized (this.A03) {
            this.A01 = AnonymousClass1gZ.CLEARED;
            this.A05.clear();
            AnonymousClass1gZ r1 = this.A00;
            AnonymousClass1gZ r0 = AnonymousClass1gZ.CLEARED;
            if (r1 != r0) {
                this.A00 = r0;
                this.A04.clear();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r4.A00 == r2) goto L_0x000e;
     */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isComplete() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A03
            monitor-enter(r3)
            X.1gZ r0 = r4.A01     // Catch:{ all -> 0x0011 }
            X.1gZ r2 = X.AnonymousClass1gZ.SUCCESS     // Catch:{ all -> 0x0011 }
            if (r0 == r2) goto L_0x000e
            X.1gZ r1 = r4.A00     // Catch:{ all -> 0x0011 }
            r0 = 0
            if (r1 != r2) goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.isComplete():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r4.A00 == r2) goto L_0x000e;
     */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isRunning() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A03
            monitor-enter(r3)
            X.1gZ r0 = r4.A01     // Catch:{ all -> 0x0011 }
            X.1gZ r2 = X.AnonymousClass1gZ.RUNNING     // Catch:{ all -> 0x0011 }
            if (r0 == r2) goto L_0x000e
            X.1gZ r1 = r4.A00     // Catch:{ all -> 0x0011 }
            r0 = 0
            if (r1 != r2) goto L_0x000f
        L_0x000e:
            r0 = 1
        L_0x000f:
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09061gO.isRunning():boolean");
    }

    @Override // X.AnonymousClass1h5
    public final void pause() {
        synchronized (this.A03) {
            AnonymousClass1gZ r0 = this.A01;
            AnonymousClass1gZ r1 = AnonymousClass1gZ.RUNNING;
            if (r0 == r1) {
                this.A01 = AnonymousClass1gZ.PAUSED;
                this.A05.pause();
            }
            if (this.A00 == r1) {
                this.A00 = AnonymousClass1gZ.PAUSED;
                this.A04.pause();
            }
        }
    }

    public C09061gO(Object obj, @Nullable AbstractC08811fX r3) {
        AnonymousClass1gZ r0 = AnonymousClass1gZ.CLEARED;
        this.A01 = r0;
        this.A00 = r0;
        this.A03 = obj;
        this.A02 = r3;
    }
}
