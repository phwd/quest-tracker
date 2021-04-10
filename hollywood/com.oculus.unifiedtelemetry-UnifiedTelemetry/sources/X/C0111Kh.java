package X;

import java.io.IOException;

/* renamed from: X.Kh  reason: case insensitive filesystem */
public final class C0111Kh implements AbstractC0312cb {
    public boolean A00;
    public boolean A01;
    public final long A02;
    public final AnonymousClass98 A03 = new AnonymousClass98();
    public final AnonymousClass98 A04 = new AnonymousClass98();
    public final /* synthetic */ C0335d3 A05;

    public C0111Kh(C0335d3 d3Var, long j) {
        this.A05 = d3Var;
        this.A02 = j;
    }

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
        C0335d3 d3Var = this.A05;
        synchronized (d3Var) {
            this.A00 = true;
            this.A03.A08();
            d3Var.notifyAll();
        }
        d3Var.A02();
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
        X.C0337d5.A0I.execute(new X.C0124Ku(r9, new java.lang.Object[]{r9.A0A, 0}, 0, r12));
        r9.A00 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a2, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a3, code lost:
        return r6;
     */
    @Override // X.AbstractC0312cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass98 r15, long r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 201
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0111Kh.read(X.98, long):long");
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A05.A09;
    }
}
