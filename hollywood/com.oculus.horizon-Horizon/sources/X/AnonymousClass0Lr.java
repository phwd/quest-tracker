package X;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* renamed from: X.0Lr  reason: invalid class name */
public final class AnonymousClass0Lr implements AbstractC07630v6 {
    public int A00 = 0;
    public final CRC32 A01 = new CRC32();
    public final Inflater A02;
    public final AnonymousClass0Lw A03;
    public final AnonymousClass0Lq A04;

    public static void A00(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void A01(AnonymousClass0B3 r8, long j, long j2) {
        C07660v9 r3 = r8.A01;
        while (j >= ((long) (r3.A01 - r3.A02))) {
            j -= (long) (r3.A01 - r3.A02);
            r3 = r3.A00;
        }
        while (j2 > 0) {
            int i = (int) (((long) r3.A02) + j);
            int min = (int) Math.min((long) (r3.A01 - i), j2);
            this.A01.update(r3.A06, i, min);
            j2 -= (long) min;
            r3 = r3.A00;
            j = 0;
        }
    }

    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A04.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d9, code lost:
        if (r0 == 2) goto L_0x00db;
     */
    @Override // X.AbstractC07630v6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass0B3 r21, long r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 289
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Lr.read(X.0B3, long):long");
    }

    @Override // X.AbstractC07630v6
    public final C07620v5 timeout() {
        return this.A03.timeout();
    }

    public AnonymousClass0Lr(AbstractC07630v6 r4) {
        if (r4 != null) {
            Inflater inflater = new Inflater(true);
            this.A02 = inflater;
            C00560Au r1 = new C00560Au(r4);
            this.A03 = r1;
            this.A04 = new AnonymousClass0Lq(r1, inflater);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }
}
