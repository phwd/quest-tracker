package X;

import java.io.IOException;

/* renamed from: X.0Os  reason: invalid class name and case insensitive filesystem */
public final class C01930Os implements AbstractC04550h0 {
    public boolean A00;
    public boolean A01;
    public final long A02;
    public final AnonymousClass0HR A03 = new AnonymousClass0HR();
    public final AnonymousClass0HR A04 = new AnonymousClass0HR();
    public final /* synthetic */ C04760hS A05;

    public C01930Os(C04760hS r2, long j) {
        this.A05 = r2;
        this.A02 = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
    public final void close() throws IOException {
        C04760hS r1 = this.A05;
        synchronized (r1) {
            this.A00 = true;
            this.A03.A08();
            r1.notifyAll();
        }
        r1.A02();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0078, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r12 = r9.A00 + r6;
        r9.A00 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        if (r12 < ((long) (r9.A04.A00() / 2))) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008b, code lost:
        X.C04780hU.A0I.execute(new X.AnonymousClass0PD(r9, new java.lang.Object[]{r9.A0A, 0}, 0, r12));
        r9.A00 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a2, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a3, code lost:
        return r6;
     */
    @Override // X.AbstractC04550h0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass0HR r15, long r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 201
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01930Os.read(X.0HR, long):long");
    }

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A05.A09;
    }
}
