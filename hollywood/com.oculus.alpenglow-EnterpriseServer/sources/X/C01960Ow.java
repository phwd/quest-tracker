package X;

import java.io.IOException;

/* renamed from: X.0Ow  reason: invalid class name and case insensitive filesystem */
public final class C01960Ow implements AbstractC04550h0 {
    public short A00;
    public byte A01;
    public int A02;
    public int A03;
    public int A04;
    public final AnonymousClass0Od A05;

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
    public final void close() throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        return -1;
     */
    @Override // X.AbstractC04550h0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass0HR r9, long r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01960Ow.read(X.0HR, long):long");
    }

    @Override // X.AbstractC04550h0
    public final C04540gz timeout() {
        return this.A05.timeout();
    }

    public C01960Ow(AnonymousClass0Od r1) {
        this.A05 = r1;
    }
}
