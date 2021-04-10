package X;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

/* renamed from: X.1gN  reason: invalid class name and case insensitive filesystem */
public final class C09051gN implements AnonymousClass1h5, AbstractC08811fX {
    @GuardedBy("requestLock")
    public AnonymousClass1gZ A00;
    @GuardedBy("requestLock")
    public AnonymousClass1gZ A01;
    @GuardedBy("requestLock")
    public boolean A02;
    @Nullable
    public final AbstractC08811fX A03;
    public final Object A04;
    public volatile AnonymousClass1h5 A05;
    public volatile AnonymousClass1h5 A06;

    @Override // X.AnonymousClass1h5
    public final void A1a() {
        AnonymousClass1gZ r0;
        AnonymousClass1gZ r02;
        synchronized (this.A04) {
            this.A02 = true;
            try {
                if (!(this.A00 == AnonymousClass1gZ.SUCCESS || this.A01 == (r02 = AnonymousClass1gZ.RUNNING))) {
                    this.A01 = r02;
                    this.A06.A1a();
                }
                if (this.A02 && this.A00 != (r0 = AnonymousClass1gZ.RUNNING)) {
                    this.A00 = r0;
                    this.A05.A1a();
                }
            } finally {
                this.A02 = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r4.A00 == X.AnonymousClass1gZ.PAUSED) goto L_0x001c;
     */
    @Override // X.AbstractC08811fX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A1w(X.AnonymousClass1h5 r5) {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A04
            monitor-enter(r3)
            X.1fX r0 = r4.A03     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r0.A1w(r4)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001c
        L_0x000d:
            X.1h5 r0 = r4.A05     // Catch:{ all -> 0x001f }
            boolean r0 = r5.equals(r0)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001c
            X.1gZ r2 = r4.A00     // Catch:{ all -> 0x001f }
            X.1gZ r1 = X.AnonymousClass1gZ.PAUSED     // Catch:{ all -> 0x001f }
            r0 = 1
            if (r2 != r1) goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            return r0
        L_0x001f:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09051gN.A1w(X.1h5):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (A5q() != false) goto L_0x001c;
     */
    @Override // X.AbstractC08811fX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A1x(X.AnonymousClass1h5 r4) {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A04
            monitor-enter(r2)
            X.1fX r0 = r3.A03     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r0.A1x(r3)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001c
        L_0x000d:
            X.1h5 r0 = r3.A05     // Catch:{ all -> 0x001f }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x001c
            boolean r1 = r3.A5q()     // Catch:{ all -> 0x001f }
            r0 = 1
            if (r1 == 0) goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            monitor-exit(r2)     // Catch:{ all -> 0x001f }
            return r0
        L_0x001f:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09051gN.A1x(X.1h5):boolean");
    }

    @Override // X.AbstractC08811fX
    public final boolean A1y(AnonymousClass1h5 r4) {
        boolean z;
        synchronized (this.A04) {
            AbstractC08811fX r0 = this.A03;
            if ((r0 == null || r0.A1y(this)) && (r4.equals(this.A05) || this.A00 != AnonymousClass1gZ.SUCCESS)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @Override // X.AbstractC08811fX
    public final AbstractC08811fX A4q() {
        AbstractC08811fX r0;
        synchronized (this.A04) {
            AbstractC08811fX r02 = this.A03;
            if (r02 != null) {
                r0 = r02.A4q();
            } else {
                r0 = this;
            }
        }
        return r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r3.A05.A5q() != false) goto L_0x0014;
     */
    @Override // X.AbstractC08811fX, X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A5q() {
        /*
            r3 = this;
            java.lang.Object r2 = r3.A04
            monitor-enter(r2)
            X.1h5 r0 = r3.A06     // Catch:{ all -> 0x0017 }
            boolean r0 = r0.A5q()     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0014
            X.1h5 r0 = r3.A05     // Catch:{ all -> 0x0017 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.C09051gN.A5q():boolean");
    }

    @Override // X.AnonymousClass1h5
    public final boolean A5s() {
        boolean z;
        synchronized (this.A04) {
            z = false;
            if (this.A00 == AnonymousClass1gZ.CLEARED) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0017 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    @Override // X.AnonymousClass1h5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A5v(X.AnonymousClass1h5 r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof X.C09051gN
            r2 = 0
            if (r0 == 0) goto L_0x0018
            X.1gN r4 = (X.C09051gN) r4
            X.1h5 r0 = r3.A05
            if (r0 != 0) goto L_0x0024
            X.1h5 r0 = r4.A05
            if (r0 != 0) goto L_0x0018
        L_0x000f:
            X.1h5 r0 = r3.A06
            if (r0 != 0) goto L_0x0019
            X.1h5 r0 = r4.A06
            if (r0 != 0) goto L_0x0018
        L_0x0017:
            r2 = 1
        L_0x0018:
            return r2
        L_0x0019:
            X.1h5 r1 = r3.A06
            X.1h5 r0 = r4.A06
            boolean r0 = r1.A5v(r0)
            if (r0 == 0) goto L_0x0018
            goto L_0x0017
        L_0x0024:
            X.1h5 r1 = r3.A05
            X.1h5 r0 = r4.A05
            boolean r0 = r1.A5v(r0)
            if (r0 == 0) goto L_0x0018
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09051gN.A5v(X.1h5):boolean");
    }

    @Override // X.AbstractC08811fX
    public final void A7p(AnonymousClass1h5 r3) {
        synchronized (this.A04) {
            if (!r3.equals(this.A05)) {
                this.A01 = AnonymousClass1gZ.FAILED;
            } else {
                this.A00 = AnonymousClass1gZ.FAILED;
                AbstractC08811fX r0 = this.A03;
                if (r0 != null) {
                    r0.A7p(this);
                }
            }
        }
    }

    @Override // X.AbstractC08811fX
    public final void A7u(AnonymousClass1h5 r3) {
        synchronized (this.A04) {
            if (r3.equals(this.A06)) {
                this.A01 = AnonymousClass1gZ.SUCCESS;
            } else {
                this.A00 = AnonymousClass1gZ.SUCCESS;
                AbstractC08811fX r0 = this.A03;
                if (r0 != null) {
                    r0.A7u(this);
                }
                if (!this.A01.isComplete()) {
                    this.A06.clear();
                }
            }
        }
    }

    @Override // X.AnonymousClass1h5
    public final void clear() {
        synchronized (this.A04) {
            this.A02 = false;
            AnonymousClass1gZ r0 = AnonymousClass1gZ.CLEARED;
            this.A00 = r0;
            this.A01 = r0;
            this.A06.clear();
            this.A05.clear();
        }
    }

    @Override // X.AnonymousClass1h5
    public final boolean isComplete() {
        boolean z;
        synchronized (this.A04) {
            z = false;
            if (this.A00 == AnonymousClass1gZ.SUCCESS) {
                z = true;
            }
        }
        return z;
    }

    @Override // X.AnonymousClass1h5
    public final boolean isRunning() {
        boolean z;
        synchronized (this.A04) {
            z = false;
            if (this.A00 == AnonymousClass1gZ.RUNNING) {
                z = true;
            }
        }
        return z;
    }

    @Override // X.AnonymousClass1h5
    public final void pause() {
        synchronized (this.A04) {
            if (!this.A01.isComplete()) {
                this.A01 = AnonymousClass1gZ.PAUSED;
                this.A06.pause();
            }
            if (!this.A00.isComplete()) {
                this.A00 = AnonymousClass1gZ.PAUSED;
                this.A05.pause();
            }
        }
    }

    public C09051gN(Object obj, @Nullable AbstractC08811fX r3) {
        AnonymousClass1gZ r0 = AnonymousClass1gZ.CLEARED;
        this.A00 = r0;
        this.A01 = r0;
        this.A04 = obj;
        this.A03 = r3;
    }
}
