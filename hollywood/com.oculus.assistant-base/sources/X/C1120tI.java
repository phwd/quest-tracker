package X;

/* renamed from: X.tI  reason: case insensitive filesystem */
public final class C1120tI implements AbstractC0609cs {
    public boolean A00;
    public boolean A01;
    public final long A02;
    public final AnonymousClass33 A03 = new AnonymousClass33();
    public final AnonymousClass33 A04 = new AnonymousClass33();
    public final /* synthetic */ cS A05;

    public C1120tI(cS cSVar, long j) {
        this.A05 = cSVar;
        this.A02 = j;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r12 = r9.A00 + r6;
        r9.A00 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008e, code lost:
        if (r12 < ((long) (r9.A04.A00() / 2))) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        X.C0584cQ.A0I.execute(new X.tV(r9, new java.lang.Object[]{r9.A08, 0}, 0, r12));
        r9.A00 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ac, code lost:
        return r6;
     */
    @Override // X.AbstractC0609cs
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A4c(X.AnonymousClass33 r15, long r16) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1120tI.A4c(X.33, long):long");
    }

    @Override // X.AbstractC0609cs
    public final C0610ct A5G() {
        return this.A05.A09;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    public final void close() {
        cS cSVar = this.A05;
        synchronized (cSVar) {
            this.A00 = true;
            this.A03.A07();
            cSVar.notifyAll();
        }
        cSVar.A02();
    }
}
