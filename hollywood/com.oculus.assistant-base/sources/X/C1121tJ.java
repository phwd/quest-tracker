package X;

/* renamed from: X.tJ  reason: case insensitive filesystem */
public final class C1121tJ implements AbstractC0608cr {
    public boolean A00;
    public boolean A01;
    public final AnonymousClass33 A02 = new AnonymousClass33();
    public final /* synthetic */ cS A03;

    public C1121tJ(cS cSVar) {
        this.A03 = cSVar;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r10 != r9.A00) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A00(boolean r13) {
        /*
            r12 = this;
            X.cS r5 = r12.A03
            monitor-enter(r5)
            X.34 r4 = r5.A0A     // Catch:{ all -> 0x005c }
            r4.A08()     // Catch:{ all -> 0x005c }
        L_0x0008:
            long r1 = r5.A01     // Catch:{ all -> 0x0057 }
            r6 = 0
            int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x0026
            boolean r0 = r12.A01     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            boolean r0 = r12.A00     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            X.cI r0 = r5.A03     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            r5.wait()     // Catch:{ InterruptedException -> 0x0020 }
            goto L_0x0008
        L_0x0020:
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x0026:
            r4.A0B()
            r5.A03()
            long r0 = r5.A01
            X.33 r9 = r12.A02
            long r2 = r9.A00
            long r10 = java.lang.Math.min(r0, r2)
            long r0 = r0 - r10
            r5.A01 = r0
            monitor-exit(r5)
            r4.A08()
            X.cQ r6 = r5.A07     // Catch:{ all -> 0x0052 }
            int r7 = r5.A06     // Catch:{ all -> 0x0052 }
            if (r13 == 0) goto L_0x004a
            long r0 = r9.A00     // Catch:{ all -> 0x0052 }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r8 = 1
            if (r2 == 0) goto L_0x004b
        L_0x004a:
            r8 = 0
        L_0x004b:
            r6.A04(r7, r8, r9, r10)     // Catch:{ all -> 0x0052 }
            r4.A0B()
            return
        L_0x0052:
            r0 = move-exception
            r4.A0B()
            throw r0
        L_0x0057:
            r0 = move-exception
            r4.A0B()
            throw r0
        L_0x005c:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1121tJ.A00(boolean):void");
    }

    @Override // X.AbstractC0608cr
    public final C0610ct A5G() {
        return this.A03.A0A;
    }

    @Override // X.AbstractC0608cr
    public final void A5k(AnonymousClass33 r7, long j) {
        AnonymousClass33 r5 = this.A02;
        r5.A5k(r7, j);
        while (r5.A00 >= 16384) {
            A00(false);
        }
    }

    @Override // java.io.Closeable, X.AbstractC0608cr, java.lang.AutoCloseable
    public final void close() {
        boolean z;
        cS cSVar = this.A03;
        synchronized (cSVar) {
            z = this.A00;
        }
        if (!z) {
            if (!cSVar.A05.A01) {
                AnonymousClass33 r6 = this.A02;
                if (r6.A00 <= 0) {
                    cSVar.A07.A04(cSVar.A06, true, null, 0);
                } else {
                    while (r6.A00 > 0) {
                        A00(true);
                    }
                }
            }
            synchronized (cSVar) {
                this.A00 = true;
            }
            cSVar.A07.A0E.A01();
            cSVar.A02();
        }
    }

    @Override // X.AbstractC0608cr, java.io.Flushable
    public final void flush() {
        cS cSVar = this.A03;
        synchronized (cSVar) {
            cSVar.A03();
        }
        while (this.A02.A00 > 0) {
            A00(false);
            cSVar.A07.A0E.A01();
        }
    }
}
