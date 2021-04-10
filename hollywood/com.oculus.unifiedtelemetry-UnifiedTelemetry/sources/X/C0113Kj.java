package X;

import java.io.IOException;

/* renamed from: X.Kj  reason: case insensitive filesystem */
public final class C0113Kj implements AbstractC0312cb {
    public short A00;
    public byte A01;
    public int A02;
    public int A03;
    public int A04;
    public final KC A05;

    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        return -1;
     */
    @Override // X.AbstractC0312cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass98 r9, long r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0113Kj.read(X.98, long):long");
    }

    @Override // X.AbstractC0312cb
    public final ca timeout() {
        return this.A05.timeout();
    }

    public C0113Kj(KC kc) {
        this.A05 = kc;
    }
}
