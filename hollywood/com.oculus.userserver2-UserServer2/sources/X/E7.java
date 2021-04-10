package X;

import java.io.IOException;

public final class E7 implements WF {
    public boolean A00;
    public boolean A01;
    public final long A02;
    public final AnonymousClass8k A03 = new AnonymousClass8k();
    public final AnonymousClass8k A04 = new AnonymousClass8k();
    public final /* synthetic */ C0156Wh A05;

    public E7(C0156Wh wh, long j) {
        this.A05 = wh;
        this.A02 = j;
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        C0156Wh wh = this.A05;
        synchronized (wh) {
            this.A00 = true;
            this.A03.A08();
            wh.notifyAll();
        }
        wh.A02();
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
        X.C0158Wj.A0I.execute(new X.EK(r9, new java.lang.Object[]{r9.A08, 0}, 0, r12));
        r9.A00 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a2, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a3, code lost:
        return r6;
     */
    @Override // X.WF
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass8k r15, long r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 201
        */
        throw new UnsupportedOperationException("Method not decompiled: X.E7.read(X.8k, long):long");
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A05.A09;
    }
}
