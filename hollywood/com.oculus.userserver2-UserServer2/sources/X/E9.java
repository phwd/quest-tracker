package X;

import java.io.IOException;

public final class E9 implements WF {
    public short A00;
    public byte A01;
    public int A02;
    public int A03;
    public int A04;
    public final Dp A05;

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        return -1;
     */
    @Override // X.WF
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass8k r9, long r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: X.E9.read(X.8k, long):long");
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A05.timeout();
    }

    public E9(Dp dp) {
        this.A05 = dp;
    }
}
