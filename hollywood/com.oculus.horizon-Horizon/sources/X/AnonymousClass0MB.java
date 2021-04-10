package X;

import java.io.IOException;

/* renamed from: X.0MB  reason: invalid class name */
public final class AnonymousClass0MB implements AbstractC07630v6 {
    public boolean A00;
    public boolean A01;
    public final long A02;
    public final AnonymousClass0B3 A03 = new AnonymousClass0B3();
    public final AnonymousClass0B3 A04 = new AnonymousClass0B3();
    public final /* synthetic */ C07900vZ A05;

    public AnonymousClass0MB(C07900vZ r2, long j) {
        this.A05 = r2;
        this.A02 = j;
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        C07900vZ r1 = this.A05;
        synchronized (r1) {
            this.A00 = true;
            this.A03.A08();
            r1.notifyAll();
        }
        r1.A02();
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
        X.C07920vb.A0I.execute(new X.AnonymousClass0MR(r9, new java.lang.Object[]{r9.A0A, 0}, 0, r12));
        r9.A00 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ac, code lost:
        return r6;
     */
    @Override // X.AbstractC07630v6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass0B3 r15, long r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0MB.read(X.0B3, long):long");
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A05.A09;
    }
}
