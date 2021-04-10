package X;

import java.io.IOException;

/* renamed from: X.0MD  reason: invalid class name */
public final class AnonymousClass0MD implements AbstractC07630v6 {
    public short A00;
    public byte A01;
    public int A02;
    public int A03;
    public int A04;
    public final AnonymousClass0Lw A05;

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        return -1;
     */
    @Override // X.AbstractC07630v6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass0B3 r9, long r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0MD.read(X.0B3, long):long");
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A05.timeout();
    }

    public AnonymousClass0MD(AnonymousClass0Lw r1) {
        this.A05 = r1;
    }
}
