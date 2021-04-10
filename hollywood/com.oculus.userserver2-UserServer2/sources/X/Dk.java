package X;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class Dk implements WF {
    public int A00 = 0;
    public final CRC32 A01 = new CRC32();
    public final Inflater A02;
    public final Dp A03;
    public final Dj A04;

    public static void A00(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void A01(AnonymousClass8k r8, long j, long j2) {
        WI wi = r8.A01;
        while (j >= ((long) (wi.A00 - wi.A01))) {
            j -= (long) (wi.A00 - wi.A01);
            wi = wi.A02;
        }
        while (j2 > 0) {
            int i = (int) (((long) wi.A01) + j);
            int min = (int) Math.min((long) (wi.A00 - i), j2);
            this.A01.update(wi.A06, i, min);
            j2 -= (long) min;
            wi = wi.A02;
            j = 0;
        }
    }

    @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A04.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d9, code lost:
        if (r0 == 2) goto L_0x00db;
     */
    @Override // X.WF
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long read(X.AnonymousClass8k r21, long r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 289
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Dk.read(X.8k, long):long");
    }

    @Override // X.WF
    public final WE timeout() {
        return this.A03.timeout();
    }

    public Dk(WF wf) {
        if (wf != null) {
            Inflater inflater = new Inflater(true);
            this.A02 = inflater;
            C00148h r1 = new C00148h(wf);
            this.A03 = r1;
            this.A04 = new Dj(r1, inflater);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }
}
